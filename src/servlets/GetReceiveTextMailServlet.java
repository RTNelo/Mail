package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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

/**
 * Servlet implementation class GetReceiveTextMailServlet
 */
@WebServlet("/GetReceiveTextMailServlet")
public class GetReceiveTextMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReceiveTextMailServlet() {
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
			List<Mail> mails = database.getMailByMailAddressIdAndType(mailaddressId, Type.RECV, limit, offset);
			if(mails != null){
				JSONObject object = new JSONObject();
				object.put("status", 0);
				object.put("comment", "success");
				JSONArray inner = new JSONArray();			
				for(int i=0;i<mails.size();i++){
					Mail mail = mails.get(i);
					JSONObject mailAddress = new JSONObject();
					mailAddress.put("from", mail.getSender());
					mailAddress.put("to", mail.getRecver()[0]);
					mailAddress.put("SendTime", mail.getSendTime());
					mailAddress.put("ReceiveTime", mail.getRecvTime());
					mailAddress.put("Subject", mail.getSubject());
					mailAddress.put("Content", mail.getContent());
					mailAddress.put("Id", mail.getId());
					inner.put(mailAddress);
				}
				object.put("result", inner);
				out.print(object.toString());
				
			}
			else{
				JSONObject object = new JSONObject();
				object.put("status", 2); 
				object.put("comment", "exist");
				out.println(object.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject object = new JSONObject();
			object.put("status", 1); 
			object.put("comment", "fail");
			out.println(object.toString());
		}
	}

}
