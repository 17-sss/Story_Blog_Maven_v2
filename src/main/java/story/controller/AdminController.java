package story.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import story.db.UserDataBean;
import story.db.UserDBMyBatis;

@Controller
@RequestMapping("/admin")
public class AdminController {
	UserDBMyBatis usPro = UserDBMyBatis.getInstance();
	String pageNum = "1";
	@ModelAttribute
	public void addAttributes(String pageNum) {
		if(pageNum !=null && pageNum != "") {
			this.pageNum = pageNum;
		}
	}
	
	// 관리자 페이지
	@RequestMapping("/admin_page")
	public ModelAndView admin_page(HttpServletRequest req, String pageNum, ModelAndView mv, String email, String p_level,
			String search, String opt) throws Exception {
		HttpSession session = req.getSession();
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
		if (p_level == null || p_level=="") {p_level = (String)session.getAttribute("s_p_level");}
		
	    int pageSize = 10;
	    
	    int currentPage = Integer.parseInt(pageNum);
	   
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	   
	    int count_user = 0; 
	    int number = 0;
	    
	    List u_list = null;
	    
	    // 유저 목록 불러오기
	    if (p_level.equals("3(Manager)")) {
		    count_user = usPro.getUserCount1(email);
			if (count_user > 0) {
				u_list = usPro.getUsers1(startRow, endRow, email);
				System.out.println("count_user: "+count_user);
			}
	    }
	    
	    if (p_level.equals("4(S-Manager)")) {
		    count_user = usPro.getUserCount(email);
			if (count_user > 0) {
				u_list = usPro.getUsers(startRow, endRow, email);
				System.out.println("count_user: "+count_user);
			}
	    }
	    // 유저 검색
	    if (search != null) {
	    	System.out.println("권한: "+p_level);
	    	
	    	if (p_level.equals("4(S-Manager)")) {
	    		if (opt.equals("EN")) {
		    		count_user = usPro.getUserCount_Search_EN(email, "%" + search + "%");
					if (count_user > 0) {
						u_list = usPro.getUsers_Search_EN(startRow, endRow, email, "%" + search + "%");
						System.out.println("[EN] Count_User: " + count_user);
					}
		    	}
		    	if (opt.equals("E")) {
		    		count_user = usPro.getUserCount_Search_E(email, "%" + search + "%");
					if (count_user > 0) {
						u_list = usPro.getUsers_Search_E(startRow, endRow, email, "%" + search + "%");
						System.out.println("[E] Count_User: " + count_user);
					}
		    	}    	
		    	if (opt.equals("N")) {
		    		count_user = usPro.getUserCount_Search_N(email, "%" + search + "%");
					if (count_user > 0) {
						u_list = usPro.getUsers_Search_N(startRow, endRow, email, "%" + search + "%");
						System.out.println("[N] Count_User: " + count_user);
					}
		    	}
	    	}
	    	
	    	if (p_level.equals("3(Manager)")) {
	    		if (opt.equals("EN")) {
		    		count_user = usPro.getUserCount_Search_EN1(email, "%" + search + "%");
					if (count_user > 0) {
						u_list = usPro.getUsers_Search_EN1(startRow, endRow, email, "%" + search + "%");
						System.out.println("[EN] Count_User: " + count_user);
					}
		    	}
	    		if (opt.equals("E")) {
		    		count_user = usPro.getUserCount_Search_E1(email, "%" + search + "%");
					if (count_user > 0) {
						u_list = usPro.getUsers_Search_E1(startRow, endRow, email, "%" + search + "%");
						System.out.println("[E] Count_User: " + count_user);
					}
		    	}    	
		    	if (opt.equals("N")) {
		    		count_user = usPro.getUserCount_Search_N1(email, "%" + search + "%");
					if (count_user > 0) {
						u_list = usPro.getUsers_Search_N1(startRow, endRow, email, "%" + search + "%");
						System.out.println("[N] Count_User: " + count_user);
					}
		    	}
	    	}
	    	
	    }
	    
	    //##################
		number = count_user - (currentPage - 1) * pageSize;

	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;    
	    int endPage = startPage + bottomLine - 1;
	    int pageCount = count_user / pageSize + (count_user % pageSize == 0 ? 0 : 1); 
	    if (endPage > pageCount) { endPage = pageCount; } 

	    mv.addObject("count_user", count_user);
	    mv.addObject("number", number);
	    mv.addObject("pageCount", pageCount);
	    mv.addObject("endPage", endPage);
	    
	    mv.addObject("u_list", u_list);
	   
	    mv.addObject("currentPage", currentPage);
	    mv.addObject("bottomLine", bottomLine);
	    mv.addObject("startPage", startPage);
	    mv.addObject("pageNum", pageNum);
	   
	    // 검색
	    mv.addObject("search", search);
	    mv.addObject("opt", opt);

		// 접속 제한
	    if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} 
	    else if (p_level.equals("1(User)") || p_level.equals("2(S-User)")) {
	    	mv.setViewName("index");
		}
	    else if (p_level.equals("3(Manager)") || p_level.equals("4(S-Manager)"))  {
			mv.setViewName("view/admin/admin_page");
		}
		
