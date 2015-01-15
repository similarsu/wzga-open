<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title"/></title>
    <link href="${ctx}/libs/skin_register/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="main">
	<div class="regist">
	        <form:form name="userForm" id="userForm" action="${ctx}/pkiregister${ext }" method="post" commandName="sysUser">
	        <div class="reg_user"><form:input  path="loginName" readonly="true"/></div>
	        <div class="reg_num"><form:input  path="policeCode"/></div>
	        <div class="reg_name"><form:input  path="chineseName" readonly="true"/></div>
	        <div class="reg_idcard"><form:input  path="identifyCode" readonly="true"/></div>
	        <div class="reg_work"><form:input  path="deptCode" readonly="true"/></div>
	        <div class="reg_phone"><form:input  path="phoneNumber"/></div>
	        <div class="reg_email"><form:input  path="email"/></div>
	        <div class="reg_button"><input type="submit" value=""/></div>
	        </form:form>
	</div>
	<div class="reg_info">
	版权所有：<spring:message code="copyright"/>
	</div>
</div>

<script type="text/javascript" src="${ctx}/libs/js/jquery.js"></script>
<script type="text/javascript">
    $("#userForm").submit(function() {
        if($('#policeCode').val()==''){
            alert('警号不能为空！');
            return false;
        }

        return true;
    });
</script>
</body>
</html>




