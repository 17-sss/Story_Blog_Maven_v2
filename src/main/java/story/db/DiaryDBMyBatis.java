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
	
	// 일기 쓰기
	public void insertDiary(DiaryDataBean diary) {
		sqlSession= sqlSession();
		int number = sqlSession.selectOne(namespace + ".getNextNumber",diary);
		
		diary.setNum(number);
	
		sqlSession.insert(namespace + ".insertDiary", diary);
		sqlSession.commit();
		sqlSession.close();
	}
	
	// 일기 수 (이메일)
	public int getDiaryCount (String user_email) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		x = sqlSession.selectOne(namespace+".getDiaryCount1", map);
		sqlSession.close();
		return x;
	}
	
	// 일기 수 (이메일 & 일기장)
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
	
	// 일기 목록 가져오기 (이메일) [정렬 - 작성일 기준 (기본)]
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
	
	// 일기 목록 가져오기 (이메일) [정렬 - 일기 날짜 기준]
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
	
	// 일기 목록 가져오기 (이메일 & 일기장) [정렬 - 작성일 기준 (기본)]
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

	// 일기 목록 가져오기 (이메일 & 일기장) [정렬 - 일기 날짜 기준]
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
	
	// 갤러리 - 사진 수 (이메일)
	public int getGalleryCount (String user_email) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		
		x = sqlSession.selectOne(namespace+".getGalleryCount1", map);
		sqlSession.close();
		return x;
	}
	
	// 갤러리 - 사진 수 (이메일, 날짜)
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
	
	// 갤러리 - 사진 가져오기 (이메일)
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
	
	// 갤러리 - 사진 가져오기 (이메일, 날짜)
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
	
	// 갤러리 - 날짜 가져오기 (이메일 기준) (갤러리 날짜 검색 전용)
	public List getGalleryDate (String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getGalleryDate" ,map);
		sqlSession.close();
		return li;
	}
	
	// 갤러리 - 날짜 가져오기2 (이메일 기준) (갤러리 날짜 검색 전용(검색한 날짜를 제외))
	public List getGalleryDate (String user_email, String date_opt) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("date_opt", date_opt);
		List li = sqlSession.selectList(namespace + ".getGalleryDate2" ,map);
		sqlSession.close();
		return li;
	}
	
	// 갤러리 - 최근 일기 n개 게시물 갯수 (메인 전용)
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
	
	// 갤러리 - 최근 일기 n개 게시물만큼 사진 가져오기 (메인 전용)
	public List getGalleryRecent (String user_email, int recent_num) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("recent_num", recent_num);
		List li = sqlSession.selectList(namespace + ".getGalleryRecent" ,map);
		sqlSession.close();
		return li;
	}
	
	// 일기장 목록 가져오기 (이메일 기준) (일기장 가져오기 전용)
	public List getDiarylist (String user_email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		List li = sqlSession.selectList(namespace + ".getDiarylist" ,map);
		sqlSession.close();
		return li;
	}
	
	// 일기장 목록 가져오기2 (이메일 기준) (일기장 수정 전용(수정할 일기의 일기장을 제외))
	public List getDiarylist (String user_email, String not_in_d_diary) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("user_email", user_email);
		map.put("not_in_d_diary", not_in_d_diary);
		List li = sqlSession.selectList(namespace + ".getDiarylist2" ,map);
		sqlSession.close();
		return li;
	}
	
	
	
	/* ======== !!!!!! 검색 관련 !!!!!! ======== */
	
	/* $ 전체 $ */
	
	/* ~ Count ~ */
	// (전체) 일기 수  - 제목 & 내용
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
	
	// (전체) 일기 수  - 제목
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
	
	// (전체) 일기 수  - 내용
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
	
	
	
	/* ~ 목록 가져오기 [전체] ~ */
	// (전체) 일기 목록 가져오기  - 제목 & 내용 [정렬 - 작성일 기준 (기본)]
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
	
	// (전체) 일기 목록 가져오기  - 제목 & 내용 [정렬 - 일기 날짜 기준]
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
	
	// (전체) 일기 목록 가져오기  - 제목 [정렬 - 작성일 기준 (기본)]
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
	
	// (전체) 일기 목록 가져오기  - 제목 [정렬 - 일기 날짜 기준]
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
	
	// (전체) 일기 목록 가져오기  - 내용 [정렬 - 작성일 기준 (기본)]
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
	
	// (전체) 일기 목록 가져오기  - 내용 [정렬 - 일기 날짜 기준]
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
	/* end. $ 전체 $ */
	
	
	
	/* $ 개별 $ */
	
	/* ~ Count2 ~ */
	// (개별) 일기 수  - 제목 & 내용
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
	
	// (개별) 일기 수  - 제목
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
	
	// (개별) 일기 수  - 내용
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
	

	/* ~ 목록 가져오기 [개별] ~ */
	// (개별) 일기 목록 가져오기  - 제목 & 내용  [정렬 - 작성일 기준 (기본)]
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
	
	// (개별) 일기 목록 가져오기  - 제목 & 내용  [정렬 - 일기 날짜 기준]
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
	
	// (개별) 일기 목록 가져오기  - 제목 [정렬 - 작성일 기준 (기본)]
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
	
	// (개별) 일기 목록 가져오기  - 제목 [정렬 - 일기 날짜 기준]
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
	
	// (개별) 일기 목록 가져오기  - 내용 [정렬 - 작성일 기준 (기본)]
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
	
	// (개별) 일기 목록 가져오기  - 내용 [정렬 - 일기 날짜 기준]
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
	/* end. $ 개별 $ */
	
	/* end. ======== !!!!!! 검색 관련 !!!!!! ======== */
	
	// 일기 수정 및 내용 가져오기
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
	
	// 일기 수정 전송
	public int updateDiary (DiaryDataBean diary) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateDiary", diary);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// 일기 삭제
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
