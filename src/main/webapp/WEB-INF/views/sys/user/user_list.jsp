<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9" ></meta>
<title>用户管理——用户列表</title>
<%@include file="../../include/system_mng_style.jsp" %>

</head>
<body>
<!-- 查询区 开始 -->
<div id="userListQueryBox" class="box2">
  <div id="box_topcenter" class="box_topcenter"><div class="box_topleft">
      <div class="box_topright"><div class="title"><span>用户管理</span></div>
      <div class="boxSubTitle"></div><div class="clear"></div></div></div>
  </div>
  <div class="box_middlecenter">
    <div class="box_middleleft"><div class="box_middleright">
      <div class="boxContent" style="overflow: visible;"><div class="padding_top5">
		<form:form name="sysUserForm" action="${ctx}/sys/user/list" method="post" commandName="sysUser" onsubmit="showProgressBar();">
		  <input type="hidden" value="1" name="pageNo" id="pageNo" />
		  <input type="hidden" value="${pager.pageSize}" name="pageSize" id="pageSize" />
		  <table>
			<tr>
			  <td>中文名：</td>
			  <td><form:input path="chineseName" /></td>
			  <td>身份证号：</td>
			  <td><form:input path="identifyCode"/></td>
			  <td>警号：</td>
			  <td><form:input path="policeCode" /></td>
			  <td><button id="btn-1" type="submit"><span class="icon_find">查询</span></button></td>
			</tr>
		  </table>
		</form:form></div>
      </div></div>
    </div>
  </div>
  <div id="box_bottomcenter" class="box_bottomcenter">
    <div class="box_bottomleft"><div class="box_bottomright"></div></div>
  </div>
</div>
<!-- 查询区 结束 -->
<!-- 工具栏 开始 -->
<div id="userListToolBox" class="box_tool_min padding_top5 padding_bottom5">
  <div class="center">
	<div class="left">
	  <div class="right">
		<div id="toolBar" class="padding_top5 padding_left10">
		  <a href="${ctx}/sys/user/add" id="addA"><span id="addBtn" class="icon_add">新增</span></a>
		  <div class="box_tool_line"></div>
		  <a href="javascript:modifyItem('${ctx}/sys/user/#id#/update');"><span class="icon_edit">修改</span></a>
		  <div class="box_tool_line"></div>
		  <a href="javascript:removeItem('${ctx}/sys/user/#id#/delete');"><span class="icon_delete">删除</span></a>
		  <div class="clear"></div>
		</div>
	  </div>		
    </div>	
  </div>
  <div class="clear"></div>
</div>     	
<!-- 工具栏 结束 -->
<!-- 列表内容 开始 -->
<div id="scrollContent"   style="overflow-x:hidden;">
  <table class="tableStyle">
	<tr>
	  <th class="th" width="4%" colspan=2></th>
	  <th class="th">登录名</th>
	  <th class="th">姓名</th>
	  <th class="th">身份证</th>
	  <th class="th">警号</th>
	  <th class="th">单位名称</th>
	  <th class="th">创建人</th>
	  <th class="th">创建时间</th>
	  <th class="th">修改人</th>
	  <th class="th">修改时间</th>
	</tr>
	<c:forEach varStatus="status" var="sysUserTmp" items="${pager.list}">
	  <%-- 判断奇偶行样式 --%>
      <tr <c:if test="${status.count%2 == 0}">class="odd"</c:if> >
        <td width="2%" align="center">${status.count}</td>
		<td width="2%" align="right"><input type="radio" name="ids" value="${sysUserTmp.id}" /></td>
		<td>${sysUserTmp.loginName}</td>
	    <td>${sysUserTmp.chineseName}</td>
	    <td>${sysUserTmp.identifyCode}</td>
	    <td>${sysUserTmp.policeCode }</td>
	    <td>${sysUserTmp.sysDepartment.deptName }</td>
	    <td>${sysUserTmp.creator.chineseName}</td>
	    <td><fmt:formatDate value="${sysUserTmp.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	    <td>${sysUserTmp.modifyMan.chineseName }</td>
	    <td><fmt:formatDate value="${sysUserTmp.modifyDate}" pattern='yyyy-MM-dd HH:mm:ss' /></td>
	  </tr>
  	</c:forEach>
  </table>
</div>
<!-- 列表内容 结束 -->
<!-- 分页 开始 -->
<jsp:include page="/includes/webpager.inc.jsp" />
<!-- 分页 结束 -->

<%@ include file="../../include/system_script.jsp" %>
<jsp:include page="/includes/operate.info.jsp" />
<!--数字分页start-->
<script type="text/javascript" src="${ctx}/libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->
<!--列表页功能start-->
<script type="text/javascript" src="${ctx}/libs/js/libnet/common_list.js"></script>
<!--列表页功能end-->
<script>
var fixObjHeight=145;
function pageReady(){
    $("#select-1").render();
    $("#date-1").render();
    $("#btn-1").render();
    $("#pageContent").render();
}
function customHeightSet(contentHeight){
  $("#scrollContent").height(contentHeight-fixObjHeight);
}

//渲染页面控件
jQuery.renderPageComponent([
    "#userListQueryBox",
	"#userListToolBox",
	"#toolBar",
	"#toolBar span",
	"#addBtn"
]);
</script>

</body>
</html>
