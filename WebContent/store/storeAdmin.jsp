<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.petshop.entity.PetOwner,com.petshop.entity.PetStore,com.petshop.service.impl.PetServiceImpl,com.petshop.service.PetService,com.petshop.service.impl.PetStoreServiceImpl,com.petshop.service.PetStoreService,com.petshop.service.impl.PetOwnerServiceImpl,com.petshop.service.PetOwnerService" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>宠物商店操作页面</title>
<link rel="icon" href="../images/logo.png">
<link rel="stylesheet" href="../css/common.css"/><!-- 基本样式 -->
<link rel="stylesheet" href="../css/animate.min.css"/> <!-- 动画效果 -->
<link rel="stylesheet" href="../css/owner_store_style.css" type="text/css" media="all"/>
<script charset="UTF-8" type="text/javascript" src="../js/myfunction.js"></script>
<script charset="UTF-8" type="text/javascript" src="../js/jquery.min.js"></script>
<script charset="UTF-8" type="text/javascript" src="../js/owner_store_function.js"></script>
</head>

<body style="background:#f5f5f5;">

<%
PetService petService = new PetServiceImpl();
PetStoreService petStoreService = new PetStoreServiceImpl();
PetOwnerService petOwnerService = new PetOwnerServiceImpl();
PetStore store=(PetStore)(session.getAttribute("store"));
session.setAttribute("store",petStoreService.find(store.getStId()));
request.setAttribute("ownerlist",petOwnerService.getAllPetOwner());
request.setAttribute("selledpet",petService.getAllPetsHaveSell());
request.setAttribute("growingpet",petService.getAllStoreOnGrowPets(store.getStId()));
request.setAttribute("storepet",petService.getAllStoreOnSellPets(store.getStId()));
%>
<script charset="UTF-8" type="text/javascript" src="../js/jquery.hDialog.js"></script>
<script charset="UTF-8" type="text/javascript" src="../js/drag.js"></script>
<script>
    $(document).ready(function () {
    	$("#cardArea").cardArea();
    	
        $('.pop1-close').click(function () {
            $('.bgPop,.pop1').hide();
        });
        $('.click_pop1').click(function () {
            $('.bgPop,.pop1').show();
        });
        $('.pop2-close').click(function () {
            $('.bgPop,.pop2').hide();
        });
        $('.click_pop2').click(function () {
            $('.bgPop,.pop2').show();
        });
        $('.pop3-close').click(function () {
            $('.bgPop,.pop3').hide();
        });
        $('.click_pop3').click(function () {
            $('.bgPop,.pop3').show();
        });
      
    	$('.dialog1').hDialog({box: '#buystock'}); //自定义弹框容器ID/Class；
    	$('.dialog2').hDialog({box: '#buynew'});
    	$('.dialog3').hDialog({box: '#sellpet'});
    });
    
    $(function(){
		$('.box-3 dl').each(function(){
			$(this).dragging({move : 'both',randomPosition : false});
		});
	});
    
</script>

