package com.moim.meet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.meet.entity.Chat;

/**
 * ChatRepository.java
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
public interface ChatRepository extends JpaRepository<Chat, Long> {

	Chat findByMeetIdAndSender(Long meetId, String sender);
	long countByMeetIdAndSenderNot(Long meetId, String sender);
	List<Chat> findBySender(String sender);	
}
