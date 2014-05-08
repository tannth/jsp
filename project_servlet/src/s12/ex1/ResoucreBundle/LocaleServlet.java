package s12.ex1.ResoucreBundle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocaleServlet
 */
@WebServlet("/LocaleServlet")
public class LocaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LocaleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Locale locale = request.getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(
				"s12.ex1.ResoucreBundle.WelcomeBundle_ge_GE", locale);
		String welcome = bundle.getString("Welcome to our website");
		String greeting = bundle.getString("Good Morning");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>"+welcome+"</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> Good morning: "+greeting+"</h1>");
		out.println("<h2> Welcome to our website: "+welcome+"</h1>");
		out.println("</body>");
		out.println("</html>");
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
