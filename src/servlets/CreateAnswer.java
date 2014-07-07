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

import entities.Answer;
import entities.Comment;
import entities.Question;
import entities.User;
import managers.CrudManager;

@ServletSecurity(@HttpConstraint(rolesAllowed = {"User"}))
@WebServlet("/Answer/*")
public class CreateAnswer extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	CrudManager crud;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = parseId(request.getPathInfo());
		
		if (id != 0)
		{
			Question question = (Question) crud.get(id, Question.class);
			User user = (User) crud.get((int)request.getSession().getAttribute("userid"), User.class);
			
			if (question != null && user != null && request.getParameter("content") != null && !request.getParameter("content").isEmpty())
			{
				String content = request.getParameter("content");
				
				Answer answer = new Answer();
				answer.setAuthor(user);
				answer.setContent(content);
				answer.setQuestion(question);
				
				crud.create(answer);

				response.sendRedirect( request.getContextPath() + "/Question/" + id );
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