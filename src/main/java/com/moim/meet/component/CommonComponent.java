package com.moim.meet.component;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.except.ErrorCode;
import com.moim.meet.except.NotFoundException;

/**
 * CommonComponent.java
 * 
 * @author cdssw
 * @since Apr 21, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 21, 2020   cdssw            최초 생성
 * </pre>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
public class CommonComponent {

	// JpaRepository findById 처리를 위한 공통 Method
	@Transactional(readOnly = true) // 성능향상을 위해
	public <T, ID, C> C findById(T t, ID id, Class<C> type) {
		final Optional<C> m = ((JpaRepository)t).findById((long)id);
		m.orElseThrow(() -> new NotFoundException(ErrorCode.ELEMENT_NOT_FOUND));
		return type.cast(m.get());
	}
	
	// JpaRepository findById 처리를 위한 공통 Method with ErrorCode
	@Transactional(readOnly = true) // 성능향상을 위해
	public <T, ID, C> C findById(T t, ID id, Class<C> type, ErrorCode errorCode) {
		final Optional<C> m = ((JpaRepository)t).findById((long)id);
		m.orElseThrow(() -> new NotFoundException(errorCode));
		return type.cast(m.get());
	}
}
