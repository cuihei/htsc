package com.ht.front.pages.datum.productupdsorcedata;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.datum.productupdsourcedata.ProductUpdSourceData;

/**
* 通知管理页面初始化类
* */
public class ProductUpdSourceDatas {
	
	/**
	 * 页面实例
	 */
	private static ProductUpdSourceDatas page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static ProductUpdSourceDatas getInstance(){
		if (page == null) {
			page = new ProductUpdSourceDatas();
		}
		return page;
	}
	
	/**
	 * 初始化通知数据页面
	 * @param status 
	 * @param status 
	 * @return 节点字符串
	 * */
	public String getListNode(String status,String userName){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "产品修改源数据");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 添加按钮
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"productupdsourcedata");
		/** 创建Grid行  结束*/
		// 创建Modal Dialog
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
		// 将行加入到容器
		root.addChildNode(modalDiv);
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-check-square");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editNotice");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("确定");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("updataPusdStatus(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		InputHidden hiddenRoleId = InputHidden.getInstance("status",status);
		root.addChildNode(hiddenRoleId);
		InputHidden hideUserName = InputHidden.getInstance("userName",userName);
		root.addChildNode(hideUserName);
		return root.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑通知信息页面
	 * @param pusdList 
	 * @return 节点字符串
	 * */
	public String getEditNode(List<ProductUpdSourceData> pusdList) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		try{
			List<Base> param = new ArrayList<Base>();
			
			InputGroup tb = InputGroup.getSelectGroup("图号", "draw", pusdList, "draw", "draw",false);
			
			Prop prop = new Prop();
			prop.setPropKey("onchange");
			prop.setPropValue("mapNoChange();");
			tb.addProp(prop);
			param.add(tb);
			tb = InputGroup.getInGroup(true, "图名", "figure_caption", null, "请输入图名");
			param.add(tb);
			tb = InputGroup.getInGroup(true,"编绘员", "compiler", null, "请输入编绘员");
			param.add(tb);
			tb = InputGroup.getInGroup(true,"质检员", "quality_inspector", null, "请输入质检员");
			param.add(tb);
			tb = InputGroup.getInGroup(true,"审定员", "authorized_member", null, "请输入审定员");
			param.add(tb);
			tb = InputGroup.getTextAreaGroup("源数据问题", "sourcedata_problem", "3", null);
			param.add(tb);
			
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "产品修改源数据问题创建");
			
			// 存储角色
			String draws = ""; 
			InputHidden hiddenRoleId = InputHidden.getInstance("draws",draws);
			editPage.addChildNode(hiddenRoleId);
			//返回节点字符串
			return editPage.getNode();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}