package servlets;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import entities.RatedPost;
import entities.User;
import entities.Vote;
import managers.CrudManager;
import managers.VoteManager;

@WebServlet("/CreateVote")
public class CreateVote extends HttpServlet {

@EJB
CrudManager crudManager;
@EJB
VoteManager voteManager;
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			doPost(req,resp);
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userid = (int) request.getSession().getAttribute("userid");
		User user =  (User) crudManager.get(userid, User.class);
		Boolean upvote = Boolean.parseBoolean(request.getParameter("upvote"));
		int postid = Integer.parseInt(request.getParameter("postid"));
		RatedPost post = (RatedPost) crudManager.get(postid, RatedPost.class);
		
		Vote vote;
		
		if(voteManager.voteExists(user, post)){
			vote = voteManager.getVote(user, post).get(0);
			int value = upvote?1:-1;
			if(vote.getValue() == value) vote.setValue(0);
			else vote.setValue(value);
			crudManager.update(vote);
		}
		else{
			vote = new Vote();
			vote.setDateCreated(new Date());
			vote.setVoter(user);
			vote.setPost(post);
			vote.setValue(upvote?1:-1);
		
			crudManager.create(vote);
		}
		
		post.getScore();
		
		crudManager.update(post);
		
		response.getOutputStream().println(post.getScore());
		response.getOutputStream().flush();
		//response.sendRedirect("Question/"+ postid);
	}
}
