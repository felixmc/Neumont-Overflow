package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Flag;
import managers.CrudManager;

/**
 * Servlet implementation class ResolveFlag
 */
@WebServlet("/ResolveFlag/*")
public class ResolveFlag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	CrudManager crudManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResolveFlag() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer flagId = ViewQuestion.parseId(request.getPathInfo());
		Flag flag = (Flag)crudManager.get(flagId, Flag.class);
		flag.setResolved(true);
		crudManager.update(flag);
		response.sendRedirect(request.getContextPath()+"/AdminPage");
	}

}
