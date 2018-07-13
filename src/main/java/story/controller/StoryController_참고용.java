package story.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import story.db.DiaryDBMyBatis;
import story.db.DiaryDataBean;
import story.db.UserDBMyBatis;
import story.db.UserDataBean;



@Controller
@RequestMapping("/story")
public class StoryController_참고용 {
	UserDBMyBatis usPro = UserDBMyBatis.getInstance();
	DiaryDBMyBatis dbPro = DiaryDBMyBatis.getInstance();

	
	@RequestMapping("/index")
	public String index(HttpServletRequest req) {
		System.out.println("기본 경로 테스트: "+req.getContextPath());
		
		return "index"; 
	}
	
	// 유저 - 회원가입
	@RequestMapping("/accountForm") //url 매핑
	public ModelAndView accountForm(ModelAndView mv) {
		mv.setViewName("accountForm");  // jsp파일
		return mv; 
	} 
	
	// 유저 - 회원가입 전송
	// 이상하게 매게변수에 user 안막힘.
	@RequestMapping("/accountPro")
	public String accountPro(MultipartHttpServletRequest req, Model model, String email)  throws Throwable { 
		UserDataBean user = new UserDataBean();
		
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();
		System.out.println("유저 파일 업로드: "+filename);
		
		user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setBirth(req.getParameter("birth"));
		user.setTel(req.getParameter("tel"));
		user.setIp(req.getRemoteAddr());
		
		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/")+"userSave";
			System.out.println("업로드 경로: " +uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int)multi.getSize());
		} else {
			user.setFilename("");
			user.setFilesize(0);
		}
		
		
		// 프로필 사진 파일 타입 체크
		String filetype=".png";
		int filechk=0;
		if (filename!=null) {
			filetype= filename.substring(filename.lastIndexOf(".")+1);
			if (!(filetype.equalsIgnoreCase("jpg")||filetype.equalsIgnoreCase("jpeg")
					||filetype.equalsIgnoreCase("png")||filetype.equalsIgnoreCase("gif")||filename.equals(""))) {
				filechk=1;
				model.addAttribute("filechk", filechk);
				return "typechk";
			} 
		}
		// 이메일 중복확인 (이중확인)
		boolean result = false;
		result = usPro.confirmEmail(email);
		
		// 최종 확인
		if(!result && filechk==0) {
			usPro.insertUser(user);
			model.addAttribute("filechk", filechk);
			model.addAttribute("result", result);
			return "typechk";
		} else {
			model.addAttribute("result", result);
			return "typechk";
		}
	} 
	
	// 유저 - 이메일 확인 팝업
	@RequestMapping("/confirmEmail")
	public String confirmEmail (String email, Model model)  throws Throwable { 
		boolean result = usPro.confirmEmail(email);
		model.addAttribute("result", result);
		model.addAttribute("email", email);
		
		return  "confirmEmail"; 
	}
	
	// 유저 - 로그인 
	@RequestMapping("/LoginPro")
	public ModelAndView LoginPro(String email, String pwd, ModelAndView mv, UserDataBean user, HttpServletRequest req)  throws Throwable { 
		HttpSession session = req.getSession();
        System.out.println("LoginPro=============");
     	
        // DB에서 아이디, 비밀번호 확인
        System.out.println("입력 email: " + email +"\n입력 pwd: "+ pwd);
        int check = usPro.loginCheck(email, pwd);
        
       
        // URL 및 로그인관련 전달 메시지
        String msg = "";
        
        // 로그인 성공
        if(check == 1) {
            // 세션에 현재 아이디 세팅
        	session.setAttribute("sessionID", email);
        	// 유저 정보 가져와서 세션에 뿌려주기.
        	user=usPro.getUser(email);
            session.setAttribute("name", user.getName());
            session.setAttribute("filename", user.getFilename());
			msg = "view/user_main";
			System.out.println("loginPro 진입완료");
			mv.setViewName(msg);
        }
        // 비밀번호가 틀릴경우
        else if(check == 0) { 
        	mv.addObject("check", check);
        	mv.setViewName("Logincheck");
        }
        // 아이디가 틀릴경우
        else {
        	mv.addObject("check", check);
        	mv.setViewName("Logincheck");
        }
        return mv;
	}
	
	// 유저 - 로그아웃
	@RequestMapping("/LogoutPro")
	public ModelAndView LogoutPro(HttpServletRequest req, ModelAndView mv)  throws Throwable {
	    HttpSession session = req.getSession();
	    session.invalidate(); // 모든세션정보 삭제
	    mv.setViewName("index");
	    
		return mv;
	}
	
	// 유저 - 메인
	@RequestMapping("/user_main")
	public String user_main (String diaryid, String subject, String pageNum, HttpServletRequest req, Model model)  throws Throwable {
		HttpSession session = req.getSession();
		
		if (diaryid==null) diaryid = "Main"; 
		if (subject==null) subject = "하루의 끝";
		if (pageNum == null || pageNum =="") {pageNum = "1";}
		
		int pageSize= 5;
		
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = 0;
		int number = 0;
		List diaryList = null;
		
		count = dbPro.getDiaryCount(diaryid, (String)session.getAttribute("sessionID"));
		//게시판에 있는 글 수 count
		if (count > 0) {
			diaryList = dbPro.getDiaries(startRow, endRow, (String)session.getAttribute("sessionID"), diaryid);
		}
		number = count - (currentPage - 1) * pageSize;
		
		System.out.println("일기장 수: "+count);
		
		int bottomLine = 3; 
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine; //곱셈, 나눗셈먼저.
		int endPage = startPage + bottomLine -1;
		
		if (endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("subject", subject);
		model.addAttribute("diaryid", diaryid);
		model.addAttribute("count", count);
		model.addAttribute("diaryList", diaryList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("number", number);
		model.addAttribute("endPage", endPage);
		
		return "view/user_main";
	}
	
	// 유저 - 갤러리
	@RequestMapping("/user_gallery")
	public String user_gallery (String diaryid, String subject, String pageNum, Model model, HttpServletRequest req)  throws Throwable {
		HttpSession session = req.getSession();
		
		/*String diaryid = req.getParameter("diaryid");
		String subject = req.getParameter("subject");
		String pageNum = req.getParameter("pageNum");*/
		
		if (diaryid==null) diaryid = "Main"; 
		if (subject==null) subject = "하루의 끝";
		if (pageNum == null || pageNum =="") {pageNum = "1";}
		int pageSize= 9;
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = 0;
		int countT = 0;
		int number = 0;
		List diaryList = null;
		count = dbPro.getDiaryCount(diaryid, (String)session.getAttribute("sessionID"));
		countT = dbPro.getImgDiaryCountTotal(diaryid, (String)session.getAttribute("sessionID"));
		//게시판에 있는 글 수 count
		if (count > 0) {
			diaryList = dbPro.getDiaries(startRow, endRow, (String)session.getAttribute("sessionID"), diaryid);
		}
		number = count - (currentPage - 1) * pageSize;
		
		System.out.println(count+":"+diaryList);
		
		int bottomLine = 3; 
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine; //곱셈, 나눗셈먼저.
		int endPage = startPage + bottomLine -1;
		
		if (endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("subject", subject);
		model.addAttribute("diaryid", diaryid);
		model.addAttribute("count", count);
		model.addAttribute("countT", countT);
		model.addAttribute("diaryList", diaryList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("number", number);
		model.addAttribute("endPage", endPage);
	
		return "view/user_gallery";
	}
	
	// 유저 - 타임라인
	@RequestMapping("/user_timeline")
	public String user_timeline (String diaryid, String subject, String pageNum, HttpServletRequest req, Model model)  throws Throwable {
		HttpSession session = req.getSession();
		
		/*String diaryid = req.getParameter("diaryid");
		String subject = req.getParameter("subject");
		String pageNum = req.getParameter("pageNum");*/
		
		if (diaryid==null) diaryid = "Main"; 
		if (subject==null) subject = "하루의 끝";
		if (pageNum == null || pageNum =="") {pageNum = "1";}
		
		
		int pageSize= 5;
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = 0;
		int number = 0;
		List diaryList = null;
		count = dbPro.getDiaryCount(diaryid, (String)session.getAttribute("sessionID"));
		//게시판에 있는 글 수 count
		if (count > 0) {
			diaryList = dbPro.getDiaries(startRow, endRow, (String)session.getAttribute("sessionID"), diaryid);
		}
		number = count - (currentPage - 1) * pageSize;
		
		System.out.println(count+":"+diaryList);
		
		int bottomLine = 3; 
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine; //곱셈, 나눗셈먼저.
		int endPage = startPage + bottomLine -1;
		
		if (endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("subject", subject);
		model.addAttribute("diaryid", diaryid);
		model.addAttribute("count", count);
		model.addAttribute("diaryList", diaryList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("bottomLine", bottomLine);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("number", number);
		model.addAttribute("endPage", endPage);
		
		return "view/user_timeline";
	}
	
	// 유저 - 일기 수정 폼
	@RequestMapping("/user_updateDForm")
	public String user_updateDForm(String diaryid, String pageNum, HttpServletRequest req, Model model)  throws Throwable { 
		HttpSession session = req.getSession();
		
		/*String diaryid = req.getParameter("diaryid");
		String pageNum = req.getParameter("pageNum");*/
		
		if (diaryid==null) diaryid="Main";
		if (pageNum == null || pageNum == "") { pageNum = "1"; }
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		try {
			DiaryDataBean diary = dbPro.getDiary(num, (String)session.getAttribute("sessionID"), diaryid);
			
			model.addAttribute("diaryid", diaryid);
			model.addAttribute("pageNum", pageNum); 
			model.addAttribute("diary", diary); 
		} catch (Exception e) {}
		
		return "view/user_updateDForm"; 
	}
	
	// 일기 수정 전송 - 파일 업로드 
	@RequestMapping("/user_updateDPro")
	public String user_updateDPro(String pageNum, String diaryid, Model model, MultipartHttpServletRequest req)  throws Throwable {
		DiaryDataBean diary = new DiaryDataBean();
		
		/*String pageNum = req.getParameter("pageNum");
		String diaryid = req.getParameter("diaryid");*/
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (diaryid==null) diaryid = "Main";
		
		int num = Integer.parseInt(req.getParameter("num"));
	
		//ModelAndView mv = new ModelAndView();
		MultipartFile multi0 = req.getFile("filename0");
		String filename0 = multi0.getOriginalFilename();
		MultipartFile multi1 = req.getFile("filename1");
		String filename1 = multi1.getOriginalFilename();
		MultipartFile multi2 = req.getFile("filename2");
		String filename2 = multi2.getOriginalFilename();
		MultipartFile multi3 = req.getFile("filename3");
		String filename3 = multi3.getOriginalFilename();
		MultipartFile multi4 = req.getFile("filename4");
		String filename4 = multi4.getOriginalFilename();
		System.out.println("일기 수정 이미지1: "+filename0+"\n" + "일기 수정 이미지2: "+filename1+"\n" + "일기 수정 이미지3: "+filename2+"\n"
				+"일기 수정 이미지4: "+filename3+"\n" + "일기 수정 이미지5: "+filename4+"\n");
		
		diary.setNum(num);
		diary.setEmail(req.getParameter("email"));
		diary.setSubject(req.getParameter("subject"));
		diary.setContent(req.getParameter("content"));
		diary.setDiaryid(req.getParameter("diaryid"));
		diary.setFilename0(req.getParameter("filename0"));
		diary.setFilename1(req.getParameter("filename1"));
		diary.setFilename2(req.getParameter("filename2"));
		diary.setFilename3(req.getParameter("filename3"));
		diary.setFilename4(req.getParameter("filename4"));
		
		if (filename0 != null && !filename0.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave"; // 작대기 그어진 거 신경쓰지말기. 이클립스에서 쓰지않았음 좋겠다는 표시를 해주는 것 뿐.
			System.out.println("업로드 경로: " + uploadPath);
			FileCopyUtils.copy(multi0.getInputStream(), new FileOutputStream(uploadPath+"/"+multi0.getOriginalFilename()));
			diary.setFilename0(filename0);
			diary.setFilesize0((int)multi0.getSize());
		}
		if (filename1 != null && !filename1.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi1.getInputStream(), new FileOutputStream(uploadPath+"/"+multi1.getOriginalFilename()));
			diary.setFilename1(filename1);
			diary.setFilesize1((int)multi1.getSize());
		}
		if (filename2 != null && !filename2.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi2.getInputStream(), new FileOutputStream(uploadPath+"/"+multi2.getOriginalFilename()));
			diary.setFilename2(filename2);
			diary.setFilesize2((int)multi2.getSize());
		}
		if (filename3 != null && !filename3.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi3.getInputStream(), new FileOutputStream(uploadPath+"/"+multi3.getOriginalFilename()));
			diary.setFilename3(filename3);
			diary.setFilesize3((int)multi3.getSize());
		}
		if (filename4 != null && !filename4.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi4.getInputStream(), new FileOutputStream(uploadPath+"/"+multi4.getOriginalFilename()));
			diary.setFilename4(filename4);
			diary.setFilesize4((int)multi4.getSize());
		} else {}
		/* else {
			user.setFilename("");
			user.setFilesize(0);
		}*/
		
		int chk = dbPro.updateDiary(diary);
		
		model.addAttribute("chk", chk);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("diaryid", diaryid);
		
		System.out.println("수정여부: " + chk);
		
		System.out.println(diary);
		
		return "view/user_updateDPro";
	}
	
	// 유저 - 일기 삭제 전송
	@RequestMapping(value = "user_deleteDPro")
	public ModelAndView user_deleteDPro(String pageNum, String diaryid, HttpServletRequest req)  throws Throwable { 
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = req.getSession();
		
		/*String pageNum = req.getParameter("pageNum");
		String diaryid = req.getParameter("diaryid");*/
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (diaryid==null) diaryid = "Main";
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		
		int check = dbPro.deleteDiary(num, (String)session.getAttribute("sessionID"), diaryid);
		
		System.out.println("삭제여부: " + check);
		
		mv.addObject("check", check);
		mv.addObject("pageNum", pageNum);
		mv.setViewName("view/user_deleteDPro");

		return mv; 
	}
	
	// 유저 - 일기 쓰기 폼 
	@RequestMapping("/user_write")
	public String user_write(String diaryid, Model model, HttpServletRequest req)  throws Throwable { 
		String subject = req.getParameter("subject"); // 매게변수에 넣으면 계속 쉼표붙음.. 왜지..
		//String diaryid = req.getParameter("diaryid");
		int num=0;
		
		if (req.getParameter("num")!=null) {num = Integer.parseInt(req.getParameter("num"));}
		if (diaryid==null) diaryid = "Main";
		if (subject==null) subject = "제목없음";
		
		
		System.out.println("제목:"+subject);

		model.addAttribute("diaryid", diaryid);
		model.addAttribute("subject", subject);
		model.addAttribute("num", num);
		
		return  "view/user_write"; 
	}
	
	// 유저 - 일기 쓰기 폼 전송 (사진 다수)
	@RequestMapping("/user_writePro")
	public String user_writePro(int num, String pageNum, String diaryid, MultipartHttpServletRequest req, Model model)  throws Throwable {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
		
		DiaryDataBean diary = new DiaryDataBean();
		
		//int num = Integer.parseInt(req.getParameter("num"));
		
		/*String pageNum = req.getParameter("pageNum");
		String diaryid = req.getParameter("diaryid");*/
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (diaryid==null) diaryid = "Main";
	
		//ModelAndView mv = new ModelAndView();
		MultipartFile multi0 = req.getFile("filename0");
		String filename0 = multi0.getOriginalFilename();
		MultipartFile multi1 = req.getFile("filename1");
		String filename1 = multi1.getOriginalFilename();
		MultipartFile multi2 = req.getFile("filename2");
		String filename2 = multi2.getOriginalFilename();
		MultipartFile multi3 = req.getFile("filename3");
		String filename3 = multi3.getOriginalFilename();
		MultipartFile multi4 = req.getFile("filename4");
		String filename4 = multi4.getOriginalFilename();
		System.out.println("일기 이미지1: "+filename0+"\n" + "일기 이미지2: "+filename1+"\n" + "일기 이미지3: "+filename2+"\n"
				+"일기 이미지4: "+filename3+"\n" + "일기 이미지5: "+filename4+"\n");
		
		diary.setNum(Integer.parseInt(req.getParameter("num")));
		diary.setEmail((String)session.getAttribute("sessionID"));
		diary.setSubject(req.getParameter("subject"));
		diary.setContent(req.getParameter("content"));
		diary.setDiaryid(req.getParameter("diaryid"));
		diary.setIp(req.getRemoteAddr());
		/*diary.setFilename0(req.getParameter("filename0"));
		diary.setFilename1(req.getParameter("filename1"));
		diary.setFilename2(req.getParameter("filename2"));
		diary.setFilename3(req.getParameter("filename3"));
		diary.setFilename4(req.getParameter("filename4"));*/
		
		if (filename0 != null && !filename0.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave"; // 작대기 그어진 거 신경쓰지말기. 이클립스에서 쓰지않았음 좋겠다는 표시를 해주는 것 뿐.
			System.out.println("업로드 경로: " + uploadPath);
			FileCopyUtils.copy(multi0.getInputStream(), new FileOutputStream(uploadPath+"/"+multi0.getOriginalFilename()));
			diary.setFilename0(filename0);
			diary.setFilesize0((int)multi0.getSize());
		}
		if (filename1 != null && !filename1.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi1.getInputStream(), new FileOutputStream(uploadPath+"/"+multi1.getOriginalFilename()));
			diary.setFilename1(filename1);
			diary.setFilesize1((int)multi1.getSize());
		}
		if (filename2 != null && !filename2.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi2.getInputStream(), new FileOutputStream(uploadPath+"/"+multi2.getOriginalFilename()));
			diary.setFilename2(filename2);
			diary.setFilesize2((int)multi2.getSize());
		}
		if (filename3 != null && !filename3.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi3.getInputStream(), new FileOutputStream(uploadPath+"/"+multi3.getOriginalFilename()));
			diary.setFilename3(filename3);
			diary.setFilesize3((int)multi3.getSize());
		}
		if (filename4 != null && !filename4.equals("")) {
			String uploadPath = req.getRealPath("/")+"fileSave";
			FileCopyUtils.copy(multi4.getInputStream(), new FileOutputStream(uploadPath+"/"+multi4.getOriginalFilename()));
			diary.setFilename4(filename4);
			diary.setFilesize4((int)multi4.getSize());
		} else {}
		/* else {
			user.setFilename("");
			user.setFilesize(0);
		}*/
		dbPro.insertDiary(diary);
		
		model.addAttribute("diaryid", diaryid);
		model.addAttribute("pageNum", pageNum);
		/*mv.addObject("pageNum", pageNum);
		mv.setViewName("view/user_main?pageNum="+pageNum+"&diaryid="+diaryid);
		mv.setViewName("view/user_main?pageNum="+pageNum);*/
		
		System.out.println("====writePro====\n"+diary+"\n==============");
		
		return "redirect:user_main";
	}
	
	// 유저 - 콘텐츠 (갤러리에서 이동)
	@RequestMapping("/user_content")
	public String user_content(String diaryid, String pageNum, HttpServletRequest req, Model model)  throws Throwable {
		HttpSession session = req.getSession();
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		//String diaryid = req.getParameter("diaryid");
		//String pageNum = req.getParameter("pageNum");
		if (diaryid==null) diaryid = "Main"; 
		if (pageNum == null || pageNum =="") {pageNum = "1";}
		
		
		try {
			DiaryDataBean diary = dbPro.getDiary(num, (String)session.getAttribute("sessionID"), diaryid);
			
			model.addAttribute("diary", diary);
			model.addAttribute("pageNum", pageNum);
			
			System.out.println("유저 콘텐츠: "+diary);
			
		} catch (Exception e) {e.printStackTrace();}
		
		return "view/user_content";
	}
	
	// 유저 - 마이페이지 
	@RequestMapping("/user_set")
	public String user_set(HttpServletRequest req, Model model)  throws Throwable { 
		HttpSession session = req.getSession();
		
		try {
			UserDataBean user = usPro.getUser((String)session.getAttribute("sessionID"));
			
			model.addAttribute("user", user); 
		} catch (Exception e) {}
		return "view/user_set"; 
	}
	
	@RequestMapping("/user_updateUPro")
	public String user_updateUPro(MultipartHttpServletRequest req, Model model)  throws Throwable {
		// ModelAndView mv = new ModelAndView();
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();
		System.out.println("[마이페이지] 유저 수정 이미지: " + filename);

		UserDataBean user = new UserDataBean();

		user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));
		user.setFilename(req.getParameter("filename"));
		user.setIp(req.getRemoteAddr());

		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/") + "userSave"; // 작대기 그어진 거 신경쓰지말기. 이클립스에서 쓰지않았음 좋겠다는 표시를 해주는 것 뿐.
			System.out.println("업로드 경로: " + uploadPath);
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int) multi.getSize());
		} /*
			 * else { user.setFilename(""); user.setFilesize(0); }
			 */

		System.out.println(user);
		int chk = usPro.updateUser(user);

		System.out.println("수정여부: " + chk);

		model.addAttribute("chk", chk);
		model.addAttribute("user", user);

		return "view/user_updateUPro";
	}
	
	@RequestMapping(value = "user_deleteUPro")
	public ModelAndView user_deleteUPro(String email, String pwd, HttpServletRequest req)  throws Throwable {
		UserDataBean user = new UserDataBean();
		ModelAndView mv = new ModelAndView();
		/*String pageNum = req.getParameter("pageNum");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");*/
		//if (pageNum == null || pageNum == "") {pageNum = "1";}
		
		
		/*user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));*/
		
		int check = usPro.deleteUser(email, pwd);
		
		System.out.println("삭제여부: " + check);
		
		mv.addObject("check", check);
		mv.setViewName("view/user_deleteUPro");
	
		/*req.setAttribute("pwd", pwd);
		req.setAttribute("email", email);
		req.setAttribute("check", check);*/
	
		return mv;
	}

	// end. 유저 - 마이페이지 ============================= 
	
	@RequestMapping("/user_search")
	public ModelAndView user_search(String email, String search, ModelAndView mv, String pageNum) throws Throwable {
		int pageSize = 10;
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List diarySearch = null;
		
		count = dbPro.getSearchDiaryCount(email, "%"+search+"%");
		if (count > 0) {
			diarySearch = dbPro.searchDiary(email, "%"+search+"%", startRow, endRow);
		}
		number = count - (currentPage - 1) * pageSize;

		int bottomLine = 3;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;

		if (endPage > pageCount)
			endPage = pageCount;
		
		mv.addObject("email", email);
		mv.addObject("search", search);
		mv.addObject("count", count);
		mv.addObject("diarySearch", diarySearch);
		mv.addObject("currentPage", currentPage);
		mv.addObject("startPage", startPage);
		mv.addObject("bottomLine", bottomLine);
		mv.addObject("pageCount", pageCount);
		mv.addObject("endPage", endPage);
		mv.addObject("number", number);
		
		System.out.println("테스트중\n이메일: "+email+"\t검색어: "+search);
		
		mv.setViewName("view/user_search");
		
		return mv;
	}

// {} class
}
