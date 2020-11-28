<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Scalini Mode</span></h1>
        <nav>
            <ul>
                <li><a href="Servlet?command=Home">Home</a></li>
                <li><a href="Servlet?command=Overview">Overview</a></li>
                <li id="actual"><a href="Servlet?command=Register">Register</a></li>
                <li><a href="Servlet?command=Reservation">Reservation</a></li>
                <c:if test="${person!=null}">
                    <li><a href="Servlet?command=RegisterTests">Register Test Result</a></li>
                </c:if>
                <li><a href="Servlet?command=Contacts">Contacts</a></li>
            </ul>
        </nav>
        <h2>
            Register
        </h2>

    </header>
    <main>
    <c:if test="${not empty errors}">
        <div class="alert-danger">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>


    <form method="post" action="Servlet?command=SignUp" novalidate="novalidate">
        <!-- novalidate in order to be able to run tests correctly -->
        <p>
            <label for="userid">User id</label>
            <input type="text" id="userid" name="userid" value="<c:out value="${useridPreviousValue}"/>" required>
        </p>
        <p>
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" required value="<c:out value="${firstNamePreviousValue}"/>">
        </p>
        <p>
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" required value="<c:out value="${lastNamePreviousValue}"/>">
        </p>
        <p>
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required value="<c:out value="${emailPreviousValue}"/>">
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password"  name="password" required value="<c:out value="${passwordPreviousValue}"/>">
        </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>

    </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
