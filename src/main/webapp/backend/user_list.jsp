<%@ page language="java" import="java.util.*" isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>用户管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{
		font-family: "微软雅黑";
		background-color: #EDEDED;
	}
	h2{
		text-align: center;
	}
	table{ 
		margin: 0 auto; 
		/* width: 96%; */
		text-align: center;
		border-collapse:collapse;
	}
	td, th{ padding: 7px;}
	th{
		background-color: #DCDCDC;
	}
	th.ths{
		width: 100px;
	} 
	input.pnum{
		width:80px;
		height:25px;
		font-size: 18px;
		text-align:center;
	}
	.row1{
		background-color: green;
	}
	.row2{
		background-color: red;
	}
</style>

<!--引入jquery的js库-->
<script src="${app}/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<h2>用户管理</h2>
	<table border="1">
		<tr>
			<th width="200px">用户ID</th>
			<th class="ths">用户名称</th>
			<th class="ths">用户昵称</th>
			<th class="ths">邮箱地址</th>
			<th class="ths">角色</th>
		</tr>

		<!-- 模版数据 -->
	
	
<%-- 通过jstl+el获取所有商品组成的list集合, 遍历显示 --%>
<c:forEach items="${ list }" var="User" varStatus="stat">
		<tr <c:if test="${stat.index%2==0}">class="row1"</c:if>
		<c:if test="${stat.index%2==1}">class="row2"</c:if>
		>
			<td>${ user.id}</td>
			<td>${ user.username }</td>
			<td>${ user.nickname }</td>
			<td>${ user.email }</td>
			<td>${ user.role }</td>
		</tr>
</c:forEach>	


	</table>
</body>
</html>
