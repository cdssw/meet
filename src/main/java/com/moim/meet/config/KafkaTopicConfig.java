package com.moim.meet.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * KafkaTopicConfig.java
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
@Profile("!test")
@Configuration
public class KafkaTopicConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> config = new HashMap<>();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		return new KafkaAdmin(config);
	}
	
	// user-modified 토픽 생성
	@Bean
	@Value("${spring.kafka.topic.user-modified}")
	public NewTopic topicUserModified(String topic) {
		return new NewTopic(topic, 1, (short) 1);
	}
	
	// user-created 토픽 생성
	@Bean
	@Value("${spring.kafka.topic.user-created}")
	public NewTopic topicUserCreated(String topic) {
		return new NewTopic(topic, 1, (short) 1);
	}
}
