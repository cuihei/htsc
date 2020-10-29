package com.ht.front.pages.complication.form;

import java.util.ArrayList;
import java.util.List;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Select;
import com.ht.front.model.Span;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.complication.form.Form;
import com.ht.service.inter.complication.form.FormService;


/**
 * 表单前台页面初始化类
 * */
public class FormPage {
	/**
	 * 页面实例
	 */
	private static FormPage page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static FormPage getInstance(){
		if (page == null) {
			page = new FormPage();
		}
		return page;
	}
	/**
	 * 初始化表单页面
	 * @return 节点字符串
	 * */
	public String getListPage() {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "表单管理");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5 search");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		// 构建删除div
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		
		// 构建全部生成按鈕
		css = new CssClass("fa fa-check");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5 search");
		button = Button.getButtonWithIcon("addall", css, "批量生成", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"form");
		/** 创建Grid行  结束*/
		//添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-5");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editForm");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editPage(this)");
		tempelate.addProp(prop);
		//添加查看按钮
		CssClass seeCss = new CssClass("fa fa-eye");
		I seeI = I.getInstance(seeCss);
		seeCss = new CssClass("btn btn-warning bk-margin-5");
		Button see = Button.getButtonWithIcon(null, seeCss, null, seeI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("seeForm");
		see.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("查看表单属性");
		see.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("seePage(this)");
		see.addProp(prop);
		//添加生成按钮
		CssClass goCss = new CssClass("fa fa-check-square");
		I goI = I.getInstance(goCss);
		goCss = new CssClass("btn btn-info bk-margin-5");
		Button go = Button.getButtonWithIcon(null, goCss, null, goI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("goForm");
		go.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("生成");
		go.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("goPage(this)");
		go.addProp(prop);
		//添加导出按钮
		/*CssClass goPoi = new CssClass("fa fa-sign-out");
		I goP = I.getInstance(goPoi);
		goPoi = new CssClass("btn btn-info bk-margin-5");
		Button goPoiBtn = Button.getButtonWithIcon(null, goPoi, null, goP);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("goPoi");
		goPoiBtn.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("导出");
		goPoiBtn.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("goPoiPage(this)");
		goPoiBtn.addProp(prop);*/
		//绑定属性
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		script.addChildNode(see);
		script.addChildNode(go);
//		script.addChildNode(goPoiBtn);
		return root.getNode()+script.getNode();
	}
	
	/**
	 * 初始化新增表单页面
	 * @return 节点字符串
	 * */
	public String getEditPage(FormService service,String id,List<BaseData> baseDataList) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 获取数据
		try {
			if(id!=null){
				Form form = service.getForm(id);
				FrontUtil util = FrontUtil.getInstance();
				List<Base> param = new ArrayList<Base>();
				
				Base base = util.creatDefaultSelectGroup("基础数据", "baseDataId", baseDataList, "id", "value", form.getBaseDataId());
				param.add(base);

				InputGroup tb = InputGroup.getInGroup("表单名称", "name", form.getName(), "请输入表单名称");
				param.add(tb);
				tb = InputGroup.getInGroup("表单地址", "url", form.getUrl(), "请输入表单地址");
				param.add(tb);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "表单编辑");
				InputHidden hidden = InputHidden.getInstance("formId", id);
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				FrontUtil util = FrontUtil.getInstance();
				String parentId = "基础数据：";
				Span parentIdSpan = Span.getDefault(parentId);
				Select parentIdSelect = Select.getDefaultWithOption("baseDataId", baseDataList, "id", "value");
				param.add(InputGroup.getInstance(parentIdSpan, parentIdSelect));
				
				InputGroup tb = InputGroup.getInGroup("表单名称", "name", null, "请输入表单名称");
				param.add(tb);
				tb = InputGroup.getInGroup("表单地址", "url", null, "请输入表单地址");
				param.add(tb);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "表单创建");
			}
			//返回节点字符串
			return editPage.getNode();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
