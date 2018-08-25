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
	
	// ȸ������ (�߰�) �޼ҵ�
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
		
	// �̸��� �ߺ�Ȯ��  - userlist.xml ����Ÿ�� ��!! Ȯ���ϱ�! ���⼱ map
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
	
	// �α��ν� ��ȿ�� üũ 
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
	
	// ȸ������ �������� (�̸���)
	public UserDataBean getUser(String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser1", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// ȸ������ �������� (�̸��� & ���)
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
	
	// ȸ������ ����
	public int updateUser (UserDataBean user) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateUser", user);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// ȸ�� Ż�� (ȸ��)
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
	
	// ȸ�� Ż�� (ȸ���� �ϱ� ����)
	public int deleteUser_diary (String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		int chk = sqlSession.delete(namespace+".deleteUser_diary", map);
		sqlSession.commit();
		sqlSession.close();
		return chk;
	}

	// [����] ȸ�� �� �޼ҵ�
	// + ���� ���� ����, ���� S-Manager ����
	public int getUserCount(String email) {
		int x = 0;
		sqlSession=sqlSession();
		x = sqlSession.selectOne(namespace+".getUserCount", email);

		sqlSession.close();
		return x;
	}
	// + ���� ���� ����, ���� S-Manager, Manager ����
	public int getUserCount1(String email) {
		int x = 0;
		sqlSession=sqlSession();
		x = sqlSession.selectOne(namespace+".getUserCount1", email);

		sqlSession.close();
		return x;
	}


	// [����] ȸ������Ʈ ������
	// + ���� ���� ����, ���� S-Manager ����
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
	// + ���� ���� ����, ���� S-Manager, Manager ����
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
	
	// @@@@@@@@@@@@@@@@ ȸ�� �˻� @@@@@@@@@@@@@@@@
	// [����] ȸ�� �˻� - ȸ�� �� 
	// + ���� ���� ����, ���� S-Manager ���� [4(S-Manager) ����]
	// �̸��� + �̸�
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
	// �̸���
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
	// �̸�
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
	// end. + ���� ���� ����, ���� S-Manager ���� [4(S-Manager) ����]
	
	// + ���� ���� ����, ���� S-Manager, Manager ���� [3(Manager) ����]
	// �̸��� + �̸�
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
	// �̸���
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
	// �̸�
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
	// end. + ���� ���� ����, ���� S-Manager, Manager ���� [3(Manager) ����]
	// ### end. [����] ȸ�� �˻� - ȸ�� ��
	
	
	// [����] ȸ�� �˻� - ������
	// + ���� ���� ����, ���� S-Manager ���� [4(S-Manager) ����]
	// �̸��� + �̸�
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
	// �̸���
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
	// �̸�
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
	// end. + ���� ���� ����, ���� S-Manager ���� [4(S-Manager) ����]
	
	// + ���� ���� ����, ���� S-Manager, Manager ���� [3(Manager) ����]
	// �̸��� + �̸�
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
	// �̸���
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
	// �̸�
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
	// end. + ���� ���� ����, ���� S-Manager, Manager ����  [3(Manager) ����]
	// ### end. [����] ȸ�� �˻� - ������
	// @@@@@@@@@@@@@@@@ end. ȸ�� �˻� @@@@@@@@@@@@@@@@
	
	// [����] ȸ�� ���� 
	public int deleteUser (String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		int chk = sqlSession.delete(namespace+".deleteUser_admin", map);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// [����] ȸ�� ���� �������� (���� ������ȣ)
	public UserDataBean getUser_n(String num) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("num", num);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser_n", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// [����] ȸ�� ���� ���� (���� �����ϱ� ���� �ʿ�)
	public int updateUser_m (UserDataBean user) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateUser_m", user);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
}