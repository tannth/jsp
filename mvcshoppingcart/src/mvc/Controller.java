package mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String homePage = "index.jsp";
	private final String showPage = "show.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		try {
			String action = request.getParameter("action");
			if (action.endsWith("Add to Cart")) {
				HttpSession session = request.getSession(true);
				CartBean shop = (CartBean) session.getAttribute("SHOP");
				if (shop == null) {
					shop = new CartBean();
				}
				String title = request.getParameter("cboBook");
				BookDTO book = new BookDTO(title);
				shop.addBook(book);
				session.setAttribute("SHOP", shop);

				RequestDispatcher rd = request.getRequestDispatcher(homePage);
				rd.forward(request, response);
			} else if (action.equals("View Cart")) {
				RequestDispatcher rd = request.getRequestDispatcher(showPage);
				rd.forward(request, response);
			} else if (action.equals("AddMore")) {
				RequestDispatcher rd = request.getRequestDispatcher(homePage);
				rd.forward(request, response);
			} else if (action.equals("Remove")) {
				String[] list = request.getParameterValues("rmv");
				if (list != null) {
					HttpSession session = request.getSession();
					if (session != null) {
						CartBean shop = (CartBean) session.getAttribute("SHOP");
						if (shop != null) {
							for (int i = 0; i < list.length; i++) {
								shop.removeBook(list[i]);
							}
							session.setAttribute("SHOP", shop);
						}

					}
				}
				String url = "Controller?action=View Cart";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

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
