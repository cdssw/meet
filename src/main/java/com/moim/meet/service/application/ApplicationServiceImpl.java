package com.moim.meet.service.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.Approval;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.User;
import com.moim.meet.except.ErrorCode;
import com.moim.meet.except.MeetBusinessException;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * ApplicationServiceImpl.java
 * 
 * @author cdssw
 * @since May 2, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 2, 2020    cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@Service
public class ApplicationServiceImpl implements ApplicationService {

	private CommonComponent commonComponent;
	
	private MeetRepository meetRepository;
	private UserRepository userRepository;
	private ApplicationMeetRepository applicationMeetRepository;

	
	// Meet 참석 지원
	@Transactional
	@Override
	public void applicationMeet(ApplicationDto.ApplicationReq dto) {
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		final User user = commonComponent.findById(userRepository, dto.getUserId(), User.class, ErrorCode.USER_NOT_FOUND);
		final long alreadyApp = applicationMeetRepository.countByMeetAndUserGroupByMeet(dto.getMeetId(), dto.getUserId());
		if(alreadyApp > 0) {
			throw new MeetBusinessException(ErrorCode.DUPLICATION_APP_MEET);
		}
		
		if(meet.getRecruitment() <= meet.getApplication()) {
			throw new MeetBusinessException(ErrorCode.FULL_RECRUITMENT_MEET);
		}
		
		final ApplicationMeet applicationMeet = ApplicationMeet.builder()
				.meet(meet).user(user)
				.approval(Approval.builder().approvalYn(false).build())
				.build();
		applicationMeetRepository.save(applicationMeet);
		meet.applicationMeet(); // 참석 지원자 카운트 증가
	}

	// 승인처리
	@Transactional
	@Override
	public void approval(ApplicationDto.ApprovalReq dto) {
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		final User user = commonComponent.findById(userRepository, dto.getUserId(), User.class, ErrorCode.USER_NOT_FOUND);
		if(meet.getUser().getId() != dto.getLeaderId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET); // 리더가 아니면 승인처리 할수 없음.
		}
		final ApplicationMeet applicationMeet = applicationMeetRepository.findByMeetAndUser(meet, user);
		applicationMeet.getApproval().approval(); // 승인처리
	}

	// 승인취소처리
	@Transactional
	@Override
	public void cancel(ApplicationDto.ApprovalReq dto) {
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		final User user = commonComponent.findById(userRepository, dto.getUserId(), User.class, ErrorCode.USER_NOT_FOUND);
		if(meet.getUser().getId() != dto.getLeaderId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET); // 리더가 아니면 승인취소처리 할수 없음.
		}
		final ApplicationMeet applicationMeet = applicationMeetRepository.findByMeetAndUser(meet, user);
		applicationMeet.getApproval().cancel(); // 승인취소처리
	}

}
