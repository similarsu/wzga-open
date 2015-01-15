<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>资源管理——修改</title>
    <%@include file="../../include/system_mng_style.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/libs/js/tree/ztree/ztree.css" />

</head>
<body>
<div id="updateBox" class="box1">
  <fieldset>
    <legend>修改资源</legend>
      <form:form name="sysResourceForm" id="sysResourceForm" action="${ctx}/sys/res/update" method="post" commandName="sysResource" onsubmit="return validateForm();" failAlert="表单填写不正确，请按要求填写！">
	  <form:hidden path="id" />
	    <table id="updateTable" class="tableStyle" formMode="line" style="width:100%;">
		  <tr>
			<th colspan="4">表单填写</th>
		  </tr>
		  <tr>
			<td width="150">资源名称：</td>
			<td width="300">
			  <form:input path="name" cssClass="validate[required,length[1,20]]" cssStyle="width:250px" onchange="ajaxName()" />
			  <span class="star">*</span>
			</td>
			<td width="150">资源地址：</td>
			<td width="300">
			  <form:input path="url" cssClass="validate[required,length[1,100]]" cssStyle="width:250px" onchange="ajaxUrl()" />
			  <span class="star">*</span>
			</td>
		  </tr>
		  <tr>	
			<td>上级菜单：</td>
			<td>
			  <div class="selectTree" prompt="请选择" data='<qjson:resJSON type="selectTree"/>' id="selectParent" selectedValue="${sysResource.parentId }" selWidth="250"></div>
			  <input type="hidden" name="parentId" id="parentId" />
			</td>
			<td>资源类别：</td>
			<td>
			  <select id="selectType" data='<qjson:dicJSON type="select" dicType="01"/>' selectedValue="${sysResource.type }" selWidth="250"></select>
			  <input type="hidden" name="type" id="type" />
			</td>
		  </tr>
		  <tr>
			<td>资源排序：</td>
			<td>
			  <form:input path="sort" cssClass="validate[required,custom[onlyNumber],length[1,3]]" cssStyle="width:250px" />
			  <span class="star">*</span>
			</td>
		    <td>资源级别：</td>
			<td>
			  <form:input path="resLevel" cssClass="validate[required,custom[onlyNumber],length[1,3]]" cssStyle="width:250px" />
			  <span class="star">*</span>
			</td>
		  </tr>
		  <tr>
			<td>展开图标：</td>
			<td>
			  <form:input path="iconOpen" cssStyle="width:250px" />
			</td>
		    <td>关闭图标：</td>
			<td>
			  <form:input path="iconClose" cssStyle="width:250px" />
			</td>
		  </tr>
		  <tr>
			<td>默认图标：</td>
			<td colspan="3">
			  <form:input path="icon" cssStyle="width:250px" />
			</td>
		  </tr>
		  <tr>
			<td colspan="4" id="btnTD">
			  <input type="submit" value=" 提 交 "/>
			  <input type="reset" value=" 重 置 "/>
			  <input type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/res/list'" />
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
<!-- 树组件start -->
<script type="text/javascript" src="${ctx}/libs/js/tree/ztree/ztree.js"></script>
<!-- 树组件end -->
<!-- 树形下拉框start -->
<script type="text/javascript" src="${ctx}/libs/js/form/selectTree.js"></script>
<!-- 树形下拉框end -->

<jsp:include page="/includes/operate.info.jsp" />
<script type="text/javascript">
	function validateForm() {
		if (valFlag && valFlag1) {
			$('#parentId').val($("#selectParent").attr("relValue"));
			$('#type').val($("#selectType").attr("relValue"));
			return true;
		} else {
			if ($("#name").val() != '' && !valFlag) {
				top.Dialog.alert('请检查资源名称！');
			}
			if ($("#url").val() != '' && !valFlag1) {
				top.Dialog.alert('请检查资源地址！');
			}
			return false;
		}

	}

	var valFlag = true;
	function ajaxName() {
		if ($("#name").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/res/name/isExists',
				type : 'POST',
				data : {
					name : $("#name").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('资源名称已存在！');
						valFlag = false;
					} else {
						valFlag = true;
					}
				}
			});
		}
	}

	var valFlag1 = true;
	function ajaxUrl() {
		if ($("#selectParent").attr("relValue") == '') {
			valFlag1 = true;
			return;
		}
		if ($("#url").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/res/url/isExists',
				type : 'POST',
				data : {
					url : $("#url").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('资源地址已存在！');
						valFlag1 = false;
					} else {
						valFlag1 = true;
					}
				}
			});
		}
	}

    // qui entry
    function initComplete(){
            jQuery.renderPageComponent([
                "#updateBox",
                "#updateTable",
                "#updateTable td input[type='text']",
                "#updateTable td select",
                "#selectParent",
                "#btnTD input"
            ]);
    }
</script>
</body>
</html>
