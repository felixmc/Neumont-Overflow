<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Why are you flagging?</title>
</head>
<body>
<c:choose>
	<c:when test="${isadmin }">
		<Form method="post" action="AdminDeletePost?postid=${postid }&userid=${userid}">
		Why are you deleting the post?
	</c:when>
	<c:otherwise>
		<Form method="post" action="FlagPost?postid=${postid }&userid=${userid}">
		Why are you flagging this post?
	</c:otherwise>
</c:choose>

<select name="type">
<option value="spam">Spam</option>
<option value="academicmisconduct">Academic Misconduct</option>
<option value="offtopic">Off Topic</option>
<option value="offensive">Offensive</option>
</select>
<input type="submit">
</Form>
</body>
</html>