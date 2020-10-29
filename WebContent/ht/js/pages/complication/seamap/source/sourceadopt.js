$(function(){
	sourceadopt.init();
})

var sourceadopt = {
	/**
	 * 初始化
	 */
	init : function(){
		grid.init("sourceAdopt");
		loading.init();
		try{
			sourceadopt.createSourceAdoptGrid();
			sourceadopt.requestSourceAdoptData();
		}
		catch(err){
			loading.close();
		}
	},
	
	/**
	 * 构建区域管理列表列集合
	 */
	createSourceAdoptColumns : function(){
		grid.resetColumn();
		grid.addNoCheckColumn("5%","id","图层");
		grid.addNoCheckColumn("5%","id","资料类型");
		grid.addNoCheckColumn("5%","id","图号");
		grid.addNoCheckColumn("5%","id","比例尺 1:");
		grid.addNoCheckColumn("5%","id","坐标系");
		grid.addNoCheckColumn("5%","id","投影");
		grid.addNoCheckColumn("5%","id","深度基准");
		grid.addNoCheckColumn("5%","id","文件类型");
		
		grid.addNoCheckColumn("5%","id","版次/日期");
		return grid.addNoCheckColumn("5%","id","资料来源");
	},
	
	/**
	 * 发送数据请求
	 */
	requestSourceAdoptData : function(){
		common.init("../source/sourceadopt?id="+sourceId.value,"POST", function(result){
			grid.bindData(result);
		});
		common.do_submit();
	},
	
	/**
	 * 创建区域管理列表
	 */
	createSourceAdoptGrid : function(){
		var columns = sourceadopt.createSourceAdoptColumns();
		grid.createGrid(columns);
	}
}
