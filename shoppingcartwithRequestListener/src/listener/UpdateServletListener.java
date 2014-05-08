package listener;

import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class UpdateServletListener
 * 
 */
@WebListener
public class UpdateServletListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		sre.getServletRequest().removeAttribute("VALID");
		sre.getServletRequest().removeAttribute("txtTitle");
		sre.getServletRequest().removeAttribute("txtQuantity");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequest request = sre.getServletRequest();
		String button = request.getParameter("action");
		if (button == null) {
			Enumeration params = request.getParameterNames();
			boolean next = true;

			while (params.hasMoreElements() && next) {
				String paramName = (String) params.nextElement();
				if (paramName.startsWith("action_")) {
					next = false;
					button = request.getParameter(paramName);
					StringTokenizer stk = new StringTokenizer(paramName, "_");
					stk.nextToken(); // token này giá trị là "action"
					String suffix = stk.nextToken();// lấy giá trị phía sau
					String title = request.getParameter("txtTitle_" + suffix);
					String quantity = request.getParameter("txtQuantity_"
							+ suffix);
					request.setAttribute("txtTitle", title);

					int quan = -1;
					try {
						quan = Integer.parseInt(quantity);
					} catch (NumberFormatException e) {
						quan = -1;
					}
					if (quan < 0) {
						request.setAttribute("VALID", false);
					} else {
						request.setAttribute("VALID", true);
						request.setAttribute("txtQuantity", quan);
					}
				}

			}

		}

	}
}
