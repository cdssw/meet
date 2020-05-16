package com.moim.meet.service.application;

/**
 * ApplicationService.java
 * 
 * @author cdssw
 * @since May 2, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 2, 2020   cdssw            최초 생성
 * </pre>
 */
public interface ApplicationService {

	void applicationMeet(ApplicationDto.ApplicationReq dto);
	void approval(ApplicationDto.ApprovalReq dto);
	void cancel(ApplicationDto.ApprovalReq dto);
}
