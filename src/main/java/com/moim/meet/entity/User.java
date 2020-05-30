package com.moim.meet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
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
@Entity
public class User extends BaseTimeEntity {

	@Id
	private Long id;

	private String userNm;
	
	@Builder
	public User(Long id, String userNm) {
		this.id = id;
		this.userNm = userNm;
	}
}
