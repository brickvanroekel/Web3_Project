<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.model.Contact" %>
<%@ page import="domain.service.ContactTracingService" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Contacts</title>
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
                <li><a href="Servlet?command=Register">Register</a></li>
                <li><a href="Servlet?command=Reservation">Reservation</a></li>
                <li id="actual"><a href="Servlet?command=Contacts">Contacts</a></li>
            </ul>
        </nav>

    </header>
    <main>
        <h2>Contact overview</h2>
        <table>
            <tr>
                <th>Date</th>
                <th>Hour</th>
                <th>Name</th>
            </tr>
            <c:choose>
            <c:when test="${not empty db}">
            <c:forEach var="contact" items="${db}">
                <tr>
                <td>${contact.date}</td>
                <td>${contact.hour}</td>
                <td>${contact.firstName} ${contact.lastName}</td>
                </tr>
            </c:forEach>
            <caption>Contacts Overview</caption>

        </c:when>

        <c:otherwise>
            <p>There are no contacts</p>
        </c:otherwise>
        </c:choose>

        </table>

        <h2>Add contact</h2>
            <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
            </c:if>
        <form method="post" action="Servlet?command=AddContact" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->

            <p>
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" required value="${firstNamePreviousValue}">
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" required value="${lastNamePreviousValue}">
            </p>
            <p>
                <label for="date">Date</label>
                <input type="date" id="date" name="date" value="${datePreviousValue}" required>
            </p>
            <p>
                <label for="hour">Hour</label>
                <input type="time" id="hour"  name="hour" required value="${hourPreviousValue}">
            </p>
            <p>
                <label for="gsm">GSM</label>
                <input type="gsm" id="gsm"  name="gsm" required value="${gsmPreviousValue}">
            </p>
            <p>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required value="${emailPreviousValue}">
            </p>

            <p><input type="submit" id="addContact" value="Add contact"></p>

        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>