package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Database;
import model.Mail;
import model.Mail.Type;
import model.MailAddress;

/**
 * Servlet implementation class GetSendTextMailServlet
 */
@WebServlet("/GetSendTextMailServlet")
public class GetSendTextMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSendTextMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Database database = Database.getDefaultDatabase();
		int mailaddressId = Integer.parseInt(request.getParameter("mailaddressId"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int offset = Integer.parseInt(request.getParameter("offset"));
		try {
			List<Mail> mails = database.getMailByMailAddressIdAndType(mailaddressId, Type.SENT, limit, offset);
			if(mails != null){
				JSONObject obj = new JSONObject();
				obj.put("status", 0);
				obj.put("comment", "success");
				JSONArray inner = new JSONArray();
				
				for(int i = 0;i < mails.size();i++){
					Mail mail = mails.get(i);
					JSONObject mailJson = new JSONObject();
					mailJson.put("From", mail.getSender());
					mailJson.put("To", mail.getRecver()[0]);
					mailJson.put("SendTime", mail.getSendTime());
					mailJson.put("ReceiveTime", mail.getRecvTime());
					mailJson.put("Subject", mail.getSubject());
					mailJson.put("Content", mail.getContent());
					mailJson.put("Id", mail.getId());
					inner.put(mail);
				}
				obj.put("result", inner);
				out.print(obj.toString());
			}
			else{
				JSONObject obj = new JSONObject();
				obj.put("status", 2);
				obj.put("comment", "not exists");
				out.print(obj.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("status", 2);
			obj.put("comment", "server error");
			out.print(obj.toString());
		}
	}

}
