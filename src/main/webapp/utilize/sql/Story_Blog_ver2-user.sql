-- Story Blog Ver.2 User
select * from story_user;
-- CREATE --
CREATE TABLE story_user (
  num int not null primary key,
  email varchar2(100) not null,
  pwd varchar2(50) not null,
  name varchar2(50) not null,
  tel varchar2(100),
  birth varchar2(50),
  filename varchar2(100),
  filesize int,
  cdate date not null,
  p_level int,
  ip varchar2(100)
);

CREATE SEQUENCE story_userSer
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE ;

COMMIT;

-- DROP --
DROP TABLE story_user;
DROP SEQUENCE story_userSer;

COMMIT;

-- UPDATE --
UPDATE story_user SET p_level=3 WHERE email='admin';
UPDATE story_user SET email='admin', p_level=3 WHERE email='jhsonking@naver.com';

COMMIT;