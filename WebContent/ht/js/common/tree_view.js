var _onSelect = null;
var _treeData = null;
var _tree = null;
var treeView = {

	/**
	 * 初始化
	 * @param treeId 树div id
	 * @param selectEvent 选择树节点事件
	 * @param treeData 树状结构数据
	 */
	init : function(treeId, selectEvent, treeData) {
		this._tree = $("#" + treeId);
		this._treeData = treeData;
		this._onSelect = selectEvent;
	},

	bindTree : function() {
		var dataObj = $.parseJSON(this._treeData);
		var dataSource = new kendo.data.HierarchicalDataSource({
			data : dataObj,
			schema : {
				model : {
					id : "id",
					children : "childrens"
				}
			}
		});

		this._tree.kendoTreeView({
			checkboxes : false,
			dataSource : dataSource,
			dataTextField : "name",
			select : this._onSelect,
			id : "id"
		});
	},
	
	/**
	 * 获取数据
	 */
	getData : function(node){
		var tree = this._tree.data("kendoTreeView");
		var data = tree.dataItem(node);
		return data;
	}
}