package Test;

import java.io.UnsupportedEncodingException;

public class Encoding_Test {
	/*
	 * String encodeVal1 = java.net.URLEncoder.encode(d_diary, "UTF-8"); String
	 * d_diary_decode = java.net.URLEncoder.encode(encodeVal1, "EUC-KR"); String
	 * encodeVal2 = java.net.URLEncoder.encode(search, "UTF-8"); String
	 * search_decode = java.net.URLEncoder.encode(encodeVal2, "EUC-KR");
	 * 
	 * d_diary = d_diary_decode; search = search_decode;
	 */
	public static void main(String[] args) {
		//String[] d_diary = {"가", "나", "다라마", "abc", "abcd"};
		String d_diary = "삘링";		
		try {
			System.out.println("utf-8 -> euc-kr: " + new String(d_diary.getBytes("utf-8"), "euc-kr"));
			System.out.println("euc-kr -> euc-kr: " + new String(d_diary.getBytes("euc-kr"), "euc-kr") + "\n");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		

		String[] charSet = { "utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949", "MS949" };

		for (int i = 0; i < charSet.length; i++) {
			for (int j = 0; j < charSet.length; j++) {
				try {
					System.out.println("[" + charSet[i] + "," + charSet[j] + "] = "
							+ new String(d_diary.getBytes(charSet[i]), charSet[j]));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		// end. 인코딩 테스트
	}
	
	public void Test2() {
		// Story_Blog_Maven_v2
		// DiaryController - diary_board 검색
		/*try {
			String search_diary_text = search;
			search_diary_text = java.net.URLDecoder.decode(search_diary_text, "EUC-KR");
			System.out.println("Test:" + search_diary_text);
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("error");
		}*/
		
		// diary_board.jsp (script)
		/*<script type="text/javascript">
		 function goEncode() {
			var form = document.search_diary;
			var uri = "${pageContext.request.contextPath}/diary/diary_board?email=${s_email}&search=${search}&opt=${opt}&d_diary=" + encodeURI(encodeURIComponent('${d_diary}'));
			form.submit(uri);
		} 
		 $(document).ready(
				function() {
					//검색 클릭
					$("#btn_search_diary").click(
						function() {
							// search_text 값을 인코딩하여 다시 넣는다.
							$("#search_diary_text").val(
									encodeURI($("#search_diary_text").val(),"EUC-KR"));
							$("#search_diary").attr("action", "${pageContext.request.contextPath}/diary/diary_board");
							$("#search_diary").submit();
						});
				}); 
		</script>*/
		
		// diary_board.jsp (form)
		/*<!-- 일기장 내에서 검색 -->
		<form class="w3-white" method="get" name="search_diary" id="search_diary">
			<div class="w3-center w3-margin-top w3-margin-bottom font-nanum-coding-c">
				<select class="w3-border" style="display: inline-block; font-size: 10pt;" name="opt">
					<option class="w3-text-gray" disabled>Search..</option>
					<option value="SC" selected>제목 + 내용</option>
					<option value="S">제목</option>
					<option value="C">내용</option>
				</select> 
				<input type="text" class="w3-border" placeholder="'${d_diary}'에서 검색" id="search_diary_text" name="search" style="padding: 0px; font-size: 10pt;">
				<input type="hidden" value="${d_diary}" name="d_diary">
				<input type="hidden" value="${s_email}" name="email">
				<input type="button" class="w3-button w3-light-gray font-montserrat-c" value="Search" id="btn_search_diary" name="btn_search_diary"
				 style="display: inline-block; padding: 6px 9px; font-size: 8pt;">
			</div>
		</form>*/
	}
}
