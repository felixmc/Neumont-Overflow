package servlets;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Flag;
import entities.Flag.FlagType;
import entities.Post;
import entities.User;
import managers.CrudManager;
import managers.UserManager;
import template.TemplateDispatcher;

/**
 * Servlet implementation class FlagPost
 */
@WebServlet("/FlagPost/*")
public class FlagPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CrudManager crud;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlagPost() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		Integer userid=(Integer)(session.getAttribute("userid"));
		
		User user = (User) crud.get(userid, User.class);		
		Post post = (Post) crud.get(postid, Post.class);
		String flagType = request.getParameter("type");
		FlagType type = null;
		switch(flagType){
		case "spam": type = FlagType.Spam; break;
		case "academicmisconduct": type=FlagType.AcademicMisconduct; break;
		case "offtopic": type=FlagType.OffTopic; break;
		case "offensive": type=FlagType.Offensive; break;
		}
		Flag flag = new Flag();
		flag.setDateCreated(new Date());
		flag.setFlaggingUser(user);
		flag.setPost(post);
		flag.setType(type);
		crud.create(flag);
		TemplateDispatcher.forwardToTemplatePage(request, response, "thankyouforflagging");
	}

}
