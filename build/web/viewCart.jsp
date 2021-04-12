<%-- 
    Document   : shoppingcart
    Created on : Jan 15, 2021, 12:56:54 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:if test="${not empty sessionScope}">
            <c:set var="welcome" value="${sessionScope.FULLNAME}"/></br>
            <c:if test="${not empty welcome}">
                Welcome <font color="blue"> ${welcome} </font>
            </c:if>
            <c:set var="admin" value="${sessionScope.ADMIN}"/>
            <form action="MainController" method="POST">
                <input type="submit" value="Log out" name="btAction" />
            </form>

            <c:set var="cart" value="${sessionScope.FOODCART}"/>
            <c:if test="${not empty cart}">
                <c:set var="item" value="${cart.foodCart}"/>
                <c:if test="${not empty item}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Remove</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="itemKey" items="${item}" varStatus="counter">
                            <form action="MainController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        ${itemKey.value.name}
                                        <input type="hidden" name="txtId" value="${itemKey.value.id}" />
                                    </td>
                                    <td>
                                        <input type="number" name="txtQuantity" min="1" max="10"value="${itemKey.value.quantity}" />$
                                        <input type="submit" value="Update Quantity" name="btAction"/>
                                    </td>
                                    <td>
                                        <c:set var="price" value="${itemKey.value.price}" />
                                        <c:set var="quantity" value="${itemKey.value.quantity}"/>
                                        ${price}$
                                    </td>
                                    <td>
                                        ${price*quantity}$
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove" name="btAction"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
                <form action="MainController" method="POST">
                    <input type="submit" value="Check out" name="btAction"/>

                </form>
            </c:if>
        </c:if>
    </c:if>
    <a href="search.jsp">Go back to shopping</a> 
</body>
</html>
