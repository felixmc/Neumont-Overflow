<%@ tag language="java" pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ attribute name="postid" required="true"%>
	<p class="flag">
	<a href="${pageContext.request.contextPath }/FlagPost/${postid }" target="_blank" id="flag"><!--img src="${pageContext.servletContext.contextPath }/static/images/Flag.png" 
	class="flag" height="25" width="32"/-->flag this post</a>
	</p>
