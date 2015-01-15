<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.InetAddress" %>
<div class="top"></div>
<div class="banner"><a href="https://<%= InetAddress.getLocalHost().getHostAddress() %>:8443${pageContext.request.contextPath}/pkiLogin" title="PKI证书登录链接"></a></div>