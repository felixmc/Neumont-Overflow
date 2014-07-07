package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import enums.Role;
import managers.CrudManager;
import managers.UserManager;

/**
 * Servlet implementation class PromoteUser
 */
@WebServlet("/PromoteUser")
public class PromoteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UserManager usermanager;
	@EJB
	CrudManager crudmanager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)usermanager.getUser(request.getParameter("email"));
		user = (User)crudmanager.get(user.getId(), User.class);
		user.getRoles().add(Role.Admin);
		crudmanager.update(user);
		response.sendRedirect(request.getContextPath()+"/AdminPage");
	}

}
