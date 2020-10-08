package com.moim.meet.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.moim.meet.entity.Meet;
import com.moim.meet.service.meet.MeetDto;
import com.moim.meet.service.mypage.MyPageDto;

/**
 * MeetCustomRepository.java
 * 
 * @author cdssw
 * @since Apr 25, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 25, 2020   cdssw            최초 생성
 * </pre>
 */
public interface MeetCustomRepository {

	Page<Meet> findSearch(MeetDto.SearchReq dto, Pageable pageable);
	Page<MyPageDto.OpenedRes> findMyPageOpened(Long userId, MyPageDto.OpenedReq dto, Pageable pageable);
}
