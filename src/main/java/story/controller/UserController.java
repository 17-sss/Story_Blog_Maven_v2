package story.controller;

import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			msg = "view/user/user_main";
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
	public ModelAndView user_page (ModelAndView mv, UserDataBean user, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			user = usPro.getUser((String)session.getAttribute("s_email"));
			mv.addObject("user", user);
			mv.setViewName("view/user/user_page");
		}
		
		return mv;
	}
	
	// ���������� ���� (Spring ������� �ϸ� ���  400..(���Ͼ�����쿣 �ߵ��ư� / ���� / ����� �õ��ص�..))
	@RequestMapping("/user_pagePro")
	public ModelAndView user_pagePro (ModelAndView mv, int num, String fname, int fsize, String email, MultipartHttpServletRequest req) throws Exception {
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
	
	// ����
	@RequestMapping("/user_main")
	public ModelAndView user_main (HttpServletRequest req, ModelAndView mv) throws Exception {
		HttpSession session = req.getSession();
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/user/user_main");
		}
		return mv;
	}
	
}
