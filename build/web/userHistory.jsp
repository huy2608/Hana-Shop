<%-- 
    Document   : userHistory
    Created on : Jan 17, 2021, 3:12:09 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User History Page</title>
    </head>
    <body>
        <h1>Your History</h1>
        <form action="MainController" method="POST">
            Search: <input type="date" name="calendar" value="${requestScope.DATE}" />
            <input type="submit" value="Search By Date" name="btAction"/>
        </form>
        <c:set var="welcome" value="${sessionScope.FULLNAME}"/></br>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:set var="list" value="${sessionScope.BILLLIST}"/>
        <c:if test="${not empty user}">
            <c:if test="${not empty welcome}">
                Welcome <font color="blue"> ${welcome} </font></br>
            </c:if>
            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Bill Serial</th>
                            <th>Create Date</th>
                            <th>Content</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="bill" items="${list}" varStatus="counter">
                            <tr>
                                <td>${counter.count}.</td>
                                <td>${bill.billId}</td>
                                <td>${bill.createDate}</td>
                                <td>${bill.description}</td>
                                <td>${bill.total}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty list}">
                Empty History
            </c:if>
        </c:if>
    </body>
</html>
