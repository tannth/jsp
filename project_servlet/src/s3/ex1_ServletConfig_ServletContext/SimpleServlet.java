package s3.ex1_ServletConfig_ServletContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ServletContext cnt = config.getServletContext();
		Date sysDate = new Date();
		cnt.setAttribute("obj", sysDate);

	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		response.setContentType("text/html;Charset:UTF-8");
		String servletName = getServletName();
		PrintWriter out = response.getWriter();
		out.println("Servlet Name: " + servletName + "<br/>");

		ServletContext cnt = getServletContext();
		Object obj = cnt.getAttribute("obj");
		if (obj != null) {
			Date d = (Date) obj;
			out.println("Date is: " + d.toString());
		} else {
			out.println("Object not found");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		ServletContext cnt = getServletContext();
		Object obj = cnt.getAttribute("obj");
		if (obj != null) {
			cnt.removeAttribute("obj*");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
