package com.ht.front.pages.datum.correctionnoticebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
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
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.datum.bookinfo.BookFile;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.correctionnoticebook.CorrectionNoticeBook;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.organization.employee.UserService;
import com.ht.service.inter.datum.bookinfo.BookFileService;
import com.ht.service.inter.datum.correctionnoticebook.CorrectionNoticeBookService;


/**
 * 员工前台页面初始化类
 * */
public class CorrectionNoticeBookPage {
	FrontUtil util = FrontUtil.getInstance();
	/**
	 * 初始化人员列表数据页面
	 * @return 节点字符串
	 * */
	public String getListPage(List<String> ids) {
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "改正通告编辑资料管理");
		util.createRowSpace(root);
				
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
		}
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"notice");
		/** 创建Grid行  结束*/		
		// 添加操作按钮
		// 上传
		Button tempelate = null;
		Button uploadTempelate = null;
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
//		CssClass editCss = new CssClass("fa fa-eye");
//		I editI = I.getInstance(editCss);
//		editCss = new CssClass("btn btn-warning bk-margin-5");
//		Button viewFile = Button.getButtonWithIcon(null, editCss, null, editI);
//		Prop propView = new Prop();
//		propView.setPropKey("name");
//		propView.setPropValue("ViewFile");
//		viewFile.addProp(propView);
//		propView = new Prop();
//		propView.setPropKey("title");
//		propView.setPropValue("查看");
//		viewFile.addProp(propView);
//		propView = new Prop();
//		propView.setPropKey("onclick");
//		propView.setPropValue("viewPage(this)");
//		viewFile.addProp(propView);
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(uploadTempelate);
		script.addChildNode(tempelate);
