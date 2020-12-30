<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <h1><span>Scalini Mode</span></h1>
    <nav>
        <ul>
            <c:choose>
                <c:when test="${person == null}">
                    <li ${param.actual eq 'index'?"id = actual":""}><a href="Servlet?command=Home">Home</a></li>
                    <li ${param.actual eq 'register'?"id = actual":""}><a href="Servlet?command=Register">Register</a></li>
                </c:when>
                <c:otherwise>
                    <li ${param.actual eq 'index'?"id = actual":""}><a href="Servlet?command=Home">Home</a></li>
                    <c:if test="${person.role == 'administrator'}">
                        <li ${param.actual eq 'admin'?"id = actual":""}><a href="Servlet?command=Admin">Admin</a></li>
                    </c:if>
                    <li ${param.actual eq 'personoverview'?"id = actual":""}><a href="Servlet?command=Overview">Overview</a></li>
                    <li ${param.actual eq 'reservation'?"id = actual":""}><a href="Servlet?command=Reservation">Reservation</a></li>
                    <li ${param.actual eq 'registertest'?"id = actual":""}><a href="Servlet?command=RegisterTest">Register Test Result</a></li>
                    <li ${param.actual eq 'search'?"id = actual":""}><a href="Servlet?command=Search">Search</a></li>
                    <li ${param.actual eq 'contacts'?"id = actual":""}><a href="Servlet?command=Contacts">Contacts</a></li>
                </c:otherwise>
            </c:choose>

        </ul>
    </nav>
    <h2>
        ${param.title}
    </h2>
</header>
