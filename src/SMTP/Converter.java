package SMTP;

import java.io.IOException;

import SMTP.*;

import javax.mail.*;

import model.*;
import model.Mail.Type;

public class Converter {
	public static Mail MessageToDataBaseVersion(MailAddress mailAddress,Message message,Type type) throws MessagingException, IOException{
		Part messagePart = message;
		Object content = messagePart.getContent();
		String con = "";
		if (content instanceof Multipart) {
		   for (int i = 0; i != ((Multipart) content).getCount(); ++i)
		   {
			   messagePart = ((Multipart) content).getBodyPart(i);
			   if (messagePart.getContentType().indexOf("text/html") != -1) {
				   con += (String)messagePart.getContent();
			   }
		   }
		}
		Mail mail = new Mail(mailAddress, message.getSubject(), con, message.getFrom()[0].toString(), AddressToString(message.getAllRecipients()), message.getSentDate(), message.getReceivedDate(), type);
		return mail;
	}
	
	public static String[] AddressToString(Address[] address){
		String[] addresses = new String[address.length];
		for(int i=0;i<address.length;i++){
			addresses[i] = address[i].toString();
		}
		return addresses;
	}
	
}
