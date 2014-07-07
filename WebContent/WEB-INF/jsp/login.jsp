<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="l" %>

<c:set var="cssClass" value="mini" scope="request" />
<c:set var="pageName" value="login" scope="request" />

<jsp:include page="template/header.jsp" />
<l:login isModal="false" />
<jsp:include page="template/footer.jsp" />