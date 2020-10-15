package com.moim.meet.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.moim.meet.service.application.ApplicationDto;
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
public interface ApplicationMeetCustomRepository {

	long countByMeetAndUserGroupByMeet(long meetId, long userId);
	Page<MyPageDto.ApplicationRes> findMyPageApplication(Long userId, MyPageDto.ApplicationReq dto, Pageable pageable);
	List<ApplicationDto.ApplicationUserRes> findUserByApplicationMeet(Long meetId);
}
