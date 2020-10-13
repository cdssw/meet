package com.moim.meet.service.meet;
/**
 * MeetService.java
 * 
 * @author cdssw
 * @since Apr 6, 2020
 * @description Controller에 제공되는 서비스
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 6, 2020    cdssw            최초 생성
 * </pre>
 */

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MeetService {

	Long createMeet(MeetDto.MeetReq dto, final String username);
	MeetDto.Res editMeet(final long id, final String username, MeetDto.MeetReq dto);
	void deleteMeet(final long id, final String username);
	
	List<MeetDto.Res> getMeetList();
	MeetDto.Res getMeet(final long id, final String username);
	Page<MeetDto.Res> getMeetListByPage(Pageable pageable);
	Page<MeetDto.Res> search(MeetDto.SearchReq dto, Pageable pageable);
}
