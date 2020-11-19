package com.moim.meet.service.application;

import java.util.List;

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
import com.moim.meet.service.application.ApplicationDto.ApplicationInfoRes;
import com.moim.meet.service.application.ApplicationDto.EstimateReq;

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
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

	private CommonComponent commonComponent;
	
	private MeetRepository meetRepository;
	private UserRepository userRepository;
	private ApplicationMeetRepository applicationMeetRepository;

	
	// Meet 참석 지원
	@Override
	public void applicationMeet(ApplicationDto.ApplicationReq dto, String username) {
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		User user = userRepository.findByUsername(username);
		final long alreadyApp = applicationMeetRepository.countByMeetAndUserGroupByMeet(dto.getMeetId(), user.getId());
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
	}

	// 승인처리
	@Override
	public void approval(ApplicationDto.ApprovalReq dto, String username) {
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		User user = userRepository.findByUsername(username);
		if(meet.getUser().getId() != user.getId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET); // 리더가 아니면 승인처리 할수 없음.
		}
		
		User applicator = commonComponent.findById(userRepository, dto.getUserId(), User.class);
		final ApplicationMeet applicationMeet = applicationMeetRepository.findByMeetAndUser(meet, applicator);
		applicationMeet.getApproval().approval(); // 승인처리
		meet.applicationMeet(); // 참석 지원자 카운트 증가
	}

	// 승인취소처리
	@Override
	public void cancel(ApplicationDto.ApprovalReq dto, String username) {
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		User user = userRepository.findByUsername(username);
		if(meet.getUser().getId() != user.getId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET); // 리더가 아니면 승인취소처리 할수 없음.
		}
		
		User applicator = commonComponent.findById(userRepository, dto.getUserId(), User.class);
		final ApplicationMeet applicationMeet = applicationMeetRepository.findByMeetAndUser(meet, applicator);
		applicationMeet.getApproval().cancel(); // 승인취소처리
	}

	// 지원자 리스트
	@Override
	public List<ApplicationDto.ApplicationUserRes> applicationUser(long meetId) {
		return applicationMeetRepository.findUserByApplicationMeet(meetId);
	}

	@Override
	public ApplicationInfoRes getApplicatorInfo(Long meetId, String applicator, String username) {
		User user = userRepository.findByUsername(username);
		final Meet meet = commonComponent.findById(meetRepository, meetId, Meet.class);
		User app = userRepository.findByUsername(applicator);
		if(meet.getUser().getId() != user.getId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET); // 리더가 아니면 승인취소처리 할수 없음.
		}
		if(app == null) {
			throw new MeetBusinessException(ErrorCode.USER_NOT_FOUND);
		}
		Integer meetCnt = applicationMeetRepository.countByUser(app);
		Integer estimateAvg = applicationMeetRepository.avgEstimate(app.getId());
		ApplicationInfoRes res = ApplicationInfoRes.builder()
				.meetCnt(meetCnt)
				.estimateAvg(estimateAvg)
				.build();
		
		return res;
	}

	@Override
	public void estimate(EstimateReq dto, String username) {
		User user = userRepository.findByUsername(username);
		final Meet meet = commonComponent.findById(meetRepository, dto.getMeetId(), Meet.class);
		User app = userRepository.findByUsername(dto.getUsername());
		if(meet.getUser().getId() != user.getId()) {
			throw new MeetBusinessException(ErrorCode.INVALID_LEADER_MEET); // 리더가 아니면 승인취소처리 할수 없음.
		}
		if(app == null) {
			throw new MeetBusinessException(ErrorCode.USER_NOT_FOUND);
		}
		
		ApplicationMeet applicationMeet = applicationMeetRepository.findByMeetAndUser(meet, app);
		applicationMeet.estimate(dto);
	}

	@Override
	public Boolean isJoin(Long meetId, String username) {
		User user = userRepository.findByUsername(username);
		final Meet meet = commonComponent.findById(meetRepository, meetId, Meet.class);		
		int count = applicationMeetRepository.countByMeetAndUser(meet, user);
		return count > 0 ? true : false;
	}
}
