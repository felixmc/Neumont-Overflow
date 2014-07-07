<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l" %>
	
		<c:if test="${not empty filterTag}">
			<h3>Posts tagged with "${filterTag.name}"</h3>
		</c:if>
		<section id="content">
			<c:forEach var="question" items="${questions}">
				<l:questionPreview question="${question}" />
			</c:forEach>
		</section>
		<aside id="sidebar">
			<c:if test="${not empty sessionScope.userid}">
				<p>Hello <strong>${sessionScope.username}</strong>!</p>
			</c:if>
			<h3>Sorting & Filtering</h3>
			<form method="GET" class="filters">
				<fieldset class="sorting">
					<label>Sort by</label>
					<select name="sortBy">
						<option>time</option>
						<option value="score" ${scoreSelected}>score</option>
					</select>
				</fieldset>
				<fieldset>
					<input type="checkbox" name="unanswered" value="checked" ${param.unanswered}/>
					<label>Unanswered Questions</label>
				</fieldset>
				<fieldset>
					<input type="checkbox" name="open" value="checked" ${param.open}/>
					<label>Open Questions</label>
				</fieldset>
			</form>
			<h3>Tags</h3>
			<c:forEach var="tag" items="${tags}">
				<l:questionTag showCount="true" tag="${tag}" />
			</c:forEach>
		</aside>