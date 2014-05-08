package filter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CacheFilter
 */
@WebFilter("/CacheFilter")
public class CacheFilter implements Filter {
	ServletContext sc;
	FilterConfig fc;
	long cacheTimeout = Long.MAX_VALUE;

	/**
	 * Default constructor.
	 */
	public CacheFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.sc = null;
		this.fc = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		// check if was a resource that shouldn't be cached.
		String r = sc.getRealPath("");
		String path = fc.getInitParameter(request.getRequestURI());
		if (path != null && path.equals("nocache")) {
			chain.doFilter(request, response);
			return;
		}
		path = r + path;
		// customize to match parameters
		String id = request.getRequestURI() + request.getQueryString();
		// optionally append i18n sensitivity
		String localeSensitive = fc.getInitParameter("locale-sensitive");
		if (localeSensitive != null) {
			StringWriter ldata = new StringWriter();
			Enumeration locales = request.getLocales();
			while (locales.hasMoreElements()) {
				Locale locale = (Locale) locales.nextElement();
				ldata.write(locale.getISO3Language());
			}
			id = id + ldata.toString();
		}
		File tempDir = (File) sc.getAttribute("javax.servlet.context.tempdir");
		// get possible cache
		String temp = tempDir.getAbsolutePath();
		File file = new File(temp + id);

		// get current resource
		if (path == null) {
			path = sc.getRealPath(request.getRequestURI());
		}
		File current = new File(path);
		try {
			long now = Calendar.getInstance().getTimeInMillis();
			// set timestamp check
			if (!file.exists()
					|| (file.exists() && current.lastModified() > file
							.lastModified())
					|| cacheTimeout < now - file.lastModified()) {
				String name = file.getAbsolutePath();
				name = name
						.substring(
								0,
								name.lastIndexOf("/") == -1 ? 0 : name
										.lastIndexOf("/"));
				new File(name).mkdirs();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				CachResponseWrapper wrappedResponse = new CachResponseWrapper(
						response, baos);
				chain.doFilter(req, wrappedResponse);

				FileOutputStream fos = new FileOutputStream(file);
				fos.write(baos.toByteArray());
				fos.flush();
				fos.close();
			}
		} catch (ServletException e) {
			if (!file.exists()) {
				throw new ServletException(e);
			}
		} catch (IOException e) {
			if (!file.exists()) {
				throw e;
			}
		}

		FileInputStream fis = new FileInputStream(file);
		String mt = sc.getMimeType(request.getRequestURI());
		response.setContentType(mt);
		ServletOutputStream sos = res.getOutputStream();
		for (int i = fis.read(); i != -1; i = fis.read()) {
			sos.write((byte) i);
		}
		// pass the request along the filter chain
		chain.doFilter(req, res);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fc = fConfig;
		String ct = fc.getInitParameter("cacheTimeout");
		if (ct != null) {
			cacheTimeout = 60 * 1000 * Long.parseLong(ct);
		}
		this.sc = fConfig.getServletContext();
	}

}
