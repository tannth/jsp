package mypkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myutil.JDBCUtils;
import entity.Cart;
import entity.CartItem;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet(name = "checkout", urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutServlet() {
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

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String sqlStr = null;
		HttpSession session = null;
		Cart cart = null;
		try {
			conn = JDBCUtils.getConnection(); // Get a connection from the pool
			stmt = conn.createStatement();

			out.println("<html><head><title>Checkout</title></head><body>");
			out.println("<h2>YAEBS - Checkout</h2>");

			// Retrieve the Cart
			session = request.getSession(false);
			if (session == null) {
				out.println("<h3>Your Shopping cart is empty!</h3></body></html>");
				return;
			}
			synchronized (session) {
				cart = (Cart) session.getAttribute("cart");
				if (cart == null) {
					out.println("<h3>Your Shopping cart is empty!</h3></body></html>");
					return;
				}
			}

			// Retrieve and process request parameters: id(s), cust_name,
			// cust_email, cust_phone
			String custName = request.getParameter("cust_name");
			boolean hasCustName = custName != null
					&& ((custName = custName.trim()).length() > 0);
			String custEmail = request.getParameter("cust_email").trim();
			boolean hasCustEmail = custEmail != null
					&& ((custEmail = custEmail.trim()).length() > 0);
			String custPhone = request.getParameter("cust_phone").trim();
			boolean hasCustPhone = custPhone != null
					&& ((custPhone = custPhone.trim()).length() > 0);

			// Validate inputs
			if (!hasCustName) {

				out.println("<h3>Please Enter Your Name!</h3></body></html>");
				return;
			} else if (!hasCustEmail || (custEmail.indexOf('@') == -1)) {
				out.println("<h3>Please Enter Your email (user@host)!</h3></body></html>");
				return;
			} else if (!hasCustPhone || custPhone.length() != 8) {
				out.println("<h3>Please Enter an 8-digit Phone Number!</h3></body></html>");
				return;
			}

			// Display the name, email and phone (arranged in a table)
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Customer Name:</td>");
			out.println("<td>" + custName + "</td></tr>");
			out.println("<tr>");
			out.println("<td>Customer Email:</td>");
			out.println("<td>" + custEmail + "</td></tr>");
			out.println("<tr>");
			out.println("<td>Customer Phone Number:</td>");
			out.println("<td>" + custPhone + "</td></tr>");
			out.println("</table>");

			// Print the book(s) ordered in a table
			out.println("<br />");
			out.println("<table border='1' cellpadding='6'>");
			out.println("<tr>");
			out.println("<th>AUTHOR</th>");
			out.println("<th>TITLE</th>");
			out.println("<th>PRICE</th>");
			out.println("<th>QTY</th></tr>");

			float totalPrice = 0f;
			for (CartItem item : cart.getItems()) {
				int id = item.getId();
				String author = item.getAuthor();
				String title = item.getTitle();
				int qtyOrdered = item.getQtyOrder();
				float price = item.getPrice();

				// No check for price and qtyAvailable change
				// Update the books table and insert an order record
				sqlStr = "UPDATE books SET qty = qty - " + qtyOrdered
						+ " WHERE id = " + id;
				// System.out.println(sqlStr); // for debugging
				stmt.executeUpdate(sqlStr);

				sqlStr = "INSERT INTO order_records values (" + id + ", "
						+ qtyOrdered + ", '" + custName + "', '" + custEmail
						+ "', '" + custPhone + "')";
				// System.out.println(sqlStr); // for debugging
				stmt.executeUpdate(sqlStr);

				// Show the book ordered
				out.println("<tr>");
				out.println("<td>" + author + "</td>");
				out.println("<td>" + title + "</td>");
				out.println("<td>" + price + "</td>");
				out.println("<td>" + qtyOrdered + "</td></tr>");
				totalPrice += price * qtyOrdered;
			}
			out.println("<tr><td colspan='4' align='right'>Total Price: $");
			out.printf("%.2f</td></tr>", totalPrice);
			out.println("</table>");

			out.println("<h3>Thank you.</h3>");
			out.println("<a href='start'>Back to Search Menu</a>");
			out.println("</body></html>");

			cart.clear(); // empty the cart
		} catch (SQLException ex) {
			cart.clear(); // empty the cart
			out.println("<h3>Service not available. Please try again later!</h3></body></html>");
			Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			out.close();
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close(); // Return the connection to the pool
			} catch (SQLException ex) {
				Logger.getLogger(CheckoutServlet.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
