package com.moim.meet.entity;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

/**
 * Email.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description Embed용 Entity
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

	@javax.validation.constraints.Email
	private String email;
	
	@Builder
	public Email(String address) {
		this.email = address;
	}
	
	public String getId() {
		int index = this.email.indexOf("@");
		return this.email.substring(0, index);
	}
	
	public String getHost() {
		int index = this.email.indexOf("@");
		return this.email.substring(index);
	}
}
