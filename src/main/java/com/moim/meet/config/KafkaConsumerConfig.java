package com.moim.meet.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.moim.kafka.EventUser;

/**
 * KafkaConsumerConfig.java
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
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	
	@Bean
	public ConsumerFactory<String, EventUser> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		
		// 패키지 허용을 위해 deserializer 처리
		JsonDeserializer<EventUser> deserializer = new JsonDeserializer<EventUser>();
		deserializer.addTrustedPackages("*"); // com.moim.kafka 패키지 허용
		
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EventUser> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EventUser> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
