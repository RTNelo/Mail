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
			out.print("{");
			out.print("\"status\":0,");
			out.print("\"comment\":\"Get mail address success\",");
			out.print("\"result\":[");
			for(int i=0;i<mailAddresses.size();i++){
				out.print("{");
				out.print("\"mailAddress\":{\"account\":\""+mailAddresses.get(i).getAccount()+"\",\"password\":\""+mailAddresses.get(i).getPassword()+"\"}");
				out.print("}");
				if(i!=mailAddresses.size()-1){
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
			out.print("\"comment\":\"Get mail address fail\"");
			out.print("}");
		}
	}

}
