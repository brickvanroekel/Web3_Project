<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script src="scripts/validateForms.js"></script>
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Home"/>
			<jsp:param name="actual" value="index"/>
		</jsp:include>
		<main>
			<c:if test="${not empty errors}">
				<div class="alert-danger">
					<ul>
						<c:forEach items="${errors}" var="error">
							<li><c:out value="${error}"/></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<c:if test="${not empty succes}">
				<div class="alert-succes">
					<ul>
						<li><c:out value="${succes}"/></li>
					</ul>
				</div>
			</c:if>

			<c:if test="${person==null}">
				<h3>Please log in</h3>
				<form name="logInForm" onsubmit="return validateLogInForm()" method="post" novalidate="novalidate" action="Servlet?command=LogIn">
					<p><label for="userid">User id</label><input type="text" id="userid" name="userid" required></p>
					<p><label for="password">Password</label><input type="password" id="password" name="password" required></p>
					<p><input type="submit" id="logIn" value="Log in"></p>
				</form>
			</c:if>
			<c:if test="${person!=null}">
				<h3>Welcome <c:out value="${person.getFirstName()}"/></h3>
				<form method="post" action="Servlet?command=LogOut" novalidate="novalidate">
					<p><input type="submit" id="logOut" value="Log Out"></p>
				</form>
			</c:if>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>