package com.moim.meet.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.QApplicationMeet;
import com.moim.meet.entity.QMeet;
import com.moim.meet.entity.QUser;
import com.moim.meet.service.mypage.MyPageDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * ApplicationMeetRepositoryImpl.java
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
public class ApplicationMeetCustomRepositoryImpl extends QuerydslRepositorySupport
		implements ApplicationMeetCustomRepository {

	final QMeet meet = QMeet.meet;
	final QUser user = QUser.user;
	final QApplicationMeet applicationMeet = QApplicationMeet.applicationMeet;
	
	public ApplicationMeetCustomRepositoryImpl() {
		super(ApplicationMeet.class);
	}

	// Meet에 해당 하는 사용자가 지원했는지 확인
	@Override
	public long countByMeetAndUserGroupByMeet(long meetId, long userId) {
		final QApplicationMeet qapplicationMeet = QApplicationMeet.applicationMeet;
		JPAQueryFactory query = new JPAQueryFactory(getEntityManager());
		return query.selectFrom(qapplicationMeet)
				.where(meet.id.eq(meetId), user.id.eq(userId))
				.groupBy(meet.id)
				.fetchCount();
	}

	// 사용자가 지원한 Meet List
	@Override
	public Page<MyPageDto.ApplicationRes> findMyPageApplication(Long userId, MyPageDto.ApplicationReq dto, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder = dto.getTitle() != null ? builder.and(meet.title.likeIgnoreCase("%" + dto.getTitle() + "%")) : builder;
		builder = dto.getContent() != null ? builder.and(meet.content.likeIgnoreCase("%" + dto.getContent() + "%")) : builder;
		builder = builder.and(applicationMeet.user.id.eq(userId));
		builder = dto.getToApproval() != null ? builder.and(applicationMeet.approval.approvalYn.eq(dto.getToApproval())) : builder;
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		final JPQLQuery<MyPageDto.ApplicationRes> query = queryFactory
				.select(Projections.bean(MyPageDto.ApplicationRes.class
						, meet.id
						, meet.title
						, meet.content
						, meet.recruitment
						, meet.application
						, meet.cost
						, meet.costOption
						, meet.term
						, meet.address
						, meet.inputDt
						, meet.modifyDt
						, meet.user
						, applicationMeet.approval
						))
				.from(applicationMeet)
				.leftJoin(meet).on(applicationMeet.meet.id.eq(meet.id))
				.where(builder);
		
		final List<MyPageDto.ApplicationRes> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(list, pageable, query.fetchCount());
	}

}
