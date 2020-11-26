package com.moim.meet.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.moim.meet.service.application.ApplicationDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ApplicationMeet.java
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
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 코드 내에서 객체를 생성하지 못하도록
@Getter
@Entity
public class ApplicationMeet extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="meet_id")
	private Meet meet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Embedded
	private Approval approval;
	
	@ColumnDefault("0")
	private Integer estimate;
	
	@Builder
	public ApplicationMeet(Meet meet, User user, Approval approval, Integer estimate) {
		this.meet = meet;
		this.user = user;
		this.approval = approval;
		this.estimate = estimate;
	}
	
	public void estimate(ApplicationDto.EstimateReq dto) {
		this.estimate = dto.getEstimate();
	}
}
