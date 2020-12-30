<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Search"/>
        <jsp:param name="actual" value="search"/>
    </jsp:include>
    <main>
        <h2>Contact the following people</h2>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <table>
            <tr>
                <th>Name</th>
                <th>GSM</th>
                <th>Email</th>
            </tr>
            <c:choose>
                <c:when test="${not empty contacts}">
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
                            <td><c:out value="${contact.gsm}"/></td>
                            <td><c:out value="${contact.email}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>There are no contacts at risk</p>
                </c:otherwise>
            </c:choose>
        </table>
        <h2>Cancel following reservations</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Date & time</th>
            </tr>
            <c:choose>
                <c:when test="${not empty reservations}">
                    <c:forEach var="reservation" items="${reservations}">
                        <tr>
                            <td><c:out value="${reservation.id}"/></td>
                            <td><c:out value="${reservation.timestamp}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>There are no reservation that need to be cancelled</p>
                </c:otherwise>
            </c:choose>
        </table>

        <form method="post" action="Servlet?command=DeleteReservations">
            <p><input type="submit" id="deleteReservation" value="Cancel"></p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
