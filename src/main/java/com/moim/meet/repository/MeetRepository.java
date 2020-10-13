package com.moim.meet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.meet.entity.Meet;
import com.moim.meet.repository.custom.MeetCustomRepository;

/**
 * MeetRepository.java
 * 
 * @author cdssw
 * @since Apr 6, 2020
 * @description Meet Table CRUD
 * 추가적인 데이터 접근이 필요하면 여기에 추가
 * 다른 서비스에서도 사용하기에 service pkg에서 분리
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 6, 2020    cdssw            최초 생성
 * </pre>
 */
// MeetCustomRepository를 추가한것은 Service에서 MeetRepository 하나로 확장하기 위함
public interface MeetRepository extends JpaRepository<Meet, Long>, MeetCustomRepository {

	// Lazy Fetch 타입으로 relation이 있는 경우 N+1 문제없이 한꺼번에 join 할수 있도록 EntityGraph 처리
	@EntityGraph(attributePaths = "user")
	Page<Meet> findByIdNotNull(Pageable pageable);
	
	Page<Meet> findAllByOrderByIdDesc(Pageable pageable);
}
