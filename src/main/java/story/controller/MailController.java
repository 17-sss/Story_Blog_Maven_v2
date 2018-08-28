package story.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import story.db.UserDBMyBatis;
import story.service.StoryMail;

@Controller
@RequestMapping("/mail")
public class MailController {
	StoryMail sm = new StoryMail();
	UserDBMyBatis uspro = UserDBMyBatis.getInstance();
	
	@RequestMapping("/findpwd")
	public ModelAndView findpwd(ModelAndView mv) {
		mv.setViewName("findpwd");
		
		return mv; 
	}

	@RequestMapping(value="/findpwdPro")
	public ModelAndView findpwdPro(ModelAndView mv, String to_email) throws AddressException, MessagingException{
	    String subject = "[Story Blog] "+ to_email + "님의 임시 비밀번호 입니다.";  //제목
	    String temp_pwd = ""; //임시 비밀번호
	    
	    // 임시 비밀번호 설정
	    temp_pwd = sm.temp_pwd(temp_pwd);
	    
	    // SMTP 서버 설정
	    final String host = "smtp.gmail.com"; // 사용할 SMTP host
	    final String accountId = "아이디"; //구글에서  "보안 수준이 낮은 앱 허용" - 사용함 으로 바꾸기
	    final String accountPwd = "비밀번호";
	    final int port = 465; // SMTP 포트
	    
	    String sender = accountId; // 보내는사람 이메일
	    String receiver = to_email; // 받는사람 이메일
	    
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
	    mimeMessage.setText(temp_pwd);
	    
	    Transport.send(mimeMessage); // Transfer
		
	    // 페이지 뷰, 유저 비밀번호 -> 임시 비밀번호로 변경
	    int chk = 0;
	    chk = uspro.updatePwd(to_email, temp_pwd);
	    System.out.println("[MailController] '"+to_email+"'의 비밀번호 설정여부: "+chk);
	    
	    mv.addObject("email", to_email);
	    mv.setViewName("findpwdPro");
	    
	    return mv;
	}
	
} 