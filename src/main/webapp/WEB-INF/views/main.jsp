<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><spring:message code="title"/></title>
    <!--框架必需start-->
    <link href="${ctx}/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/libs/css/framework/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/libs/skins/blue/style.css" rel="stylesheet"
        type="text/css" id="theme" themeColor="blue" positionTarget="positionContent"/>
    <link href="${ctx}/libs/skin_frame/style.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        a {behavior:url(${ctx}/libs/js/method/focus.htc)}
    </style>
</head>

<body>

<div id="mainFrame">
<!--头部与导航start-->
<div id="hbox">
  <div id="bs_bannercenter"><div id="bs_bannerright"><div id="bs_bannerleft"></div></div></div>
	<div id="bs_navcenter">
	<div id="bs_navleft">
	  <div id="bs_navright">
		<div class="bs_nav">
			<div class="bs_navleft">
			  <div class="popupMenu">
               【<span class="popupMenu_link">欢迎您，${fn:substring(sessionScope.sysUser.chineseName, 0, 5)}</span>】
                <span class="popupMenu_con white_con">&diams;&nbsp;${sessionScope.sysUser.chineseName}(${sessionScope.sysUser.sysDepartment.deptName})</span>
              </div>
	      	</div>
			<div class="float_left" id="positionContent"></div>	
			<div class="float_right padding_top5 padding_right5">
				<a href="${ctx}/default" target="frmright" onclick='backHome()'><span class="icon_home hand">返回导航页</span></a>
				<span class="icon_fullscreen hand" id="fullSrceen" hideNav="true">开启全屏</span>
				<span class="icon_delete hand" onclick='top.Dialog.confirm("确定要关闭本页吗？",function(){window.location="${ctx}/logout"});'>退出系统</span>
				<div class="clear"></div>
			</div>
			<div class="float_right padding_top5 padding_right5">
				【今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>】
			</div>	
			<div class="clear"></div>
		</div>
	</div>
	</div>
	</div>
</div>
<!--头部与导航end-->
<table width="100%" cellpadding="0" cellspacing="0" class="table_border0">
  <tr>
	<!--左侧区域start-->
	<td id="hideCon" class="ver01 ali01">
	  <div id="lbox"><div id="lbox_topcenter"><div id="lbox_topleft"><div id="lbox_topright"></div></div></div>
		<div id="lbox_middlecenter">
		  <div id="lbox_middleleft">
			<div id="lbox_middleright">
			  <div id="bs_left">
				<IFRAME height="100%" width="100%"  frameBorder=0 id=frmleft name=frmleft src="${ctx}/left"  allowTransparency="true"></IFRAME>
			  </div>
			  <!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
			</div>
		  </div>
		</div>
		<div id="lbox_bottomcenter"><div id="lbox_bottomleft"><div id="lbox_bottomright"><div class="lbox_foot"></div></div></div></div>
	  </div>
	</td>
	<!--左侧区域end-->
	<!--分隔栏区域start-->
	<td class="spliter main_shutiao"
        targetId="hideCon" beforeClickTip="收缩面板"
        afterClickTip="展开面板" beforeClickClass="bs_leftArr"
        afterClickClass="bs_rightArr">
	</td>
	<!--分隔栏区域end-->
	<!--右侧区域start-->
	<td class="ali01 ver01"  width="100%">
	  <div id="rbox">
	    <div id="rbox_topcenter"><div id="rbox_topleft"><div id="rbox_topright"></div></div></div>
		<div id="rbox_middlecenter">
		  <div id="rbox_middleleft">
			<div id="rbox_middleright">
			  <div id="bs_right">
				<IFRAME height="100%" width="100%" frameBorder=0 id=frmright name=frmright src="${ctx}/default"  allowTransparency="true"></IFRAME>
			  </div>
			</div>
		  </div>
		</div>
		<div id="rbox_bottomcenter" ><div id="rbox_bottomleft"><div id="rbox_bottomright"></div></div></div>
	  </div>
	</td>
	<!--右侧区域end-->
  </tr>
</table>
<!--尾部区域start-->
<div id="fbox">
  <div id="bs_footcenter"><div id="bs_footleft"><div id="bs_footright">版权所有：<spring:message code="copyright"/></div></div></div>
</div>
</div>
<!--尾部区域end-->

<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->


    <script type="text/javascript" src="${ctx}/libs/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/libs/js/language/cn.js"></script>

    <!--弹窗组件start-->
    <script type="text/javascript" src="${ctx}/libs/js/popup/drag.js"></script>
    <script type="text/javascript" src="${ctx}/libs/js/popup/dialog.js"></script>
    <!--弹窗组件end-->
    <!--分隔条start-->
    <script type="text/javascript" src="${ctx}/libs/js/nav/spliter.js"></script>
    <!--分隔条end-->
    <!--弹出式提示框start-->
    <script type="text/javascript" src="${ctx}/libs/js/popup/messager.js"></script>
    <!--弹出式提示框end-->

    <script type="text/javascript" src="${ctx}/libs/js/main.js"></script>

    <script type="text/javascript">
        function backHome(){
            document.getElementById("frmleft").contentWindow.homeHandler();
        }
    </script>
</body>
</html>






