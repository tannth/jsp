package s1.ex4_LCServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LCServlet
 */
@WebServlet("/LCServlet")
public class LCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int a = 0;

	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init");
		a += 5;
		System.out.println("a= " + a);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LCServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("Servlet life cycle");
		a += 10;
		out.println("a= " + a);
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(" This is service ");
		//processRequest(request, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//processRequest(request, response);
	}

}
