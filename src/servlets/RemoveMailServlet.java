package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Database;
import model.Mail;

/**
 * Servlet implementation class RemoveMailServlet
 */
@WebServlet("/RemoveMailServlet")
public class RemoveMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveMailServlet() {
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
		int mailId = Integer.parseInt(request.getParameter("mailId"));
		try {
			Mail mail = database.getMailById(mailId);
			if(mail!=null){
				database.removeMail(mail);
				out.print("{");
				out.print("\"status\":0,");
				out.print("\"comment\":\"remove mail success\"");
				out.print("}");
			}
			else{
				out.print("{");
				out.print("\"status\":2,");
				out.print("\"comment\":\"mail not exist\"");
				out.print("}");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{");
			out.print("\"status\":1,");
			out.print("\"comment\":\"remove mail fail\"");
			out.print("}");
		}
	}

}
