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

// sendmail �����Ѱ�! - ����
public class SendMail_Test {
	//@RequestMapping(value="/findpwd_mail")
	/*public void findpwd_mail(HttpServletRequest request) throws AddressException, MessagingException{
	     
	    String subject = (String) request.getParameter("subject"); // ���� ����
	    String message = (String) request.getParameter("message"); // ���� ����
	     
	    // SMTP ���� ����
	    final String host = "smtp.gmail.com"; // ����� smtp host, naver��� smtp.naver.com
	    final String accountId = "#����� ���̵�#";
	    final String accountPwd = "#����� ��й�ȣ#";
	    final int port = 465; // SMTP ��Ʈ
	     
	    String receiver = "#�޴»�� �̸���#"; // �޴»�� �̸���
	    String sender = "#�����»�� �̸���#"; // �����»�� �̸���
	     
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
	    mimeMessage.setText(message);
	    Transport.send(mimeMessage); // Transfer
	}
	*/
	
	// �ӽ� ��� ����
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
