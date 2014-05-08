package s1.ex2_ChoiceRequest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChoiceServlet
 */
@WebServlet("/ChoiceServlet")
public class ChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChoiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequests(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.println("<html>");
		out.println("<body>");
		out.println("<form method='post' >");
		out.println("<input type='checkbox' name='rmv' value='Servlet' /> Servlet </br>");
		out.println("<input type='checkbox' name='rmv' value='JSP'/> JSP </br>");
		out.println("<input type='checkbox' name='rmv' value='Struts'/> Struts </br>");
		out.println("<input type='checkbox' name='rmv' value='EJB'/> EJB </br>");
		out.println("<input type='submit' value='Choice' />");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
		String[] strSelect = request.getParameterValues ("rmv");
        if(strSelect!=null){
            for(int i=0; i<strSelect.length; i++){
                out.println ("Selected item name: " + strSelect[i] + "</br>");
            }
        }
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequests(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequests(request, response);
	}

}
