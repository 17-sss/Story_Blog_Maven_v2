package story.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.MybatisConnector;

public class UserDBMyBatis extends MybatisConnector {
	private final String namespace = "user.mybatis";
	private static UserDBMyBatis instance = new UserDBMyBatis();
	private UserDBMyBatis() {}
	public static UserDBMyBatis getInstance() {
		return instance;
	}
	SqlSession sqlSession;
	
	// 회원가입 (추가) 메소드
	public void insertUser(UserDataBean user) {
		sqlSession= sqlSession();
		int number = sqlSession.selectOne(namespace + ".getNextUserNumber", user);
		//number = number+1;
		user.setNum(number);
		user.setP_level("1(User)");
		
		sqlSession.insert(namespace + ".insertUser" ,user);
		sqlSession.commit();
		sqlSession.close();
	}
		
	// 이메일 중복확인  - userlist.xml 리턴타입 꼭!! 확인하기! 여기선 map
	public boolean confirmEmail(String email) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		Map map2 = new HashMap();
		map.put("email", email);
		boolean chk=true;
		
		map2=sqlSession.selectOne(namespace+".confirmEmail",map);
		
		if (map2!=null) {
			chk=true;
		}else {
			chk=false;
		}
		sqlSession.close();
		
		return chk;
	}
	
	// 로그인시 유효성 체크 
	public int loginCheck(String email, String pwd) {
		sqlSession= sqlSession();
        Map map = new HashMap();		
        map.put("email", email);
		map.put("pwd", pwd);
		
		UserDataBean user = sqlSession.selectOne(namespace+".loginCheck", map);
		int x = -1;
		String dbPW = "";
		
		if (user!=null)  {
            dbPW = user.getPwd();
            if (dbPW.equals(pwd)) {
                x = 1; 
            } else {                 
                x = 0;
            }
        } else {
           x = -1; 
        }
         return x;
	}
	
	// 회원정보 가져오기 (이메일)
	public UserDataBean getUser(String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser1", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// 회원정보 가져오기 (이메일 & 비번)
	public UserDataBean getUser (String email, String pwd) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("pwd", pwd);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser2", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// 회원정보 수정
	public int updateUser (UserDataBean user) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateUser", user);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// 회원 탈퇴 (회원)
	public int deleteUser (String email, String pwd) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("pwd", pwd);
		int chk = sqlSession.delete(namespace+".deleteUser", map);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// 회원 탈퇴 (회원의 일기 삭제)
	public int deleteUser_diary (String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		int chk = sqlSession.delete(namespace+".deleteUser_diary", map);
		sqlSession.commit();
		sqlSession.close();
		return chk;
	}

	// [어드민] 회원 수 메소드
	// + 접속 계정 제외, 권한 S-Manager 제외
	public int getUserCount(String email) {
		int x = 0;
		sqlSession=sqlSession();
		x = sqlSession.selectOne(namespace+".getUserCount", email);

		sqlSession.close();
		return x;
	}
	// + 접속 계정 제외, 권한 S-Manager, Manager 제외
	public int getUserCount1(String email) {
		int x = 0;
		sqlSession=sqlSession();
		x = sqlSession.selectOne(namespace+".getUserCount1", email);

		sqlSession.close();
		return x;
	}


	// [어드민] 회원리스트 목록출력
	// + 접속 계정 제외, 권한 S-Manager 제외
	public List getUsers(int startRow, int endRow, String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		List li = sqlSession.selectList(namespace + ".getUsers",map);
		sqlSession.close();
		return li;
	}
	// + 접속 계정 제외, 권한 S-Manager, Manager 제외
	public List getUsers1(int startRow, int endRow, String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		List li = sqlSession.selectList(namespace + ".getUsers1",map);
		sqlSession.close();
		return li;
	}
	
	// @@@@@@@@@@@@@@@@ 회원 검색 @@@@@@@@@@@@@@@@
	// [어드민] 회원 검색 - 회원 수 
	// + 접속 계정 제외, 권한 S-Manager 제외 [4(S-Manager) 전용]
	// 이메일 + 이름
	public int getUserCount_Search_EN(String email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("search", search);
		
		x = sqlSession.selectOne(namespace+".getUserCount_Search_EN", map);
		sqlSession.close();
		return x;
	}
	// 이메일
	public int getUserCount_Search_E(String email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("search", search);
		
		x = sqlSession.selectOne(namespace+".getUserCount_Search_E", map);
		sqlSession.close();
		return x;
	}
	// 이름
	public int getUserCount_Search_N(String email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("search", search);
		
		x = sqlSession.selectOne(namespace+".getUserCount_Search_N", map);
		sqlSession.close();
		return x;
	}
	// end. + 접속 계정 제외, 권한 S-Manager 제외 [4(S-Manager) 전용]
	
	// + 접속 계정 제외, 권한 S-Manager, Manager 제외 [3(Manager) 전용]
	// 이메일 + 이름
	public int getUserCount_Search_EN1(String email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("search", search);
		
		x = sqlSession.selectOne(namespace+".getUserCount_Search_EN1", map);
		sqlSession.close();
		return x;
	}
	// 이메일
	public int getUserCount_Search_E1(String email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("search", search);
		
		x = sqlSession.selectOne(namespace+".getUserCount_Search_E1", map);
		sqlSession.close();
		return x;
	}
	// 이름
	public int getUserCount_Search_N1(String email, String search) {
		int x = 0;
		sqlSession=sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("search", search);
		
		x = sqlSession.selectOne(namespace+".getUserCount_Search_N1", map);
		sqlSession.close();
		return x;
	}
	// end. + 접속 계정 제외, 권한 S-Manager, Manager 제외 [3(Manager) 전용]
	// ### end. [어드민] 회원 검색 - 회원 수
	
	
	// [어드민] 회원 검색 - 목록출력
	// + 접속 계정 제외, 권한 S-Manager 제외 [4(S-Manager) 전용]
	// 이메일 + 이름
	public List getUsers_Search_EN(int startRow, int endRow, String email, String search) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("search", search);
		List li = sqlSession.selectList(namespace + ".getUsers_Search_EN",map);
		sqlSession.close();
		return li;
	}
	// 이메일
	public List getUsers_Search_E(int startRow, int endRow, String email, String search) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("search", search);
		List li = sqlSession.selectList(namespace + ".getUsers_Search_E",map);
		sqlSession.close();
		return li;
	}
	// 이름
	public List getUsers_Search_N(int startRow, int endRow, String email, String search) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("search", search);
		List li = sqlSession.selectList(namespace + ".getUsers_Search_N",map);
		sqlSession.close();
		return li;
	}
	// end. + 접속 계정 제외, 권한 S-Manager 제외 [4(S-Manager) 전용]
	
	// + 접속 계정 제외, 권한 S-Manager, Manager 제외 [3(Manager) 전용]
	// 이메일 + 이름
	public List getUsers_Search_EN1(int startRow, int endRow, String email, String search) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("search", search);
		List li = sqlSession.selectList(namespace + ".getUsers_Search_EN1",map);
		sqlSession.close();
		return li;
	}
	// 이메일
	public List getUsers_Search_E1(int startRow, int endRow, String email, String search) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("search", search);
		List li = sqlSession.selectList(namespace + ".getUsers_Search_E1",map);
		sqlSession.close();
		return li;
	}
	// 이름
	public List getUsers_Search_N1(int startRow, int endRow, String email, String search) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("search", search);
		List li = sqlSession.selectList(namespace + ".getUsers_Search_N1",map);
		sqlSession.close();
		return li;
	}
	// end. + 접속 계정 제외, 권한 S-Manager, Manager 제외  [3(Manager) 전용]
	// ### end. [어드민] 회원 검색 - 목록출력
	// @@@@@@@@@@@@@@@@ end. 회원 검색 @@@@@@@@@@@@@@@@
	
	// [어드민] 회원 삭제 
	public int deleteUser (String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		int chk = sqlSession.delete(namespace+".deleteUser_admin", map);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// [어드민] 회원 정보 가져오기 (유저 고유번호)
	public UserDataBean getUser_n(String num) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser_n", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// [어드민] 회원 정보 수정 (권한 수정하기 위해 필요)
	public int updateUser_m (UserDataBean user) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateUser_m", user);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
}