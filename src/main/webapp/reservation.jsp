<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Reservation</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/validateForms.js"></script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Reservation"/>
        <jsp:param name="actual" value="reservation"/>
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

    <form name="reservationForm" onsubmit="return validateReservationForm()" method="post" action="Servlet?command=Reservation" novalidate="novalidate">
        <p>
            <label for="date">Date</label>
            <input type="date" id="date" name="date" value="<c:out value="${datePreviousValue}"/>" required >
        </p>
        <p>
            <label for="hour">Arrival</label>
            <input type="time" id="hour" name="hour" required value="<c:out value="${hourPreviousValue}"/>" >
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