-- user
insert into user(id, username, user_nm, user_nick_nm, phone, input_dt, modify_dt) values
(1, 'cdssw@naver.com', 'Andrew', 'Blue', '010-1111-1111', now(), now())
, (2, 'loh002@naver.com', 'Monica', 'HK','010-1111-1111', now(), now())
, (3, 'kimkh093@nate.com', '김규현', 'Developer','010-1111-1111', now(), now())
, (4, 'michael@naver.com', 'Michael', 'Green','010-1111-1111', now(), now());

-- meet
insert into meet(title, content, recruitment, application, cost, cost_option, address1, address2, sido, sgg, dt_option, start_dt, end_dt, tm_option, start_tm, end_tm, detail_day, user_id, end, input_dt, modify_dt) values
  ('다이소 매장 안내 업무', '매장 정리 및 단순 보조 업무 아르바이트 모집', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 1, false, now(), now())
, ('피트니스센터 보조 업무', '피트니스센터 내 간단한 정리 및 보조 업무 아르바이트 모집', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('물류 센터 피킹 및 분류', '빠르고 정확한 작업이 필요한 물류 센터 피킹 아르바이트 모집', 1, 0, 50000, 0, '강남구 역삼동', '1', '서울특별시', '강남구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('사무 보조 및 문서 정리', '사무실 내 문서 정리 및 간단한 보조 업무 아르바이트', 1, 0, 50000, 0, '강남구 역삼동', '2', '서울특별시', '강남구', false, null, null, false, '10:00', '16:00', 40, 1, false, now(), now())
, ('회계팀 서류 정리 아르바이트', '국가 재정 관련 문서 정리 및 간단한 보조 업무', 1, 0, 50000, 0, '강남구 역삼동', '3', '서울특별시', '강남구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('박물관 안내 및 관리', '박물관 내 관람객 안내 및 관리 아르바이트 모집', 1, 0, 50000, 0, '송파구 문정동', '1', '서울특별시', '송파구', false, null, null, false, '10:00', '16:00', 40, 1, false, now(), now())
, ('음식점 내 보조 업무', '음식점 내 간단한 정리 및 보조 업무 아르바이트 모집', 1, 0, 50000, 0, '송파구 방이동', '1', '서울특별시', '송파구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('유실물 정리 및 데이터 입력', '분실 휴대폰 및 유실물 데이터 입력 및 정리 업무', 1, 0, 50000, 0, '송파구 거여동', '1', '서울특별시', '송파구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('전자제품 매장 판매 보조', '아이폰 판매점에서 간단한 보조 및 고객 응대 아르바이트', 1, 0, 50000, 0, '영통구 망포동', '1', '경기도', '수원시 영통구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('미디어 관련 자료 정리', '미디어 관련 서류 정리 및 자료 입력 아르바이트 모집', 1, 0, 50000, 0, '영통구 망포동', '2', '경기도', '수원시 영통구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('실험실 연구 보조', '대장암 연구 실험 보조 및 데이터 정리 아르바이트 모집', 3, 0, 50000, 0, '기흥구 구갈동', '20', '경기도', '용인시 기흥구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('IT 기기 테스트 보조', '애플 기기 성능 테스트 보조 및 데이터 정리 아르바이트 모집', 2, 0, 50000, 0, '분당구 수내동', '11', '경기도', '성남시 분당구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('전자 부품 테스트', '전자 제품의 전기 저항 측정 및 테스트 보조 업무', 2, 0, 50000, 0, '분당구 수내동', '50', '경기도', '성남시 분당구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('온라인 쇼핑몰 물류 보조', '온라인 주문 상품 포장 및 배송 업무 보조 아르바이트', 2, 0, 50000, 0, '광주시 태전동', '604-14', '경기도', '광주시', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('중국어 번역 및 데이터 입력', '중국 관련 자료 번역 및 데이터 입력 업무 아르바이트 모집', 2, 0, 50000, 0, '광주시 태전동', '104', '경기도', '광주시', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('온라인 의약품 판매 모니터링', '온라인 의약품 불법 유통 신고 및 모니터링 업무', 2, 0, 50000, 0, '서초구 서초동', '사임동로8길 33', '서울특별시', '서초구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('아이 등원 도우미 모집', '유치원 등원 도우미 아르바이트 모집 (차량 운행 가능자 우대)', 2, 0, 50000, 0, '서초구 서초동', '서운로 62', '서울특별시', '서초구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
;

-- application_meet
insert into application_meet(id, meet_id, user_id, approval_dt, approval_yn, estimate, input_dt, modify_dt) values
(1, 1, 2, null, false, 0, now(), now())
, (2, 2, 1, now(), true, 2, now(), now())
, (3, 3, 1, now(), true, 2, now(), now())
, (5, 5, 1, null, false, 0, now(), now())
, (7, 7, 1, null, false, 0, now(), now())
;