package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import managers.CrudManager;
import managers.UserManager;

/**
 * Servlet implementation class BanUser
 */
@WebServlet("/BanUser")
public class BanUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UserManager usermanager;
	@EJB
	CrudManager crudmanager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BanUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = usermanager.getUser(request.getParameter("email"));
		user = (User)crudmanager.get(user.getId(), User.class);
		if(!userIsAdmin(user))
		user.setBanned(true);
		crudmanager.update(user);
	}

	private boolean userIsAdmin(User user) {
		boolean isadmin = false;
		for(enums.Role role : user.getRoles()){
			isadmin=role.equals(enums.Role.Admin);
		}
		return false;
	}

}
