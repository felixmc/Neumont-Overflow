<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l"%>

<%@ attribute name="post" type="entities.RatedPost" required="true"%>

<c:set var="isQuestion" value="${post.class.simpleName == 'Question'}" />

<c:if test="${not isQuestion}">
	<c:set var="cssClass" value=" answer" />
</c:if>

<article class="post${cssClass}">
	<c:if test="${isQuestion}">
		<h2><c:out value="${post.title}" /></h2>
	</c:if>
	<c:if test="${not isQuestion}">
		<l:CheckedUnChecked answer="${post}"/>
	</c:if>
	<l:ratingArrowsTag post="${post}" />
	<section class="content">
		<c:choose>
			<c:when test="${post.isDeleted() && not isQuestion}">
				<p class="deleted">[deleted]</p>
			</c:when>
			<c:otherwise>
				${post.content}
			</c:otherwise>
		</c:choose>
	</section>
	<c:if test="${isQuestion}">
		<p class="tags">
			<c:forEach var="tag" items="${post.tags}">
				<l:questionTag tag="${tag}" />
			</c:forEach>
		</p>
	</c:if>
	<p class="meta">
		<time datetime="<fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm:ssZ" value="${post.dateCreated}"/>">${post.dateCreated}</time>
		by <a href="${pageContext.request.contextPath}/Profile/${post.author.id}">${post.author.username}</a>
	
		<c:if test="${not empty isadmin }">
			<c:choose>
				<c:when test="${isadmin}">
					<l:adminQuestion postid="${post.id}" />
				</c:when>
				<c:otherwise>
					<l:flag postid="${post.id}" />
				</c:otherwise>
			</c:choose>
		</c:if>
	</p>
	<c:if test="${not isQuestion}">
		<c:forEach var="comment" items="${post.comments}">
			<l:comment post="${comment}" />
		</c:forEach>
		<c:if test="${not empty sessionScope.userid}">
			<l:commentForm answer="${post}" />
		</c:if>
	</c:if>
</article>