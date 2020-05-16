package com.moim.meet.service.user;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.Email;
import com.moim.meet.entity.User;
import com.moim.meet.except.NotFoundException;
import com.moim.meet.repository.UserRepository;

/**
 * UserServiceImplTest.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
	
	// 테스트 하고자 하는 class
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepository;
	
	private UserDto.SignUpReq dto = null;

	@Before
	public void setUp() {
		CommonComponent commonComponent = new CommonComponent();
		ModelMapper modelMapper = new ModelMapper();
		userServiceImpl = new UserServiceImpl(commonComponent, modelMapper, userRepository);
		dto = UserDto.SignUpReq.builder().email(Email.builder().address("cdssw@naver.com").build()).userNm("Andrew").build();
	}

	@Test
	public void testAddUser() {
		// given
		User user = mock(User.class);
		given(userRepository.save(any(User.class))).willReturn(user);
		given(user.getId()).willReturn(1L);
		
		// when
		Long userId = userServiceImpl.addUser(dto);
		
		// then
		assertEquals(userId, 1L);
	}
	
	@Test
	public void testGetUser() {
		// given
		given(userRepository.findById(any())).willReturn(Optional.of(dto.toEntity()));
		
		// when
		UserDto.Res res = userServiceImpl.getUser(1L);
		
		// then
		assertThat(res.getEmail().getEmail()).isEqualTo(dto.getEmail().getEmail());
	}
	
	@Test(expected = NotFoundException.class)
	public void testUserNotFound() {
		// given
		given(userRepository.findById(any())).willReturn(Optional.empty());
		
		// when
		userServiceImpl.getUser(1);		
	}
}
