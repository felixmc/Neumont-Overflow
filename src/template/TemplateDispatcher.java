package template;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TemplateDispatcher
{
	private static final String JSP_DIR = "/WEB-INF/jsp/";
	private static final String TEMPLATE_PATH = JSP_DIR + "template/page.jsp";

	public static void forwardToTemplatePage (HttpServletRequest request, HttpServletResponse response, String page)
	{
		forwardToTemplatePage(request, response, page, "");
	}
	
	public static void forwardToTemplatePage (HttpServletRequest request, HttpServletResponse response, String page, String cssClass)
	{
		if (request.getAttribute("pageTitle") == null)
		{
			request.setAttribute("pageTitle", "Neumont Overflow");	
		}
		
		request.setAttribute("pageName", page);
		request.setAttribute("cssClass", cssClass);
		RequestDispatcher dispatcher = request.getRequestDispatcher( TEMPLATE_PATH );
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}