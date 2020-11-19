package com.moim.meet.service.application;

import java.util.List;

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

	List<ApplicationDto.ApplicationUserRes> applicationUser(final long meetId);
	void applicationMeet(ApplicationDto.ApplicationReq dto, final String username);
	void approval(ApplicationDto.ApprovalReq dto, final String username);
	void cancel(ApplicationDto.ApprovalReq dto, final String username);
	ApplicationDto.ApplicationInfoRes getApplicatorInfo(final Long meetId, final String applicator, final String username);
	void estimate(ApplicationDto.EstimateReq dto, final String username);
	Boolean isJoin(final Long meetId, String username);
}
