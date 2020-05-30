package com.moim.meet.service.meet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.Place;
import com.moim.meet.entity.User;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.repository.UserRepository;

/**
 * MeetServiceImplTest.java
 * 
 * @author cdssw
 * @since Apr 8, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 8, 2020    cdssw            최초 생성
 * </pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class MeetServiceImplTest {

	// 테스트 하고자 하는 class
	private MeetServiceImpl meetServiceImpl;

	// repository를 테스트 하지 않음
	@Mock private MeetRepository meetRepository;
	@Mock private UserRepository userRepository;
	
	private User user;
	private Meet meet1;
	private Meet meet2;
	private MeetDto.MeetReq dto1;
	private MeetDto.MeetReq dto2;
	
	@Before
	public void setUp() {
		ModelMapper modelMapper = new ModelMapper();
		CommonComponent commonComponent = new CommonComponent();
		meetServiceImpl = new MeetServiceImpl(modelMapper, commonComponent, meetRepository, userRepository);
		
		user = User.builder().id(1L).userNm("Andrew").build();
		dto1 = MeetDto.MeetReq.builder().meetNm("First Meet").meetDesc("First save meet").cost(100)
				.place(Place.builder().address("address").addressDetail("detail").build()).recruitment(3).application(1).userId(1L).build();
		dto2 = MeetDto.MeetReq.builder().meetNm("Second Meet").meetDesc("Second save meet").cost(200)
				.place(Place.builder().address("address2").addressDetail("detail2").build()).recruitment(10).application(3).userId(1L).build();
		meet1 = Meet.builder().meetNm("First meet").meetDesc("First save meet").cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build()).recruitment(3).application(1).build();
		meet2 = Meet.builder().meetNm("Meet name 2").meetDesc("Second save meet").cost(10)
				.place(Place.builder().address("address").addressDetail("detail").build()).recruitment(3).application(1).build();
	}
	
	// 테스트 하는것은 dto를 가지고 service 함수가 문제 없이 동작하는지 확인
	@Test
	public void testCreateMeet() {
		// given
		Meet meet = mock(Meet.class);
		given(userRepository.findById(1L)).willReturn(Optional.of(user));		
		given(meetRepository.save(any(Meet.class))).willReturn(meet);
		given(meet.getId()).willReturn(1L);
		
		// when
		Long id = meetServiceImpl.createMeet(dto1);
		
		// then
		assertEquals(id, 1L);	
	}
	
	// 테스트 하는것은 dto를 가지고 service 함수가 문제 없이 동작하는지 확인
	@Test
	public void testGetMeetList() {
		// given
		given(meetRepository.findAll()).willReturn(Arrays.asList(dto1.toEntity(), dto2.toEntity()));
		
		// when 
		List<MeetDto.Res> list = meetServiceImpl.getMeetList();
		
		// then
		assertEquals(list.get(0).getMeetNm(), dto1.getMeetNm());
		assertEquals(list.get(1).getMeetNm(), dto2.getMeetNm());
	}
	
	@Test
	public void testGetMeet() {
		// given
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(dto1.toEntity()));
		
		// when
		MeetDto.Res res = meetServiceImpl.getMeet(1);
		
		// then
		assertEquals(res.getMeetNm(), dto1.getMeetNm());		
	}
	
	@Test
	public void testEditMeet() {
		// given
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(dto1.toEntity()));
		given(userRepository.findById(anyLong())).willReturn(Optional.of(user));
		
		// when
		MeetDto.Res res = meetServiceImpl.editMeet(1, dto1);
		
		// then
		assertEquals(res.getPlace().getAddress(), dto1.getPlace().getAddress());
	}
	
	@Test
	public void testDeleteMeet() {
		// given
		given(meetRepository.findById(anyLong())).willReturn(Optional.of(meet1));
		
		// when
		meetServiceImpl.deleteMeet(1);
		
		// then
		verify(meetRepository).deleteById(anyLong());
	}
	
	@Test
	public void testGetMeetListByPage() {
		// given
		List<Meet> list = Arrays.asList(dto1.toEntity(), dto2.toEntity());
		Pageable pageable = PageRequest.of(0, 10);
		Page<Meet> pageList = new PageImpl<>(list, pageable, list.size());
		given(meetRepository.findAll(pageable)).willReturn(pageList);
		
		// when
		Page<MeetDto.Res> res = meetServiceImpl.getMeetListByPage(pageable);
		
		// then
		assertEquals(res.getTotalElements(), 2);
	}
	
	@Test
	public void testSearch() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		Page<Meet> meetList = new PageImpl<>(Arrays.asList(meet1, meet2), pageable, 2);
		given(meetRepository.findSearch(any(MeetDto.SearchReq.class), any())).willReturn(meetList);
		MeetDto.SearchReq dto = MeetDto.SearchReq.builder().meetNm("name").leaderId(1L).build();
		
		// when
		Page<MeetDto.Res> res = meetServiceImpl.search(dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(2);
	}	
}
