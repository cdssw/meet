package com.moim.meet.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Address.java
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
public class Address {

	@NotBlank
	private String address1;
	
	@NotBlank
	private String address2;
	
	private String sido;
	
	private String sgg;
	
	@Builder
	public Address(String address1, String address2, String sido, String sgg) {
		this.address1 = address1;
		this.address2 = address2;
		this.sido = sgg;
		this.sgg = sgg;
	}
}
