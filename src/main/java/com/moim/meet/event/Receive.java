package com.moim.meet.event;

import javax.transaction.Transactional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.moim.kafka.EventChat;
import com.moim.kafka.EventUser;
import com.moim.meet.component.CommonComponent;
import com.moim.meet.entity.Chat;
import com.moim.meet.entity.User;
import com.moim.meet.except.ErrorCode;
import com.moim.meet.repository.ChatRepository;
import com.moim.meet.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Receive.java
 * 
 * @author cdssw
 * @since 2020. 6. 4.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 6. 4.    cdssw            최초 생성
 * </pre>
 */
//@Profile("!test")
@AllArgsConstructor
@Component
@Slf4j
public class Receive {

	private UserRepository userRepository;
	private ChatRepository chatRepository;
	private CommonComponent commonComponent;
	
	// user-modified가 발생하면 수신
	@Transactional
	@KafkaListener(topics = "${spring.kafka.topic.user-modified}", groupId = "${spring.kafka.consumer.group-id}")
	public void userModified(EventUser payload) {
		log.info(payload.getPhone());
		final User user = commonComponent.findById(userRepository, payload.getId(), User.class, ErrorCode.USER_NOT_FOUND);
		user.editUser(payload);
	}
	
	// user-created가 발생하면 수신
	@Transactional
	@KafkaListener(topics = "${spring.kafka.topic.user-created}", groupId = "${spring.kafka.consumer.group-id}")
	public void userCreated(EventUser payload) {
		User user = userRepository.findByUsername(payload.getUsername());
		if(user == null) { // 테이블에 존재하지 않으면 추가 
			user = User.builder()
					.id(payload.getId())
					.username(payload.getUsername())
					.userNm(payload.getUserNm())
					.userNickNm(payload.getUserNickNm())
					.phone(payload.getPhone())
					.avatarPath(payload.getAvatarPath())
					.build();
			userRepository.save(user);
		}
	}
	
	@Transactional
	@KafkaListener(topics = "${spring.kafka.topic.chat-created}", groupId = "${spring.kafka.consumer.group-id}")
	public void chatCreated(EventChat payload) {
		Chat chat = chatRepository.findByMeetIdAndSender(payload.getMeetId(), payload.getSender());
		if(chat == null) {
			chat = Chat.builder()
					.meetId(payload.getMeetId())
					.sender(payload.getSender())
					.build();
			chatRepository.save(chat);
		}
	}
}
