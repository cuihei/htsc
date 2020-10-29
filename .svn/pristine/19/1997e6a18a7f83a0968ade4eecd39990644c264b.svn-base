package com.ht.front.pages.system.notice;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.I;
import com.ht.front.model.Script;
import com.ht.front.template.ListPage;
import com.ht.front.util.FrontUtil;

public class UserNotices {
	
	/**
	 * 初始化人员通知关系页面
	 * @return 节点字符串
	 * */
	public String getListNode() {
		ListPage list = new ListPage();
		//创建一个列表
		Base listPage = list.createListPage("通知", "usernotice");
		FrontUtil util = FrontUtil.getInstance();
		util.createHeaderBar(listPage, "用户通知管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editUserNotice");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		//返回节点字符串
		return listPage.getNode()+script.getNode();
	}
}
