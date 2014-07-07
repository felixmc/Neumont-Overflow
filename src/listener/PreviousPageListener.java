package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebListener
public class PreviousPageListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();
		HttpSession session = request.getSession();
		String last = request.getRequestURI() + request.getQueryString();
		session.setAttribute("previousURL", last);
		String previous = (String) session.getAttribute("previous");

		if (previous == null)
			session.setAttribute("last", "home");
		else
			session.setAttribute("last", previous);

	}

}
