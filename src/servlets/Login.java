package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.UserManager;
import entities.User;
import enums.Role;
import template.TemplateDispatcher;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserManager userManager;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute("userid") == null)
			response.sendRedirect( request.getContextPath() );
		else
			response.sendRedirect( request.getHeader("Referer") );
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "ok";
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			request.logout();
			request.login(email, password);
			User currentUser = userManager.getUser(email);
			HttpSession session = request.getSession();
			session.setAttribute("userid", currentUser.getId());
			session.setAttribute("username", currentUser.getEmail().split("@")[0]);
			session.setAttribute("isadmin", determineAdminStatus(currentUser));
			session.setAttribute("isteacher", determineTeacherStatus(currentUser));
		} catch (Exception e) {
			message = "bad";
		}
		
		response.setContentType("text/plain");
		PrintWriter pw = response.getWriter();
		pw.write( message );
		pw.flush();
	}

	private Object determineTeacherStatus(User currentUser) {
		boolean isTeacher=false;
		for(Role role: currentUser.getRoles()){
			isTeacher = role.equals(Role.Teacher);
		}
		return isTeacher;
	}

	private Object determineAdminStatus(User currentUser) {
		boolean isAdmin = false;
		for(Role role: currentUser.getRoles()){
			isAdmin = role.equals(Role.Admin)?true:false;
		}
		return isAdmin;
	}

}