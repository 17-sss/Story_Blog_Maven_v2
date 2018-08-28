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
	    
	    // ��ü����
	    int count = 0; 
	    int number = 0;
	    
	    // �ϱ��� ���� ����
	    int count2 = 0;
	    int number2 = 0;
	    
	    d_diarylist = dbPro.getDiarylist(email); // �ϱ��� ��� ������
	    
	    // ��ü ����
	    if (d_diary == null) {
		    count = dbPro.getDiaryCount(email);
			if (count > 0) {
				d_list = dbPro.getDiaries(startRow, endRow, email);
				System.out.println("############\n"+"Count (��ü): " + count + "\n############");
			} 
	    }  
	    // ��ü ���� - �˻�
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
		    System.out.println("~~~~~~~~~~~~\n(��ü) �˻���: "+search+"\n�ϱ���: "+d_diary+"\n�̸���: " +email+"\n~~~~~~~~~~~~");
	    }   
	    ///// end. ��ü ����
	    
	    // �ϱ��� ����
		if (d_diary != null) {
			count2 = dbPro.getDiaryCount(email, d_diary);
			if (count2 > 0) {
				d_list = dbPro.getDiaries(startRow, endRow, email, d_diary);
				System.out.println("############\n"+"Count2 (����): "+ count2 + "\nDiary: "+d_diary+"\n############");
			}
		}
		
		// �ϱ��� ���� - �˻�
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
			
		    System.out.println("~~~~~~~~~~~~\n(����) �˻���: "+search+"\n�ϱ���: "+d_diary+"\n�̸���: " +email+"\n~~~~~~~~~~~~");
	    }  
	    ///// end. �ϱ��� ����
	    
	    if (search != null) {
	    	System.out.println("search: " +search);
	    }
	    if (d_diary != null) {
	    	System.out.println("d_diary: "+d_diary);
	    }
	   
		
		number = count - (currentPage - 1) * pageSize; // ��ü
	    number2 = count2 - (currentPage - 1) * pageSize; // ����
	    
	    int bottomLine = 3;
	    int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
	    
	    int endPage = startPage + bottomLine - 1; // ��ü 
	    int endPage2 = startPage + bottomLine - 1; // ����

	    int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); // ��ü
	    int pageCount2 = count2 / pageSize + (count2 % pageSize == 0 ? 0 : 1); // ����
	    
	    if (endPage > pageCount) { endPage = pageCount; } // ��ü
	    if (endPage2 > pageCount2) { endPage2 = pageCount2; } // ����
	    
	    // ��ü ���� ����
	    mv.addObject("count", count);
	    mv.addObject("number", number);
	    mv.addObject("pageCount", pageCount);
	    mv.addObject("endPage", endPage);
	    
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
			mv.setViewName("view/diary/diary_board");
		}
		
		return mv;
	}
}
