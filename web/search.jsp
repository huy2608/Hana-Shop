<%-- 
    Document   : search
    Created on : Jan 5, 2021, 8:05:27 PM
    Author     : Shi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="welcome" value="${sessionScope.FULLNAME}"/>
        <c:if test="${not empty welcome}">
            Welcome <font color="blue"> ${welcome} </font></br>   
        </c:if>
        <c:set var="admin" value="${sessionScope.ADMIN}"/>
        <c:set var="user" value="${sessionScope.USER}"/>
        <c:set var="errorfile" value="${requestScope.ERRORFILE}"/>
        <c:if test="${not empty user}">
            <form action="MainController" method="POST">
                <input type="submit" value="History" name="btAction"/></br>
                <input type="submit" value="Log out" name="btAction" />
            </form>
        </c:if>
        <c:if test="${not empty admin}">
            <form action="MainController" method="POST">
                <input type="submit" value="Log out" name="btAction" />
            </form>
            <a href="create.jsp" style="font-size: 40px;">Create New Food</a>
        </c:if>
        <h1>Search Foods/Drinks </h1> 
        <form action="MainController" method="POST" >
            Search: <input type="text" minlength="0" maxlength="256" name="txtName" value="${param.txtName}" /></br>
            Money: <input type="number" min="0" max="1000" name="txtBegin" value="${param.txtBegin}" /> - <input type="number" min="0" max="1000" name="txtEnd" value="${param.txtEnd}" /></br>
            Category: <select name="cbCategory">
                <option value="" >--None--</option>
                <option value="Foods" <c:if test="${param.cbCategory eq 'Foods'}">selected</c:if>>Foods</option>
                <option value="Drinks" <c:if test="${param.cbCategory eq 'Drinks'}">selected</c:if>>Drinks</option>        
                </select>
                <input type="hidden" name="searchValue" value="${requestScope.SEARCHVALUE}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:if test="${not empty errorfile}">
            <font color="red"> Update fail. Please try again</font>
        </c:if>
        <c:set var="list" value="${requestScope.FOOD}"/>
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th></th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Type</th>
                            <c:if test="${not empty admin}">
                            <th>Update</th>
                            <th>Delete</th>
                            <th>Status</th>
                            </c:if>
                            <c:if test="${empty admin}">  
                            <th></th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter">
                        <tr>
                            <td>${counter.count}.</td>
                            <td>
                                <img src="${dto.image}" width="100" height="100"/>    
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.description}
                            </td>
                            <td>
                                ${dto.price}$
                            </td>
                            <td>
                                ${dto.category}
                            </td>
                            <c:if test="${not empty admin}">
                                <td>
                                    <c:url var="update" value="update.jsp">
                                        <c:param name="foodId" value="${dto.id}"/>
                                        <c:param name="foodName" value="${dto.name}"/>
                                        <c:param name="foodDescription" value="${dto.description}"/>
                                        <c:param name="foodImage" value="${dto.image}"/>
                                        <c:param name="foodPrice" value="${dto.price}"/>
                                        <c:param name="foodQuantity" value="${dto.quantity}"/>
                                        <c:param name="foodStatus" value="${dto.status}"/>
                                        <c:param name="foodCategory" value="${dto.category}"/>
                                        <c:param name="searchName" value="${param.txtName}"/>
                                        <c:param name="searchBeginMoney" value="${param.txtBegin}"/>
                                        <c:param name="searchEndMoney" value="${param.txtEnd}"/>
                                        <c:param name="searchCategory" value="${param.cbCategory}"/>
                                        <c:param name="searchValue" value="${searchValue}"/>
                                        <c:param name="btAction" value="Update"/>
                                    </c:url>
                                    <a href="${update}">Update</a>    
                                </td>
                        <form action="MainController" method="POST">
                            <td>
                                <input type="submit" value="Delete" name="btAction" onclick="return confirm('Are you sure you want to delete')"/>
                                <input type="hidden" name="foodId" value="${dto.id}"/>
                                <input type="hidden" name="foodName" value="${dto.name}"/>
                                <input type="hidden" name="txtBeginMoney" value="${requestScope.BEGIN}" />
                                <input type="hidden" name="txtEndMoney" value="${requestScope.END}" />
                                <input type="hidden" name="foodStatus" value="${dto.status}"/>
                                <input type="hidden" name="foodCategory" value="${dto.category}"/>
                                <input type="hidden" name="searchValue" value="${searchValue}" />
                            </td>
                        </form>
                        <td>
                            <c:if test="${dto.status == true}">
                                Active
                            </c:if>
                            <c:if test="${dto.status != true}">
                                Inactive
                            </c:if>
                        </td>
                    </c:if>
                    <c:if test="${empty admin}">
                        <td>
                            <form action="MainController" method="POST">
                                <input type="submit" value="Add To Cart" name="btAction" />
                                <input type="hidden" name="foodId" value="${dto.id}"/>
                                <input type="hidden" name="foodName" value="${dto.name}"/>
                                <input type="hidden" name="txtBeginMoney" value="${requestScope.BEGIN}" />
                                <input type="hidden" name="txtEndMoney" value="${requestScope.END}" />
                                <input type="hidden" name="foodStatus" value="${dto.status}"/>
                                <input type="hidden" name="foodCategory" value="${requestScope.CATEGORY}"/>
                                <input type="hidden" name="searchValue" value="${searchValue}" />
                            </form>
                        </td>
                    </c:if>
                </tr>
            </tbody>
        </c:forEach>
    </table>
    <%--For displaying Previous link except for the 1st page --%>
    <c:set var="currentPage" value="${sessionScope.CURRENTPAGEOFSEARCH}"/> 
    <c:set var="noOfPage" value="${sessionScope.NOOFPAGE}"/>
    <c:if test="${currentPage != 1}">
    <td>
        <c:url var="searchFood" value="MainController">
            <c:param name="txtName" value="${searchValue}"/>
            <c:param name="txtBegin" value="${requestScope.BEGIN}"/>
            <c:param name="txtEnd" value="${requestScope.END}"/>
            <c:param name="cbCategory" value="${requestScope.CATEGORY}"/>
            <c:param name="page" value="${currentPage - 1}"/>
            <c:param name="btAction" value="Search"/>
        </c:url>
        <a href="${searchFood}">Previous</a>
    </td>

