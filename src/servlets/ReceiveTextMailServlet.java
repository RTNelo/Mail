package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.mail.*;

import util.GetSuffix;
import SMTP.SimpleMailReceiver;
import model.Database;
import model.MailAddress;

@WebServlet("/getmails")
public class ReceiveTextMailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ReceiveTextMailServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		
		try{
			int maId = Integer.parseInt(request.getParameter("mailaddressId"));
			Database database = Database.getDefaultDatabase();
			MailAddress ma = Database.getDefaultDatabase().getMailAddressById(maId);
			String suffix = GetSuffix.getSuffixOfMailAddress(ma.getAccount());
			String host = database.getHostBySuffix(suffix).getPop3Host();
			SimpleMailReceiver receiver = new SimpleMailReceiver(ma.getAccount(),ma.getPassword(),host);
			Message [] messages = receiver.getMail(ma);
			if(messages != null){
				JSONObject obj = new JSONObject();
				obj.put("status", 0);
				obj.put("comment", "success");
				JSONArray inner = new JSONArray();
				
				for(int i = 0;i < messages.length;i++){
					Message message = messages[i];
					JSONObject mail = new JSONObject();
					mail.put("From", message.getFrom()[0].toString());
					mail.put("To", message.getAllRecipients()[0].toString());
					mail.put("SendTime", message.getSentDate());
					mail.put("ReceiveTime", message.getReceivedDate());
					mail.put("Subject", message.getSubject());
					mail.put("Content", message.getContent());
					inner.put(mail);
				}
				obj.put("result", inner);
				out.print(obj.toString());
			
			}
			else{
				JSONObject obj = new JSONObject();
				obj.put("status", 2);
				obj.put("comment", "server error");
				out.print(obj.toString());
			}
			
		}catch(MessagingException | SQLException e){
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("status", 2);
			obj.put("comment", "cannot get message");
			out.print(obj.toString());
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
