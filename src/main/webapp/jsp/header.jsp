<%@ page import="by.gsu.epamlab.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
    <nav class="navbar navbar-dark bg-dark justify-content-between">
        <%-- User name --%>
        <div class="form-group mb-2">
            User:&nbsp;
            <c:choose>
                <c:when test="${sessionScope.user.login == null}">
                    <c:out value="Guest"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${sessionScope.user.login}"/>
                </c:otherwise>
            </c:choose>
        </div>
        <%-- Errors --%>
        <div class="form-group mb-2 error">
            <c:out value="${error}"></c:out>
        </div>
        <%-- Login --%>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <div class="btn-group">
                    <!-- Drop login start -->
                    <button class="btn btn-primary mb-2" type="button"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                            style="background-color: green; border-color: green">
                        Login
                    </button>
                    <form class="dropdown-menu p-4" name="loginForm" METHOD="POST"
                          action="<c:url value='/LoginController'/>">
                        <div class="form-group">
                            <label for="exampleDropdownFormEmail2">Username</label> <input
                                type="text" class="form-control" id="exampleDropdownFormEmail2"
                                name=<%=Constants.LOGIN%>>
                        </div>
                        <div class="form-group">
                            <label for="exampleDropdownFormPassword2">Password</label> <input
                                type="password" class="form-control"
                                id="exampleDropdownFormPassword2" name=<%=Constants.PASSWORD%>>
                        </div>
                        <button type="submit" class="btn btn-primary"
                                style="color: lightslategray; border-color: lightslategray">Login
                        </button>
                    </form>
                    <!-- Drop login end -->
                    <button type="button" class="btn btn-primary mb-2 "
                            data-toggle="modal" data-target="#registrationModal"
                            style="background-color: green; border-color: green; ">
                        Sign Up
                    </button>
                </div>
            </c:when>
            <c:otherwise>
                <form name="loginForm" METHOD="POST"
                      action="<c:url value='/LoginController'/>">
                    <button type="submit" class="btn btn-primary mb-2"
                            style="background-color: green; border-color: green; ">Logout
                    </button>
                    <input type="hidden" name="method" value="DELETE">
                </form>
            </c:otherwise>
        </c:choose>
    </nav>
    <!-- Modal registration start -->
    <div style="color: black;" class="modal fade" id="registrationModal"
         tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Registration</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form name="Registration" METHOD="POST"
                      action="<c:url value='/UserController' />">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="login">Username</label> <input id="login" type="text"
                                                                       class="form-control" name=<%=Constants.LOGIN%>>
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label> <input id="password" type="password"
                                                                          class="form-control"
                                                                          name=<%=Constants.PASSWORD%>>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal"
                                style="color: lightslategray; border-color: lightslategray; background-color: white">
                            Close
                        </button>
                        <button type="submit" class="btn btn-primary"
                                style="color: lightslategray; border-color: lightslategray">Sign Up
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal registration end -->
</header>