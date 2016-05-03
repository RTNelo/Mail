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
import model.MailAddress;
import model.User;

/**
 * Servlet implementation class getMailAddressServlet
 */
@WebServlet("/getMailAddressServlet")
public class GetMailAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMailAddressServlet() {
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
			List<MailAddress> mailAddresses = database.getMailAddressByUser(user);
			JSONObject object = new JSONObject();
			object.put("status", 0);
			object.put("comment", "success");
			JSONArray inner = new JSONArray();			
			for(int i=0;i<mailAddresses.size();i++){
				JSONObject mailAddress = new JSONObject();
				mailAddress.put("account", mailAddresses.get(i).getAccount());
				mailAddress.put("password", mailAddresses.get(i).getPassword());
				inner.put(mailAddress);
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
