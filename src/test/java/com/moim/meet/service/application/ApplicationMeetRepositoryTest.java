package com.moim.meet.service.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.config.JpaAuditingConfig;
import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.Approval;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.User;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;
import com.moim.meet.service.mypage.MyPageDto;

/**
 * ApplicationMeetCustomRepository.java
 * 
 * @author cdssw
 * @since Apr 29, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 29, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {JpaAuditingConfig.class}))
@ActiveProfiles("test")
public class ApplicationMeetRepositoryTest {

	@Autowired ApplicationMeetRepository applicationMeetRepository;
	@Autowired MeetRepository meetRepository;
	@Autowired UserRepository userRepository;
	
	CommonComponent commonComponent;

	@Before
	public void setUp() {
		commonComponent = new CommonComponent();
	}
	
	@After
	public void cleanUp() {
		applicationMeetRepository.deleteAll();
	}
	
//	@Test
//	public void testCountByMeetAndUserGroupByMeet() {
//		// given
//		Meet meet = commonComponent.findById(meetRepository, 1L, Meet.class);
//		User user = commonComponent.findById(userRepository, 1L, User.class);
//		
//		ApplicationMeet applicationMeet = ApplicationMeet.builder()
//				.meet(meet).user(user)
//				.approval(Approval.builder().approvalYn(false).build())
//				.build();
//		applicationMeetRepository.save(applicationMeet);
//		
//		// when
//		long count = applicationMeetRepository.countByMeetAndUserGroupByMeet(1, 1);
//		
//		// then
//		assertEquals(count, 1);
//	}
//
//	@Test
//	public void testFindMyPageApplication() {
//		// given
//		Pageable pageable = PageRequest.of(0, 10);
//		MyPageDto.ApplicationReq dto = MyPageDto.ApplicationReq.builder().build();
//
//		// when
//		Page<MyPageDto.ApplicationRes> res = applicationMeetRepository.findMyPageApplication(1L, dto, pageable);
//		
//		// then
//		assertEquals(res.getTotalElements(), 3);
//		assertEquals(res.getContent().get(0).getApproval().isApprovalYn(), false);
//	}
	
	@Test
	public void testFindUserByApplicationMeet() {
		// when
		List<ApplicationDto.ApplicationUserRes> res = applicationMeetRepository.findUserByApplicationMeet(1L);
		
		// then
		assertEquals(res.size(), 2);
	}
}
