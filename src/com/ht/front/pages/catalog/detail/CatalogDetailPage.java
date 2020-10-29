package com.ht.front.pages.catalog.detail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.ht.common.util.LogHelper;
import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.Div;
import com.ht.front.model.File;
import com.ht.front.model.Form;
import com.ht.front.model.I;
import com.ht.front.model.Img;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Script;
import com.ht.front.model.Select;
import com.ht.front.model.Span;
import com.ht.front.model.TextBox;
import com.ht.front.template.EditPage;
import com.ht.front.util.FrontUtil;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.catalog.area.CatalogAreaService;
import com.ht.service.inter.catalog.detail.CatalogDetailService;

/**
 * @ClassName: CatalogDetailPage
 * @Description: 目录页面初始化类
 * @author penghao
 * @date 2016年10月15日 上午9:49:46
 */
public class CatalogDetailPage
{

	/**
	 * 页面实例
	 */
	private static CatalogDetailPage page = null;

	/**
	 * 获取页面实例
	 * @return
	 */
	public static CatalogDetailPage getInstance()
	{
		if (page == null)
		{
			page = new CatalogDetailPage();
		}
		return page;
	}

	/**
	 * 初始化目录页面
	 * @param
	 * @return 节点字符串
	 */
	public String getListPage(String status, String type, String categoryId,boolean jurisdiction)
	{

		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		if (type.equals("1"))
		{// 规划目录图管理
			util.createHeaderBar(root, "规划图目录");
		}
		else if (type.equals("2"))
		{// 海军目录图管理
			util.createHeaderBar(root, "海军图目录");
		}
		else if (type.equals("3"))
		{// 港口航道图管理
			util.createHeaderBar(root, "港口航道图目录");
		}
		util.createRowSpace(root);
		// 创建第一行
		Base row = util.createRow(root);
		// 创建列
		Base col1 = util.createColumn(row, "3", "3", "3", null);
		// 创建列
		Base col2 = util.createColumn(row, "4", "4","4",null);
		// 创建列
		Base col3 = util.createColumn(row, "4", "4","4",null);
		// 创建列
		// 图名
		InputGroup ig = (InputGroup)util.createSeachControlGroup("关联图号", "mapNo", "关联图号","searchMapNo",new CssClass("fa fa-search"));
		col1.addChildNode(ig);
		//创建库存数量1搜索框
		ig = (InputGroup) util.createRangeControlGroup("纬度范围", "starLatitude", "起始纬度", "endLatitude", "终止纬度");
		col2.addChildNode(ig);
		ig = (InputGroup) util.createRangeControlGroup("经度范围", "starLongitude", "起始经度", "endLongitude", "终止经度");
		I i = I.getInstance(new CssClass("fa fa-search"));
		Span span = Span.getSpanWithI(null, i,"search");
		Prop propPointer  = new Prop();
		propPointer.setPropKey("style");
		propPointer.setPropValue("cursor: pointer;");
		span.addProp(propPointer);
		ig.addChildNode(span);
		col3.addChildNode(ig);
		util.createRowSpace(root);
		/** 创建按钮组行 开始 */
		// 创建行
		Base rowBg = util.createRow(root);
		// 创建列
		Base column = util.createColumn(rowBg, "8", "8");
		// 创建按钮组
		// 构建创建div
		if(jurisdiction){
			CssClass css = new CssClass("fa fa-plus");
			i = I.getInstance(css);
			css = new CssClass("btn btn-success");
			Button button = Button.getButtonWithIcon("add", css, "创建", i);
			column.addChildNode(button);
			// 构建删除div
			css = new CssClass("fa fa-times");
			i = I.getInstance(css);
			css = new CssClass("btn btn-danger bk-margin-5");
			button = Button.getButtonWithIcon("remove", css, "删除", i);
			column.addChildNode(button);
			// 构建导入按钮
			css = new CssClass("fa fa-sign-in");
			i = I.getInstance(css);
			css = new CssClass("btn btn-info bk-margin-5 btn-setting");
				button = Button.getButtonWithIcon("import", css, "导入", i);
			column.addChildNode(button);
		}
		
		// 构建导出按钮
		CssClass 	css = new CssClass("fa fa-sign-out");
		i = I.getInstance(css);
		css = new CssClass("btn btn-info bk-margin-5");
		Button	button = Button.getButtonWithIcon("export", css, "导出", i);
		column.addChildNode(button);
		// 构建刷新按钮
		css = new CssClass("fa fa-refresh");
		i = I.getInstance(css);
		css = new CssClass("btn btn-warning bk-margin-5");
		button = Button.getButtonWithIcon("refresh", css, "刷新", i);
		column.addChildNode(button);
		if(jurisdiction){
			// 构建提交审核按钮
			css = new CssClass("fa fa-check");
			i = I.getInstance(css);
			css = new CssClass("btn btn-success bk-margin-5");
			button = Button.getButtonWithIcon("audit", css, "提交审核", i);
			column.addChildNode(button);

			List<BaseData> statuslist = new ArrayList<BaseData>();
			BaseData data = new BaseData();
			data.setId("0");
			data.setValue("创建");
			statuslist.add(data);
			BaseData data1 = new BaseData();
			data1.setId("1");
			data1.setValue("待审核");
			statuslist.add(data1);
			BaseData data2 = new BaseData();
			data2.setId("2");
			data2.setValue("审核通过");
			statuslist.add(data2);
			BaseData data3 = new BaseData();
			data3.setId("3");
			data3.setValue("退回");
			statuslist.add(data3);
			Base selectStatus = util.createColumn(rowBg, "2", "2");
			Base selectgroup = util.creatDefaultSelectGroup("状态", "status", statuslist, "id", "value", false);
			selectStatus.addChildNode(selectgroup);
		}
		

		Base colInput = util.createColumn(rowBg, "2", "2");
		span = Span.getDefault("年份:");
		css = new CssClass("fa fa-calendar");
		i = I.getInstance(css);
		span.addChildNode(i);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		TextBox text = TextBox.getDefault("year", String.valueOf(year), "年份");
		ig = InputGroup.getInstance(span, text);
		colInput.addChildNode(ig);
		/** 创建按钮组行 结束 */
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		// 创建Grid
		util.createGrid(root, "detail");
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
		Div titleDiv = Div.getInstance(null, titleCss, "导入目录");
		headerDiv.addChildNode(titleDiv);
		// 创建form表单
		CssClass formCss = new CssClass("form-search");
		Form form = Form.getInstance("importForm", formCss, null);
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
		// 目录类型id
		InputHidden cateIdHidden = InputHidden.getInstance("categoryId", categoryId);
		Prop hiddenProp = new Prop();
		hiddenProp.setPropKey("name");
		hiddenProp.setPropValue("categoryId");
		cateIdHidden.addProp(hiddenProp);
		form.addChildNode(cateIdHidden);
		// 目录类型
		InputHidden typeHidden = InputHidden.getInstance("type", type);
		Prop typeProp = new Prop();
		typeProp.setPropKey("name");
		typeProp.setPropValue("type");
		typeHidden.addProp(typeProp);
		form.addChildNode(typeHidden);
		// 模板下载按钮
		CssClass tempCss = new CssClass("btn btn-primary btn-export");
		Button tempBtn = Button.getInstance("exportTemplate", tempCss, "下载模板");
		inputDiv.addChildNode(tempBtn);
		// 构建导入按钮
		CssClass importCss = new CssClass("import-submit btn btn-primary");
		Button importBtn = Button.getInstance("importSubmit", importCss, "导入");
		Prop importProp = new Prop();
		importProp.setPropKey("data-dismiss");
		importProp.setPropValue("modal");
		importBtn.addProp(importProp);
		headerDiv.addChildNode(importBtn);
		/** Modal Dialog 结束 */
		root.addChildNode(modalDiv);
		// 添加查看图片按钮
		CssClass viewCss = new CssClass("fa fa-eye");
		I viewI = I.getInstance(viewCss);
		viewCss = new CssClass("btn btn-warning bk-margin-4");
		Button tempelate1 = Button.getButtonWithIcon(null, viewCss, null, viewI);
		Prop viewprop = new Prop();
		viewprop.setPropKey("name");
		viewprop.setPropValue("viewImg");
		// 绑定属性
		tempelate1.addProp(viewprop);
		viewprop = new Prop();
		viewprop.setPropKey("title");
		viewprop.setPropValue("查看");
		tempelate1.addProp(viewprop);
		viewprop = new Prop();
		viewprop.setPropKey("onclick");
		viewprop.setPropValue("viewPage(this)");
		tempelate1.addProp(viewprop);
		Script script1 = Script.getInstance("viewTemplate");
		script1.addChildNode(tempelate1);
		Script script = Script.getInstance("editTemplate");
		if(jurisdiction){
			// 添加编辑按钮
			CssClass editCss = new CssClass("fa fa-edit");
			I editI = I.getInstance(editCss);
			editCss = new CssClass("btn btn-success bk-margin-4");
			Button tempelate = Button.getButtonWithIcon(null, editCss, null, editI);
			Prop prop = new Prop();
			prop.setPropKey("name");
			prop.setPropValue("editDetail");
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
			script.addChildNode(tempelate);
		}
		
		// 目录菜单类型隐藏域
		InputHidden hidden = InputHidden.getInstance("type", type);
		root.addChildNode(hidden);
		// 目录类型ID隐藏域
		InputHidden hiddenId = InputHidden.getInstance("categoryId", categoryId);
		root.addChildNode(hiddenId);
		// 目录类型ID隐藏域
		InputHidden hiddenStatus = InputHidden.getInstance("status", status);
		root.addChildNode(hiddenStatus);
		//是否隐藏操作栏
		InputHidden jurisdictionHidden = InputHidden.getInstance("jurisdiction", jurisdiction+"");
		root.addChildNode(jurisdictionHidden);
		return root.getNode() + script.getNode() + script1.getNode();
	}

