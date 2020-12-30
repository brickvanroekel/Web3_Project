<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/validateForms.js"></script>
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Admin overview"/>
        <jsp:param name="actual" value="admin"/>
    </jsp:include>
    <main>
        <h2>
            User Overview
        </h2>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>
            <c:choose>
                <c:when test="${not empty db}">
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
        </table>
        <h2>Contact overview</h2>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li><c:out value="${error}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form name ="adminFilterForm" onsubmit="return validateAdminFilterForm()" action="Servlet?command=Admin" method="post">
            <label for="startDate">From</label>
            <input type="date" name="startDate" id="startDate">
            <label for="endDate">Until</label>
            <input type="date" name="endDate" id="endDate">
            <label for="user">User</label>
            <select id="user" name="user">
                <c:forEach var="person" items="${db}" >
                    <option <c:out value="${person.userid}"/>>${person.userid}</option>
                </c:forEach>
            </select>
            <input type="submit" name="submit" value="Filter">
        </form>
        <table>
            <tr>
                <th>Date</th>
                <th>user</th>
                <th>Contactname</th>
                <th>Email</th>
                <th>Phone number</th>
            </tr>
            <c:choose>
                <c:when test="${not empty contacts}">
                    <c:forEach var="contact" items="${contacts}">
                        <tr>
                            <td><c:out value="${contact.timestamp}"/></td>
                            <td><c:out value="${contact.contact}"/></td>
                            <td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
                            <td><c:out value="${contact.email}"/></td>
                            <td><c:out value="${contact.gsm}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>There are no contacts registered</p>
                </c:otherwise>
            </c:choose>
        </table>
        <h2>Covid-19 tests</h2>
        <table>
            <tr>
                <th>user</th>
                <th>date</th>
            </tr>
            <c:choose>
                <c:when test="${not empty tests}">
                    <c:forEach var="test" items="${tests}">
                        <tr>
                            <td><c:out value="${test.userid}"/></td>
                            <td><c:out value="${test.date}"/></td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>There are no tests registered</p>
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