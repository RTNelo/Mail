package servlets;

import model.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet({ "/RegisterServlet", "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("accountInvalid", false);
		request.setAttribute("nicknameInvalid", false);
		request.setAttribute("passwordInvalid", false);
		request.setAttribute("repasswordInvalid", false);
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("accountInvalid", false);
		request.setAttribute("nicknameInvalid", false);
		request.setAttribute("passwordInvalid", false);
		request.setAttribute("repasswordInvalid", false);
		
		String account = request.getParameter("account");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		User newUser = new User(account, password, nickname);
		Database db = Database.getDefaultDatabase();
		try {
			boolean invalidFlag = false;
			if (db.getUserByAccount(account) != null || account.length() < 4) {
				request.setAttribute("accountInvalid", true);
				invalidFlag = true;
			}
			if (nickname.length() < 3) {
				request.setAttribute("nicknameInvalid", true);
				invalidFlag = true;
			}
			if (repassword.length() < 6) {
				request.setAttribute("passwordInvalid", true);
				invalidFlag = true;
			}
			if (!repassword.equals(password)) {
				request.setAttribute("repasswordInvalid", true);
				invalidFlag = true;
			}
			if (invalidFlag) {
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			db.addUser(newUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("user", newUser);
		response.sendRedirect("/Mail/MainPage");
	}

}
