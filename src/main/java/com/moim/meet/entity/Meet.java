package com.moim.meet.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.moim.meet.service.meet.MeetDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Meet.java
 * 
 * @author cdssw
 * @since Apr 6, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 6, 2020    cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 코드 내에서 객체를 생성하지 못하도록
@Getter
@Entity
public class Meet extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String meetNm;
	private String meetDesc;
	private int recruitment;
	private int application;
	private int cost;
	
	@Embedded
	private Place place;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id") // 생성될 join column
	private User user;

	@Builder
	public Meet(String meetNm, String meetDesc, int recruitment, int application, int cost, Place place, User user) {
		this.meetNm = meetNm;
		this.meetDesc = meetDesc;
		this.recruitment = recruitment;
		this.application = application;
		this.cost = cost;
		this.place = place;
		this.user = user;
	}
	
	// @Setter 처리하지 않고 목적에 맞는 method를 생성하여 처리
	// 사용자 처리
	public void editUser(User user) {
		this.user = user;
	}

	// Meet 내용 수정
	public void editMeet(MeetDto.MeetReq dto) {
		this.meetNm = dto.getMeetNm();
		this.meetDesc = dto.getMeetDesc();
		this.recruitment = dto.getRecruitment();
		this.cost = dto.getCost();
		this.place = dto.getPlace();
	}
	
	// 지원
	public void applicationMeet() {
		this.application = application + 1;
	}
}
