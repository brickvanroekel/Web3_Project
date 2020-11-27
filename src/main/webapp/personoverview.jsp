<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <header>
        <h1><span>Scalini Mode</span></h1>
        <nav>
            <ul>
                <li><a href="Servlet?command=Home">Home</a></li>
                <li id="actual"><a href="Servlet?command=Overview">Overview</a></li>
                <li><a href="Servlet?command=Register">Register</a></li>
                <li><a href="Servlet?command=Reservation">Reservation</a></li>
                <li><a href="Servlet?command=Contacts">Contacts</a></li>
            </ul>
        </nav>


    </header>
    <main>
        <h2>
            User Overview
        </h2>
    <table>
        <tr>
            <th>E-mail</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Reservation</th>
        </tr>
        <c:choose>
            <c:when test="${not empty db}">
                <c:forEach var="person" items="${db}">
                    <tr>
                        <td>${person.email}</td>
                        <td>${person.firstName}</td>
                        <td>${person.lastName}</td>
                        <%--
                        <c:when test="${user==null}}">
                            <td>Log in to see reservations</td>
                        </c:when>
                        <c:otherwise>
                            <c:when test="${not empty reservations}">
                                <td> ${person.getReservations}</td>
                            </c:when>">
                            <c:otherwise>
                                <td>No reservations</td>
                            </c:otherwise>
                        </c:otherwise>
                        --%>
                    </tr>
                </c:forEach>

    </table>
            </c:when>
                <c:otherwise>
                    <p>There is nobody registered.</p>
                </c:otherwise>
        </c:choose>
</main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>