<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tag" uri="http://www.overflow.neumont.edu/resources/tags"%>

<%@ attribute name="post" type="entities.RatedPost" required="true"%>

<c:if test="${not empty sessionScope.userid}">
	<c:set var="isEnabled" value="enabled" />
</c:if>

<div class="voteWrapper ${isEnabled}">
<tag:uservote post="${post }" />
<a href="${pageContext.request.contextPath }/CreateVote?upvote=true&postid=${post.id}">
	<div class="${upvoteclass } vote"></div>
</a>
<p class="voteScore">${post.score}</p>
<a href="${pageContext.request.contextPath }/CreateVote?upvote=false&postid=${post.id}">
	<div class="${downvoteclass } vote"></div>
</a>
</div>