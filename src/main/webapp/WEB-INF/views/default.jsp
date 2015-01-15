<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="include/system_style_min.jsp" %>
    <link href="${ctx}/libs/skin_frame/style.css" rel="stylesheet" type="text/css" />
    <style>
        body{ background:#dbeefd!important;}
        .welcome{ background-position:20% 25% !important;}
    </style>
</head>

<body>
	<div class="welcome" id="openContent">
		 <div class="welcomeTitle"></div>
	</div>

    <%@ include file="include/system_script.jsp" %>
    <script type="text/javascript">
    function customHeightSet(contentHeight){
            $("#openContent").height(contentHeight);
    }
    </script>
</body>
</body>
</html>



