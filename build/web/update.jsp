<%-- 
    Document   : upload
    Created on : Jan 12, 2021, 8:10:17 AM
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
        <c:set var="error" value="${requestScope.ERRORFILE}"/>
        <form action="UpdateController" method="POST" enctype="multipart/form-data">
            Name: <input type="text" minlength="0" maxlength="256" name="txtName" value="${param.foodName}" /></br>
            Image: <input type="file" name="file" value="" accept=".png"/></br>
            <input type="hidden" name="image" value="${param.foodImage}" />
            <img src="${param.foodImage}"width="50" height="50"/></br>
            Description: <input type="text" minlength="0" maxlength="500" name="txtDescription" value="${param.foodDescription}" /></br>
            Price: <input type="text" min="0" max="1000" name="txtPrice" value="${param.foodPrice}" /></br>
            Quantity: <input type="text"min="0" max="1000" name="txtQuantity" value="${param.foodQuantity}" />
            Category: <select name="cbCategory"></br>
                <option <c:if test="${param.foodCategory eq 'Foods'}">selected</c:if>>Foods</option>
                <option <c:if test="${param.foodCategory eq 'Drinks'}">selected</c:if>>Drinks</option>
                </select>
                Status: <select name="cbStatus"></br>
                    <option <c:if test="${param.foodStatus eq '1'}">selected</c:if>>Active</option>
                <option <c:if test="${param.foodStatus eq '0'}">selected</c:if>>Inactive</option>
                </select>
                <input type="hidden" name="txtId" value="${param.foodId}" />
            <input type="hidden" name="searchValue" value="${param.searchValue}" />
            <input type="hidden" name="searchName" value="${param.searchName}" />
            <input type="hidden" name="searchBeginMoney" value="${param.searchBeginMoney}" />
            <input type="hidden" name="searchEndMoney" value="${param.searchEndMoney}" />
            <input type="hidden" name="searchCategory" value="${param.searchCategory}" />
            <input type="submit" value="Update" name="btAction" />
        </form>
        <a href="search.jsp">Return to search page</a>
    </body>
</html>
