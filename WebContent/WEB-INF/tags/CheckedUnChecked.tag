<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="answer" required="true" type="entities.Answer"%>

<c:choose>
	
	<c:when test="${!answer.question.open}">

		<c:if test="${answer.bestAnswer}">
			<div class="bestAnswer checked"></div>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${answer.question.author.id eq sessionScope.userid}">
			<c:url value="/PickBestAnswer" var="best">
				<c:param name="answerId" value="${answer.id}"></c:param>
			</c:url>
			<div class="bestAnswer"><a href="${best}" title="choose as best answer"></a></div>
		</c:if>
	</c:otherwise>

</c:choose>

