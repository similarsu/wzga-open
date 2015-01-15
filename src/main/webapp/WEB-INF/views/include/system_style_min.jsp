<%@ page language="java" pageEncoding="UTF-8"%>
<link href="${ctx}/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin"
    prePath="${ctx}/" autoRender="false"
    <c:choose>
        <c:when test="${nScrollY}">
            scrollerY="false"
        </c:when>
        <c:otherwise>
            scrollerY="true"
        </c:otherwise>
    </c:choose>

    />
