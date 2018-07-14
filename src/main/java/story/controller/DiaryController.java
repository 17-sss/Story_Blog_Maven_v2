package story.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import story.db.DiaryDBMyBatis;
import story.db.DiaryDataBean;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	DiaryDBMyBatis diPro = DiaryDBMyBatis.getInstance();
	
	// �ϱ� ���� (��)
	@RequestMapping("/diary_write")
	public ModelAndView diary_write (HttpServletRequest req, ModelAndView mv, String email) {
		HttpSession session = req.getSession();
		
		int num=0;
		List d_diarylist = null;
		
		if (email == null || email=="") {
			email = (String)session.getAttribute("s_email");
		}
		if (req.getParameter("num")!=null) {
			num = Integer.parseInt(req.getParameter("num"));
		}
		
		d_diarylist = diPro.getDiarylist(email);
		
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.addObject("num", num);
			mv.addObject("d_diarylist", d_diarylist);
			mv.setViewName("view/diary/diary_write");
		}
		
		return mv;
	}
	
	// �ϱ� ���� (����)
	@RequestMapping("/diary_writePro")
	public ModelAndView diary_writePro (ModelAndView mv, MultipartHttpServletRequest req, int num, String user_email, String user_name,
			String d_diary, String d_date, String subject, String content, String pageNum) throws Throwable {
		DiaryDataBean diary = new DiaryDataBean();
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		
		MultipartFile multi1 = req.getFile("filename1");
		String filename1 = multi1.getOriginalFilename();
		MultipartFile multi2 = req.getFile("filename2");
		String filename2 = multi2.getOriginalFilename();
		MultipartFile multi3 = req.getFile("filename3");
		String filename3 = multi3.getOriginalFilename();
		MultipartFile multi4 = req.getFile("filename4");
		String filename4 = multi4.getOriginalFilename();
		MultipartFile multi5 = req.getFile("filename5");
		String filename5 = multi5.getOriginalFilename();
		MultipartFile multi6 = req.getFile("filename6");
		String filename6 = multi6.getOriginalFilename();
		
		System.out.println("#####[����-write]#####\n"+"�ϱ� �̹���1: "+filename1+"\n"+"�ϱ� �̹���2: "+filename2+"\n"+"�ϱ� �̹���3: "+filename3+"\n"
				+"�ϱ� �̹���4: "+filename4+"\n"+"�ϱ� �̹���5: "+filename5+"\n"+"�ϱ� �̹���6: "+filename6+"\n"+"###############");
		
		diary.setNum(num);
		diary.setUser_email(user_email);
		diary.setUser_name(user_name);
		diary.setD_diary(d_diary);
		diary.setD_date(d_date);
		diary.setSubject(subject);
		diary.setContent(content);
		//diary.setContent(req.getParameter("content"));
		
		String uploadPath = req.getRealPath("/")+"fileSave";
		
		if (filename1 != null && !filename1.equals("")) {
			System.out.println("Ȯ��) ���ε� ���: " + uploadPath);
			FileCopyUtils.copy(multi1.getInputStream(), new FileOutputStream(uploadPath+"/"+multi1.getOriginalFilename()));
			diary.setFilename1(filename1);
			diary.setFilesize1((int)multi1.getSize());
		}
		if (filename2 != null && !filename2.equals("")) {
			FileCopyUtils.copy(multi2.getInputStream(), new FileOutputStream(uploadPath+"/"+multi2.getOriginalFilename()));
			diary.setFilename2(filename2);
			diary.setFilesize2((int)multi2.getSize());
		}
		if (filename3 != null && !filename3.equals("")) {
			FileCopyUtils.copy(multi3.getInputStream(), new FileOutputStream(uploadPath+"/"+multi3.getOriginalFilename()));
			diary.setFilename3(filename3);
			diary.setFilesize3((int)multi3.getSize());
		}
		if (filename4 != null && !filename4.equals("")) {
			FileCopyUtils.copy(multi4.getInputStream(), new FileOutputStream(uploadPath+"/"+multi4.getOriginalFilename()));
			diary.setFilename4(filename4);
			diary.setFilesize4((int)multi4.getSize());
		}
		if (filename5 != null && !filename5.equals("")) {
			FileCopyUtils.copy(multi5.getInputStream(), new FileOutputStream(uploadPath+"/"+multi5.getOriginalFilename()));
			diary.setFilename5(filename5);
			diary.setFilesize5((int)multi5.getSize());
		}
		if (filename6 != null && !filename6.equals("")) {
			FileCopyUtils.copy(multi6.getInputStream(), new FileOutputStream(uploadPath+"/"+multi6.getOriginalFilename()));
			diary.setFilename6(filename6);
			diary.setFilesize6((int)multi6.getSize());
		}
		
		// ���� Ÿ�� üũ
		String file1, file2, file3, file4, file5, file6 = ".png";
		int filechk1 = 0;	int filechk3 = 0;	int filechk5 = 0;
		int filechk2 = 0;	int filechk4 = 0;	int filechk6 = 0;
		
		// filechk�� 1�� ���, Ȯ����: jpg, jpeg, png, gif�� �ƴ�.
		// equalsIgnoreCase�� ��ҹ��� ���о��� �����ִ� ��ɾ�.
		if (filename1!=null || filename2!=null || filename3!=null || filename4!=null || filename5!=null || filename6!=null) {
			
			// filename1.equals("") -> ("")�� �ƹ��͵� ���°� �ƴ�. �������� �����ִ°�. [?]
			// !filename1.equals("") �Ͻ�, �����̸� �����ϴµ�..? ��������� ���ϴ´�� ��. [?]
			// filename1.equals("")�� �翬�ϰԵ� �����̸� �����̸� ����� �ȵ�(true ��ȯ)
			System.out.println("* filename �׽�Ʈ *\n"+
					"filename1.equals(\"\")[X]: " + filename1.equals("")+"\n"
			+"!filename1.equals(\"\")[X]: " + !filename1.equals("")+"\n"
					+"!(filename1.equals(\"\"))[V]: "+!(filename1.equals(""))); 
			
			file1 = filename1.substring(filename1.lastIndexOf(".")+1);
			if (!(file1.equalsIgnoreCase("jpg")||file1.equalsIgnoreCase("jpeg")
					||file1.equalsIgnoreCase("png")||file1.equalsIgnoreCase("gif")) && !filename1.equals("")) {
				filechk1 = 1;
				mv.addObject("filechk1", filechk1);
				mv.setViewName("view/diary/diary_writePro");
			}
			
			file2 = filename2.substring(filename2.lastIndexOf(".")+1);
			if (!(file2.equalsIgnoreCase("jpg")||file2.equalsIgnoreCase("jpeg")
					||file2.equalsIgnoreCase("png")||file2.equalsIgnoreCase("gif")) && !filename2.equals("")) {
				filechk2 = 1;
				mv.addObject("filechk2", filechk2);
				mv.setViewName("view/diary/diary_writePro");
			}
			
			file3 = filename3.substring(filename3.lastIndexOf(".")+1);
			if (!(file3.equalsIgnoreCase("jpg")||file3.equalsIgnoreCase("jpeg")
					||file3.equalsIgnoreCase("png")||file3.equalsIgnoreCase("gif")) && !filename3.equals("")) {
				filechk3 = 1;
				mv.addObject("filechk3", filechk3);
				mv.setViewName("view/diary/diary_writePro");
			}
			
			file4 = filename4.substring(filename4.lastIndexOf(".")+1);
			if (!(file4.equalsIgnoreCase("jpg")||file4.equalsIgnoreCase("jpeg")
					||file4.equalsIgnoreCase("png")||file4.equalsIgnoreCase("gif")) && !filename4.equals("")) {
				filechk4 = 1;
				mv.addObject("filechk4", filechk4);
				mv.setViewName("view/diary/diary_writePro");
			}
			
			file5 = filename5.substring(filename5.lastIndexOf(".")+1);
			if (!(file5.equalsIgnoreCase("jpg")||file5.equalsIgnoreCase("jpeg")
					||file5.equalsIgnoreCase("png")||file5.equalsIgnoreCase("gif")) && !filename5.equals("")) {
				filechk5 = 1;
				mv.addObject("filechk5", filechk5);
				mv.setViewName("view/diary/diary_writePro");
			}
			
			file6 = filename6.substring(filename6.lastIndexOf(".")+1);
			if (!(file6.equalsIgnoreCase("jpg")||file6.equalsIgnoreCase("jpeg")
					||file6.equalsIgnoreCase("png")||file6.equalsIgnoreCase("gif")) && !filename6.equals("")) {
				filechk6 = 1;
				mv.addObject("filechk6", filechk6);
				mv.setViewName("view/diary/diary_writePro");
			}
		}	
		if (filechk1==0 && filechk2==0 && filechk3==0 && filechk4==0 && filechk5==0 && filechk6==0) {
			diPro.insertDiary(diary);
			System.out.println("#####Diary Write#####\n"+diary);
			mv.addObject("filechk1", filechk1);	mv.addObject("filechk2", filechk2);
			mv.addObject("filechk3", filechk3);	mv.addObject("filechk4", filechk4);
			mv.addObject("filechk5", filechk5);	mv.addObject("filechk6", filechk6);
		    mv.addObject("pageNum", pageNum);
			mv.setViewName("view/diary/diary_writePro");
		}
		
		return mv;
	}
	
	// �ϱ� �Խ��� - ��ü����
	@RequestMapping(value = "/diary_board", produces = "application/text; charset=euckr")
	public ModelAndView diary_board (HttpServletRequest req, ModelAndView mv, String pageNum, String email, String search, String opt) throws Exception {  
		HttpSession session = req.getSession();
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
	    
	    int pageSize = 10;
	    
	    int currentPage = Integer.parseInt(pageNum);
	   
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    List d_list = null;
	    List d_diarylist = null;
	    
	    // ��ü����
	    int count = 0; 
	    int number = 0;
	    
	    d_diarylist = diPro.getDiarylist(email); // �ϱ��� ��� ������
	    
	    // ��ü ����
	    count = diPro.getDiaryCount(email);
		if (count > 0) {
			d_list = diPro.getDiaries(startRow, endRow, email);
			System.out.println("############\n"+"Count (��ü): " + count + "\n############");
		} 
		
	    // ��ü ���� - �˻�
	    if (search != null) {

			if (opt.equals("SC")) {
				count = diPro.getSearchDiaryCount_SC(email, "%" + search + "%");
				if (count > 0) {
					d_list = diPro.searchDiary_SC(startRow, endRow, email, "%" + search + "%");
					System.out.println("[SC] Count: " + count);
				}
			} 
		    if (opt.equals("S")) {
				count = diPro.getSearchDiaryCount_S(email, "%" + search + "%");
				if (count > 0) {
					d_list = diPro.searchDiary_S(startRow, endRow, email, "%" + search + "%");
					System.out.println("[S] Count: " + count);
				}
			}
		    if (opt.equals("C")) {
				count = diPro.getSearchDiaryCount_C(email, "%" + search + "%");
				if (count > 0) {
					d_list = diPro.searchDiary_C(startRow, endRow, email, "%" + search + "%");
					System.out.println("[C] Count: " + count);
				}
			}
		    System.out.println("~~~~~~~~~~~~\n(��ü) �˻���: "+search+"\n�̸���: " +email+"\n~~~~~~~~~~~~");
	    }   
	    ///// end. ��ü ����
	    
		number = count - (currentPage - 1) * pageSize;

	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;    
	    int endPage = startPage + bottomLine - 1;
	    int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); 
	    if (endPage > pageCount) { endPage = pageCount; } 

	    // ��ü ���� ����
	    mv.addObject("count", count);
	    mv.addObject("number", number);
	    mv.addObject("pageCount", pageCount);
	    mv.addObject("endPage", endPage);
	   
	    // �ϱ��� ��ũ ���� ����
	    mv.addObject("d_diarylist", d_diarylist); 
	  
	    // ����
	    // (�������δ� �ȳѰ���) startRow, endRow, pageSize�� ����
	    mv.addObject("d_list", d_list);
	    mv.addObject("currentPage", currentPage);
	    mv.addObject("bottomLine", bottomLine);
	    mv.addObject("startPage", startPage);
	   
	    // �˻�
	    mv.addObject("search", search);
	    mv.addObject("opt", opt);

		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/diary/diary_board");
		}
		
		return mv;
	}
	
	// �ϱ� �Խ��� - ����
	@RequestMapping(value = "/diary_board2", produces = "application/text; charset=euckr")
	public ModelAndView diary_board2 (HttpServletRequest req, ModelAndView mv, String pageNum, String email, String d_diary, String search, String opt) throws Exception  {
		HttpSession session = req.getSession();
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
	    
	    int pageSize = 10;
	    
	    int currentPage = Integer.parseInt(pageNum);
	   
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    List d_list = null;
	    List d_diarylist = null;
	    
	    // �ϱ��� ���� ����
	    int count2 = 0;
	    int number2 = 0;
	    
	    d_diarylist = diPro.getDiarylist(email); // �ϱ��� ��� ������
	    
	    // �ϱ��� ����
		count2 = diPro.getDiaryCount(email, d_diary);
		if (count2 > 0) {
			d_list = diPro.getDiaries(startRow, endRow, email, d_diary);
			System.out.println("############\n"+"Count2 (����): "+ count2 + "\nDiary: "+d_diary+"\n############");
		}

		// �ϱ��� ���� - �˻�
	    if (search != null) {
	    	
		    if (opt.equals("SC")) {
				count2 = diPro.getSearchDiaryCount_D_SC(email, d_diary, "%"+search+"%");
				if (count2 > 0) {
					d_list = diPro.searchDiary_D_SC(startRow, endRow, email, d_diary, "%"+search+"%");
					System.out.println("[SC] Count2: " + count2);
				}
			} 
		    if (opt.equals("S")) {
				count2 = diPro.getSearchDiaryCount_D_S(email, d_diary, "%"+search+"%");
				if (count2 > 0) {
					d_list = diPro.searchDiary_D_S(startRow, endRow, email, d_diary, "%"+search +"%");
					System.out.println("[S] Count2: " + count2);
				}
			}
		    if (opt.equals("C")) {
				count2 = diPro.getSearchDiaryCount_D_C(email, d_diary, "%"+search+"%");
				if (count2 > 0) {
					d_list = diPro.searchDiary_D_C(startRow, endRow, email, d_diary, "%"+search+"%");
					System.out.println("[C] Count2: " + count2);
				}
			}
			
		    System.out.println("~~~~~~~~~~~~\n(����) �˻���: "+search+"\n�ϱ���: "+d_diary+"\n�̸���: " +email+"\n~~~~~~~~~~~~");
	    }  
	    ///// end. �ϱ��� ����
	   
	    number2 = count2 - (currentPage - 1) * pageSize;
	    
	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
	    
	    int endPage2 = startPage + bottomLine - 1;
	    int pageCount2 = count2 / pageSize + (count2 % pageSize == 0 ? 0 : 1);
	    if (endPage2 > pageCount2) { endPage2 = pageCount2; }

	    // ���� ����
	    mv.addObject("count2", count2);
	    mv.addObject("number2", number2);
	    mv.addObject("pageCount2", pageCount2);
	    mv.addObject("endPage2", endPage2);
	    mv.addObject("d_diary", d_diary);
	  
	    // �ϱ��� ��ũ ���� ����
	    mv.addObject("d_diarylist", d_diarylist); 
	  
	    // ����
	    // (�������δ� �ȳѰ���) startRow, endRow, pageSize�� ����
	    mv.addObject("d_list", d_list);
	    mv.addObject("currentPage", currentPage);
	    mv.addObject("bottomLine", bottomLine);
	    mv.addObject("startPage", startPage);
	   
	    // �˻�
	    mv.addObject("search", search);
	    mv.addObject("opt", opt);

		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/diary/diary_board2");
		}
		
		return mv;
	}
	
	// �ϱ� ����
	@RequestMapping("/diary_content")
	public ModelAndView diary_content (HttpServletRequest req, ModelAndView mv, int num, DiaryDataBean diary, String email, String pageNum, 
			String d_diary, String opt, String search, String type, String date_opt) {
		HttpSession session = req.getSession();
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
		System.out.println("####content####\n���̾: "+d_diary+"\n�˻���: "+search+"\n�ɼ�: "+opt+"\nŸ��: "+type);
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			diary = diPro.getDiary(num, email);
			mv.addObject("diary", diary);
			mv.addObject("pageNum", pageNum);
			
			// �˻��� ���� (���ư� ������ ���п� ����)
			mv.addObject("opt", opt);
			mv.addObject("search", search);
			
			// ��ü, ���� ���� (d_diary) [����, ���� �� ���ư� ������ ���п� ����] - d_diary ������ ����.
			mv.addObject("d_diary", d_diary); 
			
			// �Ϲ�, ������ ����
			mv.addObject("type", type);
			mv.addObject("date_opt", date_opt);
			
			mv.setViewName("view/diary/diary_content");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// �ϱ� ���� (����)
	@RequestMapping("/diary_deletePro")
	public ModelAndView diary_deletePro (HttpServletRequest req, ModelAndView mv, int num, String email, String pageNum,
			String d_diary, String chk_d_diary, String opt, String search) {
		HttpSession session = req.getSession();
		
		if (d_diary != null || d_diary != "") {chk_d_diary = d_diary;}
		
		System.out.println("####deletePro####\n���̾: "+d_diary+"\n�˻���: "+search+"\n�ɼ�: "+opt+
				"\n��ȣ: "+num+"\n��������ȣ: "+pageNum+"\n���̾ ���� (��ü[null] or ����[not null]): "+chk_d_diary);
		
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			int check = diPro.deleteDiary(num, email);
			System.out.println("###Diary_Delete###\n�������� (1 or 0): " + check);
			mv.addObject("check", check);
			
			// ���� (���ư� ������ ���п� ����. content��)
			mv.addObject("pageNum", pageNum);
					
			// ��ü, ���� ���� (d_diary)  [����, ���� �� ���ư� ������ ���п� ����] 
			mv.addObject("d_diary", d_diary); 
			mv.addObject("chk_d_diary", chk_d_diary);
			
			// �˻��� ���� (���ư� ������ ���п� ����)
			mv.addObject("opt", opt);
			mv.addObject("search", search);
			
			mv.setViewName("view/diary/diary_deletePro");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// �ϱ� ����
	@RequestMapping("/diary_update")
	public ModelAndView diary_update (HttpServletRequest req, ModelAndView mv, int num, DiaryDataBean diary, String email, 
			String pageNum, String d_diary, String opt, String search, String not_in_d_diary, String chk_d_diary) {
		HttpSession session = req.getSession();
		List d_diarylist = null;
		
		if (d_diary != null || d_diary != "") {chk_d_diary = d_diary;}
		
		System.out.println("####update####\n���̾: "+d_diary+"\n�˻���: "+search+"\n�ɼ�: "+opt+
				"\n��ȣ: "+num+"\n��������ȣ: "+pageNum+"\n���̾ ���� (��ü[null] or ����[not null]): "+chk_d_diary);
		
		
		// ���� ����
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			d_diarylist = diPro.getDiarylist(email, not_in_d_diary);
			diary = diPro.getDiary(num, email);
			mv.addObject("num", num);
			mv.addObject("pageNum", pageNum);
			
			mv.addObject("diary", diary);
			mv.addObject("d_diarylist", d_diarylist);
			
			// ��ü, ���� ���� (d_diary)  [����, ���� �� ���ư� ������ ���п� ����] 
			mv.addObject("d_diary", d_diary); 
			mv.addObject("chk_d_diary", chk_d_diary);
			
			// �˻��� ���� (���ư� ������ ���п� ����)
			mv.addObject("opt", opt);
			mv.addObject("search", search);
			
			mv.setViewName("view/diary/diary_update");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// �ϱ� ���� (����)
	@RequestMapping("/diary_updatePro")
	public ModelAndView diary_updatePro (MultipartHttpServletRequest req, ModelAndView mv, int num, String email, String chk_d_diary,
			String user_name, String d_date, String subject, String content, String pageNum, String d_diary, String opt, String search) throws Throwable { 
		//HttpSession session = req.getSession();
		DiaryDataBean diary = new DiaryDataBean();
		
		if (chk_d_diary == "" || chk_d_diary.equals("")) {chk_d_diary = null;}
		
		System.out.println("####updatePro####\n���̾: "+d_diary+"\n�˻���: "+search+"\n�ɼ�: "+opt+
				"\n��ȣ: "+num+"\n��������ȣ: "+pageNum+"\n���̾ ���� (��ü[null] or ����[not null]): "+chk_d_diary);
		
		MultipartFile multi1 = req.getFile("filename1");
		String filename1 = multi1.getOriginalFilename();
		MultipartFile multi2 = req.getFile("filename2");
		String filename2 = multi2.getOriginalFilename();
		MultipartFile multi3 = req.getFile("filename3");
		String filename3 = multi3.getOriginalFilename();
		MultipartFile multi4 = req.getFile("filename4");
		String filename4 = multi4.getOriginalFilename();
		MultipartFile multi5 = req.getFile("filename5");
		String filename5 = multi5.getOriginalFilename();
		MultipartFile multi6 = req.getFile("filename6");
		String filename6 = multi6.getOriginalFilename();
		
		System.out.println("#####[����-update]#####\n"+"�ϱ� ���� �̹���1: "+filename1+"\n"+"�ϱ� ���� �̹���2: "+filename2+"\n"+"�ϱ� ���� �̹���3: "+filename3+"\n"
				+"�ϱ� ���� �̹���4: "+filename4+"\n"+"�ϱ� ���� �̹���5: "+filename5+"\n"+"�ϱ� ���� �̹���6: "+filename6+"\n"+"###############");
		
		diary.setNum(num);
		diary.setUser_email(email);
		diary.setUser_name(user_name);
		diary.setD_diary(d_diary);
		diary.setD_date(d_date);
		diary.setSubject(subject);
		diary.setContent(content);
		
		diary.setFilename1(req.getParameter("filename1"));
		diary.setFilename2(req.getParameter("filename2"));
		diary.setFilename3(req.getParameter("filename3"));
		diary.setFilename4(req.getParameter("filename4"));
		diary.setFilename5(req.getParameter("filename5"));
		diary.setFilename6(req.getParameter("filename6"));
		
		String uploadPath = req.getRealPath("/")+"fileSave";
		
		if (filename1 != null && !filename1.equals("")) {
			System.out.println("Ȯ��) ���ε� ���: " + uploadPath);
			FileCopyUtils.copy(multi1.getInputStream(), new FileOutputStream(uploadPath+"/"+multi1.getOriginalFilename()));
			diary.setFilename1(filename1);
			diary.setFilesize1((int)multi1.getSize());
		}
		if (filename2 != null && !filename2.equals("")) {
			FileCopyUtils.copy(multi2.getInputStream(), new FileOutputStream(uploadPath+"/"+multi2.getOriginalFilename()));
			diary.setFilename2(filename2);
			diary.setFilesize2((int)multi2.getSize());
		}
		if (filename3 != null && !filename3.equals("")) {
			FileCopyUtils.copy(multi3.getInputStream(), new FileOutputStream(uploadPath+"/"+multi3.getOriginalFilename()));
			diary.setFilename3(filename3);
			diary.setFilesize3((int)multi3.getSize());
		}
		if (filename4 != null && !filename4.equals("")) {
			FileCopyUtils.copy(multi4.getInputStream(), new FileOutputStream(uploadPath+"/"+multi4.getOriginalFilename()));
			diary.setFilename4(filename4);
			diary.setFilesize4((int)multi4.getSize());
		}
		if (filename5 != null && !filename5.equals("")) {
			FileCopyUtils.copy(multi5.getInputStream(), new FileOutputStream(uploadPath+"/"+multi5.getOriginalFilename()));
			diary.setFilename5(filename5);
			diary.setFilesize5((int)multi5.getSize());
		}
		if (filename6 != null && !filename6.equals("")) {
			FileCopyUtils.copy(multi6.getInputStream(), new FileOutputStream(uploadPath+"/"+multi6.getOriginalFilename()));
			diary.setFilename6(filename6);
			diary.setFilesize6((int)multi6.getSize());
		}
		
		// ���� Ÿ�� üũ
		String file1, file2, file3, file4, file5, file6 = ".png";
		int filechk1 = 0;	int filechk3 = 0;	int filechk5 = 0;
		int filechk2 = 0;	int filechk4 = 0;	int filechk6 = 0;
	
		if (filename1!=null || filename2!=null || filename3!=null || filename4!=null || filename5!=null || filename6!=null) {

			file1 = filename1.substring(filename1.lastIndexOf(".")+1);
			if (!(file1.equalsIgnoreCase("jpg")||file1.equalsIgnoreCase("jpeg")
					||file1.equalsIgnoreCase("png")||file1.equalsIgnoreCase("gif")) && !filename1.equals("")) {
				filechk1 = 1;
				mv.addObject("filechk1", filechk1);
				mv.setViewName("view/diary/diary_updatePro");
			}
			
			file2 = filename2.substring(filename2.lastIndexOf(".")+1);
			if (!(file2.equalsIgnoreCase("jpg")||file2.equalsIgnoreCase("jpeg")
					||file2.equalsIgnoreCase("png")||file2.equalsIgnoreCase("gif")) && !filename2.equals("")) {
				filechk2 = 1;
				mv.addObject("filechk2", filechk2);
				mv.setViewName("view/diary/diary_updatePro");
			}
			
			file3 = filename3.substring(filename3.lastIndexOf(".")+1);
			if (!(file3.equalsIgnoreCase("jpg")||file3.equalsIgnoreCase("jpeg")
					||file3.equalsIgnoreCase("png")||file3.equalsIgnoreCase("gif")) && !filename3.equals("")) {
				filechk3 = 1;
				mv.addObject("filechk3", filechk3);
				mv.setViewName("view/diary/diary_updatePro");
			}
			
			file4 = filename4.substring(filename4.lastIndexOf(".")+1);
			if (!(file4.equalsIgnoreCase("jpg")||file4.equalsIgnoreCase("jpeg")
					||file4.equalsIgnoreCase("png")||file4.equalsIgnoreCase("gif")) && !filename4.equals("")) {
				filechk4 = 1;
				mv.addObject("filechk4", filechk4);
				mv.setViewName("view/diary/diary_updatePro");
			}
			
			file5 = filename5.substring(filename5.lastIndexOf(".")+1);
			if (!(file5.equalsIgnoreCase("jpg")||file5.equalsIgnoreCase("jpeg")
					||file5.equalsIgnoreCase("png")||file5.equalsIgnoreCase("gif")) && !filename5.equals("")) {
				filechk5 = 1;
				mv.addObject("filechk5", filechk5);
				mv.setViewName("view/diary/diary_updatePro");
			}
			
			file6 = filename6.substring(filename6.lastIndexOf(".")+1);
			if (!(file6.equalsIgnoreCase("jpg")||file6.equalsIgnoreCase("jpeg")
					||file6.equalsIgnoreCase("png")||file6.equalsIgnoreCase("gif")) && !filename6.equals("")) {
				filechk6 = 1;
				mv.addObject("filechk6", filechk6);
				mv.setViewName("view/diary/diary_updatePro");
			}
		}	
		if (filechk1==0 && filechk2==0 && filechk3==0 && filechk4==0 && filechk5==0 && filechk6==0) {
			int chk = diPro.updateDiary(diary);
			System.out.println("#####Diary Update#####\n"+diary);
			mv.addObject("filechk1", filechk1);	mv.addObject("filechk2", filechk2);
			mv.addObject("filechk3", filechk3);	mv.addObject("filechk4", filechk4);
			mv.addObject("filechk5", filechk5);	mv.addObject("filechk6", filechk6);
			
			mv.addObject("chk", chk);
		    
			// ���� (���ư� ������ ���п� ����. content��)
			mv.addObject("pageNum", pageNum);
			mv.addObject("num", num);
			
			// ��ü, ���� ���� (d_diary)  [����, ���� �� ���ư� ������ ���п� ����] 
			mv.addObject("d_diary", d_diary); 
			mv.addObject("chk_d_diary", chk_d_diary);
			
			// �˻��� ���� (���ư� ������ ���п� ����)
			mv.addObject("opt", opt);
			mv.addObject("search", search);

			mv.setViewName("view/diary/diary_updatePro");
		}
	
		return mv;
	}
	
	// ����ø
	@RequestMapping("/diary_gallery")
	public ModelAndView diary_gallery (HttpServletRequest req, ModelAndView mv, String email, String pageNum, String date_opt) {
		HttpSession session  = req.getSession();
		
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
		if (pageNum == null || pageNum == "") {pageNum = "1";}
	    
	    int pageSize = 5;
	    
	    int currentPage = Integer.parseInt(pageNum);
	   
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    List gallery_list = null;
	    List date_list = null;
	    
	    int count = 0; 
	    int number = 0;
	    
	    System.out.println("��¥ �ɼ� (������): " + date_opt);
	    
	    // ������
	    if (date_opt == null) {
		    count = diPro.getGalleryCount(email);
			if (count > 0) {
			    date_list = diPro.getGalleryDate(email); // �ϱ� ��¥ ����Ʈ
				gallery_list = diPro.getGallery(startRow, endRow, email);
				System.out.println("Count (������): " + count);
			}
	    }

	    // ������ - ��¥�� �˻�
	    if (date_opt != null) {
		    count = diPro.getGalleryCount(email, date_opt);
			if (count > 0) {
			    date_list = diPro.getGalleryDate(email, date_opt); // �ϱ� ��¥ ����Ʈ (�˻��� ��¥ ����)
				gallery_list = diPro.getGallery(startRow, endRow, email, date_opt);
				System.out.println("Count (������ - ��¥��): " + count);
			}
	    }
	    
		number = count - (currentPage - 1) * pageSize;

	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;    
	    int endPage = startPage + bottomLine - 1;
	    int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); 
	    if (endPage > pageCount) { endPage = pageCount; } 


		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			mv.addObject("count", count);
		    mv.addObject("number", number);
		    mv.addObject("pageCount", pageCount);
		    mv.addObject("endPage", endPage);
		   
		    mv.addObject("gallery_list", gallery_list);
		    mv.addObject("date_list", date_list);
		   
		    mv.addObject("date_opt", date_opt);
		    
		    mv.addObject("currentPage", currentPage);
		    mv.addObject("bottomLine", bottomLine);
		    mv.addObject("startPage", startPage);
		    
			mv.setViewName("view/diary/diary_gallery");
		} else {
			mv.setViewName("index");
		}
		return mv;
	}

}
