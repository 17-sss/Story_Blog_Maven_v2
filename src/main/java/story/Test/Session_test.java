package story.Test;

public class Session_test {
	// 접속 제한 
	// 2018.07 이전 (Ver.1)
	/*if (session.getAttribute("s_email") == null) {
	mv.setViewName("index");
	} else {
		mv.setViewName("view/user/user_main");
	}*/
	
	// 2018.07.07 (Ver.2)
	/*if (session.getAttribute("s_email") == null) {
		// 로그인이 안되있을경우.
		System.out.println("로그인 상태: "+ (session.getAttribute("s_email") == null));
		mv.setViewName("index");
	} else if (session.getAttribute("s_email").equals(email)) {
		// 세션(아이디)값과 가져온 이메일 값이 같을 경우.
		System.out.println("세션 값 = 가져온 이메일 값: " + session.getAttribute("s_email").equals(email));
		mv.setViewName("view/diary/diary_content");
	} else {
		// 세션(아이디)값과 가져온 이메일 값이 다를 경우.
		System.out.println("세션 값 = 가져온 이메일 값: " + session.getAttribute("s_email").equals(email));
		mv.setViewName("index");
	}*/
}
