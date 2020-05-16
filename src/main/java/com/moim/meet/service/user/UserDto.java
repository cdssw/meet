package com.moim.meet.service.user;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.moim.meet.entity.Email;
import com.moim.meet.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserDto.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description User Rest DTO
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
public class UserDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SignUpReq {
		
		@Valid
		private Email email;
		
		@NotEmpty
		private String userNm;
		
		@Builder
		public SignUpReq(Email email, String userNm) {
			this.email = email;
			this.userNm = userNm;
		}
		
		public User toEntity() {
			return User.builder()
					.email(email)
					.userNm(userNm)
					.build();
		}
	}
	
	@Getter
	@Setter
	public static class Res {
		private Email email;
		private String userNm;
	}
}
