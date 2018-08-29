--############################## 2018-07-14 이전 ##############################
-- Story Blog Ver.2 User Test

-- 냉무 --

-- Story Blog Ver.2 Diary Test
select * from story_diary;
Select d_diary from story_diary where user_email = 'admin';
Select distinct d_diary from story_diary where user_email = 'admin'; --distinct는 중복 제거!!
SELECT nvl(count(*),0) FROM story_diary WHERE user_email ='admin' and d_diary ='11';
Select distinct d_diary from story_diary where user_email = 'admin' order by d_diary asc;
Select distinct d_diary from story_diary where user_email = 'admin' and d_diary != 'Test' order by d_diary asc;

-- 개별 검색 테스트 (해결.. 괄호문제였음)
select * from (select rownum rnum, b.* from (select num, d_diary, d_date, subject, content from story_diary
		 where user_email='admin' and d_diary = '11' and subject like '%3%' or content like '%3%' order by num desc) b) 
		 where rnum between 1 and 10; -- 불가능. 다 불러옴
select * from (select rownum rnum, b.* from (select num, d_diary, d_date, subject, content from story_diary
		 where user_email='admin' and d_diary = '11' and (subject like '%3%' or content like '%3%') order by num desc) b) 
		 where rnum between 1 and 10; -- 가능. 원하는 값 출력함
 
-- 다중 조건인가...ㅠ
select subject, d_diary, content from story_diary where d_diary = '11' and user_email = 'admin' and subject like '%3%'; -- 조건 3개정도 였을 때, 가능.. 4개부터 이상..
select subject, d_diary, content from story_diary where d_diary = '11' and user_email = 'admin' and subject like '%3%' or content like '%3%'; -- 4개, 불가능
select subject, d_diary, content from story_diary where d_diary = '11' and user_email = 'admin' and (subject like '%3%' or content like '%3%'); -- 괄호처리, 가능

SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and d_diary = '11' and (subject like '%3%' or content like '%3%');
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and d_diary = '11' and subject like '%3%';

-- 사진 불러오기 테스트
select * from (select rownum rnum, b.* from (select subject, d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary
		 where user_email = 'admin' and (FILENAME1 is not null or filename2 is not null or
         filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null)  order by d_date desc) b) where rnum between 1 and 20;

Select subject, d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary where user_email = 'admin' and 
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null) order by d_date desc;  

-- 갤러리 - 검색한 날짜 제외
Select distinct d_date from story_diary where user_email =  'admin' and d_date != '2018-07-14' and (filename1 is not null or filename2 is not null or filename3 is not null 
or filename4 is not null or filename5 is not null or filename6 is not null) order by d_date desc;

-- 사진 수
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and 
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null);
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and d_date = '2018-07-14' and
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null);

-- 최근 사진
-- 1 (최근 게시물 기준)
select filename1, filename2, filename3, filename4, filename5, filename6 FROM story_diary where user_email = 'admin' and (filename1 is not null or 
filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null) and rownum <= 3;
-- 2 (날짜 기준)
select * from (select rownum rnum, b.* from (select num, user_email, subject, d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary
		 where user_email ='admin' and 
		 (filename1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null) 
		 order by d_date desc) b) where rownum <= 3;

-- 최근 사진 - 전체 사진 수
-- 1 (최근 게시물 기준)
select nvl(count(filename1),0) + nvl(count(filename2),0) + nvl(count(filename3),0) + nvl(count(filename4),0) + nvl(count(filename5),0) + nvl(count(filename6),0) 
from story_diary where user_email = 'admin' and (filename1 is not null or filename2 is not null or filename3 is not null or 
filename4 is not null or filename5 is not null or filename6 is not null) and rownum <= 3;
-- 1-1
select nvl(count(filename1),0) + nvl(count(filename2),0) + nvl(count(filename3),0) + nvl(count(filename4),0) + nvl(count(filename5),0) + nvl(count(filename6),0) 
from story_diary where user_email = 'admin' and rownum <= 3;
-- 2 (날짜 기준)
select nvl(count(filename1),0) + nvl(count(filename2),0) + nvl(count(filename3),0) + nvl(count(filename4),0) + nvl(count(filename5),0) + nvl(count(filename6),0)
from (select rownum rnum, b.* from (select d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary
		 where user_email = 'admin' and (FILENAME1 is not null or filename2 is not null or
         filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null)  order by d_date desc) b) where  rownum <= 3;
-- end. ############################## 2018-07-14 이전 ##############################         
         
--############################## 2018-07-14 이후 ##############################

-- Story Blog Ver.2 User Test
-- Delete Join (탈퇴시 일기도 삭제)
delete from story_diary where user_email = '123@ccc'; -- 그냥 삭제하려고..
Delete from story_user A where A.email = 
(select B.user_email from story_diary B where user_email='123@ccc'); -- 이건 그냥 삭제나 다를바가없음..
delete from story_user from 
story_user aa INNER JOIN story_diary bb ON bb.user_email = aa.email where aa.email = '123@ccc'; -- mysql 문법.. 안댐
delete su, sd from story_user as su JOIN story_diary as sd on 
(su.email = sd.user_email) where su.email = '123@ccc'; -- 이거도 안돼..
delete from (select * from story_user as su, story_diary as sd where su.email = sd.user_email) where story_user.email = '123@ccc'; -- ㅅㅂ
delete from story_user where email='123@ccc'; delete from story_diary where user_email='123@ccc'; -- ##결국 각자 삭제.##

--- 유저 검색 ---
-- 4(S-Manager) 전용 --
 -- 목록
select * from (select rownum rnum, a.* from (select num, email, pwd, name, tel, birth, filename, cdate, p_level, ip from
 story_user where p_level != '4(S-Manager)' and email != 'admin' and (email like '%j%' or name like '%j%')
 order by cdate desc)a) where rnum between 1 and 10;
  -- 유저 수
SELECT nvl(count(*),0) FROM story_user where p_level != '4(S-Manager)' and email != 'admin' and (email like '%j%' or name like '%j%');
 -- 3(Manager) 전용 --
 -- 목록
 select * from (select rownum rnum, a.* from (select num, email, pwd, name, tel, birth, filename, cdate, p_level, ip from
 story_user where (p_level != '4(S-Manager)' and p_level != '3(Manager)') and email != 'admin' and (email like '%j%' or name like '%j%')
 order by cdate desc)a) where rnum between 1 and 10;
 -- 유저 수
 SELECT nvl(count(*),0) FROM story_user where (p_level != '4(S-Manager)' and p_level != '3(Manager)') and email != 'admin' and (email like '%j%' or name like '%j%');
 SELECT nvl(count(*),0) FROM story_user where (p_level != '4(S-Manager)' and p_level != '3(Manager)') and email != 'admin' and email like '%a%';

-- Story Blog Ver.2 Diary Test




