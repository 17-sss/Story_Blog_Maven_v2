package story.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.MybatisConnector;

public class DiaryDBMyBatis extends MybatisConnector {
	private final String namespace = "diary.mybatis";
	private static DiaryDBMyBatis instance = new DiaryDBMyBatis();
	private DiaryDBMyBatis() {}
	public static DiaryDBMyBatis getInstance() {
		return instance;
	}
	SqlSession sqlSession;
	
	// �ϱ� ����
	public void insertDiary(DiaryDataBean diary) {
		sqlSession= sqlSession();
		int number = sqlSession.selectOne(namespace + ".getNextNumber",diary);
		
		diary.setNum(number);
	
		sqlSession.insert(namespace + ".insertDiary", diary);
		sqlSession.commit();
		sqlSession.close();
	}
	
	// �ϱ� �� (�̸���)
	public int getDiaryCount (String user_email) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		x = sqlSession.selectOne(namespace+".getDiaryCount1", map);
		sqlSession.close();
		return x;
	}
	
	// �ϱ� �� (�̸��� & �ϱ���)
	public int getDiaryCount (String user_email, String d_diary) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("d_diary", d_diary);
		
		x = sqlSession.selectOne(namespace+".getDiaryCount2", map);
		sqlSession.close();
		return x;
	}
	
	// �ϱ� ��� �������� (�̸���) [���� - �ۼ��� ���� (�⺻)]
	public List getDiaries (int startRow, int endRow, String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getDiaries1" ,map);
		sqlSession.close();
		return li;
	}
	
	// �ϱ� ��� �������� (�̸���) [���� - �ϱ� ��¥ ����]
	public List getDiaries_d (int startRow, int endRow, String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getDiaries1_d" ,map);
		sqlSession.close();
		return li;
	}
	
	// �ϱ� ��� �������� (�̸��� & �ϱ���) [���� - �ۼ��� ���� (�⺻)]
	public List getDiaries (int startRow, int endRow, String user_email, String d_diary) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("user_email", user_email);
		map.put("d_diary", d_diary);
		List li = sqlSession.selectList(namespace + ".getDiaries2" ,map);
		sqlSession.close();
		return li;
	}

	// �ϱ� ��� �������� (�̸��� & �ϱ���) [���� - �ϱ� ��¥ ����]
	public List getDiaries_d (int startRow, int endRow, String user_email, String d_diary) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("user_email", user_email);
		map.put("d_diary", d_diary);
		List li = sqlSession.selectList(namespace + ".getDiaries2_d" ,map);
		sqlSession.close();
		return li;
	}
	
	// ������ - ���� �� (�̸���)
	public int getGalleryCount (String user_email) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		
		x = sqlSession.selectOne(namespace+".getGalleryCount1", map);
		sqlSession.close();
		return x;
	}
	
	// ������ - ���� �� (�̸���, ��¥)
	public int getGalleryCount (String user_email, String date_opt) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("date_opt", date_opt);
		
		x = sqlSession.selectOne(namespace+".getGalleryCount2", map);
		sqlSession.close();
		return x;
	}
	
	// ������ - ���� �������� (�̸���)
	public List getGallery (int startRow, int endRow, String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getGallery1" ,map);
		sqlSession.close();
		return li;
	}
	
	// ������ - ���� �������� (�̸���, ��¥)
	public List getGallery (int startRow, int endRow, String user_email, String date_opt) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("user_email", user_email);
		map.put("date_opt", date_opt);
		List li = sqlSession.selectList(namespace + ".getGallery2" ,map);
		sqlSession.close();
		return li;
	}
	
	// ������ - ��¥ �������� (�̸��� ����) (������ ��¥ �˻� ����)
	public List getGalleryDate (String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getGalleryDate" ,map);
		sqlSession.close();
		return li;
	}
	
	// ������ - ��¥ ��������2 (�̸��� ����) (������ ��¥ �˻� ����(�˻��� ��¥�� ����))
	public List getGalleryDate (String user_email, String date_opt) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("date_opt", date_opt);
		List li = sqlSession.selectList(namespace + ".getGalleryDate2" ,map);
		sqlSession.close();
		return li;
	}
	
	// ������ - �ֱ� �ϱ� n�� �Խù� ���� (���� ����)
	public int getGalleryRecentCount (String user_email, int recent_num) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("recent_num", recent_num);
		
		x = sqlSession.selectOne(namespace+".getGalleryRecentCount", map);
		sqlSession.close();
		return x;
	}
	
	// ������ - �ֱ� �ϱ� n�� �Խù���ŭ ���� �������� (���� ����)
	public List getGalleryRecent (String user_email, int recent_num) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("recent_num", recent_num);
		List li = sqlSession.selectList(namespace + ".getGalleryRecent" ,map);
		sqlSession.close();
		return li;
	}
	
	// �ϱ��� ��� �������� (�̸��� ����) (�ϱ��� �������� ����)
	public List getDiarylist (String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getDiarylist" ,map);
		sqlSession.close();
		return li;
	}
	
	// �ϱ��� ��� ��������2 (�̸��� ����) (�ϱ��� ���� ����(������ �ϱ��� �ϱ����� ����))
	public List getDiarylist (String user_email, String not_in_d_diary) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("not_in_d_diary", not_in_d_diary);
		List li = sqlSession.selectList(namespace + ".getDiarylist2" ,map);
		sqlSession.close();
		return li;
	}
	
	
	
	/* ======== !!!!!! �˻� ���� !!!!!! ======== */
	
	/* $ ��ü $ */
	
	/* ~ Count ~ */
	// (��ü) �ϱ� ��  - ���� & ����
	public int getSearchDiaryCount_SC(String user_email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("search", search);
		x = sqlSession.selectOne(namespace+".getSearchDiaryCount_SC", map);
		sqlSession.close();
		
		return x;
	}
	
	// (��ü) �ϱ� ��  - ����
	public int getSearchDiaryCount_S(String user_email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("search", search);
		x = sqlSession.selectOne(namespace+".getSearchDiaryCount_S", map);
		sqlSession.close();
		
		return x;
	}
	
	// (��ü) �ϱ� ��  - ����
	public int getSearchDiaryCount_C(String user_email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("search", search);
		x = sqlSession.selectOne(namespace+".getSearchDiaryCount_C", map);
		sqlSession.close();
		
		return x;
	}
	
	
	
	/* ~ ��� �������� [��ü] ~ */
	// (��ü) �ϱ� ��� ��������  - ���� & ���� [���� - �ۼ��� ���� (�⺻)]
	public List searchDiary_SC(int startRow, int endRow, String user_email, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_SC",map);
        sqlSession.close();
        
        return li;
	}
	
	// (��ü) �ϱ� ��� ��������  - ���� & ���� [���� - �ϱ� ��¥ ����]
	public List searchDiary_SC_d(int startRow, int endRow, String user_email, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_SC_d",map);
        sqlSession.close();
        
        return li;
	}
	
	// (��ü) �ϱ� ��� ��������  - ���� [���� - �ۼ��� ���� (�⺻)]
	public List searchDiary_S(int startRow, int endRow, String user_email, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_S",map);
        sqlSession.close();
        
        return li;
	}
	
	// (��ü) �ϱ� ��� ��������  - ���� [���� - �ϱ� ��¥ ����]
	public List searchDiary_S_d(int startRow, int endRow, String user_email, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_S_d",map);
        sqlSession.close();
        
        return li;
	}
	
	// (��ü) �ϱ� ��� ��������  - ���� [���� - �ۼ��� ���� (�⺻)]
	public List searchDiary_C(int startRow, int endRow, String user_email, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_C",map);
        sqlSession.close();
        
        return li;
	}
	
	// (��ü) �ϱ� ��� ��������  - ���� [���� - �ϱ� ��¥ ����]
	public List searchDiary_C_d(int startRow, int endRow, String user_email, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_C_d",map);
        sqlSession.close();
        
        return li;
	}
	/* end. $ ��ü $ */
	
	
	
	/* $ ���� $ */
	
	/* ~ Count2 ~ */
	// (����) �ϱ� ��  - ���� & ����
	public int getSearchDiaryCount_D_SC(String user_email, String d_diary, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("d_diary", d_diary);
		map.put("search", search);
		x = sqlSession.selectOne(namespace+".getSearchDiaryCount_D_SC", map);
		sqlSession.close();
		
		return x;
	}
	
	// (����) �ϱ� ��  - ����
	public int getSearchDiaryCount_D_S(String user_email, String d_diary, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("d_diary", d_diary);
		map.put("search", search);
		x = sqlSession.selectOne(namespace+".getSearchDiaryCount_D_S", map);
		sqlSession.close();
		
		return x;
	}
	
	// (����) �ϱ� ��  - ����
	public int getSearchDiaryCount_D_C(String user_email, String d_diary, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("d_diary", d_diary);
		map.put("search", search);
		x = sqlSession.selectOne(namespace+".getSearchDiaryCount_D_C", map);
		sqlSession.close();
		
		return x;
	}
	

	/* ~ ��� �������� [����] ~ */
	// (����) �ϱ� ��� ��������  - ���� & ����  [���� - �ۼ��� ���� (�⺻)]
	public List searchDiary_D_SC(int startRow, int endRow, String user_email, String d_diary, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("d_diary", d_diary);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_D_SC",map);
        sqlSession.close();
        
        return li;
	}
	
	// (����) �ϱ� ��� ��������  - ���� & ����  [���� - �ϱ� ��¥ ����]
	public List searchDiary_D_SC_d(int startRow, int endRow, String user_email, String d_diary, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("d_diary", d_diary);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_D_SC_d",map);
        sqlSession.close();
        
        return li;
	}
	
	// (����) �ϱ� ��� ��������  - ���� [���� - �ۼ��� ���� (�⺻)]
	public List searchDiary_D_S(int startRow, int endRow, String user_email, String d_diary, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("d_diary", d_diary);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_D_S",map);
        sqlSession.close();
        
        return li;
	}
	
	// (����) �ϱ� ��� ��������  - ���� [���� - �ϱ� ��¥ ����]
	public List searchDiary_D_S_d(int startRow, int endRow, String user_email, String d_diary, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("d_diary", d_diary);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_D_S_d",map);
        sqlSession.close();
        
        return li;
	}
	
	// (����) �ϱ� ��� ��������  - ���� [���� - �ۼ��� ���� (�⺻)]
	public List searchDiary_D_C(int startRow, int endRow, String user_email, String d_diary, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("d_diary", d_diary);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_D_C",map);
        sqlSession.close();
        
        return li;
	}
	
	// (����) �ϱ� ��� ��������  - ���� [���� - �ϱ� ��¥ ����]
	public List searchDiary_D_C_d(int startRow, int endRow, String user_email, String d_diary, String search) {
	    sqlSession= sqlSession();
        Map map = new HashMap();
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        map.put("user_email", user_email);
        map.put("d_diary", d_diary);
        map.put("search", search);
        
        List li = sqlSession.selectList(namespace + ".searchDiary_D_C_d",map);
        sqlSession.close();
        
        return li;
	}
	/* end. $ ���� $ */
	
	/* end. ======== !!!!!! �˻� ���� !!!!!! ======== */
	
	// �ϱ� ���� �� ���� ��������
	public DiaryDataBean getDiary(int num, String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		map.put("user_email", user_email);
		
		DiaryDataBean diary=sqlSession.selectOne(namespace + ".getDiary" ,map);
		sqlSession.commit();
		sqlSession.close();
		
		return diary;
	}
	
	// �ϱ� ���� ����
	public int updateDiary (DiaryDataBean diary) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateDiary", diary);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// �ϱ� ����
	public int deleteDiary (int num, String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		map.put("user_email", user_email);
		int chk = sqlSession.delete(namespace+".deleteDiary", map);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;	
	}
	//////////////////////////
}
