-- user
insert into user(id, user_nm, input_dt, modify_dt) values
(1, 'Andrew', now(), now())
, (2, 'Monica', now(), now())
, (3, 'Michael', now(), now());

-- meet
insert into meet(meet_nm, meet_desc, recruitment, application, cost, address, address_detail, user_id, input_dt, modify_dt) values
('지고메고 백패킹', '자연속에서 LNT를 실천하고 트레킹과 비박을 통해 힐링하는, 특별한 즐거움', 3, 2, 1000, '서울특별시 강남구 대치동', 'Startbucks', 1, now(), now())
, ('신문에 나온 알아두면 좋을 이야기들', '신문요약 포스팅', 1, 1, 10000, '경기 수원시 영통구 망포동', '206-4', 1, now(), now())
, ('세계의 명화', '인류가 남긴 위대한 명화들을 살피면서, 현재의 우리와 미래의 후손들이 아름다운 예술을 간접적으로나마 경험해볼 수 있도록 기약해 봅니다~', 3, 2, 0, '경기도 용인시 처인구 중부대로', '1294', 2, now(), now())
, ('과자 빵 간식.음식사랑', '과자ㆍ빵ㆍ떡ㆍ음식 좋아하는 밴드 최애간식 음식 ㆍ숨겨둔음식ㆍ몰래먹는간식 음식 대용량 간식 음식ㆍ 나만의 간식 음식 비법 뭐라도 먹자', 4, 0, 30000, '충북 충주시 단월동', '663-4', 2, now(), now());

-- applicationMeet
insert into application_meet(meet_id, user_id, approval_yn, approval_dt, input_dt, modify_dt) values
(1, 1, false, null, now(), now())
, (1, 2, false, null, now(), now())
, (2, 1, false, null, now(), now())
, (3, 1, false, null, now(), now())
, (3, 3, false, null, now(), now());