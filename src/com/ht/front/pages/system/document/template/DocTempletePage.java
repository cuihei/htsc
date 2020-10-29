package com.ht.front.pages.system.document.template;


import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.ButtonGroup;
import com.ht.front.model.Div;
import com.ht.front.model.File;
import com.ht.front.model.I;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Span;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.complication.form.Form;
import com.ht.service.inter.complication.form.FormService;

public class DocTempletePage {

	FrontUtil util = null;
	
	Base root = null;
	
	public DocTempletePage(){
		// 获取前端工具实例
		util = FrontUtil.getInstance();
		root = util.createRoot();
	}
	
	/**
	 * 
	 * 初始化目录页面
	 * @param 
	 * @return 节点字符串
	 */
	public  String getListNode(String formId,FormService formService) throws Exception{
		// 获取前端工具实例
		util = FrontUtil.getInstance();
		util.createHeaderBar(root, "表单模板");
		// 创建行
		Base row = util.createRow();
		// 创建列
		Base col = util.createColumn(row, "12","12");
		// 创建取消按钮列
		util.createRow(col);
		CssClass css = new CssClass("col-md-12 col-xs-12 col-sm-12");
		Div div1 = Div.getInstance(null, css, null);
		css = new CssClass("fa fa-mail-reply-all");
		I ii = I.getInstance(css);
		css = new CssClass("btn btn-success ");
//		buttons = Button.getButtonWithIcon("save", css, "保存", null);
//		Prop saveProp=new Prop();
//		saveProp.setPropKey("onclick");
//		saveProp.setPropValue("SaveDoc();");
//		buttons.addProp(saveProp);
//		div1.addChildNode(buttons);
		Button buttons = Button.getButtonWithIcon("open", css, "上传", null);
		div1.addChildNode(buttons);
		buttons = Button.getButtonWithIcon("introduce", css, "说明文档", null);
		div1.addChildNode(buttons);
		buttons = Button.getButtonWithIcon("back", css, "返回", ii);
		div1.addChildNode(buttons);
		Form form = formService.getForm(formId);
		div1.addChildNode(Span.getDefault("表单："+form.getName()));
		col.addChildNode(div1);
		root.addChildNode(col);
		this.createGrid("docTemplete");
		InputHidden hidden = InputHidden.getInstance("formId", formId);
		root.addChildNode(hidden);
		createUploadDialog();
		return root.getNode();
	}
	/**
	 * 创建网格
	 * @param listName 网格id
	 */
	private void createGrid(String listName){
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行  开始*/
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,listName);
		/** 创建Grid行  结束*/
		// 将行加入到容器
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
	}
	/**
	 * 
	 * @param formId 表单id
	 * @return
	 */
	public String formDetilsPage(String formId,String taskInstId,String processInstId,boolean isDownLoad){
		// 创建取消按钮列
		Base col = util.createColumn(util.createRow(), "12","12");
		CssClass css = new CssClass("col-md-12 col-xs-12 col-sm-12");
		Div div1 = Div.getInstance(null, css, null);
		css = new CssClass("fa fa-mail-reply-all");
		I ii = I.getInstance(css);
		css = new CssClass("btn btn-success ");
		if(isDownLoad){
			A a=A.getInstance("..\\upload\\docTempleteDetils\\"+formId+".doc", "下载");
			Prop prop=new Prop();
			prop.setPropKey("class");
			prop.setPropValue("btn btn-success ");
			a.addProp(prop);
			div1.addChildNode(a);
		}
		Button buttons = Button.getButtonWithIcon("back", css, "返回", ii);
		div1.addChildNode(buttons);
		col.addChildNode(div1);
		root.addChildNode(col);
		InputHidden hidden = InputHidden.getInstance("formId", formId);
		root.addChildNode(hidden);
		hidden = InputHidden.getInstance("taskInstId", taskInstId);
		root.addChildNode(hidden);
		hidden = InputHidden.getInstance("processInstId", processInstId);
		root.addChildNode(hidden);
		return root.getNode();
	}
	/**
	 * 弹出框
	 */
	public void createUploadDialog() {
		/** 创建Modal Dialog 开始 */
		CssClass modelCss = new CssClass("modal fade col-lg-12 in");
		Div modalDiv = Div.getInstance("myModal", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		Prop styleProp = new Prop();
		styleProp.setPropKey("style");
		styleProp.setPropValue("width:40%");
		dialogDiv.addProp(styleProp);
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
		Div titleDiv = Div.getInstance(null, titleCss, "上传文档模板");
		headerDiv.addChildNode(titleDiv);
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12", "12");
		// 创建form
		CssClass formCss = new CssClass("form-search");
		com.ht.front.model.Form form = com.ht.front.model.Form.getInstance("importForm", formCss, null);
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		Prop formProps = new Prop();
		formProps.setPropKey("enctype");
		formProps.setPropValue("multipart/form-data");
		Prop formUrl = new Prop();
		formUrl.setPropKey("action");
		formUrl.setPropValue("../docTempleteAction/save");
		form.addProp(formProp);
		form.addProp(formProps);
		form.addProp(formUrl);
		columnGrid.addChildNode(form);
		// 创建文件导入input框
		CssClass fileCss = new CssClass("file-input file-input-style");
		File file = File.getInstance("Control", fileCss);
		Prop fileProp = new Prop();
		fileProp.setPropKey("name");
		fileProp.setPropValue("Control");
		file.addProp(fileProp);
		form.addChildNode(file);
		// 设置隐藏id
		InputHidden hiddenId = InputHidden.getInstance("formID", "");
		Prop hiddenProp = new Prop();
		hiddenProp.setPropKey("name");
		hiddenProp.setPropValue("formId");
		hiddenId.addProp(hiddenProp);
		form.addChildNode(hiddenId);
		headerDiv.addChildNode(util.createRowSpace());
		headerDiv.addChildNode(rowGrid);
		// 绑定按钮组
		List<Button> btnList = new ArrayList<Button>();
		// 创建确定按钮。
		closeProp = new Prop();
		closeProp.setPropKey("data-dismiss");
		closeProp.setPropValue("modal");
		/*Button taskSubmit = Button.getInstance("taskSubmit", new CssClass(
				"btn btn-success"), "确定");
		taskSubmit.addProp(closeProp);
		btnList.add(taskSubmit);*/
		
		CssClass submitCss = new CssClass("btn btn-success");
		Button submitBtn = Button.getInstance("templeteSubmit", submitCss, "确定");
		Prop submitProp = new Prop();
		submitProp.setPropKey("data-dismiss");
		submitProp.setPropValue("modal");
		submitBtn.addProp(submitProp);
		headerDiv.addChildNode(submitBtn);
		
		CssClass backCss = new CssClass("btn btn-default");
		Button backBtn = Button.getInstance("hiddenModal", backCss, "返回");
		backBtn.addProp(closeProp);
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("margin-left: 10px");
		backBtn.addProp(prop);
		headerDiv.addChildNode(backBtn);
		
		// 创建返回按钮。
		headerDiv.addChildNode(util.createRowSpace());
		headerDiv.addChildNode(ButtonGroup.getInstance(btnList));
		/** Modal Dialog 结束 */
		// 加入到root
		root.addChildNode(modalDiv);
	}
}
