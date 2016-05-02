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
		User user = (User)request.getSession().getAttribute("User");
		try {
			List<Contact> contacts = database.getContactByUserId(user.getId());
			out.print("{");
			out.print("\"status\":0,");
			out.print("\"comment\":\"Get contact success\",");
			out.print("\"result\":[");
			for(int i=0;i<contacts.size();i++){
				out.print("{");
				out.print("\"contact\":{\"nickname\":\""+contacts.get(i).getNickname()+"\",\"mailAddress\":\""+contacts.get(i).getMailAddress()+"\"}");
				out.print("}");
				if(i!=contacts.size()-1){
					out.print(",");
				}
			}
			out.print("]");
			out.print("}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{");
			out.print("\"status\":1,");
			out.print("\"comment\":\"Get contact fail\"");
			out.print("}");
		}
	}

}
