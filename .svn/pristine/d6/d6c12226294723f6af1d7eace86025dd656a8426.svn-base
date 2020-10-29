package com.ht.front.pages.system.workflow.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.Span;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.workflow.common.ProcessDefinitionInfo;


/**
 * 创建工作流页面初始化类
 * */
public class CreateWorkFlowPage {
	
	/**
	 * 初始化工作流数据页面
	 * @return 节点字符串
	 * */
	public String getWorkFlowPage(List<ProcessDefinitionInfo> workFlowList,List<BaseData> processTypeList) {
		List<Base> baseList = new ArrayList<>();
		FrontUtil util = FrontUtil.getInstance();
		// 流程选择
		Base processSelect = util.creatDefaultSelectGroup("选择流程", "processType", processTypeList, "code", "value", true);		
		//任务添加按钮
		CssClass addTaskCss = new CssClass("col-md-12 col-xs-12 input-group");
		Div addTaskDiv = Div.getInstance(null, addTaskCss, null);
		//添加任务
		Span span = Span.getDefault("添加任务：");
		Prop propWidth = new Prop();
		propWidth.setPropKey("style");
		propWidth.setPropValue("width:40px;");
		span.addProp(propWidth);
		//添加任务Div
		addTaskDiv.addChildNode(span);
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success search120 btn-setting");
		Button button = Button.getButtonWithIcon("add", css, "添加任务", i);
		addTaskDiv.addChildNode(button);
		//任务添加按钮
		 css = new CssClass("fa fa-plus");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info search120 bk-margin-5");
		button = Button.getButtonWithIcon("addEndTask", css, "结束任务", i);
		addTaskDiv.addChildNode(button);		
		Base processTypeRow = util.createRow();
		Base processTypeCol = util.createColumn(processTypeRow,"12", "12", "12", null);
		processTypeCol.addChildNode(processSelect);
		baseList.add(processTypeRow);
		baseList.add(addTaskDiv);
		
		/** 创建Grid行  开始*/
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"createworkflow");
		/** 创建Grid行  结束*/
		// 将行加入到容器
		baseList.add(rowGrid);
		
	
		//添加提交返回按钮
		Base rowOperation = util.createRow();	
		// 创建编辑流程图按钮
//		Base opratorImage = util.createColumn(rowOperation,"3","2","2",null);
//		CssClass cssBtnImage = new CssClass("btn btn-default");
//		Button btnImage = Button.getInstance("image", cssBtnImage, "流程图样式");
//		opratorImage.addChildNode(btnImage);
		
		// 创建确定按钮列
		Base opratorSubmit = util.createColumn(rowOperation,"3","2","2",null);
		// 创建确定按钮
		CssClass cssBtnSuccess = new CssClass("btn btn-success");
		Button btnSuccess = Button.getInstance("submit", cssBtnSuccess, "提交");
		opratorSubmit.addChildNode(btnSuccess);
		// 创建取消按钮
		Base opratorCancel = util.createColumn(rowOperation,"3","2","2",null);
		CssClass cssBtnCancel = new CssClass("btn btn-default");
		Button btnCancel = Button.getInstance("back", cssBtnCancel, "返回");
		opratorCancel.addChildNode(btnCancel);
		
