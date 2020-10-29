package com.ht.front.pages.datum.books;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.exception.DBException;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
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
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.bookinfo.BookFileService;
import com.ht.service.inter.datum.books.BooksService;
import com.ht.service.inter.datum.type.DatumCategoryService;
import com.ht.workflow.common.ProcessDefinitionInfo;

/**
 * 图书资料页面初始化类
 * @author zyd
 *
 */
public class BooksPage {
	
	/**
	 * 
	 * 初始化资料维护页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListNode(BaseDataService baseDataService,DatumCategoryService datumcategoryService,List<String> ids,List<OrganizationUsersRelation> result,UserService userService){
		
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "海图资料");
		util.createRowSpace(root);
		Div div = Div.getBlankDiv("headerserch");
		// 创建第一行
		Base row = util.createRow(div);
		// 创建列
		Base col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col4 = util.createColumn(row,"3","3", "3","0");
		//创建图名搜索框
		InputGroup input = InputGroup.getInGroup("图&#12288&#12288名", "chartName",null, "请输入图名");
		col1.addChildNode(input);
		//创建图号搜索框
		input = InputGroup.getInGroup("图&#12288&#12288号", "chartNo",null, "请输入图号");
		col2.addChildNode(input);
		//创建录入者搜索框
		input = InputGroup.getInGroup("录入者&#12288", "person",null, "请输入录入者");
		col3.addChildNode(input);	
		//创建版本号搜索框
		input = InputGroup.getInGroup("版本号&#12288", "version",null, "请输入版本号");
		col4.addChildNode(input);
		
		//添加1个行间距
		div.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(div);
		// 创建列
		col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col4 = util.createColumn(row,"3","3", "3","0");
		//创建一级子类下拉框搜索框
		List<DatumCategory> dcList = datumcategoryService.getDatumCategoryByParentId("201610301150");
		Base onesubclass = util.creatDefaultSelectGroup("一级子类", "oneSubClass", dcList, "id", "categoryName", false);
		col1.addChildNode(onesubclass);
		//创建二级子类下拉框搜索框
		Base twosubclass = util.creatDefaultSelectGroup("二级子类", "twoSubClass", null,"id", "categoryName", false);
		col2.addChildNode(twosubclass);		
		// 创建状态下拉框
		List<BaseData> stateList = baseDataService.getBaseDataByTypeId("11031952486860383");
		Base state = util.creatDefaultSelectGroup("状&#12288&#12288态", "state", stateList, "id", "value", false);
		col3.addChildNode(state);
		// 创建港口地区下拉框
		List<BaseData> portList = baseDataService.getBaseDataByTypeId("02281125502140137");
		Base port = util.creatDefaultSelectGroup("港口/地区", "port", portList, "id", "value", false);
		col4.addChildNode(port);
		
		//添加1个行间距
		div.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(div);
		// 创建列
		col1 = util.createColumn(row,"6","6", "6",null);
		// 创建列
		col2 = util.createColumn(row,"6","6", "6",null);
		//创建库存数量1搜索框
		input = (InputGroup) util.createRangeControlGroup("库存数量", "stockNo", "请输入库存数量", "stockNoTwo", "请输入库存数量");
		col1.addChildNode(input);			
		input = (InputGroup) util.createYearMonthDatePickerGroup("出版日期", "publicationDate", "请输入出版日期", "publicationDateTwo", "请输入出版日期");
		col2.addChildNode(input);
		//添加1个行间距
		div.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(div);
		// 创建列
		col1 = util.createColumn(row,"6","6", "6",null);
		// 创建列
		col2 = util.createColumn(row,"6","6", "6",null);
		input = (InputGroup) util.createRangeControlGroup("比例尺(1:)", "scale", "请输入比例尺", "scaleTwo", "请输入比例尺");
		col1.addChildNode(input);			
		//创建搜索按钮
		Button searchButton = Button.getButtonWithIcon("search", new CssClass("btn btn-primary search"), "查询", I.getInstance(new CssClass("fa fa-search")));
		col2.addChildNode(searchButton);
		
		root.addChildNode(div);
		root.addChildNode(util.createRowSpace());
		root.addChildNode(util.createRowSpace());
		//创建一个机构人员列表
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
			// 构建导入按钮
			css = new CssClass("fa fa-sign-in");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 search btn-settings");
			button = Button.getButtonWithIcon("import", css, "&nbsp;"+"导入", i);
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
		Base rowGrid = util.createGrid(root,"books");
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
			uploadProp.setPropValue("uploadDatumFile");
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
			prop.setPropValue("editBooks");
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
		propView.setPropValue("ViewFile");
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
		InputHidden bookInfoIdHidden = InputHidden.getInstance("booksId", null);
		Prop bookInfoIdProp = new Prop();
		bookInfoIdProp.setPropKey("name");
		bookInfoIdProp.setPropValue("booksId");
		bookInfoIdHidden.addProp(bookInfoIdProp);
		form.addChildNode(bookInfoIdHidden);
		
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("importSubmit", importCss, "导入");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		importBtn.addProp(importProp);
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束*/
		/** 创建Modal Dialog 导入图书 开始 */
		CssClass modelCss2 = new CssClass("modal fade");
		Div modalDiv2 = Div.getInstance("myModals", modelCss2, null);
		// 创建div
		CssClass dialogCss2 = new CssClass("modal-dialog");
		Div dialogDiv2 = Div.getInstance(null, dialogCss2, null);
		modalDiv2.addChildNode(dialogDiv2);
		// 创建div
		CssClass contentCss2 = new CssClass("modal-content");
		Div contentDiv2 = Div.getInstance(null, contentCss2, null);
		dialogDiv2.addChildNode(contentDiv2);
		// 创建header div
		CssClass headerCss2 = new CssClass("modal-header");
		Div headerDiv2 = Div.getInstance(null, headerCss2, null);
		contentDiv2.addChildNode(headerDiv2);
		// 构建关闭按钮
		CssClass closeCss2 = new CssClass("close");
		Button closeBtn2 = Button.getInstance(null, closeCss2, "&times;");
		Prop closeProp2 = new Prop();
		closeProp2.setPropKey("data-dismiss");
		closeProp2.setPropValue("modal");
		Prop closeProps2 = new Prop();
		closeProps2.setPropKey("aria-hidden");
		closeProps2.setPropValue("true");
		// 绑定属性
		closeBtn2.addProp(closeProp2);
		closeBtn2.addProp(closeProps2);
		headerDiv2.addChildNode(closeBtn2);
		// 创建标题div
		CssClass titleCss2 = new CssClass("modal-title bk-fg-primary model-custom");
		Div titleDiv2 = Div.getInstance(null, titleCss2, "导入图书");
		headerDiv2.addChildNode(titleDiv2);
		// 创建form表单
		CssClass formCss2 = new CssClass("form-search");
		Form form2 = Form.getInstance("importBookForm", formCss2, null);
		Prop formProp2 = new Prop();
		formProp2.setPropKey("method");
		formProp2.setPropValue("post");
		form2.addProp(formProp2);
		Prop formProps2 = new Prop();
		formProps2.setPropKey("enctype");
		formProps2.setPropValue("multipart/form-data");
		form2.addProp(formProps2);
		headerDiv2.addChildNode(form2);
		// 文件导入和模板下载
		CssClass inputCss2 = new CssClass("row");
		Div inputDiv2 = Div.getInstance(null, inputCss2, null);
		form2.addChildNode(inputDiv2);
		// 创建文件导入input框
		CssClass fileCss2 = new CssClass("file-input");
		File file2 = File.getInstance("uploadBookFile", fileCss2);
		Prop fileProp2 = new Prop();
		fileProp2.setPropKey("name");
		fileProp2.setPropValue("uploadBook");
		file2.addProp(fileProp2);
		inputDiv2.addChildNode(file2);
		// 模板下载按钮
		CssClass tempCss2 = new CssClass("btn btn-primary btn-export");
		Button tempBtn2 = Button.getInstance("exportBookTemplate", tempCss2, "下载模板");
		inputDiv2.addChildNode(tempBtn2);
		// 构建导入按钮
		CssClass importCss2 = new CssClass("import-submit btn btn-primary");
		Button importBtn2 = Button.getInstance("importBookSubmit", importCss2, "导入");
		Prop importProp2 = new Prop();
		importProp2.setPropKey("data-dismiss");
		importProp2.setPropValue("modal");
		importBtn2.addProp(importProp2);
		headerDiv2.addChildNode(importBtn2);
		/** Modal Dialog 结束 */
		// 添加归还按钮
		/*editCss = new CssClass("fa fa-plus-square");
		editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-warning bk-margin-5");
		Button returnBtn = Button.getButtonWithIcon(null, editCss, null, editI);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("return");
		returnBtn.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("归还");
		returnBtn.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("returnPage(this)");
		returnBtn.addProp(prop);*/
		
