package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Database;
import model.Mail;
import model.MailAddress;

/**
 * Servlet implementation class RemoveMailAddressServlet
 */
@WebServlet("/RemoveMailAddressServlet")
public class RemoveMailAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveMailAddressServlet() {
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
		int mailAddressId = Integer.parseInt(request.getParameter("mailAddressId"));
		try {
				database.removeMailByMailAddressId(mailAddressId);
				database.removeMailAddressById(mailAddressId);
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
