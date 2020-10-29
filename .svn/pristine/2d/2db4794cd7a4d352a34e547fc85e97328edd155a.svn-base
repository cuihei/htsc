package com.ht.front.pages.background.dicdata.scalecontrol;

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
import com.ht.persistence.model.background.dicdata.scalecontrol.ScaleControl;

/**
 * 图书资料页面初始化类
 * @author zyd
 *
 */
public class ScaleControlPage {
	
	/**
	 * 
	 * 初始化资料维护页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListNode(){
		//创建列表页面类
		ListPage list = new ListPage();
		//创建资源列表
		Base listPage = list.createListPage("", "scaleControl");
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createHeaderBar(root, "标准比例尺管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editScaleControl");
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
	 * @param id
	 * @return
	 */
	public String getEditNode(ScaleControl scaleControl) {
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		Base editPage = null;
		if(scaleControl != null){	
			List<Base> param = new ArrayList<Base>();
			InputGroup ipg = InputGroup.getInGroup("&#12288原比例尺(1:)","oldScale", scaleControl.getOldScale(), "请输入原比例尺");
			param.add(ipg);
			ipg = InputGroup.getInGroup("标准比例尺(1:)","standardScale", scaleControl.getStandardScale(), "请输入标准比例尺 ");
			param.add(ipg);
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "标准比例尺编辑");
			InputHidden hidden = InputHidden.getInstance("id", scaleControl.getId());
			editPage.addChildNode(hidden);
		}else{
			List<Base> param = new ArrayList<Base>();
			InputGroup ipg = InputGroup.getInGroup("&#12288原比例尺(1:)","oldScale", null , "请输入原比例尺 ");
			param.add(ipg);
			ipg = InputGroup.getInGroup("标准比例尺(1:)","standardScale", null , "请输入标准比例尺 ");
			param.add(ipg);
			editPage = edit.createEditPage(param);
			util.createHeaderBar(editPage, "标准比例尺管理创建");
		}
	   return editPage.getNode();
	}
}
