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
	
	// 일기 쓰기 (폼)
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
		
		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.addObject("num", num);
			mv.addObject("d_diarylist", d_diarylist);
			mv.setViewName("view/diary/diary_write");
		}
		
		return mv;
	}
	
	// 일기 쓰기 (전송)
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
		
		System.out.println("#####[사진-write]#####\n"+"일기 이미지1: "+filename1+"\n"+"일기 이미지2: "+filename2+"\n"+"일기 이미지3: "+filename3+"\n"
				+"일기 이미지4: "+filename4+"\n"+"일기 이미지5: "+filename5+"\n"+"일기 이미지6: "+filename6+"\n"+"###############");
		
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
			System.out.println("확인) 업로드 경로: " + uploadPath);
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
		
		// 파일 타입 체크
		String file1, file2, file3, file4, file5, file6 = ".png";
		int filechk1 = 0;	int filechk3 = 0;	int filechk5 = 0;
		int filechk2 = 0;	int filechk4 = 0;	int filechk6 = 0;
		
		// filechk가 1일 경우, 확장자: jpg, jpeg, png, gif가 아님.
		// equalsIgnoreCase는 대소문자 구분없이 비교해주는 명령어.
		if (filename1!=null || filename2!=null || filename3!=null || filename4!=null || filename5!=null || filename6!=null) {
			
			// filename1.equals("") -> ("")는 아무것도 없는게 아님. 공백으로 남아있는것. [?]
			// !filename1.equals("") 일시, 파일이름 무시하는듯..? 결과적으로 원하는대로 됨. [?]
			// filename1.equals("")은 당연하게도 파일이름 공백이면 통과가 안됨(true 반환)
			System.out.println("* filename 테스트 *\n"+
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
	
	// 일기 게시판 - 전체보기
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
	    
	    // 전체보기
	    int count = 0; 
	    int number = 0;
	    
	    d_diarylist = diPro.getDiarylist(email); // 일기장 목록 생성용
	    
	    // 전체 보기
	    count = diPro.getDiaryCount(email);
		if (count > 0) {
			d_list = diPro.getDiaries(startRow, endRow, email);
			System.out.println("############\n"+"Count (전체): " + count + "\n############");
		} 
		
	    // 전체 보기 - 검색
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
		    System.out.println("~~~~~~~~~~~~\n(전체) 검색어: "+search+"\n이메일: " +email+"\n~~~~~~~~~~~~");
	    }   
	    ///// end. 전체 보기
	    
		number = count - (currentPage - 1) * pageSize;

	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;    
	    int endPage = startPage + bottomLine - 1;
	    int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); 
	    if (endPage > pageCount) { endPage = pageCount; } 

	    // 전체 보기 전용
	    mv.addObject("count", count);
	    mv.addObject("number", number);
	    mv.addObject("pageCount", pageCount);
	    mv.addObject("endPage", endPage);
	   
	    // 일기장 링크 생성 전용
	    mv.addObject("d_diarylist", d_diarylist); 
	  
	    // 공용
	    // (페이지로는 안넘겨줌) startRow, endRow, pageSize도 공용
	    mv.addObject("d_list", d_list);
	    mv.addObject("currentPage", currentPage);
	    mv.addObject("bottomLine", bottomLine);
	    mv.addObject("startPage", startPage);
	   
	    // 검색
	    mv.addObject("search", search);
	    mv.addObject("opt", opt);

		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/diary/diary_board");
		}
		
		return mv;
	}
	
	// 일기 게시판 - 개별
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
	    
	    // 일기장 개별 전용
	    int count2 = 0;
	    int number2 = 0;
	    
	    d_diarylist = diPro.getDiarylist(email); // 일기장 목록 생성용
	    
	    // 일기장 개별
		count2 = diPro.getDiaryCount(email, d_diary);
		if (count2 > 0) {
			d_list = diPro.getDiaries(startRow, endRow, email, d_diary);
			System.out.println("############\n"+"Count2 (개별): "+ count2 + "\nDiary: "+d_diary+"\n############");
		}

		// 일기장 개별 - 검색
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
			
		    System.out.println("~~~~~~~~~~~~\n(개별) 검색어: "+search+"\n일기장: "+d_diary+"\n이메일: " +email+"\n~~~~~~~~~~~~");
	    }  
	    ///// end. 일기장 개별
	   
	    number2 = count2 - (currentPage - 1) * pageSize;
	    
	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
	    
	    int endPage2 = startPage + bottomLine - 1;
	    int pageCount2 = count2 / pageSize + (count2 % pageSize == 0 ? 0 : 1);
	    if (endPage2 > pageCount2) { endPage2 = pageCount2; }

	    // 개별 전용
	    mv.addObject("count2", count2);
	    mv.addObject("number2", number2);
	    mv.addObject("pageCount2", pageCount2);
	    mv.addObject("endPage2", endPage2);
	    mv.addObject("d_diary", d_diary);
	  
	    // 일기장 링크 생성 전용
	    mv.addObject("d_diarylist", d_diarylist); 
	  
	    // 공용
	    // (페이지로는 안넘겨줌) startRow, endRow, pageSize도 공용
	    mv.addObject("d_list", d_list);
	    mv.addObject("currentPage", currentPage);
	    mv.addObject("bottomLine", bottomLine);
	    mv.addObject("startPage", startPage);
	   
	    // 검색
	    mv.addObject("search", search);
	    mv.addObject("opt", opt);

		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else {
			mv.setViewName("view/diary/diary_board2");
		}
		
		return mv;
	}
	
	// 일기 내용
	@RequestMapping("/diary_content")
	public ModelAndView diary_content (HttpServletRequest req, ModelAndView mv, int num, DiaryDataBean diary, String email, String pageNum, 
			String d_diary, String opt, String search, String type, String date_opt) {
		HttpSession session = req.getSession();
		if (email == null || email=="") {email = (String)session.getAttribute("s_email");}
		System.out.println("####content####\n다이어리: "+d_diary+"\n검색어: "+search+"\n옵션: "+opt+"\n타입: "+type);
		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			diary = diPro.getDiary(num, email);
			mv.addObject("diary", diary);
			mv.addObject("pageNum", pageNum);
			
			// 검색어 구분 (돌아갈 페이지 구분에 쓰임)
			mv.addObject("opt", opt);
			mv.addObject("search", search);
			
			// 전체, 개별 구분 (d_diary) [수정, 삭제 후 돌아갈 페이지 구분에 쓰임] - d_diary 있으면 개별.
			mv.addObject("d_diary", d_diary); 
			
			// 일반, 갤러리 구분
			mv.addObject("type", type);
			mv.addObject("date_opt", date_opt);
			
			mv.setViewName("view/diary/diary_content");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// 일기 삭제 (전송)
	@RequestMapping("/diary_deletePro")
	public ModelAndView diary_deletePro (HttpServletRequest req, ModelAndView mv, int num, String email, String pageNum,
			String d_diary, String chk_d_diary, String opt, String search) {
		HttpSession session = req.getSession();
		
		if (d_diary != null || d_diary != "") {chk_d_diary = d_diary;}
		
		System.out.println("####deletePro####\n다이어리: "+d_diary+"\n검색어: "+search+"\n옵션: "+opt+
				"\n번호: "+num+"\n페이지번호: "+pageNum+"\n다이어리 구분 (전체[null] or 개별[not null]): "+chk_d_diary);
		
		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			int check = diPro.deleteDiary(num, email);
			System.out.println("###Diary_Delete###\n삭제여부 (1 or 0): " + check);
			mv.addObject("check", check);
			
			// 공용 (돌아갈 페이지 구분에 쓰임. content로)
			mv.addObject("pageNum", pageNum);
					
			// 전체, 개별 구분 (d_diary)  [수정, 삭제 후 돌아갈 페이지 구분에 쓰임] 
			mv.addObject("d_diary", d_diary); 
			mv.addObject("chk_d_diary", chk_d_diary);
			
			// 검색어 구분 (돌아갈 페이지 구분에 쓰임)
			mv.addObject("opt", opt);
			mv.addObject("search", search);
			
			mv.setViewName("view/diary/diary_deletePro");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// 일기 수정
	@RequestMapping("/diary_update")
	public ModelAndView diary_update (HttpServletRequest req, ModelAndView mv, int num, DiaryDataBean diary, String email, 
			String pageNum, String d_diary, String opt, String search, String not_in_d_diary, String chk_d_diary) {
		HttpSession session = req.getSession();
		List d_diarylist = null;
		
		if (d_diary != null || d_diary != "") {chk_d_diary = d_diary;}
		
		System.out.println("####update####\n다이어리: "+d_diary+"\n검색어: "+search+"\n옵션: "+opt+
				"\n번호: "+num+"\n페이지번호: "+pageNum+"\n다이어리 구분 (전체[null] or 개별[not null]): "+chk_d_diary);
		
		
		// 접속 제한
		if (session.getAttribute("s_email") == null) {
			mv.setViewName("index");
		} else if (session.getAttribute("s_email").equals(email)) {
			d_diarylist = diPro.getDiarylist(email, not_in_d_diary);
			diary = diPro.getDiary(num, email);
			mv.addObject("num", num);
			mv.addObject("pageNum", pageNum);
			
			mv.addObject("diary", diary);
			mv.addObject("d_diarylist", d_diarylist);
			
			// 전체, 개별 구분 (d_diary)  [수정, 삭제 후 돌아갈 페이지 구분에 쓰임] 
			mv.addObject("d_diary", d_diary); 
			mv.addObject("chk_d_diary", chk_d_diary);
			
			// 검색어 구분 (돌아갈 페이지 구분에 쓰임)
			mv.addObject("opt", opt);
			mv.addObject("search", search);
			
			mv.setViewName("view/diary/diary_update");
		} else {
			mv.setViewName("index");
		}
		
		return mv;
	}
	
	// 일기 수정 (전송)
	@RequestMapping("/diary_updatePro")
	public ModelAndView diary_updatePro (MultipartHttpServletRequest req, ModelAndView mv, int num, String email, String chk_d_diary,
			String user_name, String d_date, String subject, String content, String pageNum, String d_diary, String opt, String search) throws Throwable { 
		//HttpSession session = req.getSession();
		DiaryDataBean diary = new DiaryDataBean();
		
		if (chk_d_diary == "" || chk_d_diary.equals("")) {chk_d_diary = null;}
		
		System.out.println("####updatePro####\n다이어리: "+d_diary+"\n검색어: "+search+"\n옵션: "+opt+
				"\n번호: "+num+"\n페이지번호: "+pageNum+"\n다이어리 구분 (전체[null] or 개별[not null]): "+chk_d_diary);
		
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
		
		System.out.println("#####[사진-update]#####\n"+"일기 수정 이미지1: "+filename1+"\n"+"일기 수정 이미지2: "+filename2+"\n"+"일기 수정 이미지3: "+filename3+"\n"
				+"일기 수정 이미지4: "+filename4+"\n"+"일기 수정 이미지5: "+filename5+"\n"+"일기 수정 이미지6: "+filename6+"\n"+"###############");
		
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
			System.out.println("확인) 업로드 경로: " + uploadPath);
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
		
		// 파일 타입 체크
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
		    
			// 공용 (돌아갈 페이지 구분에 쓰임. content로)
			mv.addObject("pageNum", pageNum);
			mv.addObject("num", num);
			
			// 전체, 개별 구분 (d_diary)  [수정, 삭제 후 돌아갈 페이지 구분에 쓰임] 
			mv.addObject("d_diary", d_diary); 
			mv.addObject("chk_d_diary", chk_d_diary);
			
			// 검색어 구분 (돌아갈 페이지 구분에 쓰임)
			mv.addObject("opt", opt);
			mv.addObject("search", search);

			mv.setViewName("view/diary/diary_updatePro");
		}
	
		return mv;
	}
	
	// 사진첩
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
	    
	    System.out.println("날짜 옵션 (갤러리): " + date_opt);
	    
	    // 갤러리
	    if (date_opt == null) {
		    count = diPro.getGalleryCount(email);
			if (count > 0) {
			    date_list = diPro.getGalleryDate(email); // 일기 날짜 리스트
				gallery_list = diPro.getGallery(startRow, endRow, email);
				System.out.println("Count (갤러리): " + count);
			}
	    }

	    // 갤러리 - 날짜별 검색
	    if (date_opt != null) {
		    count = diPro.getGalleryCount(email, date_opt);
			if (count > 0) {
			    date_list = diPro.getGalleryDate(email, date_opt); // 일기 날짜 리스트 (검색한 날짜 제외)
				gallery_list = diPro.getGallery(startRow, endRow, email, date_opt);
				System.out.println("Count (갤러리 - 날짜별): " + count);
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
