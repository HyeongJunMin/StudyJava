package sendMail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static void main(String[] args) {

		String host = "smtp.naver.com";
		final String user = "alsgudwns007";
		final String password = "dywjdalrud";

		String to = "zionadd@naver.com";
//		String to = "01068889859@hanmail.net";

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("Java Mail Test With Json 2");

			// Text
			message.setText("Simple mail test..");
			
			// Json
			String json = "{\r\n" + 
					"\r\n" + 
					"    \"name\": \"moogii\",\r\n" + 
					"\r\n" + 
					"    \"age\": 5,\r\n" + 
					"\r\n" + 
					"    \"isProgrammer\": true,\r\n" + 
					"\r\n" + 
					"    \"skills\": [\r\n" + 
					"\r\n" + 
					"        {\r\n" + 
					"\r\n" + 
					"            \"language\": \"JAVA\",\r\n" + 
					"\r\n" + 
					"            \"level\": 1\r\n" + 
					"\r\n" + 
					"        },\r\n" + 
					"\r\n" + 
					"        {\r\n" + 
					"\r\n" + 
					"            \"language\": \"Python\",\r\n" + 
					"\r\n" + 
					"            \"level\": 2\r\n" + 
					"\r\n" + 
					"        }\r\n" + 
					"\r\n" + 
					"    ],\r\n" + 
					"\r\n" + 
					"    \"workingDays\": [\"월\", \"화\", \"수\", \"목\", \"금\", \"금\", \"금\"]\r\n" + 
					"\r\n" + 
					"}";
			message.setContent(json, "text/json");
			
			message.setFileName("jsonTest2.json");

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}