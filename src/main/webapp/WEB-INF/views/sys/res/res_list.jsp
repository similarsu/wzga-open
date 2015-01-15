<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>资源管理——列表</title>
    <%@include file="../../include/system_mng_style.jsp" %>

</head>
<body>
<!-- 查询区 开始 -->
<div id="queryBox" class="box2">
  <div id="box_topcenter" class="box_topcenter"><div class="box_topleft">
      <div class="box_topright"><div class="title"><span>资源管理</span></div>
      <div class="boxSubTitle"></div><div class="clear"></div></div></div>
  </div>
  <div class="box_middlecenter">
    <div class="box_middleleft"><div class="box_middleright">
      <div class="boxContent" style="overflow: visible;"><div class="padding_top5">
		<form:form name="sysResourceForm" action="${ctx}/sys/res/list" method="post" commandName="sysResource" onsubmit="showProgressBar();">
		  <input type="hidden" value="1" name="pageNo" id="pageNo" />
		  <input type="hidden" value="${pager.pageSize}" name="pageSize" id="pageSize" />
		  <table id="queryTable">
			<tr>
			  <td>资源名称：</td>
			  <td><form:input path="name" id="name" style="width:260px;" /></td>
			  <td>资源地址：</td>
			  <td><form:input path="url" id="url"  style="width:260px;" /></td>
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
<div class="box_tool_min padding_top5 padding_bottom5">
  <div class="center">
	<div class="left">
	  <div class="right">
		<div class="padding_top5 padding_left10">
		  <a href="${ctx}/sys/res/add"><span class="icon_add">新增</span></a>
		  <div class="box_tool_line"></div>
		  <a href="javascript:modifyItem('${ctx}/sys/res/#id#/update');"><span class="icon_edit">修改</span></a>
		  <div class="box_tool_line"></div>
		  <a href="javascript:removeItem('${ctx}/sys/res/#id#/delete');"><span class="icon_delete">删除</span></a>
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
  <table id="listTable" class="tableStyle">
	<tr>
	  <th class="th" width="4%" colspan=2></th>
	  <th class="th">名称</th>
	  <th class="th">地址</th>
	  <th class="th">类型</th>
	  <th class="th">层次</th>
	  <th class="th">排序</th>
	  <th class="th">默认图标</th>
	  <th class="th">创建人</th>
	  <th class="th">创建时间</th>
	  <th class="th">修改人</th>
	  <th class="th">修改时间</th>
	</tr>
	<c:forEach varStatus="status" var="sysResTmp" items="${pager.list}">
	  <%-- 判断奇偶行样式 --%>
      <tr <c:if test="${status.count%2 == 0}">class="odd"</c:if> >
        <td width="2%" align="center">${status.count}</td>
		<td width="2%" align="right"><input type="radio" name="ids" value="${sysResTmp.id}" /></td>
		<td>${sysResTmp.name}</td>
	    <td>${sysResTmp.url}</td>
	    <td><qjson:dicJSON type="select" dicType="01" dicKey="${sysResTmp.type }" /></td>
	    <td>${sysResTmp.resLevel}</td>
	    <td>${sysResTmp.sort}</td>
	    <td>${sysResTmp.icon}</td>
	    <td>${sysResTmp.creator.chineseName}</td>
	    <td><fmt:formatDate value="${sysResTmp.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	    <td>${sysResTmp.modifyMan.chineseName }</td>
	    <td><fmt:formatDate value="${sysResTmp.modifyDate}" pattern='yyyy-MM-dd HH:mm:ss' /></td>
	  </tr>
  	</c:forEach>
  </table>
</div>
<!-- 列表内容 结束 -->
<!-- 分页 开始 -->
<jsp:include page="/includes/webpager.inc.jsp" />
<!-- 分页 结束 -->

<%@ include file="../../include/system_script.jsp" %>

<!--列表页功能start-->
<script type="text/javascript" src="${ctx}/libs/js/libnet/common_list.js"></script>
<!--列表页功能end-->
<!--数字分页start-->
<script type="text/javascript" src="${ctx}/libs/js/nav/pageNumber.js"></script>
<!--数字分页end-->

<jsp:include page="/includes/operate.info.jsp" />

<script type="text/javascript">
var fixObjHeight=145;
function pageReady(){

    jQuery.renderPageComponent([
        "#queryBox",
        "#queryTable",
        "#queryTable td input[type='text']",
        "#btn-1",
        "#listTable",
        "#pageContent"
    ]);
}
function customHeightSet(contentHeight){
	$("#scrollContent").height(contentHeight-fixObjHeight);
}
</script>
</body>
</html>
