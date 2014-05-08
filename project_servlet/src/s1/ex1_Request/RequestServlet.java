package s1.ex1_Request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration parNames = request.getParameterNames();
		PrintWriter out = response.getWriter();
		int count = 0;
		while (parNames.hasMoreElements()) {
			count++;
			String parName = (String) parNames.nextElement();
			out.print("parName" + count + " is " + parName);

			String parVal = request.getParameter(parName);
			out.println(" and value is: " + parVal);
		}
		count = 0;
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			++count;
			String headerName = (String) headerNames.nextElement();
			out.print("header " + headerName + " is ");
			String headerVal = request.getHeader(headerName);
			out.println(headerVal + "<br/>");
		}

		String strServer = request.getServerName();
		out.println("Server Name: " + strServer);

		int length = request.getContentLength();
		out.println("Length in bytes: " + length);

		String strHost = request.getHeader("host");
		out.println("Host: " + strHost);

		String strMethod = request.getMethod();
		out.println("Request method: " + strMethod);

		String strPath = request.getPathInfo();
		out.println("Path infor: " + strPath);

		String strAuth = request.getAuthType();
		out.println("Authentication: " + strAuth);

		String qs = request.getQueryString();
		out.println("Query String: " + qs);
		
		out.println ("</br></br>Headers Accept ");
        Enumeration headers = request.getHeaders ("Accept");
        while(headers.hasMoreElements ()){
            String header = (String)headers.nextElement ();
            out.println (header + "</br>");
        }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