<!-- 主页面 -->
<ul id="cardArea" class="card-area clearfix">
	<li class="card-item">
		<div class="card">
			<div class="card-title title-even">
				<div class="content"><h1>宠物购买区</h1></div>
			</div>
			<div class="card-content content-first">
				<div style="padding:50px;">宠物宠物商店可以在这里购买所有已出售宠物</div>
			</div>
			<div class="card-content content-second">
				<div class="main-info">
                	<ul>
                    	<li>
                        	<div class="left">
                            	<p class="tit">宠物展示</p>
                                <p class="hys">所有已出售宠物都在这里</p>
                            </div>
                            <div class="right">
                           <button class="click_pop1">查看详情</button>
                            </div>
                        </li>
                    	<li>
                        	<div class="left">
                            	<p class="tit">宠物购买</p>
                                <p class="hys">所有已出售宠物都可以购买</p>
                            </div>
                            <div class="right">
                            	<button class="zoomIn dialog1">点击购买</button>
                            </div>
                        </li>
                    </ul>
				</div>
			</div>
		</div>
	</li>
	<li class="card-item active">
		<div class="card">
			<div class="card-title title-even">
				<div class="content"><h1>宠物培育区</h1></div>
			</div>
			<div class="card-content content-first">
				<div style="padding:50px;">宠物商店可以在这里完成新宠物的培育</div>
			</div>

			<div class="card-content content-second">
				<div class="main-info">
                	<ul>
                    	<li>
                        	<div class="left">
                            	<p class="tit">宠物展示</p>
                                <p class="hys">所有正在培育宠物都在这里</p>
                            </div>
                            <div class="right">
                            	<button class="click_pop2">查看详情</button>
                            </div>
                        </li>
                    	<li>
                        	<div class="left">
                            	<p class="tit">宠物培育</p>
                                <p class="hys">所有宠物在这里完成培育</p>
                            </div>
                            <div class="right">
                            	<button class="zoomIn dialog2">点击完成</button>
                            </div>
                        </li>
                    </ul>
				</div>
			</div>
		</div>
	</li>
	<li class="card-item">
		<div class="card">
			<div class="card-title title-even">
				<div class="content"><h1>宠物出售</h1></div>
			</div>
			<div class="card-content content-first">
				<div style="padding:50px;">宠物商店可以在这里出售本店所有的宠物给宠物主人，包括库存宠物和新培育宠物</div>
			</div>

			<div class="card-content content-second">
				<div class="main-info">
                	<ul>
                    	<li>
                        	<div class="left">
                            	<p class="tit">宠物展示</p>
                                <p class="hys">本店所有待出售宠物都在这里</p>
                            </div>
                            <div class="right">
                            	<button class="click_pop3">查看详情</button>
                            </div>
                        </li>
                    	<li>
                        	<div class="left">
                            	<p class="tit">宠物出售</p>
                                <p class="hys">商店所有宠物在这里都可被出售</p>
                            </div>
                            <div class="right">
                            	<button class="zoomIn dialog3">立即出售</button>
                            </div>
                        </li>
                    </ul>
				</div>
			</div>
		</div>
	</li>
</ul>

<!--遮罩层-->
<div class="bgPop"></div>

<!--弹出框展示-->
<c:forEach  begin="1"  end="3"  step= "1"  varStatus="status">
<c:choose>
	<c:when test="${status.count==1}">
	    <c:set  var="showpet"  value="${requestScope.selledpet}"  scope="request"/>
		<c:set  var="div_class"  value="pop1"  scope="page"/>
	    <c:set  var="span_class"  value="pop1-close"  scope="page"/>
	    <c:set  var="title"  value="平台所有卖出的宠物"  scope="page"/>
	</c:when>
	<c:when test="${status.count==2}">
	    <c:set  var="showpet"  value="${requestScope.growingpet}"  scope="request"/>
		<c:set  var="div_class"  value="pop2"  scope="page"/>
	    <c:set  var="span_class"  value="pop2-close"  scope="page"/>
	    <c:set  var="title"  value="本店正在培育的宠物"  scope="page"/>
	</c:when>
	<c:otherwise>
	    <c:set  var="showpet"  value="${requestScope.storepet}"  scope="request"/>
	    <c:set  var="div_class"  value="pop3"  scope="page"/>
	    <c:set  var="span_class"  value="pop3-close"  scope="page"/>
	    <c:set  var="title"  value="本店所有待售宠物"  scope="page"/>
	</c:otherwise>
</c:choose >
<div class="${pageScope.div_class}">
<div class="pop-top">
<h2>${pageScope.title}</h2>
<span class="${pageScope.span_class}">Ｘ</span>
</div>
<div class="pop-content">
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
	<td>状态</td>
</tr>
<c:forEach items="${requestScope.showpet}" var="pet" varStatus="row">
	<tr>
	<td>${pet.pId }</td>
	<td>${pet.pName }</td>
	<td>${pet.typeName }</td>
	<td>${pet.health }</td>
	<td>${pet.love }</td>
	<td>${pet.birthDay }</td>
	<td>${pet.owName }</td>
	<td>${pet.stName }</td>
	<td>
		<c:if test="${pet.canSell eq 0 }">
			培育中
		</c:if>
		<c:if test="${pet.canSell eq 1 }">
			新培育完成
		</c:if>
		<c:if test="${pet.canSell eq 2 }">
			已卖出
		</c:if>
		<c:if test="${pet.canSell eq 3 }">
			库存中
		</c:if>
	</td>
</tr>
</c:forEach>
</table>
</div>
<div class="pop-foot">
    <c:choose>
	<c:when test="${status.count==1}">
	<input type="button" value="关闭" class="pop-cancel pop1-close"/>
	</c:when>
	<c:when test="${status.count==2}">
	<input type="button" value="关闭" class="pop-cancel pop2-close"/>
	</c:when>
	<c:otherwise>
	<input type="button" value="关闭" class="pop-cancel pop3-close"/> 
	</c:otherwise>
