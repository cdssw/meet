package com.moim.meet.service.applicationmeet;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
	}
	
	// 지원 req
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED) // 빈 생성자를 생성하되 상속받지 않은곳에서 new 하지 못하도록 제한, BaseReq를 확장하였기 때문에 부모의 빈 생성자를 호출하게 됨
	public static class ApplicationReq extends BaseReq {
		
		@Builder
		public ApplicationReq(Long meetId) {
			super(meetId); // 상위 클래스의 생성자 값을 설정
		}
	}
	
	// 승인관련 req
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ApprovalReq extends BaseReq {
		
		@NotNull
		private Long userId;
		
		@Builder
		public ApprovalReq(Long meetId, Long userId) {
			super(meetId); // 상위 클래스의 생성자 값을 설정
			this.userId = userId;
		}
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static abstract class BaseRes {
	}
	
	// 결과 DTO
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder // 부모의 생성자에 대하여 builder를 사용할수 있게 해준다.
	public static class ApplicationUserRes extends BaseRes {
		private Long id;
		private String userNickNm;
		private String avatarPath;
		private boolean approvalYn;
		// Time format 처리
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime approvalDt;
	}
}
