-- Story Blog Ver.2 Diary
select * from story_diary;
-- CREATE --
CREATE TABLE story_diary(
  num INT NOT NULL PRIMARY KEY,
  user_email VARCHAR2(100) NOT NULL,
  user_name VARCHAR2(50) NOT NULL,
  d_diary VARCHAR2(100) DEFAULT 'Main',
  d_date VARCHAR2(100),
  d_cdate DATE NOT NULL,
  subject VARCHAR2(100) DEFAULT '제목없음',
  content NCLOB DEFAULT '내용없음',
  filename1 VARCHAR2(100),
  filesize1 INT,
  filename2 VARCHAR2(100),
  filesize2 INT,
  filename3 VARCHAR2(100),
  filesize3 INT,
  filename4 VARCHAR2(100),
  filesize4 INT,
  filename5 VARCHAR2(100),
  filesize5 INT,
  filename6 VARCHAR2(100),
  filesize6 INT
);

CREATE SEQUENCE story_diarySer
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE ;

COMMIT;

-- DROP --
DROP TABLE story_diary;
DROP SEQUENCE story_diarySer;

TRUNCATE TABLE story_diary;

COMMIT;