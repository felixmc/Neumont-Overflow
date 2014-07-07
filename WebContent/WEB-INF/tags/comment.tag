<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute name="post" type="entities.Comment" required="true" %>

<article class="post comment">
	<c:choose>
		<c:when test="${post.isDeleted()}">
			<p class="deleted">[deleted]</p>
		</c:when>
		<c:otherwise>
			<c:out value="${post.content}" />
		</c:otherwise>
	</c:choose>
	<span class="meta">
		<time datetime="<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ssZ" value="${post.dateCreated}"/>">${post.dateCreated}</time>
		by <a href="${pageContext.request.contextPath}/Profile/${post.author.id}">${post.author.username}</a>
	</span>
	<l:flag postid="${post.id }"/>
</article>