	/**
	 * 初始化Form页面
	 * @param
	 * @return 节点字符串
	 */
	public String getEditPage(CatalogAreaService catalogAreaService, BaseDataService baseDataService, CatalogDetailService catalogDetailService,
			String id, String categoryId, String type,String flag)
	{
		EditPage edit = new EditPage();
		Base editPage = null;
		// 从基础字典表获取目录类型列表
		List<BaseData> typeList = null;
		// 获取目录区域列表
		List<CatalogArea> areaList = null;
		// 获取目录
		CatalogDetail detail = null;
		try
		{
			// 获取目录类型
			typeList = baseDataService.getBaseDataById(categoryId);
			// 获取目录区域
			areaList = catalogAreaService.getCatalogAreaListByCategoryId(categoryId);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		if (StringUtils.isNotBlank(id))
		{
			try
			{
				// 根据ID获取目录
				detail = catalogDetailService.getDetailById(id);
			}
			catch (Exception e)
			{
				e.getMessage();
			}
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("目录类型", "catalogTypeId", typeList, "id", "value", categoryId);
			param.add(selectgroup);
			String areaId = null;
			if (detail.getArea() != null && !StringUtils.isEmpty(detail.getArea().getId()))
			{
				areaId = detail.getArea().getId();
			}
			selectgroup = util.creatDefaultSelectGroup("目录区域", "catalogAreaId", areaList, "id", "areaName", areaId);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", detail.getMapNo() == null ? null : detail.getMapNo(), "图号");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288名", "mapName", detail.getMapName() == null ? null : detail.getMapName(), "图名");
			param.add(ig);
			ig = InputGroup.getInGroup("比例尺(1:)1:", "scale", detail.getScale() == null ? "" : detail.getScale(), "比例尺(1:)");
			param.add(ig);
			ig = InputGroup.getInGroup("性&#12288&#12288质", "nature", detail.getNature() == null ? null : detail.getNature(), "性质");
			param.add(ig);
			String starLatitude = "";
			if(StringUtils.isNotBlank(detail.getStarLatitude())){
				starLatitude = detail.getStarLatitude();
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
			ig = InputGroup
					.getInGroup("起始纬度", "starLatitude", starLatitude, "起始纬度，度分秒用“-”代替");
			param.add(ig);
			String endLatitude = "";
			if(StringUtils.isNotBlank(detail.getEndLatitude())){
				endLatitude = detail.getEndLatitude();
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
			ig = InputGroup.getInGroup("终止纬度", "endLatitude", endLatitude, "终止纬度，度分秒用“-”代替");
			param.add(ig);
			String starLongitude = "";
			if(StringUtils.isNotBlank(detail.getStarLongitude())){
				starLongitude = detail.getStarLongitude();
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
			ig = InputGroup.getInGroup("起始经度", "starLongitude", starLongitude,"起始经度，度分秒用“-”代替");
			param.add(ig);
			String endLongitude = "";
			if(StringUtils.isNotBlank(detail.getEndLongitude())){
				endLongitude = detail.getEndLongitude();
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
			ig = InputGroup
					.getInGroup("终止经度", "endLongitude",endLongitude, "终止经度，度分秒用“-”代替");
			param.add(ig);
			ig = InputGroup.getInGroup("基测", "measurementPeriod", detail.getMeasurementPeriod() == null ? null : detail.getMeasurementPeriod(),
					"基测");
			param.add(ig);
			ig = InputGroup.getInGroup("检测", "testIng", detail.getTestIng() == null ? null : detail.getTestIng(),
					"检测");
			param.add(ig);
			ig = InputGroup.getInGroup("出版周期", "publicationYear", detail.getPublicationYear() == null ? null : detail.getPublicationYear(),
					"出版周期");
			param.add(ig);
			ig = InputGroup.getInGroup("基准纬度", "datumLatitude", detail.getDatumLatitude() == null ? null : detail.getDatumLatitude(), "基准纬度");
			param.add(ig);
			
			ig = InputGroup.getInGroup("图&#12288&#12288积", "mapProportion", detail.getMapProportion() == null ? null : detail.getMapProportion(), "图积");
			param.add(ig);
			ig = InputGroup.getInGroup("调整性质", "adjustmentProperty", detail.getAdjustmentProperty() == null ? null : detail.getAdjustmentProperty(),
					"调整性质");
			param.add(ig);
			String datestr = null;
			if (detail.getPublicationDate() != null)
			{
				datestr = DateFormatUtils.format(detail.getPublicationDate(), "yyyy-MM");
			}
			ig = InputGroup.getInGroup("出版日期", "publicationDate", datestr, "出版日期");
			param.add(ig);
			/*ig = InputGroup.getInGroup("印&#12288&#12288次", "printNum", detail.getPrintNum() == null ? null : detail.getPrintNum(), "印&#12288&#12288次");
			param.add(ig);*/
			ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", detail.getRemarks() == null ? null : detail.getRemarks(), "备注");
			param.add(ig);
			editPage = edit.createEditPage(param);
			if (type.equals("1"))
			{// 规划目录图管理
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "规划目录图明细");
				}else{
					util.createHeaderBar(editPage, "规划目录图编辑");
				}
			}
			else if (type.equals("2"))
			{// 海军目录图管理
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "海军目录图明细");
				}else{
					util.createHeaderBar(editPage, "海军目录图编辑");
				}
			}
			else if (type.equals("3"))
			{// 港口航道图管理
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "港口航道图明细");
				}else{
					util.createHeaderBar(editPage, "港口航道图编辑");
				}
			}
			InputHidden hiddenId = InputHidden.getInstance("detailId", id);
			
			

