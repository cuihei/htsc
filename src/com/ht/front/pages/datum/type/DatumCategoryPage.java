package com.ht.front.pages.datum.type;

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
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.datum.type.DatumCategoryService;

/**
 * 资料类别前台页面初始化类
 * @author zyd
 *
 */
public class DatumCategoryPage {
	
	
	/**
	 * 
	 * 初始化资料类别页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListPage(){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "资料分类管理");
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
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"datumcategory");
		/** 创建Grid行  结束*/
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editDatumCategory");
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
	 * 初始化编辑资料类别信息页面
	 * @param datumcategoryList 
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	public String getEditNode(String flag, DatumCategoryService service,String id, List<DatumCategory> datumcategoryList) throws Exception {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 获取资料类别数据
		try {
			if(id!=null){
				DatumCategory datum = service.getDatumCategory(id);
				FrontUtil util = FrontUtil.getInstance();
				List<Base> param = new ArrayList<Base>();
				Base base = util.creatDefaultSelectGroup("上级资料类别", "parentId", datumcategoryList, "id", "categoryName", datum.getParentId());
				param.add(base);
				
				/** 创建按钮组行  开始*/
				// 创建行
				Base rowBg = util.createRow();
				// 创建列
				Base column = util.createColumn(rowBg, "12","12");
				// 创建按钮组
				// 构建行div
				String businessName = "资料类别";
				CssClass css = new CssClass("row");
				util.createRow(column);
				css = new CssClass("col-md-12 col-xs-12 col-sm-12");
				Div div = Div.getInstance(null, css, null);
				InputGroup tb = InputGroup.getInGroup("资料类别名称", "categoryName", datum.getCategoryName(), "请输入资料类别名称");
				param.add(tb);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "资料类别编辑");
				// 设置资料类别的隐藏域
				InputHidden hiddenId = InputHidden.getInstance("datumcategoryId", id);
				editPage.addChildNode(hiddenId);
			}else{
				List<Base> param = new ArrayList<Base>();
				FrontUtil util = FrontUtil.getInstance();
				Base base = util.creatDefaultSelectGroup("上级资料类别", "parentId", datumcategoryList, "id", "categoryName", false);
				param.add(base);
				
				// 创建一个容器
				Base root = util.createRoot();
				
				/** 创建按钮组行  开始*/
				// 创建行
				Base rowBg = util.createRow();
				// 创建列
				Base column = util.createColumn(rowBg, "12","12");
				// 创建按钮组
				// 构建行div
				String businessName = "资料类别";
				CssClass css = new CssClass("row");
				util.createRow(column);
				css = new CssClass("col-md-12 col-xs-12 col-sm-12");
				Div div = Div.getInstance(null, css, null);
				// 构建创建div
				css = new CssClass("fa fa-plus");
				I i = I.getInstance(css);
				css = new CssClass("btn btn-success bk-margin-1 btn-setting select");
				Button button = Button.getButtonWithIcon("add", css, "选择"+businessName, i);
				div.addChildNode(button);
				param.add(div);
				InputGroup tb = InputGroup.getInGroup("资料类别名称", "categoryName", null, "请输入资料类别名称");
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
				// 创建footer
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
				Div titleDiv = Div.getInstance(null, titleCss, "选择资料类别");
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
				
				// 设置资料类别的隐藏域
				InputHidden hidden = InputHidden.getInstance("dCataParentName", id);
				
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "资料类别创建");
				editPage.addChildNode(hidden);
				root.addChildNode(div);
			}
			//返回节点字符串
			return editPage.getNode();
		} catch (DBException e) {
			return e.getMessageCode();
		}
	}
}
