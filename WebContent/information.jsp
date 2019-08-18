<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息展示页面</title>
<link rel="icon" href="images/logo.png">
<script charset="UTF-8" type="text/javascript" src="js/myfunction.js"></script>

</head>
<body>
	<div width=100% align="center">
	    <c:if test="${(empty requestScope.petList)&&(empty requestScope.ownerList)&&(empty requestScope.storeList)&&(empty requestScope.accountList)}">
	    <script type="text/javascript">send_redirect("initservlet")</script>
	    </c:if>
       <h1>所有的宠物信息</h1>
	<table border="3">
		<tr>
			<td>宠物编号</td>
			<td>宠物姓名</td>
			<td>宠物类型</td>
			<td>健康值</td>
			<td>亲密度</td>
			<td>出生日期</td>
			<td>所属主人</td>
			<td>所属商店</td>
		</tr>
		<c:if test="${not empty requestScope.petList}">
		<c:forEach items="${requestScope.petList}" var="pet" varStatus="row">
			<tr>
			<td>${pet.pId }</td>
			<td>${pet.pName }</td>
			<td>${pet.typeName }</td>
			<td>${pet.health }</td>
			<td>${pet.love }</td>
			<td>${pet.birthDay }</td>
			<td>${pet.owName }</td>
			<td>${pet.stName }</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	<h1>所有的宠物主人信息</h1>
	<table border="3">
		<tr>
			<td>主人编号</td>
			<td>主人姓名</td>
			<td>主人余额</td>
		</tr>
		<c:if test="${not empty requestScope.ownerList}">
		<c:forEach items="${requestScope.ownerList }" var="owner" varStatus="row">
			<tr>
			<td>${owner.owId }</td>
			<td>${owner.owName }</td>
			<td>${owner.money }</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	<h1>所有的宠物商店信息</h1>
	<table border="3">
		<tr>
			<td>商店编号</td>
			<td>商店姓名</td>
			<td>商店余额</td>
		</tr>
		<c:if test="${not empty requestScope.storeList}">
		<c:forEach items="${requestScope.storeList }" var="store" varStatus="row">
			<tr>
			<td>${store.stId }</td>
			<td>${store.stName }</td>
			<td>${store.balance }</td>
		</tr>
		</c:forEach>
		</c:if>
	</table>
	<h1>所有的交易账目信息</h1>
	<table border="3">
		<tr>
			<td>账目编号</td>
			<td>交易类型</td>
			<td>宠物名称</td>
			<td>卖家名称</td>
			<td>买家名称</td>
			<td>交易价格</td>
			<td>交易时间</td>
		</tr>
		<c:if test="${not empty requestScope.accountList}">
		<c:forEach items="${requestScope.accountList }" var="account" varStatus="row">
			<tr>
			<td>${account.acId }</td>
			<td>
				<c:if test="${account.dealType == 1 }">商店卖给主人</c:if>
				<c:if test="${account.dealType == 2 }">主人卖给商店</c:if>
			</td>
			<td>${account.pName }</td>
			<td>${account.sellerName }</td>
			<td>${account.buyerName }</td>
			<td>${account.price }</td>
			<td>${account.dealTime }</td>
		</tr>
		</c:forEach>
		</c:if>
	</table> 
	</div>
	<script type="text/javascript" color="7,197,19" pointColor="7,197,19" opacity='0.7' zIndex="-2" count="100" src="js/canvas-nest.js"></script>
</body>
</html>


