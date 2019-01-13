//预定义查询对象
var type=getQueryString("type");
var param = {
		type:'',
		keywords:''
};
// 启用layer模块
layui.use(['laypage', 'form'], function(){
	var laypage = layui.laypage;
	var form = layui.form;
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
	var url = 'user/pagelist';
	var paramForm = document.getElementById('paramFrom');
	param = formToJson(paramForm);
	param.pageNum=1;
	param.type=1; //来源小程序
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
	layui.use('laypage', function(laypage) {
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
	});
}
// 跳页不涉及查询对象属性的改变
function jumpTo(page) {
	console.log(page);
	$("#data-table").empty();
	param.pageNum=page;
	param.type=1; //来源小程序
	param.numPerPage=10;
	var url = 'user/pagelist';
	callAjax(url, param, function (data) {printTable(data.dataList);});
}

function printTable(data) {
	var target = document.getElementById('data-table');
	var html = '';
	for (var x = 0; x < data.length; x++) {
	    html += '<tr>';
	    html += 	'<td>' + (x+1) + '</td	>';
	    html += 	'<td>' + isnull(data[x].name) + '</td>';
	    if(data[x].avatar){
		    html += 	'<td><img  class="imgList"  src=' +conertPicPath(data[x].avatar)+ '></td>';
	    }else{
	    	html += 	'<td><img  class="imgList1"  src="../../images/moren.png"></td>';
	    }
	    html += 	'<td>' + isnull(data[x].phone) + '</td>';
	    html += 	'<td>' + isnull(data[x].sex) + '</td>';
	    html += 	'<td>' + isnull(data[x].birthday) + '</td>';
	    html += 	'<td>' + isnull(data[x].hk_province)+isnull(data[x].hk_city)+isnull(data[x].hk_area) + '</td>';
	    html += 	'<td>' + isnull(data[x].province)+isnull(data[x].city)+isnull(data[x].area) + '</td>';
	    html += 	'<td><a href="../../page/compMan/resumexq.html?id='+ data[x].id+'" target="_blank" class="layui-btn">简历信息</a></td>';
	    html += '</tr>';
	}
	$('#data-table').empty();
	$('#data-table').append(html);
}





