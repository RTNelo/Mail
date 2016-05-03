package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.Address;
import javax.mail.Message;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Database;
import model.MailAddress;
import model.User;

/**
 * Servlet implementation class MailAddressSetter
 */
@WebServlet("/MailAddressSetter")
public class AddMailAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMailAddressServlet() {
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
		try {
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			User user = (User)request.getSession().getAttribute("user");
			MailAddress mailAddress = new MailAddress(user, account, password);
			if(database.getMailAddressByUserAndAccount(user, account)==null){
				int ret = database.addMailAddress(mailAddress);
				JSONObject object = new JSONObject();
				object.put("status", 0);
				object.put("comment", "success");
				JSONObject inner = new JSONObject();
				inner.put("id", ret);
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
