package com.ht.front.pages.background.application;

import java.util.ArrayList;
import java.util.List;

import com.ht.exception.DBException;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.inter.background.application.ApplicationService;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;

/**
 * 应用资源界面初始化类
 * 
 * @author zhongquanyou
 *
 */
public class ApplicationPage {
	/**
	 * 初始化应用资源数据页面
	 * 
	 * @return 节点字符串
	 * */
	public String getListNode() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "应用资源管理");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting search");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		// 构建编辑div
		css = new CssClass("fa fa-edit");
		i = I.getInstance(css);
		css = new CssClass("btn btn-primary bk-margin-5 search");
		button = Button.getButtonWithIcon("edit", css, "编辑", i);
		column.addChildNode(button);
		// 构建删除div
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		// 构建导出按钮
		css = new CssClass("fa fa-sign-out");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5 search");
		button = Button.getButtonWithIcon("export", css, "导出", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"application");
		/** 创建Grid行  结束*/
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4 btn-settings");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editApplication");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		return root.getNode()+script.getNode();
	}

	/**
	 * 初始化编辑应用资源页面
	 * 
	 * @param List<Application> 应用资源对象的List集合
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public String getEditNode(ApplicationService service, BaseDataService baseDataService,
			List<BaseData> basedataList, String id) throws Exception {
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Application> appList = service.getApplication();
		try {
			if (id != null) {
				Application app = service.getApplication(id);
				List<Base> param = new ArrayList<Base>();
				FrontUtil util = FrontUtil.getInstance();
				InputGroup ipg = InputGroup.getSelectGroup("&#12288父节点", "appParentId",appList, "id", "appName", app.getAppParentId());
				param.add(ipg);
				// 创建行
				Base rowBg = util.createRow();
				// 创建列
				Base column = util.createColumn(rowBg, "12","12");
				CssClass css = new CssClass("row");
				util.createRow(column);
				css = new CssClass("col-md-12 col-xs-12 col-sm-12");
				Div div = Div.getInstance(null, css, null);
				// 构建创建div
				css = new CssClass("fa fa-plus");
				I i = I.getInstance(css);
				css = new CssClass("btn btn-success bk-margin-1 btn-setting");
				Button button = Button.getButtonWithIcon("add", css, "选择上级菜单", i);
				div.addChildNode(button);
				param.add(div);
				
				ipg = InputGroup.getInGroup("资源编码", "appCode",app.getAppCode(), "请输入资源编码 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("资源名称", "appName",app.getAppName(), "请输入资源名称 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("资源路径", "appUrl", app.getAppUrl(),"请输入资源路径 ");
				param.add(ipg);
				ipg = InputGroup.getSelectGroup("资源类型", "appType",basedataList, "id", "value",app.getAppType());
				param.add(ipg);
				ipg = InputGroup.getInGroup("资源内容", "appContent",app.getAppContent(), "请输入资源内容");
				param.add(ipg);
				ipg = InputGroup.getInGroup("资源图片", "appImg", app.getAppImg(),"请输入资源图片");
				param.add(ipg);
				/** 创建Modal Dialog 开始*/
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
				
				CssClass footerCss = new CssClass("modal-footer");
				Div footerDiv = Div.getInstance("select", footerCss, null);
				contentDiv.addChildNode(footerDiv);
				
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
				Div titleDiv = Div.getInstance(null, titleCss, "选择上级菜单");
				headerDiv.addChildNode(titleDiv);
				// 创建form表单
				CssClass formCss = new CssClass("form-search");
				Form form = Form.getInstance("select",formCss,null);
				Prop formProp = new Prop();
				formProp.setPropKey("method");
				formProp.setPropValue("post");
				Prop formProps = new Prop();
				formProp.setPropKey("enctype");
				formProp.setPropValue("multipart/form-data");
				form.addProp(formProp);
				form.addProp(formProps);
				headerDiv.addChildNode(form);
				// 构建确定按钮
				CssClass importCss = new CssClass("import-submit btn btn-primary");
				Button importBtn = Button.getInstance("importSubmit", importCss, "确定");
				Prop importProp = new Prop();
				importProp.setPropKey("data-dismiss");
				importProp.setPropValue("modal");
				importBtn.addProp(importProp);
				headerDiv.addChildNode(importBtn);
				/** Modal Dialog 结束*/
				div.addChildNode(modalDiv);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "应用资源编辑");
				InputHidden hidden = InputHidden.getInstance("appParentName", null);
				InputHidden hidden1 = InputHidden.getInstance("appId", id);
				editPage.addChildNode(hidden);
				editPage.addChildNode(hidden1);

			} else {
				List<Base> param = new ArrayList<Base>();
				FrontUtil util = FrontUtil.getInstance();
				InputGroup ipg = InputGroup.getSelectGroup("&#12288父节点", "appParentId",appList, "id", "appName", false);
				param.add(ipg);
				
				/** 创建按钮组行  开始*/
				// 创建行
				Base rowBg = util.createRow();
				// 创建列
				Base column = util.createColumn(rowBg, "12","12");
				// 创建按钮组
				// 构建行div
//				String businessName = "资料类别";
				CssClass css = new CssClass("row");
				util.createRow(column);
				css = new CssClass("col-md-12 col-xs-12 col-sm-12");
				Div div = Div.getInstance(null, css, null);
				// 构建创建div
				css = new CssClass("fa fa-plus");
				I i = I.getInstance(css);
				css = new CssClass("btn btn-success bk-margin-1 btn-setting");
				Button button = Button.getButtonWithIcon("add", css, "选择上级菜单", i);
				div.addChildNode(button);
				param.add(div);
				ipg = InputGroup.getInGroup("资源编码", "appCode", null,"请输入资源编码 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("资源名称", "appName", null, "请输入资源名称 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("资源路径", "appUrl", null, "请输入资源路径 ");
				param.add(ipg);
				ipg = InputGroup.getSelectGroup("资源类型", "appType",basedataList, "id", "value", false);
				param.add(ipg);
				
				ipg = InputGroup.getInGroup("资源内容", "appContent", null,"请输入资源内容");
				param.add(ipg);
				
				ipg = InputGroup.getInGroup("资源图片", "appImg", null,"请输入资源图片");
				param.add(ipg);
				
				editPage = edit.createEditPage(param);
				/** 创建Modal Dialog 开始*/
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
				CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
				Div titleDiv = Div.getInstance(null, titleCss, "选择上级菜单");
				headerDiv.addChildNode(titleDiv);
				// 创建form表单
				CssClass formCss = new CssClass("form-search");
				Form form = Form.getInstance("select",formCss,null);
				Prop formProp = new Prop();
				formProp.setPropKey("method");
				formProp.setPropValue("post");
				Prop formProps = new Prop();
				formProp.setPropKey("enctype");
				formProp.setPropValue("multipart/form-data");
				form.addProp(formProp);
				form.addProp(formProps);
				headerDiv.addChildNode(form);
				// 构建确定按钮
				CssClass importCss = new CssClass("import-submit btn btn-primary");
				Button importBtn = Button.getInstance("importSubmit", importCss, "确定");
				Prop importProp = new Prop();
				importProp.setPropKey("data-dismiss");
				importProp.setPropValue("modal");
				importBtn.addProp(importProp);
				headerDiv.addChildNode(importBtn);
				/** Modal Dialog 结束*/
				div.addChildNode(modalDiv);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "应用资源创建");
				InputHidden hidden = InputHidden.getInstance("appParentName", null);
				editPage.addChildNode(hidden);
			}
			return editPage.getNode();
		} catch (DBException e) {
			return e.getMessageCode();
		}

	}
}