		return mv;
	}
	
	@RequestMapping("/admin_user_deletePro")
	public ModelAndView admin_user_deletePro(HttpServletRequest req, ModelAndView mv,
			@RequestParam Map<String, String> paramMap, String pageNum, String opt, String search) throws Exception {
		int check = 0;
		int check_diary =0;
		
		String[] arr_del_user = paramMap.get("email").toString().split(",");
		for (int i=0; i<arr_del_user.length; i++) {
			check = usPro.deleteUser(arr_del_user[i]);
			check_diary = usPro.deleteUser_diary(arr_del_user[i]);
			
			System.out.println("####이메일: "+arr_del_user[i]+"####\n"+"삭제여부: " + check+"\n다이어리 삭제여부: "+check_diary);
		}

		mv.addObject("pageNum", pageNum);
		mv.addObject("search", search);
		mv.addObject("opt", opt);
		
		mv.addObject("check", check);
		mv.addObject("check_diary", check_diary);
		mv.setViewName("view/admin/admin_user_deletePro");
		
		return mv;
	}
	
	@RequestMapping("/admin_userinfo")
	public ModelAndView admin_userinfo (HttpServletRequest req, ModelAndView mv, String p_level, String userN, UserDataBean user,
			String pageNum, String search, String opt) {
		HttpSession session = req.getSession();
		if (p_level == null || p_level=="") {p_level = (String)session.getAttribute("s_p_level");}

		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} 
	    else if (p_level.equals("1(User)") || p_level.equals("2(S-User)")) {
	    	mv.setViewName("index");
		}
	    else if (p_level.equals("3(Manager)") || p_level.equals("4(S-Manager)"))  {
			user = usPro.getUser_n(userN);
			mv.addObject("user", user); mv.addObject("pageNum", pageNum);
			mv.addObject("search", search); mv.addObject("opt", opt);
			mv.setViewName("view/admin/admin_userinfo");
		}
		
		return mv;
	}
	
	@RequestMapping("/admin_userinfoPro")
	public ModelAndView admin_userinfoPro (ModelAndView mv, int num, String fname, int fsize, String email, String p_level,
			MultipartHttpServletRequest req, String opt, String search, String pageNum, String sort_option) throws Exception {
		UserDataBean user = new UserDataBean();
		
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();

		System.out.println("[Manager] '"+email+"'를 변경 ###\n"+"[Manager] 확인 1) 유저 파일 업로드: "+filename+"\t # 기존 이미지: "+fname);
		
		user.setNum(num);
		//user.setEmail(email);
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));
		user.setSort_option(sort_option);
		user.setP_level(p_level);
		
		
		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/")+"userSave";
			System.out.println("[Manager] 확인 2) 업로드 경로: " +uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int)multi.getSize());
		} else {
			user.setFilename(fname);
			user.setFilesize(fsize);
		}
		
		// 프로필 사진 파일 타입 체크
		String filetype=".png";
		int filechk=0;
		if (filename!=null) {
			filetype= filename.substring(filename.lastIndexOf(".")+1);
			if (!(filetype.equalsIgnoreCase("jpg")||filetype.equalsIgnoreCase("jpeg")
					||filetype.equalsIgnoreCase("png")||filetype.equalsIgnoreCase("gif")||filename.equals(""))) {
				filechk=1;
				mv.addObject("filechk", filechk);
				mv.setViewName("view/admin/admin_userinfoPro");
			} 
		}
		
		if(filechk==0) {
			int chk = usPro.updateUser_m(user);
			
			System.out.println("[Manager] "+email+"님의 정보가 변경되었습니다" + "\t # 변경여부[chk]: "+chk+"\n");
			mv.addObject("filechk", filechk); mv.addObject("chk", chk);
			mv.addObject("pageNum", pageNum); mv.addObject("search", search); 
			mv.addObject("opt", opt);
			mv.addObject("num", num); // 유저고유번호 (페이지 되돌아갈때 쓰임)
			mv.setViewName("view/admin/admin_userinfoPro");
		}
		
		return mv;
	}
	
	//=============================================================================================================================
	// 관리자 유저수정
	// /admin/updateUserForm
	@RequestMapping("/updateUserForm")
	public String updateUserForm(String email, String pwd, String pageNum, Model model) throws Exception {
		/*String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");*/
		
		UserDataBean user = usPro.getUser(email, pwd);
		
		model.addAttribute("user", user);
		model.addAttribute("pageNum", pageNum);
		
		return "/admin/updateUserForm";
	}

	// 관리자 유저수정(Pro)
	// /admin/updateUserPro
	@RequestMapping("/updateUserPro")
	public String updateUserPro(Model model, MultipartHttpServletRequest req, String pageNum) throws Exception {
		//String pageNum = req.getParameter("pageNum");
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
	
		//ModelAndView mv = new ModelAndView();
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();
		System.out.println("유저 수정 이미지: "+filename);
		
		UserDataBean user = new UserDataBean();
		
		user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));
		user.setFilename(req.getParameter("filename"));
		user.setIp(req.getRemoteAddr());
		
		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/")+"userSave"; // 작대기 그어진 거 신경쓰지말기. 이클립스에서 쓰지않았음 좋겠다는 표시를 해주는 것 뿐.
			System.out.println("업로드 경로: " + uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int)multi.getSize());
		}/* else {
			user.setFilename("");
			user.setFilesize(0);
		}*/
			 
		
		System.out.println(user);
		int chk = usPro.updateUser(user);
		
		System.out.println("수정여부: " + chk);
		
		model.addAttribute("chk", chk);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("user", user);
		
		return "/admin/updateUserPro";
	}

	// 관리자 유저삭제(Pro)
    // /admin/deleteUserPro
	@RequestMapping(value = "deleteUserPro")
	public ModelAndView deleteUserPro(String email, String pwd,  HttpServletRequest req) throws Throwable {
		ModelAndView mv = new ModelAndView();
		
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		int check = usPro.deleteUser(email, pwd);
		
		System.out.println("삭제여부: " + check);
		
		mv.addObject("check", check);
		mv.addObject("pageNum",pageNum);
		mv.setViewName("/admin/deleteUserPro");
		
		/*UserDataBean user = new UserDataBean();

		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		String email = req.getParameter("email");
		String pwd = req.getParameter("pwd");

		user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));

		UserDBMyBatis dbPro = UserDBMyBatis.getInstance();

		int check = dbPro.deleteUser(email, pwd);

		System.out.println("삭제여부: " + check);

		req.setAttribute("pwd", pwd);
		req.setAttribute("email", email);
		req.setAttribute("check", check);*/

		return mv;
	}
}
