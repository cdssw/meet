package com.moim.meet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User.java
 * 
 * @author cdssw
 * @since Apr 12, 2020
 * @description 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 12, 2020   cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseTimeEntity {

	@Id
	private Long id;

	@JsonIgnore
	private String username;
	private String userNm;
	private String phone;

	// phone 번호 수정
	public void editPhone(String phone) {
		this.phone = phone;
	}
}
