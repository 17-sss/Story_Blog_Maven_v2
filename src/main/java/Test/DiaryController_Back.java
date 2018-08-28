package Test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import story.db.DiaryDBMyBatis;

/*@Controller
@RequestMapping("/diary")*/
public class DiaryController_Back {
	DiaryDBMyBatis dbPro = DiaryDBMyBatis.getInstance();
	
	
	/*@RequestMapping(value = "/diary_board", produces = "application/text; charset=euckr")*/
	public ModelAndView diary_board (HttpServletRequest req, ModelAndView mv, String pageNum, String email, String d_diary, String search, String opt) throws Exception {  
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
	    
	    // 일기장 개별 전용
	    int count2 = 0;
	    int number2 = 0;
	    
	    d_diarylist = dbPro.getDiarylist(email); // 일기장 목록 생성용
	    
	    // 전체 보기
	    if (d_diary == null) {
		    count = dbPro.getDiaryCount(email);
			if (count > 0) {
				d_list = dbPro.getDiaries(startRow, endRow, email);
				System.out.println("############\n"+"Count (전체): " + count + "\n############");
			} 
	    }  
	    // 전체 보기 - 검색
	    if (search != null && d_diary == null) {
	   
		    
			if (opt.equals("SC")) {
				count = dbPro.getSearchDiaryCount_SC(email, "%" + search + "%");
				if (count > 0) {
					d_list = dbPro.searchDiary_SC(startRow, endRow, email, "%" + search + "%");
					System.out.println("[SC] Count: " + count);
				}
			} 
		    if (opt.equals("S")) {
				count = dbPro.getSearchDiaryCount_S(email, "%" + search + "%");
				if (count > 0) {
					d_list = dbPro.searchDiary_S(startRow, endRow, email, "%" + search + "%");
					System.out.println("[S] Count: " + count);
				}
			}
		    if (opt.equals("C")) {
				count = dbPro.getSearchDiaryCount_C(email, "%" + search + "%");
				if (count > 0) {
					d_list = dbPro.searchDiary_C(startRow, endRow, email, "%" + search + "%");
					System.out.println("[C] Count: " + count);
				}
			}
		    System.out.println("~~~~~~~~~~~~\n(전체) 검색어: "+search+"\n일기장: "+d_diary+"\n이메일: " +email+"\n~~~~~~~~~~~~");
	    }   
	    ///// end. 전체 보기
	    
	    // 일기장 개별
		if (d_diary != null) {
			count2 = dbPro.getDiaryCount(email, d_diary);
			if (count2 > 0) {
				d_list = dbPro.getDiaries(startRow, endRow, email, d_diary);
				System.out.println("############\n"+"Count2 (개별): "+ count2 + "\nDiary: "+d_diary+"\n############");
			}
		}
		
		// 일기장 개별 - 검색
	    if (search != null && d_diary != null) {
	    	
		    if (opt.equals("SC")) {
				count2 = dbPro.getSearchDiaryCount_D_SC(email, d_diary, "%"+search+"%");
				if (count2 > 0) {
					d_list = dbPro.searchDiary_D_SC(startRow, endRow, email, d_diary, "%"+search+"%");
					System.out.println("[SC] Count2: " + count2);
				}
			} 
		    if (opt.equals("S")) {
				count2 = dbPro.getSearchDiaryCount_D_S(email, d_diary, "%"+search+"%");
				if (count2 > 0) {
					d_list = dbPro.searchDiary_D_S(startRow, endRow, email, d_diary, "%"+search +"%");
					System.out.println("[S] Count2: " + count2);
				}
			}
		    if (opt.equals("C")) {
				count2 = dbPro.getSearchDiaryCount_D_C(email, d_diary, "%"+search+"%");
				if (count2 > 0) {
					d_list = dbPro.searchDiary_D_C(startRow, endRow, email, d_diary, "%"+search+"%");
					System.out.println("[C] Count2: " + count2);
				}
			}
			
		    System.out.println("~~~~~~~~~~~~\n(개별) 검색어: "+search+"\n일기장: "+d_diary+"\n이메일: " +email+"\n~~~~~~~~~~~~");
	    }  
	    ///// end. 일기장 개별
	    
	    if (search != null) {
	    	System.out.println("search: " +search);
	    }
	    if (d_diary != null) {
	    	System.out.println("d_diary: "+d_diary);
	    }
	   
		
		number = count - (currentPage - 1) * pageSize; // 전체
	    number2 = count2 - (currentPage - 1) * pageSize; // 개별
	    
	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
	    
	    int endPage = startPage + bottomLine - 1; // 전체 
	    int endPage2 = startPage + bottomLine - 1; // 개별

	    int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); // 전체
	    int pageCount2 = count2 / pageSize + (count2 % pageSize == 0 ? 0 : 1); // 개별
	    
	    if (endPage > pageCount) { endPage = pageCount; } // 전체
	    if (endPage2 > pageCount2) { endPage2 = pageCount2; } // 개별
	    
	    // 전체 보기 전용
	    mv.addObject("count", count);
	    mv.addObject("number", number);
	    mv.addObject("pageCount", pageCount);
	    mv.addObject("endPage", endPage);
	    
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
			mv.setViewName("view/diary/diary_board");
		}
		
		return mv;
	}
}
