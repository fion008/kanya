var globleURL = "http://localhost:8081/kanya/m/";
var picPath="http://localhost:8081/photo/";  
var webURL = "http://localhost:8081/kanya/";
//var globleURL = "https://yh.yunboi.com/kanya/m/";
//var picPath="https://yh.yunboi.com/photo/"; 
//var webURL = "https://yh.yunboi.com/kanya/";

/**
 * 正常的POST请求
 * @param url
 * @param params
 * @param successFunc
 * @param errorFunc
 */
function callAjax(url, params, successFunc, errorFunc){
	params = params || {};
	var $ = window.jQuery;
	if(!$){
		return;
	}
	var user=getCookie('user');
	if(!user){
		 window.top.location.href = webURL+"/login.html";
		return;
	}
	$.ajax({
		url : globleURL + url,
		dataType : "json",
    	contentType: "application/x-www-form-urlencoded",  
		type : "POST",
		data: params, xhrFields: {withCredentials: true},
		success : function(data){
			if(data.code != 200){
			    alert(data.message);
				return;
			}
			if(successFunc && (typeof successFunc == "function")){ 
				successFunc.apply(this, arguments);
			}
		},
		error : function(data){
          alert(data.message||'系统出错，请稍候再试！');
		}
	});
}


/*未登录*/
function callAjaxNoCookie(url, params, successFunc, errorFunc){
	params = params || {};
	var $ = window.jQuery;
	if(!$){
		return;
	}
	$.ajax({
		url : globleURL + url,
		dataType : "json",
    	contentType: "application/x-www-form-urlencoded",  
		type : "POST",
		data: params, xhrFields: {withCredentials: true},
		success : function(data){
			if(data.code != 200){
				 alert(data.message);
				return;
			}
			if(successFunc && (typeof successFunc == "function")){ 
				successFunc.apply(this, arguments);
			}
		},
		error : function(data){
		  alert('系统出错，请稍候再试！');
		}
	});
}

/**
 * 带头部的请求
 * @param url
 * @param params
 * @param successFunc
 * @param errorFunc
 */
function callAjaxHeader(url, params,headName,headValue,successFunc, errorFunc){
	params = params || {};
	var $ = window.jQuery;
	if(!$){
		return;
	}
	var user=getCookie('user');
	if(!user){
		 window.top.location.href = "../../login.html";
		return;
	}
	$.ajax({
		url : globleURL + url,
		dataType : "json",
    	contentType: "application/x-www-form-urlencoded",  
		type : "POST",
		data: params, xhrFields: {withCredentials: true},
        beforeSend: function(request) {  
            request.setRequestHeader(headName, headValue);  
        }, 
		success : function(data){
			if(data.code != 200){
			    alert(data.message);
				return;
			}
			if(successFunc && (typeof successFunc == "function")){ 
				successFunc.apply(this, arguments);
			}
		},
		error : function(data){
          alert(data.message||'系统出错，请稍候再试！');
		}
	});
}


/**
 * 请求参数为json格式
 * @param url
 * @param params
 * @param successFunc		
 * @param errorFunc
 */
function callAjaxJson(url, params,headName,headValue, successFunc, errorFunc){
	params = params || {};
	var $ = window.jQuery;
	if(!$){
		return;
	}
	errorFunc = errorFunc || function(){
       alert('系统出错，请稍候再试！');
	};
	$.ajax({
		url : globleURL + url,
		dataType : "json",
    	contentType: "application/json;charset=utf-8",  
		type : "POST",
		data: params, xhrFields: {withCredentials: true},
	    beforeSend: function(request) {  
	            request.setRequestHeader(headName, headValue);  
	        }, 
		success : function(data){
			if(data.code != 200){
				 alert(data.message);
				return;
			}
			if(successFunc && (typeof successFunc == "function")){
				successFunc.apply(this, arguments);
			}
		},
		error : errorFunc
	});
}


/**
 * 文件请求
 * @param eleId
 * @param url
 * @param otherParams
 * @param successFunc
 */
function callFileAjax(eleId, url, otherParams, successFunc){
	var file = document.getElementById(eleId).files[0];
	if(!file){
		console.log('no file upload...');
		return;
	}
    var formData = new FormData();
    formData.append('file', file);
    if(otherParams){
    	for(var i in otherParams){
    	    formData.append(i, otherParams[i]);
    	}
    }
	var $ = window.jQuery;
	if(!$){
		return;
	}
    $.ajax({
    	type: "POST",
		url: globleURL + url,
		async: false,
		contentType: false,    // 这个一定要写
		processData: false, // 这个也一定要写，不然会报错
		data: formData,
  		xhrFields: {withCredentials: true},
		dataType: 'json', 
		success: function(data){
			if(data.code != 200){
				 alert(data.message);
				return;
			}
			successFunc(data);
		},
		error:function(xmlHttpRequest, textStatus, errorThrown, data){
			alert('系统出错，请稍候再试！');
		}            
    });
}


