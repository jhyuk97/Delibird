package Admin;

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	String fromName;
	String fromEmail;
	
	public SendEmail(String fromName, String fromEmail) {
		this.fromName = fromName;
		this.fromEmail = fromEmail;
	}

	public void goEmail(){
		try {
			
			Properties props = new Properties();
			
			props.put("mail.smtp.starttls.enable", "true");    
	        props.put("mail.smtp.host", "smtp.gmail.com");
 
            props.put("mail.smtp.port", "587");
 
            props.put("mail.smtp.auth", "true");
            
            Authenticator myauth = new SMTPAuthenticator("jhyuk57@gmail.com", "blue2010");
            
            Session sess = Session.getInstance(props, myauth);
            
            InternetAddress addr = new InternetAddress();
            
            addr.setPersonal("최재혁", "UTF-8");
 
            addr.setAddress("jhyuk57@gmail.com");
            
            Message msg = new MimeMessage(sess);
            
            msg.setFrom(addr);

 
            msg.setSubject(MimeUtility.encodeText("DeliBird 매장 신청 결과입니다.", "utf-8", "B"));
 
            msg.setContent("귀하께서는 아쉽지만 신청에서 떨어지셨습니다.", "text/html;charset=utf-8");
 
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(fromEmail));

            Transport.send(msg);
 
	}catch(Exception e) {
		e.getMessage();
	}
	}
}
