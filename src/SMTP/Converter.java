package SMTP;

import java.io.IOException;

import SMTP.*;

import javax.mail.*;

import model.*;
import model.Mail.Type;

public class Converter {
	public static Mail MessageToDataBaseVersion(MailAddress mailAddress,Message message,Type type) throws MessagingException, IOException{
		Mail mail = new Mail(mailAddress, message.getSubject(), message.getContent().toString(), message.getFrom()[0].toString(), AddressToString(message.getAllRecipients()), message.getSentDate(), message.getReceivedDate(), type);
		return mail;
	}
	
	public static String[] AddressToString(Address[] address){
		String[] addresses = {};
		for(int i=0;i<address.length;i++){
			addresses[i] = address[i].toString();
		}
		return addresses;
	}
	
}
