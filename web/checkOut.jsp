<%-- 
    Document   : checkout
    Created on : Jan 17, 2021, 11:19:09 AM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}"/>
        <form action="MainController" method="POST">


            <c:if test="${not empty user}">
                Name: <input type="text" name="txtFullName" value="${user.fullname}" required="required"/></br>
                Phone: <input type="text" name="txtPhoneNumber" value="${user.phoneNumber}" required="required"/></br>
                Address: <input type="text" name="txtAddress" value="${user.address}" required="required"/></br>


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
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="itemKey" items="${item}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            ${itemKey.value.name}
                                            <input type="hidden" name="txtId" value="${itemKey.value.id}" />
                                        </td>
                                        <td>
                                            ${itemKey.value.quantity}
                                        </td>
                                        <td>
                                            <c:set var="price" value="${itemKey.value.price}" />
                                            <c:set var="quantity" value="${itemKey.value.quantity}"/>
                                            ${price}$
                                        </td>
                                        <td>
                                            ${price*quantity}$
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="submit" value="Confirm" name="btAction" />
                    </c:if>
                </c:if>
            </c:if>
        </form>
        <c:if test="${empty user}">
            <a href="login.jsp">Click here to login</a>
        </c:if>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <c:if test="${not empty error}">
            <font color="red" > ${error}. Please try again</font>
        </c:if>
            <a href="viewCart.jsp">Go to your cart</a>
    </body>
</html>
