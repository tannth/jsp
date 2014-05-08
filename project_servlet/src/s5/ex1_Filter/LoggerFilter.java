package s5.ex1_Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoggerServlet
 */
public class LoggerFilter implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myReq = (HttpServletRequest) request;
		
		String reqURI = myReq.getRequestURI();
		System.out.println(reqURI);
		try {
			request.setAttribute("requestURI", reqURI);
			System.out.println("Before processing the Request");
			chain.doFilter(request, response);
			System.out.println ("Before sending response to the client."); 
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
