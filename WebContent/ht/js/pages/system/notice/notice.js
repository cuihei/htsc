$(function(){
	notice.init();
})

/** 绑定编辑通知窗口按钮的click事件*/
function editPage(obj) {
	var tr = $(obj).parent().parent();
	notice.isRelease(tr);
}

var notice = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("notice");
		loading.init();
		try{
			notice.createNoticeGrid();
			notice.requestNoticeData();
			notice.bindPageEvent();
			notice.initMultiselect();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 初始化多选控件
	 */
	initMultiselect : function(){
		$("#userId").kendoMultiSelect({
	        placeholder: "请选择发布人员",
	        dataTextField: "userName",
	        dataValueField: "id",
	        autoBind:false,
	    });
		
		$("#orgId").kendoMultiSelect({
	        placeholder: "请选择发布部门",
	        dataTextField: "orgName",
	        dataValueField: "id",
	        autoBind:false,
	    });
	},
	
	/**
	 * 构建通知类型集合
	 */
	createNoticeColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","title","标题");
		grid.addColumn("30%","description","描述");
		grid.addColumn("30%","notice_Type","通知类型");
		grid.addColumn("30%","modifyDate","修改时间");
		return grid.addColumn("15%","handle","操作",kendo.template($("#editTemplate").html()));
	},
	/**
	 * 创建通知类型列表
	 */
	createNoticeGrid : function(){
		var columns = notice.createNoticeColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestNoticeData : function(){
		var flag = $("#flag").val();
		common.init("../notice/list?flag="+flag,"POST",notice.bindNoticeGrid);
		common.do_submit();
	},
	
	/**
	 * 删除成功
	 * */
	removeSuccess : function(){
		grid.init("notice");
		layer.close(1);
		layer.msg('删除成功!');
		var flag = $("#flag").val();
		common.init("../notice/list?flag="+flag,"POST",notice.bindNoticeGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindNoticeGrid : function(result){
		grid.bindData(result);
		//grid.setEvents();
	},
	
	/**
	 * 是否发布校验
	 */
	isRelease : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取通知ID
		var noticeId = rowData.id;
		var data = common.add_param("noticeId",noticeId);
		$("#noticeId").val(noticeId);
		common.init("../notice/isRelease","POST",notice.toNoticeEditPage);
		common.do_submit(data);
	},
	
	/**
	 * 跳转到通知编辑页面
	 */
	toNoticeEditPage : function(result){
		if(result == null){
			var noticeId = $("#noticeId").val();
			common.toPage("../notice/edit_init?noticeId="+noticeId);
		}else {
			layer.msg("当前通知已经发布，不能修改！");
		}
	},
	
	/**
	 * 跳转到通知增加页面
	 */
	toNoticeAddPage : function(){
		common.toPage("../notice/edit_init");
	},
	
	/**
	 * 删除通知
	 */
	removeNotices : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length <= 0) {
			layer.msg('未选择任何行!', {icon:5,time:1500});
			return;
		}
		/*删除*/
		layer.confirm('确认要删除吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var notices = [];
			$.each(rowDatas,function(i,item){
				var id = $("#notice").data("kendoGrid").dataItem(item).id;
				var notice = {};
				notice.id = id;
				notices.push(notice);
			});
			var param = JSON.stringify(notices);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("notice",param);
			common.init("../notice/remove","POST",notice.removeSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 绑定页面事件
	 */
	bindPageEvent : function(){
		/** 
		 * 绑定增加通知按钮的click事件
		 */
		$("#add").on("click",function(){
			notice.toNoticeAddPage();
		});
		
		/** 
		 * 绑定删除通知按钮的click事件
		 */
		$("#remove").on("click",function(){
			notice.removeNotices();
		});
		
		/** 绑定刷新按钮的click事件*/
		$("#refresh").on("click",function(){
			window.location.reload();
		});
		
		/** 绑定导出通知按钮的click事件*/
		$("#export").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			// 判断是否选中行
			if (rowDatas.length<=0) {
				layer.msg('未选择任何行!', {icon:5,time:1000});
				return false;
			}
			var notices = [];
			$.each(rowDatas,function(i,item){
				var id = $("#notice").data("kendoGrid").dataItem(item).id;
				var notice = {};
				notice.id = id;
				notices.push(notice);
			});
			var param = JSON.stringify(notices);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("notice",param);
			window.location.href = "../notice/export?notice="+param;
		});
	

		/** 绑定发布按钮的click事件*/
		$("#publishSubmit").on("click",function(){
			var rowDatas = grid.getSelectRowsData();
			var notices = [];
			$.each(rowDatas,function(i,item){
				var id = $("#notice").data("kendoGrid").dataItem(item).id;
				var notice = {};
				notice.id = id;
				notices.push(notice);
			});
			//将数组转换成JOSN数据
			var noticeJson = JSON.stringify(notices);
			
			userIds = "";
			var userItem = $("#userId").data("kendoMultiSelect").dataItems();
			$.each(userItem,function(i,item){
				userIds += ','+item.id;
			});
			orgIds = "";
			var orgItem = $("#orgId").data("kendoMultiSelect").dataItems();
			$.each(orgItem,function(i,item){
				orgIds += ','+item.id;
			});
			userIds = userIds.substring(1);
			orgIds = orgIds.substring(1);
			if(userIds == ''&&orgIds == ''){
				layer.msg('请至少选择人员或者机构其中一种!', {icon:5,time:1000});
				return false;
			}
			common.add_param("user",userIds);
			common.add_param("org",orgIds);
			var param = common.add_param("notice",noticeJson);
			common.init("../une/add","POST",function(result){
				// 执行用户通知列表刷新按钮点击事件
				parent.$("#refresh").click();
				layer.close(1);
				layer.msg('发布成功!');
			});
			common.do_submit(param);
			
		});
		

		/** 绑定查看按钮的click事件*/
		 $("#look").on("click",function(){
			//调回通知列表显示页面
			 var flag = $("#flag").val();
			 common.toPage("../unv/index?flag="+flag);
			//执行刷新通知列表按钮
			 parent.$("#refresh").click();
		 });
		 
		 /** 发布通知按钮点击事件 */
		 $("#publish").on("click",function(){
			 var rowDatas = grid.getSelectRowsData();
				if (rowDatas.length<=0) {
					layer.msg("请至少选择一条通知！");
					return false;
				}
				$('#myModal').modal('show');
		 });
	}
}