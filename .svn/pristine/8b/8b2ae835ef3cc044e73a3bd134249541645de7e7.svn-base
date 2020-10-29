package com.ht.front.pages.datum.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;

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
import com.ht.front.model.Select;
import com.ht.front.model.Span;
import com.ht.front.model.TextBox;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.datum.DatumFileType;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.datum.datum.DatumFileService;

public class DatumFilePage {
	
	/**
	 * 
	 * 初始化资料维护页面
	 * @param 
	 * @return 节点字符串
	 */
	public String getListNode(){
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "资料维护");
		util.createRowSpace(root);
		/** 创建按钮组行  开始*/
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建按钮组
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		// 构建创建div
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success btn-setting search");
		Button button = Button.getButtonWithIcon("add", css, "创建", i);
		column.addChildNode(button);
		
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		/** 创建按钮组行  结束*/
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		Base rowGrid = util.createGrid(root,"datumfile");
		/** 创建Grid行  结束*/
		// 添加查看文件夹按钮
		CssClass foldCss = new CssClass("fa fa-folder");
		I foldI = I.getInstance(foldCss);
		foldCss = new CssClass("btn btn-info bk-margin-5");
		Button fold = Button.getButtonWithIcon(null, foldCss, null, foldI);
		Prop foldprop = new Prop();
		foldprop.setPropKey("name");
		foldprop.setPropValue("folderDatumFile");
		fold.addProp(foldprop);
		
		// 添加查看文档属性按钮
		CssClass bookCss = new CssClass("fa fa-book");
		I bookI = I.getInstance(bookCss);
		bookCss = new CssClass("btn btn-info bk-margin-5");
		Button book = Button.getButtonWithIcon(null, bookCss, null, bookI);
		Prop bookprop = new Prop();
		bookprop.setPropKey("name");
		bookprop.setPropValue("bookDatumFile");
		book.addProp(bookprop);
		Script lookscript = Script.getInstance("lookTemplate");
		lookscript.addChildNode(fold);
		lookscript.addChildNode(book);
		
		// 添加操作按钮
		// 上传
		CssClass uploadCss = new CssClass("fa  fa-cloud-upload");
		I uploadI = I.getInstance(uploadCss);
		uploadCss = new CssClass("btn btn-primary bk-margin-5");
		Button uploadTempelate = Button.getButtonWithIcon(null, uploadCss, "&nbsp;"+"上传", uploadI);
		Prop uploadProp = new Prop();
		uploadProp.setPropKey("name");
		uploadProp.setPropValue("uploadDatumFile");
		uploadTempelate.addProp(uploadProp);
		uploadProp = new Prop();
		uploadProp.setPropKey("title");
		uploadProp.setPropValue("上传");
		uploadTempelate.addProp(uploadProp);
		
		// 下载
		CssClass downloadCss = new CssClass("fa  fa-cloud-download");
		I downloadI = I.getInstance(downloadCss);
		downloadCss = new CssClass("btn btn-success bk-margin-5");
		Button downloadTempelate = Button.getButtonWithIcon(null, downloadCss, "&nbsp;"+"下载", downloadI);
		Prop downloadProp = new Prop();
		downloadProp.setPropKey("name");
		downloadProp.setPropValue("downloadDatumFile");
		downloadTempelate.addProp(downloadProp);
		downloadProp = new Prop();
		downloadProp.setPropKey("title");
		downloadProp.setPropValue("下载");
		downloadTempelate.addProp(downloadProp);
		
		// 借阅
		CssClass borrowCss = new CssClass("fa fa-plus-square");
		I borrowI = I.getInstance(borrowCss);
		borrowCss = new CssClass("btn btn-warning bk-margin-5");
		Button borrowTempelate = Button.getButtonWithIcon(null, borrowCss, "&nbsp;"+"借阅", borrowI);
		Prop borrowProp = new Prop();
		borrowProp.setPropKey("name");
		borrowProp.setPropValue("borrowDatumFile");
		borrowTempelate.addProp(borrowProp);
		borrowProp = new Prop();
		borrowProp.setPropKey("title");
		borrowProp.setPropValue("借阅");
		borrowTempelate.addProp(borrowProp);
		
