package story.Test;

public class Session_test {
	// ���� ���� 
	// 2018.07 ���� (Ver.1)
	/*if (session.getAttribute("s_email") == null) {
	mv.setViewName("index");
	} else {
		mv.setViewName("view/user/user_main");
	}*/
	
	// 2018.07.07 (Ver.2)
	/*if (session.getAttribute("s_email") == null) {
		// �α����� �ȵ��������.
		System.out.println("�α��� ����: "+ (session.getAttribute("s_email") == null));
		mv.setViewName("index");
	} else if (session.getAttribute("s_email").equals(email)) {
		// ����(���̵�)���� ������ �̸��� ���� ���� ���.
		System.out.println("���� �� = ������ �̸��� ��: " + session.getAttribute("s_email").equals(email));
		mv.setViewName("view/diary/diary_content");
	} else {
		// ����(���̵�)���� ������ �̸��� ���� �ٸ� ���.
		System.out.println("���� �� = ������ �̸��� ��: " + session.getAttribute("s_email").equals(email));
		mv.setViewName("index");
	}*/
}
