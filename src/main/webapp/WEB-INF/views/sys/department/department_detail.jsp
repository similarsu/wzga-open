<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统管理——组织机构详情</title>
<%@include file="../../include/system_mng_style.jsp" %>
</head>
<body>
<div id="dmDetailBox" class="box1">
  <fieldset>
    <legend>查看组织机构</legend>
	  <table id="dmDetailTable" class="tableStyle" formMode="view">
		<tr>
			<th colspan="4">详细信息</th>
		</tr>
		<tr>
		  <td width="150">单位代码：</td>
		  <td width="300">${sysDepartment.deptCode}</td>
		  <td width="150">单位名称：</td>
		  <td width="300">${sysDepartment.deptName}</td>
		</tr>
        <tr>
          <td>其他名称：</td>
          <td>${sysDepartment.otherName }</td>
          <td>单位简称：</td>
          <td>${sysDepartment.simpleName }</td>
        </tr>
        <tr>
          <td>正式机构：</td>
          <td>${sysDepartment.isFormalStr }</td>
          <td>启用日期：</td>
          <td>${sysDepartment.enableDate }</td>
        </tr>
        <tr>
          <td>是否停用：</td>
          <td>${sysDepartment.isStoppedStr }</td>
          <td>停用日期：</td>
          <td>${sysDepartment.disableDate }</td>
        </tr>
        <tr>
          <td>原机构代码：</td>
          <td>${sysDepartment.originalCode }</td>
          <td>原机构停用日期：</td>
          <td>${sysDepartment.originalStoppedDate }</td>
        </tr>
		<tr>	
    	  <td>单位拼音：</td>
    	  <td>${sysDepartment.deptSpell }</td>
    	  <td>单位层次：</td>
    	  <td>${sysDepartment.deptLevel }</td>
		</tr>
		<tr>
		  <td colspan="4">
			<input id="backBtn" type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/department/list'" />
		  </td>
		</tr>
	 </table>
 </fieldset>
</div>
<%@ include file="../../include/system_script.jsp" %>

<script type="text/javascript">
//渲染页面控件
jQuery.renderPageComponent([
    "#dmDetailBox",
    "#dmDetailTable",
    "#backBtn"
]);
</script>
</body>
</html>