		Base root = createFromPage(baseList);
		
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		root.addChildNode(rowSpace);
		root.addChildNode(rowOperation);
		root.addChildNode(rowSpace);
		root.addChildNode(addTaskDialog(workFlowList));
		root.addChildNode(addDirectionDialog());
		root.addChildNode(addApprovalDialog());
		//返回节点字符串
		return root.getNode();
	}
	
	/**
	 * 添加设置审批Dialog
	 * @return
	 */
	public Base addApprovalDialog(){
		/** 创建Modal Dialog 开始*/
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("approvalSet", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		modalDiv.addChildNode(dialogDiv);
		// 创建div 
		CssClass contentCss = new CssClass("modal-content");
		Div contentDiv = Div.getInstance(null, contentCss, null);
		dialogDiv.addChildNode(contentDiv);
		// 创建header div
		CssClass headerCss = new CssClass("modal-header");
		Div headerDiv = Div.getInstance(null, headerCss, null);
		contentDiv.addChildNode(headerDiv);
		// 构建关闭按钮
		CssClass closeCss = new CssClass("close");
		Button closeBtn = Button.getInstance(null, closeCss, "&times;");
		Prop closeProp = new Prop();
		closeProp.setPropKey("data-dismiss");
		closeProp.setPropValue("modal");
		Prop closeProps = new Prop();
		closeProps.setPropKey("aria-hidden");
		closeProps.setPropValue("true");
		// 绑定属性
		closeBtn.addProp(closeProp);
		closeBtn.addProp(closeProps);
		headerDiv.addChildNode(closeBtn);
		// 创建标题div
		CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "设置审批角色");
		headerDiv.addChildNode(titleDiv);
	
		InputGroup setApprovalRole = InputGroup.getSelectGroup("设置审批角色", "approvalRoleSelete",null, "id", "userName", "false");
		/** 创建Grid行  结束*/
		// 将行加入到容器
		headerDiv.addChildNode(setApprovalRole);
		
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("commitApproalGroup", importCss, "确认");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束*/
		return modalDiv;
	}
	
	/**
	 * 添加设置流向Dialog
	 * @return
	 */
	public Base addDirectionDialog(){
		/** 创建Modal Dialog 开始*/
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("directionSet", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		modalDiv.addChildNode(dialogDiv);
		// 创建div 
		CssClass contentCss = new CssClass("modal-content");
		Div contentDiv = Div.getInstance(null, contentCss, null);
		dialogDiv.addChildNode(contentDiv);
		// 创建header div
		CssClass headerCss = new CssClass("modal-header");
		Div headerDiv = Div.getInstance(null, headerCss, null);
		contentDiv.addChildNode(headerDiv);
		// 构建关闭按钮
		CssClass closeCss = new CssClass("close");
		Button closeBtn = Button.getInstance(null, closeCss, "&times;");
		Prop closeProp = new Prop();
		closeProp.setPropKey("data-dismiss");
		closeProp.setPropValue("modal");
		Prop closeProps = new Prop();
		closeProps.setPropKey("aria-hidden");
		closeProps.setPropValue("true");
		// 绑定属性
		closeBtn.addProp(closeProp);
		closeBtn.addProp(closeProps);
		headerDiv.addChildNode(closeBtn);
		// 创建标题div
		CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "设置流向");
		headerDiv.addChildNode(titleDiv);
	
		// 创建文件input框
		List<BaseData> listBaseData = new ArrayList<BaseData>();
		BaseData sType = new BaseData();
		sType.setId("step");
		sType.setValue("任务");
		listBaseData.add(sType);
		BaseData pType = new BaseData();
		pType.setId("subprocess");
		pType.setValue("子流程");
		listBaseData.add(pType);
		
		Base successNextStep = FrontUtil.getInstance().creatDefaultSelectGroup("同意", "successNextStep",null, "id", "value", "false");
		Base failNextStep = FrontUtil.getInstance().creatDefaultSelectGroup("返回", "failNextStep",null, "id", "value", "false");
		
		FrontUtil util = FrontUtil.getInstance();
		Base rowSpace = util.createRowSpace();
		headerDiv.addChildNode(rowSpace);
		headerDiv.addChildNode(successNextStep);
		headerDiv.addChildNode(rowSpace);
		headerDiv.addChildNode(failNextStep);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("commitDirection", importCss, "确认");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束*/
		return modalDiv;
	}
	
	/**
	 * 添加任务Dialog
	 * @return
	 */
	public Base addTaskDialog(List<ProcessDefinitionInfo> workFlowList){
		/** 创建Modal Dialog 开始*/
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("createTask", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		modalDiv.addChildNode(dialogDiv);
		// 创建div 
		CssClass contentCss = new CssClass("modal-content");
		Div contentDiv = Div.getInstance(null, contentCss, null);
		dialogDiv.addChildNode(contentDiv);
		// 创建header div
		CssClass headerCss = new CssClass("modal-header");
		Div headerDiv = Div.getInstance(null, headerCss, null);
		contentDiv.addChildNode(headerDiv);
		// 构建关闭按钮
		CssClass closeCss = new CssClass("close");
		Button closeBtn = Button.getInstance(null, closeCss, "&times;");
		Prop closeProp = new Prop();
		closeProp.setPropKey("data-dismiss");
		closeProp.setPropValue("modal");
		Prop closeProps = new Prop();
		closeProps.setPropKey("aria-hidden");
		closeProps.setPropValue("true");
		// 绑定属性
		closeBtn.addProp(closeProp);
		closeBtn.addProp(closeProps);
		headerDiv.addChildNode(closeBtn);
		// 创建标题div
		CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "创建任务");
		headerDiv.addChildNode(titleDiv);
	
		// 创建文件input框
		List<Map<String,String>> taskTypeList = new ArrayList<Map<String,String>>();
		Map<String,String> taskTypeMap = new HashMap<String, String>();
		taskTypeMap.put("type", "0");
		taskTypeMap.put("value", "任务");
		taskTypeList.add(taskTypeMap);
		taskTypeMap = new HashMap<String, String>();
		taskTypeMap.put("type", "1");
		taskTypeMap.put("value", "调用子流程");
		taskTypeList.add(taskTypeMap);
		InputGroup taskType = InputGroup.getSelectGroupWithDefaultOption("任务类别", "taskType", taskTypeList, "type", "value", true);
		headerDiv.addChildNode(taskType);
		InputGroup taskName = InputGroup.getInGroup("任务名称","taskName", "", "请输入任务名称 ");
		headerDiv.addChildNode(taskName);
		List<Map<String,String>> flowList = new ArrayList<Map<String,String>>();
		if (workFlowList != null) {
			for (int i = 0; i < workFlowList.size(); i++) {
				Map<String,String> flowMap = new HashMap<String, String>();
				flowMap.put("key", workFlowList.get(i).getKey());
				flowMap.put("name", workFlowList.get(i).getName());
				flowList.add(flowMap);
			}
		}
		InputGroup flows = InputGroup.getSelectGroupWithDefaultOption("流程列表", "flow", flowList, "key", "name", false);
		headerDiv.addChildNode(flows);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("commitTask", importCss, "确认");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束*/
		return modalDiv;
	}
	
	public Base createFromPage(List<Base> groupList){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "工作流创建");
		// 创建上间距
		Base rowSpace = util.createRowSpace();
		// 循环
		for (int i = 0; i < groupList.size(); i++) {
			// 创建行
			Base row = util.createRow();
			// 创建列
			Base column = util.createColumn(row,"12","12","12","0");
			// 将组件放入到列
			column.addChildNode(groupList.get(i));
			// 将行放入到容器中
			root.addChildNode(rowSpace);
			root.addChildNode(rowSpace);
			root.addChildNode(row);
		}
		return root;
	}
}
