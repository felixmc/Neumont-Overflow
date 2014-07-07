package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.CrudManager;
import entities.User;
import enums.Role;
import template.TemplateDispatcher;

@WebServlet("/SignUp")
@DeclareRoles("user")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Pattern emailPattern = Pattern.compile("[a-zA-Z0-9\\._]+@(student.)?neumont.edu");
	private static final Pattern teacherEmailPattern = Pattern.compile("[a-zA-Z0-9\\._]+@neumont.edu");

	@EJB
	CrudManager crudManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute("userid") == null)
		{
			request.setAttribute("pageTitle", "Sign Up | Neumont Overflow");
			TemplateDispatcher.forwardToTemplatePage(request, response, "signup", "mini");
		}
		else
			response.sendRedirect( request.getContextPath() );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");

		boolean confirmedPassword = password.equals(passwordConfirm) && password.length() >= 6;
		boolean confirmedEmail = confirmApprovedEmail(email);
		boolean isTeacherEmail = isTeacherEmail(email);
		try{
			if(confirmedPassword && confirmedEmail){
				User user = new User();
				enums.Role role = Role.User;
				user.setEmail(email);
				user.setNewPassword(password);
				user.setDateCreated(new Date());
				user.getRoles().add(role);
				if(isTeacherEmail(email)) 
					user.getRoles().add(Role.Teacher);
				crudManager.create(user);
				request.getRequestDispatcher("Login").forward(request, response);
			}
			else{
				response.setContentType("text/plain");
				PrintWriter pw = response.getWriter();
				pw.write( !confirmedEmail ? "Invalid email address." : password.length() < 6 ? "Password is too short." : "Passwords did not match." );
				pw.flush();
			}
		} catch(Exception e){
			response.setContentType("text/plain");
			PrintWriter pw = response.getWriter();
			pw.write( !confirmedEmail ? "Invalid email address." : password.length() < 6 ? "Password is too short." : "Passwords did not match." );
			pw.flush();
		}

	}

	private boolean isTeacherEmail(String email) {
		return teacherEmailPattern.matcher(email).matches();
	}

	private boolean confirmApprovedEmail(String email) {
		return emailPattern.matcher(email).matches();
	}

}
