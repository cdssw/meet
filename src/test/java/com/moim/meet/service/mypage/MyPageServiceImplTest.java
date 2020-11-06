package com.moim.meet.service.mypage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.Address;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.Term;
import com.moim.meet.entity.User;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.ChatRepository;
import com.moim.meet.repository.FileRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;

/**
 * MyPageServiceImplTest.java
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
@RunWith(MockitoJUnitRunner.class)
public class MyPageServiceImplTest {

	// 테스트 하고자 하는 class
	private MyPageServiceImpl myPageServiceImpl;

	// repository를 테스트 하지 않음
	@Mock private MeetRepository meetRepository;
	@Mock private UserRepository userRepository;
	@Mock private ApplicationMeetRepository applicationMeetRepository;
	@Mock private FileRepository fileRepository;
	@Mock private ChatRepository chatRepository;
	
	private MyPageDto.ApplicationRes app1;
	private MyPageDto.ApplicationRes app2;
	
	private User user;
	private Meet meet1;
	
	@Before
	public void setUp() {
		CommonComponent commonComponent = new CommonComponent();
		myPageServiceImpl = new MyPageServiceImpl(meetRepository, userRepository, applicationMeetRepository, fileRepository, chatRepository, commonComponent);
		
		app1 = MyPageDto.ApplicationRes.builder()
				.title("Meet name 2")
				.content("Second save meet")
				.cost(10)
				.address(Address.builder().address1("address").address2("detail").build())
				.recruitment(3)
				.application(1)
				.build();
		app2 = MyPageDto.ApplicationRes.builder()
				.title("Meet name 2")
				.content("Second save meet")
				.cost(10)
				.address(Address.builder().address1("address").address2("detail").build())
				.recruitment(3)
				.application(1)
				.build();
		
		user = User.builder().id(1L).userNm("Andrew").build();
		meet1 = Meet.builder()
				.title("First meet")
				.content("First save meet")
				.cost(10)
				.address(Address.builder().address1("address").address2("detail").build())
				.term(Term.builder()
						.startDt("2020-09-01")
						.endDt("2020-09-30")
						.startTm("10:00")
						.endTm("16:00")
						.detailDay(64).build())
				.recruitment(3)
				.application(1)
				.user(user)
				.build();
	}
	
	@Test
	public void testMyPageOpened() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		MyPageDto.OpenedRes o1 = mock(MyPageDto.OpenedRes.class);
		given(o1.getId()).willReturn(1L);
		MyPageDto.OpenedRes o2 = mock(MyPageDto.OpenedRes.class);
		given(o2.getId()).willReturn(1L);

		Page<MyPageDto.OpenedRes> list = new PageImpl<>(Arrays.asList(o1, o2), pageable, 2);
		given(meetRepository.findMyPageOpened(any(), any(), any())).willReturn(list);
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet1));
		
		User user = mock(User.class);
		given(userRepository.findByUsername(any())).willReturn(user);
		given(user.getId()).willReturn(1L);

		MyPageDto.OpenedReq dto = MyPageDto.OpenedReq.builder()
				.title("name")
				.toApproval(false)
				.build();
		
		// when
		Page<MyPageDto.OpenedRes> res = myPageServiceImpl.opened("cdssw@naver.com", dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(2);
	}
	
	@Test
	public void testMyPageApplication() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		MyPageDto.ApplicationRes a1 = mock(MyPageDto.ApplicationRes.class);
		given(a1.getId()).willReturn(1L);
		MyPageDto.ApplicationRes a2 = mock(MyPageDto.ApplicationRes.class);
		given(a2.getId()).willReturn(1L);
		
		Page<MyPageDto.ApplicationRes> list = new PageImpl<>(Arrays.asList(a1, a2), pageable, 2);
		given(applicationMeetRepository.findMyPageApplication(any(), any(), any())).willReturn(list);
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet1));
		
		User user = mock(User.class);
		given(userRepository.findByUsername(any())).willReturn(user);
		given(user.getId()).willReturn(1L);
		

		MyPageDto.ApplicationReq dto = MyPageDto.ApplicationReq.builder()
				.title("name")
				.build();
		
		// when
		Page<MyPageDto.ApplicationRes> res = myPageServiceImpl.application("cdssw@naver.com", dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(2);
	}	
}