		root.addChildNode(modalDiv);
		root.addChildNode(modalDiv2);
		root.addChildNode(addUser(result,userService));
		Script script = Script.getInstance("editTemplate");
		//Script script1 = Script.getInstance("editTemplate1");
		script.addChildNode(uploadTempelate);
	
		script.addChildNode(tempelate);
		script.addChildNode(viewFile);
		//script1.addChildNode(borrowTempelate);
		//script1.addChildNode(returnBtn);
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
	 * 初始化编辑页面
	 * @param id
	 * @param bookInfoService 
	 * @param baseDataService 
	 * @param list 
	 * @return
	 */
	public String getEditNode(String id,BooksService booksService,BaseDataService baseDataService, List<DatumCategory> list, UserService userService,DatumCategoryService datumcategoryService) {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		try{
			if(id != null){
				List<Base> param = new ArrayList<Base>();
				// 获取海图
				Books books = booksService.getBooks(id);
				InputGroup tb = InputGroup.getInGroup("编&#12288&#12288码", "code", books.getCode(), "请输入编码");
				param.add(tb);
				tb = InputGroup.getInGroup("图&#12288&#12288号", "chartNo", books.getChartNo(), "请输入图号");
				param.add(tb);
				tb = InputGroup.getInGroup("图&#12288&#12288名", "chartName", books.getChartName(), "请输入图名");
				param.add(tb);
				Base selectgroup = util.creatDefaultSelectGroup("一级子类", "oneSubClass", list, "id", "categoryName",books.getOneSubClass());
				param.add(selectgroup);
				
				List<DatumCategory> twoList = datumcategoryService.getDatumCategoryByParentId(books.getOneSubClass());
				selectgroup = util.creatDefaultSelectGroup("二级子类", "twoSubClass", twoList, "id", "categoryName",books.getTwoSubClass());
				param.add(selectgroup);
				List<BaseData> portlist = baseDataService.getBaseDataByTypeId("02281125502140137");
				selectgroup = util.creatDefaultSelectGroup("港口/地区", "port", portlist, "id", "value",books.getPort());
				param.add(selectgroup);
				
				String scale = books.getScale();
				if(scale != null && scale != ""){
					tb = InputGroup.getInGroup("比例尺(1:)", "scale", scale, "请输入比例尺");
					param.add(tb);
				}else {
					tb = InputGroup.getInGroup("比例尺(1:)", "scale", null, "请输入比例尺");
					param.add(tb);
				}
				
				// 获取首版年月
				Date date = books.getFirstVersionDate();
				if(date != null){
					String publicationDate = (new SimpleDateFormat("yyyy-MM")).format(date);
					InputGroup TimeInput = InputGroup.getDatePickerYearMonth("首版年月", "firstVersionDate", publicationDate, "请选择首版年月");
					param.add(TimeInput);
				}else {
					InputGroup TimeInput = InputGroup.getDatePickerYearMonth("首版年月", "firstVersionDate", null, "请选择首版年月");
					param.add(TimeInput);
				}
				// 获取出版年月
				date = books.getPublicationDate();
				if(date != null){
					String publicationDate = (new SimpleDateFormat("yyyy-MM")).format(date);
					InputGroup TimeInput = InputGroup.getDatePickerYearMonth("出版年月", "publicationDate", publicationDate, "请选择出版年月");
					param.add(TimeInput);
				}else {
					InputGroup TimeInput = InputGroup.getDatePickerYearMonth("出版年月", "publicationDate", null, "请选择出版年月");
					param.add(TimeInput);
				}
				//出版单位
				tb = InputGroup.getInGroup("出版单位", "publicationCompany", books.getPublicationCompany(), "请输入出版单位");
				param.add(tb);
				
				String version = books.getVersion();
				if(version != null && version != ""){
					tb = InputGroup.getInGroup("&#12288版本号", "version", version, "请输入版本号");
					param.add(tb);
				}else {
					tb = InputGroup.getInGroup("&#12288版本号", "version", null, "请输入版本号");
					param.add(tb);
				}
				// 获取印刷年月
				date = books.getPrintDate();
				if(date != null){
					String printDate = (new SimpleDateFormat("yyyy-MM")).format(date);
					InputGroup TimeInput = InputGroup.getDatePickerYearMonth("印刷年月", "printDate", printDate, "请选择印刷年月");
					param.add(TimeInput);
				}else {
					InputGroup TimeInput = InputGroup.getDatePickerYearMonth("印刷年月", "printDate", null, "请选择印刷年月");
					param.add(TimeInput);
				}
				//改正项号
				tb = InputGroup.getInGroup("改正项号", "correctNo", books.getCorrectNo(), "请输入改正项号");
				param.add(tb);
				String savePlace = books.getSavePlace();
				if(savePlace != null && savePlace != ""){
					tb = InputGroup.getInGroup("存储位置", "savePlace", savePlace, "请输入存储位置");
					param.add(tb);
				}else {
					tb = InputGroup.getInGroup("存储位置", "savePlace", null, "请输入存储位置");
					param.add(tb);
				}
				tb = InputGroup.getInGroup("库存总数", "total", books.getTotal(), "请输入库存总数");
				param.add(tb);
				tb = InputGroup.getInGroup(true,"在库数量", "stockNo", books.getStockNo(), "请输入在库数量");
				param.add(tb);
				tb = InputGroup.getInGroup(true,"可借数量", "canBorrowing", books.getCanBorrowing(), "请输入可借数量");
				param.add(tb);
				//经度范围
				String starLongitude = "";
				if(StringUtils.isNotBlank(books.getLongitudeFrom())){
					starLongitude = books.getLongitudeFrom();
					String[] starLongitudeArray = starLongitude.split("°");
					Long result = Long.valueOf(starLongitudeArray[0])-180;
					if(result>=0){
						if(starLongitudeArray.length == 1){
							starLongitude = result+"°"+"E";
							starLongitude = starLongitude.replace("°", "");
						}else{
							starLongitude = result+"°"+starLongitudeArray[1]+"E";
							starLongitude = starLongitude.replace("°", "-");
							if(starLongitudeArray[1].contains("″")){
								starLongitude = starLongitude.replace("′", "-");
								starLongitude = starLongitude.replace("″", "");
							}else{
								starLongitude = starLongitude.replace("′", "");
							}
						}
					}else{
						if(starLongitudeArray.length == 1){
							starLongitude = Math.abs(result)+"°"+"W";
							starLongitude = starLongitude.replace("°", "");
						}else{
							starLongitude = Math.abs(result)+"°"+starLongitudeArray[1]+"W";
							starLongitude = starLongitude.replace("°", "-");
							if(starLongitudeArray[1].contains("″")){
								starLongitude = starLongitude.replace("′", "-");
								starLongitude = starLongitude.replace("″", "");
							}else{
								starLongitude = starLongitude.replace("′", "");
							}
						}
					}
				}
				tb = InputGroup.getInGroup("起始经度", "longitudeFrom",starLongitude, "请输入起始经度（东经）");
				param.add(tb);
				String endLongitude = "";
				if(StringUtils.isNotBlank(books.getLongitudeTo())){
					endLongitude = books.getLongitudeTo();
					String[] endLongitudeArray = endLongitude.split("°");
					Long result = Long.valueOf(endLongitudeArray[0])-180;
					if(result>=0){
						if(endLongitudeArray.length == 1){
							endLongitude = result+"°"+"E";
							endLongitude = endLongitude.replace("°", "");
						}else{
							endLongitude = result+"°"+endLongitudeArray[1]+"E";
							endLongitude = endLongitude.replace("°", "-");
							if(endLongitudeArray[1].contains("″")){
								endLongitude = endLongitude.replace("′", "-");
								endLongitude = endLongitude.replace("″", "");
							}else{
								endLongitude = endLongitude.replace("′", "");
							}
						}
					}else{
						if(endLongitudeArray.length == 1){
							endLongitude = Math.abs(result)+"°"+"W";
							endLongitude = endLongitude.replace("°", "");
						}else{
							endLongitude = Math.abs(result)+"°"+endLongitudeArray[1]+"W";
							endLongitude = endLongitude.replace("°", "-");
							if(endLongitudeArray[1].contains("″")){
								endLongitude = endLongitude.replace("′", "-");
								endLongitude = endLongitude.replace("″", "");
							}else{
								endLongitude = endLongitude.replace("′", "");
							}
						}
					}
				}
				tb = InputGroup.getInGroup("终止经度", "longitudeTo", endLongitude, "请输入终止经度（西经）");
				param.add(tb);
				//纬度范围
				
				String starLatitude = "";
				if(StringUtils.isNotBlank(books.getLatitudeFrom())){
					starLatitude = books.getLatitudeFrom();
					String[] starLatitudeArray = starLatitude.split("°");
					Long result = Long.valueOf(starLatitudeArray[0])-90;
					if(result>=0){
						if(starLatitudeArray.length == 1){
							starLatitude = result+"°"+"N";
							starLatitude = starLatitude.replace("°", "");
						}else{
							starLatitude = result+"°"+starLatitudeArray[1]+"N";
							starLatitude = starLatitude.replace("°", "-");
							if(starLatitudeArray[1].contains("″")){
								starLatitude = starLatitude.replace("′", "-");
								starLatitude = starLatitude.replace("″", "");
							}else{
								starLatitude = starLatitude.replace("′", "");
							}
						}
					}else{
						if(starLatitudeArray.length == 1){
							starLatitude = Math.abs(result)+"°"+"S";
							starLatitude = starLatitude.replace("°", "");
						}else{
							starLatitude = Math.abs(result)+"°"+starLatitudeArray[1]+"S";
							starLatitude = starLatitude.replace("°", "-");
							if(starLatitudeArray[1].contains("″")){
								starLatitude = starLatitude.replace("′", "-");
								starLatitude = starLatitude.replace("″", "");
							}else{
								starLatitude = starLatitude.replace("′", "");
							}
						}
					}
				}
				tb = InputGroup.getInGroup("起始纬度", "latitudeFrom", starLatitude, "请输入起始纬度（北纬）");
				param.add(tb);
				
				String endLatitude = "";
				if(StringUtils.isNotBlank(books.getLatitudeTo())){
					endLatitude = books.getLatitudeTo();
					String[] endLatitudeArray = endLatitude.split("°");
					Long result = Long.valueOf(endLatitudeArray[0])-90;
					if(result>=0){
						if(endLatitudeArray.length == 1){
							endLatitude = result+"°"+"N";
							endLatitude = endLatitude.replace("°", "");
						}else{
							endLatitude = result+"°"+endLatitudeArray[1]+"N";
							endLatitude = endLatitude.replace("°", "-");
							if(endLatitudeArray[1].contains("″")){
								endLatitude = endLatitude.replace("′", "-");
								endLatitude = endLatitude.replace("″", "");
							}else{
								endLatitude = endLatitude.replace("′", "");
							}
						}
					}else{
						if(endLatitudeArray.length == 1){
							endLatitude = Math.abs(result)+"°"+"S";
							endLatitude = endLatitude.replace("°", "");
						}else{
							endLatitude = Math.abs(result)+"°"+endLatitudeArray[1]+"S";
							endLatitude = endLatitude.replace("°", "-");
							if(endLatitudeArray[1].contains("″")){
								endLatitude = endLatitude.replace("′", "-");
								endLatitude = endLatitude.replace("″", "");
							}else{
								endLatitude = endLatitude.replace("′", "");
							}
						}
					}
				}
				tb = InputGroup.getInGroup("终止纬度", "latitudeTo", endLatitude, "请输入终止纬度（南纬）");
				param.add(tb);
				//海图状态
				tb = InputGroup.getInGroup("海图状态", "seaMapStatus", books.getSeaMapStatus(), "请输入海图状态");
				param.add(tb);
				//电子数据下载权限
				tb = InputGroup.getInGroup("下载权限", "downPermission", books.getDownPermission(), "请输入电子数据下载权限");
				param.add(tb);
				param.add(null);
				//备注
				InputGroup textArea = InputGroup.getTextAreaGroup("备&#12288&#12288注","remarks","5",books.getRemarks() == null ? null : books.getRemarks());
				param.add(textArea);
				//流转意见
				InputGroup flowSuggestion = InputGroup.getTextAreaGroup("流转意见","flowSuggestion","5",books.getFlowSuggestion() == null ? null : books.getFlowSuggestion());
				param.add(flowSuggestion);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "海图资料编辑");
				InputHidden hidden = InputHidden.getInstance("booksId", id);
				editPage.addChildNode(hidden);
				hidden = InputHidden.getInstance("sum", books.getTotal());
				editPage.addChildNode(hidden);
			}else{
				List<Base> param = new ArrayList<Base>();
				InputGroup tb = InputGroup.getInGroup("编&#12288&#12288码", "code", null, "请输入编码");
				param.add(tb);
				tb = InputGroup.getInGroup("图&#12288&#12288号", "chartNo", null, "请输入图号");
				param.add(tb);
				tb = InputGroup.getInGroup("图&#12288&#12288名", "chartName", null, "请输入图名");
				param.add(tb);
				Base selectgroup = util.creatDefaultSelectGroup("一级子类", "oneSubClass", list, "id", "categoryName",false);
				param.add(selectgroup);
				selectgroup = util.creatDefaultSelectGroup("二级子类", "twoSubClass", null, "id", "categoryName",false);
				param.add(selectgroup);
				List<BaseData> portlist = baseDataService.getBaseDataByTypeId("02281125502140137");
				selectgroup = util.creatDefaultSelectGroup("港口/地区", "port", portlist, "id", "value",false);
				param.add(selectgroup);
				tb = InputGroup.getInGroup("比例尺(1:)", "scale", null, "请输入比例尺");
				param.add(tb);
				// 首版年月
				InputGroup TimeInput = InputGroup.getDatePickerYearMonth("首版年月", "firstVersionDate", null, "请选择首版年月");
				Prop prop = new Prop();
				prop.setPropKey("format");
				prop.setPropValue("yyyy-MM");
				TimeInput.addProp(prop);
				param.add(TimeInput);
				TimeInput = InputGroup.getDatePickerYearMonth("出版年月", "publicationDate", null, "请选择出版年月");
				param.add(TimeInput);
				//出版单位
				tb = InputGroup.getInGroup("出版单位", "publicationCompany", null, "请输入出版单位");
				param.add(tb);
				tb = InputGroup.getInGroup("&#12288版本号", "version", null, "请输入版本号");
				param.add(tb);
				// 获取印刷年月
				TimeInput = InputGroup.getDatePickerYearMonth("印刷年月", "printDate", null, "请选择印刷年月");
				param.add(TimeInput);
				//改正项号
				tb = InputGroup.getInGroup("改正项号", "correctNo", null, "请输入改正项号");
				param.add(tb);
				tb = InputGroup.getInGroup("存储位置", "savePlace", null, "请输入存储位置");
				param.add(tb);
				tb = InputGroup.getInGroup("库存总数", "total", null, "请输入库存总数");
				param.add(tb);
				Base root = util.createRoot();
				//经度范围
				tb = InputGroup.getInGroup("起始经度", "longitudeFrom",null, "请输入起始经度（东经）");
				param.add(tb);
				tb = InputGroup.getInGroup("终止经度", "longitudeTo", null, "请输入终止经度（西经）");
				param.add(tb);
				//纬度范围
				tb = InputGroup.getInGroup("起始纬度", "latitudeFrom", null, "请输入起始纬度（北纬）");
				param.add(tb);
				tb = InputGroup.getInGroup("终止纬度", "latitudeTo", null, "请输入终止纬度（南纬）");
				param.add(tb);
				//海图状态
				tb = InputGroup.getInGroup("海图状态", "seaMapStatus",null, "请输入海图状态");
				param.add(tb);
				//电子数据下载权限
				tb = InputGroup.getInGroup("下载权限", "downPermission", null, "请输入电子数据下载权限");
				param.add(tb);
				param.add(null);
				//备注
				InputGroup textArea = InputGroup.getTextAreaGroup("备&#12288&#12288注","remarks","5",null);
				param.add(textArea);
				//流转意见
				InputGroup flowSuggestion = InputGroup.getTextAreaGroup("流转意见","flowSuggestion","5",null);
				param.add(flowSuggestion);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "海图资料创建");
			}
			//返回节点字符串
			return editPage.getNode();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 
	 * 初始化资料附件维护页面
	 * @param bookId 图书id
	 * @return 节点字符串
	 */
	/*public String getFileListNode(String booksId){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "图书附件管理");
		*//** 创建按钮组行  开始*//*
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
		*//** 创建按钮组行  结束*//*
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace();
		*//** 创建Grid行  开始*//*
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12","12");
		// 创建Grid
		util.createGrid(columnGrid,"booksfile");
		*//** 创建Grid行  结束*//*
		// 将行加入到容器
		root.addChildNode(rowBg);
		root.addChildNode(rowSpace);
		root.addChildNode(rowGrid);
		// 添加下载按钮
		CssClass editCss = new CssClass("fa fa-cloud-download");
		I editI = I.getInstance(editCss);
		editCss = new CssClass("btn btn-success bk-margin-5");
		Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("downloadFile");
		tempelate.addProp(prop);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		
		InputHidden hidden = InputHidden.getInstance("booksId", booksId);
		root.addChildNode(hidden);
		return root.getNode() + script.getNode();
	}*/
	
