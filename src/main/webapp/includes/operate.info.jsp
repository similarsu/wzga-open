<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${not empty message_warn}">
<script type="text/javascript">
$(function(){
	<c:if test="${empty message_param}">
	top.Dialog.alert('<div class="blue"><spring:message code="${message_warn}" /></div>|警告');
    </c:if>
	<c:if test="${not empty message_param}">
	top.Dialog.alert('<div class="blue"><spring:message code="${message_warn}" arguments="${message_param}"/></div>|警告');
	</c:if>
})
</script>
<c:remove var="message_warn" scope="session" />
</c:if>

<c:if test="${not empty message_success}">
<script type="text/javascript">
$(function(){
	<c:if test="${empty message_param}">
	top.Dialog.alert('<spring:message code="${message_success}" />');
    </c:if>
	<c:if test="${not empty message_param}">
	top.Dialog.alert('<spring:message code="${message_success}" arguments="${message_param}"/>');
	</c:if> 
})
</script>
<c:remove var="message_success" scope="session" />
</c:if>

<c:if test="${not empty message_error }">
<script type="text/javascript">
$(function(){
	<c:if test="${empty message_param}">
	top.Dialog.alert('<div class="red"><spring:message code="${message_error}" /></div>|错误');
    </c:if>
	<c:if test="${not empty message_param}">
	top.Dialog.alert('<div class="red"><spring:message code="${message_error}" arguments="${message_param}"/></div>|错误');
	</c:if>
}
</script>
<c:remove var="message_error" scope="session" />  
</c:if>
