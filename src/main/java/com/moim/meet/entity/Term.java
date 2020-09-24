package com.moim.meet.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Term.java
 * 
 * @author cdssw
 * @since 2020. 8. 20.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 8. 20.    cdssw            최초 생성
 * </pre>
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Term {

	@NotBlank
	private String startDt;
	
	@NotBlank
	private String endDt;
	
	@NotBlank
	private String startTm;
	
	@NotBlank
	private String endTm;
	
	/*
	 * 0x10000000(128) = 협의
	 * 0x01000000(64) = 일
	 * 0x00100000(32) = 월
	 * 0x00010000(16) = 화
	 * 0x00001000(8) = 수
	 * 0x00000100(4) = 목
	 * 0x00000010(2) = 금
	 * 0x00000001(1) = 토
	 */
	private int detailDay;
	
	@Builder
	public Term(String startDt, String endDt, String startTm, String endTm, int detailDay) {
		this.startDt = startDt;
		this.endDt = endDt;
		this.startTm = startTm;
		this.endTm = endTm;
		this.detailDay = detailDay;
	}
}
