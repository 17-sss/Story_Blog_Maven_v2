package story.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ModelAndView admin_page(HttpServletRequest req, String pageNum, ModelAndView mv, String email, String p_level) throws Exception {
		HttpSession session = req.getSession();
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
		if (p_level == null || p_level=="") {p_level = (String)session.getAttribute("s_p_level");}
		
		System.out.println("권한레벨: "+p_level);
	    int pageSize = 10;
	    
	    int currentPage = Integer.parseInt(pageNum);
	   
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	   
	    int count = 0; 
	    int number = 0;
	    
	    // 유저 목록 불러오기
	   /* count = diPro.getDiaryCount(email);
		if (count > 0 && sort_opt.equals("") || sort_opt.equals("cd")) {
			d_list = diPro.getDiaries(startRow, endRow, email);
			System.out.println("############\n"+"Count (전체, 정렬 - 작성일): " + count + "\n############");
		}*/
	    // 검색 게시판 참고해서 만들기
	    
	    //##################
		number = count - (currentPage - 1) * pageSize;

	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;    
	    int endPage = startPage + bottomLine - 1;
	    int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); 
	    if (endPage > pageCount) { endPage = pageCount; } 

	    mv.addObject("count", count);
	    mv.addObject("number", number);
	    mv.addObject("pageCount", pageCount);
	    mv.addObject("endPage", endPage);
	   
	    mv.addObject("currentPage", currentPage);
	    mv.addObject("bottomLine", bottomLine);
	    mv.addObject("startPage", startPage);
	    mv.addObject("pageNum", pageNum);
	   
	    // 검색
	   /* mv.addObject("search", search);
	    mv.addObject("opt", opt);*/

		// 접속 제한
		if (session.getAttribute("s_email") == null || !p_level.equals("3")) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/admin/admin_page");
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
