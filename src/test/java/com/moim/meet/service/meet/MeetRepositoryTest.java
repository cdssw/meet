package com.moim.meet.service.meet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moim.meet.config.JpaAuditingConfig;
import com.moim.meet.entity.Meet;
import com.moim.meet.repository.MeetRepository;
import com.moim.meet.service.mypage.MyPageDto;

/**
 * MeetRepositoryTest.java
 * 
 * @author cdssw
 * @since Apr 8, 2020
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 8, 2020    cdssw            최초 생성
 * </pre>
 */
@RunWith(SpringRunner.class)
@DataJpaTest(includeFilters = @Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = {JpaAuditingConfig.class}))
@ActiveProfiles("test")
public class MeetRepositoryTest {

	@Autowired
	MeetRepository meetRepository;
	
	@After
	public void cleanup() {
		meetRepository.deleteAll();
	}
	
	// 테스트 하는것은 create/modify time 동작여부 확인
	@Test
	public void testBaseTimeEntity() {
		// when
		List<Meet> meetList = meetRepository.findAll();
		
		// then
		Meet meet = meetList.get(0);
		LocalDateTime now = LocalDateTime.now();
		assertTrue(meet.getInputDt().isBefore(now));
		assertTrue(meet.getModifyDt().isBefore(now));
	}
	
	// 1개만 조회되는지 확인
	@Test
	public void testFindSearch() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		MeetDto.SearchReq dto = MeetDto.SearchReq.builder()
				.sido("경기도")
				.sgg("용인시 처인구")
				.build();

		// when
		Page<MeetDto.Res> res = meetRepository.findSearch(dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(2L);
	}
	
	@Test
	public void tesetFindMyPageOpened() {
		// given
		Pageable pageable = PageRequest.of(0, 10);
		MyPageDto.OpenedReq dto = MyPageDto.OpenedReq.builder()
				.toApproval(false)
				.build();

		// when
		Page<MyPageDto.OpenedRes> res = meetRepository.findMyPageOpened(1L, dto, pageable);
		
		// then
		assertThat(res.getTotalElements()).isEqualTo(3);
	}
}
