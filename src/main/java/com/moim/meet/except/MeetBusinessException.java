package com.moim.meet.except;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MeetBusinessException.java
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
@AllArgsConstructor
@Getter
public class MeetBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2891950653809573137L;
	private ErrorCode errorCode; // rest 결과처리를 위한 ErrorCode Enum
}
