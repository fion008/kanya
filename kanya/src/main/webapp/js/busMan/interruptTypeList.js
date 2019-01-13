var param = {};
var laypage;
var form;
var layer;
// 启用layer模块
layui.use(['layer','laypage', 'form'], function(){
	laypage = layui.laypage;
	form = layui.form;
	layer = layui.layer;
	initPage();
	initEvent();
	
});

function initPage() {
	// 页面初始化时即重置查询条件，所有条件为空
	initTable();
	// 加载下拉选
	initSelectList();

}
function initEvent() {
	$('#searchBtn').click(function () {
		initTable();
	});
	//新增
	$('#addBtn').click(function () {
		 modify('');
	});	
	
	// 监听表单提交
	form.on('submit(formDemo)', function(data){
		var da=data.field;
		var params=da;
		var url = 'interruptType/update';
		callAjax(url, params, function(data) {
			layer.msg("保存成功");
				layer.close(index); 			
				initTable();
			});
		    return false;
	  });	


}


// 加载角色下拉选择查询条件
function initSelectList() {

}


function initTable() {
	// 根据用户设置的查询条件获取数据，生成分页并跳转至第一页
	var url = 'interruptType/pagelist';
	var paramForm = document.getElementById('paramFrom');
	param = formToJson(paramForm);
	param.pageNum=1;
	param.numPerPage=10;
	callAjax(url, param,function(data){
		if(!(data.code == 200 && data.dataList)) {
			return;
		}
		initTableBack(data);
	});
}

// 获取信息后初始化分页并装载数据
function initTableBack(data) {
	// 重新获取laypage实例
	laypage.render({
		elem : 'my-table',
		limit : 10,
		count : data.totalCount,
	
		// 首次加载不执行jumpTo，避免重复ajax浪费性能
		jump : function (obj, first) {
			if(!first){
				jumpTo(obj.curr);
			}
		},
		layout : ['prev', 'page', 'next', 'count']
	});
	printTable(data.dataList);
}
// 跳页不涉及查询对象属性的改变
function jumpTo(page) {
	$("#data-table").empty();
	param.pageNum=page;
	var url = 'interruptType/pagelist';
	callAjax(url, param, function (data) {printTable(data.dataList);});
}

function printTable(data) {
	var target = document.getElementById('data-table');
	var html = '';
	for (var x = 0; x < data.length; x++) {
	    html += '<tr>';
	    html += 	'<td>' + (x+1) + '</td	>';
	    html += 	'<td>' + isnull(data[x].name) + '</td>';
	    html += 	'<td><img class="tupian" src="'+ conertPicPath(data[x].icon) +'" /> </td>';
	    if(data[x].status == 0){
	    	html += 	'<td>启用</td>';
	    }else if(data[x].status == 1){
	    	html += 	'<td>停用</td>';
	    }else{
	    	html += 	'<td></td>';
	    }
	    html += '<td><a href="javascript:;" class="layui-btn" onclick=modify(' + data[x].id + ')>修改</a>';
	    if(data[x].status == 0){
	    	html += '<a href="javascript:;" class="layui-btn" onclick=closeThis(' + data[x].id + ') >停用</a>';
	    }else{
	    	html += '<a href="javascript:;" class="layui-btn layui-btn-danger"   onclick=openThis(' + data[x].id + ')>启用</a>';
	    }
	    html += '<a href="javascript:;" class="layui-btn" onclick=deleteThis(' + data[x].id + ')>删除</a></td>';
	    html += '</tr>';
	}
	$('#data-table').empty();
	$('#data-table').append(html);
}

//启用
function  openThis(id){
	callAjax("common/change", {tableName:"interrupt_type",value:"0",keyValue:id}, function(data) {
		if(!(data.code == 200 && data.data)) {
			layer.msg("启用失败");
			return;
		}
		layer.msg("启用成功");
		initTable();
	});
}

//停用
function  closeThis(id){
	callAjax("common/change", {tableName:"interrupt_type",value:"1",keyValue:id,}, function(data) {
		if(!(data.code == 200 && data.data)) {
			layer.msg("停用失败");
			return;
		}
		layer.msg("停用成功");
		initTable();
	});
}

//删除记录
function deleteThis(id) {
	layer.open({
		  content: '确定删除该记录？'
		  ,btn: ['确定', '取消']
		  ,yes: function(index, layero){
			layer.close(index); // 关闭信息框
			callAjax("common/change", {tableName:"interrupt_type",value:"9",keyValue:id,}, function(data) {
				layer.msg("删除成功");
				initTable();
			});
		  }
	     ,btn2: function(index, layero){
		  }
		  ,cancel: function(){ 
		 }
	});
}


var index ='';
// 修改
function  modify(id){
	//清空数据
	$('#modifyForm input').val('');
	$('#modifyForm select').val('');	
	if(id){
		callAjax("interruptType/byid", {id:id}, function(data) {
			doModify(data.data);
		});		
	}
		

	// 重新渲染DOM
    form.render('select');
	index=layer.open({
			type : 1,
			content : $('#modifyDiv'),
			title : false,
			closeBtn : 0,
			shadeClose : true,
			area: ['600px', '500px'],
	});
	
	$('#cancelBtn').click(function(){
		layer.close(index); 
	});
	
}

// 回显数据
function doModify(data){
	$('#id').val(data.id) ;
    $('#name').val(data.name) ;
    $('#icon').val(data.icon) ;
    $('#pathURL').attr('src', picPath+data.icon); 
	//重新渲染DOM
    form.render('select');
}


//上传图片
  layui.use('upload', function(){
  	  var $ = layui.jquery;
  	  var upload = layui.upload;
  	  //普通图片上传
  	  var uploadInst = upload.render({
  	    elem: '#upload'
  	    ,url: globleURL+'file/upload'
  	    ,data:{path:"kanya"}
  	    ,before: function(obj){
  	      //预读本地文件示例，不支持ie8
  	      obj.preview(function(index, file, result){
  	        $('#pathURL').attr('src', result); //图片链接（base64）
  	      });
  	    }
  	    ,done: function(res){
  	        //如果上传失败
  	        if(res.code != 200){
  	          return layer.msg('上传失败');
  	        }
  	        //上传成功
  	        $('#icon').val(res.data[0].path);
  	      }
  	      ,error: function(){
  	        //演示失败状态，并实现重传
  	        var demoText = $('#demoText');
  	        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
  	        demoText.find('.demo-reload').on('click', function(){
  	          uploadInst.upload();
  	        });
  	      }
  	 });
  });
  

