package com.moim.meet.service.mypage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.repository.ApplicationMeetRepository;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.service.mypage.MyPageDto.ApplicationReq;
import com.moim.meet.service.mypage.MyPageDto.ApplicationRes;
import com.moim.meet.service.mypage.MyPageDto.OpenedReq;
import com.moim.meet.service.mypage.MyPageDto.OpenedRes;

import lombok.AllArgsConstructor;

/**
 * MyPageServiceImpl.java
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
@AllArgsConstructor // private 변수에 autowired 추가 안해도 됨
@Service
public class MyPageServiceImpl implements MyPageService {

	private MeetRepository meetRepository;
	private ApplicationMeetRepository applicationMeetRepository;
	
	@Transactional(readOnly = true)
	@Override
	public Page<OpenedRes> opened(OpenedReq dto, Pageable pageable) {
		return meetRepository.findMyPageOpened(dto, pageable);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<ApplicationRes> application(ApplicationReq dto, Pageable pageable) {
		return applicationMeetRepository.findMyPageApplication(dto, pageable);
	}
}
