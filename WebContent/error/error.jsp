<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>这个.. 页面没有找到！！！</title>

<style type="text/css">
body{margin:0;padding:0;background:#efefef;font-family:Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif;}

div#mother{margin:40px auto 0 auto;width:943px;height:572px;position:relative;}
div#errorBox{background: url(../images/404_bg.png) no-repeat top left;width:943px;height:572px;margin:auto;}
div#errorText{color:#39351e;padding:146px 0 0 446px }
div#errorText p{width:303px;font-size:14px;line-height:26px;}
div.link{height:50px;width:145px;float:left;}
div#home{margin:20px 0 0 444px;}
div#contact{margin:20px 0 0 25px;}
h1{font-size:40px;margin-bottom:35px;}
</style>

</head>
<body>

<div id="mother">
	<div id="errorBox">
		<div id="errorText">
			<h1>Sorry..页面没有找到！</h1>
			<p>似乎你所寻找的网页已移动或丢失了。<br />
			或者也许你只是键入错误了一些东西。<br />
			请不要担心，这没事。如果该资源对你很重要，请与管理员联系。<br />
			火星不太安全，我可以免费送你回地球</p>
		</div>
		<a href="#" title="返回SJ5D首页">
			<div class="link" id="home"></div>
		</a>
		<a href="#" title="联系管理员">
			<div class="link" id="contact"></div>
		</a>
	</div>
</div>

</body>
</html>