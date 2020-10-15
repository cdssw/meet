package com.moim.meet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.meet.service.application.ApplicationDto.ApplicationReq;
import com.moim.meet.service.application.ApplicationDto.ApprovalReq;

import lombok.extern.slf4j.Slf4j;

/**
 * ApplicationControllerTest.java
 * 
 * @author cdssw
 * @since May 2, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * May 2, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@WebMvcTest // controller 관련 bean만 로딩
@Slf4j
public class ApplicationControllerTest extends BaseControllerTest {
	
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	private ObjectMapper objectMapper;

	private ApplicationReq applicationReq;
	private ApprovalReq approvalReq;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 항상 결과 print
				.build();
		
		applicationReq = ApplicationReq.builder()
				.meetId(1L).build();
		
		approvalReq = ApprovalReq.builder()
				.meetId(1L)
				.userId(1L)
				.build();
	}
	
	@Test
	public void testApplicationMeet() throws Exception {
		// when
		final MvcResult result = mvc.perform(post("/application")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(applicationReq)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testApplicationApproval() throws Exception {
		// when
		final MvcResult result = mvc.perform(post("/application/approval")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(approvalReq)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testApplicationCancel() throws Exception {
		// when
		final MvcResult result = mvc.perform(post("/application/cancel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(approvalReq)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	
	@Test
	public void testApplicationInvalid() throws Exception {
		ApprovalReq dto = ApprovalReq.builder().build();
		
		// when
		final MvcResult result = mvc.perform(post("/application/cancel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}	
}
