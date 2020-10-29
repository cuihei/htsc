var graph = {
	/**
	 * 浏览器支持校验
	 */
	checkBrowser : function() {
		var check = mxClient.isBrowserSupported()
		if (!check) {
			// 浏览器不支持弹出提示
			layer.msg("浏览器不支持绘图");
		}
		return check;
	},

	/**
	 * 创建连接图标
	 */
	createConnectImage : function() {
		mxConnectionHandler.prototype.connectImage = new mxImage(
				'../ht/resource/mxgraph/images/connector.gif', 16, 16);
		mxGraph.prototype.collapsedImage = new mxImage(
				'../ht/resource/mxgraph/images/collapsed.gif', 9, 9);
		mxGraph.prototype.expandedImage = new mxImage(
				'../ht/resource/mxgraph/images/expanded.gif', 9, 9);
	},

	/**
	 * 创建绘图容器和模板
	 * @param divContainer 面板div id
	 */
	createGraph : function(divContainer) {
		var container = document.getElementById(divContainer);
		container.style.background = 'url("../ht/resource/mxgraph/examples/editors/images/grid.gif")';
		container.style.height = document.body.clientHeight
				- (document.body.clientHeight) * 0.2 + "px";
		- (document.body.clientHeight) * 0.2 + "px";
		if (mxClient.IS_IE) {
			new mxDivResizer(container);
		}
		// 创建新图形模板 并且放入到容器内
		var model = new mxGraphModel();
		var graph = new mxGraph(container, model);
		// 设置是否允许连接
		graph.setConnectable(true);
		// 设置是否允许同一顶点多个连接
		graph.setMultigraph(true);
		// 设置cell可以选择
		graph.setCellsSelectable(true);
		// 设置cell可以移动
		graph.setCellsMovable(true);
		// 设置cell不可以被拉缩
		graph.setCellsResizable(false);
		graph.setCellsDisconnectable(true);
		// 设置不可编辑
		graph.setCellsEditable(false);
		// 使线在所有元素的底下
		graph.orderCells(false);
		// 允许ToolTip
		graph.setTooltips(true); 
		// 容器大小自适应
		graph.setResizeContainer(true);
		// 禁用浏览器默认的右键菜单栏
		mxEvent.disableContextMenu(container)
		//预览时鼠标悬浮到节点时，改变鼠标样式
		graph.getCursorForCell = function(cell){  
		    if (cell != null ){  
		         return 'pointer';  
		    }  
		};
		// 设置放大缩小时是否向中心移动
		graph.centerZoom = false;
		// FireFox 情况下
		if (mxClient.IS_MT) {
			container.addEventListener("DOMMouseScroll",function(e){
				 if (e.detail<0) {
			        	graph.zoomIn();
					}
			        else{
			        	graph.zoomOut()
			        }
			},false);
		}
		// 其他浏览器情况下
		else{
			container.onmousewheel = function(e) {
		        if (e.wheelDelta>0) {
		        	graph.zoomIn();
				}
		        else{
		        	graph.zoomOut()
		        }
		    };
		}
		return graph;
	},

	/**
	 * 设置全局样式
	 */
	setStyle : function(graph) {
		var style = graph.getStylesheet().getDefaultVertexStyle();
		style[mxConstants.STYLE_SHAPE] = 'treenode';
		style[mxConstants.STYLE_GRADIENTCOLOR] = '#00FF99';
		style[mxConstants.STYLE_SHADOW] = false;

		var styleEdge = graph.getStylesheet().getDefaultEdgeStyle();
		// 设置连接线弯曲形状是否为弧形
		styleEdge[mxConstants.STYLE_ROUNDED] = false;
		// 设置连接曲线样式
		styleEdge[mxConstants.STYLE_EDGE] = mxEdgeStyle.TopToBottom;
	},
	
	/**
	 * 设置元素的样式
	 */
	setCellStyle:function(graph,cell,style){
		var parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			cell.setStyle(style);
		} finally {
			//事务结束   
			graph.getModel().endUpdate();
		}
		
	},

	/**
	 * 创建一个新图形
	 */

	createItem : function(graph, value, basicProp,style,id) {
		var parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			var left = 0;
			var top = 0;
			var width = 0;
			var height = 0;
			if (basicProp != null) {
				left = basicProp.left;
				top = basicProp.top;
				width = basicProp.width;
				height = basicProp.height;
			}
			var v = graph.insertVertex(parent, id, value, left, top, width,
					height,style);
			var cell = graph.addCell(v);
			return cell;
		} finally {
			//事务结束   
			graph.getModel().endUpdate();
		}
	},
	
	createLable:function(graph,cell,value,style){
		graph.getModel().beginUpdate();
		try {
			var label = graph.insertVertex(cell, null, value, 0.5, 0.9, 0, 0, style, true);
		}
		finally {
			//事务结束   
			graph.getModel().endUpdate();
		}
	},

	createEdge:function(graph,value,v1, v2,style,id){
		var parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			var e = graph.insertEdge(parent,id, value, v1, v2,style);
		} finally {
			//事务结束   
			graph.getModel().endUpdate();
		}
		return e;
	},
	
	/**
	 * 链接两项
	 */
	connectItem : function(graph, v1, v2,style,value) {
		var parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			var e = graph.insertEdge(parent,null, value, v1, v2,style);
		} finally {
			//事务结束   
			graph.getModel().endUpdate();
		}
		return e;
	},

	/**
	 * 删除指定cell
	 */
	removeCells : function(graph, cells) {
		var parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			graph.removeCells(cells);
		} finally {
			//事务结束   
			graph.getModel().endUpdate();
		}
	},
	
	/**
	 * 删除cell
	 */
	removeCell:function(graph,cell){
		var cellArray =[];
		cellArray.push(cell);
		graph.removeCells(cellArray);
	},

	/**
	 * 获取全部元素
	 */
	getAllCells :function(graph){
		return graph.getChildVertices(graph.getDefaultParent());
	},
	
	/**
	 * 根据ID获取cell
	 */
	getCellById:function(graph,id){
		var model = graph.getModel();
		var cell = model.getCell(id);
		return cell;
	},
	
	setCellValue:function(graph,cell,value){
		graph.getModel().beginUpdate();
		cell.setValue(value);
		graph.getModel().endUpdate();
	},
	
	/**
	 * 移动cell
	 */
	moveCell:function(graph,cell,x,y){
		var geometry = cell.getGeometry();
		geometry.y = y;
		geometry.x = x;
		cell.setGeometry(geometry);
		// 刷新cell
		graph.refresh(cell);
		var count = cell.getEdgeCount();
		// 刷新线
		for (var int = 0; int < count; int++) {
			var edge = cell.getEdgeAt(int);
			if (edge!=null) {
				graph.refresh(edge);
			}
		}
	},
	
	/**
	 * 获取全部元素
	 */
	getAllEdges :function(graph){
		return graph.getChildEdges(graph.getDefaultParent());
	},
	
	
	/**
	 * 创建工具栏
	 */
	createToolbar : function(graphIns, tbContainer) {
		var container = document.getElementById(tbContainer);
		var toolbar = new mxToolbar(container);
		return toolbar;
	},
	
	/**
	 * 工具栏里添加点
	 */
	addToolbarItem : function(graphIns,toolbar,icon,func) {
		// 创建用于拖动图标的图像（预览）
		var img = toolbar.addMode(null, icon);
		if (func != null) {
			// 在其他监听之前监听
			mxEvent.addListener(img, "dblclick", func);
		}
		img.enabled = true;
		return img;
	},
	
	/**
	 * 编码图像的xml格式
	 */
	getXml : function(graph) {
		var encoder = new mxCodec();
		var node = encoder.encode(graph.getModel());
		var xml = mxUtils.getPrettyXml(node);
		return xml;
	},
	
	/**
	 * 编码图像的xml格式
	 */
	getXmlByNode : function(graph,node) {
		var encoder = new mxCodec();
		var node = encoder.encode(node);
		var xml = mxUtils.getPrettyXml(node);
		return xml;
	},

	/**
	 * 解码xml，生成图像
	 */
	getGraphFromXml : function(xml, graph) {
		graph.getModel().beginUpdate();
		var req = mxUtils.load(xml);
		var root = req.getDocumentElement();
		var dec = new mxCodec(root);
		dec.decode(root, graph.getModel());
		graph.getModel().endUpdate();
	},
	
	/**
	 * 获取第一个父节点cell
	 */
	getParentCell:function(graph,cell){
		var edgeCount =  cell.getEdgeCount();
		for (var int = 0; int < edgeCount; int++) {
			var edge = cell.getEdgeAt(int);
			if (edge!=null) {
				if (edge.target == cell) {
					return edge.source;
				}
			}
		}
		return null;
	},
	
	/**
	 * 获取子节点cell
	 */
	getChildCells:function(graph,cell){
		var childCells= [];
		var edgeCount =  cell.getEdgeCount();
		for (var int = 0; int < edgeCount; int++) {
			var edge = cell.getEdgeAt(int);
			if (edge!=null) {
				if (edge.source == cell) {
					if (edge.target!=null) {
						childCells.push(edge.target);
					}
				}
			}
		}
		return childCells;
	},
	
	/**
	 * 获取子链接线
	 */
	getChildEdges:function(graph,cell){
		var childEdges= [];
		var edgeCount =  cell.getEdgeCount();
		for (var int = 0; int < edgeCount; int++) {
			var edge = cell.getEdgeAt(int);
			if (edge!=null) {
				if (edge.source == cell) {
					childEdges.push(edge);
				}
			}
		}
		return childEdges;
	},
	
	/**
	 * 获取子链接线
	 */
	getEdges:function(graph,cell){
		var edges = [];
		var edgeCount =  cell.getEdgeCount();
		for (var int = 0; int < edgeCount; int++) {
			var edge = cell.getEdgeAt(int);
			edges.push(edge);
		}
		return edges;
	},
	
	/**
	 * 根据来源和去向获取连接线
	 */
	getEdgeBySourceAndTarget:function(graph,source,target){
		return graph.getEdgesBetween(source,target);
	},
	
	/**
	 * 是否叶子节点
	 */
	isLeafCell:function(graph,cell){
		var edgeCount =  cell.getEdgeCount();
		for (var int = 0; int < edgeCount; int++) {
			var edge = cell.getEdgeAt(int);
			if (edge!=null) {
				var style = graph.getCellStyle(edge);
				var color = style["strokeColor"];
				if (color!="black") {
					if (edge.source == cell) {
						return false;
					}
				}
			}
		}
		return true;
	},
	
	/**
	 * 图形放大
	 */
	blowUpGraph:function(graph){
		graph.zoomIn();
	},
	
	/**
	 * 图形缩小
	 */
	blowUpGraph:function(graph){
		graph.zoomOut();
	}
}
