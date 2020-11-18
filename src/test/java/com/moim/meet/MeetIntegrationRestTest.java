package com.moim.meet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.meet.entity.Address;
import com.moim.meet.entity.Term;
import com.moim.meet.except.RestExceptionHandler.ExceptRes;
import com.moim.meet.service.meet.MeetDto;
import com.moim.meet.service.meet.MeetDto.MeetReq;
import com.moim.meet.service.mypage.MyPageDto;

/**
 * MeetIntegrationRestTest.java
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
public class MeetIntegrationRestTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testNotFoundException() throws Exception {
		String result = testRestTemplate.getForObject("/100", String.class);
		ExceptRes res = objectMapper.readValue(result, ExceptRes.class);
		
		assertEquals(res.getCode(), "E_00001");
	}
	
	@Test
	public void testGetMeet() throws Exception {
		// when
		String result = testRestTemplate.getForObject("/1", String.class);
		MeetDto.Res res = objectMapper.readValue(result, MeetDto.Res.class);
		
		// then
		assertEquals(res.getId(), 1);
	}
	
	@Test
	public void testMyPageOpened() {
		// given
		MyPageDto.OpenedReq dto = MyPageDto.OpenedReq.builder()
				.title("신문")
				.toApproval(false)
				.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("username", "cdssw@naver.com");
		HttpEntity<MyPageDto.OpenedReq> entity = new HttpEntity<MyPageDto.OpenedReq>(dto, headers);
		
		// when
		ParameterizedTypeReference<RestPageImpl<MyPageDto.OpenedReq>> responseType = 
				new ParameterizedTypeReference<RestPageImpl<MyPageDto.OpenedReq>>() {};
		
		ResponseEntity<RestPageImpl<MyPageDto.OpenedReq>> result = testRestTemplate.exchange(
				"/mypage/opened?page=0&size=10"
				, HttpMethod.POST
				, entity
				, responseType);
		
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getTotalElements(), 0);
	}
	
	@Test
	public void testMyPageApplication() {
		// given
		MyPageDto.ApplicationReq dto = MyPageDto.ApplicationReq.builder().build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("username", "cdssw@naver.com");
		HttpEntity<MyPageDto.ApplicationReq> entity = new HttpEntity<MyPageDto.ApplicationReq>(dto, headers);
		
		// when
		ParameterizedTypeReference<RestPageImpl<MyPageDto.ApplicationReq>> responseType = 
				new ParameterizedTypeReference<RestPageImpl<MyPageDto.ApplicationReq>>() {};
		
		ResponseEntity<RestPageImpl<MyPageDto.ApplicationReq>> result = testRestTemplate.exchange(
				"/mypage/application?page=0&size=10"
				, HttpMethod.POST
				, entity
				, responseType);
		
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getTotalElements(), 4);
	}
	
	@Test
	public void testCreateMeet() throws Exception {
		// given
		MeetDto.MeetReq dto = MeetReq.builder()
				.title("title")
				.content("content")
				.recruitment(1)
				.cost(1000)
				.costOption(true)
				.address(Address.builder().address1("서울시").address2("강남구").build())
				.term(Term.builder()
						.startDt("2020-09-01")
						.endDt("2020-09-30")
						.startTm("10:00")
						.endTm("16:00")
						.detailDay(64).build())
				.imgList(Arrays.asList(1L, 2L))
				.build();
		HttpEntity<MeetDto.MeetReq> entity = new HttpEntity<MeetDto.MeetReq>(dto);
		
		// when
		ResponseEntity<Long> result = testRestTemplate.exchange(
				"/"
				, HttpMethod.POST
				, entity
				, Long.class);
		
		assertEquals(result.getStatusCode(), HttpStatus.CREATED);
	}
	
}
