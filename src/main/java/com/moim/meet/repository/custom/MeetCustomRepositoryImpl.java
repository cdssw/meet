package com.moim.meet.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.moim.meet.entity.Meet;
import com.moim.meet.entity.QApplicationMeet;
import com.moim.meet.entity.QMeet;
import com.moim.meet.service.meet.MeetDto;
import com.moim.meet.service.mypage.MyPageDto;
import com.moim.meet.service.mypage.MyPageDto.OpenedRes;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * MeetCustomRepositoryImpl.java
 * 
 * @author cdssw
 * @since Apr 25, 2020
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 25, 2020   cdssw            최초 생성
 * </pre>
 */
@Transactional(readOnly = true)
public class MeetCustomRepositoryImpl extends QuerydslRepositorySupport implements MeetCustomRepository {
	
	final QMeet meet = QMeet.meet;
	final QApplicationMeet applicationMeet = QApplicationMeet.applicationMeet;
	
	public MeetCustomRepositoryImpl() {
		super(Meet.class);
	}
	
	// 전체 Meet 검색
	@Override
	public Page<Meet> findSearch(MeetDto.SearchReq dto, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder = dto.getTitle() != null ? builder.and(meet.title.likeIgnoreCase("%" + dto.getTitle() + "%")) : builder;
		builder = dto.getContent() != null ? builder.and(meet.content.likeIgnoreCase("%" + dto.getContent() + "%")) : builder;
		builder = dto.getLeaderId() != null ? builder.and(meet.user.id.eq(dto.getLeaderId())) : builder;

		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		final JPQLQuery<Meet> query = queryFactory
				.selectFrom(meet)
				.where(builder);
		
		final List<Meet> meetList = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(meetList, pageable, query.fetchCount());
	}

	// 사용자가 개설한 Meet
	@Override
	public Page<MyPageDto.OpenedRes> findMyPageOpened(Long userId, MyPageDto.OpenedReq dto, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder = dto.getTitle() != null ? builder.and(meet.title.likeIgnoreCase("%" + dto.getTitle() + "%")) : builder;
		builder = dto.getContent() != null ? builder.and(meet.content.likeIgnoreCase("%" + dto.getContent() + "%")) : builder;
		builder = builder.and(meet.user.id.eq(userId));
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		final JPQLQuery<OpenedRes> query = queryFactory
				.select(Projections.bean(OpenedRes.class
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
						, applicationMeet.count().as("toApprovalCnt")
						))
				.from(meet)
				.leftJoin(applicationMeet).on(meet.id.eq(applicationMeet.meet.id), applicationMeet.approval.approvalYn.eq(dto.getToApproval()))
				.where(builder)
				.groupBy(meet);
		
		final List<OpenedRes> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(list, pageable, query.fetchCount());
	}

}
