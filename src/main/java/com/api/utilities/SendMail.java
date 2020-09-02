package com.api.utilities; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



@SuppressWarnings("all")

public class SendMail extends Utility{
	static ConfiguratorSupport config = new ConfiguratorSupport("config.properties");
	
	

	public static void attachReportsToEmail() throws Exception {
		
		System.out.println("Sending an email....");

		String strBrowser = config.getProperty("browserType");
		String strReportsFolder = "";

		if (strBrowser.equalsIgnoreCase("ie")) 
		{
			strReportsFolder = "IE";

		} 
		else if (strBrowser.equalsIgnoreCase("Chrome")) 
		{
			strReportsFolder = "Chrome";

		}

		
		String strZipFilePath = "Results\\Reports.zip";	
		
		String toAddress = config.getProperty("to");
		String ccAddress = config.getProperty("cc");
		String bccAddress = config.getProperty("bcc");
		
		String[] to = null, cc = null, bcc = null;
		
		if (!toAddress.isEmpty()) {
			to = toAddress.split(",");	
		}
		
		if (!ccAddress.isEmpty()) {
			cc =ccAddress.split(",");
		}
		
		if (!bccAddress.trim().isEmpty()) {
			bcc =bccAddress.split(",");
		}
		
		
		String subject = config.getProperty("EmailSubject");   
		String emailBodyMessage = config.getProperty("BodyMessage");
		String footer = config.getProperty("BodyFooterMessage");
		
		/*
		String subject = Utility.getProperty("EmailSubject");
		String emailBodyMessage = Utility.getProperty("BodyMessage");
		String footer = Utility.getProperty("BodyFooterMessage");
		*/
		
		sendMail(config.getProperty("MailUserName"), config.getProperty("MailpassWord"),
                "outlook.standard.com",
                        "587",
                        "true", "true", true, "javax.net.ssl.SSLSocketFactory", "true", to, cc, bcc, subject , emailBodyMessage ,footer,
                        strZipFilePath ,
                        "Reports.zip");
		
		
	}

	public static boolean sendMail(final String userName, String passWord,
			String host, String port, String starttls, String auth,
			boolean debug, String socketFactoryClass, String fallback,
			String[] to, String[] cc, String[] bcc, String subject,
			String text, String footer, String attachmentPath, String attachmentName) {

		//		String strReportsFolder = "Firefox";
		/**/

		Properties props = new Properties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if (!"".equals(port))

			props.put("mail.smtp.port", port);

		if (!"".equals(starttls))

			props.put("mail.smtp.starttls.enable", starttls);

		props.put("mail.smtp.auth", auth);


		if (debug) {

			props.put("mail.smtp.debug", "true");

		} else {

			props.put("mail.smtp.debug", "false");

		}


		if (!"".equals(port))

			props.put("mail.smtp.socketFactory.port", port);

		if (!"".equals(socketFactoryClass))

			props.put("mail.smtp.socketFactory.class", socketFactoryClass);

		if (!"".equals(fallback))

			props.put("mail.smtp.socketFactory.fallback", fallback);

		props.put("mail.smtp.ssl.trust", "outlook.standard.com");
		props.put("mail.mime.address.strict", "false");
		

		try

		{

			Session session = Session.getDefaultInstance(props, null);
			

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);

			msg.setSubject(subject);

			MimeBodyPart messageBodyPart1 = new MimeBodyPart();
			//StringBuffer table3 = callhtml();
			//messageBodyPart1.setContent(table3.toString(), "text/html");

			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			
			messageBodyPart.setContent(text, "text/html; charset=utf-8");
			messageBodyPart1.setContent(footer, "text/html; charset=utf-8");
			
			DataSource source = new FileDataSource(attachmentPath);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(messageBodyPart1);
			msg.setContent(multipart);
			
			
			msg.setFrom(new InternetAddress(config.getProperty("FromAddresses")));
			

			if (to != null) {
				try {
					for (int i = 0; i < to.length; i++) {

						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
								to[i]));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (cc != null) {
				try {
					for (int i = 0; i < cc.length; i++) {

						msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
								cc[i]));

					}
				} catch (Exception e) {
				e.printStackTrace();	
				}
			}
			
			if (bcc != null) {
				try {
					for (int i = 0; i < bcc.length; i++) {

						msg.addRecipient(Message.RecipientType.BCC,
								new InternetAddress(bcc[i]));

					}

				} catch (Exception e) {
					e.printStackTrace();	
				}
			}



			msg.saveChanges();
			
			Transport transport = session.getTransport("smtp");
			
			transport.connect(host, userName, passWord);
			
			transport.sendMessage(msg, msg.getAllRecipients());

			transport.close();

			return true;
			
		}

		catch (Exception mex)

		{

	//		logger.debug(mex.getMessage());
			Utility.fnLogging(mex.getMessage());

			return false;

		}

	}

}
