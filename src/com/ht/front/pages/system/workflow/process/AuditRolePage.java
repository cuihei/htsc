package com.ht.front.pages.system.workflow.process;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.H6;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Span;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.workflow.service.IWorkflowService;

/**
 * 审批角色配置界面初始化类
 * @author yx
 */
public class AuditRolePage
{

	/**
	 * 初始化应用资源数据页面
	 * @return 节点字符串
	 */
	public String getListNode()
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "审批角色管理");
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting maxwidth");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		// 构建编辑div
		css = new CssClass("fa fa-hand-o-up");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5 search");
		button = Button.getButtonWithIcon("edit", css, "分配用户", i);
		column.addChildNode(button);
		// 构建删除div
		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-5 search");
		button = Button.getButtonWithIcon("remove", css, "删除", i);
		column.addChildNode(button);
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(root, "auditrole");
		/** 创建Grid行 结束 */
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editauditrole");
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
		// 委托组配置
		Script scriptDelegate = Script.getInstance("delegateTemplate");
		css = new CssClass("fa fa-hand-o-right");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning");
		tempelate = Button.getButtonWithIcon(null, css, null, i);
		prop = new Prop("onclick", "auditrole.allotDelegateUser(this)");
		tempelate.addProp(prop);
		scriptDelegate.addChildNode(tempelate);
		return root.getNode() + script.getNode() + scriptDelegate.getNode();
	}

	/**
	 * 初始化新增/编辑人员信息页面
	 * @return 节点字符串
	 */
	public String getEditPage(IWorkflowService service, String id)
	{
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		Base editPage = null;
		// 获取用户数据
		try
		{
			if (id != null)
			{
				List<Group> list = service.getGroupById(id);
				List<Base> param = new ArrayList<Base>();
				String name = "", ids = "";
				if (list.size() > 0)
				{
					ids = list.get(0).getId();
					name = list.get(0).getName();
				}
				InputGroup tb = InputGroup.getInGroup("组名称", "name", name, "请输入组名称");
				param.add(tb);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "审批角色编辑");
				InputHidden hidden = InputHidden.getInstance("id", ids);
				editPage.addChildNode(hidden);
			}
			else
			{
				List<Base> param = new ArrayList<Base>();
				InputGroup tb = InputGroup.getInGroup("组名称", "name", null, "请输入组名称");
				param.add(tb);
				editPage = edit.createEditPage(param);
				InputHidden hidden = InputHidden.getInstance("id", "");
				editPage.addChildNode(hidden);
				util.createHeaderBar(editPage, "审批角色创建");
			}
			// 返回节点字符串
			return editPage.getNode();
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
	}

	/**
	 * 初始化分配用户数据页面
	 * @return 节点字符串
	 */
	public String getUserListNode(String id, String groupName)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "审批角色用户管理");
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		Base header = Div.getInstance(null, null, null);
		root.addChildNode(header);
		// 创建标题H6
		Base h6 = H6.getInstance(null);
		CssClass css = new CssClass("h6-span");
		Base span = Span.getInstance(css, groupName);
		h6.addChildNode(span);
		header.addChildNode(h6);
		// 创建按钮组
		// 构建创建div
		css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5 btn-setting");
		Button button = Button.getButtonWithIcon("add", css, "保存", i);
		column.addChildNode(button);
		
		css = new CssClass("fa fa-reply");
		i = I.getInstance(css);
		css = new CssClass("btn btn-default btn-setting");
		button = Button.getButtonWithIcon("back", css, "返回", i);
		column.addChildNode(button);
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(root, "auditrole");
		/** 创建Grid行 结束 */
		InputHidden hidden = InputHidden.getInstance("GroupId", id);
		// 将行加入到容器
		root.addChildNode(hidden);
		// 添加编辑按钮
		CssClass editCss = new CssClass("fa fa-edit");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editauditrole");
		// 绑定属性
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		return root.getNode() + script.getNode();
	}
	
	/**
	 * 初始化分配委托用户数据页面
	 * @return 节点字符串
	 */
	public String getDelegateUserPage(String groupId,String groupName)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "选择委托人-"+groupName);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		Base col = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		util.createSubmitButton(col);
		util.createCancelButtonBk5(col);
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		util.createGrid(root, "delegateUser");
		/** 创建Grid行 结束 */
		InputHidden hidden = InputHidden.getInstance("groupId", groupId);
		// 将行加入到容器
		root.addChildNode(hidden);
		return root.getNode();
	}
}