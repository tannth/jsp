package s4.as1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	List<String> item = new ArrayList<String>();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		/*
		 * HttpSession session = request.getSession(true); String txtUser =
		 * (String) session.getAttribute("user"); out.println("Hello,"+
		 * txtUser);
		 */
		String action = request.getParameter("process");

		String select = request.getParameter("select");

		if (action.equalsIgnoreCase("Add to Cart")) {
			item.add(select);
			RequestDispatcher rs = request
					.getRequestDispatcher("/s4shopping.html?select=" + select);
			out.println("Insert " + select + " success");
			rs.include(request, response);
		}
		if (action.equalsIgnoreCase("View Cart")) {
			response.setContentType("text/html");
			out.println("<h1>Shopping Cart</h1>");
			if (item.size() > 0) {
				out.print("<form action='CartServlet?mode=remove' method='post'><table border=1 align='20px'><tr><td>Order</td>"
						+ "<td>Book tile</td>" + "<td>Action</td> </tr>");
				for (int i = 0; i < item.size(); i++) {
					int orderNumber = i + 1;
					out.print("<tr><td>"
							+ orderNumber
							+ "</td>"
							+ "<td>"
							+ item.get(i)
							+ "</td>"
							+ "<td><input type='checkbox' name='checked' value="
							+ item.get(i) + "></td> </tr>");
				}
				out.print("<tr>"
						+ "<td></td>"
						+ "<td><a href='s4shopping.html'>Choose next</a> </td>"
						+ "<td><input type='submit' name='process' value='Remove'> </form></td>"
						+ "</tr>" + "</table>");

			} else {
				out.println("No product.");
			}
		}
		if (action.equalsIgnoreCase("Remove")) {
			String mode = request.getParameter("mode");
			if (mode.equalsIgnoreCase("remove")) {
				String[] selected = request.getParameterValues("checked");
				for (int selectedIndex = 0; selectedIndex < selected.length; selectedIndex++) {
					for (int j = 0; j < item.size(); j++) {
						System.out.println(item.get(j).getClass());
						System.out.println(selected[selectedIndex].getClass());
						if (item.get(j).equals(selected[selectedIndex])) {
							item.remove(item.get(j));
						}
					}
				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
