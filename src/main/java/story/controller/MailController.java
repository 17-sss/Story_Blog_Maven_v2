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
	    String subject = "[Story Blog] "+ to_email + "���� �ӽ� ��й�ȣ �Դϴ�.";  //����
	    String temp_pwd = ""; //�ӽ� ��й�ȣ
	    
	    // �ӽ� ��й�ȣ ����
	    temp_pwd = sm.temp_pwd(temp_pwd);
	    
	    // SMTP ���� ����
	    final String host = "smtp.gmail.com"; // ����� SMTP host
	    final String accountId = "���̵�"; //���ۿ���  "���� ������ ���� �� ���" - ����� ���� �ٲٱ�
	    final String accountPwd = "��й�ȣ";
	    final int port = 465; // SMTP ��Ʈ
	    
	    String sender = accountId; // �����»�� �̸���
	    String receiver = to_email; // �޴»�� �̸���
	    
	    // Property ���� ����
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.trust", host);
	     
	    // ����� ����
	    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	        protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
	            return new javax.mail.PasswordAuthentication(accountId, accountPwd);
	        }
	    });
	    session.setDebug(true);
	     
	    Message mimeMessage = new MimeMessage(session); //MimeMesage ����
	    mimeMessage.setFrom(new InternetAddress(sender)); // ������ EMAIL (��Ȯ�� ����� SMTP �������� ���� ���е��� ����)
	    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); 
	     
	    // Message Setting
	    mimeMessage.setSubject(subject);
	    mimeMessage.setText(temp_pwd);
	    
	    Transport.send(mimeMessage); // Transfer
		
	    // ������ ��, ���� ��й�ȣ -> �ӽ� ��й�ȣ�� ����
	    int chk = 0;
	    chk = uspro.updatePwd(to_email, temp_pwd);
	    System.out.println("[MailController] '"+to_email+"'�� ��й�ȣ ��������: "+chk);
	    
	    mv.addObject("email", to_email);
	    mv.setViewName("findpwdPro");
	    
	    return mv;
	}
	
} 