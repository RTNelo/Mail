package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import SMTP.MailSenderInfo;
import SMTP.SimpleMailSender;

/**
 * Servlet implementation class mail
 */
@WebServlet("/sendmail")
public class SendTextMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendTextMailServlet() {
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
		MailSenderInfo mailInfo = new MailSenderInfo();
		int maId = Integer.parseInt(request.getParameter("mailaddressid"));
		MailAddress ma = null;
		try {
			ma = Database.getDefaultDatabase().getMailAddressById(maId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailInfo.setMailServerHost("smtp.qq.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName(request.getParameter("user"));//�û�
		mailInfo.setPassword(request.getParameter("pass"));//����
		mailInfo.setFromAddress(request.getParameter("from"));//��������
		String[] to = {request.getParameter("to")};//�ռ�����
		mailInfo.setToAddress(to);
		String[] toCC = {request.getParameter("toCC")};//����
		mailInfo.setToCarbonCopyAddress(toCC);
		String[] toBCC = {request.getParameter("toBCC")};//����
		mailInfo.setToBlindCarbonCopyAddress(toBCC);
		String[] file = {request.getParameter("file")};//����
		mailInfo.setAttachFileNames(file);
		mailInfo.setSubject(request.getParameter("title"));//����
		String body = request.getParameter("content");//����
		mailInfo.setContent(body);
		System.out.println(SimpleMailSender.sendHtmlMail(ma, mailInfo));//���������ʽ
		out.print("{");
		out.print("\"status\":0,");
		out.print("\"comment\":\"Send mail success\"");
		out.print("}");
	}
	
	

}
