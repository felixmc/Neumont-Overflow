package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import template.TemplateDispatcher;
import entities.Answer;
import entities.Comment;
import entities.Question;
import entities.Tag;
import entities.User;
import managers.CrudManager;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"User"}))
@WebServlet("/Comment/*")
public class CreateComment extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	CrudManager crud;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = parseId(request.getPathInfo());
		
		if (id != 0)
		{
			Answer answer = (Answer) crud.get(id, Answer.class);
			User user = (User) crud.get((int)request.getSession().getAttribute("userid"), User.class);
			
			if (answer != null && user != null && request.getParameter("content") != null && !request.getParameter("content").isEmpty())
			{
				String content = request.getParameter("content");
				
				Comment comment = new Comment();
				comment.setAuthor(user);
				comment.setContent(content);
				comment.setPost(answer);
				
				crud.create(comment);
				
				response.sendRedirect( request.getContextPath() + "/Question/" + answer.getQuestion().getId() );
				return;
			}
		}

		response.sendError(response.SC_NOT_FOUND);
	}

	private int parseId (String pathInfo)
	{
		int id = 0;
		
		try
		{
			id = Integer.parseInt( pathInfo.split("/")[1] );			
		}
		catch (Exception e)
		{
			
		}
		
		return id;
	}

}