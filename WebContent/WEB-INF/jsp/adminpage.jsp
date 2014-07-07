<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="content">
		<c:forEach items="${allFlaggedPosts }" var="post">

			<h3>
				<a
					href="${pageContext.servletContext.contextPath}/Question/${post.id}"
					target="_blank">Post Id: <c:out value="${post.id }" /></a><br>
				Post Author:
				<c:out value="${post.author.email }" />
				<br>
			</h3>
			<c:forEach items="${post.flags }" var="flag">
				<c:if test="${not flag.resolved }">
					<p class="flagInfo">
						Flag Reason:
						<c:out value="${flag.type }" />
						<br> Flagging User:
						<c:out value="${flag.flaggingUser.email }" />
						<c:forEach items="flag.flaggingUser.roles" var="role">
						<c:if test="${role='Teacher' }"><br>TEACHER FLAG!</c:if>
						</c:forEach>
						<br> User Id:
						<c:out value="${flag.flaggingUser.email }" />
						<br> Date of Flag:
						<c:out value="${flag.dateCreated }" />
						<br> <a
							href="${pageContext.servletContext.contextPath }/ResolveFlag/${flag.id}">Mark
							as Resolved</a><br>
					</p>
					<br>
				</c:if>
			</c:forEach>
		</c:forEach>
	</div>
	<div id="sidebar">
	<l:modifyUser/>
	</div>
</body>
</html>