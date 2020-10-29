$(function(){
	seeMore.init();
})

var seeMore = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("seeMore");
		loading.init();
		try{
			seeMore.createseeMoreGrid();
			seeMore.requestseeMoreData();
			seeMore.bindPageEvent();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建用户通知集合
	 */
	createseeMoreColumns : function(){
		grid.resetColumn();
		grid.addColumn("30%","title","标题");
		grid.addColumn("30%","description","描述");
		/*grid.addColumn("15%","handle","操作",kendo.template($("#a").html()));*/
		return grid.addColumn("30%","isRead","状态");;
	},
	
	/**
	 * 创建用户通知列表
	 */
	createseeMoreGrid : function(){
		var columns = seeMore.createseeMoreColumns();
		grid.createGrid(columns);
	},
	
	/**
	 * 发送数据请求
	 */
	requestseeMoreData : function(){
		var flag = $("#flag").val();
		common.init("../workSpace/list?flag="+flag+"&isRead="+flag,"POST",seeMore.bindseeMoreGrid);
		common.do_submit();
	},
	
	/**
	 * 接收服务器响应数据,绑定表格
	 * 这是一个回调函数，不用手动调用
	 */
	bindseeMoreGrid : function(result){
		var data = result.value;
		if(data != null && data != undefined && data != ""){
			for(var i=0;i<data.length;i++){
				// 
				var isRead = data[i].isRead;
				if(isRead=="是"){
					data[i]["isRead"] = "已读";
				}else{
					data[i]["isRead"] = "未读";
				}
			}
		}
		grid.bindData(result);
		// 标记为已读点击事件
    	var editBtns = $("a[name='read']");
		$.each(editBtns,function(i,item){
			$(item).on("click",function(){
				$(item).css("color","red");
				var tr = $(item).parent().parent();
				seeMore.isRead(tr);
			});
		})
	},
	
	/**
	 * 标记为已读
	 */
	isRead : function(tr){
		// 获取选中行数据对象
		var rowData = grid.getSelectRowDataByRow(tr);
		// 获取用户通知ID
		var userNoticeId = rowData.id;
		var userNotices = [];
		var userNotice = {};
		userNotice.id = userNoticeId;
		userNotices.push(userNotice);
		// 转成JSON
		var param = JSON.stringify(userNotices);
		// 跳转到资料类别编辑页面
		common.init("../workSpace/modify?userNoticeId="+param,"POST",null);
		common.do_submit();
	},
	
	/**
	 * 批量标记为已读
	 */
	readAlls : function(){
		var rowDatas = grid.getSelectRowsData();
		if (rowDatas.length <= 0) {
			layer.msg('未选择任何行!', {icon:5,time:1500});
			return false;
		}
		/*删除*/
		layer.confirm('确认要将所选通知全部标记为已读吗？',function(index){
			// 获取Grid的选中行
			var rowDatas = grid.getSelectRowsData();
			var userNotices = [];
			var flag = false;
			$.each(rowDatas,function(i,item){
				var id = $("#seeMore").data("kendoGrid").dataItem(item).id;
				var isRead = $("#seeMore").data("kendoGrid").dataItem(item).is_read;
				if(isRead == "是"){
					flag = true;
					return flag;
				}
				var userNotice = {};
				userNotice.id = id;
				userNotices.push(userNotice);
			});
			if(flag){
				layer.msg("您所选的通知中包含已读通知，请重新选择！");
				return;
			}
			var param = JSON.stringify(userNotices);
			// 添加参数 @param 参数key；参数value
			var data = common.add_param("userNoticeId",param);
			common.init("../workSpace/modify","POST",seeMore.readSuccess);
			// 执行提交操作
			common.do_submit(data);
		});
	},
	
	/**
	 * 标记已读成功回调
	 * */
	readSuccess : function(result){
		layer.msg('已标记为已读!');
		seeMore.requestseeMoreData();
	},
	
	/**
	 * 页面点击事件
	 */
	bindPageEvent : function(){
		/** 标记为已读点击事件 */
		$("#read").on("click",function(){
			seeMore.readAlls();
		});
		
		/** 返回点击事件 */
		$("#back").on("click",function(){
			common.toPage("../workSpace/init");
		});
		
		$("#isRead").change(function(){
			var flag = $("#flag").val();
			var isRead = $("#isRead").val();
			common.init("../workSpace/list?flag="+flag+"&isRead="+isRead,"POST",seeMore.bindseeMoreGrid);
			common.do_submit();
		});
		
	}
	
	
	
}