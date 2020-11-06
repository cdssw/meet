package com.moim.meet.service.mypage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * MyPageService.java
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
public interface MyPageService {

	Page<MyPageDto.OpenedRes> opened(final String username, MyPageDto.OpenedReq dto, Pageable pageable);
	Page<MyPageDto.ApplicationRes> application(final String username, MyPageDto.ApplicationReq dto, Pageable pageable);
	Page<MyPageDto.ApplicationRes> chatAndApplication(final String username, MyPageDto.ApplicationReq dto, Pageable pageable);
}
