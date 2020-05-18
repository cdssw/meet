package com.moim.meet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.meet.except.RestExceptionHandler.ExceptRes;
import com.moim.meet.service.mypage.MyPageDto;

/**
 * MeetIntegrationTest.java
 * 
 * @author cdssw
 * @since May 7, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 7, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MeetIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testNotFoundException() throws Exception {
		String result = testRestTemplate.getForObject("/5", String.class);
		ExceptRes res = objectMapper.readValue(result, ExceptRes.class);
		
		assertEquals(res.getCode(), "E_00001");
	}
	
	@Test
	public void testMyPageOpened() {
		// given
		MyPageDto.OpenedReq dto = MyPageDto.OpenedReq.builder()
				.meetNm("신문")
				.leaderId(1L).
				toAppBoolean(false)
				.build();
		HttpEntity<MyPageDto.OpenedReq> entity = new HttpEntity<MyPageDto.OpenedReq>(dto);
		
		// when
		ParameterizedTypeReference<RestPageImpl<MyPageDto.OpenedReq>> responseType = 
				new ParameterizedTypeReference<RestPageImpl<MyPageDto.OpenedReq>>() {};
		
		ResponseEntity<RestPageImpl<MyPageDto.OpenedReq>> result = testRestTemplate.exchange(
				"/mypage/opened?page=0&size=10"
				, HttpMethod.POST
				, entity
				, responseType);
		
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getTotalElements(), 1);
	}
	
	@Test
	public void testMyPageApplication() {
		// given
		MyPageDto.ApplicationReq dto = MyPageDto.ApplicationReq.builder()
				.userId(1L).
				toAppBoolean(false)
				.build();
		HttpEntity<MyPageDto.ApplicationReq> entity = new HttpEntity<MyPageDto.ApplicationReq>(dto);
		
		// when
		ParameterizedTypeReference<RestPageImpl<MyPageDto.ApplicationReq>> responseType = 
				new ParameterizedTypeReference<RestPageImpl<MyPageDto.ApplicationReq>>() {};
		
		ResponseEntity<RestPageImpl<MyPageDto.ApplicationReq>> result = testRestTemplate.exchange(
				"/mypage/application?page=0&size=10"
				, HttpMethod.POST
				, entity
				, responseType);
		
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getTotalElements(), 3);
	}	
}
