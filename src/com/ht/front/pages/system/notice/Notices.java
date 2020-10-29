package com.ht.front.pages.system.notice;

import java.util.ArrayList;
import java.util.List;

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
import com.ht.front.model.Select;
import com.ht.front.model.Span;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.persistence.model.system.notice.Notice;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.system.notice.NoticeService;

/**
* 通知管理页面初始化类
* */
public class Notices {
	
	/**
	 * 页面实例
	 */
	private static Notices page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static Notices getInstance(){
		if (page == null) {
			page = new Notices();
		}
		return page;
	}
	
	/**
	 * 初始化通知数据页面
	 * @return 节点字符串
	 * */
	public String getListNode(UserService service,
			List<User> userList, OrganizationService services, List<Organization> orgList,String flag ){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "通知管理");
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
		// 构建删除按钮
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5");
		button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		// 构建发布按钮
		css = new CssClass("fa  fa-bullhorn");
		i = I.getInstance(css);
		css = new CssClass("btn btn-primary bk-margin-5 search");
		button = Button.getButtonWithIcon("publish", css, "发布", i);
		column.addChildNode(button);
		// 构建导出按钮
		css = new CssClass("fa fa-sign-out");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		button = Button.getButtonWithIcon("export", css, "导出", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		// 构件查看通知按钮
		css = new CssClass("fa fa-eye");
		i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5");
		button = Button.getButtonWithIcon("look", css, "查看", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"notice");
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
		// 创建标题div
		CssClass titleCss = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv = Div.getInstance(null, titleCss, "发布通知");
		headerDiv.addChildNode(titleDiv);
		// 创建form表单
		CssClass formCss = new CssClass("form-search");
		Form form = Form.getInstance("publishForm",formCss,null);
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		Prop formProps = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		form.addProp(formProp);
		form.addProp(formProps);
		headerDiv.addChildNode(form);
		// 创建发布通知modal
		FrontUtil Futil = FrontUtil.getInstance();
		Base base = Futil.creatDefaultSelectGroup("选择人员", "userId", userList, "id", "userName", false);		
		headerDiv.addChildNode(base);
		// 插入空行
		headerDiv.addChildNode(rowSpace);
		Base no = Futil.creatDefaultSelectGroup("选择部门", "orgId", orgList, "id", "orgName", false);
		headerDiv.addChildNode(no);
		// 构建发布按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("publishSubmit", importCss, "发布");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		Prop importProps = new Prop();
		importProps.setPropKey("aria-hidden");
		importProps.setPropValue("true");
		importBtn.addProp(importProp);
		importBtn.addProp(importProps);
		headerDiv.addChildNode(rowSpace);
		headerDiv.addChildNode(importBtn);
		// 将行加入到容器
		root.addChildNode(modalDiv);
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
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
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editPage(this)");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		// 设置通知Id 隐藏域
		InputHidden noticeId = InputHidden.getInstance("noticeId", null);
		root.addChildNode(noticeId);
		InputHidden flagId = InputHidden.getInstance("flag", flag);
		root.addChildNode(flagId);
		return root.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑通知信息页面
	 * @param baseList 
	 * @return 节点字符串
	 * */
	public String getEditNode(NoticeService service, String id, List<BaseData> baseList) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		try{
			if(id!=null){
				Notice notice = service.getNotice(id);
				List<Base> param = new ArrayList<Base>();
				
				String fileType = "通知类型：";
				Span span = Span.getDefault(fileType);
				Select select = Select.getDefaultWithOption("NoticeType", baseList, "id", "value", notice.getNotice_Type());
				param.add(InputGroup.getInstance(span, select));
				
				InputGroup tb = InputGroup.getInGroup("通知标题", "title",notice.getTitle(), "请输入通知标题");
				param.add(tb);
				tb = InputGroup.getInGroup("通知描述", "description", notice.getDescription(), "请输入通知描述");
				param.add(tb);
				
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "通知编辑");
				InputHidden hidden = InputHidden.getInstance("noticeId", id);
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				
				String fileType = "通知类型：";
				Span span = Span.getDefault(fileType);
				Select select = Select.getDefaultWithOption("NoticeType", baseList, "id", "value");
				param.add(InputGroup.getInstance(span, select));
				
				InputGroup tb = InputGroup.getInGroup("通知标题", "title", null, "请输入通知标题");
				param.add(tb);
				tb = InputGroup.getInGroup("通知描述", "description", null, "请输入通知描述");
				param.add(tb);
				
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "通知创建");
			}
			//返回节点字符串
			return editPage.getNode();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}