package SMTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import model.*;
import model.Mail.Type;

public class SimpleMailSender {
	
	public static boolean sendMail(MailAddress mailAddress, MailSenderInfo senderInfo){
		boolean flag = false;
		
		// �ж��Ƿ���Ҫ�����֤
		MyAuthenticator authenticator = null;
		Properties props = senderInfo.getProperties();
		props.setProperty("mail.smtp.auth", "true");
		HostInfo info;
		try {
			info = Database.getDefaultDatabase().getHostBySuffix(util.GetSuffix.getSuffixOfMailAddress(mailAddress.getAccount()));
			props.setProperty("mail.smtp.host", info.getSmtpHost());
			props.setProperty("mail.smtp.port", info.getSmtpPort() + "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(senderInfo.isValidate()){
			// �����Ҫ�����֤���򴴽�һ��������֤��   
			authenticator = new MyAuthenticator(senderInfo.getUserName(), senderInfo.getPassword());
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(props, authenticator);
		
		try {
			// ����session����һ���ʼ���Ϣ
			Message sendMailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(senderInfo.getFromAddress());
			// �����ʼ���Ϣ�ķ�����
			sendMailMessage.setFrom(from);
			
			// �����ʼ������ߵ�ַ
			String[] tos = senderInfo.getToAddress();
			if(tos != null && tos.length != 0){
				InternetAddress[] to = new InternetAddress[tos.length];
				// �����ʼ���Ϣ�ķ�����
				for (int i = 0; i < tos.length; i++) {
					to[i] = new InternetAddress(tos[i]);
				}
				sendMailMessage.setRecipients(Message.RecipientType.TO, to);
			}
			
			// �����ʼ������ߵ�ַ
			String[] toCCs = senderInfo.getToCarbonCopyAddress();
			if(toCCs != null && toCCs.length != 0){
				InternetAddress[] toCC = new InternetAddress[toCCs.length];
				// �����ʼ���Ϣ�ķ�����
				for (int i = 0; i < toCCs.length; i++) {
					toCC[i] = new InternetAddress(toCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.CC, toCC);
			}
			
			// �����ʼ������ߵ�ַ
			String[] toBCCs = senderInfo.getToBlindCarbonCopyAddress();
			if(toBCCs != null && toBCCs.length != 0){
				InternetAddress[] toBCC = new InternetAddress[toBCCs.length];
				for (int i = 0; i < toBCCs.length; i++) {
					toBCC[i] = new InternetAddress(toBCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.BCC, toBCC);
			}
			
			// �����ʼ�����
			sendMailMessage.setSubject(MimeUtility.encodeText(senderInfo.getSubject(),"utf-8","B"));
			// �����ʼ�����
			//sendMailMessage.setText(senderInfo.getContent());
			Multipart multipart = new MimeMultipart();
			// �ʼ��ı�����
			if(senderInfo.getContent() != null && !"".equals(senderInfo.getContent())){
				BodyPart part = new MimeBodyPart();
				part.setContent(senderInfo.getContent(), "text/plain;charset=utf-8");//�����ʼ��ı�����
				multipart.addBodyPart(part);
			}
			
			// ����
			String attachFileNames[] = senderInfo.getAttachFileNames();
			int leng = attachFileNames == null ? 0 : attachFileNames.length;
			for (int i = 0; i < leng; i++) {
				BodyPart part = new MimeBodyPart();
				// �����ļ�����ȡ����Դ
				DataSource dataSource = new FileDataSource(attachFileNames[i]);
				DataHandler dataHandler = new DataHandler(dataSource);
				// �õ�������������BodyPart
				part.setDataHandler(dataHandler);
				// �õ��ļ���ͬ������BodyPart
				part.setFileName(MimeUtility.encodeText(dataSource.getName()));
				multipart.addBodyPart(part);
			}
			
			sendMailMessage.setContent(multipart);
			// �����ʼ����͵�ʱ��
			sendMailMessage.setSentDate(new Date());
			// �����ʼ�
			//Transport.send(sendMailMessage);
			Transport transport = sendMailSession.getTransport("smtp");
			transport.connect(senderInfo.getUserName(), senderInfo.getPassword());
			transport.send(sendMailMessage,sendMailMessage.getAllRecipients());
			// �ر�transport
			transport.close();
			//��������ʼ������ݿ�
			Database dataBase = Database.getDefaultDatabase();
			dataBase.addMail(Converter.MessageToDataBaseVersion(mailAddress, sendMailMessage, Type.SENT));
			
			flag = true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public static boolean sendHtmlMail(MailAddress mailAddress, MailSenderInfo senderInfo){
		boolean flag = false;
		
		// �ж��Ƿ���Ҫ�����֤
		MyAuthenticator authenticator = null;
		Properties props = senderInfo.getProperties();
		if(senderInfo.isValidate()){
			// �����Ҫ�����֤���򴴽�һ��������֤��   
			authenticator = new MyAuthenticator(senderInfo.getUserName(), senderInfo.getPassword());
		}
		// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session
		Session sendMailSession = Session.getDefaultInstance(props, authenticator);
		
		try {
			// ����session����һ���ʼ���Ϣ
			Message sendMailMessage = new MimeMessage(sendMailSession);
			// �����ʼ������ߵ�ַ
			Address from = new InternetAddress(senderInfo.getFromAddress());
			// �����ʼ���Ϣ�ķ�����
			sendMailMessage.setFrom(from);
			
			// �����ʼ������ߵ�ַ
			String[] tos = senderInfo.getToAddress();
			if(tos != null && tos.length != 0){
				InternetAddress[] to = new InternetAddress[tos.length];
				// �����ʼ���Ϣ�ķ�����
				for (int i = 0; i < tos.length; i++) {
					to[i] = new InternetAddress(tos[i]);
				}
				sendMailMessage.setRecipients(Message.RecipientType.TO, to);
			}
			
			// �����ʼ������ߵ�ַ
			String[] toCCs = senderInfo.getToCarbonCopyAddress();
			if(toCCs != null && toCCs.length != 0){
				InternetAddress[] toCC = new InternetAddress[toCCs.length];
				// �����ʼ���Ϣ�ķ�����
				for (int i = 0; i < toCCs.length; i++) {
					toCC[i] = new InternetAddress(toCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.CC, toCC);
			}
			
			// �����ʼ������ߵ�ַ
			String[] toBCCs = senderInfo.getToBlindCarbonCopyAddress();
			if(toBCCs != null && toBCCs.length != 0){
				InternetAddress[] toBCC = new InternetAddress[toBCCs.length];
				for (int i = 0; i < toBCCs.length; i++) {
					toBCC[i] = new InternetAddress(toBCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.BCC, toBCC);
			}
			
			// �����ʼ�����
			sendMailMessage.setSubject(MimeUtility.encodeText(senderInfo.getSubject(),"utf-8","B"));
			// �����ʼ�����
			//sendMailMessage.setText(senderInfo.getContent());
			Multipart multipart = new MimeMultipart();
			// �ʼ��ı�����
			if(senderInfo.getContent() != null && !"".equals(senderInfo.getContent())){
				BodyPart part = new MimeBodyPart();
				part.setContent(senderInfo.getContent(), "text/html;charset=utf-8");//�����ʼ��ı�����
				multipart.addBodyPart(part);
			}
			
			// ����
			String attachFileNames[] = senderInfo.getAttachFileNames();
			int leng = attachFileNames == null ? 0 : attachFileNames.length;
			for (int i = 0; i < leng; i++) {
				BodyPart part = new MimeBodyPart();
				// �����ļ�����ȡ����Դ
				DataSource dataSource = new FileDataSource(attachFileNames[i]);
				DataHandler dataHandler = new DataHandler(dataSource);
				// �õ�������������BodyPart
				part.setDataHandler(dataHandler);
				// �õ��ļ���ͬ������BodyPart
				part.setFileName(MimeUtility.encodeText(dataSource.getName()));
				multipart.addBodyPart(part);
			}
			
			sendMailMessage.setContent(multipart);
			// �����ʼ����͵�ʱ��
			sendMailMessage.setSentDate(new Date());
			// �����ʼ�
			//Transport.send(sendMailMessage);
			Transport transport = sendMailSession.getTransport("smtp");
			transport.connect(senderInfo.getUserName(), senderInfo.getPassword());
			transport.send(sendMailMessage,sendMailMessage.getAllRecipients());
			
			// �ر�transport
			transport.close();
			//��������ʼ������ݿ�
			Database dataBase = Database.getDefaultDatabase();
			dataBase.addMail(Converter.MessageToDataBaseVersion(mailAddress, sendMailMessage, Type.SENT));
			
			flag = true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
//	public static void main(String[] args) {
//		MailSenderInfo mailInfo = new MailSenderInfo();
//		mailInfo.setMailServerHost("smtp.qq.com");
//		mailInfo.setMailServerPort("25");
//		mailInfo.setValidate(true);
//		mailInfo.setUserName("********@qq.com");
//		mailInfo.setPassword("*********");// ������������
//		mailInfo.setFromAddress("598800639@qq.com");
//		String[] to = {"********@qq.com"};
//		mailInfo.setToAddress(to);
//		String[] toCC = {"**********@qq.com"};
//		mailInfo.setToCarbonCopyAddress(toCC);
//		String[] toBCC = {"*******@qq.com"};
//		mailInfo.setToBlindCarbonCopyAddress(toBCC);
//		mailInfo.setAttachFileNames(new String[]{""});
//		mailInfo.setSubject("����HTML�ʼ�");
//		String body = " ";
//		mailInfo.setContent(body);
//		// �������Ҫ�������ʼ�
//		System.out.println(SimpleMailSender.sendHtmlMail(mailInfo));//���������ʽ
//	}
}

