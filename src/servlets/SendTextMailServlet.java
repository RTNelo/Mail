package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GetSuffix;
import model.*;
import SMTP.MailSenderInfo;
import SMTP.SimpleMailSender;

import org.json.*;

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
		int maId = Integer.parseInt(request.getParameter("mailaddressId"));
		MailAddress ma = null;
		try {
			ma = Database.getDefaultDatabase().getMailAddressById(maId);
			Database database = Database.getDefaultDatabase();
			String suffix = GetSuffix.getSuffixOfMailAddress(ma.getAccount());
			mailInfo.setMailServerHost(database.getHostBySuffix(suffix).getSmtpHost());
			mailInfo.setMailServerPort(database.getHostBySuffix(suffix).getSmtpPort()+"");
			mailInfo.setValidate(true);
			mailInfo.setUserName(ma.getAccount());//锟矫伙拷
			mailInfo.setPassword(ma.getPassword());//锟斤拷锟斤拷
			mailInfo.setFromAddress(ma.getAccount());//锟斤拷锟斤拷锟斤拷锟斤拷
			String[] to = {request.getParameter("to")};//锟秸硷拷锟斤拷锟斤拷
			mailInfo.setToAddress(to);
//			String[] toCC = {request.getParameter("toCC")};//锟斤拷锟斤拷
//			mailInfo.setToCarbonCopyAddress(toCC);
//			String[] toBCC = {request.getParameter("toBCC")};//锟斤拷锟斤拷
//			mailInfo.setToBlindCarbonCopyAddress(toBCC);
//			String[] file = {request.getParameter("file")};//锟斤拷锟斤拷
//			mailInfo.setAttachFileNames(file);
			mailInfo.setSubject(request.getParameter("title"));//锟斤拷锟斤拷
			String body = request.getParameter("content");//锟斤拷锟斤拷
			mailInfo.setContent(body);
			System.out.println(SimpleMailSender.sendMail(ma, mailInfo));//锟斤拷锟斤拷锟斤拷锟斤拷锟绞�
			JSONObject obj = new JSONObject();
			obj.put("status", 0);
			obj.put("comment", "success");
			out.print(obj.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			
			obj.put("status", 1);
			obj.put("comment", "server error");
			out.print(obj.toString());
		}
		
	}
	
	

}
