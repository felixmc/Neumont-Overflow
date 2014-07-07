<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l" %>

		<section id="content">
			<h2>Ask Question</h2>
			<form method="post" id="questionForm">
				<input type="text" name="title" placeholder="title">
				
				<l:markdownEditor placeholder="question details" />
				
				<label>Tags</label>
				<div class="tag-editor">
					<input id="addTag" type="text" placeholder="add tag" />
					<p id="tags"></p>
				</div>
				<p class="error">Authentication failed.</p>
				<input type="submit" value="Ask Question" class="butt" />
			</form>
		</section>
		<aside id="sidebar" class="wide top-padding">
			<p>Remember, <strong>Neumont Overflow</strong> does not allow academic misconduct.</p>
			<p>Please make your question as descriptive as possible and include any relevant code. The more detail you provide the better the answers will be.</p>
			<p><strong>Neumont Overflow</strong> allows markdown formatting in the question body.</p>
			<p>If you find the answer yourself, remember to come back and answer your own question so others might learn as well!<p>
			<br>
			<br>
			<br>
			<br>
			<p>Add tags to your Question. Tags are seperated by comma's. Hit enter after all the tags are put in to ensure that the tags are logged. <p>
		</aside>