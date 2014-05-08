package mvcdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sun.awt.windows.WWindowPeer;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	final private String errorPage = "fail.jsp";
	final private String welcomePage = "welcome.jsp";
	final private String homePage = "index.jsp";
	final private String registerPage = "register.jsp";
	final private String showPage = "showpage.jsp";

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String action = request.getParameter("btnAction");
			if (action.equals("Login")) {
				String username = request.getParameter("txtusername");
				String password = request.getParameter("txtpassword");
				LoginBean login = new LoginBean();
				boolean result = login.checkLogin(username, password);
				String url = errorPage;
				if (result) {
					HttpSession session = request.getSession(true);
					session.setAttribute("USERNAME", username);
					url = welcomePage;
				}
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			} else if (action.equals("register")) {
				RequestDispatcher rd = request
						.getRequestDispatcher(registerPage);
				rd.forward(request, response);
			} else if (action.equals("tryAgain")) {
				RequestDispatcher rd = request.getRequestDispatcher(homePage);
				rd.forward(request, response);
			} else if (action.equals("Register")) {
				String user = request.getParameter("txtusername");
				String pass = request.getParameter("txtpassword");
				String lname = request.getParameter("txtlname");
				String isAdmin = request.getParameter("chkAdmin");
				if (isAdmin == null) {
					isAdmin = "0";
				}
				LoginBean login = new LoginBean();
				boolean result = login.insert(user, pass, lname, isAdmin);
				if (result) {
					RequestDispatcher rd = request
							.getRequestDispatcher(homePage);
					rd.forward(request, response);
				} 

			} else if (action.equals("Search")) {
				String name = request.getParameter("txtsearch");
				LoginBean login = new LoginBean();
				LoginBean[] result = login.searchLikeLastName(name);

				request.setAttribute("INFO", result);
				RequestDispatcher rd = request.getRequestDispatcher(showPage);
				rd.forward(request, response);

			} else if (action.equals("Delete")) {
				String username = request.getParameter("username");

				String name = request.getParameter("txtsearch");

				LoginBean login = new LoginBean();
				login.setUsername(username);
				boolean result = login.delete();
				String url = "Controller?btnAction=Search&txtsearch=" + name;
				if (result) {
					RequestDispatcher rd = request.getRequestDispatcher(url);
					rd.forward(request, response);
				}
			} else if (action.equals("Update")) {
				String username = request.getParameter("txtUsername");
				String lastname = request.getParameter("txtLastname");
				String role = request.getParameter("chkAdmin");
				if (role == null) {
					role = "0";
				}
				String name = request.getParameter("txtsearch");
				LoginBean bean = new LoginBean(username, lastname, role);
				boolean result = bean.update();

				String url = "Controller?btnAction=Search&txtsearch=" + name;
				if (result) {
					RequestDispatcher rd = request.getRequestDispatcher(url);
					rd.forward(request, response);
				}

			} else if (action.equals("Back")) {
				RequestDispatcher rd = request.getRequestDispatcher(welcomePage);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
