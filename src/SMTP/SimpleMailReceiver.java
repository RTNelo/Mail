package SMTP;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.mail.*;

import model.Database;
import model.Mail.Type;
import model.MailAddress;

public class SimpleMailReceiver {
	Folder inbox;
	Store store;
	String name = "";
	String password = "";
	String host = "";
	
//	static class MyAuthenticator extends Authenticator{
//		String user = null;
//		String pass = "";
//		public MyAuthenticator(String user,String pass){
//			this.user = user;
//			this.pass = pass;
//		}
//		protected PasswordAuthentication getPasswordAuthentication(){
//			return new PasswordAuthentication(user, pass);
//		}
//	}
	/**
	 * Initialize the Receiver
	 * @param name
	 * @param password
	 * @param host
	 */
	public SimpleMailReceiver(String name, String password, String host){
		this.name = name;
		this.password = password;
		this.host = host;
	}
	
	/**
	 * Get all the mails in the mailbox
	 * @return
	 * @throws MessagingException
	 */
	public Message [] getMail(MailAddress mailAddress) throws MessagingException{
		Properties prop = new Properties();
		prop.put("mail.pop3.host", host);
		MyAuthenticator auth = new MyAuthenticator(name,password);
		Session mailSession = Session.getDefaultInstance(prop,auth);
		store = mailSession.getStore("pop3");
		store.connect(host,name,password);
		inbox = store.getDefaultFolder().getFolder("inbox");
		inbox.open(Folder.READ_WRITE);
		Message [] msg = inbox.getMessages();
		//�յ����ʼ������ݿ�
		Database dataBase = Database.getDefaultDatabase();
		for(int i=0;i<msg.length;i++){
			try {
				dataBase.addMail(Converter.MessageToDataBaseVersion(mailAddress, msg[i], Type.RECV));
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return msg;
	}
}
