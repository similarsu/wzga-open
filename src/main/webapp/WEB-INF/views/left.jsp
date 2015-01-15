<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/libs/css/import_basic.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" id="skin"
        prePath="${ctx}/" autoRender="false" scrollerY="false"/>
    <link href="${ctx}/libs/skin_frame/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/libs/js/tree/ztree/ztree.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        .ztree li span.zbutton.diy01_ico_open, .ztree li span.zbutton.diy01_ico_close{width:24px!important;height:24px!important;padding-top:0;}
    </style>

</head>
<body leftFrame="true">

<div id="scrollContent" style="overflow-x:hidden;">
  <div>
	<ul id="treeDemo" class="ztree ztree_accordition"></ul>
  </div>
</div>

    <%@ include file="include/system_script.jsp" %>

    <!-- 树型抽屉导航-->
    <script type="text/javascript" src="${ctx}/libs/js/tree/ztree/ztree.js"></script>

    <script type="text/javascript">
        var zNodes =[${menu}];

        function customHeightSet(contentHeight){
            $("#scrollContent").height(contentHeight);
        }
    </script>

    <!-- entry -->
    <script type="text/javascript" src="${ctx}/libs/js/nav/treeAccordion_normal.js"></script>
</body>
</html>
