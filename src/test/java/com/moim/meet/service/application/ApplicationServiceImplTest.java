package com.moim.meet.service.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.Approval;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.Place;
import com.moim.meet.entity.User;
import com.moim.meet.except.MeetBusinessException;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;
import com.moim.meet.service.application.ApplicationDto.ApplicationReq;
import com.moim.meet.service.application.ApplicationDto.ApprovalReq;

/**
 * ApplicationServiceImplTest.java
 * 
 * @author cdssw
 * @since May 2, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 2, 2020    cdssw            최초 생성
 * </pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest {

	// 테스트 하고자 하는 class
	private ApplicationServiceImpl applicationServiceImpl;

	// repository를 테스트 하지 않음
	@Mock private MeetRepository meetRepository;
	@Mock private UserRepository userRepository;
	@Mock private ApplicationMeetRepository applicationMeetRepository;
	
	private User user;
	private Meet meet1;
	private ApplicationReq applicationReq;
	private ApprovalReq approvalReq;
	
	@Before
	public void setUp() {
		CommonComponent commonComponent = new CommonComponent();
		applicationServiceImpl = new ApplicationServiceImpl(commonComponent, meetRepository, userRepository, applicationMeetRepository);
		
		user = User.builder().id(1L).userNm("Andrew").build();
		meet1 = Meet.builder().meetNm("First meet").meetDesc("First save meet").cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build()).recruitment(3).application(1)
				.user(user)
				.build();
		
		applicationReq = ApplicationReq.builder()
				.meetId(1L).userId(1L).build();
		
		approvalReq = ApprovalReq.builder()
				.meetId(1L).userId(1L).leaderId(1L)
				.build();

	}
	
	// 중복지원 예외 Test
	@Test(expected = MeetBusinessException.class)
	public void testApplicationDuplicationException() {
		// given
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet1));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(applicationMeetRepository.countByMeetAndUserGroupByMeet(anyLong(), anyLong())).willReturn(1L);
		
		// when
		applicationServiceImpl.applicationMeet(applicationReq);
	}
	
	// 모집완료 예외 Test
	@Test(expected = MeetBusinessException.class)
	public void testApplicationFullRecruitmentException() {
		// given
		Meet meet = Meet.builder().meetNm("First meet").meetDesc("First save meet").cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build()).recruitment(3).application(3).build();
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(applicationMeetRepository.countByMeetAndUserGroupByMeet(anyLong(), anyLong())).willReturn(0L);
		
		// when
		applicationServiceImpl.applicationMeet(applicationReq);
	}
	
	@Test
	public void testApplicationMeet() {
		// given
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet1));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(applicationMeetRepository.countByMeetAndUserGroupByMeet(anyLong(), anyLong())).willReturn(0L);
		
		// when
		applicationServiceImpl.applicationMeet(applicationReq);
		
		// then
		verify(applicationMeetRepository).save(any(ApplicationMeet.class));
	}
	
	@Test
	public void testApplicationApproval() {
		// given
		Meet meet = mock(Meet.class);
		User user = mock(User.class);
		ApplicationMeet applicationMeet = mock(ApplicationMeet.class);
		Approval approval = mock(Approval.class);
		
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(meet.getUser()).willReturn(user);
		given(user.getId()).willReturn(1L);
		given(applicationMeetRepository.findByMeetAndUser(any(Meet.class), any(User.class))).willReturn(applicationMeet);
		given(applicationMeet.getApproval()).willReturn(approval);
		
		
		// when
		applicationServiceImpl.approval(approvalReq);
		
		// then
		verify(approval).approval();
	}
	
	@Test(expected = MeetBusinessException.class)
	public void testApplicationApprovalExcept() {
		// given
		Meet meet = mock(Meet.class);
		User user = mock(User.class);
		
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(meet.getUser()).willReturn(user);
		given(user.getId()).willReturn(2L); // leader가 2
		
		// when
		applicationServiceImpl.approval(approvalReq); // 승인처리자가 1이므로 except
	}
	
	@Test
	public void testApplicationCancel() {
		// given
		Meet meet = mock(Meet.class);
		User user = mock(User.class);
		ApplicationMeet applicationMeet = mock(ApplicationMeet.class);
		Approval approval = mock(Approval.class);
		
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(meet.getUser()).willReturn(user);
		given(user.getId()).willReturn(1L);
		given(applicationMeetRepository.findByMeetAndUser(any(Meet.class), any(User.class))).willReturn(applicationMeet);
		given(applicationMeet.getApproval()).willReturn(approval);
		
		// when
		applicationServiceImpl.cancel(approvalReq);
		
		// then
		verify(approval).cancel();
	}
	
	@Test(expected = MeetBusinessException.class)
	public void testApplicationCancelExcept() {
		// given
		Meet meet = mock(Meet.class);
		User user = mock(User.class);
		
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		given(meet.getUser()).willReturn(user);
		given(user.getId()).willReturn(2L); // leader가 2
		
		// when
		applicationServiceImpl.cancel(approvalReq); // 취소처리자가 1이므로 except
	}
}
