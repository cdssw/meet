package com.moim.meet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Chat.java
 * 
 * @author cdssw
 * @since 2020. 11. 5.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 11. 5.    cdssw            최초 생성
 * </pre>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 코드 내에서 객체를 생성하지 못하도록
@Getter
@Entity
public class Chat extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long meetId;
	
	private String sender;
	
	@Builder
	public Chat(Long meetId, String sender) {
		this.meetId = meetId;
		this.sender = sender;
	}
}
