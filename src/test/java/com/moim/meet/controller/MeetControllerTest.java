package com.moim.meet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.moim.meet.service.meet.MeetDto;

import lombok.extern.slf4j.Slf4j;

/**
 * MeetControllerTest.java
 * 
 * @author cdssw
 * @since Apr 18, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 18, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@WebMvcTest // controller 관련 bean만 로딩
@Slf4j
public class MeetControllerTest extends ControllerTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MeetDto.MeetReq dto;
	private MeetDto.Res res1;
	private MeetDto.Res res2;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 항상 결과 print
				.build();
		
		dto = MeetDto.MeetReq.builder().meetNm("meet1").meetDesc("meet1 desc").recruitment(10).application(1).cost(10000).userId(1L).build();
		res1 = MeetDto.Res.builder().meetNm("meet1").meetDesc("meet1 desc").recruitment(10).application(1).cost(10000).build();
		res2 = MeetDto.Res.builder().meetNm("meet2").meetDesc("meet2 desc").recruitment(20).application(1).cost(20000).build();
	}
	
	// 테스트 하는것은 dto를 가지고 controller 호출이 잘 되는지 확인
	@Test
	public void testCreateMeet() throws Exception {
		// given
		// 서비스 호출시 무조건 1L 리턴
		given(meetService.createMeet(any(MeetDto.MeetReq.class))).willReturn(1L);
		
		// when
		final MvcResult result = mvc.perform(post("/meet")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated()) // 201 확인
				.andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		// assert
		assertEquals(content, "1");		
	}
	
	// 테스트 하는것은 dto를 가지고 controller 호출시 valid 처리가 잘 되는지 확인
	@Test
	public void testCreateMeetValidExcept() throws Exception {
		// given
		MeetDto.MeetReq dto = MeetDto.MeetReq.builder().meetNm("meet1").meetDesc("meet1 desc").recruitment(10).application(1).cost(10000).build();
		given(meetService.createMeet(any(MeetDto.MeetReq.class))).willReturn(1L);
		
		// when
		final MvcResult result = mvc.perform(post("/meet")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isBadRequest()) // bad-request(400) 확인
				.andReturn();
		
		// then
		log.error(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetMeetList() throws Exception {
		// given
		
		given(meetService.getMeetList()).willReturn(Arrays.asList(res1, res2));
		
		// when
		final MvcResult result = mvc.perform(get("/meet")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testEditMeet() throws Exception {
		// given
		given(meetService.editMeet(1, dto)).willReturn(res1);
		
		// when
		final MvcResult result = mvc.perform(put("/meet/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testGetMeet() throws Exception {
		// given
		given(meetService.getMeet(anyLong())).willReturn(res1);
		
		// when
		final MvcResult result = mvc.perform(get("/meet/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testDeleteMeet() throws Exception {
		// when
		final MvcResult result = mvc.perform(delete("/meet/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testGetMeetListByPage() throws Exception {
		// given
		List<MeetDto.Res> list = Arrays.asList(res1, res2);
		Pageable pageable = PageRequest.of(0, 10);
		Page<MeetDto.Res> pageList = new PageImpl<>(list, pageable, list.size());
		given(meetService.getMeetListByPage(pageable)).willReturn(pageList);
		
		// when
		final MvcResult result = mvc.perform(get("/meet")
				.contentType(MediaType.APPLICATION_JSON)
				.param("page", "0")
				.param("size", "10"))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
	
	@Test
	public void testSearch() throws Exception {
		// given
		List<MeetDto.Res> list = Arrays.asList(res1, res2);
		Pageable pageable = PageRequest.of(0, 10);
		Page<MeetDto.Res> pageList = new PageImpl<>(list, pageable, list.size());
		given(meetService.search(any(MeetDto.SearchReq.class), any())).willReturn(pageList);
		MeetDto.SearchReq dto = MeetDto.SearchReq.builder().meetDesc("name").leaderId(1L).build();
		
		// when
		final MvcResult result = mvc.perform(post("/meet/search")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andReturn();
		
		// then
		log.info(result.getRequest().getContentAsString());
	}
}
