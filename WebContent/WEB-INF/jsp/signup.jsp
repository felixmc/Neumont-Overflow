<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<h2>Sign Up</h2>
	<form method="POST" id="signup">
	<input type="text" name="email" placeholder="email" autocomplete="off" required>
	<input type= "password" name="password" placeholder="password" autocomplete="off" required> 
	<input type="password" name="passwordConfirm" placeholder="confirm password" autocomplete="off" required>
	<p class="error"></p>
	<input class="butt" type="submit" value="Create Account"/>
	</form>