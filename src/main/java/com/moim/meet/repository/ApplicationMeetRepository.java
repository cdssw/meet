package com.moim.meet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.Meet;
import com.moim.meet.entity.User;
import com.moim.meet.repository.custom.ApplicationMeetCustomRepository;

/**
 * ApplicationMeetRepository.java
 * 
 * @author cdssw
 * @since Apr 29, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 29, 2020   cdssw            최초 생성
 * </pre>
 */
public interface ApplicationMeetRepository extends JpaRepository<ApplicationMeet, Long>, ApplicationMeetCustomRepository {

	ApplicationMeet findByMeetAndUser(Meet meet, User user);
	void deleteByMeet(Meet meet); // meet에 연관된 applicationMeet 제거
	int countByUser(User user);
	int countByMeetAndUser(Meet meet, User user);
}
