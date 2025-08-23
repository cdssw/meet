-- user
insert into user(id, username, user_nm, user_nick_nm, phone, input_dt, modify_dt) values
(1, 'cdssw@naver.com', 'Andrew', 'Blue', '010-1111-1111', now(), now())
, (2, 'loh002@naver.com', 'Monica', 'HK','010-1111-1111', now(), now())
, (3, 'kimkh093@nate.com', '김규현', 'Developer','010-1111-1111', now(), now())
, (4, 'michael@naver.com', 'Michael', 'Green','010-1111-1111', now(), now());

-- meet
insert into meet(title, content, recruitment, application, cost, cost_option, address1, address2, sido, sgg, dt_option, start_dt, end_dt, tm_option, start_tm, end_tm, detail_day, user_id, end, input_dt, modify_dt) values
('다이소 매장 안내 업무', '매장 정리 및 단순 보조 업무 아르바이트 모집', 1, 0, 100000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '11:30', '18:30', 40, 1, false, now(), now())
, ('피트니스센터 보조 업무', '피트니스센터 내 간단한 정리 및 보조 업무 아르바이트 모집', 1, 0, 100000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:30', '17:00', 40, 2, false, now(), now())
, ('물류 센터 피킹 및 분류', '빠르고 정확한 작업이 필요한 물류 센터 피킹 아르바이트 모집', 1, 0, 50000, 0, '강남구 역삼동', '1', '서울특별시', '강남구', false, null, null, false, '10:00', '19:00', 40, 2, false, now(), now())
, ('사무 보조 및 정리', '사무실 내 문서 정리 및 보조 업무 아르바이트 모집', 1, 0, 90000, 0, '강남구 역삼동', '2', '서울특별시', '강남구', false, null, null, false, '11:00', '16:30', 40, 1, false, now(), now())
, ('단순 서류 정리', '서류 정리 및 입력 보조 업무 아르바이트 모집', 1, 0, 20000, 0, '강남구 역삼동', '3', '서울특별시', '강남구', false, null, null, false, '12:00', '15:30', 40, 2, false, now(), now())
, ('점자 책 제작 보조', '한글 점자 책 제작 보조 업무 아르바이트 모집', 1, 0, 60000, 0, '송파구 문정동', '1', '서울특별시', '송파구', false, null, null, false, '11:30', '18:00', 40, 1, false, now(), now())
, ('해군기지 내 단순 업무', '군 기지 내 물품 정리 및 단순 보조 업무 아르바이트 모집', 1, 0, 30000, 0, '송파구 방이동', '1', '서울특별시', '송파구', false, null, null, false, '10:30', '16:30', 40, 2, false, now(), now())
, ('분실물 정리 및 데이터 입력', '분실 휴대폰 데이터 입력 및 정리 아르바이트 모집', 1, 0, 50000, 0, '송파구 거여동', '1', '서울특별시', '송파구', false, null, null, false, '11:00', '17:30', 40, 2, false, now(), now())
, ('스마트폰 제품 정리', '아이폰 신제품 정리 및 포장 업무 아르바이트 모집', 1, 0, 30000, 0, '영통구 망포동', '1', '경기도', '수원시 영통구', false, null, null, false, '10:00', '19:00', 40, 2, false, now(), now())
, ('미디어 관련 서류 정리', '미디어 관련 서류 정리 및 입력 보조 업무 아르바이트 모집', 1, 0, 50000, 0, '영통구 망포동', '2', '경기도', '수원시 영통구', false, null, null, false, '11:30', '15:00', 40, 2, false, now(), now())
, ('실험 데이터 정리', '실험 데이터 정리 및 분석 보조 업무 아르바이트 모집', 3, 0, 20000, 0, '기흥구 구갈동', '20', '경기도', '용인시 기흥구', false, null, null, false, '10:30', '18:30', 40, 2, false, now(), now())
, ('전자 제품 판매 보조', '애플 제품 판매 및 고객 응대 보조 아르바이트 모집', 2, 0, 60000, 0, '분당구 수내동', '11', '경기도', '성남시 분당구', false, null, null, false, '12:00', '16:00', 40, 2, false, now(), now())
, ('전기저항 실험 보조', '과학 실험실 보조 및 실험 데이터 정리 아르바이트 모집', 2, 0, 20000, 0, '분당구 수내동', '50', '경기도', '성남시 분당구', false, null, null, false, '11:00', '17:00', 40, 2, false, now(), now())
, ('온라인 마켓 정산 보조', '온라인 판매 정산 및 데이터 입력 보조 업무 아르바이트 모집', 2, 0, 20000, 0, '광주시 태전동', '604-14', '경기도', '광주시', false, null, null, false, '11:30', '15:30', 40, 2, false, now(), now())
, ('검색 엔진 데이터 분석', '검색 엔진 데이터 수집 및 분석 보조 아르바이트 모집', 2, 0, 90000, 0, '광주시 태전동', '104', '경기도', '광주시', false, null, null, false, '10:30', '18:00', 40, 1, false, now(), now())
, ('의약품 유통 관리 보조', '온라인 의약품 유통 관련 데이터 정리 및 입력 아르바이트 모집', 2, 0, 80000, 0, '서초구 서초동', '사임동로8길 33', '서울특별시', '서초구', false, null, null, false, '11:00', '17:30', 40, 1, false, now(), now())
, ('아이 등원 도우미', '아이를 등원시켜 주실 분을 구합니다.', 2, 0, 60000, 0, '서초구 서초동', '서운로 62', '서울특별시', '서초구', false, null, null, false, '12:00', '19:00', 40, 1, false, now(), now())
;

-- application_meet
insert into application_meet(id, meet_id, user_id, approval_dt, approval_yn, estimate, input_dt, modify_dt) values
(1, 1, 2, null, false, 0, now(), now())
, (2, 2, 1, now(), true, 2, now(), now())
, (3, 3, 1, now(), true, 2, now(), now())
, (5, 5, 1, null, false, 0, now(), now())
, (7, 7, 1, null, false, 0, now(), now())
;