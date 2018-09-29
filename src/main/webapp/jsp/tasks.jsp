<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.gsu.epamlab.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    <c:out value="${Section}"/>
</h1>
<table class="table" id="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Description</th>
        <c:if
                test="${Section eq 'SOMEDAY' || Section eq 'BUCKET' || Section eq 'FIX'}">
            <th scope="col">Date</th>
        </c:if>
        <th scope="col">File</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tasks}" var="task" varStatus="i">
        <tr id="${task.id}">
            <th scope="row"><input type="checkbox"
                                   aria-label="Checkbox for following text input" class="checkbox"
                                   value="${task.id }"> <c:out value="${i.count}"/></th>
            <td><c:out value="${task.description}"/></td>
            <c:if
                    test="${Section eq 'SOMEDAY' || Section eq 'BUCKET' || Section eq 'FIX'}">
                <td><c:out value="${task.date}"/></td>
            </c:if>
            <td><c:if test="${task.hasFile}">
                <form name="downloadForm" METHOD="GET" action="file">
                    <button
                            type="submit"
                            class="btn btn-outline-secondary">DOWNLOAD
                    </button>
                    <input type="hidden" name="id" value="<c:url value='${task.id}' />">
                </form>
                <button onclick="doDeleteFile(${task.id});"
                        class="btn btn-outline-secondary">DELETE
                </button>
            </c:if></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if
        test="${Section ne 'BUCKET' && Section ne 'FIX'}">
    <form name="Add" METHOD="POST" enctype="multipart/form-data"
          action='<c:out value="${request.getPathInfo() }"></c:out>'>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
				<span class="input-group-text" id="">Description <c:if
                        test="${Section eq 'SOMEDAY'}">
                    <c:out value="and Date"></c:out>
                </c:if>
				</span>
            </div>
            <input type="text" class="form-control"
                   name=<%=Constants.DESCRIPTION%>>
            <c:if test="${Section eq 'SOMEDAY'}">
                <input required type="date" class="form-control"
                       name=<%=Constants.DATE%>>
            </c:if>
        </div>
        <div class="input-group mb-3">
            <div class="custom-file">
                <input type="file" name="file" class="custom-file"
                       id="inputGroupFile04">
            </div>
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="submit">ADD</button>
            </div>
        </div>
    </form>
</c:if>

<button onclick="doDelete();" class="btn btn-outline-secondary">DELETE</button>
<c:if test="${Section eq 'BUCKET'}">
    <button onclick="doClear();" class="btn btn-outline-secondary">CLEAR</button>
</c:if>
<c:if test="${Section ne 'FIX'}">
    <button onclick="doPut();" class="btn btn-outline-secondary">
        <c:choose>
            <c:when test="${Section ne 'BUCKET'}">
                <c:out value="FIX"/>
            </c:when>
            <c:otherwise>
                <c:out value="RESTORE"/>
            </c:otherwise>
        </c:choose>
    </button>
</c:if>
