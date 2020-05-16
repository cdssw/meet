package com.moim.meet.service.meet;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.User;
import com.moim.meet.except.ErrorCode;
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
	
	@Transactional
	@Override
	public Long createMeet(MeetDto.MeetReq dto) {
		final User user = commonComponent.findById(userRepository, dto.getUserId(), User.class, ErrorCode.USER_NOT_FOUND);
		Meet meet = dto.toEntity();
		meet.editUser(user);
		return meetRepository.save(meet).getId();
	}

	@Override
	public List<MeetDto.Res> getMeetList() {
		return meetRepository.findAll().stream().map(m -> modelMapper.map(m, MeetDto.Res.class)).collect(Collectors.toList());		
	}

	@Transactional
	@Override
	public Res editMeet(long id, MeetDto.MeetReq dto) {
		final User user = commonComponent.findById(userRepository, dto.getUserId(), User.class, ErrorCode.USER_NOT_FOUND);
		final Meet meet = commonComponent.findById(meetRepository, id, Meet.class);
		meet.editMeet(dto); // meet 내용 수정
		meet.editUser(user); // 수정자 update
		return modelMapper.map(meet, MeetDto.Res.class);
	}

	@Override
	public Res getMeet(long id) {
		final Meet meet = commonComponent.findById(meetRepository, id, Meet.class);
		return modelMapper.map(meet, MeetDto.Res.class);
	}

	@Transactional
	@Override
	public void deleteMeet(long id) {
		commonComponent.findById(meetRepository, id, Meet.class); // exist check
		meetRepository.deleteById(id);
	}

	@Transactional(readOnly = true) // 성능향상을 위해
	@Override
	public Page<Res> getMeetListByPage(Pageable pageable) {
		return meetRepository.findAll(pageable).map(m -> modelMapper.map(m, MeetDto.Res.class));
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
