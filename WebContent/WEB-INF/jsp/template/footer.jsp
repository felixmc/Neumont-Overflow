<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		</div>
	</div>
	<c:if test="${pageName eq 'ask' || pageName eq 'question'}">
		<script>
			var converter = Markdown.getSanitizingConverter();
		    
		    converter.hooks.chain("preBlockGamut", function (text, rbg) {
		        return text.replace(/^ {0,3}""" *\n((?:.*?\n)+?) {0,3}""" *$/gm, function (whole, inner) {
		            return "<blockquote>" + rbg(inner) + "</blockquote>\n";
		        });
		    });
		    
		    var editor = new Markdown.Editor(converter);
		    
		    editor.run();
		</script>
	</c:if>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/prefixfree.min.js"></script>
</body>
</html>