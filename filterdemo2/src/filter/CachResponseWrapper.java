package filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CachResponseWrapper extends HttpServletResponseWrapper {
	protected HttpServletResponse origResponse = null;
	protected ServletOutputStream stream = null;
	protected PrintWriter writer = null;
	protected OutputStream cache = null;

	public CachResponseWrapper(HttpServletResponse response, OutputStream cache) {
		super(response);
		origResponse = response;
		this.cache = cache;
	}

	public ServletOutputStream creatOutputStream() throws IOException {
		return (new CacheResponseStream(origResponse, cache));
	}

	public void flushBuffer() throws IOException {
		stream.flush();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (writer != null) {
			throw new IllegalStateException(
					"getWriter() has already been called!");
		}
		if (stream == null) {
			stream = creatOutputStream();
		}
		return stream;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
			return writer;
		}
		if (stream != null) {
			throw new IllegalStateException(
					"getOutputStream() has already been called!");
		}
		stream = creatOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
		return writer;
	}
}
