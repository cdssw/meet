package com.moim.meet.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moim.meet.service.mypage.MyPageDto;

import lombok.extern.slf4j.Slf4j;

/**
 * MyPageControllerTest.java
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
@WebMvcTest // controller 관련 bean만 로딩
@Slf4j
public class MyPageControllerTest extends BaseControllerTest {

private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MyPageDto.OpenedReq openDto;
	private MyPageDto.OpenedRes open1;
	private MyPageDto.OpenedRes open2;
	
	private MyPageDto.ApplicationReq appDto;
	private MyPageDto.ApplicationRes app1;
	private MyPageDto.ApplicationRes app2;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 항상 결과 print
				.build();
		
		openDto = MyPageDto.OpenedReq.builder()
				.title("meet1")
				.leaderId(1L)
				.toAppBoolean(false)
				.build();
		open1 = MyPageDto.OpenedRes.builder()
				.title("meet1")
				.content("meet1 desc")
				.recruitment(10)
				.application(1)
				.cost(10000)
				.build();
		open2 = MyPageDto.OpenedRes.builder()
				.title("meet2")
				.content("meet2 desc")
				.recruitment(20)
				.application(1)
				.cost(20000)
				.build();
		
		appDto = MyPageDto.ApplicationReq.builder()
				.title("meet1")
				.userId(1L)
				.toAppBoolean(false)
				.build();
		app1 = MyPageDto.ApplicationRes.builder()
				.title("meet1")
				.content("meet1 desc")
				.recruitment(10)
				.application(1)
				.cost(10000)
				.build();
		app2 = MyPageDto.ApplicationRes.builder()
				.title("meet2")
				.content("meet2 desc")
				.recruitment(20)
				.application(1)
				.cost(20000)
				.build();
	}

	@Test
	public void testMyPageOpened() throws Exception {
		// given
		List<MyPageDto.OpenedRes> list = Arrays.asList(open1, open2);
		Pageable pageable = PageRequest.of(0, 10);
		Page<MyPageDto.OpenedRes> pageList = new PageImpl<>(list, pageable, list.size());
		given(myPageService.opened(any(), any())).willReturn(pageList);
		
		// when
		final MvcResult result = mvc.perform(post("/mypage/opened")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(openDto)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}

	@Test
	public void testMyPageApplication() throws Exception {
		// given
		List<MyPageDto.ApplicationRes> list = Arrays.asList(app1, app2);
		Pageable pageable = PageRequest.of(0, 10);
		Page<MyPageDto.ApplicationRes> pageList = new PageImpl<>(list, pageable, list.size());
		given(myPageService.application(any(), any())).willReturn(pageList);
		
		// when
		final MvcResult result = mvc.perform(post("/mypage/application")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(appDto)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}	
}
