<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>系统管理——组织机构修改</title>
<%@include file="../../include/system_mng_style.jsp" %>

</head>
<body>
<div id="dmUpdateBox" class="box1">
  <fieldset>
    <legend>修改组织机构</legend>
    <form:form id="sysDepartmentForm" name="sysDepartmentForm" action="${ctx}/sys/department/update" method="post" commandName="sysDepartment" onsubmit="return validateForm();" failAlert="表单填写不正确，请按要求填写！">
	  <form:hidden path="id" />
	  <table id="dmUpdateTable" class="tableStyle" formMode="line" style="width:100%;">
	    <tr><th colspan="4">表单填写</th></tr>
		<tr>
		  <td width="150">单位代码：</td>
		  <td width="300">
			<form:input path="deptCode" cssClass="validate[required,custom[noSpecialCaracters],length[1,12]]" cssStyle="width:150px" onchange="ajaxDeptCode()" />
			<span class="star">*</span>
		  </td>
		  <td width="150">单位名称：</td>
		  <td width="300">
			<form:input path="deptName" cssClass="validate[required]" cssStyle="width:150px" />
			<span class="star">*</span>
		  </td>
		</tr>
        <tr>
          <td width="150">其他名称：</td>
          <td width="300">
            <form:input path="otherName" cssStyle="width:150px" />
          </td>
          <td width="150">单位简称：</td>
          <td width="300">
            <form:input path="simpleName" cssClass="validate[required]" cssStyle="width:150px" />
            <span class="star">*</span>
          </td>
        </tr>
        <tr>
          <td>正式机构：</td>
          <td colspan="3">
            <select id="selectIsFormal" prompt="请选择" data='<qjson:dicJSON type="select" dicType="01"/>'
              selectedValue="${sysDepartment.isFormal }" selWidth="150" onchange="$('#isFormal').val($('#selectIsFormal').val())"/>
            <form:hidden path="isFormal" />
          </td>
        </tr>
        <tr>
          <td>原机构代码：</td>
          <td><form:input path="originalCode" cssStyle="width:150px" /></td>
          <td>原机构停用日期：</td>
          <td><form:input path="originalStoppedDate" cssStyle="width:150px" /></td>
        </tr>
		<tr>
    	  <td>单位拼音：</td>
    	  <td>
    		<form:input path="deptSpell" cssStyle="width:150px"/>
    	  </td>
    	  <td>单位层次：</td>
    	  <td>
    		<form:input path="deptLevel" cssStyle="width:150px" />、
    	  </td>
		</tr>
		<tr>
		  <td colspan="4">
			<input type="submit" value=" 提 交 "/>
			<input type="reset" value=" 重 置 "/>
			<input type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/department/list'" />
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

<script type="text/javascript">
	function validateForm() {
		if(valFlag){
			if ($('#deptCode').val() == '') {
				top.Dialog.alert('单位代码不能为空！');
				return false;
			}
			return true;
		}else{
			if ($('#deptCode').val() != ''&&!valFlag) {
				top.Dialog.alert('请检查单位代码！');
			}
			return false;
		}
	}

	var valFlag = true;
	function ajaxDeptCode() {
		if ($("#deptCode").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/dept/isExists',
				type : 'POST',
				data : {
					deptCode : $("#deptCode").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('您输入的单位代码已存在！');
						valFlag = false;
					} else {
						valFlag = true;
					}
				}
			});
		}
	}
	
	//渲染页面控件
	jQuery.renderPageComponent([
	    "#dmUpdateBox",
	    "#dmUpdateTable",
	    "#dmUpdateTable input",
	    "#dmUpdateTable select"
	]);
</script>
</body>
</html>
