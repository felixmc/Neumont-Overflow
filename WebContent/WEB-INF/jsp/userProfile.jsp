<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p><strong>User Name:</strong> ${user.username}</p>
<p><strong>User Score:</strong> ${user.userScore}</p>
<p><strong>User Roles:</strong>
		<c:forEach var="role" items="${user.roles}">
			<span class="role">${role}</span>
		</c:forEach>
</p>
<h3>Questions Asked</h3>
	<c:forEach var="question" items="${questionList}">
		<l:questionPreview question="${question}" />
	</c:forEach>
<h3>Answers Given To These Questions</h3>
	<c:forEach var="questionAnswered" items="${questionsAnswerList}">
		<l:questionPreview question="${questionAnswered}" />
	</c:forEach>