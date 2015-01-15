<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.inc.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>单位选择页面</title>
    <%@include file="../../include/system_mng_style.jsp" %>
</head>

<body>
<div class="height_15"></div>
  <table class="formTable" align="center">
	<tr><td class="ali03">搜索：</td>
	  <td>
	    <div id="searchInput" class="suggestion" suggestMode="remote"
            inputWidth="300" url="${ctx}/ajax/dept/query"
            minChars="6" delay="1000"
            ></div>
      </td>
	</tr>

	<tr><td class="ali03">省：</td>
	  <td>
		<select id="provice" selectedValue="${provinceCode}" selWidth="300"></select>
      </td>
	</tr>
    <tr><td class="ali03">市：</td>
      <td>
        <select id="city" selectedValue="${cityCode}" selWidth="300">
		</select>
      </td>
    </tr>	
	<tr><td class="ali03">区：</td>
	  <td>
	    <select id="country" selectedValue="${countyCode}" selWidth="300">
		</select>
	  </td>
	</tr>
	<tr><td class="ali03">单位：</td>
	  <td>
	    <select id="deptCode" selectedValue="${deptCode}" selWidth="300">
		</select>
	  </td>
	</tr>
  </table>

    <%@ include file="../../include/system_script.jsp" %>
    <!-- suggestion js -->
    <script type="text/javascript" src="${ctx}/libs/js/form/suggestion.js"></script>
    <!-- main js -->
    <script type="text/javascript" src="${ctx}/libs/js/libnet/deptChoose.js"></script>
    <script type="text/javascript">
    	var prefix = "${ctx}";
    	var provinceData = {"list":[
            {"value":"-1","key":"请选择"}
            <c:forEach items="${provinceList}" var="province" varStatus="s">
                ,{"value":"${province.deptCode}","key":"${province.deptName}"}
		    </c:forEach>
        ]};

    	var cityData = {"list":[
            {"value":"-1","key":"请选择"}
            <c:forEach items="${cityList}" var="city" varStatus="s">
                , {"value":"${city.deptCode}","key":"${city.deptName}"}
		    </c:forEach>
        ]};

    	var countryData = {"list":[
            {"value":"-1","key":"请选择"}
            <c:forEach items="${countyList}" var="country" varStatus="s">
                , {"value":"${country.deptCode}","key":"${country.deptName}"}
		    </c:forEach>
        ]};

    	var deptData = {"list":[
            {"value":"-1","key":"请选择"}
            <c:forEach items="${deptList}" var="country" varStatus="s">
                , {"value":"${country.deptCode}","key":"${country.deptName}"}
		    </c:forEach>
        ]};
    </script>
</body>
</html>
