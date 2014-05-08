package s4.ex1_Session_URLRewriting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class URLServlet
 */
@WebServlet("/URLServlet")
public class URLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public URLServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		if (name.equalsIgnoreCase("Aptech") && password.equalsIgnoreCase("123456")) {
			String queryString = response.encodeURL("NextServlet?name=" + name
					+ "&password=" + password);
			 out.println ("Click here forward to <a href=" + queryString + ">Welcome</a>"); 
		} else {
			String queryString = response.encodeURL("url.html?name=" + name
					+ "&password=" + password);
			out.println("Please enter your information. Click <a href="
					+ queryString + ">here</a> to go back");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
