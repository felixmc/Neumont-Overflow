<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l"%>
		<section id="content">
			<l:ratedPost post="${question}" />
			<c:forEach var="answer" items="${question.answers}">
				<l:ratedPost post="${answer}" />
			</c:forEach>
			<c:if test="${not empty sessionScope.userid && question.open}">
				<l:answerForm question="${question}" />
			</c:if>
		</section>
		<aside id="sidebar">
			<h3>Tags</h3>
			<c:forEach var="tag" items="${tags}">
				<l:questionTag showCount="true" tag="${tag}" />
			</c:forEach>
		</aside>