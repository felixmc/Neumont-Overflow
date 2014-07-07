package listener;

import javax.ejb.EJB;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import managers.CrudManager;
import managers.UserManager;

/**
 * Application Lifecycle Listener implementation class LoginListener
 *
 */
@WebListener
public class LoginListener implements ServletRequestListener {

	@EJB
	UserManager um;
	
    public void requestDestroyed(ServletRequestEvent sre) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre) {
       
    	HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
    	if(request.getRemoteUser() != null && request.getServletContext().getAttribute("user") == null)
    	{
    		int id = um.getUserId(request.getRemoteUser());
    		    		
    		request.getSession().setAttribute("userId", id);
    		
    		//this user is the email
    		request.getSession().setAttribute("user", request.getRemoteUser());
    	}
    	
    }
	
}
