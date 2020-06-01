package com.moim.meet.service.mypage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.moim.meet.entity.Place;
import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.MeetRepository;

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
	@Mock private ApplicationMeetRepository applicationMeetRepository;
	
	private MyPageDto.OpenedRes open1;
	private MyPageDto.OpenedRes open2;
	
	private MyPageDto.ApplicationRes app1;
	private MyPageDto.ApplicationRes app2;
	
	@Before
	public void setUp() {
		myPageServiceImpl = new MyPageServiceImpl(meetRepository, applicationMeetRepository);
		
		open1 = MyPageDto.OpenedRes.builder()
				.meetNm("Meet name 2")
				.meetDesc("Second save meet")
				.cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build())
				.recruitment(3)
				.application(1)
				.build();
		open2 = MyPageDto.OpenedRes.builder()
				.meetNm("Meet name 2")
				.meetDesc("Second save meet")
				.cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build())
				.recruitment(3)
				.application(1)
				.build();
		
		app1 = MyPageDto.ApplicationRes.builder()
				.meetNm("Meet name 2")
				.meetDesc("Second save meet")
				.cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build())
				.recruitment(3)
				.application(1)
				.build();
		app2 = MyPageDto.ApplicationRes.builder()
				.meetNm("Meet name 2")
				.meetDesc("Second save meet")
				.cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build())
				.recruitment(3)
				.application(1)
				.build();
	}
	
	@Test
	public void testMyPageOpened() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		Page<MyPageDto.OpenedRes> list = new PageImpl<>(Arrays.asList(open1, open2), pageable, 2);
		given(meetRepository.findMyPageOpened(any(), any())).willReturn(list);
		MyPageDto.OpenedReq dto = MyPageDto.OpenedReq.builder()
				.meetNm("name")
				.leaderId(1L)
				.toAppBoolean(false)
				.build();
		
		// when
		Page<MyPageDto.OpenedRes> res = myPageServiceImpl.opened(dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(2);
	}
	
	@Test
	public void testMyPageApplication() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		Page<MyPageDto.ApplicationRes> list = new PageImpl<>(Arrays.asList(app1, app2), pageable, 2);
		given(applicationMeetRepository.findMyPageApplication(any(), any())).willReturn(list);
		MyPageDto.ApplicationReq dto = MyPageDto.ApplicationReq.builder()
				.meetNm("name")
				.userId(1L)
				.toAppBoolean(false)
				.build();
		
		// when
		Page<MyPageDto.ApplicationRes> res = myPageServiceImpl.application(dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(2);
	}	
}
