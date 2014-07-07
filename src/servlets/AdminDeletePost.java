package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Post;
import entities.Post.DeleteReason;
import managers.CrudManager;
import template.TemplateDispatcher;

/**
 * Servlet implementation class AdminDeletePost
 */
@WebServlet("/AdminDeletePost/*")
public class AdminDeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CrudManager crud;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer postid = ViewQuestion.parseId(request.getPathInfo());
		session.setAttribute("postid", postid);
		Integer userid = (Integer) request.getSession().getAttribute("userid");
		if(userid == null||userid==0) response.sendRedirect(request.getContextPath() + "/Login");
		else  TemplateDispatcher.forwardToTemplatePage(request, response, "flag");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer postid=(Integer)(session.getAttribute("postid"));
				
		Post post = (Post) crud.get(postid, Post.class);
		
		post.setDeleted(true);
		
		String deleteType = request.getParameter("type");
		DeleteReason type = null;
		switch(deleteType){
		case "spam": type = DeleteReason.Spam; break;
		case "academicmisconduct": type=DeleteReason.AcademicMisconduct; break;
		case "offtopic": type=DeleteReason.OffTopic; break;
		case "offensive": type=DeleteReason.Offensive; break;
		}
		
		post.setDeleteReason(type);
		
		crud.update(post);
		response.sendRedirect(request.getContextPath()+"/Index");
	}

}
