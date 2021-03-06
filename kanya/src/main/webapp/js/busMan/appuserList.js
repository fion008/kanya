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


}
function initEvent() {
	$('#searchBtn').click(function () {
		initTable();
	});
}



function initTable() {
	// 根据用户设置的查询条件获取数据，生成分页并跳转至第一页
	var url = 'appuser/pagelist';
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
	var url = 'appuser/pagelist';
	callAjax(url, param, function (data) {printTable(data.dataList);});
}

function printTable(data) {
	var target = document.getElementById('data-table');
	var html = '';
	for (var x = 0; x < data.length; x++) {
	    html += '<tr>';
	    html += 	'<td>' + (x+1) + '</td	>';
	    html += 	'<td>' + isnull(data[x].name) + '</td>';
	    html += 	'<td> <img class="tupian" src= ' + isnull(data[x].avatar) + '  /></td>';
	    html += 	'<td>' + isnull(data[x].phone) + '</td>';
	    if(data[x].identity == 0){
	    	html += 	'<td>医生</td>';
	    	html += '<td><a href="javascript:;" class="layui-btn" onclick=viewPatients(' + data[x].id + ',0)>查看患者</a> </td>';
	    }else if(data[x].identity == 1){
	    	html += 	'<td>患者代表</td>';
	    	html += '<td><a href="javascript:;" class="layui-btn" onclick=viewPatients(' + data[x].id + ',1)>查看患者</a> ';
	    	html += '<a href="javascript:;" class="layui-btn" onclick=beDoctor(' + data[x].id + ')>设为医生</a> </td>';
	    }else{
	    	html += 	'<td>未知</td>';
	    	html += 	'<td></td>';
	    }
	    html += '</tr>';
	}
	$('#data-table').empty();
	$('#data-table').append(html);
}


function viewPatients(id,type){
	window.open(webURL+"page/busMan/patients.html?type="+type+"&app_user_id="+id);
}

function beDoctor(id){
	callAjax("appuser/set", {id:id}, function(data) {
		if(!(data.code == 200 && data.data)) {
			layer.msg("操作失败");
			return;
		}
		layer.msg("操作成功");
		initTable();
	});
}
