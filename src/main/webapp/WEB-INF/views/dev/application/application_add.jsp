<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>应用管理——应用添加</title>
<%@include file="../../include/system_mng_style.jsp" %>

</head>
<body>
<jsp:include page="/includes/operate.info.jsp" />
 <div id="applicationAddBox" class="box1">
    <fieldset>
      <legend>新增应用</legend>
      <form:form name="applicationForm" id="applicationForm" action="${ctx}/dev/application/add" method="post" commandName="application" failAlert="表单填写不正确，请按要求填写！">
	  <table id="applicationAddTable" class="tableStyle" formMode="line" style="width:100%;">
		<tr>
			<th colspan="2">表单填写</th>
		</tr>
		<tr>
			<td>应用名：</td>
			<td><form:input path="appName" cssClass="validate[required]" cssStyle="width:150px" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
				<input type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/application/list'" />
			</td>
		</tr>
	 </table>
  </form:form>
 </fieldset>
</div>

<%@ include file="../../include/system_script.jsp" %>
<script type="text/javascript" src="${ctx}/libs/js/form/filter.js"></script>
<script src="${ctx}/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="${ctx}/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 树形下拉框start -->
<script type="text/javascript" src="${ctx}/libs/js/form/selectTree.js"></script>
<!-- 树形下拉框end -->

<script type="text/javascript">
	function validateForm() {
		
	}

	//渲染页面控件
	jQuery.renderPageComponent([
	    "#applicationAddBox",
	    "#applicationAddTable",
	    "#applicationAddTable input",
	    "#applicationAddTable select"
	]);
</script>
</body>
</html>
