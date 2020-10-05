package com.moim.meet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moim.meet.entity.File;
import com.moim.meet.entity.Meet;

/**
 * FileRepository.java
 * 
 * @author cdssw
 * @since 2020. 9. 11.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 9. 11.    cdssw            최초 생성
 * </pre>
 */
public interface FileRepository extends JpaRepository<File, Long> {

	List<File> findByMeet(Meet meet);
}
