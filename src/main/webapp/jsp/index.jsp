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
    <h1>Welcome! Create your ToDo List!</h1>
</div>
<c:if test="${sessionScope.user ne null}">
    <c:redirect url="/jsp/list.jsp"/>
</c:if>
<%@ include file="/jsp/footer.html" %>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>


