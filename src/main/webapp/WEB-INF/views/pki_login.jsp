<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp" %>
<%@ page import="cn.wzga.open.cache.SettingCache" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title"/></title>
    <link href="${ctx }/libs/skin_login/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="login_main">
		<div class="login_top">
		</div>
		<div class="login_center">
			<form id="loginForm" method="post" action="${loginURL}">
	        	<div class="login_middle">
						<!--按钮-->
				
					<a id="pkiBtn" class="login_button"></a>
					<div class="login_error">
                        <c:if test="${not empty message_warn }">
                            <spring:message code="${message_warn}" />
                            <c:remove var="message_warn" scope="session" />
                        </c:if>

                    </div>
				</div>
			</form>
		</div>
        <div class="login_footer">
			<div class="login_bottom">
				<div class="login_copyright">版权所有: <spring:message code="copyright"/>
				</div>
			</div>
         </div>
	</div>
<script type="text/javascript" src="${ctx}/libs/js/jquery.js"></script>
<script type="text/javascript">

	$("#pkiBtn").click(function() {
        var port = "<%=SettingCache.getPkiPort() %>";
        var action = "https://" +
                window.location.hostname +
                (!port ? ":8443" : (":" + port)) + "/pkilogin/action";

		$("#loginForm").attr("action", action).submit();
		return false;
	});
	
</script>
</body>
</html>
