<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        body {
            background: url(/images/1.jpg);
        }
    </style>
    <meta charset="UTF-8">
    <title>Index</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" media="all">
</head>
<body>
<jsp:include page="/jsp/header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-2">
            <jsp:include page="/jsp/menu.jsp"></jsp:include>
        </div>
        <div class="col-10">
            <c:if test="${tasks ne null }">
                <jsp:include page="/jsp/tasks.jsp"></jsp:include>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="/jsp/footer.html" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>