<%-- 
    Document   : login
    Created on : Jan 4, 2021, 1:29:12 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="MainController" method="POST">
            <c:set var="Err" value="${requestScope.ERR}"/>
            Username: <input type="text" name="txtUsername" value="" /></br>
            Password: <input type="password" name="txtPassword" value="" /></br>
            <input type="submit" value="Login" name="btAction" /></br>
            <c:if test="${not empty Err}">
                <font color ="red">${Err}</font>
            </c:if>
        </form>
            <a href="search.jsp">Go Shopping</a>
    </body>
</html>