		// 归档
		CssClass returnCss = new CssClass("fa fa-minus-square");
		I returnI = I.getInstance(returnCss);
		returnCss = new CssClass("btn btn-success bk-margin-5");
		Button returnTempelate = Button.getButtonWithIcon(null, returnCss, "&nbsp;"+"归还", returnI);
		Prop returnProp = new Prop();
		returnProp.setPropKey("name");
		returnProp.setPropValue("returnDatumFile");
		returnTempelate.addProp(returnProp);
		returnProp = new Prop();
		returnProp.setPropKey("title");
		returnProp.setPropValue("归档");
		returnTempelate.addProp(returnProp);
		
		// 删除
		CssClass deleteCss = new CssClass("fa fa-trash-o");
		I deleteI = I.getInstance(deleteCss);
		deleteCss = new CssClass("btn btn-danger btn-setting bk-margin-5");
		Button deleteTempelate = Button.getButtonWithIcon(null, deleteCss, "&nbsp;"+"删除", deleteI);
		Prop deleteProp = new Prop();
		deleteProp.setPropKey("name");
		deleteProp.setPropValue("deleteDatumFile");
		deleteTempelate.addProp(deleteProp);
		deleteProp = new Prop();
		deleteProp.setPropKey("title");
		deleteProp.setPropValue("删除");
		deleteTempelate.addProp(deleteProp);
		
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
		InputHidden CategoryIdHidden = InputHidden.getInstance("categoryId", null);
		Prop cateProp = new Prop();
		cateProp.setPropKey("name");
		cateProp.setPropValue("categoryId");
		CategoryIdHidden.addProp(cateProp);
		form.addChildNode(CategoryIdHidden);
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
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(uploadTempelate);
		script.addChildNode(downloadTempelate);
		script.addChildNode(borrowTempelate);
		script.addChildNode(returnTempelate);
		script.addChildNode(deleteTempelate);
		return root.getNode()+lookscript.getNode()+script.getNode();
	}
	
	/**
	 * 初始化编辑资料文档信息页面
	 * @param datumFileList 
	 * @return 节点字符串
	 * */
	public String getEditNode(String flag, DatumFileService datumFileService,List<DatumCategory> datumCatagoryList, String id, List<DatumFile> datumFileList) throws Exception {
		EditPage edit = new EditPage();
		Base editPage = null;
		if(id!=null){
			// 创建前端工具实例
			FrontUtil util = FrontUtil.getInstance();
			List<Base> param = new ArrayList<Base>();
			// 创建一个容器
			Base root = util.createRoot();
			util.createHeaderBar(root, "资料文件编辑");
			// 创建form表单
			CssClass formCss = new CssClass("form-search");
			Form form = Form.getInstance("importForm",formCss,null);
			
			Prop formprop = new Prop();
			formprop.setPropKey("method");
			formprop.setPropValue("post");
			
			Prop formprops = new Prop();
			formprops.setPropKey("name");
			formprops.setPropValue("upload");
			
			Prop formProp = new Prop();
			formProp.setPropKey("enctype");
			formProp.setPropValue("multipart/form-data");
			
			form.addProp(formprop);
			form.addProp(formprops);
			form.addProp(formProp);
			root.addChildNode(form);
			
			CssClass cclass = new CssClass("row");
			cclass = new CssClass("col-md-12 col-xs-12 col-sm-12");
			
			DatumFileType datumFileType = new DatumFileType();
			DatumFileType datumFileType2 = new DatumFileType();
			
			datumFileType.setFileType("实体文档");
			datumFileType2.setFileType("电子文档");
			List<DatumFileType> list = new ArrayList<DatumFileType>();
			list.add(datumFileType);
			list.add(datumFileType2);
			
			String fileType = "文档类型";
			Span span = Span.getDefault(fileType);
			Select select = Select.getDefaultWithOption("FileTypeId", list, "id", "fileType");
			Prop prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("FileTypeId");
			select.addProp(prop);
			param.add(InputGroup.getInstance(span, select));
			
			String parentId = "上级资料类别";
			Span parentIdSpan = Span.getDefault(parentId);
			Select parentIdSelect = Select.getDefaultWithOption("parentId", datumCatagoryList, "id", "categoryName");
			Prop parentIdProp = new Prop();
			parentIdProp.setPropKey("name");
			parentIdProp.setPropValue("FileTypeId");
			parentIdSelect.addProp(parentIdProp);
			param.add(InputGroup.getInstance(parentIdSpan, parentIdSelect));
			
			/** 创建按钮组行  开始*/
			// 创建行
			Base rowBg = util.createRow();
			// 创建列
			Base column = util.createColumn(rowBg, "12","12");
			// 创建按钮组
			// 构建行div
			String businessName = "资料类别";
			CssClass css = new CssClass("row");
			util.createRow(column);
			css = new CssClass("col-md-12 col-xs-12 col-sm-12");
			Div d = Div.getInstance(null, css, null);
			// 构建创建div
			css = new CssClass("fa fa-plus");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-success bk-margin-1 btn-setting select");
			Button button = Button.getButtonWithIcon("add", css, "选择"+businessName, i);
			d.addChildNode(button);
			param.add(d);
			CssClass Css = new CssClass("form-control");
			// 设置备注输入框
			String remarksText = "备注";
			Span remarksSpan = Span.getDefault(remarksText);
			TextBox remarksTb = TextBox.getInstance("remarks", "remarks", null, Css, "请输入备注 ");
			param.add(InputGroup.getInstance(remarksSpan, remarksTb));
			// 创建文档导入input框
			CssClass fileCss = new CssClass("file-input-style");
			File file = File.getInstance("uploadFile",fileCss);
			param.add(file);
			Prop fileProp = new Prop();
			fileProp.setPropKey("name");
			fileProp.setPropValue("upload");
			file.addProp(fileProp);
			
			// 设置实体文档名输入框
			String entityFileNameText = "实体文档名";
			Span entityFileNameSpan = Span.getDefault(entityFileNameText);
			TextBox entityFileNameTb = TextBox.getInstance("entityFileName", "entityFileName", null, Css, "请输入实体文档名 ");
			param.add(InputGroup.getInstance(entityFileNameSpan, entityFileNameTb));
			// 设置实体文档数量输入框
			String entityFileNumText = "实体文档数量";
			Span entityFileNumSpan = Span.getDefault(entityFileNumText);
			TextBox entityFileNumTb = TextBox.getInstance("entityFileNum", "entityFileNum", null, Css, "请输入实体文档数量 ");
			param.add(InputGroup.getInstance(entityFileNumSpan, entityFileNumTb));
			/** 创建Modal Dialog 开始*/
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
			// 创建footer
			CssClass footerCss = new CssClass("modal-footer");
			Div footerDiv = Div.getInstance("select", footerCss, null);
			contentDiv.addChildNode(footerDiv);
			
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
			CssClass title = new CssClass("modal-title bk-fg-primary model-custom");
			Div titleDiv = Div.getInstance(null, title, "选择资料类别");
			headerDiv.addChildNode(titleDiv);
			
			// 创建form表单
			CssClass formCssTree = new CssClass("form-search tree-font-size");
			Form formTree = Form.getInstance("select",formCssTree,null);
			Prop formPropTree = new Prop();
			formPropTree.setPropKey("method");
			formPropTree.setPropValue("post");
			Prop formPropsTree = new Prop();
			formPropTree.setPropKey("enctype");
			formPropTree.setPropValue("multipart/form-data");
			formTree.addProp(formPropTree);
			formTree.addProp(formPropsTree);
			headerDiv.addChildNode(formTree);
			
			// 构建确定按钮
			CssClass importCss = new CssClass("import-submit btn btn-primary");
			Button importBtn = Button.getInstance("importSubmit", importCss, "确定");
			Prop importProp = new Prop();
			importProp.setPropKey("data-dismiss");
			importProp.setPropValue("modal");
			importBtn.addProp(importProp);
			headerDiv.addChildNode(importBtn);
			/** Modal Dialog 结束*/
			root.addChildNode(modalDiv);
			// 设置资料类别的隐藏域
			InputHidden hidden = InputHidden.getInstance("dCataParentName", id);

			editPage = edit.createEditPage(param);
			editPage.addChildNode(hidden);
			form.addChildNode(editPage);
			return root.getNode();
		}else{
			// 创建前端工具实例
			FrontUtil util = FrontUtil.getInstance();
			List<Base> param = new ArrayList<Base>();
			// 创建一个容器
			Base root = util.createRoot();
			util.createHeaderBar(root, "资料文件创建");
			// 创建form表单
			CssClass formCss = new CssClass("form-search");
			Form form = Form.getInstance("importForm",formCss,null);
			
			Prop formprop = new Prop();
			formprop.setPropKey("method");
			formprop.setPropValue("post");
			
			Prop formprops = new Prop();
			formprops.setPropKey("name");
			formprops.setPropValue("upload");
			
			Prop formProp = new Prop();
			formProp.setPropKey("enctype");
			formProp.setPropValue("multipart/form-data");
			
			form.addProp(formprop);
			form.addProp(formprops);
			form.addProp(formProp);
			root.addChildNode(form);
			
			CssClass cclass = new CssClass("row");
			cclass = new CssClass("col-md-12 col-xs-12 col-sm-12");
			
			DatumFileType datumFileType = new DatumFileType();
			DatumFileType datumFileType2 = new DatumFileType();
			
			datumFileType.setFileType("实体文档");
			datumFileType2.setFileType("电子文档");
			List<DatumFileType> list = new ArrayList<DatumFileType>();
			list.add(datumFileType);
			list.add(datumFileType2);
			
			String fileType = "文档类型";
			Span span = Span.getDefault(fileType);
			Select select = Select.getDefaultWithOption("FileTypeId", list, "id", "fileType");
			Prop prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("FileTypeId");
			select.addProp(prop);
			param.add(InputGroup.getInstance(span, select));
			
			String parentId = "上级资料类别";
			Span parentIdSpan = Span.getDefault(parentId);
			Select parentIdSelect = Select.getDefaultWithOption("parentId", datumCatagoryList, "id", "categoryName");
			Prop parentIdProp = new Prop();
			parentIdProp.setPropKey("name");
			parentIdProp.setPropValue("FileTypeId");
			parentIdSelect.addProp(parentIdProp);
			param.add(InputGroup.getInstance(parentIdSpan, parentIdSelect));
			
			/** 创建按钮组行  开始*/
			// 创建行
			Base rowBg = util.createRow();
			// 创建列
			Base column = util.createColumn(rowBg, "12","12");
			// 创建按钮组
			// 构建行div
			String businessName = "资料类别";
			CssClass css = new CssClass("row");
			util.createRow(column);
			css = new CssClass("col-md-12 col-xs-12 col-sm-12");
			Div d = Div.getInstance(null, css, null);
			// 构建创建div
			css = new CssClass("fa fa-plus");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-success bk-margin-1 btn-setting select");
			Button button = Button.getButtonWithIcon("add", css, "选择"+businessName, i);
			d.addChildNode(button);
			param.add(d);
			CssClass Css = new CssClass("form-control");
			// 创建文档导入input框
			CssClass fileCss = new CssClass("file-input-style");
			File file = File.getInstance("uploadFile",fileCss);
			param.add(file);
			Prop fileProp = new Prop();
			fileProp.setPropKey("name");
			fileProp.setPropValue("upload");
			file.addProp(fileProp);
			// 设置备注输入框
			String remarksText = "备注";
			Span remarksSpan = Span.getDefault(remarksText);
			TextBox remarksTb = TextBox.getInstance("remarks", "remarks", null, Css, "请输入备注 ");
			param.add(InputGroup.getInstance(remarksSpan, remarksTb));
			
			// 设置实体文档名输入框
			String entityFileNameText = "实体文档名";
			Span entityFileNameSpan = Span.getDefault(entityFileNameText);
			TextBox entityFileNameTb = TextBox.getInstance("entityFileName", "entityFileName", null, Css, "请输入实体文档名 ");
			param.add(InputGroup.getInstance(entityFileNameSpan, entityFileNameTb));
			// 设置实体文档数量输入框
			String entityFileNumText = "实体文档数量";
			Span entityFileNumSpan = Span.getDefault(entityFileNumText);
			TextBox entityFileNumTb = TextBox.getInstance("entityFileNum", "entityFileNum", null, Css, "请输入实体文档数量 ");
			param.add(InputGroup.getInstance(entityFileNumSpan, entityFileNumTb));
			/** 创建Modal Dialog 开始*/
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
			// 创建footer
			CssClass footerCss = new CssClass("modal-footer");
			Div footerDiv = Div.getInstance("select", footerCss, null);
			contentDiv.addChildNode(footerDiv);
			
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
			CssClass title = new CssClass("modal-title bk-fg-primary model-custom");
			Div titleDiv = Div.getInstance(null, title, "选择资料类别");
			headerDiv.addChildNode(titleDiv);
			
			// 创建form表单
			CssClass formCssTree = new CssClass("form-search tree-font-size");
			Form formTree = Form.getInstance("select",formCssTree,null);
			Prop formPropTree = new Prop();
			formPropTree.setPropKey("method");
			formPropTree.setPropValue("post");
			Prop formPropsTree = new Prop();
			formPropTree.setPropKey("enctype");
			formPropTree.setPropValue("multipart/form-data");
			formTree.addProp(formPropTree);
			formTree.addProp(formPropsTree);
			headerDiv.addChildNode(formTree);
			
			// 构建确定按钮
			CssClass importCss = new CssClass("import-submit btn btn-primary");
			Button importBtn = Button.getInstance("importSubmit", importCss, "确定");
			Prop importProp = new Prop();
			importProp.setPropKey("data-dismiss");
			importProp.setPropValue("modal");
			importBtn.addProp(importProp);
			headerDiv.addChildNode(importBtn);
			/** Modal Dialog 结束*/
			root.addChildNode(modalDiv);
			// 设置资料类别的隐藏域
			InputHidden hidden = InputHidden.getInstance("dCataParentName", id);

			editPage = edit.createEditPage(param);
			editPage.addChildNode(hidden);
			form.addChildNode(editPage);
			return root.getNode();
		}
	}
	
		/**
		 * 初始化资料借阅页面
		 * @param datumFileService
		 * @param id
		 * @param borrowBookName
		 * @param datumCategoryId
		 * @param borr 
		 * @return
		 * @throws Exception
		 */
		public String getBorrowingNode(DatumFileService datumFileService,String fileId,String borrowBookName, String datumCategoryId,String flag, Borrowing borr) throws Exception {
			EditPage edit = new EditPage();
			FrontUtil util = FrontUtil.getInstance();
			List<Base> param = new ArrayList<Base>();
			if(flag == null) {
				InputGroup ig = InputGroup.getInGroup("图书编号", "borrowBookNo",fileId, "请输入图书编号 ");
				param.add(ig);
				ig = InputGroup.getInGroup("图书名称", "borrowBookName",borrowBookName, "请输入图书名称 ");
				param.add(ig);
				ig = InputGroup.getInGroup("借阅者", "borrowPerson",null, "请输入借阅者 ");
				param.add(ig);
				String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
				ig = InputGroup.getDatePicker("借阅日期：","borrowDate",date,"请选择借阅日期");
				param.add(ig);
				ig = InputGroup.getInGroup("借阅数量", "borrowNo",null, "请输入借阅数量 ");
				param.add(ig);
				ig = InputGroup.getInGroup("借阅天数", "borrowDays",null, "请输入借阅天数 ");
				param.add(ig);
				// 设置资料类别的隐藏域
				Base editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "图书借阅");
				// 设置文件夹Id隐藏域
				InputHidden folderId = InputHidden.getInstance("folderId", datumCategoryId);
				// 设置归还数量隐藏域
				InputHidden returnNo = InputHidden.getInstance("returnNo", "0");
				editPage.addChildNode(folderId);
				editPage.addChildNode(returnNo);
				//返回节点字符串
				return editPage.getNode();
			}else {
				CssClass Css = new CssClass("form-control");
				Prop prop = new Prop();
				prop.setPropKey("readonly");
				prop.setPropValue("readonly");
				// 设置图书编号输入框
				String borrowBookNo = "图书编号";
				Span borrowBookNoSpan = Span.getDefault(borrowBookNo);
				TextBox borrowBookNoTb = TextBox.getInstance("borrowBookNo", "borrowBookNo", borr.getBorrowCode(), Css, "请输入图书编号 ");
				borrowBookNoTb.addProp(prop);
				param.add(InputGroup.getInstance(borrowBookNoSpan,borrowBookNoTb));
				
				// 设置图书名称输入框
				String borrowBook = "图书名称";
				Span borrowBookNameSpan = Span.getDefault(borrowBook);
				TextBox borrowBookNameTb = TextBox.getInstance("borrowBookName", "borrowBookName", borr.getBorrowBookName(), Css, "请输入图书编号 ");
				borrowBookNameTb.addProp(prop);
				param.add(InputGroup.getInstance(borrowBookNameSpan, borrowBookNameTb));

				// 设置图书名称输入框
				String borrowPerson = "借阅者";
				Span borrowPersonSpan = Span.getDefault(borrowPerson);
				TextBox borrowPersonTb = TextBox.getInstance("borrowPerson", "borrowPerson", borr.getBorrowPerson(), Css, "请输入借阅者 ");
				borrowPersonTb.addProp(prop);
				param.add(InputGroup.getInstance(borrowPersonSpan, borrowPersonTb));

				// 设置借阅日期
				String borrowDate = "借阅日期";
				Span borrowDateSpan = Span.getDefault(borrowDate);
				TextBox borrowDateTb = TextBox.getInstance("borrowDate", "borrowDate", (borr.getBorrowDate()).toString(), Css, "请选择借阅日期 ");
				borrowDateTb.addProp(prop);
				param.add(InputGroup.getInstance(borrowDateSpan, borrowDateTb));
				
				// 设置借阅数量输入框
				String borrowNo = "借阅数量";
				Span borrowNoSpan = Span.getDefault(borrowNo);
				TextBox borrowNoTb = TextBox.getInstance("borrowNo", "borrowNo", borr.getBorrowNo(), Css, "请输入借阅数量 ");
				borrowNoTb.addProp(prop);
				param.add(InputGroup.getInstance(borrowNoSpan, borrowNoTb));

				/*// 设置借阅天数
				String borrowDays = "借阅天数";
				Span borrowDaysSpan = Span.getDefault(borrowDays);
				TextBox borrowDaysTb = TextBox.getInstance("borrowDays", "borrowDays", borr.getBorrowDays(), Css, "请输入借阅天数 ");
				borrowDaysTb.addProp(prop);
				param.add(InputGroup.getInstance(borrowDaysSpan, borrowDaysTb));*/
				
				InputGroup ig = InputGroup.getInGroup("归还数量", "returnNo", null, "请输入归还数量 ");
				param.add(ig);
				// 设置资料类别的隐藏域
				Base editPage = edit.createEditPage(param);
				util.createHeaderBar(editPage, "图书借阅");
				// 设置文件夹Id隐藏域
				InputHidden folderId = InputHidden.getInstance("folderId", datumCategoryId);
				editPage.addChildNode(folderId);
				// 设置id隐藏域
				InputHidden id = InputHidden.getInstance("id", borr.getId());
				editPage.addChildNode(id);
				//返回节点字符串
				return editPage.getNode();
			}
		}
		
		/**
		 * 初始化文件夹下的所有文档页面
		 * @param datumFileService
		 * @param id
		 * @return
		 */
		public Object getViewNode(DatumFileService datumFileService, String id) {
			// 创建前端工具实例
			FrontUtil util = FrontUtil.getInstance();
			// 创建一个容器
			Base root = util.createRoot();
			util.createHeaderBar(root, "文档管理");
			
			/** 创建Grid行  开始*/
			// 创建一个行间隔
			Base rowSpace = util.createRowSpace();
			// 创建行
			Base rowGrid = util.createRow();
			// 创建列
			Base columnGrid = util.createColumn(rowGrid, "12","12");
			// 创建Grid
			util.createGrid(columnGrid,"folder");
			/** 创建Grid行  结束*/
			// 将行加入到容器
			root.addChildNode(rowGrid);
			root.addChildNode(rowSpace);
			
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
			css = new CssClass("fa fa-plus");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-success bk-margin-5");
			Button button = Button.getButtonWithIcon("back", css, "返回", i);
			div.addChildNode(button);
			column.addChildNode(div);
			/** 创建按钮组行  结束*/
			root.addChildNode(rowBg);
			// 设置id隐藏域
			InputHidden categoryId = InputHidden.getInstance("categoryId", id);
			root.addChildNode(categoryId);
			return root.getNode();
		}
		
		/**
		 * 初始化文档属性页面
		 * @param datumFileService
		 * @param datumFile
		 * @return
		 */
		public Object getFileAttrNode(DatumFileService datumFileService,DatumFile datumFile) {
			if(datumFile.getFileType().equals("电子文档")){
				EditPage edit = new EditPage();
				List<Base> param = new ArrayList<Base>();
				CssClass Css = new CssClass("form-control");
				Prop prop = new Prop();
				prop.setPropKey("readonly");
				prop.setPropValue("readonly");
				// 设置文档名称
				String fileName = "文档名称";
				Span fileNameSpan = Span.getDefault(fileName);
				TextBox fileNameTb = TextBox.getInstance("fileName", "fileName", datumFile.getFileName(), Css, "");
				fileNameTb.addProp(prop);
				param.add(InputGroup.getInstance(fileNameSpan,fileNameTb));

				// 设置后缀名
				String suffixName = "后缀名";
				Span suffixNameSpan = Span.getDefault(suffixName);
				TextBox suffixNameTb = TextBox.getInstance("suffixName", "suffixName", datumFile.getSuffixName(), Css, "");
				suffixNameTb.addProp(prop);
				param.add(InputGroup.getInstance(suffixNameSpan, suffixNameTb));

				// 设置空间大小
				String spaceSize = "空间大小";
				Span spaceSizeSpan = Span.getDefault(spaceSize);
				TextBox spaceSizeTb = TextBox.getInstance("spaceSize", "spaceSize", datumFile.getSpaceSize(), Css, "");
				spaceSizeTb.addProp(prop);
				param.add(InputGroup.getInstance(spaceSizeSpan, spaceSizeTb));

				// 设置路径
				String filePath = "路径";
				Span filePathSpan = Span.getDefault(filePath);
				TextBox filePathTb = TextBox.getInstance("filePath", "filePath", datumFile.getFilePath(), Css, "");
				filePathTb.addProp(prop);
				param.add(InputGroup.getInstance(filePathSpan, filePathTb));

				// 设置备注
				String remarks = "备注";
				Span remarksSpan = Span.getDefault(remarks);
				TextBox remarksTb = TextBox.getInstance("remarks", "remarks", datumFile.getRemarks(), Css, "");
				remarksTb.addProp(prop);
				param.add(InputGroup.getInstance(remarksSpan, remarksTb));

				Base editPage = edit.createEditPage(param);
				//返回节点字符串
				return editPage.getNode();
			}else {
				EditPage edit = new EditPage();
				List<Base> param = new ArrayList<Base>();
				CssClass Css = new CssClass("form-control");
				Prop prop = new Prop();
				prop.setPropKey("readonly");
				prop.setPropValue("readonly");
				// 设置文档名称
				String entityFileName = "文档名称";
				Span entityFileNamepan = Span.getDefault(entityFileName);
				TextBox entityFileNameTb = TextBox.getInstance("entityFileName", "entityFileName", datumFile.getEntityFileName(), Css, "");
				entityFileNameTb.addProp(prop);
				param.add(InputGroup.getInstance(entityFileNamepan, entityFileNameTb));
				// 设置文档数量
				String entityFileNum = "文档数量";
				Span entityFileNumSpan = Span.getDefault(entityFileNum);
				TextBox entityFileNumTb = TextBox.getInstance("entityFileNum", "entityFileNum", datumFile.getEntityFileNum(), Css, "");
				entityFileNumTb.addProp(prop);
				param.add(InputGroup.getInstance(entityFileNumSpan, entityFileNumTb));
				Base editPage = edit.createEditPage(param);
				//返回节点字符串
				return editPage.getNode();
			}
		}
}
