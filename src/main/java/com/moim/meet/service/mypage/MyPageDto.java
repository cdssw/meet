package com.moim.meet.service.mypage;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moim.meet.entity.Approval;
import com.moim.meet.entity.Term;
import com.moim.meet.entity.Address;
import com.moim.meet.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * MyPageDto.java
 * 
 * @author cdssw
 * @since May 9, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 9, 2020   cdssw            최초 생성
 * </pre>
 */
public class MyPageDto {

	
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class OpenedReq {
		
		private String title;
		private String content;
		
		@NotNull
		private Boolean toApproval;
		
		@Builder
		public OpenedReq(String title, String content, Boolean toApproval) {
			this.title = title;
			this.content = content;
			this.toApproval = toApproval;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ApplicationReq {
		
		private String title;
		private String content;
		
		@Builder
		public ApplicationReq(String title, String content) {
			this.title = title;
			this.content = content;
		}
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static abstract class BaseRes {
		private Long id;
		private String title;
		private String content;
		private int recruitment;
		private int application;
		private int cost;
		private Boolean costOption;
		private Address address;
		private Term term;
		private List<Long> imgList;

		// Time format 처리
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime inputDt;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDt;
		private User user;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static class OpenedRes extends BaseRes {
		private long toApprovalCnt;
		private long chatCnt;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static class ApplicationRes extends BaseRes {
		private Approval approval;
		private long chatCnt;
	}	
}
