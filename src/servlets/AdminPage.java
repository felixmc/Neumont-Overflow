package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import template.TemplateDispatcher;
import managers.FlagManager;
import entities.Flag;
import entities.Post;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	FlagManager flagManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Flag> allUnresolvedFlags =  flagManager.getAllUnresolvedFlags();
		Set<Post> allUnresolvedFlaggedPosts = getAllFlaggedPosts(allUnresolvedFlags); 
		request.setAttribute("allFlaggedPosts",allUnresolvedFlaggedPosts);
		TemplateDispatcher.forwardToTemplatePage(request, response, "adminpage");
	}

	private Set<Post> getAllFlaggedPosts(List<Flag> allFlags) {
		Set<Post> allFlaggedPosts = new HashSet<Post>();
		for(Flag flag : allFlags){
			allFlaggedPosts.add(flag.getPost());
		}
		return allFlaggedPosts;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
