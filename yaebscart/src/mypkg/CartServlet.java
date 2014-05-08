package mypkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;
import entity.CartItem;
import myutil.InputFilter;
import myutil.JDBCUtils;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(name = "cart", urlPatterns = { "/cart" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// Retrieve current HTTPSession object. If none, create one.
		HttpSession session = request.getSession(true);
		Cart cart;
		synchronized (session) { // synchronized to prevent concurrent updates
			// Retrieve the shopping cart for this session, if any. Otherwise,
			// create one.
			cart = (Cart) session.getAttribute("cart");
			if (cart == null) { // No cart, create one.
				cart = new Cart();
				session.setAttribute("cart", cart); // Save it into session
			}
		}

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String sqlStr = null;

		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			out.println("<html><head><title>Order Confirmation</title></head><body>");
			out.println("<h2>YAEBS - Order Confirmation YAEBSYAEBS</h2>");

			// This servlet handles 4 cases:
			// (1) todo=add id=1001 qty1001=5 [id=1002 qty1002=1 ...]
			// (2) todo=update id=1001 qty1001=5
			// (3) todo=remove id=1001
			// (4) todo=view

			String todo = request.getParameter("todo");
			if (todo == null)
				todo = "view"; // to prevent null pointer

			if (todo.equals("add") || todo.equals("update")) {
				// (1) todo=add id=1001 qty1001=5 [id=1002 qty1002=1 ...]
				// (2) todo=update id=1001 qty1001=5
				String[] ids = request.getParameterValues("id");
				if (ids == null) {
					out.println("<h3>Please Select a Book!</h3></body></html>");
					return;
				}
				for (String id : ids) {
					sqlStr = "SELECT * FROM books WHERE id = " + id;
					// System.out.println(sqlStr); // for debugging
					rset = stmt.executeQuery(sqlStr);
					rset.next(); // Expect only one row in ResultSet
					String title = rset.getString("title");
					String author = rset.getString("author");
					float price = rset.getFloat("price");

					// Get quantity ordered - no error check!
					int qtyOrdered = Integer.parseInt(request
							.getParameter("qty" + id));
					int idInt = Integer.parseInt(id);
					if (todo.equals("add")) {
						cart.add(idInt, title, author, price, qtyOrdered);
					} else if (todo.equals("update")) {
						cart.update(idInt, qtyOrdered);
					}
				}

			} else if (todo.equals("remove")) {
				String id = request.getParameter("id"); // Only one id for
				System.out.println(id);										// remove case
				cart.remove(Integer.parseInt(id));
			}

			// All cases - Always display the shopping cart
			if (cart.isEmpty()) {
				out.println("<p>Your shopping cart is empty</p>");
			} else {
				out.println("<table border='1' cellpadding='6'>");
				out.println("<tr>");
				out.println("<th>AUTHOR</th>");
				out.println("<th>TITLE</th>");
				out.println("<th>PRICE</th>");
				out.println("<th>QTY</th>");
				out.println("<th>REMOVE</th></tr>");

				float totalPrice = 0f;
				for (CartItem item : cart.getItems()) {
					int id = item.getId();
					String author = item.getAuthor();
					String title = item.getTitle();
					float price = item.getPrice();
					int qtyOrdered = item.getQtyOrder();

					out.println("<tr>");
					out.println("<td>" + author + "</td>");
					out.println("<td>" + title + "</td>");
					out.println("<td>" + price + "</td>");

				
					out.println("<td><form method='get'>");
					out.println("<input type='hidden' name='todo' value='update' />");
					out.println("<input type='hidden' name='id' value='" + id+ "' />");
					out.println("<input type='text' size='3' name='qty" + id
							+ "' value='" + qtyOrdered + "' />");
					out.println("<input type='submit' value='Update' />");
					out.println("</form></td>");
					
					out.println("<td><form method='get'>");
					out.println("<input type='hidden' name='todo' value='remove'/>");
					out.println("<input type='hidden' name='id' value='" + id+ "' />");
					
					out.println("<input type='submit' value='Remove'/>");
					out.println("</form></td>");
					
					
					out.println("</tr>");
					totalPrice += price * qtyOrdered;
				}
				out.println("<tr><td colspan='5' align='right'>Total Price: $");
				out.printf("%.2f</td></tr>", totalPrice);
				out.println("</table>");
			}

			out.println("<p><a href='start'>Select More Books...</a></p>");

			// Display the Checkout
			if (!cart.isEmpty()) {
				out.println("<br /><br />");
				out.println("<form method='get' action='checkout'>");
				out.println("<input type='submit' value='CHECK OUT'>");
				out.println("<p>Please fill in your particular before checking out:</p>");
				out.println("<table>");
				out.println("<tr>");
				out.println("<td>Enter your Name:</td>");
				out.println("<td><input type='text' name='cust_name' /></td></tr>");
				out.println("<tr>");
				out.println("<td>Enter your Email:</td>");
				out.println("<td><input type='text' name='cust_email' /></td></tr>");
				out.println("<tr>");
				out.println("<td>Enter your Phone Number:</td>");
				out.println("<td><input type='text' name='cust_phone' /></td></tr>");
				out.println("</table>");
				out.println("</form>");
			}

			out.println("</body></html>");
		} catch (SQLException ex) {
			try {
				conn.rollback(); // rollback the updates
				out.println("<h3>Service not available. Please try again later!</h3></body></html>");
			} catch (SQLException ex1) {
			}
			Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			out.close();
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE,
						null, ex);
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
