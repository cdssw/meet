package com.moim.kafka;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * EventChat.java
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class EventChat {
	
	private Long meetId;
	private String sender;
}
