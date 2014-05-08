package s4.ex4_Session_HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SessionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String heading;
		Integer accessCount = (Integer) session.getAttribute("accessCount");
		if (accessCount == null) {
			accessCount = new Integer(0);
			heading = "Welcome Session Tracking";
		} else {
			heading = "Comeback";
			accessCount = new Integer(accessCount.intValue() + 1);
			session.setAttribute("accessCount", accessCount);
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<H1 ALIGN=\"CENTER\">"
				+ heading
				+ "</H1>\n<H2>Information on Session:</H2>\n"
				+ "<TABLE BORDER=1 ALIGN=\"CENTER\">\n<TR BGCOLOR=\"#FFAD00\">\n <TH>Info Type<TH>Value\n"
				+ "<TR>\n <TD>ID\n <TD>" + session.getId()
				+ "\n<TR>\n <TD>Create time\n <TD>"
				+ new Date(session.getCreationTime())
				+ "\n<TR>\n <TD>Time of Last Access\n <TD>"
				+ new Date(session.getLastAccessedTime())
				+ "\n<TR>\n <TD>Number of Previous Accesses\n <TD>"
				+ accessCount + "\n<TR>\n <TD>Session Time out\n <TD>"
				+ session.getMaxInactiveInterval() + "</TABLE>");

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
