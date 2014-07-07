package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Question;
import entities.Tag;
import template.TemplateDispatcher;
import managers.CrudManager;

@WebServlet("/Question/*")
public class ViewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	CrudManager crud;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = parseId(request.getPathInfo());
		
		if (id != 0)
		{
			Question question = (Question) crud.get(id, Question.class);
			if (question != null && !question.isDeleted())
			{
				request.setAttribute("question", question);
				request.setAttribute("tags", crud.getTop(Tag.class, 100));
				request.setAttribute("pageTitle", question.getTitle() + " | Neumont Overflow");
				TemplateDispatcher.forwardToTemplatePage(request, response, "question");
				return;
			}
		}

		response.sendError(response.SC_NOT_FOUND);
	}

	public static int parseId (String pathInfo)
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