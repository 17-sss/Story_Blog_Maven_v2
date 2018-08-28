package story.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import story.db.DiaryDBMyBatis;
import story.db.UserDBMyBatis;
import story.db.UserDataBean;

@Controller
@RequestMapping("/user")
public class UserController {
	UserDBMyBatis usPro = UserDBMyBatis.getInstance();
	DiaryDBMyBatis diPro = DiaryDBMyBatis.getInstance();
	
	// index
	@RequestMapping("/index")
	public String index(HttpServletRequest req) {
		System.out.println("�⺻ ��� �׽�Ʈ: "+req.getContextPath());
		
		return "index"; 
	}
	
	// ȸ�� ���� ��
	@RequestMapping("/accountForm") //url ����
	public ModelAndView accountForm(ModelAndView mv) {
		mv.setViewName("accountForm");  // jsp����
		return mv; 
	}
	
	// ȸ�� ���� ���
	@RequestMapping("/accountPro")
	public ModelAndView accountPro(MultipartHttpServletRequest req, ModelAndView mv, String email) throws Exception {
		UserDataBean user = new UserDataBean();
		
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();
		System.out.println("[����] Ȯ�� 1) ���� ���� ���ε�: "+filename);
		
		user.setEmail(req.getParameter("email"));
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));
		user.setSort_option(null);
		user.setIp(req.getRemoteAddr());
		
		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/")+"userSave";
			System.out.println("[����] Ȯ�� 2) ���ε� ���: " +uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int)multi.getSize());
		} else {
			user.setFilename("");
			user.setFilesize(0);
		}
		
		// ������ ���� ���� Ÿ�� üũ
		String filetype=".png";
		int filechk=0;
		if (filename!=null) {
			filetype= filename.substring(filename.lastIndexOf(".")+1);
			if (!(filetype.equalsIgnoreCase("jpg")||filetype.equalsIgnoreCase("jpeg")
					||filetype.equalsIgnoreCase("png")||filetype.equalsIgnoreCase("gif")||filename.equals(""))) {
				filechk=1;
				mv.addObject("filechk", filechk);
				mv.setViewName("typechk");
			} 
		}
		// �̸��� �ߺ�Ȯ�� (����Ȯ��)
		boolean result = false;
		result = usPro.confirmEmail(email);
		
		// ���� Ȯ��
		if(!result && filechk==0) {
			usPro.insertUser(user);
			
			System.out.println(email +"���� �����߽��ϴ�");
			mv.addObject("filechk", filechk);
			mv.addObject("result", result);
			mv.setViewName("typechk");
		} else {
			mv.addObject("result", result);
			mv.setViewName("typechk");
		}
		
		return mv;
	}
	
	// �̸��� �ߺ�üũ
	@RequestMapping("/confirmEmail")
	public String confirmEmail (String email, Model model) throws Throwable { 
		boolean result = usPro.confirmEmail(email);
		model.addAttribute("result", result);
		model.addAttribute("email", email);
		
		return  "confirmEmail"; 
	}
	
	// �α��� (���� ���)
	@RequestMapping("/LoginPro")
	public ModelAndView LoginPro(String email, String pwd, ModelAndView mv, UserDataBean user, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		
        int check = usPro.loginCheck(email, pwd);
        
        String msg = "";
        
        if(check == 1) {
        	user=usPro.getUser(email);
        	session.setAttribute("s_email", user.getEmail());
        	session.setAttribute("s_name", user.getName());
            session.setAttribute("s_filename", user.getFilename());
            session.setAttribute("s_option", user.getSort_option());
            session.setAttribute("s_p_level", user.getP_level());
			msg = "LoginPro";
			System.out.println(email+"���� �α��� �ϼ̽��ϴ�.");
			mv.setViewName(msg);
        }
        // ��й�ȣ�� Ʋ�����
        else if(check == 0) { 
        	mv.addObject("check", check);
        	mv.setViewName("Logincheck");
        }
        // ���̵� Ʋ�����
        else {
        	mv.addObject("check", check);
        	mv.setViewName("Logincheck");
        }
        
		return mv;
	}
	
	// �α׾ƿ�
	@RequestMapping("/LogoutPro")
	public ModelAndView LogoutPro(HttpServletRequest req, ModelAndView mv)  throws Throwable {
	    HttpSession session = req.getSession();
	    System.out.println(session.getAttribute("s_email")+"���� �α׾ƿ� �ϼ̽��ϴ�.");
	    session.invalidate(); // ��缼������ ����
	    
	    mv.setViewName("index");
	    
		return mv;
	}
	
	// ����������
	@RequestMapping("/user_page")
	public ModelAndView user_page (ModelAndView mv, UserDataBean user, String email, HttpServletRequest req, String sort_option) throws Exception {
		HttpSession session = req.getSession();
		if (email == null || email == "") {email = (String)session.getAttribute("s_email");}
		if (sort_option == null || sort_option == "") {sort_option = (String)session.getAttribute("s_option");}
		
		System.out.println("���� ���� �ɼ�: " + sort_option);
		
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			user = usPro.getUser(email);
			mv.addObject("user", user);
			mv.setViewName("view/user/user_page");
		}
		
		return mv;
	}
	
	@RequestMapping("user_deletePro")
	public ModelAndView user_deletePro (ModelAndView mv, String email, String pwd) {
		int check = usPro.deleteUser(email, pwd);
		int check_diary = usPro.deleteUser_diary(email);
		
		// ���̾�� �ϳ��� ���� ���� ������, �������������� check�� �Ű澲��. 1�� ����, 0�� ����
		System.out.println("��������: " + check+"\n���̾ ��������: "+check_diary);
		
		mv.addObject("check", check);
		mv.addObject("check_diary", check_diary);
		mv.setViewName("view/user/user_deletePro");
		
		return mv;
	}
	
	// ���������� ���� (Spring ������� �ϸ� ���  400..(���Ͼ�����쿣 �ߵ��ư� / ���� / ����� �õ��ص�..))
	@RequestMapping("/user_pagePro")
	public ModelAndView user_pagePro (ModelAndView mv, int num, String fname, int fsize, String email, String sort_option,
			MultipartHttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		
		UserDataBean user = new UserDataBean();
		
		MultipartFile multi = req.getFile("filename");
		String filename = multi.getOriginalFilename();
		System.out.println("[����������] Ȯ�� 1) ���� ���� ���ε�: "+filename+"\t # ���� �̹���: "+fname);
		
		user.setNum(num);
		user.setEmail(email);
		user.setPwd(req.getParameter("pwd"));
		user.setName(req.getParameter("name"));
		user.setTel(req.getParameter("tel"));
		user.setBirth(req.getParameter("birth"));
		user.setSort_option(sort_option);
		
		
		if (filename != null && !filename.equals("")) {
			String uploadPath = req.getRealPath("/")+"userSave";
			System.out.println("[����������] Ȯ�� 2) ���ε� ���: " +uploadPath);
			FileCopyUtils.copy(multi.getInputStream(), new FileOutputStream(uploadPath+"/"+multi.getOriginalFilename()));
			user.setFilename(filename);
			user.setFilesize((int)multi.getSize());
		} else {
			user.setFilename(fname);
			user.setFilesize(fsize);
		}
		
		// ������ ���� ���� Ÿ�� üũ
		String filetype=".png";
		int filechk=0;
		if (filename!=null) {
			filetype= filename.substring(filename.lastIndexOf(".")+1);
			if (!(filetype.equalsIgnoreCase("jpg")||filetype.equalsIgnoreCase("jpeg")
					||filetype.equalsIgnoreCase("png")||filetype.equalsIgnoreCase("gif")||filename.equals(""))) {
				filechk=1;
				mv.addObject("filechk", filechk);
				mv.setViewName("view/user/user_pagePro");
			} 
		}
		
		if(filechk==0) {
			int chk = usPro.updateUser(user);
			session.setAttribute("s_name", user.getName());
			session.setAttribute("s_filename", user.getFilename());
			session.setAttribute("s_option", user.getSort_option());
			
			System.out.println(email +"���� ������ ����Ǿ����ϴ�" + "\t # ���濩��[chk]: "+chk+"\n");
			mv.addObject("filechk", filechk);
			mv.addObject("chk", chk);
			mv.setViewName("view/user/user_pagePro");
		}
		
		return mv;
	}
	
	// ���������� ���� (���� X - �׽�Ʈ)  
	/*@RequestMapping("/user_pagePro")
	public ModelAndView user_pagePro (ModelAndView mv, int num, String email, UserDataBean user) throws Exception {
		
		int chk = usPro.updateUser(user);
		
		System.out.println(email +"���� ������ ����Ǿ����ϴ�" + "\n���濩��[chk]: "+chk);
		mv.addObject("chk", chk);
		mv.setViewName("view/user/user_pagePro");
	
		return mv;
	}*/
	
	// FAQ
	@RequestMapping("/user_faq")
	public ModelAndView user_faq (HttpServletRequest req, ModelAndView mv, String email) {
		HttpSession session = req.getSession();
		if (email == "" || email == null) {email = (String) session.getAttribute("s_email");}
		
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			mv.setViewName("view/user/user_faq");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// ����
	@RequestMapping("/user_main")
	public ModelAndView user_main (HttpServletRequest req, ModelAndView mv, String email, String sort_option) {
		HttpSession session = req.getSession();
		if (email == "" || email == null) {email = (String) session.getAttribute("s_email");}
		if (sort_option == "" || sort_option == null) {sort_option = (String) session.getAttribute("s_option");}
		
		int count = 0;
		
		int recent_num = 3;
		List recent_picture = null;
		
		count = diPro.getGalleryRecentCount(email, recent_num);
		
		if (count > 0) {
			if (sort_option.equals("r_date")) {
				recent_picture = diPro.getGalleryRecent(email, recent_num);
			}
			if (sort_option.equals("n_date")) {
				recent_picture = diPro.getGalleryRecent_d(email, recent_num);
			}
		}	
		System.out.println("���������� (�ֱ� ��¥ ���� ����): "+count+"\n���� �ɼ�: "+sort_option);
		
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.addObject("count", count);
			mv.addObject("recent_picture", recent_picture);
			mv.setViewName("view/user/user_main");
		}
		return mv;
	}
	
	@RequestMapping("/findpwd")
	public ModelAndView findpwd(ModelAndView mv) {
		mv.setViewName("findpwd");
		
		return mv; 
	}
	
}