</c:if>

<%--For displaying Page numbers. 
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${sessionScope.NOOFPAGE}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td>
                        <c:url var="searchFood" value="MainController">
                            <c:param name="txtName" value="${searchValue}"/>
                            <c:param name="txtBegin" value="${requestScope.BEGIN}"/>
                            <c:param name="txtEnd" value="${requestScope.END}"/>
                            <c:param name="cbCategory" value="${requestScope.CATEGORY}"/>
                            <c:param name="page" value="${i}"/>
                            <c:param name="btAction" value="Search"/>
                        </c:url>
                        <a href="${searchFood}">${i}</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPage}">
    <td>
        <c:url var="searchFood" value="MainController">
            <c:param name="txtName" value="${searchValue}"/>
            <c:param name="txtBegin" value="${requestScope.BEGIN}"/>
            <c:param name="txtEnd" value="${requestScope.END}"/>
            <c:param name="cbCategory" value="${requestScope.CATEGORY}"/>
            <c:param name="page" value="${currentPage + 1}"/>
            <c:param name="btAction" value="Search"/>
        </c:url>
        <a href="${searchFood}">Next</a>
    </td>
</c:if>
</c:if>
<c:set var="foodlist" value="${sessionScope.FOODLIST}"/>
<c:if test="${not empty foodlist}">
    <c:if test="${empty list}">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th></th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Type</th>
                        <c:if test="${not empty admin}">  
                        <th>Update</th>
                        <th>Delete</th>
                        <th></th>
                        </c:if>
                        <c:if test="${empty admin}">
                        <th></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${foodlist}" varStatus="counter">
                    <tr>
                        <td>${counter.count}.</td>
                        <td>
                            <img src="${dto.image}" width="100" height="100"/>    
                        </td>
                        <td>
                            ${dto.name} 
                        </td>
                        <td>
                            ${dto.description} 
                        </td>
                        <td>
                            ${dto.price}$
                        </td>
                        <td>
                            ${dto.category}
                        </td>
                        <c:if test="${not empty admin}">         
                            <td>
                                <c:url var="update" value="update.jsp">
                                    <c:param name="foodId" value="${dto.id}"/>
                                    <c:param name="foodName" value="${dto.name}"/>
                                    <c:param name="foodDescription" value="${dto.description}"/>
                                    <c:param name="foodImage" value="${dto.image}"/>
                                    <c:param name="foodPrice" value="${dto.price}"/>
                                    <c:param name="foodQuantity" value="${dto.quantity}"/>
                                    <c:param name="foodStatus" value="${dto.status}"/>
                                    <c:param name="foodCategory" value="${dto.category}"/>
                                    <c:param name="btAction" value="Update"/>
                                </c:url>
                                <a href="${update}">Update</a>    
                            </td>
                    <form action="MainController" method="POST">
                        <td>
                            <input type="submit" value="Delete" name="btAction" onclick="return confirm('Are you sure you want to delete')"/>
                            <input type="hidden" name="foodId" value="${dto.id}" />
                        </td>
                    </form>
                    <td>
                        <c:if test="${dto.status == true}">
                            Active
                        </c:if>
                        <c:if test="${dto.status != true}">
                            Inactive
                        </c:if>
                    </td>
                </c:if>
                <c:if test="${empty admin}">
                    <td>
                        <form action="MainController" method="POST">
                            <input type="submit" value="Add To Cart" name="btAction" />
                            <input type="hidden" name="foodId" value="${dto.id}"/>
                            <input type="hidden" name="foodName" value="${dto.name}"/>
                            <input type="hidden" name="txtBeginMoney" value="${requestScope.BEGIN}" />
                            <input type="hidden" name="txtEndMoney" value="${requestScope.END}" />
                            <input type="hidden" name="foodStatus" value="${dto.status}"/>
                            <input type="hidden" name="foodCategory" value="${requestScopeCATEGORY}"/>
                            <input type="hidden" name="searchValue" value="${searchValue}" />
                        </form>
                    </td>
                </c:if>
            </tr>
        </tbody>
    </c:forEach>
