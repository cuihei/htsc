package com.ht.front.pages.complication.form;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.A;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.ButtonGroup;
import com.ht.front.model.Div;
import com.ht.front.model.File;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.Img;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Select;
import com.ht.front.model.Span;
import com.ht.front.model.Templete;
import com.ht.front.model.TextArea;
import com.ht.front.model.TextBox;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropBaseData;
import com.ht.persistence.model.system.issue.YearIssue;
import com.ht.persistence.model.system.symbol.Symbol;
import com.ht.persistence.model.system.workflow.process.ProcessFormProp;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.system.issue.YearIssueService;
import com.ht.service.inter.system.symbol.SymbolService;
import com.ht.workflow.form.delegate.FormDefaultValueDelegate;

import sun.misc.BASE64Decoder;

/**
 * 表属性前台页面初始化类
 */
public class FormValuePage
{
	/**
	 * 页面实例
	 */
	private static FormValuePage page = null;
	
	/**
	 * 获取页面实例
	 * @return
	 */
	public static FormValuePage getInstance()
	{
		if (page == null)
		{
			page = new FormValuePage();
		}
		return page;
	}

	/**
	 * 初始化表单编辑页面
	 * @return 节点字符串
	 * @throws Exception
	 */
	public String getInitForm(List<FormProp> formPropList, String formParam, SymbolService symbolService,BaseDataService baseDataService) throws Exception
	{

		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, formParam);
		// 创建一个div
		Base row = util.createRow(root);
		// 创建列
		Base column = util.createColumn(row, "12", "12", "12", null);
		List<Base> param = new ArrayList<Base>();
		CssClass titleCss = new CssClass("row");
		// 构件Div
		Div div = Div.getInstance(null, titleCss, null);
		column.addChildNode(div);
		// 创建form表单
		CssClass css = new CssClass("form");
		Form form = Form.getInstance("importFV", css, null);
		// form表单添加属性
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		form.addProp(formProp);
		formProp = new Prop();
		formProp.setPropKey("name");
		formProp.setPropValue("upload");
		form.addProp(formProp);
		formProp = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		form.addProp(formProp);
		div.addChildNode(form);
		if (formPropList != null)
		{
			for (int i = 0; i < formPropList.size(); i++)
			{
				String propType = formPropList.get(i).getPropType();
				String propKey = formPropList.get(i).getPropKey();
				String propName = formPropList.get(i).getPropName();
				String propDefValue = formPropList.get(i).getPropDefaultValue();
				String propRequired = formPropList.get(i).getRequired();
				String selectData = formPropList.get(i).getSelectData();
				String selectDic = formPropList.get(i).getSelectDic();
				List<BaseData> dics = null;
				if(StringUtils.isNotBlank(selectDic)){
					dics=baseDataService.getBaseDataByTypeId(selectDic);
				}
				Base propRow = getPropRow(propType, propKey, propName, propDefValue, null, propRequired, selectData, "是", null, symbolService,dics);
				param.add(propRow);
			}
		}
		form = createEditPage(form, param);
		InputHidden hiddenProp = InputHidden.getInstance("formProps", DataConverter.convertObject2Json(formPropList));
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("props");
		hiddenProp.addProp(prop);
		form.addChildNode(hiddenProp);
		// 返回节点字符串
		return root.getNode();
	}

	/**
	 * 初始化表单编辑页面
	 * @return 节点字符串
	 * @throws Exception
	 */
	public String getInitProcessForm(List<BaseData> dateList,List<ProcessFormProp> formPropList, String formParam, Map<String, Object> params, SymbolService symbolService,YearIssueService yearIssueService,BaseDataService baseDataService )
			throws Exception
	{

		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建一个div
		Base row = util.createRow(root);
		// 创建列
		Base column = util.createColumn(row, "12", "12", "12", null);
		List<Base> param = new ArrayList<Base>();
		CssClass titleCss = new CssClass("row");
		// 构件Div
		Div div = Div.getInstance(null, titleCss, null);
		column.addChildNode(div);
		// 创建form表单
		CssClass css = new CssClass("form");
		Form form = Form.getInstance("importFV", css, null);
		// form表单添加属性
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		form.addProp(formProp);
		formProp = new Prop();
		formProp.setPropKey("name");
		formProp.setPropValue("upload");
		form.addProp(formProp);
		formProp = new Prop();
		formProp.setPropKey("enctype");
		formProp.setPropValue("multipart/form-data");
		form.addProp(formProp);
		div.addChildNode(form);
		if (formPropList != null)
		{
			for (int i = 0; i < formPropList.size(); i++)
			{
				String propType = formPropList.get(i).getPropType();
				String propKey = formPropList.get(i).getPropKey();
				String propName = formPropList.get(i).getPropName();
				String propDefValue = formPropList.get(i).getDefaultValue();
				String propRequired = formPropList.get(i).getRequired();
				String editable = formPropList.get(i).getEditAble();
				String selectData = formPropList.get(i).getSelectData();
				String selectDic = formPropList.get(i).getSelectDic();
				if("tgqh".equals(propKey)||"gzqh".equals(propKey)){
					selectData="";
					List<YearIssue> list = yearIssueService.getAll();
					if(list!=null&&list.size()>0){
						for (YearIssue yearIssue : list) {
							if(selectData==""){
								selectData+=yearIssue.getIssue();
							}else{
								selectData+="，"+yearIssue.getIssue();
							}
						}
					}
				}
				List<BaseData> dics =null;
				if(StringUtils.isNotBlank(selectDic)){
					dics=baseDataService.getBaseDataByTypeId(selectDic);
				}
				String propDefDelegate = formPropList.get(i).getDefaultDelegate();
				Base propRow = getPropRow(propType, propKey, propName, propDefValue, propDefDelegate, propRequired, selectData, editable, params,
						symbolService,dics);
				param.add(propRow);
			}
		}
		
		form = createEditPage(form, param);
		
		InputHidden hiddenProp = InputHidden.getInstance("formProps", DataConverter.convertObject2Json(formPropList));
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("props");
		hiddenProp.addProp(prop);
		form.addChildNode(hiddenProp);
		 hiddenProp = InputHidden.getInstance("dateList", DataConverter.convertObject2Json(dateList));
		 root.addChildNode(hiddenProp);
		// 返回节点字符串
		return root.getNode();
	}

	private Base getPropRow(String propType, String propKey, String propName, String propDefValue, String propDefDelegate, String propRequired,
			String selectData, String editable, Map<String, Object> params, SymbolService symbolService,List<BaseData> dics) throws Exception
	{
		// 获取默认委托的返回值
		if (StringUtils.isNotEmpty(propDefDelegate))
		{
			FormDefaultValueDelegate delegate = new FormDefaultValueDelegate(propDefDelegate);
			Map<String, Object> defaultParam = delegate.getParams();
			// 将默认参数加入到参数里
			for (String key : defaultParam.keySet())
			{
				params.put(key, defaultParam.get(key));
			}
			// 设置委托参数
			delegate.setParams(params);
			try
			{
				Object value = delegate.excute();
				if (value != null)
				{
					propDefValue = value.toString();
				}
			}
			catch (Exception e)
			{
				LogHelper.ERROR.log(e.getMessage(), e);
			}
		}
		String[] selectStr = null;
		List<FormPropBaseData> fpList = new ArrayList<FormPropBaseData>();
		if(dics!=null&&dics.size()>0){
			for (BaseData data : dics) {
				FormPropBaseData fpbd = new FormPropBaseData();
				fpbd.setPropSelectKey(data.getValue());
				fpbd.setPropSelectValue(data.getValue());
				fpList.add(fpbd);
			}
		}else if (selectData != null)
		{
			selectStr = selectData.split("，");
			for (int j = 0; j < selectStr.length; j++)
			{
				FormPropBaseData fpbd = new FormPropBaseData();
				fpbd.setPropSelectKey(selectStr[j]);
				fpbd.setPropSelectValue(GenerateSequenceUtil.generateSequenceNo());
				fpList.add(fpbd);
			}
		}
		FrontUtil util = FrontUtil.getInstance();
		Base propRow = util.createRow();
		Base propCol = util.createColumn(propRow, "3", "3", "3", null);
		CssClass css = new CssClass("control-label");
		Div lable = Div.getInstance(null, css, propName + "：");
		if (propRequired != null)
		{
			if (editable.equals("是"))
			{
				if (propRequired.equals("是"))
				{
					Base span = Span.getInstance(new CssClass("required"), "*");
					lable.addChildNode(span);
				}
			}
		}
		Prop prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue(propName);
		lable.addProp(prop);
		propCol.addChildNode(lable);
		Base valueCol = util.createColumn(propRow, "8", "8", "8", null);
		css = new CssClass("form-control");
		Base valueControl = null;
		if (propType.contentEquals("text"))
		{
			if (propRequired.contentEquals(BaseDataConstants.REQUIRED_YES))
			{
				// 需要加*
				valueControl = TextBox.getInstance(propKey, propKey, propDefValue, css, null);
			}
			else
			{
				valueControl = TextBox.getInstance(propKey, propKey, propDefValue, css, null);
			}

		}
		else if (propType.equalsIgnoreCase("select"))
		{
			if (selectData != null)
			{
				valueControl = Select.getDefaultWithOption(propKey, fpList, "propSelectKey", "propSelectKey", propDefValue);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
			}
			else
			{
				valueControl = Select.getDefaultWithOption(propKey, null, "propSelectKey", "propSelectKey", propDefValue);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
			}
		}
		else if (propType.equalsIgnoreCase("kendoselect"))
		{
			if(dics!=null&&dics.size()>0){

				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "kendoselect");
				valueControl.addProp(prop);
				String list = "";
				for (int i = 0; i < dics.size(); i++) {

					if (i != 0)
					{
						list += ",{val:\"" + dics.get(i).getValue() + "\"}";
					}
					else if (i == 0)
					{
						list += "{val:\"" +  dics.get(i).getValue() + "\"}";
					}
				}
				propCol.addChildNode(createKendoSelect(propKey, null, list, false));
			
				
			}else if (selectData != null && selectStr.length > 0)
			{
				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "kendoselect");
				valueControl.addProp(prop);
				String list = "";
				for (int i = 0; i < selectStr.length; i++)
				{

					if (i != 0)
					{
						list += ",{val:\"" + selectStr[i] + "\"}";
					}
					else if (i == 0)
					{
						list += "{val:\"" + selectStr[i] + "\"}";
					}
				}
				propCol.addChildNode(createKendoSelect(propKey, null, list, false));
			}
			else
			{
				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "kendoselect");
				valueControl.addProp(prop);
				propCol.addChildNode(createKendoSelect(propKey, null, null, false));
			}
		}
		else if (propType.equalsIgnoreCase("multiselect"))
		{
			if(dics!=null&&dics.size()>0){

				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "multiselect");
				valueControl.addProp(prop);
				String list = "";
				for (int i = 0; i < dics.size(); i++) {

					if (i != 0)
					{
						list += ",{val:\"" + dics.get(i).getValue() + "\"}";
					}
					else if (i == 0)
					{
						list += "{val:\"" +  dics.get(i).getValue() + "\"}";
					}
				}
				propCol.addChildNode(createMultiSelect(propKey, null, list, false));
			
				
			}else if (selectData != null && selectStr.length > 0)
			{
				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "multiselect");
				valueControl.addProp(prop);
				String list = "";
				for (int i = 0; i < selectStr.length; i++)
				{
					
					if (i != 0)
					{
						list += ",{val:\"" + selectStr[i] + "\"}";
					}
					else if (i == 0)
					{
						list += "{val:\"" + selectStr[i] + "\"}";
					}
				}
				propCol.addChildNode(createMultiSelect(propKey, null, list, false));
			}
			else
			{
				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "multiselect");
				valueControl.addProp(prop);
				propCol.addChildNode(createMultiSelect(propKey, null, null, false));
			}
		}
		else if (propType.equalsIgnoreCase("imgselect"))
		{
			if (symbolService != null)
			{
				List<Symbol> symbolList = symbolService.getAllSymbolList();
				if (symbolList != null && symbolList.size() > 0)
				{
					valueControl = Templete.getInstance("input", null);
					prop = new Prop("id", propKey);
					valueControl.addProp(prop);
					prop = new Prop("name", propKey);
					valueControl.addProp(prop);
					prop = new Prop("class", "imgselect");
					valueControl.addProp(prop);
					prop = new Prop("style", "width:200px");
					valueControl.addProp(prop);
					String list = "";
					for (int i = 0; i < symbolList.size(); i++)
					{
						String rootPath = FileUtil.ROOT_PATH;
						java.io.File file = new java.io.File(rootPath + symbolList.get(i).getImgUrl());
						if (!file.exists())
						{
							if(symbolList.get(i)!=null&&symbolList.get(i).getImg()!=null&&symbolList.get(i).getImg()!=""){
								// 新建一个路径
								String path = "\\upload\\symbol\\";
								BASE64Decoder decoder = new BASE64Decoder();
								// Base64解码
								byte[] bytes = decoder.decodeBuffer(symbolList.get(i).getImg());
								if(bytes!=null&&bytes.length!=0){
									for (int y = 0; y < bytes.length; ++y)
									{
										if (bytes[y] < 0)
										{
											// 调整异常数据
											bytes[y] += 256;
										}
									}
									
									// 创建文件夹
							 		FileUtil.CreateFolder(rootPath+path);
									// 生成jpeg图片
									OutputStream out = new FileOutputStream(rootPath + symbolList.get(i).getImgUrl());
									out.write(bytes);
									out.flush();
									out.close();
								}else{
									continue;
								}
							}else{
								continue;
							}
						}
						if (i != 0)
						{
							list += ",{image:\"" + symbolList.get(i).getImgUrl().replace("\\", "/") + "\",val:\"" + symbolList.get(i).getCode()
									+ "\"}";
						}
						else if (i == 0)
						{
							list += "{image:\"" + symbolList.get(i).getImgUrl().replace("\\", "/") + "\",val:\"" + symbolList.get(i).getCode()
									+ "\"}";
						}
					}
					propCol.addChildNode(createKendoSelect(propKey, null, list, true));
				}

			}
			else
			{
				valueControl = Templete.getInstance("input", null);
				prop = new Prop("id", propKey);
				valueControl.addProp(prop);
				prop = new Prop("name", propKey);
				valueControl.addProp(prop);
				prop = new Prop("class", "kendoselect");
				valueControl.addProp(prop);
				propCol.addChildNode(createKendoSelect(propKey, null, null, false));
			}
		}
		else if (propType.equalsIgnoreCase("textArea"))
		{
			valueControl = TextArea.getInstance(propKey, css, "10", propDefValue);
			prop = new Prop("name", propKey);
			valueControl.addProp(prop);
		}
		else if (propType.equalsIgnoreCase("file"))
		{
			// 上传文件
			valueControl = Templete.getInstance("input", null);
			prop = new Prop();
			prop.setPropKey("type");
			prop.setPropValue("file");
			valueControl.addProp(prop);
			prop.setPropKey("name");
			prop.setPropValue("files");
			valueControl.addProp(prop);
			prop.setPropKey("id");
			prop.setPropValue(propKey + "fjs");
			valueControl.addProp(prop);
			prop.setPropKey("onchange");
			prop.setPropValue("getFileName(this)");
			valueControl.addProp(prop);
			prop.setPropKey("style");
			prop.setPropValue("display:none");
			valueControl.addProp(prop);
			A a = A.getInstance("", propName);
			prop.setPropKey("id");
			prop.setPropValue(propKey);
			a.addProp(prop);
			prop.setPropKey("style");
			prop.setPropValue("color:blue;cursor: pointer;");
			a.addProp(prop);
			if (editable.equals("是"))
			{
				prop.setPropKey("onclick");
				prop.setPropValue("handleFile(this)");
				a.addProp(prop);
			}
			valueControl.addChildNode(a);
		}
		if (propType.equalsIgnoreCase("img"))
		{
			// 上传图片
			css = new CssClass("input-group");
			valueControl = Div.getInstance(null, css, null);
			Img img = Img.getInstance(null, null, null);
			prop = new Prop();
			prop.setPropKey("src");
			String src = "../../ht/ht/upload/images/uploadpic.png";
			prop.setPropValue(src);
			img.addProp(prop);
			prop = new Prop();
			prop.setPropKey("onclick");
			prop.setPropValue("onclic(this)");
			img.addProp(prop);
			prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("upload");
			img.addProp(prop);
			valueControl.addChildNode(img);

			img = Img.getInstance(null, null, null);
			prop = new Prop();
			prop.setPropKey("style");
			prop.setPropValue("cursor:pointer");
			img.addProp(prop);
			prop = new Prop();
			prop.setPropKey("onclick");
			prop.setPropValue("onclic(this)");
			img.addProp(prop);
			prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("myImg");
			img.addProp(prop);
			valueControl.addChildNode(img);

			Templete input = Templete.getInstance("input", null);
			prop = new Prop();
			prop.setPropKey("type");
			prop.setPropValue("file");
			input.addProp(prop);
			prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("formImgUpload");
			input.addProp(prop);
			prop = new Prop();
			prop.setPropKey("onchange");
			prop.setPropValue("changeImg(this)");
			input.addProp(prop);
			prop = new Prop();
			prop.setPropKey("style");
			prop.setPropValue("display:none");
			input.addProp(prop);
			valueControl.addChildNode(input);
		}
		valueCol.addChildNode(valueControl);
		if (editable.equals("否"))
		{
			prop = new Prop("readonly", "readonly");
			valueControl.addProp(prop);
		}
		return propRow;
	}

	/**
	 * 初始form表单列表数据页面
	 * @return 节点字符串
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public String getListNode(List<Map<String, String>> maps,String formName,String delegate, Map<String, Object> params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, formName);
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		// 创建Grid
		util.createGrid(root, "formPropValue");
		/** 创建Grid行 结束 */
		/** 创建Modal Dialog 开始 */
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
		Div titleDiv = Div.getInstance(null, titleCss, "导入表单数据");
		headerDiv.addChildNode(titleDiv);
		// 创建form表单
		CssClass formCss = new CssClass("form-search");
		Form form = Form.getInstance("importFormValue", formCss, null);
		Prop formProp = new Prop();
		formProp.setPropKey("method");
		formProp.setPropValue("post");
		form.addProp(formProp);
		Prop formProps = new Prop();
		formProps.setPropKey("enctype");
		formProps.setPropValue("multipart/form-data");
		form.addProp(formProps);
		headerDiv.addChildNode(form);
		// 文件导入和模板下载
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
		// 将map集合转换为JSON字符串
		String mapString = DataConverter.convertObject2Json(maps);
		InputHidden cateIdHidden = InputHidden.getInstance("props", mapString);
		Prop hiddenProp = new Prop();
		hiddenProp.setPropKey("name");
		hiddenProp.setPropValue("props");
		cateIdHidden.addProp(hiddenProp);
		form.addChildNode(cateIdHidden);
		// 模板下载按钮
		CssClass tempCss = new CssClass("btn btn-primary btn-export");
		Button tempBtn = Button.getInstance("exportTemplate", tempCss, "下载模板");
		inputDiv.addChildNode(tempBtn);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("importDownSubmit", importCss, "导入");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		importBtn.addProp(importProp);
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束 */
		root.addChildNode(modalDiv);
		// 设置照片编辑按钮
		CssClass formImgCss = new CssClass("fa fa-eye");
		I FormImgi = I.getInstance(formImgCss);
		formImgCss = new CssClass("btn btn-info");
		Button formImgpelate = Button.getButtonWithIcon(null, formImgCss, null, FormImgi);
		Prop formImgprop = new Prop();
		formImgprop.setPropKey("name");
		formImgprop.setPropValue("editImg");
		formImgpelate.addProp(formImgprop);
		formImgprop = new Prop();
		formImgprop.setPropKey("title");
		formImgprop.setPropValue("查看图片");
		formImgpelate.addProp(formImgprop);
		Script formImgScript = Script.getInstance("formImgTemplate");
		formImgScript.addChildNode(formImgpelate);

		// 设置文件编辑按钮
		CssClass formFileCss = new CssClass("fa fa-cloud-download");
		I FormFilei = I.getInstance(formFileCss);
		formFileCss = new CssClass("btn btn-warning");
		Button formFilepelate = Button.getButtonWithIcon(null, formFileCss, null, FormFilei);
		Prop formFileprop = new Prop();
		formFileprop.setPropKey("name");
		formFileprop.setPropValue("editFile");
		formFilepelate.addProp(formFileprop);
		formImgprop = new Prop();
		formImgprop.setPropKey("title");
		formImgprop.setPropValue("查看文件");
		formImgpelate.addProp(formImgprop);
		Script formFileScript = Script.getInstance("formFileTemplate");
		formFileScript.addChildNode(formFilepelate);
		// 创建按钮组
		String scripts = "";
		CssClass css = new CssClass("fa fa-edit");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-5");
		Button tempelate = Button.getButtonWithIcon("editpage", css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("edit");
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

		css = new CssClass("fa fa-copy");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		tempelate = Button.getButtonWithIcon("copypage", css, null, i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("copy");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("copyPage(this)");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("复制");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);

		css = new CssClass("fa fa-times");
		i = I.getInstance(css);
		css = new CssClass("btn btn-danger bk-margin-4");
		tempelate = Button.getButtonWithIcon("removepaeg", css, null, i);
		prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("remove");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("removePage(this)");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("删除");
		tempelate.addProp(prop);
		script.addChildNode(tempelate);
		scripts += script.getNode();
		// 添加隐藏域
		InputHidden mapHidden = InputHidden.getInstance("mapHidden", mapString);
		root.addChildNode(mapHidden);
		// 创建列
		// 创建操作按钮行
		// 创建行
		Base rowBg = util.createRow(root);
		Base column = util.createColumn(rowBg, "12", "12", "12", null);

		// 构建添加按钮
		css = new CssClass("fa fa-plus");
		i = I.getInstance(css);
		css = new CssClass("btn btn-primary");
		Button button = Button.getButtonWithIcon("add", css, "添加", i);
		column.addChildNode(button);

		if(!formName.contains("结论")&&!formName.contains("问题处理记录")&&!formName.contains("总工")){
			// 构建导入按钮
			css = new CssClass("fa fa-sign-in");
			i = I.getInstance(css);
			css = new CssClass("btn btn-warning bk-margin-5 btn-setting");
			button = Button.getButtonWithIcon("import", css, "导入", i);
			column.addChildNode(button);
			// 确定按钮
			css = new CssClass("fa fa-check");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info");
		}else{
			// 确定按钮
			css = new CssClass("fa fa-check");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5");
		}
	

		
		if(formName.equals("疑难问题汇交记录表")){
			button = Button.getButtonWithIcon("back", css, "提交", i);
		}else{
			button = Button.getButtonWithIcon("back", css, "确定", i);
		}
		column.addChildNode(button);
		if(delegate != null){
			css = new CssClass("btn btn-info bk-margin-5");
			button = Button.getInstance("data", css, "数据");
			Prop prop2 = new Prop("style", "float:right");
			button.addProp(prop2);
			column.addChildNode(button);
			this.createModalDialog(util,root);
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(delegate != null){
			FormDefaultValueDelegate df = new FormDefaultValueDelegate(delegate);
			Map<String, Object> defaultParam = df.getParams();
			// 将默认参数加入到参数里
			for (String key : defaultParam.keySet())
			{
				params.put(key, defaultParam.get(key));
			}
			// 设置委托参数
			df.setParams(params);
			list = (List<Map<String, Object>>)df.excute();
		}
		InputHidden listHidden =  InputHidden.getInstance("listHidden", DataConverter.convertObject2Json(list));
		Prop prop1 = new Prop();
		prop1.setPropKey("name");
		prop1.setPropValue("lists");
		listHidden.addProp(prop1);
		root.addChildNode(listHidden);
		InputHidden hiddenId = InputHidden.getInstance("formName",formName);
		root.addChildNode(hiddenId);
		return root.getNode() + formImgScript.getNode() + formFileScript.getNode() + scripts;
	}

	/**
	 * 初始化图片查看页面
	 * @param url 图片地址
	 * @return 节点字符串
	 */
	public String getViewPage(String url)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		// 创建一个div
		Base row = util.createRow(root);
		// 创建列
		Base column = util.createColumn(row, "12", "12", "12", null);
		Base div = Div.getBlankDiv("content");
		column.addChildNode(div);
		// 图片
		Img img = Img.getInstance("formImg", null, null);
		Prop prop = new Prop();
		prop.setPropKey("src");
		prop.setPropValue(url);
		img.addProp(prop);
		div.addChildNode(img);
		// 创建返回按钮
		row = util.createRow(root);
		column = util.createColumn(row, "3", "2", "2", null);
		util.createCancelButton(column);
		return root.getNode();
	}

	/**
	 * 前端编辑工具
	 * @param groupList
	 * @return
	 */
	public Form createEditPage(Form form, List<Base> groupList)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建上间距
		Base rowSpace = util.createRowSpace(form);
		// 循环
		for (int i = 0; i < groupList.size(); i++)
		{
			// 将行放入到容器中
			form.addChildNode(groupList.get(i));
			util.createRowSpace();
		}
		// 创建操作按钮行
		Base rowOperation = util.createRow();
		// 创建确定按钮列
		Base column = util.createColumn(rowOperation, "8", "8", "8", "3");
		CssClass css = new CssClass("fa fa-check");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success");
		Button btn = Button.getButtonWithIcon("submit", css, "确定", i);
		column.addChildNode(btn);
		css = new CssClass("btn btn-default bk-margin-5");
		btn = Button.getInstance("back", css, "取消");
		Prop prop = new Prop("data-dismiss","modal");
		btn.addProp(prop);
		prop = new Prop("aria-hidden","true");
		btn.addProp(prop);
		column.addChildNode(btn);
		// 将操作行添加到div
		form.addChildNode(rowSpace);
		form.addChildNode(rowOperation);
		return form;
	}
	

	/**
	 * 创建一个返回按钮
	 * @param parent 父容器
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public Base createAddButton(Base parent)
	{
		CssClass css = new CssClass("fa fa-plus");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-success");
		Button btn = Button.getButtonWithIcon("add", css, "添加", i);
		parent.addChildNode(btn);
		return btn;
	}

	/**
	 * 创建流程表单
	 * @return
	 */
	public String createProcessForms()
	{
		String script = null;
		// 图片按钮
		CssClass formImgCss = new CssClass("fa fa-eye");
		I FormImgi = I.getInstance(formImgCss);
		formImgCss = new CssClass("btn btn-info");
		Button formImgpelate = Button.getButtonWithIcon(null, formImgCss, null, FormImgi);
		Prop formImgprop = new Prop();
		formImgprop.setPropKey("name");
		formImgprop.setPropValue("#: key #");
		formImgpelate.addProp(formImgprop);
		formImgprop = new Prop();
		formImgprop.setPropKey("onclick");
		formImgprop.setPropValue("showImg(this)");
		formImgpelate.addProp(formImgprop);
		formImgprop = new Prop();
		formImgprop.setPropKey("title");
		formImgprop.setPropValue("查看图片");
		formImgpelate.addProp(formImgprop);
		Script formImgScript = Script.getInstance("formImgTemplate");
		formImgScript.addChildNode(formImgpelate);
		script = formImgScript.getNode();
		// 文件按钮
		CssClass formFileCss = new CssClass("fa fa-cloud-download");
		I FormFilei = I.getInstance(formFileCss);
		formFileCss = new CssClass("btn btn-warning");
		Button formFilepelate = Button.getButtonWithIcon(null, formFileCss, null, FormFilei);
		Prop formFileprop = new Prop();
		formFileprop.setPropKey("name");
		formFileprop.setPropValue("#: key #");
		formFilepelate.addProp(formFileprop);
		formFileprop = new Prop();
		formFileprop.setPropKey("onclick");
		formFileprop.setPropValue("showFile(this)");
		formFilepelate.addProp(formFileprop);
		Script formFileScript = Script.getInstance("formFileTemplate");
		formFileScript.addChildNode(formFilepelate);
		script += formImgScript.getNode();
		return script;
	}

	/**
	 * 创建带查询的选择框
	 * @param id
	 * @param selectStr
	 * @return
	 */
	public Script createKendoSelect(String id, String value, String selectStr, boolean isImg)
	{
		String scriptContent = "$(function(){$(\"#" + id + "\").kendoDropDownList({" + "optionLabel: " + "\"--请选择--\"," + "dataTextField:\"val\","
				+ "dataValueField:\"val\",";
		if (selectStr != "" && selectStr != null)
		{
			scriptContent += "dataSource:[" + selectStr.toString() + "],";
		}
		else
		{
			scriptContent += "dataSource:" + "[],";
		}
		if (isImg)
		{
			scriptContent += "template:'<span><img src=\"../#: image #\" alt=\"#: val #\" />#:val  #</span>',";
		}
		scriptContent += "filter:" + "\"contains\"," + "suggest:" + "true" + " });});";
		Script script = Script.getInstance(null, scriptContent);

		return script;
	}
	/**
	 * 创建带查询的多选选择框
	 * @param id
	 * @param selectStr
	 * @return
	 */
	public Script createMultiSelect(String id, String value, String selectStr, boolean isImg)
	{
		String scriptContent = "$(function(){$(\"#" + id + "\").kendoMultiSelect({ dataTextField:\"val\","
				+ "dataValueField:\"val\",";
		if (selectStr != "" && selectStr != null)
		{
			scriptContent += "dataSource:[" + selectStr.toString() + "],";
		}
		else
		{
			scriptContent += "dataSource:" + "[],";
		}
		if (isImg)
		{
			scriptContent += "template:'<span><img src=\"../#: image #\" alt=\"#: val #\" />#:val  #</span>',";
		}
		scriptContent += "filter:" + "\"contains\"," + "suggest:" + "true" + " });});";
		Script script = Script.getInstance(null, scriptContent);
		
		return script;
	}
	
	/**
	 * 弹出框
	 */
	public void createModalDialog(FrontUtil util,Base root)
	{
		/** 创建Modal Dialog 开始 */
		CssClass modelCss = new CssClass("modal fade col-lg-12 in");
		Div modalDiv = Div.getInstance("myModal1", modelCss, null);
		// 创建div
		CssClass dialogCss = new CssClass("modal-dialog");
		Div dialogDiv = Div.getInstance(null, dialogCss, null);
		Prop styleProp = new Prop();
		styleProp.setPropKey("style");
		styleProp.setPropValue("width:80%");
		dialogDiv.addProp(styleProp);
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
		Prop closeProp = new Prop();
		closeProp.setPropKey("data-dismiss");
		closeProp.setPropValue("modal");
		Prop closeProp1 = new Prop("aria-hidden","true");
		CssClass closeCss = new CssClass("close");
		Button closeBtn = Button.getInstance("closeModel1", closeCss, "&times;");
		closeBtn.addProp(closeProp);
		closeBtn.addProp(closeProp1);
		headerDiv.addChildNode(closeBtn);
		// 创建标题div
		// 创建行
		Base rowGrid = util.createRow();
		// 创建列
		Base columnGrid = util.createColumn(rowGrid, "12", "12");
		// 创建Grid
		util.createGrid(columnGrid, "datalist");
		headerDiv.addChildNode(util.createRowSpace());
		headerDiv.addChildNode(rowGrid);
		// 绑定按钮组
		List<Button> btnList = new ArrayList<Button>();
		// 创建确定按钮。
		Button taskSubmit = Button.getInstance("listSubmit", new CssClass("btn btn-success"), "确定");
		taskSubmit.addProp(closeProp);
		btnList.add(taskSubmit);
		// 创建返回按钮。
		Button backBtn = Button.getInstance("hiddenModal1", new CssClass("btn btn-default"), "返回");
		backBtn.addProp(closeProp);
		backBtn.addProp(closeProp1);
		btnList.add(backBtn);
		headerDiv.addChildNode(util.createRowSpace());
		headerDiv.addChildNode(ButtonGroup.getInstance(btnList));
		/** Modal Dialog 结束 */
		// 加入到root
		root.addChildNode(modalDiv);
	}
	
	FrontUtil util = FrontUtil.getInstance();
	
	/**
	 * 编绘计划问题初始化
	 * @return
	 */
	public String getPlanPage(String taskId,String taskDefId,String taskInstId,String processInstId,String parentProcessInstId,String isRuTask)
	{
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "编绘计划问题");
		util.createRowSpace(root);
		// 创建行
		Base rowBg = util.createRow(root);
		Base column = util.createColumn(rowBg, "12", "12", "12", null);
		if(isRuTask.equals("true")){
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
		}else{
			CssClass css = new CssClass("fa fa-file");
			I i = I.getInstance(css);
			css = new CssClass("btn btn-success search");
			Button button = Button.getButtonWithIcon("zhijian", css, "&nbsp;"+"质检记录", i);
			column.addChildNode(button);
			// 构建删除按钮
			css = new CssClass("fa fa-file");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 search");
			button = Button.getButtonWithIcon("shending", css, "&nbsp;"+"审定记录", i);
			column.addChildNode(button);
		}
		// 构建刷新按钮
		CssClass css = new CssClass("fa fa-refresh");
		I i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5 search");
		Button button = Button.getButtonWithIcon("refresh", css, "&nbsp;"+"刷新", i);
		column.addChildNode(button);
		
		css = new CssClass("fa fa-default");
		i = I.getInstance(css);
		css = new CssClass("btn btn-default bk-margin-5 search");
		button = Button.getButtonWithIcon("back", css, "&nbsp;"+"返回", i);
		column.addChildNode(button);
		
		// 创建一个行间隔
		util.createRowSpace(root);
		/** 创建Grid行  开始*/
		// 创建Grid
		util.createGrid(root,"plan");
		
		InputHidden taskIDHidId = InputHidden.getInstance("taskID", taskId);
		root.addChildNode(taskIDHidId);
		InputHidden taskDefHidId = InputHidden.getInstance("taskDefId", taskDefId);
		root.addChildNode(taskDefHidId);
		InputHidden taskInstHidId = InputHidden.getInstance("taskInstId", taskInstId);
		root.addChildNode(taskInstHidId);
		InputHidden processInstHidId = InputHidden.getInstance("processInstId", processInstId);
		root.addChildNode(processInstHidId);
		InputHidden parentProcessInstHidId = InputHidden.getInstance("parentProcessInstId", parentProcessInstId);
		root.addChildNode(parentProcessInstHidId);
		
		InputHidden HidId = InputHidden.getInstance("planId", null);
		root.addChildNode(HidId);
		InputHidden isRutaskHidId = InputHidden.getInstance("isRuTask", isRuTask);
		root.addChildNode(isRutaskHidId);
		css = new CssClass("fa fa-edit");
		i = I.getInstance(css);
		css = new CssClass("btn btn-success bk-margin-4");
		Button tempelate = Button.getButtonWithIcon(null, css, null, i);
		Prop prop = new Prop();
		prop.setPropKey("name");
		prop.setPropValue("editPlan");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("onclick");
		prop.setPropValue("editPlan(this)");
		tempelate.addProp(prop);
		prop = new Prop();
		prop.setPropKey("title");
		prop.setPropValue("编辑");
		tempelate.addProp(prop);
		if(isRuTask.equals("false")){
			tempelate.addProp(prop);
			prop = new Prop("disabled","disabled");
			tempelate.addProp(prop);
			prop = new Prop("style","background-color:gray");
			tempelate.addProp(prop);
		}
		Script script = Script.getInstance("editTemplate");
		script.addChildNode(tempelate);
		root.addChildNode(planModal(taskDefId));
		return root.getNode()+script.getNode();
	}
	
	/**
	 * 创建任务提交模态窗口
	 * @param processDefKey
	 * @return
	 */
	private Div planModal(String taskDefId){
		/** 创建Modal Dialog 审批 开始*/
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
		if(taskDefId.contains("zhijian")){//质检
			CssClass titleCss1 = new CssClass("modal-title bk-fg-primary model-custom");
			Div titleDiv1 = Div.getInstance("zj", titleCss1, "质检记录：");
			headerDiv.addChildNode(titleDiv1);
			TextArea text1 = TextArea.getDefault("zj_remark","6");
			Prop PropText1 = new Prop("style","width:98%");
			text1.addProp(PropText1);
			headerDiv.addChildNode(text1);
		}else if(taskDefId.contains("shending")){//审定
			CssClass titleCss1 = new CssClass("modal-title bk-fg-primary model-custom");
			Div titleDiv1 = Div.getInstance("sd", titleCss1, "审定记录：");
			headerDiv.addChildNode(titleDiv1);
			TextArea text1 = TextArea.getDefault("sd_remark","6");
			Prop PropText1 = new Prop("style","width:98%");
			text1.addProp(PropText1);
			headerDiv.addChildNode(text1);
		}
		//退回按钮
		CssClass backCss = new CssClass("btn btn-primary btn-top");
		Button backBtn = Button.getInstance("tijiao", backCss, "提交");
		Prop prop = new Prop("onclick", "plan.tijiao()");
		backBtn.addProp(prop);
		headerDiv.addChildNode(backBtn);
		//取消按钮
		CssClass cancelCss = new CssClass("btn btn-default cus-cancel  btn-top");
		Button cancelBtn = Button.getInstance("back", cancelCss, "取消");
		prop = new Prop("style", "margin-left:5px;");
		cancelBtn.addProp(prop);
		Prop cancelProp = new Prop();
		cancelProp.setPropKey("data-dismiss");
		cancelProp.setPropValue("modal");
		cancelBtn.addProp(cancelProp);
		headerDiv.addChildNode(cancelBtn);
		/** Modal Dialog 结束*/
		return modalDiv;
	}

}
