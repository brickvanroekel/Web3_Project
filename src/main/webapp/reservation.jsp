<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Reservation</title>
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
                <li ><a href="Servlet?command=Register">Register</a></li>
                <li id="actual"><a href="Servlet?command=Reservation">Reservation</a></li>
                <li><a href="Servlet?command=Contacts">Contacts</a></li>
            </ul>
        </nav>
        <h2>
            Register
        </h2>

    </header><main>
    <c:if test="${not empty errors}">
        <div class="alert-danger">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>


    <form method="post" action="Servlet?command=Book" novalidate="novalidate">
        <p>
            <label for="date">Date</label>
            <input type="date" id="date" name="date" value="${datePreviousValue}" required >
        </p>
        <p>
            <label for="hour">Arrival</label>
            <input type="time" id="hour" name="hour" required value="${arrivalPreviousValue}" >
        </p>
        <p><input type="submit" id="book" value="Book"></p>

    </form>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>