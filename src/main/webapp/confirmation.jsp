<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Confirmation</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Scalini Mode</span>
        </h1>
        <nav>
            <ul>
                <li><a href="Servlet?command=Home">Home</a></li>
                <li><a href="Servlet?command=Overview">Overview</a></li>
                <li><a href="Servlet?command=Register">Register</a></li>
                <li><a href="Servlet?command=Reservation">Reservation</a></li>
                <c:if test="${person!=null}">
                    <li><a href="Servlet?command=RegisterTests">Register Test Result</a></li>
                </c:if>
                <li><a href="Servlet?command=Contacts">Contacts</a></li>
            </ul>
        </nav>
        <h2>Thank you for your reservation!</h2>
    </header>

    <main>
        <p>You have booked a reservation <c:out value="${person.reservation}"/></p>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>