package com.moim.meet.service.application;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ApplicationDto.java
 * 
 * @author cdssw
 * @since May 3, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 3, 2020    cdssw            최초 생성
 * </pre>
 */
public class ApplicationDto {
	
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈 생성자를 생성하되 상속받지 않은곳에서 new 하지 못하도록 제한
	public static abstract class BaseReq {
		
		@NotNull
		private Long meetId;
		
		@NotNull
		private Long userId;
	}
	
	// 지원 req
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈 생성자를 생성하되 상속받지 않은곳에서 new 하지 못하도록 제한, BaseReq를 확장하였기 때문에 부모의 빈 생성자를 호출하게 됨
	public static class ApplicationReq extends BaseReq {
		
		@Builder
		public ApplicationReq(Long meetId, Long userId) {
			super(meetId, userId); // 상위 클래스의 생성자 값을 설정
		}
	}
	
	// 승인관련 req
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ApprovalReq extends BaseReq {
		
		@NotNull
		private Long leaderId;
		
		@Builder
		public ApprovalReq(Long meetId, Long userId, Long leaderId) {
			super(meetId, userId); // 상위 클래스의 생성자 값을 설정
			this.leaderId = leaderId;
		}
	}
}
