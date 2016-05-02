package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
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
		
		
		String name = request.getParameter("name");//�û���
		String password = request.getParameter("password");//����
		String host = request.getParameter("host");//������
		SimpleMailReceiver receiver = new SimpleMailReceiver(name,password,host);
		try{
			int maId = Integer.parseInt(request.getParameter("mailaddressid"));
			MailAddress ma = Database.getDefaultDatabase().getMailAddressById(maId);
			Message [] messages = receiver.getMail(ma);
			out.print("{");
			out.print("\"status\":0,");
			out.print("\"comment\":\"Get message success\",");
			out.print("\"result\":[");
			for(int i = 0;i < messages.length;i++){
				Message message = messages[i];
				out.print("{"+"\"From\":\""+message.getFrom()[0].toString()+"\",");
				out.print("\"To\":\""+message.getAllRecipients()[0].toString()+"\",");
				out.print("\"SendTime\":\""+message.getSentDate()+"\",");
				out.print("\"Subject\":\""+message.getSubject()+"\",");
				out.print("\"Content\":\""+message.getContent()+"\"}");
				if(i!=messages.length-1){
					out.print(",");
				}
			}
			out.print("]");
			out.print("}");
//			out.print("<table border='1'><tr><td>Sequence</td><td>Sender</td><td>Recipient</td><td>Subject</td><td>Content</td>");
//			for(int i = 0; i < messages.length; i++){
//				Message message = messages[i];
//				out.print("<tr>");
//				out.print("<td>" + i + 1 + "</td>");
//				out.print("<td>" + message.getFrom()[0].toString() + "</td>");
//				out.print("<td>" + message.getAllRecipients()[0].toString() + "</td>");
//				out.print("<td>" + message.getSubject() + "</td>");
//				out.print("<td>" + message.getContent() + "</td>");
//				out.print("</tr>");
//			}
//			out.print("</table>");
		}catch(MessagingException | SQLException e){
			e.printStackTrace();
			out.print("{");
			out.print("\"status\":1,");
			out.print("\"comment\":\"Get message fail\"");
			out.print("}");
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