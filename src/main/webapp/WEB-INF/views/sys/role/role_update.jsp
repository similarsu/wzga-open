<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>角色管理——角色修改</title>
    <c:set var="nScrollY" value="false" />
    <%@include file="../../include/system_mng_style.jsp" %>

</head>
<body>
<jsp:include page="/includes/operate.info.jsp" />
<div id="roleUpdateBox" class="box1">
  <fieldset>
    <legend>修改角色</legend>
    <form:form name="roleForm" id="roleForm" action="${ctx}/sys/role/update" method="post" commandName="sysRole" onsubmit="return validateForm();" failAlert="表单填写不正确，请按要求填写！">
	  <form:hidden path="id" />
	  <table class="tableStyle" formMode="line" style="width:100%;">
	    <tr>
		  <th colspan="4">表单填写</th>
	    </tr>
	    <tr>
		  <td width="150">角色名称：</td>
		  <td width="300">
		    <form:input path="name" cssClass="validate[required,length[1,20]]" cssStyle="width:250px" onchange="ajaxName()" />
		    <span class="star">*</span>
		  </td>
	    </tr>
	    <tr>
	      <td>角色描述：</td>
		  <td>
		    <form:input path="remark" cssClass="validate[length[1,100]]" cssStyle="width:250px" />
		    <span class="star">*</span>
		  </td>
	    </tr>

	    <tr>
            <td>是否超级管理员</td>
            <td>
                <input type="checkbox" id="isAdmin" />
                <input type="hidden" id="isAdminHidden" name="isAdmin" value="0" />
                (勾选代表这个角色拥有当前用户角色的所有权限)
            </td>
        </tr>

	  </table>
      <div class="height_15"></div>
      <table class="treeTable" id="cdTable" checkMode="true">
  		<tr>
		  <th>名称</th>
	      <th>是否菜单</th>
		  <th>链接地址</th>
  	    </tr>
  		<tr id="node-0">
		  <td><span class="folder">所有权限</span></td>
		  <td></td>
		  <td></td>
  		</tr>
  		<c:forEach varStatus="status" var="sysResourceTmp" items="${sysResourceAllList}">
		<tr id="${sysResourceTmp.id}"
		  <c:if test="${not empty sysResourceTmp.parent}">class="child-of-${sysResourceTmp.parent.id}"</c:if>
		  <c:if test="${empty sysResourceTmp.parent}">class="child-of-node-0"</c:if>>
		  <td <c:if test="${sysResourceTmp.type==2}">style="color:blue"</c:if>>
		    <c:if test="${sysResourceTmp.resLevel==2}">
			  <span class="folder">${sysResourceTmp.name}</span>
		    </c:if> 
		    <c:if test="${sysResourceTmp.resLevel!=2}">
			  <span class="file">${sysResourceTmp.name}</span>
		    </c:if>
		  </td>
		  <c:if test="${sysResourceTmp.type==1}">
			<td>是</td>
		  </c:if>
		  <c:if test="${sysResourceTmp.type==2}">
			<td style="color: blue">否</td>
		  </c:if>
		  <td>${sysResourceTmp.url}</td>
	    </tr>
		</c:forEach>
		<input type="hidden" id="resIds" name="resIds" />
 	  </table>
 	  <table class="tableStyle" formMode="transparent" style="border-top: 0px;">
   		<tr>
		  <td colspan="4">
			<input type="submit" value=" 提 交 "/>
			<input type="reset" value=" 重 置 "/>
			<input type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/role/list'" />
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
<!--树形表格start-->
<script type="text/javascript" src="${ctx}/libs/js/table/treeTable.js"></script>
<!--树形表格end-->

	<script type="text/javascript">
		function validateForm() {
			if (valFlag) {
				getSelectValue();
				showProgressBar();
				return true;
			} else {
				if ($("#name").val() != '' && !valFlag) {
					top.Dialog.alert('请检查角色名称！');
				}
				return false;
			}
		}

		var valFlag = true;
		function ajaxName() {
			if ($("#name").val() != '') {
				$.ajax({
					url : '${ctx}/ajax/role/name/isExists${ext}',
					type : 'POST',
					data : {
						name : $("#name").val()
					},
					error : function() {
						top.Dialog.alert('验证失败！');
					},
					success : function(res) {
						if (res == 'false') {
							top.Dialog.alert('角色名称已存在！');
							valFlag = false;
						} else {
							valFlag = true;
						}
					}
				});
			}
		}

		function getSelectValue() {
			var msg = "";
			var i = 0;
			$("#cdTable input[type=checkbox]").each(function() {
				if ($(this).attr("checked")) {
					var cdId = $(this).parents("tr").attr("id");
					if (cdId != 'node-0') {
						if (i++ != 0)
							msg += ',';
						msg = msg + cdId;
					}
				}
			});
			$('#resIds').val(msg);
		}

		function initComplete() {
			<c:forEach varStatus="status" var="res" items="${sysRole.resourceList}">
			$("#check-${res.id}").attr("checked", true);
			</c:forEach>
		}
		
		//渲染页面控件
		jQuery.renderPageComponent([
		    "#roleUpdateBox",
		    "#roleUpdateBox table",
		    "#roleUpdateBox table input",
		]);
	</script>
</body>
</html>
