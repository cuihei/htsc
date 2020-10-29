package com.ht.front.pages.system.document.type;

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
import com.ht.front.template.EditPage;
import com.ht.front.template.ListPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.system.document.type.ModelType;
import com.ht.service.inter.system.document.type.ModelTypeService;

/**
* 模板类型前台页面初始化类
* */
public class ModelTypes {

	/**
	 * 初始化模板类型数据页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		ListPage list = new ListPage();
		//创建一个列表
		Base listPage = list.createListPage("模板", "modeltype");
		FrontUtil util = FrontUtil.getInstance();
		util.createHeaderBar(listPage, "模板类型");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editModelType");
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
		//返回节点字符串
		return listPage.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑模板类型信息页面
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	public String getEditNode(ModelTypeService service, String id){
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		Base editPage = null;
			try {
				if(id!=null){
					ModelType modeltype = service.getModelType(id);
					List<Base> param = new ArrayList<Base>();
					InputGroup tb = InputGroup.getInGroup("模板类型名称", "name",modeltype.getName(), "请输入模板类型名称");
					param.add(tb);
					editPage = edit.createEditPage(param);
					util.createHeaderBar(editPage, "模板类型编辑");
					InputHidden hidden = InputHidden.getInstance("modeltypeId", id);
					editPage.addChildNode(hidden);
			}else{
					List<Base> param = new ArrayList<Base>();
					InputGroup tb = InputGroup.getInGroup("模板类型名称", "name", null, "请输入模板类型名称");
					param.add(tb);
					editPage = edit.createEditPage(param);
					util.createHeaderBar(editPage, "模板类型创建");
			}
			//返回节点字符串
			return editPage.getNode();
			} catch (Exception e) {
				return e.getMessage();
			}
		}
	}