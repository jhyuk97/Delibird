package Admin;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	
	PasswordAuthentication pa;
	
	public SMTPAuthenticator(String mail, String pwd) {
		 
        pa = new PasswordAuthentication(mail, pwd);

    }
	
	@Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}
