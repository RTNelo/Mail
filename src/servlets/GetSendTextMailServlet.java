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
		try {
			List<Mail> mails = database.getMailByMailAddressIdAndType(mailaddressId, Type.SENT);
			if(mails != null){
				out.print("{");
				out.print("\"status\":0,");
				out.print("\"comment\":\"Get message success\",");
				out.print("\"result\":[");
				for(int i = 0;i < mails.size();i++){
					Mail mail = mails.get(i);
					out.print("{"+"\"From\":\""+mail.getSender()+"\",");
					out.print("\"To\":\""+mail.getRecver()[0]+"\",");
					out.print("\"SendTime\":\""+mail.getSendTime()+"\",");
					out.print("\"ReceiveTime\":\""+mail.getRecvTime()+"\",");
					out.print("\"Subject\":\""+mail.getSubject()+"\",");
					out.print("\"Content\":\""+mail.getContent()+"\"}");
					if(i!=mails.size()-1){
						out.print(",");
					}
				}
				out.print("]");
				out.print("}");
			}
			else{
				out.print("{");
				out.print("\"status\":2,");
				out.print("\"comment\":\"send mail no exist\"");
				out.print("}");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{");
			out.print("\"status\":1,");
			out.print("\"comment\":\"Get message fail\"");
			out.print("}");
		}
	}

}
