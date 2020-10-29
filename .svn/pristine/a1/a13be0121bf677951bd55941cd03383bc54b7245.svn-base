package com.ht.front.pages.system.dictionariesType;

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
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.service.inter.background.dicdata.type.TypeService;

public class DictionariesTypePage {
	/**
	 * 初始化页面
	 * @return 节点字符串
	 * */
	public static String getListNode() {
		//创建列表页面类
		ListPage list = new ListPage();
		//创建资源列表
		Base listPage = list.createListPage("", "dictionariesType");
		FrontUtil util = FrontUtil.getInstance();
		Base root = util.createRoot();
		util.createHeaderBar(root, "字典类型管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editDictionariesType");
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
	 * @throws Exception 
	 * @retrun 节点字符串
	 */
	public static String getEditNode(TypeService service,String id) throws Exception{
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		Base editPage = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(id != null){	
				Type type = service.getType(id);
				List<Base> param = new ArrayList<Base>();
				InputGroup ipg = InputGroup.getInGroup("类别名称","name", type.getName(), "请输入类别名称 ");
				param.add(ipg);
//				ipg = InputGroup.getInGroup("创建者","creator", type.getCreator(), "请输入创建者");
//				param.add(ipg);
				/*ipg = InputGroup.getInGroup("更新者","modifier", type.getModifier(), "请输入更新者");
				param.add(ipg);*/
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "字典类型编辑");
				InputHidden hidden = InputHidden.getInstance("id", id);
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				InputGroup ipg = InputGroup.getInGroup("类别名称","name", null, "请输入类别名称 ");
				param.add(ipg);
				/*ipg = InputGroup.getInGroup("创建者","creator", null, "请输入创建者");
				param.add(ipg);*/
//				ipg = InputGroup.getInGroup("更新者","modifier", null, "请输入更新者");
//				param.add(ipg);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "字典类型创建");
			}
		   return editPage.getNode();
		}catch (DBException e) {
			return e.getMessageCode();
		}
		
	}
}
