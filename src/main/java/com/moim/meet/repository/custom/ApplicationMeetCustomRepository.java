package com.moim.meet.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	Page<MyPageDto.ApplicationRes> findMyPageApplication(MyPageDto.ApplicationReq dto, Pageable pageable);
}
