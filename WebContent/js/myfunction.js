function send_redirect(url)
{
  window.location.href=url;
}

function set_empty(id)
{
	document.getElementById(id).value="";
}

function chklogin(theForm)
{
  
  if (theForm.username.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("用户名不能为空！");
    theForm.username.focus();   
    return (false);   
  }   
  if (theForm.userpw.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("密码不能为空！");
    theForm.userpw.focus();   
    return (false);   
  } 
  if (theForm.role.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("请选择登录角色！");
    return (false);   
  } 
  /*if (theForm.checkcode.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("请输入验证码！");
    theForm.checkcode.focus();   
    return (false);   
  } */
}

function chkregister(theForm)
{
  
  if (theForm.username.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("用户名不能为空！");
    theForm.username.focus();   
    return (false);   
  }   
  if (theForm.userpw.value.replace(/(^\s*)|(\s*$)/g, "") == ""||theForm.confuserpw.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("密码不能为空！");
    theForm.userpw.focus();   
    return (false);   
  } 
  if (theForm.userpw.value != theForm.confuserpw.value)
  {
    alert("两次输入密码不一样！");
    theForm.pass.focus();   
    return (false);   
  } 
  if (theForm.role.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("请选择注册角色！");
    return (false);   
  } 
  /*if (theForm.checkcode.value.replace(/(^\s*)|(\s*$)/g, "") == "")
  {
    alert("请输入验证码！");
    theForm.checkcode.focus();   
    return (false);   
  } */
}

String.prototype.trim = function() {
	  return  this.substring(Math.max(this.search(/\S/), 0),this.search(/\S\s*$/) + 1);
	}

function ajax_to (elem,url,money) {
	var theForm=document.getElementById(elem);
	console.log(theForm.role.value);
	if (theForm.petBuy.value.replace(/(^\s*)|(\s*$)/g, "") == "")
	  {
		console.log("pet:"+theForm.petBuy.value);
	    alert("请选择宠物！");
	    return (false);   
	  } 
	
	if(theForm.role.value=="owner")
	{
		if (theForm.price.value.replace(/(^\s*)|(\s*$)/g, "") == "")
		  {
		    console.log("price:"+theForm.price.value);
		    alert("请填写价格！");
		    return (false);   
		  }
		    console.log("money-price:"+money-theForm.price.value);
		    money=parseInt(money);
			var price=parseInt(theForm.price.value);
			console.log("money:"+money+"  "+typeof(money));
			console.log("price:"+price+"  "+typeof(price));
			console.log("money-price:"+(money-price)+"  "+typeof(money-price));
		  
		  if (elem!="form3"&& (money-price<0))
		  {
		    console.log("money-price:"+(money-price));
		    alert("余额不足！");
		    return (false);   
		  } 
		  if (elem=="form3"&&theForm.target.value.replace(/(^\s*)|(\s*$)/g, "") == "")
		  {
			console.log("store:"+theForm.target.value);
		    alert("请选择商店！");
		    return (false);   
		  } 	
	}
	else if(theForm.role.value=="store")
	{
		if (elem!="form2"&&theForm.price.value.replace(/(^\s*)|(\s*$)/g, "") == "")
		  {
		    console.log("price:"+theForm.price.value);
		    alert("请填写价格！");
		    return (false);   
		  }
		if(elem!="form2")
	    {
			money=parseInt(money);
			var price=parseInt(theForm.price.value);
			if (elem=="form1"&& (money-price<0))
			  {
			    console.log("money-price:"+(money-price));
			    alert("余额不足！");
			    return (false);   
			  } 
	    }
		
		if (elem=="form3"&&theForm.target.value.replace(/(^\s*)|(\s*$)/g, "") == "")
		  {
			console.log("store:"+theForm.target.value);
		    alert("请选择宠物主人！");
		    return (false);   
		  } 	
	}
	else return(false);
	$.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: url ,//url
        data: $('#'+elem).serialize(),
        contentType: "application/x-www-form-urlencoded",
        success: function (data) 
        {   //打印服务端返回的数据(调试用)
        	console.log(typeof(data));
        	console.log(typeof("true"));
            console.log(data=="true");
            console.log(data.trim()=="true");
            data=data.trim();
            if (data == "true")
            	{alert("操作成功");window.location.reload();}
            else if (data == "false")
            	alert("操作失败");
            else
            	alert("系统异常，请重新刷新");
        },
        error : function()
        {
            alert("系统异常，请重新刷新");
            console.log("系统异常");
        }
    });
	return true;
}

