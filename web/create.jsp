<%-- 
    Document   : update
    Created on : Jan 9, 2021, 7:11:35 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <c:set var="welcome" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty welcome}">
            Welcome <font color="blue"> ${welcome} </font></br>   
        </c:if>
        <form action="CreateController" method="POST" enctype="multipart/form-data">
            <c:set var="error" value="${requestScope.ERRORFILE}"/>
            Id: <input type="text" minlength="1" maxlength="50" name="txtId" value="${requestScope.FOODID}" required="required"/></br>
            Name: <input type="text" minlength="1" maxlength="256" name="txtName" value="${requestScope.FOODNAME}" required="required"/></br>
            Description: <input type="text" minlength="1" maxlength="500" name="txtDescription" value="${requestScope.FOODDESCRIPTION}" required="required"/></br>
            Price: <input type="number" min="0" max="1000" step="0.01" name="txtPrice" value="${requestScope.PRICE}" required="required"/></br>
            Category: <select name="cbCategory">
                <option value="Foods" <c:if test="${requestScope.FOODCATEGORY eq 'Foods'}">selected</c:if>>Foods</option>
                <option value="Drinks" <c:if test="${requestScope.FOODCATEGORY eq 'Drinks'}">selected</c:if>>Drinks</option>
                </select></br>
                Quantity: <input type="number" min="0" max="1000" name="txtQuantity" value="${requestScope.FOODQUANTITY}" required="required" /></br>
            File: <input type="file" accept=".png" name="txtImage" required="required"/>
            <c:if test="${not empty error}">
                <font color="red"> ${error} </font></br>
            </c:if>
            <input type="submit" value="Create" name="btAction" />
        </form>
        <a href="search.jsp">Return to search page</a>
    </body>
</html>
