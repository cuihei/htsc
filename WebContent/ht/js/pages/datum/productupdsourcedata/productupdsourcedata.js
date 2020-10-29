$(function(){
	productusd.init();
})

/** 绑定确定按钮的click事件*/
function updataPusdStatus(obj) {
	var tr = $(obj).parent().parent();
	productusd.updataPusdStatus(tr);
}

var productusd = {
	/**
	 * 初始化
	 */
	init : function(){
		
		var status = $("#status").val()
		if(status == ""){
			// 设置属性不可点击
			$("#add").attr("disabled","true");
			// 背景设置为灰色
			$("#add").attr("class","btn");
			$("#add").attr("style","background-color: gray;");
		}
		
		grid.init("productupdsourcedata");
		loading.init();
		try{
			productusd.createPusdGrid();
			productusd.requestPusdData();
			productusd.bindPageEvent();
		}catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建pusd类型集合
	 */
	createPusdColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","draw","图号");
		grid.addColumn("30%","figure_caption","图名");
		grid.addColumn("30%","compiler","编绘员");
		grid.addColumn("30%","quality_inspector","质检员");
		grid.addColumn("30%","authorized_member","审定员");
		grid.addColumn("30%","sourcedata_problem","源数据问题");
		return grid.addColumn("15%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	
	/**
	 * 创建pusd类型列表
	 */
	createPusdGrid : function(){
		var columns = productusd.createPusdColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestPusdData : function(){
		common.init("../product/list","POST",productusd.bindPusdGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindPusdGrid : function(result){
		grid.bindData(result);
		// 根据不同状态控制按钮权限
		var status = $("#status").val();
		var rowDatas = common.get_all_rows($("#productupdsourcedata"));
		$.each(rowDatas,function(i,item){
			var userName = $("#userName").val();
			var compiler = $("#productupdsourcedata").data("kendoGrid").dataItem(item).compiler;
			var quality_inspector = $("#productupdsourcedata").data("kendoGrid").dataItem(item).quality_inspector;
			var authorized_member = $("#productupdsourcedata").data("kendoGrid").dataItem(item).authorized_member;
			// 判断当前登录人是否是三种操作员中的其中之一
			if(userName != compiler && userName != quality_inspector && userName != authorized_member){
				// 设置属性不可点击
				$(item).find('[name=editNotice]').attr("disabled","true");
				// 背景设置为灰色
				$(item).find('[name=editNotice]').attr("class","btn");
				$(item).find('[name=editNotice]').attr("style","background-color: gray;");
			}
			// 判断当前登录人角色，与每条数据的操作角色状态是否一致
			var data_status = $("#productupdsourcedata").data("kendoGrid").dataItem(item).status;
			if(data_status != status){
				// 设置属性不可点击
				$(item).find('[name=editNotice]').attr("disabled","true");
				// 背景设置为灰色
				$(item).find('[name=editNotice]').attr("class","btn");
				$(item).find('[name=editNotice]').attr("style","background-color: gray;");
			}
			if(data_status == "1"){
				$(item).find('td:eq(4)').html(compiler+"（<span style='color:red'>待确认</span>）");
				$(item).find('td:eq(5)').html(quality_inspector+"（<span style='color:red'>待确认</span>）");
				$(item).find('td:eq(6)').html(authorized_member+"（<span style='color:red'>待确认</span>）");
			}
			if(data_status == "2"){
				$(item).find('td:eq(4)').html(compiler+"（<span style='color:green'>已确认</span>）");
				$(item).find('td:eq(5)').html(quality_inspector+"（<span style='color:red'>待确认</span>）");
				$(item).find('td:eq(6)').html(authorized_member+"（<span style='color:red'>待确认</span>）");
			}
			if(data_status == "3"){
				$(item).find('td:eq(4)').html(compiler+"（<span style='color:green'>已确认</span>）");
				$(item).find('td:eq(5)').html(quality_inspector+"（<span style='color:green'>已确认</span>）");
				$(item).find('td:eq(6)').html(authorized_member+"（<span style='color:red'>待确认</span>）");
			}
			if(data_status == "4"){
				$(item).find('td:eq(4)').html(compiler+"（<span style='color:green'>已确认</span>）");
				$(item).find('td:eq(5)').html(quality_inspector+"（<span style='color:green'>已确认</span>）");
				$(item).find('td:eq(6)').html(authorized_member+"（<span style='color:green'>已确认</span>）");
			}
			
		});
	},
	
	/**
	 * 跳转到pusd增加页面
	 */
	toPusdAddPage : function(){
		common.toPage("../product/edit_init");
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加pusd按钮的click事件
		 */
		$("#add").on("click",function(){
			productusd.toPusdAddPage();
		});
		
		/** 绑定刷新按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
	},
	
	/**
	 * 确定
	 */
	updataPusdStatus : function(tr){
		var rowDatas = grid.getSelectRowsData();
		/* 提交 */
		layer.confirm('确定执行吗？',function(index){
			// 获取选中行数据对象
			var rowData = grid.getSelectRowDataByRow(tr);
			// 获取id
			var id = rowData.id;
			// 添加参数 @param 参数key；参数value
			common.init("../product/updataPusdStatus?id="+id,"POST",productusd.updataPusdStatusSuccess);
			// 执行提交操作
			common.do_submit();
		});
	},
	
	/**
	 * 操作成功
	 * */
	updataPusdStatusSuccess : function(result){
		if(result.value == 'success'){
			layer.close(1);
			layer.msg('操作成功');
			common.init("../product/list","POST",productusd.bindPusdGrid);
			common.do_submit();
		}
	}
}