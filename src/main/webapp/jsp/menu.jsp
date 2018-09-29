<%@ page import="by.gsu.epamlab.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="list-group">
    <%--Today --%>
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/list/TodaySection'/>">Today</a>
    <%--Tomorrow --%>
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/list/TomorrowSection' />">Tomorrow</a>
    <%--Someday --%>
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/list/SomeDaySection' />">Someday</a>
    <%--Fixed --%>
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/list/FixSection' />">Fixed</a>
    <%--Basket --%>
    <a class="list-group-item list-group-item-action"
       href="<c:url value='/list/BucketSection' />">Bin</a>
</div>