//获取cookie
function getCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';'); // 把cookie分割成组
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i]; // 取得字符串
		while (c.charAt(0) == ' ') { // 判断一下字符串有没有前导空格
			c = c.substring(1, c.length); // 有的话，从第二位开始取
		}
		if (c.indexOf(nameEQ) == 0) { // 如果含有我们要的name
			return unescape(c.substring(nameEQ.length, c.length)); // 解码并截取我们要值
		}
	}
	return false;
}

// 清除cookie
function clearCookie(name) {
	setCookie(name, "", -1);
}

// 设置cookie
function setCookie(name, textue, seconds) {
	seconds = seconds || 0;
	var expires = "";
	if (seconds != 0) {
		var date = new Date();
		date.setTime(date.getTime() + (seconds * 1000));
		expires = "; expires=" + date.toGMTString();
	}
	document.cookie = name + "=" + escape(textue) + expires + ";path=/";
}


//获取参数
function getUrlParam(url){
	url = url || document.location.href;
	var questionMark = url.indexOf("?");
	var param = {};
	if(questionMark >= 0) {
		var str = url.substr(1 + questionMark);
		var strs = str.split("&");
		for(var i=0;i<strs.length;i++){
			var item = strs[i].split('=');
			var key = item[0];
			var val = unescape(item[1]);
			param[key] = val;
		}
	}
	return param;
}


/**
 * 将数值每3位加，
 * @param {Object} nStr
 */
	function addCommas(nStr) {
		nStr += '';
		x = nStr.split('.');
		x1 = x[0];
		x2 = x.length > 1 ? '.' + x[1] : '';
		var rgx = /(\d+)(\d{3})/;
		while(rgx.test(x1)) {
			x1 = x1.replace(rgx, '$1' + ',' + '$2');
		}
		return x1 + x2;
	}
	//form表单转json对象的工具方法
	//所有form表单的输入元素必须有name属性
	function formToJson(form) {
		if (form == null) {
			return null;
		}
		var result = {};
		var obj;
		for (x in form.children) {
			obj = form.children[x];
			if (obj.nodeName === 'INPUT' || obj.nodeName === 'SELECT'){
				//选择radio
				if (obj.type === 'radio') {
					if (obj.checked === true) {
						eval('result.' + obj.name + ' = obj.value');
					}
				}
				//选择checkbox 
				else if (obj.type === 'checkbox') {
				}
				//普通的input
				else if(obj.name!=''){
					eval('result.' + obj.name + ' = obj.value');
				}else{
					
				}
			} else {
				//实现递归
				result = JsonContact(result, formToJson(obj));
			}
		}
		return result;
	}
	//将两个JSON对象拼接的工具方法
	//@return 两个对象之和
	function JsonContact(ob1, ob2) {
		var len = Object.keys(ob2).length;
		for (var x = 0; x < len; x++) {
			eval('ob1.' + Object.keys(ob2)[x] + ' = ob2[Object.keys(ob2)[x]]');
		}
		return ob1;
	}

	//JSON对象填充至tbody的方法
	function Json2Table(json, table) {
		if (!table) {
			return;
		}
		for (x in table.children) {
			obj = table.children[x];
			if (typeof(obj) != 'object') {
				return;
			}
			for (key in json) {
				if (obj.getAttribute('name') == key) {
					obj.innerHTML = json[key];
				}
			}
			Json2Table(json,obj);
		}
	}
	
	//JS获取URL参数
	function getQueryString(name){  
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
	    var r = window.location.search.substr(1).match(reg);  
	    if (r!=null) return r[2]; return '';  
	}
	
	
	
//判断是否有值
	function isnull(obj) {
		if (typeof (obj) == "undefined") {
			obj = "";
		}
		return obj;
	}
	
	
	function conertPicPath(obj) {
		if (typeof (obj) == "undefined") {
			obj = "";
		}else{
			obj=picPath+obj;
		}
		return obj;
	}

	//格式化时间
	function formatTime(_time){
	    var year = _time.getFullYear();
	    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
	    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
	    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
	    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
	    return year+"-"+month+"-"+day+" "+hour+":"+minute;
	}
//渲染下拉框
function printSelect(tag,data,key,value) {
	var target = document.getElementById(tag);
	var html = ''
	for (var x = 0; x < data.length; x++) {
		var tempName="";
		if(data[x][value] != undefined){
			tempName=data[x][value];
		}
		html += '<option class="'+tag+'Option" value="' + data[x][key] + '">' + tempName + '</option>'
	}
	target.innerHTML += html
	form.render();
}