--############################## 2018-07-14 ���� ##############################
-- Story Blog Ver.2 User Test

-- �ù� --

-- Story Blog Ver.2 Diary Test
select * from story_diary;
Select d_diary from story_diary where user_email = 'admin';
Select distinct d_diary from story_diary where user_email = 'admin'; --distinct�� �ߺ� ����!!
SELECT nvl(count(*),0) FROM story_diary WHERE user_email ='admin' and d_diary ='11';
Select distinct d_diary from story_diary where user_email = 'admin' order by d_diary asc;
Select distinct d_diary from story_diary where user_email = 'admin' and d_diary != 'Test' order by d_diary asc;

-- ���� �˻� �׽�Ʈ (�ذ�.. ��ȣ��������)
select * from (select rownum rnum, b.* from (select num, d_diary, d_date, subject, content from story_diary
		 where user_email='admin' and d_diary = '11' and subject like '%3%' or content like '%3%' order by num desc) b) 
		 where rnum between 1 and 10; -- �Ұ���. �� �ҷ���
select * from (select rownum rnum, b.* from (select num, d_diary, d_date, subject, content from story_diary
		 where user_email='admin' and d_diary = '11' and (subject like '%3%' or content like '%3%') order by num desc) b) 
		 where rnum between 1 and 10; -- ����. ���ϴ� �� �����
 
-- ���� �����ΰ�...��
select subject, d_diary, content from story_diary where d_diary = '11' and user_email = 'admin' and subject like '%3%'; -- ���� 3������ ���� ��, ����.. 4������ �̻�..
select subject, d_diary, content from story_diary where d_diary = '11' and user_email = 'admin' and subject like '%3%' or content like '%3%'; -- 4��, �Ұ���
select subject, d_diary, content from story_diary where d_diary = '11' and user_email = 'admin' and (subject like '%3%' or content like '%3%'); -- ��ȣó��, ����

SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and d_diary = '11' and (subject like '%3%' or content like '%3%');
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and d_diary = '11' and subject like '%3%';

-- ���� �ҷ����� �׽�Ʈ
select * from (select rownum rnum, b.* from (select subject, d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary
		 where user_email = 'admin' and (FILENAME1 is not null or filename2 is not null or
         filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null)  order by d_date desc) b) where rnum between 1 and 20;

Select subject, d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary where user_email = 'admin' and 
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null) order by d_date desc;  

-- ������ - �˻��� ��¥ ����
Select distinct d_date from story_diary where user_email =  'admin' and d_date != '2018-07-14' and (filename1 is not null or filename2 is not null or filename3 is not null 
or filename4 is not null or filename5 is not null or filename6 is not null) order by d_date desc;

-- ���� ��
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and 
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null);
SELECT nvl(count(*),0) FROM story_diary WHERE user_email = 'admin' and d_date = '2018-07-14' and
(FILENAME1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null);

-- �ֱ� ����
-- 1 (�ֱ� �Խù� ����)
select filename1, filename2, filename3, filename4, filename5, filename6 FROM story_diary where user_email = 'admin' and (filename1 is not null or 
filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null) and rownum <= 3;
-- 2 (��¥ ����)
select * from (select rownum rnum, b.* from (select num, user_email, subject, d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary
		 where user_email ='admin' and 
		 (filename1 is not null or filename2 is not null or filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null) 
		 order by d_date desc) b) where rownum <= 3;

-- �ֱ� ���� - ��ü ���� ��
-- 1 (�ֱ� �Խù� ����)
select nvl(count(filename1),0) + nvl(count(filename2),0) + nvl(count(filename3),0) + nvl(count(filename4),0) + nvl(count(filename5),0) + nvl(count(filename6),0) 
from story_diary where user_email = 'admin' and (filename1 is not null or filename2 is not null or filename3 is not null or 
filename4 is not null or filename5 is not null or filename6 is not null) and rownum <= 3;
-- 1-1
select nvl(count(filename1),0) + nvl(count(filename2),0) + nvl(count(filename3),0) + nvl(count(filename4),0) + nvl(count(filename5),0) + nvl(count(filename6),0) 
from story_diary where user_email = 'admin' and rownum <= 3;
-- 2 (��¥ ����)
select nvl(count(filename1),0) + nvl(count(filename2),0) + nvl(count(filename3),0) + nvl(count(filename4),0) + nvl(count(filename5),0) + nvl(count(filename6),0)
from (select rownum rnum, b.* from (select d_date, filename1, filename2, filename3, filename4, filename5, filename6 from story_diary
		 where user_email = 'admin' and (FILENAME1 is not null or filename2 is not null or
         filename3 is not null or filename4 is not null or filename5 is not null or filename6 is not null)  order by d_date desc) b) where  rownum <= 3;
-- end. ############################## 2018-07-14 ���� ##############################         
         
--############################## 2018-07-14 ���� ##############################

-- Story Blog Ver.2 User Test
-- Delete Join (Ż��� �ϱ⵵ ����)
delete from story_diary where user_email = '123@ccc'; -- �׳� �����Ϸ���..
Delete from story_user A where A.email = 
(select B.user_email from story_diary B where user_email='123@ccc'); -- �̰� �׳� ������ �ٸ��ٰ�����..
delete from story_user from 
story_user aa INNER JOIN story_diary bb ON bb.user_email = aa.email where aa.email = '123@ccc'; -- mysql ����.. �ȴ�
delete su, sd from story_user as su JOIN story_diary as sd on 
(su.email = sd.user_email) where su.email = '123@ccc'; -- �̰ŵ� �ȵ�..
delete from (select * from story_user as su, story_diary as sd where su.email = sd.user_email) where story_user.email = '123@ccc'; -- ����
delete from story_user where email='123@ccc'; delete from story_diary where user_email='123@ccc'; -- ##�ᱹ ���� ����.##

--- ���� �˻� ---
-- 4(S-Manager) ���� --
 -- ���
select * from (select rownum rnum, a.* from (select num, email, pwd, name, tel, birth, filename, cdate, p_level, ip from
 story_user where p_level != '4(S-Manager)' and email != 'admin' and (email like '%j%' or name like '%j%')
 order by cdate desc)a) where rnum between 1 and 10;
  -- ���� ��
SELECT nvl(count(*),0) FROM story_user where p_level != '4(S-Manager)' and email != 'admin' and (email like '%j%' or name like '%j%');
 -- 3(Manager) ���� --
 -- ���
 select * from (select rownum rnum, a.* from (select num, email, pwd, name, tel, birth, filename, cdate, p_level, ip from
 story_user where (p_level != '4(S-Manager)' and p_level != '3(Manager)') and email != 'admin' and (email like '%j%' or name like '%j%')
 order by cdate desc)a) where rnum between 1 and 10;
 -- ���� ��
 SELECT nvl(count(*),0) FROM story_user where (p_level != '4(S-Manager)' and p_level != '3(Manager)') and email != 'admin' and (email like '%j%' or name like '%j%');
 SELECT nvl(count(*),0) FROM story_user where (p_level != '4(S-Manager)' and p_level != '3(Manager)') and email != 'admin' and email like '%a%';

-- Story Blog Ver.2 Diary Test




