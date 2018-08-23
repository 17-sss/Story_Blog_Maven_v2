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
		user.setP_level("1");
		
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
	public int getUserCount() {
		int x = 0;
		sqlSession=sqlSession();
		x = sqlSession.selectOne(namespace+".getUserCount");
		sqlSession.close();
		return x;
	}

	// [어드민] 회원리스트 목록출력
	public List getUsers(int startRow, int endRow) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List li = sqlSession.selectList(namespace + ".getUsers",map);
		sqlSession.close();
		return li;
	}
	
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
	
}