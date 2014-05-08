package mypkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myutil.JDBCUtils;
import entity.Cart;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Servlet implementation class EntryServlet
 */
@WebServlet(name = "start", urlPatterns = { "/start" })
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = JDBCUtils.getConnection();
			stmt = conn.createStatement();
			String sqlStr = "Select DISTINCT author from books where qty >0";
			// system.out.println(sqlStr) //for debug
			ResultSet rset = stmt.executeQuery(sqlStr);
			out.println("<html><head><title>Welcome to YaEshop</title></head><body>");
			out.println("<h2>Welcome to Yet Another E-BookShop</h2>");
			// Begin an HTML form
			out.println("<form method='get' action='query'>");
			// A pull-down menu of all the authors with a no-selection option
			out.println("Choose an Author: <select name='author' size='1'>");
			out.println("<option value=''>Select...</option>"); // no-selection
			while (rset.next()) {
				String author = rset.getString("author");
				out.println("<option value='" + author + "'>" + author
						+ "</option>");
			}
			out.println("</select><br />");
			out.println("<p>OR</p>");

			// A text field for entering search word for pattern matching
			out.println("Search \"Title\" or \"Author\": <input type='text' name='search' />");

			// Submit and reset buttons
			out.println("<br /><br />");
			out.println("<input type='submit' value='SEARCH' />");
			out.println("<input type='reset' value='CLEAR' />");
			out.println("</form>");

			 // Show "View Shopping Cart" if the cart is not empty
	         HttpSession session = request.getSession(false); // check if session exists
	         if (session != null) {
	            Cart cart;
	            synchronized (session) {
	               // Retrieve the shopping cart for this session, if any. Otherwise, create one.
	               cart = (Cart) session.getAttribute("cart");
	               if (cart != null && !cart.isEmpty()) {
	                  out.println("<P><a href='cart?todo=view'>View Shopping Cart</a></p>");
	               }
	            }
	         }
			
			out.println("</body></html>");
		} catch (Exception ex) {
			out.println("<h3>Service not available. Please try again later!</h3></body></html>");
			Logger.getLogger(EntryServlet.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			out.close();
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(EntryServlet.class.getName()).log(
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
		// TODO Auto-generated method stub
	}

}