//		script.addChildNode(viewFile);
		String roles =""; 
		if(ids != null){
			if(ids.size()>0){
				for (int j = 0; j < ids.size(); j++) {
					roles +=  "," +ids.get(j) ;
				}
				roles = roles.substring(1);
			}
		}
		InputHidden hidden = InputHidden.getInstance("roles", roles);
		root.addChildNode(hidden);
		
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
		Div titleDiv2 = Div.getInstance(null, titleCss2, "导入改正通告原始资料");
		headerDiv2.addChildNode(titleDiv2);
		// 创建form表单
		CssClass formCss2 = new CssClass("form-search");
		Form form2 = Form.getInstance("importInfoForm", formCss2, null);
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
		File file2 = File.getInstance("uploadInfoFile", fileCss2);
		Prop fileProp2 = new Prop();
		fileProp2.setPropKey("name");
		fileProp2.setPropValue("uploadInfo");
		file2.addProp(fileProp2);
		inputDiv2.addChildNode(file2);
		// 模板下载按钮
		CssClass tempCss2 = new CssClass("btn btn-primary btn-export");
		Button tempBtn2 = Button.getInstance("exportInfoTemplate", tempCss2, "下载模板");
		inputDiv2.addChildNode(tempBtn2);
		// 构建导入按钮
		CssClass importCss2 = new CssClass("import-submit btn btn-primary");
		Button importBtn2 = Button.getInstance("importInfoSubmit", importCss2, "导入");
		Prop importProp2 = new Prop();
		importProp2.setPropKey("data-dismiss");
		importProp2.setPropValue("modal");
		importBtn2.addProp(importProp2);
		headerDiv2.addChildNode(importBtn2);
		/** Modal Dialog 结束 */
		root.addChildNode(modalDiv);
		root.addChildNode(modalDiv2);
		return root.getNode() + script.getNode();
	}
	
	
	/**
	 * 初始化新增/编辑信息页面
	 * @param id 人员id
	 * @return 节点字符串
	 * @throws Exception 
	 * */
	@SuppressWarnings("deprecation")
	public String getEditPage(String id, CorrectionNoticeBookService correctionNoticeBookService,BaseDataService baseDataService) throws Exception {
		EditPage edit = new EditPage();
		Base editPage = null;
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		try{
			if(id != null){
				List<Base> param = new ArrayList<Base>();
				// 获取图书资料
				CorrectionNoticeBook bookInfo = correctionNoticeBookService.getCorrectionNoticeBook(id);
				
				InputGroup tb = InputGroup.getInGroup("资料来源", "source", bookInfo.getSource(), "请输入资料来源");
				List<BaseData> arrayList = baseDataService.getBaseDataByTypeId("04171410193260026");
				Base sourceList = util.creatDefaultSelectGroup("资料来源", "source", arrayList, "value", "value",bookInfo.getSource());
				param.add(sourceList);
				String titanic="";
				if(bookInfo.getTitanic()!=null&&bookInfo.getTitanic()!=""){
					titanic=bookInfo.getTitanic();
				}
				tb = InputGroup.getInGroup("文&#12288&#12288号", "titanic",titanic, "请输入文号");
				param.add(tb);
				Date date = bookInfo.getReceiveDate();
				if(date != null){
					String publishDate = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
					tb = InputGroup.getDatePicker("收到日期", "receiveDate", publishDate, null);
					param.add(tb);
				}else {
					tb = InputGroup.getDatePicker("收到日期", "receiveDate", null, null);
					param.add(tb);
				}
				tb = InputGroup.getInGroup("主要内容", "content", bookInfo.getContent(), "请输入主要内容");
				param.add(tb);
				/*tb = InputGroup.getInGroup("备&#12288&#12288注", "remark", bookInfo.getRemark(), "请输入备注");
				param.add(tb);*/
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "改正通告编辑资料编辑");
				InputHidden hidden = InputHidden.getInstance("id", id);
				editPage.addChildNode(hidden);
			    hidden = InputHidden.getInstance("state", bookInfo.getState());
				editPage.addChildNode(hidden);
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				List<Base> param = new ArrayList<Base>();
				// 获取图书资料
				InputGroup tb = InputGroup.getInGroup("资料来源", "source", null, "请输入资料来源");
				List<BaseData> arrayList = baseDataService.getBaseDataByTypeId("04171410193260026");
				 Base sourceList = util.creatDefaultSelectGroup("资料来源", "source", arrayList, "value", "value",false);
				param.add(sourceList);
				String titanic="";
				Date date=new Date();
				titanic=sdf.format(date)+"第（）号";
				tb = InputGroup.getInGroup("文&#12288&#12288号", "titanic", titanic, "请输入文号");
				param.add(tb);
				tb = InputGroup.getDatePicker("收到日期", "receiveDate", null, null);
				param.add(tb);
				tb = InputGroup.getInGroup("主要内容", "content", null, "请输入主要内容");
				param.add(tb);
//				tb = InputGroup.getInGroup("备&#12288&#12288注", "remark", null, "请输入备注");
//				param.add(tb);
				editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "改正通告编辑资料创建");
			}
			//返回节点字符串
			return editPage.getNode();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 查看初始化页面
	 * @param userService
	 * @param baseDataService
	 * @param bookFileService
	 * @param correctionNoticeBookService
	 * @param booksId
	 * @param list
	 * @param mark
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public String getFileListNode(UserService userService,
			BaseDataService baseDataService, BookFileService bookFileService,
			CorrectionNoticeBookService correctionNoticeBookService,
			String booksId, List<DatumCategory> list, String mark, boolean flag) throws Exception {
		List<Base> param = new ArrayList<Base>();
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建第一行
		Base row = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		Base col3 = util.createColumn(row,"3","3", "3","0");
		// 获取图书资料
		CorrectionNoticeBook bookInfo = correctionNoticeBookService.getCorrectionNoticeBook(booksId);
		
		InputGroup tb = null;
		List<BaseData> arrayList = baseDataService.getBaseDataByTypeId("04171410193260026");
		Base sourceList = util.creatDefaultSelectGroup("资料来源", "source", arrayList, "value", "value",bookInfo.getSource());
		col1.addChildNode(sourceList);
		String titanic="";
		if(bookInfo.getTitanic()!=null&&bookInfo.getTitanic()!=""){
			titanic=bookInfo.getTitanic();
		}
		tb = InputGroup.getInGroup("文&#12288&#12288号", "titanic",titanic, "");
		col2.addChildNode(tb);
		Date date = bookInfo.getReceiveDate();
		if(date != null){
			String publishDate = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
			tb = InputGroup.getDatePicker("收到日期", "receiveDate", publishDate, null);
			col3.addChildNode(tb);
		}else {
			tb = InputGroup.getDatePicker("收到日期", "receiveDate", null, null);
			col3.addChildNode(tb);
		}
		//新建一行
		row = util.createRow(root);
		// 创建列
		 col1 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		 col2 = util.createColumn(row,"3","3", "3","0");
		// 创建列
		 col3 = util.createColumn(row,"3","3", "3","0");
		tb = InputGroup.getInGroup("主要内容", "content", bookInfo.getContent(), "");
		col1.addChildNode(tb);
		tb = InputGroup.getInGroup("备&#12288&#12288注", "remark", bookInfo.getRemark(), "");
		col2.addChildNode(tb);
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