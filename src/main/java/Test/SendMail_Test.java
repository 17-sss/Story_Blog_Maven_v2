package Test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

// sendmail 성공한거! - 샘플
public class SendMail_Test {
	//@RequestMapping(value="/findpwd_mail")
	/*public void findpwd_mail(HttpServletRequest request) throws AddressException, MessagingException{
	     
	    String subject = (String) request.getParameter("subject"); // 메일 제목
	    String message = (String) request.getParameter("message"); // 메일 내용
	     
	    // SMTP 서버 설정
	    final String host = "smtp.gmail.com"; // 사용할 smtp host, naver라면 smtp.naver.com
	    final String accountId = "#사용자 아이디#";
	    final String accountPwd = "#사용자 비밀번호#";
	    final int port = 465; // SMTP 포트
	     
	    String receiver = "#받는사람 이메일#"; // 받는사람 이메일
	    String sender = "#보내는사람 이메일#"; // 보내는사람 이메일
	     
	    // Property 정보 생성
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.trust", host);
	     
	    // 사용자 인증
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
	            return new javax.mail.PasswordAuthentication(accountId, accountPwd);
	        }
	    });
	    session.setDebug(true);
	     
	    Message mimeMessage = new MimeMessage(session); //MimeMesage 생성
	    mimeMessage.setFrom(new InternetAddress(sender)); // 보내는 EMAIL (정확히 적어야 SMTP 서버에서 인증 실패되지 않음)
	    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); 
	     
	    // Message Setting
	    mimeMessage.setSubject(subject);
	    mimeMessage.setText(message);
	    Transport.send(mimeMessage); // Transfer
	}
	*/
	
	// 임시 비번 생성
	public String temp_pwd (String tempPassword) {
		for (int i = 0; i < 8; i++) {
			int random_Val = (int) (Math.random() * 62);
			
			if (random_Val < 10) {
				tempPassword += random_Val;
			} else if (random_Val > 35) {
				tempPassword += (char) (random_Val + 61);
			} else {
				tempPassword += (char) (random_Val + 55);
			}
		}
		return tempPassword;
	}
	
	public static void main(String[] args) {
		String temppwd_test = "";
		SendMail_Test st = new SendMail_Test();
		temppwd_test = st.temp_pwd(temppwd_test);
		
		System.out.println(temppwd_test);

	}
}
