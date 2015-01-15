<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>系统管理——数据字典添加</title>
    <%@include file="../../include/system_mng_style.jsp" %>
</head>

<body>
 <div id="addBox" class="box1">
    <fieldset>
      <legend>新增数据字典</legend>
      <form:form name="sysDicForm" id="sysDicForm" action="${ctx}/sys/dic/add" method="post" commandName="sysDic" onsubmit="return validateForm();" failAlert="表单填写不正确，请按要求填写！">
	  <table id="addTable" class="tableStyle" formMode="line" style="width:100%;">
		<tr>
			<th colspan="4">表单填写</th>
		</tr>
		<tr>
			<td width="150">数据类型：</td>
			<td width="300">
			  <select data='<qjson:dicJSON type="parent" />' id="selectDicType" prompt="请选择" class="validate[required]" selWidth="250"
				 onchange="$('#dicType').val($('#selectDicType').val());$('#typeDesc').val($('#selectDicType').attr('relText'));ajaxDicKey();"></select>
			  <span class="star">*</span> 
			  <form:hidden path="dicType" />
			</td>
			<td width="150">数据键：</td>
			<td width="300">
			  <form:input path="dicKey" cssClass="validate[required,custom[onlyNumber],length[1,32]]" cssStyle="width:250px" onchange="ajaxDicKey()" />
			  <span class="star">*</span>
			</td>
		</tr>
		<tr>	
			<td>数据值：</td>
			<td>
			  <form:input path="dicValue" cssClass="validate[required,length[1,64]]" cssStyle="width:250px"/>
			  <span class="star">*</span>
			</td>
			<td>排序：</td>
			<td>
			  <form:input path="sort" cssClass="validate[required,custom[onlyNumber],length[1,3]]" cssStyle="width:250px"/>
			  <span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td>数据类型说明：</td>
			<td colspan="3">
			  <form:input path="typeDesc" cssClass="validate[required]" cssStyle="width:250px" readonly="true" />
			  <span class="star">*</span>
			</td>
		</tr>
		<tr>
			<td id="btnTD" colspan="4">
				<input type="submit" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
				<input type="button" value=" 返 回 " onclick="location.href='${ctx}/sys/dic/list'" />
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

<jsp:include page="/includes/operate.info.jsp" />

<script type="text/javascript">
	function validateForm() {
		if (valFlag) {
			return true;
		} else {
			if ($("#dicType").val() != '' && !valFlag) {
				top.Dialog.alert('请检查数据字典键！');
			}
		}

	}

	var valFlag = false;
	function ajaxDicKey() {
		if ($("#dicType").val() != '' && $("#dicKey").val() != '') {
			$.ajax({
				url : '${ctx}/ajax/dic/dicKey/isExists',
				type : 'POST',
				data : {
					dicType : $("#dicType").val(),
					dicKey : $("#dicKey").val()
				},
				error : function() {
					top.Dialog.alert('验证失败！');
				},
				success : function(res) {
					if (res == 'false') {
						top.Dialog.alert('数据字典键已存在！');
						valFlag = false;
					} else {
						valFlag = true;
					}
				}
			});
		}
	}

    // qui entry
    function initComplete(){
            jQuery.renderPageComponent([
                "#addBox",
                "#addTable",
                "#addTable td input[type='text']",
                "#addTable td select",
                "#btnTD input"
            ]);
    }
</script>
</body>
</html>
