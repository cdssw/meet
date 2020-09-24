package com.moim.meet.service.meet;
/**
 * ModelMapperTest.java
 * 
 * @author cdssw
 * @since Apr 10, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 10, 2020   cdssw            최초 생성
 * </pre>
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.moim.meet.entity.Meet;

public class ModelMapperTest {

	private ModelMapper modelMapper;
	
	@Before
	public void setUp() {
		modelMapper = new ModelMapper();
	}
	
	@Test
	public void testModelMapper() {
		// given
		MeetDto.MeetReq req = MeetDto.MeetReq.builder().title("First Meet").content("First save meet").cost(100).recruitment(3).build();
		Meet entity = req.toEntity();
		
		// when
		MeetDto.Res res = modelMapper.map(entity, MeetDto.Res.class);
		
		// then
		assertEquals(res.getTitle(), entity.getTitle());
	}
}
