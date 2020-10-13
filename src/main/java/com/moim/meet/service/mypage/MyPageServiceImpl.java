package com.moim.meet.service.mypage;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.User;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.FileRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;
import com.moim.meet.service.mypage.MyPageDto.ApplicationReq;
import com.moim.meet.service.mypage.MyPageDto.ApplicationRes;
import com.moim.meet.service.mypage.MyPageDto.OpenedReq;
import com.moim.meet.service.mypage.MyPageDto.OpenedRes;

import lombok.AllArgsConstructor;

/**
 * MyPageServiceImpl.java
 * 
 * @author cdssw
 * @since May 9, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 9, 2020   cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor // private 변수에 autowired 추가 안해도 됨
@Service
public class MyPageServiceImpl implements MyPageService {

	private MeetRepository meetRepository;
	private UserRepository userRepository;
	private ApplicationMeetRepository applicationMeetRepository;
	private FileRepository fileRepository;
	private CommonComponent commonComponent;
	
	@Transactional(readOnly = true)
	@Override
	public Page<OpenedRes> opened(String username, OpenedReq dto, Pageable pageable) {
		User user = userRepository.findByUsername(username);
		return meetRepository.findMyPageOpened(user.getId(), dto, pageable).map(m -> {
			m.setImgList(fileRepository.findByMeet(commonComponent.findById(meetRepository, m.getId(), Meet.class))
					.stream().map(f -> f.getFileId()).collect(Collectors.toList())
					);
			return m;
		});
	}

	@Transactional(readOnly = true)
	@Override
	public Page<ApplicationRes> application(String username, ApplicationReq dto, Pageable pageable) {
		User user = userRepository.findByUsername(username);
		return applicationMeetRepository.findMyPageApplication(user.getId(), dto, pageable).map(m -> {
			m.setImgList(fileRepository.findByMeet(commonComponent.findById(meetRepository, m.getId(), Meet.class))
					.stream().map(f -> f.getFileId()).collect(Collectors.toList())
					);
			return m;
		});
	}
}
