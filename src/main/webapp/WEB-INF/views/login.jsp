<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title" /></title>
    <link href="${ctx }/libs/skin_userlogin/style.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="mainBox" class="main">
		<div id="loginBox" class="login">
			<form id="loginForm" action="${ctx }/login${ext}" method="post">
				<div class="login_user">
					<input type="text" id="loginName" name="loginName"/>
				</div>
				<div class="login_psw">
					<input type="password" id="password" name="password"/>
				</div>
				<div class="login_button">
					<input id="loginBtn" type="button" onclick="login()" />
				</div>
				<div class="login_error">
					<c:if test="${not empty message_error }">
						<spring:message code="${message_error}" />
						<c:remove var="message_error" scope="session" />
					</c:if>
				</div>
			</form>
		</div>
		<div class="login_info">
			版权所有：
			<spring:message code="copyright" />
		</div>
	</div>

    <script type="text/javascript" src="${ctx}/libs/js/jquery.js"></script>

	<script type="text/javascript">
		$(function() {
			//居中
			$("#loginName").focus();
			$("#loginName").keydown(function(event) {
				if (event.keyCode == 13) {
					login();
				}
			})
			$("#password").keydown(function(event) {
				if (event.keyCode == 13) {
					login();
				}
			})

		})

		//登录
		function login() {
			var errorMsg = "";
			var loginName = $("#loginName");
			var password = $("#password");
			if ($.trim(loginName.val()) == "") {
				errorMsg += "&nbsp;&nbsp;用户名不能为空!";
			}
			if ($.trim(password.val()) == "") {
				errorMsg += "&nbsp;&nbsp;密码不能为空!";
			}

			if (errorMsg != "") {
				$(".login_error").html(errorMsg);
			} else {
				document.forms['loginForm'].submit();
			}
		}

	</script>
</body>

</html>



