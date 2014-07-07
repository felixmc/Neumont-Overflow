<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="isModal" type="Boolean" required="true" %>

	<h2>Login</h2>
	<form method="POST" action="${pageContext.request.contextPath}/Login" id="loginForm">
		<input type="text" placeholder="email" name="email" />
		<input type="password" name="password" placeholder="password" />
		<p class="error">Authentication failed.</p>
		<input type="submit" value="Login" class="butt" />
		<c:if test="${isModal}">
			<input type="button" class="butt butt-red hide-modal" value="Close" />
		</c:if>
	</form>