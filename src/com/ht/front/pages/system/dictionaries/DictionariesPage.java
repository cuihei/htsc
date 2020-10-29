package com.ht.front.pages.system.dictionaries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ht.exception.DBException;
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
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;

public class DictionariesPage {
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public static String getListNode() {
		//创建列表页面类
		ListPage list = new ListPage();
		//创建资源列表
		Base listPage = list.createListPage("", "dictionaries");
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createHeaderBar(root, "字典管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editDictionaries");
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
		return root.getNode()+listPage.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑页面
	 * @param List<Type> 应用资源对象的List集合
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public static String getEditNode(List<Type> list,BaseDataService service,String id) throws Exception{
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		Base editPage = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(id != null){	
				BaseData baseData = service.getBaseData(id);
				List<Base> param = new ArrayList<Base>();
				InputGroup ipg = InputGroup.getInGroup("基础数据编码","code", baseData.getCode(), "请输入基础数据编码 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("基础数据名称","value", baseData.getValue(), "基础数据名称 ");
				param.add(ipg);
				ipg = InputGroup.getSelectGroup("类&#12288&#12288&#12288&#12288别","type", list, "id", "name",baseData.getTypeId());
				param.add(ipg);
//				ipg = InputGroup.getInGroup("创建者","creator", baseData.getCreator(), "请输入创建者");
//				param.add(ipg);
//				ipg = InputGroup.getInGroup("更新者","modifier", baseData.getModifier(), "请输入更新者");
//				param.add(ipg);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "字典编辑");
				InputHidden hidden = InputHidden.getInstance("id", id);
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				InputGroup ipg = InputGroup.getInGroup("基础数据编码","code", null , "请输入基础数据编码 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("基础数据名称","value", null , "基础数据名称 ");
				param.add(ipg);
				ipg = InputGroup.getSelectGroup("类&#12288&#12288&#12288&#12288别","type", list, "id", "name",false);
				param.add(ipg);
//				ipg = InputGroup.getInGroup("创建者","creator", null , "请输入创建者");
//				param.add(ipg);
//				ipg = InputGroup.getDatePicker("creationDate");
//				param.add(ipg);
//				ipg = InputGroup.getInGroup("更新者","modifier", null , "请输入更新者");
//				param.add(ipg);
//				ipg = InputGroup.getDatePicker("modifyDate");
//				param.add(ipg);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "字典创建");
			}
		   return editPage.getNode();
		}catch (DBException e) {
			return e.getMessageCode();
		}
		
	}
	
	
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public String getBooksNode(String flag) {
		//创建列表页面类
		ListPage list = new ListPage();
		//创建资源列表
		Base listPage = list.createListPage("", "booksArea");
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		if(flag != null){
			if(flag.equals("1")){
				util.createHeaderBar(root, "海图资料所属海区管理");
			}else if(flag.equals("2")){
				util.createHeaderBar(root, "外业汇交资料所属海区管理");
			}
		}
		
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editDictionaries");
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
		InputHidden hidden = InputHidden.getInstance("flag", flag);
		root.addChildNode(hidden);
		return root.getNode()+listPage.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑页面
	 * @param List<Type> 应用资源对象的List集合
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public static String getBooksEditNode(BaseDataService service,String id,String flag) throws Exception{
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		Base editPage = null;
		try {
			if(id != null){	
				BaseData baseData = service.getBaseData(id);
				List<Base> param = new ArrayList<Base>();
				InputGroup ipg = InputGroup.getInGroup("海区编码","code", baseData.getCode(), "请输入海区编码 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("海区名称","value", baseData.getValue(), "请输入海区名称 ");
				param.add(ipg);
				editPage = edit.createEditPage(param);
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "海图资料所属海区编辑");
				}else{
					util.createHeaderBar(editPage, "外业汇交资料所属海区编辑");
				}
				InputHidden hidden = InputHidden.getInstance("id", id);
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				InputGroup ipg = InputGroup.getInGroup("海区编码","code", null , "请输入海区编码 ");
				param.add(ipg);
				ipg = InputGroup.getInGroup("海区名称","value", null , "请输入海区名称 ");
				param.add(ipg);
				editPage = edit.createEditPage(param);
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "海图资料所属海区创建");
				}else{
					util.createHeaderBar(editPage, "外业汇交资料所属海区创建");
				}
			}
			InputHidden hidden = InputHidden.getInstance("flag", flag);
			editPage.addChildNode(hidden);
		   return editPage.getNode();
		}catch (DBException e) {
			return e.getMessageCode();
		}
		
	}
	
}
