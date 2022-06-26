-- user
INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (1, now(), 'SYSTEM', now(), 0, 'test@kakao.com', 'abcdef1234gef', 'loginId1234', 'testUser', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (2, now(), 'SYSTEM', now(), 0, 'hello-world@kakao.com', '1q2w3e4r1q2w3e4r', 'jdh1234', '장동혁', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (3, now(), 'SYSTEM', now(), 0, 'kim-kakao@kakao.com', '1aasdfwqwe', 'kim1234', '김카카오', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (4, now(), 'SYSTEM', now(), 0, 'cou@coupang.com', 'sdfkh21jh', 'cou1234', '나쿠팡', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (5, now(), 'SYSTEM', now(), 0, 'nice@nike.com', 'sdlfkjwekjkwlejt', 'nice119', '나이키', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (6, now(), 'SYSTEM', now(), 0, 'NEVER@naver.com', 'sdfkjwekljwlejt', 'never119', '네', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (7, now(), 'SYSTEM', now(), 0, 'apple@apple.com', 'zxjldsjglksj', 'appleOneBite', '사마천', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (8, now(), 'SYSTEM', now(), 0, 'nice123@nike.com', 'zzzzzzzzzzz', 'nice1xv19', '강호동', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (9, now(), 'SYSTEM', now(), 0, 'niceweew@nike.com', 'qrqwqwfqwf', 'nicedf119', '유재석', 0, 0);

INSERT INTO USERS (`user_id`, `created_at`, `created_by`, `modified_at`, `dormant`, `email`, `hashed_password`,
                   `login_id`, `username`, `withdrawn`, `deleted`)
values (10, now(), 'SYSTEM', now(), 0, 'werkjksjdfkj@sm.com', 'asgasdgasd', 'hohoho123', '김슬기', 0, 0);

-- store
INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (1, now(), 'SYSTEM', now(), 0, 'GROCERY', '우리마트');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (2, now(), 'SYSTEM', now(), 0, 'GROCERY', '내일마트');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (3, now(), 'SYSTEM', now(), 0, 'GROCERY', '카카오마트');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (4, now(), 'SYSTEM', now(), 0, 'COSMETICS', '카카오화장품');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (5, now(), 'SYSTEM', now(), 0, 'COSMETICS', '화장품B');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (6, now(), 'SYSTEM', now(), 0, 'COSMETICS', '화장품C');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (7, now(), 'SYSTEM', now(), 0, 'RESTAURANT', '중국집');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (8, now(), 'SYSTEM', now(), 0, 'RESTAURANT', '식당A');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (9, now(), 'SYSTEM', now(), 0, 'RESTAURANT', '식당B');

INSERT INTO STORE (`store_id`, `created_at`, `created_by`, `modified_at`, `deleted`, `industry_type`, `store_name`)
values (10, now(), 'SYSTEM', now(), 0, 'COSMETICS', '식당C');








