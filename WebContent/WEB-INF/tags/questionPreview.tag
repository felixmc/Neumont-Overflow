<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l" %>

<%@ attribute name="question" type="entities.Question" required="true" %>

<article class="question preview">
	<table class="stats">
		<tr>
			<td>${question.getScore()}</td>
			<td>${question.answers.size()}</td>
		</tr>
		<tr>
			<th>score</th>
			<th>answers</th>
		</tr>
	</table>
	<h3><a href="${pageContext.request.contextPath}/Question/${question.id}"><c:out value="${question.title}" /></a></h3>
	<p class="meta">
		<time datetime="<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ssZ" value="${question.dateCreated}"/>">${question.dateCreated}</time>
		by <a href="${pageContext.request.contextPath}/Profile/${question.author.id}">${question.author.username}</a>
	</p>
	<p class="tags">
		<c:forEach var="tag" items="${question.tags}" end="4">
			<l:questionTag tag="${tag}" />
		</c:forEach>
	</p>
</article>