<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="l"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${pageTitle}</title>
	<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/favicon.ico">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css" type="text/css" />
	<c:if test="${pageName eq 'ask' || pageName eq 'question'}">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/editor.css" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Markdown.Converter.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Markdown.Sanitizer.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Markdown.Editor.js"></script>
	</c:if>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/timeago.jquery.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.userid && pageName ne 'login' }">
		<div class="modal">
			<l:login isModal="true" />
		</div>
	</c:if>
	<div class="modal-overlay"></div>
	<div id="page" class="${cssClass}">
		<header id="header">
			<div id="logo">
				<a href="${pageContext.request.contextPath}"></a>
			</div>
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<c:choose>
						<c:when test="${not empty sessionScope.userid}">
							<li><a href="#login">Login</a></li>
							<li><a href="${pageContext.request.contextPath}/SignUp">Sign Up</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/AskQuestion">Ask Question</a></li>
							<li><a href="${pageContext.request.contextPath}/Profile">Profile</a></li>
							<c:if test="${sessionScope.isadmin}">
								<li><a href="${pageContext.request.contextPath }/AdminPage">Admin Page</a></li>
							</c:if>
							<c:if test="${sessionScope.isteacher }">
								<li><a href="${pageContext.request.contextPath }/TeacherPage">Teacher Page</a></li>
							</c:if>
							<li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</header>
		<div id="content-wrapper">