<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/validateForms.js"></script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Contacts"/>
        <jsp:param name="actual" value="contacts"/>
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

        <h2>Contact overview</h2>
        <form name ="contactFilterForm" onsubmit="return validateContactFilterForm()" action="Servlet?command=FilterContacts" method="post">
            <label for="startDate">From</label>
            <input type="date" name="startDate" id="startDate">
            <label for="endDate">Until</label>
            <input type="date" name="endDate" id="endDate">
            <input type="submit" name="submit" value="Filter">
        </form>
        <table>
            <tr>
                <th>Date & hour</th>
                <th>Name</th>
                <th>e-mail</th>
                <th>mobile</th>
                <!--<th>test-result</th>-->
            </tr>
            <c:choose>
            <c:when test="${not empty contacts}">
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><c:out value="${contact.timestamp}"/></td>
                    <td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
                    <td><c:out value="${contact.email}"/></td>
                    <td><c:out value="${contact.gsm}"/></td>
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
        <form method="post" action="Servlet?command=AddContact" novalidate="novalidate">
            <p>
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" required value="<c:out value="${firstNamePreviousValue}"/>">
            </p>
            <p>
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" required value="<c:out value="${lastNamePreviousValue}"/>">
            </p>
            <p>
                <label for="date">Date</label>
                <input type="date" id="date" name="date" value="<c:out value="${datePreviousValue}"/>" required>
            </p>
            <p>
                <label for="hour">Hour</label>
                <input type="time" id="hour"  name="hour" required value="<c:out value="${hourPreviousValue}"/>">
            </p>
            <p>
                <label for="gsm">GSM</label>
                <input type="gsm" id="gsm"  name="gsm" required value="<c:out value="${gsmPreviousValue}"/>">
            </p>
            <p>
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required value="<c:out value="${emailPreviousValue}"/>">
            </p>

            <p><input type="submit" id="addContact" value="Add contact"></p>

        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>