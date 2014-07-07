<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="tag" type="entities.Tag" required="true" %>
<%@ attribute name="showCount" type="Boolean" required="false" %>

<c:if test="${tag.getLivePostCount() > 0 }">
<span class="question-tag">
	<a href="${pageContext.request.contextPath}/?tagId=${tag.id}">
		<c:out value="${tag.name}" />
		<c:if test="${not empty showCount && showCount}">
			<span class="count">${tag.getLivePostCount()}</span>
		</c:if>
	</a>
</span>
</c:if>