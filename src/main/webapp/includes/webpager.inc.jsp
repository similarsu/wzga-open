<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="height:35px;" class="bg_tb2">
  <div class="float_left padding5"></div>
  <div class="float_right padding5">
	<div class="pageNumber" total="${pager.totalCount}" page="${pager.pageNo-1}" pageSize="${pager.pageSize}" showSelect="true" showInput="true" id="pageContent"></div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">
	function postData() {
		var form = document.forms[0];
		form.submit();
	}
</script>