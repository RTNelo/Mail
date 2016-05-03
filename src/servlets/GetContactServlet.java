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

import model.Contact;
import model.Database;
import model.User;

/**
 * Servlet implementation class getContactServlet
 */
@WebServlet("/getContactServlet")
public class GetContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetContactServlet() {
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
		User user = (User)request.getSession().getAttribute("user");
		try {
			List<Contact> contacts = database.getContactByUserId(user.getId());
			JSONObject object = new JSONObject();
			object.put("status", 0);
			object.put("comment", "success");
			JSONArray inner = new JSONArray();			
			for(int i=0;i<contacts.size();i++){
				JSONObject contact = new JSONObject();
				contact.put("nickname", contacts.get(i).getNickname());
				contact.put("mailAddress", contacts.get(i).getMailAddress());
				inner.put(contact);
			}
			object.put("result", inner);
			out.print(object.toString());
			
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
