package com.ht.front.pages.system.workflow.task;

import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.File;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.organization.employee.User;

public class ProblemPage {

	/**
	 * 页面实例
	 */
	private static ProblemPage page = null;

	/**
	 * 获取页面实例
	 * 
	 * @return
	 */
	public static ProblemPage getInstance() {
		if (page == null) {
			page = new ProblemPage();
		}
		return page;
	}

	/**
	 * 初始化目录页面
	 * 
	 * @param
	 * @return 节点字符串
	 */
	public String getListPage(List<User> qualityUsers, List<User> approvalUsers,boolean flag) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "疑难问题");

		util.createRowSpace(root);
		// 创建第一行
		Base row = util.createRow(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base column = util.createColumn(rowBg, "8", "8");
		// 创建按钮组
		// 构建导入按钮
		if(flag){
			CssClass css = new CssClass("fa fa-plus");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 btn-setting");
			Button button = Button.getButtonWithIcon("add", css, "创建", i);
			column.addChildNode(button);
		}
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		// 创建Grid
		util.createGrid(root, "problem");
		/** 创建Grid行 结束 */
		/** 创建Modal Dialog 开始 */
		CssClass modelCss = new CssClass("modal fade");
		Div modalDiv = Div.getInstance("myModal", modelCss, null);
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
		CssClass titleCss = new CssClass(
				"modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "设置审批人员");
		headerDiv.addChildNode(titleDiv);
		// 创建form表单
		CssClass formCss = new CssClass("form-search");
		Form form = Form.getInstance("importForm", formCss, null);
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		form.addProp(formProp);
		Prop formProps = new Prop();
		formProps.setPropKey("enctype");
		formProps.setPropValue("multipart/form-data");
		form.addProp(formProps);
		headerDiv.addChildNode(form);
		// 获取质检组人员
		InputGroup taskType = InputGroup.getSelectGroup("质检人员", "quality",
				qualityUsers, "id", "userName", "false");
		headerDiv.addChildNode(taskType);
		InputGroup taskName = InputGroup.getSelectGroup("审定人员", "approval",
				approvalUsers, "id", "userName", "false");
		headerDiv.addChildNode(taskName);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("importSubmit", importCss, "提交");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		importBtn.addProp(importProp);
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束 */
		root.addChildNode(modalDiv);
		// 添加查看图片按钮
		return root.getNode();
	}

}