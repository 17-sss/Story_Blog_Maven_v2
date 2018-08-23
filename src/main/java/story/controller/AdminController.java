package story.controller;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	// ������ ������
	@RequestMapping("/admin_page")
	public ModelAndView admin_page(HttpServletRequest req, String pageNum, ModelAndView mv, String email, String p_level) throws Exception {
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
	    
	    // ���� ��� �ҷ�����
	   count_user = usPro.getUserCount();
		if (count_user > 0) {
			u_list = usPro.getUsers(startRow, endRow);
			System.out.println("count_user: "+count_user);
		}
	    // �˻� �Խ��� �����ؼ� �����
	    
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
	   
	    // �˻�
	   /* mv.addObject("search", search);
	    mv.addObject("opt", opt);*/

		// ���� ����
		if (session.getAttribute("s_email") == null || !p_level.equals("3")) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/admin/admin_page");
		}
		
		return mv;
	}
	
	@RequestMapping("/admin_user_deletePro")
	public ModelAndView admin_user_deletePro(HttpServletRequest req, ModelAndView mv,
			@RequestParam Map<String, String> paramMap) throws Exception {
		int check = 0;
		int check_diary =0;
		
		String[] arrIdx = paramMap.get("email").toString().split(",");
		for (int i=0; i<arrIdx.length; i++) {
			check = usPro.deleteUser(arrIdx[i]);
			check_diary = usPro.deleteUser_diary(arrIdx[i]);
			
			System.out.println("####�̸���: "+arrIdx[i]+"####\n"+"��������: " + check+"\n���̾ ��������: "+check_diary);
		}

		
		mv.addObject("check", check);
		mv.addObject("check_diary", check_diary);
		mv.setViewName("view/admin/admin_user_deletePro");
		
		return mv;
	}
	
	//=============================================================================================================================
	// ������ ��������
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

	// ������ ��������(Pro)
	// /admin/updateUserPro
	@RequestMapping("/updateUserPro")
	public String updateUserPro(Model model, MultipartHttpServletRequest req, String pageNum) throws Exception {
		//String pageNum = req.getParameter("pageNum");
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
	
		//ModelAndView mv = new ModelAndView();
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();
		System.out.println("���� ���� �̹���: "+filename);
		
		UserDataBean user = new UserDataBean();
		
		user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));
		user.setFilename(req.getParameter("filename"));
		user.setIp(req.getRemoteAddr());
		
		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/")+"userSave"; // �۴�� �׾��� �� �Ű澲������. ��Ŭ�������� �����ʾ��� ���ڴٴ� ǥ�ø� ���ִ� �� ��.
			System.out.println("���ε� ���: " + uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int)multi.getSize());
		}/* else {
			user.setFilename("");
			user.setFilesize(0);
		}*/
			 
		
		System.out.println(user);
		int chk = usPro.updateUser(user);
		
		System.out.println("��������: " + chk);
		
		model.addAttribute("chk", chk);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("user", user);
		
		return "/admin/updateUserPro";
	}

	// ������ ��������(Pro)
    // /admin/deleteUserPro
	@RequestMapping(value = "deleteUserPro")
	public ModelAndView deleteUserPro(String email, String pwd,  HttpServletRequest req) throws Throwable {
		ModelAndView mv = new ModelAndView();
		
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		int check = usPro.deleteUser(email, pwd);
		
		System.out.println("��������: " + check);
		
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

		System.out.println("��������: " + check);

		req.setAttribute("pwd", pwd);
		req.setAttribute("email", email);
		req.setAttribute("check", check);*/

		return mv;
	}
}