</c:choose >
</div>
</div>
</c:forEach>

<!--弹出框表单-->
<div id="wrapper">
<div class="box">
<c:forEach  begin="1"  end="3"  step= "1"  varStatus="status">
<c:choose>
	<c:when test="${status.count==1}">
	  <c:set  var="showpet"  value="${requestScope.selledpet}"  scope="request"/>
	  <c:set  var="action"  value="buy"  scope="page"/>
	  <c:set  var="div_id"  value="buystock"  scope="page"/>
	  <c:set  var="title"  value="商店购买宠物"  scope="page"/>
	  <c:set  var="form_id"  value="form1"  scope="page"/>
	  <c:set  var="button_name"  value="确认购买"  scope="page"/>
	</c:when>
	<c:when test="${status.count==2}">
	  <c:set  var="showpet"  value="${requestScope.growingpet}"  scope="request"/>
	  <c:set  var="action"  value="grow"  scope="page"/>
	  <c:set  var="div_id"  value="buynew"  scope="page"/>
	  <c:set  var="title"  value="商店培育宠物"  scope="page"/>
	  <c:set  var="form_id"  value="form2"  scope="page"/>
	  <c:set  var="button_name"  value="完成培育"  scope="page"/>
	</c:when>
	<c:otherwise>	  
	  <c:set  var="showpet"  value="${requestScope.storepet}"  scope="request"/>
	  <c:set  var="action"  value="sell"  scope="page"/>
	  <c:set  var="div_id"  value="sellpet"  scope="page"/>
	  <c:set  var="title"  value="商店卖出宠物"  scope="page"/>
	  <c:set  var="form_id"  value="form3"  scope="page"/>
	  <c:set  var="button_name"  value="确认卖出"  scope="page"/>
	</c:otherwise>
</c:choose >
<div id="${pageScope.div_id}">
<form id="${pageScope.form_id}" action="" method="post" onsubmit="return false;">
	  <!-- 隐藏域的形式提供其他参数 -->
      <input type="hidden" name="role" value="store" />
      <input type="hidden" name="action" value="${pageScope.action}" />
		<ul class="list">
			<li>
				<div align="center">
					<div style="font-size:25px">${pageScope.title}</div>
			    </div>
			</li>
			<li>
				<strong>宠物：</strong>
				<div class="fl">
				<select name="petBuy" class="ipt">
				<c:forEach items="${requestScope.showpet}" var="pet" varStatus="row">
					<option value="${pet.pId }">${pet.pName }</option>
				</c:forEach>
			    </select>
				</div>
			</li>
			<c:if test="${status.count==3}">
			<li>
				<strong>主人：</strong>
				<div class="fl">
				<select name="target" class="ipt">
				<c:forEach items="${requestScope.ownerlist}" var="owner">
					<option value="${owner.owId}">${owner.owName }</option>
				</c:forEach>
			    </select>
				</div>
			</li>
			</c:if>
			<c:if test="${status.count!=2}">
			<li>
				<strong>价格：</strong>
				<div class="fl">
				<input type="number" name="price" value="" min="0" max="1000" step="50" class="ipt"/>
				</div>
			</li>	
			</c:if>
			    <!-- 此处用ajax方式，按钮应为button而不是submit -->
			<li>
			    <input onclick="ajax_to('${pageScope.form_id}','dealpetservlet',${sessionScope.store.balance})" type="button" value="${pageScope.button_name}" class="submitBtn" />
			</li>
		</ul>
	</form>
</div><!-- div_id end -->	
</c:forEach>
</div><!-- box end -->
</div><!-- wrapper end -->

<!--登录信息-->
<div class='box box-3' style="z-index:3;">
  <dl>
    <dd style="">用户登录信息<b id="small_button" class="up"></b></dd>
  	<ul>
   	   <li><span>角&nbsp;&nbsp;&nbsp;色：</span><i>宠物商店</i></li>
       <li><span>用户名：</span><i>${sessionScope.store.stName }</i></li>
       <li><span>余&nbsp;&nbsp;&nbsp;额：</span><i>${sessionScope.store.balance}</i></li>
       <li align="center"><a href="identityservlet?action=logout">退出登录</a></li>
   </ul>            
 </dl>
</div>

</body>
</html>
