package com.moim.meet.service.meet;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.Approval;
import com.moim.meet.entity.File;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.User;
import com.moim.meet.except.ErrorCode;
import com.moim.meet.except.MeetBusinessException;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.FileRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;
import com.moim.meet.service.meet.MeetDto.Res;

import lombok.AllArgsConstructor;

/**
 * MeetSerivceImpl.java
 * 
 * @author cdssw
 * @since Apr 6, 2020
 * @description 서비스 구현체
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 6, 2020    cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor // private 변수에 autowired 추가 안해도 됨
@Service
public class MeetServiceImpl implements MeetService {

	private ModelMapper modelMapper;
	private CommonComponent commonComponent;
	
	private MeetRepository meetRepository;
	private UserRepository userRepository;
	private ApplicationMeetRepository applicationMeetRepository;
	private FileRepository fileRepository;
	
	@Transactional
	@Override
	public Long createMeet(MeetDto.MeetReq dto, final String username) {
		User user = userRepository.findByUsername(username);
		Meet meet = dto.toEntity();
		meet.setLeader(user);
		
		// meet 생성
		Long id = meetRepository.save(meet).getId();
		
		// 리더 application, approval 처리
		ApplicationMeet applicationMeet = ApplicationMeet.builder()
				.meet(meet)
				.user(user)
				.approval(Approval.builder().approvalYn(true).approvalDt(LocalDateTime.now()).build())
				.build();
		applicationMeetRepository.save(applicationMeet);
		
		// 파일id 추가
		for(Long fileId : dto.getImgList()) {
			File file = File.builder().fileId(fileId).meet(meet).build();
			fileRepository.save(file);
		}
		
		return id;
	}

	@Override
	public List<MeetDto.Res> getMeetList() {
		return meetRepository.findAll().stream().map(m -> modelMapper.map(m, MeetDto.Res.class)).collect(Collectors.toList());		
	}

	@Transactional
	@Override
	public Res editMeet(final long id, final String username, MeetDto.MeetReq dto) {
		final User user = userRepository.findByUsername(username);
		final Meet meet = commonComponent.findById(meetRepository, id, Meet.class);
		if(user.getId() != meet.getUser().getId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET);
		}
		meet.editMeet(dto); // meet 내용 수정
		return modelMapper.map(meet, MeetDto.Res.class);
	}

	@Override
	public Res getMeet(final long id, final String username) {
		final User user = userRepository.findByUsername(username);
		final Meet meet = commonComponent.findById(meetRepository, id, Meet.class);
		MeetDto.Res res = modelMapper.map(meet, MeetDto.Res.class);
		res.setImgList(fileRepository.findByMeet(meet).stream().map(m -> m.getFileId()).collect(Collectors.toList()));
		ApplicationMeet applicationMeet = applicationMeetRepository.findByMeetAndUser(meet, user);
		if(applicationMeet != null) {
			res.setApprovalYn(true);
			res.setApprovalDt(applicationMeet.getApproval().getApprovalDt());
		}

		return res;
	}

	@Transactional
	@Override
	public void deleteMeet(final long id, final String username) {
		final User user = userRepository.findByUsername(username);
		final Meet meet = commonComponent.findById(meetRepository, id, Meet.class);
		if(meet == null) {
			throw new MeetBusinessException(ErrorCode.ELEMENT_NOT_FOUND);
		}
		if(user.getId() != meet.getUser().getId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET);
		}
		applicationMeetRepository.deleteByMeet(meet); // 지원테이블에서 해당 meet 삭제
		meetRepository.deleteById(id); // meet 삭제
	}

	@Transactional(readOnly = true) // 성능향상을 위해
	@Override
	public Page<Res> getMeetListByPage(Pageable pageable) {
		return meetRepository.findAllByOrderByIdDesc(pageable).map(m -> {
			Res r = modelMapper.map(m, MeetDto.Res.class);
			r.setImgList(fileRepository.findByMeet(m).stream().map(f -> f.getFileId()).collect(Collectors.toList()));
			return r;
		});
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Res> search(MeetDto.SearchReq dto, Pageable pageable) {
		Page<Meet> meetList = meetRepository.findSearch(dto, pageable);
		return new PageImpl<>(
				meetList.getContent().stream().map(m -> modelMapper.map(m, MeetDto.Res.class)).collect(Collectors.toList()),
				pageable,
				meetList.getTotalElements());
	}
}
