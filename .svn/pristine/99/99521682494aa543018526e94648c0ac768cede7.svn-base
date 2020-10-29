package com.ht.front.pages.drawtask.tasksplit;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.Input;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.util.FrontUtil;
import com.ht.service.impl.system.workflow.task.ProcessTypeEnum;
import com.ht.service.impl.system.workflow.util.BusinessUtil;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;


/**
 * 编绘任务清单前台页面初始化类
 * */
public class TaskSplitPage {
	
	/**
	 * 初始化编绘任务拆分
	 * @return 节点字符串
	 * */
	public String getListPage(String flag,BaseDataService baseDataService) {
		FrontUtil util = FrontUtil.getInstance();
		Base root  = util.createRoot();
		util.createHeaderBar(root, BusinessUtil.getInstance().getDecriptionNameByProcess(flag));
		util.createRowSpace(root);
		// 创建Grid
		util.createGrid(root,"taskSplit");
		//拆分按钮
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-primary");
		Button splitButton = Button.getButtonWithIcon("", css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("split");
		splitButton.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("splitPage(this)");
		splitButton.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("拆分");
		splitButton.addProp(prop);
		Script script = Script.getInstance("splitTemplate");
		script.addChildNode(splitButton);
		// 构建隐藏按钮
		Div div = Div.getInstance(null, null, null);
		css = new CssClass("fa fa-plus");
		i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5 btn-setting");
		Button hiddenButton = Button.getButtonWithIcon("hiddenButton", css, null, i);
		Prop hiddenProp = new Prop();
		hiddenProp.setPropKey("style");
		hiddenProp.setPropValue("display:none");
		hiddenButton.addProp(hiddenProp);
		div.addChildNode(hiddenButton);
		// 不是改正通告
		/** 创建Modal Dialog 拆分  开始*/
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("myModal", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		modalDiv.addChildNode(dialogDiv);
		// 创建div 
		CssClass contentCss = new CssClass("modal-content");
		Div contentDiv = Div.getInstance(null, contentCss, null);
		Prop contentProp = new Prop();
		contentProp.setPropKey("style");
		contentProp.setPropValue("width:400px");
		contentDiv.addProp(contentProp);
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
		closeBtn.addProp(closeProp);
		closeProp = new Prop();
		closeProp.setPropKey("aria-hidden");
		closeProp.setPropValue("true");
		closeBtn.addProp(closeProp);
		closeProp = new Prop();
		closeProp.setPropKey("id");
		closeProp.setPropValue("windowClose");
		closeBtn.addProp(closeProp);
		// 绑定属性
		headerDiv.addChildNode(closeBtn);
		// 创建标题div
		CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "编绘任务拆分");
		headerDiv.addChildNode(titleDiv);
		//分类下拉框
		Base row = util.createRow();
		if(ProcessTypeEnum.TASK_BOOK.name().equals(flag)){
			Base column = util.createColumn(row, "4", "4", "3", null);
			// 将组件放入到列
			Input input = Input.getInstance(ProcessTypeEnum.SEA_MAP_COMPILATION_SOURCE_DATA.name(), "splitType","源数据编绘","源数据编绘");
			column.addChildNode(input);
			column = util.createColumn(row, "4", "4", "3", null);
			input = Input.getInstance(ProcessTypeEnum.SEA_MAP_COMPILATION_PAPER.name(),"splitType", "纸海图编绘", "纸海图编绘");
			column.addChildNode(input);
			column = util.createColumn(row, "4", "4", "3", null);
			input = Input.getInstance(ProcessTypeEnum.SEA_MAP_COMPILATION_ELECTRONIC.name(),  "splitType","电子海图编绘","电子海图编绘");
			column.addChildNode(input);
		}
//		if(ProcessTypeEnum.TASK_BOOK_PROJECT_SPECIAL.name().equals(flag)){
//			// 将组件放入到列
//			Base column = util.createColumn(row, "6", "6", "6", null);
//			Input input = Input.getInstance(ProcessTypeEnum.PROJECT_SPECIAL_PAPER.name(),"splitType", "工程&专题图-纸海图编绘", "工程&专题图-纸海图编绘");
//			column.addChildNode(input);
//			column = util.createColumn(row, "6", "6", "6", null);
//			input = Input.getInstance(ProcessTypeEnum.PROJECT_SPECIAL_ELECTRONIC.name(),  "splitType","工程&专题图-电子海图编绘","工程&专题图-电子海图编绘");
//			column.addChildNode(input);
//		}
//		if (ProcessTypeEnum.TASK_BOOK_SMALL_CORRECTION.name().equals(flag))
//		{
//			// 将组件放入到列
//			Base column = util.createColumn(row, "6", "6", "6", null);
//			Input input = Input.getInstance(ProcessTypeEnum.SMALL_CORRECTION_PAPER.name(),"splitType", "小改正-纸海图编绘", "小改正-纸海图编绘");
//			column.addChildNode(input);
//			column = util.createColumn(row, "6", "6", "6", null);
//			input = Input.getInstance(ProcessTypeEnum.SMALL_CORRECTION_ELECTRONIC.name(),  "splitType","小改正-电子海图编绘","小改正-电子海图编绘");
//			column.addChildNode(input);
//		}
//		if (ProcessTypeEnum.TASK_BOOK_OTHER_NAVIGATIONAL.name().equals(flag))
//		{
//			// 将组件放入到列
//			Base column = util.createColumn(row, "6", "6", "6", null);
//			Input input = Input.getInstance(ProcessTypeEnum.OTHER_NAVIGATIONAL.name(),"splitType", "其他航海图编绘", "其他航海图编绘");
//			column.addChildNode(input);
//		}
		headerDiv.addChildNode(row);
		
		
		// 构建确定按钮
		Base rowOperation = util.createRow();
		// 创建确定按钮
		css = new CssClass("btn btn-success bk-margin-5");
		Base submit = Button.getButtonWithIcon("submit", css, "确定", null);
		rowOperation.addChildNode(submit);
		// 创建取消按钮
		css = new CssClass("btn btn-default bk-margin-5");
		Base cancel = Button.getButtonWithIcon("back", css, "取消", null);
		rowOperation.addChildNode(cancel);
		headerDiv.addChildNode(util.createRowSpace());
		headerDiv.addChildNode(rowOperation);
		/** Modal Dialog 结束*/
			root.addChildNode(modalDiv);
		root.addChildNode(div);
		InputHidden hiddenId = InputHidden.getInstance("flag",flag);
		root.addChildNode(hiddenId);
		//返回节点字符串
		return root.getNode()+script.getNode();
	}
}
