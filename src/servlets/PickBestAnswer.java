package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import template.TemplateDispatcher;

import managers.CrudManager;
import entities.Answer;
import entities.Question;

/**
 * Servlet implementation class PickBestAnswer
 */
@WebServlet("/PickBestAnswer")
public class PickBestAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@EJB
	CrudManager cm;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int answerId = Integer.parseInt(request.getParameter("answerId"));
		Answer answer = (Answer) cm.get(answerId, Answer.class);
		Question question = answer.getQuestion();
		
		answer.setBestAnswer(true);
		question.setOpen(false);
		
		cm.update(answer);
		cm.update(question);
		
		response.sendRedirect(request.getHeader("Referer"));
		
	}

}