	/**
	 * 初始化图书借阅页面
	 * @param bookInfo
	 * @param bookId
	 * @return
	 */
	public String getBorrowing(Books books) {
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		List<Base> param = new ArrayList<Base>();
		CssClass Css = new CssClass("form-control");
		Prop prop = new Prop();
		prop.setPropKey("readonly");
		prop.setPropValue("readonly");
		// 设置图书编号输入框
		String borrowBookNo = "图书编号";
		Span borrowBookNoSpan = Span.getDefault(borrowBookNo);
		TextBox borrowBookNoTb = TextBox.getInstance("code", "code", books.getCode(), Css, "请输入图书编号 ");
		borrowBookNoTb.addProp(prop);
		param.add(InputGroup.getInstance(borrowBookNoSpan,borrowBookNoTb));
		
		// 设置图书名称输入框
		String borrowBook = "图书名称";
		Span borrowBookNameSpan = Span.getDefault(borrowBook);
		TextBox borrowBookNameTb = TextBox.getInstance("borrowBookName", "borrowBookName", books.getChartName(), Css, "请输入图书名称 ");
		borrowBookNameTb.addProp(prop);
		param.add(InputGroup.getInstance(borrowBookNameSpan, borrowBookNameTb));
		
		/*// 设置图书名称输入框
		String borrowPerson = "借阅者";
		Span borrowPersonSpan = Span.getDefault(borrowPerson);
		TextBox borrowPersonTb = TextBox.getInstance("borrowPerson", "borrowPerson", null, Css, "请输入借阅者 ");
		param.add(InputGroup.getInstance(borrowPersonSpan, borrowPersonTb));
		
		// 设置借阅日期
		InputGroup tb = InputGroup.getDatePicker("借阅日期"+"&nbsp;&nbsp;", "borrowDate", null, null);
		param.add(tb);*/
		
		// 设置可借数量输入框
		String CanBorrowNo = "可借数量";
		Span canBorrowNoSpan = Span.getDefault(CanBorrowNo);
		TextBox canBorrowNoTb = TextBox.getInstance("canBorrowNo", "canBorrowNo", books.getCanBorrowing(), Css, "可借阅数量 ");
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
		util.createHeaderBar(editPage, "图书借阅");
		InputHidden hidden = InputHidden.getInstance("bookId", books.getId());
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
		util.createHeaderBar(root, "资料借阅申请表");
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
	 * 初始化图书归还页面
	 * @return
	 */
	public Object getReturn(Books books) {
		EditPage edit = new EditPage();
		FrontUtil util = FrontUtil.getInstance();
		List<Base> param = new ArrayList<Base>();

		CssClass Css = new CssClass("form-control");
		// 设置图书编号输入框
		String borrowBookNo = "图书编号";
		Span borrowBookNoSpan = Span.getDefault(borrowBookNo);
		TextBox borrowBookNoTb = TextBox.getInstance("BookNo", "BookNo", books.getChartNo() , Css, "请输入图书编号 ");
		Prop prop = new Prop();
		prop.setPropKey("readonly");
		prop.setPropValue("readonly");
		borrowBookNoTb.addProp(prop);
		param.add(InputGroup.getInstance(borrowBookNoSpan,borrowBookNoTb));
		
		// 设置图书名称输入框
		String borrowBook = "图书名称";
		Span borrowBookNameSpan = Span.getDefault(borrowBook);
		TextBox borrowBookNameTb = TextBox.getInstance("BookName", "BookName", books.getChartName() , Css, "请输入图书名称 ");
		prop = new Prop();
		prop.setPropKey("readonly");
		prop.setPropValue("readonly");
		borrowBookNameTb.addProp(prop);
		param.add(InputGroup.getInstance(borrowBookNameSpan, borrowBookNameTb));
		
		// 设置图书名称输入框
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
		util.createHeaderBar(editPage, "图书归还");
		//返回节点字符串
		return editPage.getNode();
	
	}
	
	/**
	 * 
	 * 初始化资料附件维护页面
	 * @param bookId 图书id
	 * @return 节点字符串
	 * @throws Exception 
	 */
	public String getFileListNode(UserService userService,BaseDataService baseDataService,BookFileService bookFileService,BooksService booksService,String booksId, List<DatumCategory> list,String mark,boolean flag) throws Exception{
		List<Base> param = new ArrayList<Base>();
		// 获取海图
		Books books = booksService.getBooks(booksId);
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "航海海图信息查看");
		util.createRowSpace(root);
		// 创建第一行
		Base row = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col4 = util.createColumn(row,"3","3", "3","0");
		//编码
		InputGroup input = InputGroup.getInGroup(true,"编&#12288&#12288码", "code",books.getCode(), null);
		col1.addChildNode(input);
		//图名
		input = InputGroup.getInGroup(true,"图&#12288&#12288名", "chartName",books.getChartName(), null);
		col2.addChildNode(input);
		//图号
		input = InputGroup.getInGroup(true,"图&#12288&#12288号", "chartNo",books.getChartNo(), null);
		col3.addChildNode(input);
		//港口/地区
		List<BaseData> portlist = baseDataService.getBaseDataByTypeId("11081036553200000");
		Base selectgroup = util.creatDefaultSelectGroup("港口/地区", "port", portlist, "id", "value",books.getPort());
		col4.addChildNode(selectgroup);
	
		
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col4 = util.createColumn(row,"3","3", "3","0");
		//一级子类
		selectgroup = util.creatDefaultSelectGroup("一级子类", "oneSubClass", list, "id", "categoryName",books.getOneSubClass());
		col1.addChildNode(selectgroup);
		selectgroup = util.creatDefaultSelectGroup("二级子类", "twoSubClass", list, "id", "categoryName",books.getTwoSubClass());
		col2.addChildNode(selectgroup);
		//比例尺
		input = InputGroup.getInGroup(true,"比例尺(1:)", "scale",books.getScale(), null);
		col3.addChildNode(input);
		//版本号
		input = InputGroup.getInGroup(true,"&#12288版本号", "version",books.getVersion(), null);
		col4.addChildNode(input);
				
	
		
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col4 = util.createColumn(row,"3","3", "3","0");
		//库存数量
		input = InputGroup.getInGroup(true,"库存数量", "stockNo",books.getStockNo(), null);
		col1.addChildNode(input);	
		//首版年月
		String date = "";
		if(books.getFirstVersionDate()!=null){
			date = (new SimpleDateFormat("yyyy-MM")).format(books.getFirstVersionDate());
		}
		input = InputGroup.getInGroup(true,"首版年月", "firstVersionDate",date, null);
		col2.addChildNode(input);	
		//改正项号
		input = InputGroup.getInGroup(true,"改正项号", "correctNo",books.getCorrectNo(), null);
		col3.addChildNode(input);
		//印刷年月
		String printDate = "";
		if(books.getPrintDate()!=null){
			printDate = (new SimpleDateFormat("yyyy-MM")).format(books.getPrintDate());
		}
		input = InputGroup.getInGroup(true,"印刷年月", "printDate",printDate, null);
		col4.addChildNode(input);
		
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col4 = util.createColumn(row,"3","3", "3","0");

		//出版日期
		String publicationDate="";
		if(books.getPublicationDate()!=null){
			publicationDate = new SimpleDateFormat("yyyy-MM").format(books.getPublicationDate());
		}
		input = InputGroup.getInGroup(true,"出版年月","publicationDate",publicationDate,null);
		col1.addChildNode(input);
		//出版单位
		input = InputGroup.getInGroup(true,"出版单位","publicationCompany",books.getPublicationCompany(),null);
		col2.addChildNode(input);
		//出版单位
		input = InputGroup.getInGroup(true,"存储位置","savePlace",books.getSavePlace(),null);
		col3.addChildNode(input);
		//海图状态
		input = InputGroup.getInGroup(true,"海图状态","seaMapStatus",books.getSeaMapStatus(),null);
		col4.addChildNode(input);

		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col4 = util.createColumn(row,"3","3", "3","0");
		//经度范围
		String starLongitude = "";
		if(StringUtils.isNotBlank(books.getLongitudeFrom())){
			starLongitude = books.getLongitudeFrom();
			String[] starLongitudeArray = starLongitude.split("°");
			Long result = Long.valueOf(starLongitudeArray[0])-180;
			if(result>=0){
				if(starLongitudeArray.length == 1){
					starLongitude = result+"°"+"E";
//					starLongitude = starLongitude.replace("°", "");
				}else{
					starLongitude = result+"°"+starLongitudeArray[1]+"E";
//					starLongitude = starLongitude.replace("°", "-");
//					if(starLongitudeArray[1].contains("″")){
//						starLongitude = starLongitude.replace("′", "-");
//						starLongitude = starLongitude.replace("″", "");
//					}else{
//						starLongitude = starLongitude.replace("′", "");
//					}
				}
			}else{
				if(starLongitudeArray.length == 1){
					starLongitude = Math.abs(result)+"°"+"W";
//					starLongitude = starLongitude.replace("°", "");
				}else{
					starLongitude = Math.abs(result)+"°"+starLongitudeArray[1]+"W";
//					starLongitude = starLongitude.replace("°", "-");
//					if(starLongitudeArray[1].contains("″")){
//						starLongitude = starLongitude.replace("′", "-");
//						starLongitude = starLongitude.replace("″", "");
//					}else{
//						starLongitude = starLongitude.replace("′", "");
//					}
				}
			}
		}
		String endLongitude = "";
		if(StringUtils.isNotBlank(books.getLongitudeTo())){
			endLongitude = books.getLongitudeTo();
			String[] endLongitudeArray = endLongitude.split("°");
			Long result = Long.valueOf(endLongitudeArray[0])-180;
			if(result>=0){
				if(endLongitudeArray.length == 1){
					endLongitude = result+"°"+"E";
//					endLongitude = endLongitude.replace("°", "");
				}else{
					endLongitude = result+"°"+endLongitudeArray[1]+"E";
//					endLongitude = endLongitude.replace("°", "-");
//					if(endLongitudeArray[1].contains("″")){
//						endLongitude = endLongitude.replace("′", "-");
//						endLongitude = endLongitude.replace("″", "");
//					}else{
//						endLongitude = endLongitude.replace("′", "");
//					}
				}
			}else{
				if(endLongitudeArray.length == 1){
					endLongitude = Math.abs(result)+"°"+"W";
//					endLongitude = endLongitude.replace("°", "");
				}else{
					endLongitude = Math.abs(result)+"°"+endLongitudeArray[1]+"W";
//					endLongitude = endLongitude.replace("°", "-");
//					if(endLongitudeArray[1].contains("″")){
//						endLongitude = endLongitude.replace("′", "-");
//						endLongitude = endLongitude.replace("″", "");
//					}else{
//						endLongitude = endLongitude.replace("′", "");
//					}
				}
			}
		}
		//纬度范围
		String starLatitude = "";
		if(StringUtils.isNotBlank(books.getLatitudeFrom())){
			starLatitude = books.getLatitudeFrom();
			String[] starLatitudeArray = starLatitude.split("°");
			Long result = Long.valueOf(starLatitudeArray[0])-90;
			if(result>=0){
				if(starLatitudeArray.length == 1){
					starLatitude = result+"°"+"N";
//					starLatitude = starLatitude.replace("°", "");
				}else{
					starLatitude = result+"°"+starLatitudeArray[1]+"N";
//					starLatitude = starLatitude.replace("°", "-");
//					if(starLatitudeArray[1].contains("″")){
//						starLatitude = starLatitude.replace("′", "-");
//						starLatitude = starLatitude.replace("″", "");
//					}else{
//						starLatitude = starLatitude.replace("′", "");
//					}
				}
			}else{
				if(starLatitudeArray.length == 1){
					starLatitude = Math.abs(result)+"°"+"S";
//					starLatitude = starLatitude.replace("°", "");
				}else{
					starLatitude = Math.abs(result)+"°"+starLatitudeArray[1]+"S";
//					starLatitude = starLatitude.replace("°", "-");
//					if(starLatitudeArray[1].contains("″")){
//						starLatitude = starLatitude.replace("′", "-");
//						starLatitude = starLatitude.replace("″", "");
//					}else{
//						starLatitude = starLatitude.replace("′", "");
//					}
				}
			}
		}
		String endLatitude = "";
		if(StringUtils.isNotBlank(books.getLatitudeTo())){
			endLatitude = books.getLatitudeTo();
			String[] endLatitudeArray = endLatitude.split("°");
			Long result = Long.valueOf(endLatitudeArray[0])-90;
			if(result>=0){
				if(endLatitudeArray.length == 1){
					endLatitude = result+"°"+"N";
//					endLatitude = endLatitude.replace("°", "");
				}else{
					endLatitude = result+"°"+endLatitudeArray[1]+"N";
//					endLatitude = endLatitude.replace("°", "-");
//					if(endLatitudeArray[1].contains("″")){
//						endLatitude = endLatitude.replace("′", "-");
//						endLatitude = endLatitude.replace("″", "");
//					}else{
//						endLatitude = endLatitude.replace("′", "");
//					}
				}
			}else{
				if(endLatitudeArray.length == 1){
					endLatitude = Math.abs(result)+"°"+"S";
//					endLatitude = endLatitude.replace("°", "");
				}else{
					endLatitude = Math.abs(result)+"°"+endLatitudeArray[1]+"S";
//					endLatitude = endLatitude.replace("°", "-");
//					if(endLatitudeArray[1].contains("″")){
//						endLatitude = endLatitude.replace("′", "-");
//						endLatitude = endLatitude.replace("″", "");
//					}else{
//						endLatitude = endLatitude.replace("′", "");
//					}
				}
			}
		}
		//经度范围起
		input = InputGroup.getInGroup(true,"起始经度", "longitudeFrom",starLongitude, null);
		col1.addChildNode(input);	
		//经度范围止
		input = InputGroup.getInGroup(true,"终止经度","longitudeTo",endLongitude,null);
		col2.addChildNode(input);

		//纬度范围起
		input = InputGroup.getInGroup(true,"起始纬度", "latitudeFrom",starLatitude, null);
		col3.addChildNode(input);	
		//纬度范围止
		input = InputGroup.getInGroup(true,"终止纬度","latitudeTo",endLatitude,null);
		col4.addChildNode(input);
		
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col3 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		col4 = util.createColumn(row,"3","3", "3","0");
		
		//录入日期
		date = "";
		if(books.getCreationDate()!=null){
			date = new SimpleDateFormat("yyyy-MM").format(books.getCreationDate());
		}
		input = InputGroup.getInGroup(true,"录入日期","notesDate",date,null);
		col1.addChildNode(input);
		//电子数据下载权限
		input = InputGroup.getInGroup(true,"下载权限","downPermission",books.getDownPermission(),null);
		col2.addChildNode(input);
				
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"12","12", "12","0");
				
