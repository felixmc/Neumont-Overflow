package tags;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import managers.CrudManager;
import entities.Post;
import entities.RatedPost;

public class howDidUserVote extends SimpleTagSupport {

	@EJB
	CrudManager crudManager;
	
	RatedPost post;
	
	public void setPost(RatedPost post){
		this.post = post;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext)getJspContext();	
		HttpSession session = context.getSession();
		HttpServletRequest request = (HttpServletRequest)context.getRequest();
		
		Integer userid = (Integer)session.getAttribute("userid");
		int vote = userid == null ? 0 : post.useresVote(userid);
		
		if(vote == -1){
			request.setAttribute("downvoteclass", "downvote rated");
			request.setAttribute("upvoteclass", "upvote");
		}
		else if(vote == 1){
			request.setAttribute("downvoteclass", "downvote");
			request.setAttribute("upvoteclass", "upvote rated");
		}
		else{
			request.setAttribute("downvoteclass", "downvote");
			request.setAttribute("upvoteclass", "upvote");
		}
		
	}
}
