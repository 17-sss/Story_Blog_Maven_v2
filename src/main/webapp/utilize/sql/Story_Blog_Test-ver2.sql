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

-- 사진 수
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and 
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null);

