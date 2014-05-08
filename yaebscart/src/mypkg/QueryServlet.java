package mypkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import myutil.JDBCUtils;
import entity.Cart;

/**
 * Servlet implementation class QueryServlet
 */
@WebServlet(name = "query", urlPatterns = { "/query" })
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection cn = null;
		Statement stmt = null;
		try {
			String author = request.getParameter("author");
			boolean hasAuthorParam = author != null
					&& !author.equals("Select...");
			String searchWord = request.getParameter("search");
			boolean hasSearchParam = searchWord != null
					&& ((searchWord.trim()).length() > 0);
			out.println("<html><head><title>Query Results</title></head><body>");
			out.println("<h2>YAEBS - Query Results</h2>");

			if (!hasAuthorParam && !hasSearchParam) { // No params present
				out.println("<h3>Please select an author or enter a search term!</h3>");
				out.println("<p><a href='start'>Back to Select Menu</a></p>");
			} else {
				cn = JDBCUtils.getConnection();
				stmt = cn.createStatement();

				// Form a SQL command based on the param(s) present
				StringBuilder sqlStr = new StringBuilder(); // more efficient
															// than String
				/*
				 * SELECT * FROM books WHERE qty > 0 AND (author = 'name' OR
				 * author LIKE '%term%' OR title LIKE '%term%')
				 */
				sqlStr.append("SELECT * FROM books WHERE qty > 0 AND (");
				if (hasAuthorParam) {
					sqlStr.append("author= '").append(author).append("'");
				}
				if (hasSearchParam) {
					if (hasAuthorParam) {
						sqlStr.append(" OR ");
					}
					sqlStr.append("author LIKE '%").append(searchWord)
							.append("%' OR title LIKE '%").append(searchWord)
							.append("%'");
				}
				sqlStr.append(") ORDER BY author, title");

				// System.out.println(sqlStr); // for debugging
				ResultSet rset = stmt.executeQuery(sqlStr.toString());
				if (!rset.next()) { // Check for empty ResultSet (no book found)
					out.println("<h3>No book found. Please try again!</h3>");
					out.println("<p><a href='start'>Back to Select Menu</a></p>");
				} else {
					// Print the result in an HTML form inside a table
					out.println("<form method='get' action='cart'>");
					out.println("<input type='hidden' name='todo' value='add' />");
					out.println("<table border='1' cellpadding='6'>");
					out.println("<tr>");
					out.println("<th>&nbsp;</th>");
					out.println("<th>AUTHOR</th>");
					out.println("<th>TITLE</th>");
					out.println("<th>PRICE</th>");
					out.println("<th>QTY</th>");
					out.println("</tr>");
					// ResultSet's cursor now pointing at first row
					do {
						// Print each row with a checkbox identified by book's
						// id
						String id = rset.getString("id");
						out.println("<tr>");
						out.println("<td><input type='checkbox' name='id' value='"
								+ id + "' /></td>");
						out.println("<td>" + rset.getString("author") + "</td>");
						out.println("<td>" + rset.getString("title") + "</td>");
						out.println("<td>$" + rset.getString("price") + "</td>");
						out.println("<td><input type='text' size='3' value='1' name='qty"
								+ id + "' /></td>");
						out.println("</tr>");
					} while (rset.next());
					out.println("</table><br />");

				/*	// Ask for name, email and phone using text fields (arranged
					// in a table)
					out.println("<table>");
					out.println("<tr><td>Enter your Name:</td>");
					out.println("<td><input type='text' name='cust_name' /></td></tr>");
					out.println("<tr><td>Enter your Email (user@host):</td>");
					out.println("<td><input type='text' name='cust_email' /></td></tr>");
					out.println("<tr><td>Enter your Phone Number (8-digit):</td>");
					out.println("<td><input type='text' name='cust_phone' /></td></tr></table><br />");*/

					// Submit and reset buttons
					out.println("<input type='submit' value='ORDER' />");
					out.println("<input type='reset' value='CLEAR' /></form>");

					// Hyperlink to go back to search menu
					out.println("<p><a href='start'>Back to Select Menu</a></p>");

					// Show "View Shopping Cart" if the cart is not empty
					HttpSession session = request.getSession(false); // check if
																		// session
																		// exists
					if (session != null) {
						Cart cart;
						synchronized (session) {
							// Retrieve the shopping cart for this session, if
							// any. Otherwise, create one.
							cart = (Cart) session.getAttribute("cart");
							if (cart != null && !cart.isEmpty()) {
								out.println("<P><a href='cart?todo=view'>View Shopping Cart</a></p>");
							}
						}
					}
				}
			}
			out.println("</body></html>");
		} catch (Exception ex) {
			out.println("<h3>Service not available. Please try again later!</h3></body></html>");
			Logger.getLogger(QueryServlet.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			out.close();
			try {
				if (stmt != null)
					stmt.close();
				if (cn != null)
					cn.close();
			} catch (SQLException ex) {
				Logger.getLogger(QueryServlet.class.getName()).log(
						Level.SEVERE, null, ex);
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
