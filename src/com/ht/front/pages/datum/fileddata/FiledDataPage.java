package com.ht.front.pages.datum.fileddata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.exception.DBException;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.File;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Span;
import com.ht.front.model.TextBox;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.organization.employee.User;
import com.ht.persistence.model.background.organization.organization.OrganizationUsersRelation;
import com.ht.persistence.model.datum.fileddata.FiledData;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.fileddata.FiledDataService;
import com.ht.service.inter.datum.type.DatumCategoryService;

/**
 * 外业汇交资料页面初始化类
 * @author zyd
 *
 */
public class FiledDataPage {
	
	/**
	 * 
	 * 初始化外业数据页面
	 * @param 
	 * @return 节点字符串
	 */
	//public String getListNode(BaseDataService baseDataService, DatumCategoryService datumcategoryService,List<String> ids){
		public String getListNode(BaseDataService baseDataService,DatumCategoryService datumcategoryService,List<String> ids,List<OrganizationUsersRelation> result,UserService userService){
				
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "外业汇交资料");
		util.createRowSpace(root);
		// 创建form表单内容
		CssClass Css = new CssClass("form-control");
		Base div = Div.getInstance(null, Css, null);
		
		// 创建第一行
		Base row = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(row,"4","4", "4",null);
		// 创建列
		Base col2 = util.createColumn(row,"4", "4","4",null);
		// 创建列
		Base col3 = util.createColumn(row,"4", "4","4",null);
		//创建图号搜索框
		InputGroup input = InputGroup.getInGroup("图&#12288&#12288号", "picNo",null, "请输入图号");
		col1.addChildNode(input);
		//创建比例尺搜索框
		input = InputGroup.getInGroup("比例尺(1:)", "scale",null, "请输入比例尺");
		col2.addChildNode(input);
		//创建项目名称搜索框
		input = InputGroup.getInGroup("项目名称", "projectName",null, "请输入项目名称");
		col3.addChildNode(input);

		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"6","6", "6",null);
		// 创建列
		col2 = util.createColumn(row,"6","6", "6",null);
		//创建汇交时间索框
		InputGroup ig = (InputGroup) util.createRangeDatePickerGroup("汇交时间", "concurrentTime", "请输入汇交时间", "concurrentTimeTwo", "请输入汇交时间");
		col1.addChildNode(ig);
		//创建搜索按钮
		Button searchButton = Button.getButtonWithIcon("search", new CssClass("btn btn-primary search"), "查询", I.getInstance(new CssClass("fa fa-search")));
		col2.addChildNode(searchButton);
		
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		if(ids.contains("11031915039750121")){// 资料录入员能看到所有
			
			CssClass css = new CssClass("fa fa-plus");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-success search");
			Button button = Button.getButtonWithIcon("add", css, "&nbsp;"+"创建", i);
			column.addChildNode(button);
			// 构建删除按钮
			css = new CssClass("fa fa-times");
			i = I.getInstance(css);
			css = new CssClass("btn btn-danger bk-margin-5 search");
			button = Button.getButtonWithIcon("remove", css, "&nbsp;"+"删除", i);
			column.addChildNode(button);
			// 构建导出div
			css = new CssClass("fa fa-sign-out");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 search");
			button = Button.getButtonWithIcon("export", css, "&nbsp;"+"导出", i);
			column.addChildNode(button);
			// 构建刷新按钮
			css = new CssClass("fa fa-refresh");
			i = I.getInstance(css);
			css = new CssClass("btn btn-warning bk-margin-5 search");
			button = Button.getButtonWithIcon("refresh", css, "&nbsp;"+"刷新", i);
			column.addChildNode(button);
			// 批量借阅按钮
			css = new CssClass("fa fa-book");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 search");
			button = Button.getButtonWithIcon("batchBorrowing", css, "&nbsp;"+"借阅", i);
			column.addChildNode(button);
			// 构建提交审核按钮
			css = new CssClass("fa fa-check");
			i = I.getInstance(css);
			css = new CssClass("btn btn-success bk-margin-5");
			button = Button.getButtonWithIcon("audit", css, "提交审核", i);
			column.addChildNode(button);
		}else{
			CssClass css = new CssClass("fa fa-book");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 search");
			Button button = Button.getButtonWithIcon("batchBorrowing", css, "&nbsp;"+"借阅", i);
			column.addChildNode(button);
		}
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"fileddata");
		/** 创建Grid行  结束*/	
		
		// 添加操作按钮
		// 上传
		Button uploadTempelate = null;
		Button tempelate = null;
		if(ids.contains("11031915039750121")){// 资料录入员能看到所有
			CssClass uploadCss = new CssClass("fa fa-upload");
			I uploadI = I.getInstance(uploadCss);
			uploadCss = new CssClass("btn btn-info bk-margin-5");
			uploadTempelate = Button.getButtonWithIcon(null, uploadCss, null, uploadI);
			Prop uploadProp = new Prop();
			uploadProp.setPropKey("name");
			uploadProp.setPropValue("uploadFiledData");
			uploadTempelate.addProp(uploadProp);
			uploadProp = new Prop();
			uploadProp.setPropKey("title");
			uploadProp.setPropValue("上传");
			uploadTempelate.addProp(uploadProp);
			uploadProp = new Prop();
			uploadProp.setPropKey("onclick");
			uploadProp.setPropValue("uploadPage(this)");
			uploadTempelate.addProp(uploadProp);
			
			// 添加编辑按钮
			CssClass editCss = new CssClass("fa fa-edit");
			I editI = I.getInstance(editCss);
			editCss = new CssClass("btn btn-success bk-margin-5");
			tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
			Prop prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("editFiledData");
			tempelate.addProp(prop);
			prop = new Prop();
			prop.setPropKey("title");
			prop.setPropValue("编辑");
			tempelate.addProp(prop);
			prop = new Prop();
			prop.setPropKey("onclick");
			prop.setPropValue("editPage(this)");
			tempelate.addProp(prop);
		}
		
		
		// 添加查看按钮
		CssClass editCss = new CssClass("fa fa-eye");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-warning bk-margin-5");
		Button viewFile = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop propView = new Prop();
		propView.setPropKey("name");
		propView.setPropValue("viewFile");
		viewFile.addProp(propView);
		propView = new Prop();
		propView.setPropKey("title");
		propView.setPropValue("查看");
		viewFile.addProp(propView);
		propView = new Prop();
		propView.setPropKey("onclick");
		propView.setPropValue("viewPage(this)");
		viewFile.addProp(propView);
		
		/** 创建Modal Dialog 导入  开始*/
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
		Div titleDiv = Div.getInstance(null, titleCss, "上传文件");
		headerDiv.addChildNode(titleDiv);
		// 创建form表单
		CssClass formCss = new CssClass("form-search");
		Form form = Form.getInstance("importForm",formCss,null);
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		form.addProp(formProp);
		Prop formProps = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		form.addProp(formProp);
		form.addProp(formProps);
		headerDiv.addChildNode(form);
		//文件导入和模板下载
		CssClass inputCss = new CssClass("row");
		Div inputDiv = Div.getInstance(null, inputCss, null);
		form.addChildNode(inputDiv);
		// 创建文件导入input框
		CssClass fileCss = new CssClass("file-input");
		File file = File.getInstance("uploadFile", fileCss);
		Prop fileProp = new Prop();
		fileProp.setPropKey("name");
		fileProp.setPropValue("upload");
		file.addProp(fileProp);
		inputDiv.addChildNode(file);
		//标识隐藏
		InputHidden hidden = InputHidden.getInstance("filedDataId", null);
		Prop hiddenProp = new Prop();
		hiddenProp.setPropKey("name");
		hiddenProp.setPropValue("filedDataId");
		hidden.addProp(hiddenProp);
		form.addChildNode(hidden);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("importSubmit", importCss, "导入");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		importBtn.addProp(importProp);
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束*/
		
	   	root.addChildNode(modalDiv);
		root.addChildNode(addUser(result,userService));
		
	    Script script = Script.getInstance("editTemplate");
		//Script script1 = Script.getInstance("editTemplate1");
		script.addChildNode(uploadTempelate);
	    script.addChildNode(tempelate);
	    script.addChildNode(viewFile);

		//script1.addChildNode(borrowTempelate);
		//script1.addChildNode(returnTempelate);
	   return root.getNode() + script.getNode();
	}
	 
		/**
		 * 根据登陆人的角色 判断是否显示createTask弹窗   
		 * 资料管理员可以帮指定机构借阅资料
		 * @return
		 */
		public Base addUser(List<OrganizationUsersRelation> result,UserService userService){
			/** 创建Modal Dialog 开始*/
			CssClass modelCss = new CssClass("modal fade");
			Div modalDiv = Div.getInstance("createTask", modelCss, null);
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
			Div titleDiv = Div.getInstance(null, titleCss, "指定人员");
			headerDiv.addChildNode(titleDiv);
		
			// 创建文件input框
			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			if(result != null && result.size()>0) {
				for (int i = 0; i < result.size(); i++) {
					Map<String,String> map = new HashMap<String, String>();
					String userId =  result.get(i).getUserId();
					try {
						User user = userService.getUser(userId);
						
						if(user!= null) {
							map.put("userNo",user.getUserNo());
			     			map.put("name",  user.getUserName());
							mapList.add(map);
						}
					} catch (DBException e) {
						e.printStackTrace();
					}
				}
			}
			InputGroup taskType = InputGroup.getSelectGroupWithDefaultOption("分配人员", "user", mapList, "userNo", "name", true);
			headerDiv.addChildNode(taskType);
			// 构建导入按钮
			CssClass importCss = new CssClass("import-submit btn btn-primary");
			Button importBtn = Button.getInstance("commit", importCss, "确认");
			Prop importProp = new Prop();
			importProp.setPropKey("data-dismiss");
			importProp.setPropValue("modal");
			
			headerDiv.addChildNode(importBtn);
			/** Modal Dialog 结束*/
			return modalDiv;
		}
		
	/**
	 * 编辑外业汇交页面初始化
	 * @param id
	 * @param userService 
	 * @param filedDataService 
	 * @return
	 */
	public String getEditNode(String id, UserService userService, BaseDataService baseDataService,FiledDataService filedDataService,String mark) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		List<BaseData> seaArealist = baseDataService.getBaseDataByTypeId("02281555137100012");
		// 创建一个容器
		try{
			if(id != null){
				List<Base> param = new ArrayList<Base>();
				// 获取用户list
				List<User> userList = userService.getUserList();
				// 获取一条外业汇交数据
				FiledData filedData = filedDataService.getFiledData(id);
				InputGroup tb = InputGroup.getInGroup("图&#12288&#12288号", "picNo", filedData.getPicNo(), "请输入图号");
				param.add(tb);
				
				Base sg = util.creatDefaultSelectGroup("海&#12288&#12288区", "seaArea", seaArealist, "value", "value",filedData.getSeaArea());
				param.add(sg);
				
				tb = InputGroup.getInGroup("比例尺（1:）", "scale", filedData.getScale(), "请输入比例尺：");
				param.add(tb);
				tb = InputGroup.getInGroup("项目名称", "projectName", filedData.getProjectName(), "请输入项目名称");
				param.add(tb);
				tb = InputGroup.getInGroup("测量周期", "measureCycle", filedData.getMeasureCycle(), "请输入测量周期");
				param.add(tb);
				// 获取汇交时间
				String concurrentTime = filedData.getConcurrentTime().toString();
				tb = InputGroup.getDatePicker("汇交时间", "concurrentTime", concurrentTime, null);
				param.add(tb);
				
				tb = InputGroup.getInGroup("数据名称", "dataName", filedData.getDataName(), "请输入数据名称");
				param.add(tb);
				tb = InputGroup.getInGroup("数据形式", "dataForm", filedData.getDataForm(), "请输入数据形式");
				param.add(tb);
				tb = InputGroup.getInGroup("原始文件", "originalFileName", filedData.getOriginalFileName(), "请输入原始文件名称");
				param.add(tb);
				tb = InputGroup.getInGroup("&#12288总份数", "total", filedData.getTotal(), "请输入总份数");
				param.add(tb);
				tb = InputGroup.getInGroup(true,"在库份数", "copies", filedData.getCopies(), "请输入在库份数");
				param.add(tb);
				tb = InputGroup.getInGroup(true,"可借份数", "canBorrowing", filedData.getCanBorrowing(), "请输入可借份数");
				param.add(tb);
				Base selectgroup = util.creatDefaultSelectGroup("&#12288接收人", "recipient", userList, "id", "userName",filedData.getRecipient());
				param.add(selectgroup);
				editPage = edit.createEditPage(param);
				if(mark.equals("2")){
					util.createHeaderBar(editPage, "外业汇交编辑");
				}else{
					util.createHeaderBar(editPage, "外业汇交明细查看");
				}
				InputHidden hidden = InputHidden.getInstance("filedDataId", id);
				editPage.addChildNode(hidden);
				InputHidden markHidden = InputHidden.getInstance("mark", mark);
				editPage.addChildNode(markHidden);
				hidden = InputHidden.getInstance("sum", filedData.getTotal());
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				List<User> userList = userService.getUserList();
				InputGroup tb = InputGroup.getInGroup("图&#12288&#12288号", "picNo", null, "请输入图号");
				param.add(tb);
				
				Base sg = util.creatDefaultSelectGroup("海&#12288&#12288区", "seaArea", seaArealist, "value", "value",false);
				param.add(sg);
				
				tb = InputGroup.getInGroup("比例尺(1:)", "scale", null, "请输入比例尺");
				param.add(tb);
				tb = InputGroup.getInGroup("项目名称", "projectName", null, "请输入项目名称");
				param.add(tb);
				tb = InputGroup.getInGroup("测量周期", "measureCycle", null, "请输入测量周期");
				param.add(tb);
				tb = InputGroup.getDatePicker("汇交时间", "concurrentTime", null, "请选择汇交时间");
				param.add(tb);
				tb = InputGroup.getInGroup("数据名称", "dataName", null, "请输入数据名称");
				param.add(tb);
				tb = InputGroup.getInGroup("数据形式", "dataForm", null, "请输入数据形式");
				param.add(tb);
				tb = InputGroup.getInGroup("原始文件", "originalFileName", null, "请输入原始文件名称");
				param.add(tb);
				tb = InputGroup.getInGroup("&#12288总份数", "total", null, "请输入总份数");
				param.add(tb);
				Base selectgroup = util.creatDefaultSelectGroup("&#12288接收人", "recipient", userList, "id", "userName",false);
				param.add(selectgroup);
				
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "外业汇交创建");
			}
			//返回节点字符串
			return editPage.getNode();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 
	 * 初始化外业汇交资料附件页面
	 * @param bookId 外业汇交id
	 * @return 节点字符串
	 */
	public String getFileListNode(String filedDataId,boolean flag){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "外业汇交附件管理");
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow();
		// 创建列
		Base column = util.createColumn(rowBg, "12","12");
		// 创建按钮组
		// 构建行div
		CssClass css = new CssClass("row");
		util.createRow(column);
		css = new CssClass("col-md-12 col-xs-12 col-sm-12");
		Div div = Div.getInstance(null, css, null);
		// 构建创建div
		css = new CssClass("fa fa-mail-reply-all");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success ");
		Button button = Button.getButtonWithIcon("back", css, "返回", i);
		div.addChildNode(button);
		column.addChildNode(div);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		/** 创建Grid行  开始*/
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"filedData");
		/** 创建Grid行  结束*/
		// 将行加入到容器
		root.addChildNode(rowBg);
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
		Script script = Script.getInstance("editTemplate");
			// 添加编辑按钮
			CssClass editCss = new CssClass("fa fa-cloud-download");
			I editI = I.getInstance(editCss);
			editCss = new CssClass("btn btn-success bk-margin-5");
			Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
			Prop prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("downloadFile");
			tempelate.addProp(prop);
			script.addChildNode(tempelate);
			if(flag){
			// 删除按钮
			CssClass delCss = new CssClass("fa fa-times");
			I delI = I.getInstance(delCss);
			delCss = new CssClass("btn btn-danger bk-margin-5");
			tempelate = Button.getButtonWithIcon("remove", delCss, null, delI);
			prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("removeFile");
			tempelate.addProp(prop);
			script.addChildNode(tempelate);
			}
		
		
		InputHidden hidden = InputHidden.getInstance("filedDataId", filedDataId);
		root.addChildNode(hidden);
		return root.getNode() + script.getNode();
	}
	
	/**
	 * 初始化外业汇交借阅页面
	 * @param filedData
	 * @param bookId
	 * @return
	 */
	public String getBorrowing(FiledData filedData) {
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		List<Base> param = new ArrayList<Base>();
		CssClass Css = new CssClass("form-control");
		Prop prop = new Prop();
		prop.setPropKey("readonly");
		prop.setPropValue("readonly");
		// 设置图号输入框
		String picNo = "图号";
		Span picNoSpan = Span.getDefault(picNo);
		TextBox picNoTb = TextBox.getInstance("picNo", "picNo", filedData.getPicNo(), Css, "请输入外业汇交编号 ");
		picNoTb.addProp(prop);
		param.add(InputGroup.getInstance(picNoSpan,picNoTb));
		
		// 设置项目名称输入框
		String projectName = "项目名称";
		Span projectNameSpan = Span.getDefault(projectName);
		TextBox projectNameTb = TextBox.getInstance("projectName", "projectName", filedData.getProjectName(), Css, "请输入外业汇交编号 ");
		projectNameTb.addProp(prop);
		param.add(InputGroup.getInstance(projectNameSpan, projectNameTb));

		// 设置借阅者输入框
	/*	String borrowPerson = "借阅者";
		Span borrowPersonSpan = Span.getDefault(borrowPerson);
		TextBox borrowPersonTb = TextBox.getInstance("borrowPerson", "borrowPerson", null, Css, "请输入借阅者 ");
		param.add(InputGroup.getInstance(borrowPersonSpan, borrowPersonTb));*/
		
		// 设置借阅日期
		/*InputGroup tb = InputGroup.getDatePicker("借阅日期"+"&nbsp;&nbsp;", "borrowDate", null, null);
		param.add(tb);*/
		
		// 设置可借数量输入框
		String CanBorrowNo = "可借数量";
		Span canBorrowNoSpan = Span.getDefault(CanBorrowNo);
		TextBox canBorrowNoTb = TextBox.getInstance("canBorrowNo", "canBorrowNo", filedData.getCanBorrowing(), Css, "请输入借阅数量 ");
		canBorrowNoTb.addProp(prop);
		param.add(InputGroup.getInstance(canBorrowNoSpan, canBorrowNoTb));
		
		// 设置借阅数量输入框
		String borrowNo = "借阅数量";
		Span borrowNoSpan = Span.getDefault(borrowNo);
		TextBox borrowNoTb = TextBox.getInstance("borrowNo", "borrowNo", null, Css, "请输入借阅数量 ");
		param.add(InputGroup.getInstance(borrowNoSpan, borrowNoTb));
		
		// 设置借阅天数
		/*String borrowDays = "借阅天数";
		Span borrowDaysSpan = Span.getDefault(borrowDays);
		TextBox borrowDaysTb = TextBox.getInstance("borrowDays", "borrowDays", null, Css, "请输入借阅天数 ");
		param.add(InputGroup.getInstance(borrowDaysSpan, borrowDaysTb));*/
		
		List<BaseData> secrecylist = new ArrayList<BaseData>();
		BaseData data1 = new BaseData();
		data1.setId("是");
		data1.setValue("是");
		secrecylist.add(data1);
		BaseData data = new BaseData();
		data.setId("否");
		data.setValue("否");
		secrecylist.add(data);
		Base selectgroup = util.creatDefaultSelectGroup("资料是否涉密", "isSecrecy", secrecylist, "id", "value", false);
		param.add(selectgroup);
		
		Base editPage = edit.createEditPage(param);
		util.createHeaderBar(editPage, "外业汇交资料借阅");
		InputHidden hidden = InputHidden.getInstance("filedDataId", filedData.getId());
		editPage.addChildNode(hidden);
		//返回节点字符串
		return editPage.getNode();
	}
	
	/**
	 * 初始化图书借阅页面
	 * @param bookInfo
	 * @param bookId
	 * @return
	 */
	public String getBatchBorrowing(String ids,String userNo) {

		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "外业资料借阅申请表");
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "9", "9", "9", null);
		// 构建提交按钮
		CssClass css = new CssClass("fa fa-check-square");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success search");
		Button button = Button.getButtonWithIcon("submit", css, "提交", i);
		column.addChildNode(button);
		// 构建返回按钮
		css = new CssClass("btn btn-default bk-margin-5 search");
		button = Button.getButtonWithIcon("back", css, "返回",null);
		column.addChildNode(button);
		
		Base colInput = util.createColumn(rowBg, "3", "3");
		List<BaseData> secrecylist = new ArrayList<BaseData>();
		BaseData data1 = new BaseData();
		data1.setId("是");
		data1.setValue("是");
		secrecylist.add(data1);
		BaseData data = new BaseData();
		data.setId("否");
		data.setValue("否");
		secrecylist.add(data);
		Base selectgroup = util.creatDefaultSelectGroup("资料是否涉密", "isSecrecy", secrecylist, "id", "value", false);
		colInput.addChildNode(selectgroup);
		
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行 开始 */
		// 创建Grid
		Base rowGrid = util.createGrid(root, "batchBorrowing");
		/** 创建Grid行 结束 */
		InputHidden hidden = InputHidden.getInstance("ids", ids);
		root.addChildNode(hidden);
		InputHidden hidden1 = InputHidden.getInstance("userNo", userNo);
		root.addChildNode(hidden1);
		return root.getNode();
	}
	
	/**
	 * 初始化外业汇交归还页面
	 * @param filedData 
	 * @return
	 */
	public String getReturn(FiledData filedData) {
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		List<Base> param = new ArrayList<Base>();

		CssClass Css = new CssClass("form-control");
		Prop prop = new Prop();
		prop.setPropKey("disabled");
		prop.setPropValue("disabled");
		// 设置图号号输入框
		String picNo = "图号";
		Span picNoSpan = Span.getDefault(picNo);
		TextBox picNoTb = TextBox.getInstance("picNo", "picNo", filedData.getPicNo(), Css, "请输入图号 ");
		picNoTb.addProp(prop);
		param.add(InputGroup.getInstance(picNoSpan,picNoTb));
		
		// 设置项目名称输入框
		String projectName = "项目名称";
		Span projectNameSpan = Span.getDefault(projectName);
		TextBox projectNameTb = TextBox.getInstance("projectName", "projectName", filedData.getProjectName(), Css, "请输入项目名称 ");
		projectNameTb.addProp(prop);
		param.add(InputGroup.getInstance(projectNameSpan, projectNameTb));
		
		// 设置归还人名称输入框
		String returnPerson = "归还人";
		Span returnPersonSpan = Span.getDefault(returnPerson);
		TextBox returnPersonTb = TextBox.getInstance("returnPerson", "returnPerson", null, Css, "请输入借阅者 ");
		param.add(InputGroup.getInstance(returnPersonSpan, returnPersonTb));
		
		// 设置归还日期
		InputGroup tb = InputGroup.getDatePicker("归还日期"+"&nbsp;&nbsp;", "returnDate", null, null);
		param.add(tb);
		
		// 设置借阅数量输入框
		String borrowNo = "归还数量";
		Span returnNoSpan = Span.getDefault(borrowNo);
		TextBox returnNoTb = TextBox.getInstance("returnNo", "returnNo", null, Css, "请输入归还数量 ");
		param.add(InputGroup.getInstance(returnNoSpan, returnNoTb));
		
		// 设置借阅天数
		String borrowDays = "借阅天数";
		Span borrowDaysSpan = Span.getDefault(borrowDays);
		TextBox borrowDaysTb = TextBox.getInstance("borrowDays", "borrowDays", null, Css, "请输入借阅天数 ");
		param.add(InputGroup.getInstance(borrowDaysSpan, borrowDaysTb));
		
		Base editPage = edit.createEditPage(param);
		util.createHeaderBar(editPage, "外业汇交资料借阅");
		//返回节点字符串
		return editPage.getNode();
	}
}
