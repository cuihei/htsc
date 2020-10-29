package com.ht.front.pages.background.organization.organization;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.Form;
import com.ht.front.model.H6;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Span;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.service.inter.background.organization.organization.OrganizationService;

/**
 * 组织前台页面初始化类
 * @author liukai
 * */
public class OrganizationPage {
	
	/**
	 * 页面实例
	 */
	private static OrganizationPage page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static OrganizationPage getInstance(){
		if (page == null) {
			page = new OrganizationPage();
		}
		return page;
	}
	
	/**
	 * 初始化组织机构树数据页面
	 * @return 返回组织机构树页面
	 * */
	public String getListPage() throws Exception{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "组织机构管理");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 添加按钮
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5 search");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		// 构建删除按钮
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		// 构建导入按钮
		css = new CssClass("fa fa-hand-o-up");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5 search");
		button = Button.getButtonWithIcon("alloc_user", css, "分配人员", i);
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
		Base rowGrid = util.createGrid(root,"org");
		/** 创建Grid行  结束*/
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4 btn-settings");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editOrg");
		// 绑定属性
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editPage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		return root.getNode()+script.getNode();
	}
	
	/**
	 * 初始化新增和编辑组织机构信息页面
	 * @param organizationService 组织机构信息接口
	 * @param id 组织机构id
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	public String getEditNode(OrganizationService organizationService,String id) throws Exception {
		EditPage edit = new EditPage();
		Base editPage = null;
		List<Base> param = new ArrayList<Base>();
		//获取机构列表
		List<Organization> list = organizationService.getOrganization();
		if(id!=null){
			Organization o =organizationService.getOrganization(id);
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("&#12288父机构", "parentId", list, "id", "orgName",o.getParentId());
			param.add(selectgroup);
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
			css = new CssClass("btn btn-success bk-margin-1 btn-setting select");
			Button button = Button.getButtonWithIcon("add", css, "选择父机构", i);
			div.addChildNode(button);
			param.add(div);
			/*InputGroup tb = InputGroup.getInGroup("机构编号", "orgNo", o.getOrgNo(), "请输入组织机构编号");
			param.add(tb);*/
			InputGroup tb = InputGroup.getInGroup("机构名称", "orgName", o.getOrgName(), "请输入组织机构名称");
			param.add(tb);
			
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
			Div titleDiv = Div.getInstance(null, titleCss, "选择父机构");
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
			util.createHeaderBar(editPage, "组织机构编辑");
			InputHidden hidden = InputHidden.getInstance("orgParentName", null);
			InputHidden hidden1 = InputHidden.getInstance("orgId", id);
			editPage.addChildNode(hidden);
			editPage.addChildNode(hidden1);
		}else{
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("&#12288父机构", "parentId", list, "id", "orgName",false);
			param.add(selectgroup);
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
			css = new CssClass("btn btn-success bk-margin-1 btn-setting select");
			Button button = Button.getButtonWithIcon("add", css, "选择父机构", i);
			div.addChildNode(button);
			param.add(div);
			/*InputGroup tb = InputGroup.getInGroup("机构编号", "orgNo", null, "请输入组织机构编号");
			param.add(tb);*/
			InputGroup tb = InputGroup.getInGroup("机构名称", "orgName", null, "请输入组织机构名称");
			param.add(tb);
			
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
			Div titleDiv = Div.getInstance(null, titleCss, "选择父机构");
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
			util.createHeaderBar(editPage, "组织机构创建");
			InputHidden hidden = InputHidden.getInstance("orgParentName", null);
			editPage.addChildNode(hidden);
		}
		//返回节点字符串
		return editPage.getNode();
	}
	
	/**
	 * 初始化分配组织人员页面
	 * @param id 需要进行人员分配的组织机构id
	 * @return 节点字符串
	 * */
	public String getAllocUserPage(String id,String orgName) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "组织机构人员管理");
		util.createRowSpace(root);
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base column = util.createColumn(rowBg, "12","12");
		// 创建标题panel
		/*CssClass css = new CssClass("panel panel-default");
		Base panel = Div.getInstance(null, css, null);
		column.addChildNode(panel);*/
		// 创建标题panelHeader
		//CssClass css = new CssClass("panel-heading panel-heading-style");
		Base header = Div.getInstance(null, null, null);
		root.addChildNode(header);
		// 创建标题H6
		Base h6 = H6.getInstance(null);
		CssClass css = new CssClass("h6-span");
		Base span = Span.getInstance(css, orgName);
		h6.addChildNode(span);
		header.addChildNode(h6);
		
		// 构建创建div
		css = new CssClass("fa fa-check-square");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting");
		Button button = Button.getButtonWithIcon("submit", css, "提交", i);
		column.addChildNode(button);
		css = new CssClass("btn btn-default bk-margin-5");
		button = Button.getButtonWithIcon("back", css, "返回",i);
		column.addChildNode(button);
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"orgUser");
		
		InputHidden hidden = InputHidden.getInstance("orgId", id);
		root.addChildNode(hidden);
		//返回节点字符串
		return root.getNode();
	}
}