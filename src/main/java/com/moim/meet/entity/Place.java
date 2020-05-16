package com.moim.meet.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Place.java
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
public class Place {

	@NotBlank
	private String address;
	
	@NotBlank
	private String addressDetail;
	
	@Builder
	public Place(String address, String addressDetail) {
		this.address = address;
		this.addressDetail = addressDetail;
	}
}
