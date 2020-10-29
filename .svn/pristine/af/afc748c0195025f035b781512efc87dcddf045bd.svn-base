package com.ht.front.pages.background.organization.employee;

import java.util.ArrayList;
import java.util.List;

import com.ht.common.util.Utilmd5;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.Img;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Templete;
import com.ht.front.template.EditPage;
import com.ht.front.template.ListPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.authority.role.Role;
import com.ht.persistence.model.background.authority.role.RoleUsers;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.organization.Organization;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;
import com.ht.service.inter.background.authority.role.RoleService;
import com.ht.service.inter.background.authority.role.RoleUsersService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.background.organization.organization.OrganizationService;
import com.ht.service.inter.background.organization.organization.OrganizationUsersRelationService;


/**
 * 员工前台页面初始化类
 * */
public class EmployeePage {
	FrontUtil util = FrontUtil.getInstance();
	/**
	 * 初始化人员列表数据页面
	 * @return 节点字符串
	 * */
	public String getListPage() {
		ListPage list = new ListPage();
		//创建一个列表
		Base listPage = list.createListPage("", "user");
		Base root = util.createRoot();
		util.createHeaderBar(root, "用户管理");
		CssClass qzcss = new CssClass("fa fa-eye");
		I qzi = I.getInstance(qzcss);
		qzcss = new CssClass("btn btn-warning bk-margin-4");
		Button qztempelate = Button.getButtonWithIcon(null, qzcss, null, qzi);
		Prop qzprop = new Prop();
		qzprop.setPropKey("name");
		qzprop.setPropValue("editImg");
		qztempelate.addProp(qzprop);
		qzprop = new Prop();
		qzprop.setPropKey("onclick");
		qzprop.setPropValue("signaturePage(this)");
		qztempelate.addProp(qzprop);
		qzprop = new Prop();
		qzprop.setPropKey("title");
		qzprop.setPropValue("查看签章");
		qztempelate.addProp(qzprop);
		Script qzscript = Script.getInstance("qzTemplate");
		qzscript.addChildNode(qztempelate);
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editUser");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editPage(this)");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		//返回节点字符串
		return root.getNode()+listPage.getNode()+script.getNode()+qzscript.getNode();
	}
	
	/**
	 * 初始化人员列表数据页面
	 * @return 节点字符串
	 * */
	public String getFlowListPage() {
		ListPage list = new ListPage();
		//创建一个列表
		Base listPage = list.createSubmitListPage("","user");
		FrontUtil util = FrontUtil.getInstance();
		util.createHeaderBar(listPage, "用户管理");
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editUser");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		//返回节点字符串
		return listPage.getNode()+script.getNode();
	}
	
	/**
	 * 初始化新增/编辑人员信息页面
	 * @param UserService 人员信息接口
	 * @param id 人员id
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	public String getEditPage(UserService service, String id, OrganizationService orgService,
			List<Organization> orgList, RoleService roleService, List<Role> roleList,RoleUsersService ruService,
			OrganizationUsersRelationService ourService) throws Exception {
		User user = new User();
		OrganizationUsersRelation uor = new OrganizationUsersRelation();
		List<RoleUsers> ru = new ArrayList<RoleUsers>();
		Role role = new Role();
		Organization org = new Organization();
		String password = "";
		
		// 存储角色
		String roles = ""; 
		if(id!=null){
			uor = ourService.getUsersByUserId(id);
			ru = ruService.getRoleUsersByUserId(id);
			org = orgService.getOrganization(uor.getOrgId());
			for(int i=0;i<ru.size();i++){
				role = roleService.getRole(ru.get(i).getRoleId());
				roles += role.getId()+",";
			}
			user = service.getUser(id);
			// 将密码解密显示
			password = Utilmd5.New().convertMD5(user.getPassword());
		}
		EditPage edit = new EditPage();
		List<Base> param = new ArrayList<Base>();
		CssClass formCss = new CssClass("form-search");
		//创建form
		Form form = Form.getInstance("importForm",formCss,null);
		if(id!=null){
			FrontUtil util = FrontUtil.getInstance();
			util.createHeaderBar(form, "用户编辑");
		}else{
			FrontUtil util = FrontUtil.getInstance();
			util.createHeaderBar(form, "用户创建");
		}
		
		Prop formprop = new Prop();
		formprop.setPropKey("method");
		formprop.setPropValue("post");	
		
		Prop formProp = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		
		form.addProp(formprop);
		form.addProp(formProp);
		InputGroup tb = InputGroup.getInGroup("登陆账号", "userNo", user.getUserNo(), "请输入登陆账号");
		param.add(tb);
		tb = InputGroup.getInGroup("用户名称", "userName", user.getUserName(), "请输入用户名称");
		param.add(tb);
		tb = InputGroup.getPassWordInGroup("用户密码", "password", password, "请输入用户密码");
		param.add(tb);
		tb = InputGroup.getPassWordInGroup("确认密码", "newpassword", password, "请确认用户密码");
		param.add(tb);
		
		// 所属组织机构
		if (org.getId() != null) {
			if(org.getId().equals("10191641226710006")){
				tb = InputGroup.getSelectGroup("组织机构", "orgId", orgList, "id", "orgName",false);
			}
			else{
				tb = InputGroup.getSelectGroup("组织机构", "orgId", orgList, "id", "orgName",org.getId(),false);
			}
		}
		else{
			tb = InputGroup.getSelectGroup("组织机构", "orgId", orgList, "id", "orgName",false);
		}
		param.add(tb);
		// 所属角色
		tb = InputGroup.getSelectGroup("所属角色", "roleId", roleList, "id", "roleName",false);
		param.add(tb);
		// 上传签章图片
		InputGroup span = InputGroup.getSpan("上传签章");
		form.addChildNode(span);
		form.addChildNode(util.createRowSpace());
		Div div = Div.getInstance(null, null, null);
		Img img = Img.getInstance("uploadImg", null, null);
		Prop prop = new Prop();
		prop.setPropKey("src");
		String src ="";
		if(null==user.getUserImg()){
			 src =  "../ht/upload/images/uploadpic.png";
		}else{
			 src =  user.getUserImg();
		}
		prop.setPropValue(src);
		img.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("onclic()");
		img.addProp(prop);
		form.addChildNode(img);
		
		img = Img.getInstance("myImg", null, null);
		prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("cursor:pointer");
		img.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("onclic()");
		img.addProp(prop);
		form.addChildNode(img);
		
		Templete input = Templete.getInstance("input", null);
		prop = new Prop();
		prop.setPropKey("type");
		prop.setPropValue("file");
		input.addProp(prop);
		prop.setPropKey("name");
		prop.setPropValue("myfiles");
		input.addProp(prop);
		prop.setPropKey("id");
		prop.setPropValue("myfiles");
		input.addProp(prop);
		prop.setPropKey("onchange");
		prop.setPropValue("changeImg()");
		input.addProp(prop);
		prop.setPropKey("style");
		prop.setPropValue("display:none");
		input.addProp(prop);
		form.addChildNode(input);
		div.addChildNode(form);
		
		param.add(div);
		Base editPage = edit.createEditPage(param);
		InputHidden hiddenId = InputHidden.getInstance("userId",id);
		editPage.addChildNode(hiddenId);
		InputHidden hiddenRoleId = InputHidden.getInstance("roles",roles);
		editPage.addChildNode(hiddenRoleId);
		//返回节点字符串
		return editPage.getNode();
	}
	
	/**
	 * 初始化签章图片查看页面
	 * @param url 签章图片的服务器地址 
	 * @return 节点字符串
	 */
	public String getViewPage(String url) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "用户签章查看");
		util.createRowSpace(root);
		
		// 创建一个div
		CssClass divCss = new CssClass("col-lg-4 col-md-4 col-sm-3 col-xs-3");
		Div div = Div.getInstance("content", divCss, null);
		// 图片
		Img img = Img.getInstance("userImg", null, null);
		CssClass cssbtn = new CssClass("btn btn-default img-top");
		Prop prop = new Prop();
		prop.setPropKey("src");
		prop.setPropValue(url);
		img.addProp(prop);
		div.addChildNode(img);
		divCss = new CssClass("col-lg-12 col-md-12 col-sm-12 col-xs-12");
		Div div2 = Div.getInstance("content", divCss, null);
		// 返回按钮
		Button btn = Button.getInstance("back", cssbtn, "返回");
		div2.addChildNode(btn);
		root.addChildNode(div);
		root.addChildNode(div2);
		return root.getNode();
	}
}