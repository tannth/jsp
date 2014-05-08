package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.CartObj;

/**
 * Servlet implementation class ProcessServlet
 */
@WebServlet("/ProcessServlet")
public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String homePage = "index.jsp";
	private final String showPage = "show.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			Enumeration params = request.getParameterNames();
			boolean next = true;
			while (params.hasMoreElements() && next) {
				String paramName = (String) params.nextElement();
				if (paramName.startsWith("action")) {
					next = false;
					action = request.getParameter(paramName);
				}
			}
		}
		if (action != null) {

			if (action.equals("Add to Cart")) {
				String title = request.getParameter("cboBook");
				HttpSession session = request.getSession();
				CartObj shopping = (CartObj) session.getAttribute("SHOP");
				if (shopping == null) {
					shopping = new CartObj("Customer1");
				}
				shopping.addItem(title);
				session.setAttribute("SHOP", shopping);

				RequestDispatcher rd = request.getRequestDispatcher(homePage);
				rd.forward(request, response);
			} else if (action.equals("View Cart")) {
				RequestDispatcher rd = request.getRequestDispatcher(showPage);
				rd.forward(request, response);
			} else if (action.equals("Add More")) {
				RequestDispatcher rd = request.getRequestDispatcher(homePage);
				rd.forward(request, response);
			} else if (action.equals("Remove")) {

				String[] list = request.getParameterValues("chkRemove");
				if (list != null) {
					HttpSession session = request.getSession(false);
					if (session != null) {
						CartObj shoppingCart = (CartObj) session
								.getAttribute("SHOP");
						if (shoppingCart != null) {
							for (int i = 0; i < list.length; i++) {
								shoppingCart.removeItem(list[i]);
							}
							session.setAttribute("SHOP", shoppingCart);
						}
					}
				}

				RequestDispatcher rd = request.getRequestDispatcher(showPage);
				rd.forward(request, response);
			} else if (action.equals("Update")) {
				Boolean valid = (Boolean) request.getAttribute("VALID");
				if (valid) {
					HttpSession session = request.getSession(false);
					if (session != null) {
						CartObj shoppingCart = (CartObj) session
								.getAttribute("SHOP");
						if (shoppingCart != null) {
							String title = (String) request
									.getAttribute("txtTitle");
							Integer quantity = (Integer) request
									.getAttribute("txtQuantity");

							shoppingCart.updateItem(title, quantity);
							session.setAttribute("SHOP", shoppingCart);

						}
					}

				}
				String url = "ProcessServlet?action=View Cart";
				response.sendRedirect(url);

			}
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
