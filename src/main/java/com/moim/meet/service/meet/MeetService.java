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

	Long createMeet(MeetDto.MeetReq dto);
	List<MeetDto.Res> getMeetList();
	MeetDto.Res editMeet(long id, MeetDto.MeetReq dto);
	MeetDto.Res getMeet(long id);
	void deleteMeet(long id);
	Page<MeetDto.Res> getMeetListByPage(Pageable pageable);
	Page<MeetDto.Res> search(MeetDto.SearchReq dto, Pageable pageable);
}
