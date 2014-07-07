<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="answer" type="entities.Answer" required="true" %>

<section class="comment form">
	<form action="${pageContext.request.contextPath}/Comment/${answer.id}" method="POST">
		<textarea name="content" placeholder="enter comment.."></textarea>
		<input type="submit" class="butt butt-small" value="Comment" />
	</form>
</section>