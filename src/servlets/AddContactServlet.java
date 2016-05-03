package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contact;
import model.Database;
import model.User;

import org.json.*;

/**
 * Servlet implementation class addContactServlet
 */
@WebServlet("/addContactServlet")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactServlet() {
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
			String nickname = request.getParameter("nickname");
			String mailAddress = request.getParameter("mailaddress");
			User user = (User)request.getSession().getAttribute("user");
			Contact contact = new Contact(user, nickname, mailAddress);
			if(database.getContactByUserAndMailAddress(user, mailAddress)==null){
				int ret = database.addContact(contact);
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
