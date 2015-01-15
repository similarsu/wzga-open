<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户管理——用户修改</title>
<%@include file="../../include/system_mng_style.jsp" %>

</head>
<body>
<jsp:include page="/includes/operate.info.jsp" />
 <div id="userUpdateBox" class="box1">
    <fieldset>
      <legend>修改用户</legend>
      <form:form name="userForm" id="userForm" action="${ctx}/sys/user/update" method="post" commandName="sysUser" onsubmit="return validateForm();" failAlert="表单填写不正确，请按要求填写！">
	  <form:hidden path="id" />
	  <table id="userUpdateTable" class="tableStyle" formMode="line" style="width:100%;">
		<tr>
			<th colspan="4">表单填写</th>
		</tr>
		<tr>
			<td width="150">登录名称：</td>
			<td width="300">
			  <form:input path="loginName" cssClass="validate[required,custom[noSpecialCaracters],length[4,20]]" cssStyle="width:150px" onchange="ajaxLoginName()" />
			  <span class="star">*</span>
			</td>
			<td width="150">登录密码：</td>
			<td>
			  <form:password path="password" cssStyle="width:150px" />
			  <span class="star">密码为空表示不修改</span>
			</td>
		</tr>
		<tr>	
			<td>警&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
			<td>
			  <form:input path="policeCode" cssClass="validate[required,custom[onlyNumber]]" cssStyle="width:150px" onchange="ajaxPoliceCode()" />
			  <span class="star">*</span>
			</td>
			<td>中&nbsp;文&nbsp;名：</td>
			<td>
			  <form:input path="chineseName" cssClass="validate[required,custom[chinese]]" cssStyle="width:150px" />
			  <span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td>身份证号：</td>
			<td>
			  <form:input path="identifyCode" cssClass="validate[required,custom[noSpecialCaracters],length[1,18]]" cssStyle="width:150px" onchange="ajaxIdcard()" />
			  <span class="star">*</span>
			</td>
		    <td>所属单位：</td>
			<td>
			  <input name="deptName" id="deptName" readonly="readonly" class="validate[required]" 
			  	style="width:150px;" onclick="openHtml()" value="<qjson:departmentJSON deptCode='${sysUser.deptCode }'/>"/>
			  <span class="star">*</span> 
			  <form:hidden path="deptCode" />
			</td>
		</tr>
		<tr>
			<td>手&nbsp;机&nbsp;号：</td>
			<td><form:input path="phoneNumber" cssClass="validate[custom[mobilephone]]" cssStyle="width:150px" /></td>
		    <td>EMAIL：</td>
			<td><form:input path="email" cssClass="validate[custom[email]]" cssStyle="width:150px" /></td>
		</tr>
		<tr>
			<td>隶属角色：</td>
			<td colspan="3">
			  <select prompt="请选择" data='<qjson:roleJSON type="select"/>' selectedValue="${sysUser.roleIds }" id="select" selWidth="150" />
			  <input type="hidden" name="roleIds" id="roleIds" />
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
				<input type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/user/list'" />
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
		if (valFlag && valFlag2 && valFlag3) {
			if ($('#deptCode').val() == '') {
				top.Dialog.alert('所属单位不能为空！');
				return false;
			}
			$('#roleIds').val($("#select").attr("relValue"));
			if ($('#roleIds').val() == '') {
				top.Dialog.alert('请至少选择一个隶属角色！');
				return false;
			}
			return true;
		} else {
			if ($("#loginName").val() != '' && !valFlag) {
				top.Dialog.alert('请检查登录名称！');
			} else if ($("#policeCode").val() != '' && !valFlag2) {
				top.Dialog.alert('请检查警号！');
			} else if ($("#").val() != '' && !valFlag3) {
				top.Dialog.alert('请检查身份证号！');
			}
			return false;
		}
	}

	function openHtml() {
		var deptCode = $("#deptCode").val();
		if (deptCode == "") {
			deptCode = "-1";
		}
		var diag = new top.Dialog();
		diag.Title = "单位选择";
		diag.URL = "${ctx}/ajax/dept/" + deptCode + "/init${ext}";
		diag.Width = 450;
		diag.OKEvent = function() {
            diag.innerFrame.contentWindow.sureHandler();
		};
		diag.show();
	}
	var valFlag = true;
	function ajaxLoginName() {
		if ($("#loginName").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/user/loginName/isExists${ext}',
				type : 'POST',
				data : {
					loginName : $("#loginName").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('登录名称已存在！');
						valFlag = false;
					} else {
						valFlag = true;
					}
				}
			});
		}
	}
	var valFlag2 = true;
	function ajaxPoliceCode() {
		if ($("#policeCode").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/user/policeCode/isExists${ext}',
				type : 'POST',
				data : {
					policeCode : $("#policeCode").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('警号已存在！');
						valFlag2 = false;
					} else {
						valFlag2 = true;
					}
				}
			});
		}
	}
	var valFlag3 = true;
	function ajaxIdcard() {
		if ($("#identifyCode").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/user/identifyCode/isExists${ext}',
				type : 'POST',
				data : {
					idcard : $("#identifyCode").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('身份证号已存在！');
						valFlag3 = false;
					} else {
						valFlag3 = true;
					}
				}
			});
		}
	}
	
	//渲染页面控件
	jQuery.renderPageComponent([
	    "#userUpdateBox",
	    "#userUpdateTable",
	    "#userUpdateTable input",
	    "#userUpdateTable select"
	]);
</script>
</body>
</html>
