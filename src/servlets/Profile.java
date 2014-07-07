package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.GetMostRecentResults;
import managers.UserManager;
import template.TemplateDispatcher;
import entities.Answer;
import entities.Question;
import entities.User;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile/*")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	GetMostRecentResults gmrr;
	@EJB
	UserManager um;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int userId = 0;
		if (request.getPathTranslated() == null) {
			userId = (int) request.getSession().getAttribute("userid");
		} else {
			String tempinfo = request.getPathInfo();
			userId = Integer.parseInt(tempinfo.substring(1));
		}
		User user = um.getUser(userId);
		int userScore = user.getUserScore();

		List<Question> nQuestionList = gmrr.getNextNQuestion(5, userId);
		List<Answer> nAnswerList = gmrr.getNextNAnswer(5, userId);
		List<Question> questionsAnswered = new ArrayList<>();

		removeDealtedPosts(nQuestionList, nAnswerList);

		for (Answer answer : nAnswerList) {

			Question ques = answer.getQuestion();
			if (!answer.isDeleted()) {
				questionsAnswered.add(ques);
			}

		}

		request.setAttribute("pageTitle", user.getUsername()
				+ " on Neumont Overflow");
		request.setAttribute("user", user);
		request.setAttribute("questionList", nQuestionList);
		request.setAttribute("questionsAnswerList", questionsAnswered);
		TemplateDispatcher.forwardToTemplatePage(request, response,
				"userProfile");

	}

	private void removeDealtedPosts(List<Question> nQuestionList,
			List<Answer> nAnswerList) {
		Iterator<Question> qIt = nQuestionList.iterator();
		Iterator<Answer> aIt = nAnswerList.iterator();
		List<Answer> answersToRemove = new ArrayList<>();
		List<Question> questionToRemove = new ArrayList<>();

		while (qIt.hasNext()) {
			Question q = qIt.next();
			if (q.isDeleted()) {
				questionToRemove.add(q);
			}
		}

		while (aIt.hasNext()) {

			Answer a = aIt.next();
			if (a.isDeleted()) {
				answersToRemove.add(a);
			}
		}
		nAnswerList.removeAll(answersToRemove);
		nQuestionList.removeAll(questionToRemove);
	}

}
