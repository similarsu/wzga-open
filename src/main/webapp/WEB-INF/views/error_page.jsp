<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
    <%@include file="include/system_style.jsp" %>
    <link href="${ctx}/libs/css/framework/icon.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="scrollContent">
<div css="ali02">
    <div style="height:100px;"></div>
	<div id="errBox" class="box1" panelWidth="450" position="center">
		<div class="msg_icon2"></div>
		<div class="padding_left50 padding_right15 padding_top20 font_10 red" >
		<c:if test="${not empty message_error}">
          <spring:message code="${message_error}" />
        </c:if>
        <c:if test="${empty message_error}"> 来自JSP页面的错误！ </c:if>
        <c:remove var="message_error" scope="session" />
		</div>
		<div class="padding_left50 padding_right15 padding_top20 minHeight_100 font_10" >
		点击&nbsp;<a href="javascript:history.go(-1);">这里</a>&nbsp;返回前一页。
		</div>
	</div>
</div>

</div>
    <%@ include file="include/system_script.jsp" %>
    <script type="text/javascript">
        function initComplete() {
            jQuery.renderPageComponent([
                "#errBox"
            ]);
        }
    </script>

</body>
</html>
