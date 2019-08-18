<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/xgxt_login.css" />
<title>用户登录</title>
<link rel="icon" href="images/logo.png">
<script charset="UTF-8" type="text/javascript" src="js/myfunction.js"></script>
</head>
<body>
<div id="header">
	<div class="header_title">
		<span class="title_con">宠物商店</span>
	</div>
</div>

<div id="content">
	<center>
		<div class="con">
		<div class="con_title">
			<span class="con_title_sp">欢迎登录WIT宠物商店</span>
		</div>
		<div class="con_panel">
		<form id="login_form" name="login_form" onSubmit="return chklogin(this)" action = "identityservlet" method="post">
		    <c:if test="${not empty requestScope.errMsg}">
		    <span style="color:red;">${requestScope.errMsg}</span>
            <c:remove var="errMsg" scope="request"/>
            </c:if>
			<input type="hidden" name="action" value="login"/>
			<div class="con_input" style="margin: 3.0em 0 0.5em 0">
				<span>用户名：</span><input type="text" id="username" name="username" placeholder="请输入用户名"/>
			</div>
			<div class="con_input">
				<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" id="userpw" name="userpw" placeholder="请输入密码"/>
			</div>
			<div class="con_select">
				<input type="radio" id="role1" name="role" value="owner" />宠物主人
				<input type="radio" id="role2" name="role" value="store" />宠物商店
			</div>
			<input type="submit" value="登    录" class="submit-btn"/>
			<div class="con_input" style="margin: 0em 0 2.5em 0">
		       <a href="information.jsp">详细数据</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="register.jsp">用户注册</a>
		    </div>
		    </form>
		</div>
	</div>
	</center>
</div>

<div id="footer">
	<center>
		<p>WIT PetShop @ All Rights Reserved 1767331708@qq.com</p>
	</center>
</div>
<script type="text/javascript" color="7,197,19" pointColor="7,197,19" opacity='0.7' zIndex="-2" count="100" src="js/canvas-nest.js"></script>
</body>
<c:if test="${not empty requestScope.errMsg}">
<script>set_empty("userpw");</script>
</c:if>
</html>