			InputHidden hiddenType = InputHidden.getInstance("type", type);
			InputHidden hiddenFlag = InputHidden.getInstance("flag", flag);
			InputHidden hiddenCateId = InputHidden.getInstance("categoryId", categoryId);
			InputHidden hiddenStatus = InputHidden.getInstance("status", detail.getStatus() == null?null:detail.getStatus());
			editPage.addChildNode(hiddenId);
			
			
			editPage.addChildNode(hiddenType);
			editPage.addChildNode(hiddenFlag);
			editPage.addChildNode(hiddenCateId);
			editPage.addChildNode(hiddenStatus);
		}
		else
		{
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("目录类型", "catalogTypeId", typeList, "id", "value", true);
			param.add(selectgroup);
			selectgroup = util.creatDefaultSelectGroup("目录区域", "catalogAreaId", areaList, "id", "areaName", true);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", null, "图号");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288名", "mapName", null, "图名");
			param.add(ig);
			ig = InputGroup.getInGroup("比例尺(1:)", "scale", "", "比例尺(1:)");
			param.add(ig);
			ig = InputGroup.getInGroup("性&#12288&#12288质", "nature", null, "性质");
			param.add(ig);
			ig = InputGroup.getInGroup("起始纬度", "starLatitude", null, "起始纬度");
			param.add(ig);
			ig = InputGroup.getInGroup("终止纬度", "endLatitude", null, "终止纬度");
			param.add(ig);
			ig = InputGroup.getInGroup("起始经度", "starLongitude", null, "起始经度");
			param.add(ig);
			ig = InputGroup.getInGroup("终止经度", "endLongitude", null, "终止经度 ");
			param.add(ig);
			ig = InputGroup.getInGroup("基测", "measurementPeriod", null, "基测");
			param.add(ig);
			ig = InputGroup.getInGroup("检测", "testIng", null, "检测");
			param.add(ig);
			ig = InputGroup.getInGroup("出版周期", "publicationYear", null, "出版周期");
			param.add(ig);
			/*String spanText = "出版年份" + "：";
			Span span = Span.getDefault(spanText);
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			TextBox text = TextBox.getDefault("publicationYear",String.valueOf(year) , "年份");
			ig = InputGroup.getInstance(span, text);
			param.add(ig);*/
			ig = InputGroup.getInGroup("基准纬度", "datumLatitude", null, "基准纬度");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288积", "mapProportion", null, "图积");
			param.add(ig);
			ig = InputGroup.getInGroup("调整性质", "adjustmentProperty", null, "调整性质");
			param.add(ig);
			String date = DateFormatUtils.format(new Date(), "yyyy-MM");
			ig = InputGroup.getInGroup("出版日期", "publicationDate", date, "出版日期");
			param.add(ig);
			ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", null, "备注");
			param.add(ig);
			editPage = edit.createEditPage(param);
			if (type.equals("1"))
			{// 规划目录图管理
				util.createHeaderBar(editPage, "规划目录图创建");
			}
			else if (type.equals("2"))
			{// 海军目录图管理
				util.createHeaderBar(editPage, "海军目录图创建");
			}
			else if (type.equals("3"))
			{// 港口航道图管理
				util.createHeaderBar(editPage, "港口航道图创建");
			}
			InputHidden hiddenId = InputHidden.getInstance("detailId", id);
			
			
			InputHidden hiddenType = InputHidden.getInstance("type", type);
			InputHidden hiddenFlag = InputHidden.getInstance("flag", flag);
			InputHidden hiddenCateId = InputHidden.getInstance("categoryId", categoryId);
			
			
			editPage.addChildNode(hiddenId);
			editPage.addChildNode(hiddenType);
			editPage.addChildNode(hiddenFlag);
			editPage.addChildNode(hiddenCateId);
			
			
		}
		return editPage.getNode();
	}

	/**
	 * 初始化Form页面
	 * @param
	 * @return 节点字符串
	 */
	public String getEditElsePage(CatalogAreaService catalogAreaService, BaseDataService baseDataService, CatalogDetailService catalogDetailService,
			String id, String categoryId, String type,String flag)
	{
		EditPage edit = new EditPage();
		Base editPage = null;
		// 从基础字典表获取目录类型列表
		List<BaseData> typeList = null;
		// 获取目录区域列表
		List<CatalogArea> areaList = null;
		// 获取目录
		CatalogDetail detail = null;
		try
		{
			// 获取目录类型
			typeList = baseDataService.getBaseDataById(categoryId);
			// 获取目录区域
			areaList = catalogAreaService.getCatalogAreaListByCategoryId(categoryId);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		if (StringUtils.isNotBlank(id))
		{
			try
			{
				// 根据ID获取目录
				detail = catalogDetailService.getDetailById(id);
			}
			catch (Exception e)
			{
				e.getMessage();
			}
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("目录类型", "catalogTypeId", typeList, "id", "value", categoryId);
			param.add(selectgroup);
			String areaId = null;
			if (detail.getArea() != null && !StringUtils.isEmpty(detail.getArea().getId()))
			{
				areaId = detail.getArea().getId();
			}
			selectgroup = util.creatDefaultSelectGroup("目录区域", "catalogAreaId", areaList, "id", "areaName", areaId);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", detail.getMapNo() == null ? null : detail.getMapNo(), "图号");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288名", "mapName", detail.getMapName() == null ? null : detail.getMapName(), "图名");
			param.add(ig);
			ig = InputGroup.getInGroup("比例尺(1:)", "scale", detail.getScale() == null ? "" : detail.getScale(), "比例尺(1:)");
			param.add(ig);
			String datestr = null;
			if (detail.getPublicationDate() != null)
			{
				datestr = DateFormatUtils.format(detail.getPublicationDate(), "yyyy-MM");
			}
			ig = InputGroup.getInGroup("出版日期", "publicationDate", datestr, "出版日期");
			param.add(ig);
			String starLatitude = "";
			if(StringUtils.isNotBlank(detail.getStarLatitude())){
				starLatitude = detail.getStarLatitude();
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
			ig = InputGroup
					.getInGroup("起始纬度", "starLatitude", starLatitude, "起始纬度，度分秒用“-”代替");
			param.add(ig);
			String endLatitude = "";
			if(StringUtils.isNotBlank(detail.getEndLatitude())){
				endLatitude = detail.getEndLatitude();
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
			ig = InputGroup.getInGroup("终止纬度", "endLatitude", endLatitude, "终止纬度，度分秒用“-”代替");
			param.add(ig);
			String starLongitude = "";
			if(StringUtils.isNotBlank(detail.getStarLongitude())){
				starLongitude = detail.getStarLongitude();
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
			ig = InputGroup.getInGroup("起始经度", "starLongitude", starLongitude,"起始经度，度分秒用“-”代替");
			param.add(ig);
			String endLongitude = "";
			if(StringUtils.isNotBlank(detail.getEndLongitude())){
				endLongitude = detail.getEndLongitude();
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
			ig = InputGroup
					.getInGroup("终止经度", "endLongitude",endLongitude, "终止经度，度分秒用“-”代替");
			param.add(ig);
			ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", detail.getRemarks() == null ? null : detail.getRemarks(), "备注");
			param.add(ig);
			editPage = edit.createEditPage(param);
			if (type.equals("1"))
			{// 规划目录图管理
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "规划目录图明细");
				}else{
					util.createHeaderBar(editPage, "规划目录图编辑");
				}
			}
			else if (type.equals("2"))
			{// 海军目录图管理
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "海军目录图明细");
				}else{
					util.createHeaderBar(editPage, "海军目录图编辑");
				}
			}
			else if (type.equals("3"))
			{// 港口航道图管理
				if(flag.equals("1")){
					util.createHeaderBar(editPage, "港口航道图明细");
				}else{
					util.createHeaderBar(editPage, "港口航道图编辑");
				}
			}
			InputHidden hiddenId = InputHidden.getInstance("detailId", id);
			
			
			InputHidden hiddenType = InputHidden.getInstance("type", type);
			InputHidden hiddenFlag = InputHidden.getInstance("flag", flag);
			InputHidden hiddenCateId = InputHidden.getInstance("categoryId", categoryId);
			InputHidden hiddenStatus = InputHidden.getInstance("status", detail.getStatus() == null?null:detail.getStatus());
			editPage.addChildNode(hiddenId);
			
			
			editPage.addChildNode(hiddenType);
			editPage.addChildNode(hiddenFlag);
			editPage.addChildNode(hiddenCateId);
			editPage.addChildNode(hiddenStatus);
		}
		else
		{
			List<Base> param = new ArrayList<Base>();
			FrontUtil util = FrontUtil.getInstance();
			Base selectgroup = util.creatDefaultSelectGroup("目录类型", "catalogTypeId", typeList, "id", "value", true);
			param.add(selectgroup);
			selectgroup = util.creatDefaultSelectGroup("目录区域", "catalogAreaId", areaList, "id", "areaName", true);
			param.add(selectgroup);
			InputGroup ig = InputGroup.getInGroup("图&#12288&#12288号", "mapNo", null, "图号");
			param.add(ig);
			ig = InputGroup.getInGroup("图&#12288&#12288名", "mapName", null, "图名");
			param.add(ig);
			ig = InputGroup.getInGroup("比例尺(1:)", "scale", "", "比例尺(1:)");
			param.add(ig);
			String date = DateFormatUtils.format(new Date(), "yyyy-MM");
			ig = InputGroup.getInGroup("出版日期", "publicationDate", date, "出版日期");
			param.add(ig);
			ig = InputGroup.getInGroup("起始纬度", "starLatitude", null, "起始纬度 ");
			param.add(ig);
			ig = InputGroup.getInGroup("终止纬度", "endLatitude", null, "终止纬度");
			param.add(ig);
			ig = InputGroup.getInGroup("起始经度", "starLongitude", null, "起始经度");
			param.add(ig);
			ig = InputGroup.getInGroup("终止经度", "endLongitude", null, "终止经度");
			param.add(ig);
			ig = InputGroup.getInGroup("备&#12288&#12288注", "remarks", null, "备注");
			param.add(ig);
			editPage = edit.createEditPage(param);
			if (type.equals("1"))
			{// 规划目录图管理
				util.createHeaderBar(editPage, "规划目录图创建");
			}
			else if (type.equals("2"))
			{// 海军目录图管理
				util.createHeaderBar(editPage, "海军目录图创建");
			}
			else if (type.equals("3"))
			{// 港口航道图管理
				util.createHeaderBar(editPage, "港口航道图创建");
			}
			InputHidden hiddenId = InputHidden.getInstance("detailId", id);
			
			
			InputHidden hiddenType = InputHidden.getInstance("type", type);
			InputHidden hiddenFlag = InputHidden.getInstance("flag", flag);
			InputHidden hiddenCateId = InputHidden.getInstance("categoryId", categoryId);
			editPage.addChildNode(hiddenId);
			
			
			editPage.addChildNode(hiddenType);
			editPage.addChildNode(hiddenFlag);
			editPage.addChildNode(hiddenCateId);
		}
		return editPage.getNode();
	}

	/**
	 * 初始化图片查看页面
	 * @param
	 * @return 节点字符串
	 */
	public String getViewPage(CatalogDetailService catalogDetailService, String id, String type, String categoryId, String year)
	{
		// 获取图片url
		String imgurl = null;
		String imgType = null;
		String src = null;
		try
		{
			CatalogDetail detail = catalogDetailService.getDetailById(id);
			if (detail != null && detail.getArea() != null && StringUtils.isNotEmpty(detail.getArea().getAreaImg()))
			{
				imgurl = detail.getArea().getAreaImg();
			}
			if (detail != null && detail.getArea() != null && StringUtils.isNotEmpty(detail.getArea().getImgType()))
			{
				imgType = detail.getArea().getImgType();
			}
			src = "data:image/" + imgType + ";base64," + imgurl;
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		// 创建前端工具实例
		FrontUtil util = FrontUtil.getInstance();
		// 创建一个容器
		Base root = util.createRoot();
		util.createHeaderBar(root, "区域图片查看");
		// 创建一个行间隔
		Base rowSpace = util.createRowSpace(root);
		// 创建一个div
		CssClass divCss = new CssClass("col-lg-4 col-md-4 col-sm-3 col-xs-3");
		Div div = Div.getInstance("content", divCss, null);
		// 图片
		CssClass css = new CssClass("view-img");
		Img img = Img.getInstance("areaImg", null, css);
		CssClass cssbtn = new CssClass("btn btn-default img-top");
		Prop prop = new Prop();
		prop.setPropKey("src");
		prop.setPropValue(src);
		img.addProp(prop);
		div.addChildNode(img);
		// 返回按钮
		Button btn = Button.getInstance("back", cssbtn, "返回");
		div.addChildNode(btn);
		InputHidden hiddenName = InputHidden.getInstance("type", type);
		root.addChildNode(hiddenName);
		InputHidden hiddenCategoryId = InputHidden.getInstance("categoryId", categoryId);
		root.addChildNode(hiddenCategoryId);
		InputHidden hiddenYear = InputHidden.getInstance("year", year);
		root.addChildNode(hiddenYear);
		root.addChildNode(div);
		return root.getNode();
	}
}