<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="User overview"/>
        <jsp:param name="actual" value="personoverview"/>
    </jsp:include>
    <main>
    <table>
        <c:choose>
            <c:when test="${person.role == 'administrator'}">

                <c:choose>
                    <c:when test="${not empty db}">
                        <tr>
                            <th>E-mail</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                        </tr>
                        <c:forEach var="person" items="${db}">
                            <tr>
                                <td><c:out value="${person.email}"/></td>
                                <td><c:out value="${person.firstName}"/></td>
                                <td><c:out value="${person.lastName}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>There is nobody registered.</p>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <th>Reservations</th>
                </tr>

                <c:choose>
                    <c:when test="${not empty reservations}">
                        <tr>
                            <th>ID</th>
                            <th>Date & time</th>
                        </tr>
                        <c:forEach var="reservation" items="${reservations}">
                            <tr>
                                <td><c:out value="${reservation.id}"/></td>
                                <td><c:out value="${reservation.timestamp}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>There are no reservation</p>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <tr>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Reservation</th>
                </tr>
                <tr>
                    <td><c:out value="${person.email}"/></td>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                    <td>
                        <c:choose>
                        <c:when test="${not empty reservations}">
                        <c:forEach var="reservation" items="${reservations}">
                    <td><c:out value="${reservation}"/></td>
                    </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>No reservations</p>
                    </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>