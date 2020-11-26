-- user
insert into user(id, username, user_nm, user_nick_nm, phone, input_dt, modify_dt) values
(1, 'cdssw@naver.com', 'Andrew', 'Blue', '010-1111-1111', now(), now())
, (2, 'loh002@naver.com', 'Monica', 'Red','010-1111-1111', now(), now())
, (3, 'kimkh093@nate.com', '김규현', 'Developer','010-1111-1111', now(), now())
, (4, 'michael@naver.com', 'Michael', 'Green','010-1111-1111', now(), now());

-- meet
insert into meet(title, content, recruitment, application, cost, cost_option, address1, address2, sido, sgg, dt_option, start_dt, end_dt, tm_option, start_tm, end_tm, detail_day, user_id, end, input_dt, modify_dt) values
  ('무너진 계층 사다리', '무너진 계층 사다리', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 1, false, now(), now())
, ('육군훈련소, 훈련병', '육군훈련소, 훈련병', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('신속이 생명인데', '신속이 생명인데', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('검찰, 모뉴엘 전 대표', '검찰, 모뉴엘 전 대표', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 1, false, now(), now())
, ('기재차관 국가채무 증가', '기재차관 국가채무 증가', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('우리나라 최초 한글점자', '우리나라 최초 한글점자', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 1, false, now(), now())
, ('미 해리스 8월 해군기지', '미 해리스 8월 해군기지', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('분실 휴대폰서', '분실 휴대폰서', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('왜 아이폰 12 1차도 2차도 아닌 1.5차', '왜 아이폰 12 1차도 2차도 아닌 1.5차', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('미디어 생태계 발전 위해', '미디어 생태계 발전 위해', 1, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('콜레스테롤에 의한 대장암 전이', '콜레스테롤에 의한 대장암 전이', 3, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('최고속도로 무장한 애플', '최고속도로 무장한 애플', 2, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('영상 14도서 전기저항 제로', '영상 14도서 전기저항 제로', 2, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('1만원 팔면 1500원은 카카오가', '1만원 팔면 1500원은 카카오가', 2, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('구글 쫓는 중국의 구글', '구글 쫓는 중국의 구글', 2, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('온라인 의약품 불법 유통 신고', '온라인 의약품 불법 유통 신고', 2, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
, ('아이 등원 요청', '아이를 등원시켜 주실분 구합니다.', 2, 0, 50000, 0, '처인구 역북동', '663-4', '경기도', '용인시 처인구', false, null, null, false, '10:00', '16:00', 40, 2, false, now(), now())
;

-- application_meet
insert into application_meet(id, meet_id, user_id, approval_dt, approval_yn, input_dt, modify_dt) values
(1, 1, 2, null, false, now(), now())
, (2, 2, 1, null, false, now(), now())
, (3, 3, 1, null, false, now(), now())
, (5, 5, 1, null, false, now(), now())
, (7, 7, 1, null, false, now(), now())
