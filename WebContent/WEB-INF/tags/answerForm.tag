<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l"%>

<%@ attribute name="question" type="entities.Question" required="true" %>

<section class="answerForm">
	<h2>Answer Question</h2>
	<form action="${pageContext.request.contextPath}/Answer/${question.id}" method="POST">
		<l:markdownEditor placeholder="answer description" />
		<input type="submit" class="butt" value="Answer" />
	</form>
</section>