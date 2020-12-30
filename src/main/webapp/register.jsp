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
    <script src="scripts/validateForms.js"></script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Register"/>
        <jsp:param name="actual" value="register"/>
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

    <form name="registerForm" onsubmit="return validateRegisterForm()" method="post" action="Servlet?command=Register" novalidate="novalidate">
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
