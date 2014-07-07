package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Question;
import entities.Tag;
import entities.User;
import managers.CrudManager;
import managers.TagManager;
import template.TemplateDispatcher;

/**
 * Servlet implementation class AskQuestion
 */
@WebServlet("/AskQuestion")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"User"}))
public class AskQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CrudManager crudManager;

	@EJB
	private TagManager tm;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("pageTitle", "Ask Question | Neumont Overflow");
		TemplateDispatcher.forwardToTemplatePage(request, response, "ask");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain");
		PrintWriter pw = response.getWriter();
		
		int id = (Integer)request.getSession().getAttribute("userid");
		
		User u = (User) crudManager.get(id, User.class);
		
		String content = request.getParameter("content");
		
		String title = request.getParameter("title");
		
		String tagsp = request.getParameter("tags");
		
		if ((content == null || content.isEmpty()) || (title == null || title.isEmpty()) || (tagsp == null || tagsp.isEmpty()))
		{
			pw.write( "Please fill out the whole form." );
			pw.flush();
			return;
		}
		
		String[] tempTags = tagsp.split(",");

		Question q = new Question();
		
		q.setAuthor(u);
		q.setContent(content);
		q.setTitle(title);
		q.setDateCreated(new Date());
		q.setDeleted(false);
		q.setOpen(true);
		q.setScore(0);
		crudManager.create(q);
		
		List<Tag> tags = new ArrayList<Tag>();
		
		for(String s: tempTags ){
			if (!s.isEmpty())
			{
				Tag t = new Tag();
				t.setDateCreated(new Date());
				t.setName(s);
				if(tm.tagExists(t)){
					t = tm.getTagNamed(t.getName());
					t.getQuestions().add(q);
					crudManager.update(t);
				}
				else{
					t.getQuestions().add(q);
					crudManager.create(t);
				}
				
				tags.add(t);
			}
		}
		
		q.setTags(tags);
		crudManager.update(q);
		
		pw.write( "ok " + request.getContextPath() + "/Question/" + q.getId() );
		pw.flush();
	}
	
}