		//备注
		input = InputGroup.getInGroup(true,"备&#12288&#12288注","remarks",books.getRemarks(),null);
		col1.addChildNode(input);
		
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"12","12", "12","0");
				
		//流转意见
		input = InputGroup.getInGroup(true,"流转意见","flowSuggestion",books.getFlowSuggestion(),null);
		col1.addChildNode(input);
				
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建第二行
		row = util.createRow(root);
		// 创建列
		col1 = util.createColumn(row,"12","12", "12","0");
				
		//附件
		CssClass css = new CssClass("panel-title");
		Span span = Span.getInstance(null, "附	件:");
		Prop prop = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("font-size:15px;color:black");
		span.addProp(prop);
		col1.addChildNode(span);
		col1.addChildNode(util.createRowSpace());
		//下载附件
		BookFile bookFile = new BookFile();
		bookFile.setBookId(booksId);
		String bookFileParam = DataConverter.convertObject2Json(bookFile);
		List<BookFile> bookFilelist = bookFileService.getFileByBookId(bookFileParam);
		if(bookFilelist.size()>0){
			for(BookFile i :bookFilelist){
				String fileDown ="";
				fileDown += i.getFileName().toString()+"	"+createDownButton(i.getId(),flag);
				// 创建第二行
				row = util.createRow(root);
				// 创建列
				col1 = util.createColumn(row,"12","12", "12","0");
				span = Span.getInstance(css,fileDown);
				col1.addChildNode(span);
				col1.addChildNode(util.createRowSpace());
			}
		}else{
			span = Span.getInstance(null, "暂无附件");
			prop = new Prop();
			prop.setPropKey("style");
			prop.setPropValue("font-size:15px;color:black");
			span.addProp(prop);
			col1.addChildNode(span);
			col1.addChildNode(util.createRowSpace());
		}
		//添加1个行间距
		root.addChildNode(util.createRowSpace());
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base column = util.createColumn(rowBg, "10", "10");
		// 创建按钮组
		// 构建创建div
		css = new CssClass("btn btn-default");
		Button button = Button.getButtonWithIcon("back", css, "返回",null);
		column.addChildNode(button);
		InputHidden hiddenId = InputHidden.getInstance("mark",mark);
		root.addChildNode(hiddenId);
		return root.getNode();
	}
	
	public String createDownButton(String id,boolean flag){
		Base a = A.getInstance("", "下载");
		Prop Prop = new Prop();
		Prop.setPropKey("href");
		Prop.setPropValue("javascript:void(0)");
		a.addProp(Prop);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue(id);
		a.addProp(prop);
		Prop prop1 = new Prop();
		prop1.setPropKey("style");
		prop1.setPropValue("color:blue;padding-left: 10px;");
		a.addProp(prop1);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("down(this)");
		a.addProp(prop);
		Base b = null;
		if(flag){
			b = A.getInstance("", "删除");
			Prop Prop1 = new Prop();
			Prop1.setPropKey("href");
			Prop1.setPropValue("javascript:void(0)");
			b.addProp(Prop1);
			Prop prop2 = new Prop();
			prop2.setPropKey("name");
			prop2.setPropValue(id);
			b.addProp(prop2);
			Prop prop3 = new Prop();
			prop3.setPropKey("style");
			prop3.setPropValue("color:red;padding-left: 10px;");
			b.addProp(prop3);
			prop = new Prop();
			prop.setPropKey("onclick");
			prop.setPropValue("remove(this)");
			b.addProp(prop);
		}
		String bNode = "";
		if(b != null){
			bNode = b.getNode();
		} 
		return a.getNode()+bNode;
	}
	
	
}
