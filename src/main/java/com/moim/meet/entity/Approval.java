package com.moim.meet.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Approval.java
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
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Approval {

	@Column(nullable = false)
	private boolean approvalYn;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime approvalDt;
	
	@Builder
	public Approval(boolean approvalYn, LocalDateTime approvalDt) {
		this.approvalYn = approvalYn;
		this.approvalDt = approvalDt;
	}
	
	// 승인처리
	public void approval() {
		this.approvalYn = true;
		this.approvalDt = LocalDateTime.now();
	}
	
	// 승인취소처리
	public void cancel() {
		this.approvalYn = false;
		this.approvalDt = LocalDateTime.now();
	}
}
