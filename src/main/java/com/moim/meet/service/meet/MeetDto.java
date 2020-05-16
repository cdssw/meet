package com.moim.meet.service.meet;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.Place;
import com.moim.meet.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * MeetDto.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description  Meet Rest용 DTO
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
public class MeetDto {

	// 새로운 Meet 등록 요청 DTO
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class MeetReq {
		
		@NotBlank
		private String meetNm;
		private String meetDesc;
		
		@Min(value = 1)
		private int recruitment;
		private int application;
		private int cost;

		@Valid
		private Place place;
		
		
		@NotNull
		private Long userId;
		
		@Builder
		public MeetReq(String meetNm, String meetDesc, int recruitment, int application, int cost, Place place, Long userId) {
			this.meetNm = meetNm;
			this.meetDesc = meetDesc;
			this.recruitment = recruitment;
			this.application = application;
			this.cost = cost;
			this.place = place;
			this.userId = userId;
		}
		
		public Meet toEntity() {
			return Meet.builder()
					.meetNm(meetNm)
					.meetDesc(meetDesc)
					.recruitment(recruitment)
					.application(application)
					.cost(cost)
					.place(place)
					.build();
		}
	}
	
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SearchReq {
		
		private String meetNm;
		private String meetDesc;
		private Long leaderId;
		
		@Builder
		public SearchReq(String meetNm, String meetDesc, Long leaderId) {
			this.meetNm = meetNm;
			this.meetDesc = meetDesc;
			this.leaderId = leaderId;
		}
	}

	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder
	public static abstract class BaseRes {
		private Long id;
		private String meetNm;
		private String meetDesc;
		private int recruitment;
		private int application;
		private int cost;
		private Place place;
		
		// Time format 처리
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime inputDt;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime modifyDt;
		private User user;		
	}
	
	// 결과 DTO
	@Getter
	@Setter
	@NoArgsConstructor
	@SuperBuilder // 부모의 생성자에 대하여 builder를 사용할수 있게 해준다.
	public static class Res extends BaseRes {

	}

}
