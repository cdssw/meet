-- user
insert into user(id, username, user_nm, user_nick_nm, phone, input_dt, modify_dt) values
(1, 'cdssw@naver.com', 'Andrew', 'Blue', '010-1111-1111', now(), now())
, (2, 'loh002@naver.com', 'Monica', 'Red','010-1111-1111', now(), now())
, (3, 'michael@naver.com', 'Michael', 'Green','010-1111-1111', now(), now());

-- meet
insert into meet(title, content, recruitment, application, cost, cost_option, address1, address2, sido, sgg, start_dt, end_dt, start_tm, end_tm, detail_day, user_id, input_dt, modify_dt) values
  ('무너진 계층 사다리', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 1, now(), now())
, ('육군훈련소, 훈련병', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('신속이 생명인데', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('검찰, 모뉴엘 전 대표', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 1, now(), now())
, ('기재차관 국가채무 증가', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('우리나라 최초 한글점자', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 1, now(), now())
, ('미 해리스 8월 해군기지', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('분실 휴대폰서', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('왜 아이폰 12 1차도 2차도 아닌 1.5차', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('미디어 생태계 발전 위해', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('콜레스테롤에 의한 대장암 전이', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('최고속도로 무장한 애플', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('영상 14도서 전기저항 제로', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('1만원 팔면 1500원은 카카오가', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('구글 쫓는 중국의 구글', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('온라인 의약품 불법 유통 신고', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
, ('아이 등원 요청', '아이를 등원시켜 주실분 구합니다.', 4, 0, 50000, 0, '기흥구 중동', '663-4', '경기도', '용인시 처인구', '2020-09-01', '2020-09-30', '10:00', '16:00', 40, 2, now(), now())
;