</table>
<%--For displaying Previous link except for the 1st page --%>
<c:set var="currentPage" value="${sessionScope.CURRENTPAGE}"/> 
<c:set var="noOfPage" value="${sessionScope.NOOFPAGE}"/>
<c:if test="${currentPage != 1}">
    <td>
        <c:url var="searchFood" value="MainController">
            <c:param name="searchValue" value="${searchValue}"/>
            <c:param name="txtBegin" value="${requestScope.BEGIN}"/>
            <c:param name="txtEnd" value="${requestScope.END}"/>
            <c:param name="cbCategory" value="${requestScope.CATEGORY}"/>
            <c:param name="page" value="${currentPage - 1}"/>
            <c:param name="btAction" value=""/>
        </c:url>
        <a href="${searchFood}">Previous</a>
    </td>

</c:if>

<%--For displaying Page numbers. 
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${sessionScope.NOOFPAGE}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td>
                        <c:url var="searchFood" value="MainController">
                            <c:param name="txtName" value="${searchValue}"/>
                            <c:param name="txtBegin" value="${requestScope.BEGIN}"/>
                            <c:param name="txtEnd" value="${requestScope.END}"/>
                            <c:param name="cbCategory" value="${requestScope.CATEGORY}"/>
                            <c:param name="page" value="${i}"/>
                            <c:param name="btAction" value=""/>
                        </c:url>
                        <a href="${searchFood}">${i}</a>
                    </td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPage}">
    <td>
        <c:url var="searchFood" value="MainController">
            <c:param name="txtName" value="${searchValue}"/>
            <c:param name="txtBegin" value="${requestScope.BEGIN}"/>
            <c:param name="txtEnd" value="${requestScope.END}"/>
            <c:param name="cbCategory" value="${requestScope.CATEGORY}"/>
            <c:param name="page" value="${currentPage + 1}"/>
            <c:param name="btAction" value=""/>
        </c:url>
        <a href="${searchFood}">Next</a>
    </td>
</c:if>
</c:if>
</c:if>
<c:if test="${empty foodlist}">
    No Result
</c:if>
<c:if test="${empty admin}">
    <form action="MainController" method="POST">
        <input type="submit" value="View Cart" name="btAction"/>
    </form>
</c:if>
<c:if test="${empty welcome}">
    <a href="login.jsp">Return to login</a>
</c:if>
</body>
</html>

