package com.moim.meet.service.user;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.User;
import com.moim.meet.repository.UserRepository;
import com.moim.meet.service.user.UserDto.Res;
import com.moim.meet.service.user.UserDto.SignUpReq;

import lombok.AllArgsConstructor;

/**
 * UserServiceImpl.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description 서비스 구현체 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private CommonComponent commonComponent;
	private ModelMapper modelMapper;
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public Long addUser(SignUpReq dto) {
		User user = userRepository.save(dto.toEntity());
		return user.getId();
	}

	@Override
	public Res getUser(long userId) {
		User user = commonComponent.findById(userRepository, userId, User.class);
		Res res = modelMapper.map(user, Res.class);
		return res;
	}

}
