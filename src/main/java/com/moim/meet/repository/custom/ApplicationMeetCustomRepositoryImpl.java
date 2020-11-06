package com.moim.meet.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.moim.meet.entity.ApplicationMeet;
import com.moim.meet.entity.QApplicationMeet;
import com.moim.meet.entity.QChat;
import com.moim.meet.entity.QMeet;
import com.moim.meet.entity.QUser;
import com.moim.meet.entity.User;
import com.moim.meet.service.application.ApplicationDto;
import com.moim.meet.service.mypage.MyPageDto;
import com.moim.meet.service.mypage.MyPageDto.ApplicationReq;
import com.moim.meet.service.mypage.MyPageDto.ApplicationRes;
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
	final QChat chat = QChat.chat;
	
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
						, chat.count().as("chatCnt")
						))
				.from(applicationMeet)
				.leftJoin(meet).on(applicationMeet.meet.id.eq(meet.id))
				.leftJoin(chat).on(meet.id.eq(chat.meetId).and(meet.user.username.ne(chat.sender)))
				.where(builder)
				.groupBy(meet);
		
		final List<MyPageDto.ApplicationRes> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(list, pageable, query.fetchCount());
	}

	@Override
	public List<ApplicationDto.ApplicationUserRes> findUserByApplicationMeet(Long meetId) {
		BooleanBuilder builder = new BooleanBuilder();
		builder = builder.and(applicationMeet.meet.id.eq(meetId));
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
		return queryFactory
				.select(Projections.bean(ApplicationDto.ApplicationUserRes.class
						, applicationMeet.user.id
						, applicationMeet.user.username
						, applicationMeet.user.userNickNm
						, applicationMeet.user.avatarPath
						, applicationMeet.approval.approvalYn
						, applicationMeet.approval.approvalDt
						))
				.from(applicationMeet)
				.where(builder)
				.fetch();
	}

	// 사용자가 지원하거나 채팅한 Meet List
	@Override
	public Page<ApplicationRes> findMyPageChatAndApplication(User user, List<Long> chatList, ApplicationReq dto, Pageable pageable) {
		BooleanBuilder builder = new BooleanBuilder();
		builder = builder.and(meet.user.id.ne(user.getId()));
		builder = builder.and((applicationMeet.id.isNotNull().or(chat.id.isNotNull())));
		builder = dto.getTitle() != null ? builder.and(meet.title.likeIgnoreCase("%" + dto.getTitle() + "%")) : builder;
		builder = dto.getContent() != null ? builder.and(meet.content.likeIgnoreCase("%" + dto.getContent() + "%")) : builder;
		
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
						, meet.count().as("chatCnt")
						))
				.from(meet)
				.leftJoin(applicationMeet).on(meet.id.eq(applicationMeet.meet.id).and(applicationMeet.user.id.eq(user.getId())))
				.leftJoin(chat).on(meet.id.eq(chat.meetId).and(chat.sender.ne(meet.user.username)).and(meet.id.in(chatList)))
				.where(builder)
				.groupBy(meet);
		
		final List<MyPageDto.ApplicationRes> list = getQuerydsl().applyPagination(pageable, query).fetch();
		return new PageImpl<>(list, pageable, query.fetchCount());
	}

}
