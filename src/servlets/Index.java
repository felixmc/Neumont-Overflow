package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.QuestionManager;
import template.TemplateDispatcher;
import entities.ManagedEntity;
import entities.Question;
import entities.Tag;

@WebServlet(urlPatterns = { "", "/Index" })
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// @EJB
	// CrudManager crud;
	//
	// @EJB
	// QuestionManager qm;

	List<Question> getQuestions() {
		List<Question> questions = new ArrayList<>();

		return questions;
	}

	List<ManagedEntity> getTags() {
		List<ManagedEntity> tags = new ArrayList<>();

		// Tag tag = new Tag();
		// tag.setName("tag");
		// tag.setDateCreated(new Date());
		// tag.setQuestions(new ArrayList<Question>());
		// tag.setId(1);
		//
		// tags.add(tag);

		return tags;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer tagId = request.getParameter("tagId") == null ? null : Integer
				.parseInt(request.getParameter("tagId"));

		// if (tagId != null) {
		// request.setAttribute("filterTag", crud.get(tagId, Tag.class));
		// }

		request.setAttribute("questions", getQuestions());

		List<ManagedEntity> tags = getTags();
		Collections.sort(tags, new Comparator<ManagedEntity>() {
			@Override
			public int compare(ManagedEntity o1, ManagedEntity o2) {
				return ((Tag) o2).getQuestions().size()
						- ((Tag) o1).getQuestions().size();
			}
		});

		request.setAttribute("tags", tags);

		TemplateDispatcher.forwardToTemplatePage(request, response, "home");
	}

	private List<QuestionManager.Param> getParams(HttpServletRequest request) {
		List<QuestionManager.Param> params = new ArrayList<>();

		for (String param : request.getParameterMap().keySet()) {
			try {
				if (request.getParameter(param).equals("checked"))
					params.add(QuestionManager.Param.valueOf(param));
			} catch (IllegalArgumentException e) {

			}
		}

		if ("score".equals(request.getParameter("sortBy"))) {
			params.add(QuestionManager.Param.byScore);
			request.setAttribute("scoreSelected", "selected");
		}

		return params;
	}

}