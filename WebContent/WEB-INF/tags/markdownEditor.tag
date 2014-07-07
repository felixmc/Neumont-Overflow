<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="placeholder" required="true"%>

	<div class="wmd-panel">
		<div id="wmd-button-bar"></div>
		<textarea class="wmd-input" id="wmd-input" name="content" placeholder="${placeholder}"></textarea>
	</div>
	<label>Preview</label>
	<div id="wmd-preview" class="content"></div>