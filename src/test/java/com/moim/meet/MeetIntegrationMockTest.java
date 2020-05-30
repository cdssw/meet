package com.moim.meet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.moim.meet.except.ErrorCode;
import com.moim.meet.except.NotFoundException;

/**
 * MeetIntegrationTest.java
 * 
 * @author cdssw
 * @since Apr 28, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 28, 2020   cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MeetIntegrationMockTest {

	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글깨짐 방지 처리
				.alwaysDo(print()) // 결과 항상 print
				.build();
	}
	
	@Test
	public void testNotFoundException() throws Exception {
		this.mvc.perform(get("/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(
						e -> assertEquals(
								(((NotFoundException)e.getResolvedException()).getErrorCode()),
								ErrorCode.ELEMENT_NOT_FOUND));
	}
}
