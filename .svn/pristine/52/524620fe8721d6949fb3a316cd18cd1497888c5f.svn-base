package com.ht.action.complication.formprop;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.formprop.FormPropPage;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.type.Type;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.persistence.model.complication.formprop.FormPropFormView;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.type.TypeService;
import com.ht.service.inter.complication.form.FormService;
import com.ht.service.inter.complication.formprop.FormPropService;

/**
 * 表单属性类控制器
 * @author houchen
 */
@SuppressWarnings("serial")
public class FormPropAction extends BaseAction{
	/**
	 * 注入表单属性Service
	 */
	@Resource(name="formPropService")
	FormPropService formPropService;
	
	/**
	 * 注入表单Service
	 */
	@Resource(name="formService")
	FormService formService;
	
	/**
	 * 注入基础数据baseDataService
	 */
	@Resource(name="baseDataService")
	BaseDataService baseDataService;

	@Resource(name="typeService")
	private TypeService typeService;
	
	
	/**
	 * 初始化表单属性页面，返回成功列表页面
	 */
	public String init(){
		//获取前台传入参数
		String formId = getParam("form_id");
		//创建表单前台页面初始化类
		FormPropPage formProps = new FormPropPage();
		request.setAttribute("html", formProps.getListPage(formId));
		return SUCCESS;
	}
	
	/**
	 * 初始化新增编辑表单属性页面，返回成功页面
	 * */
	public String editInit() {
		//查询表单list
		List<Form> formList;
		//查询基础数据list
		List<BaseData> baseDataList;
		
		//查询基础数据
		List<BaseData> requiredList = null;
		try {
			formList = formService.getForm();
			baseDataList = baseDataService.getBaseDataByTypeId("10301206317200000");
			
			requiredList = baseDataService.getBaseDataByTypeId(BaseDataConstants.REQUIRED);
			List<Type> list = typeService.getType();
			// 接受要修改的属性id
			String id = getParam("id");
			// 接受Formid
			String formId = getParam("form_id");
			//创建表单前台页面初始化类
			FormPropPage formProps = new FormPropPage();
			request.setAttribute("html", formProps.getEditPage(formPropService,id,formList,baseDataList,formId,requiredList,list));
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 新增formProp数据
	 */
	public void addFormProp(){
		try {
			//获取前台传入参数
			String formProp = getParam("formProp");
			//执行保存操作
			formPropService.addFormProp(formProp);
			//返回客户端消息
			writeSuccessResult(formProp);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新FormProp数据
	 * @param formProp json类型的表单属性对象
	 * @throws Exception
	 */
	public void modifyFormProp() {
		// 获取FormProp修改数据
		String formProp = getParam("formProp");
		try {
			// 执行修改操作
			formPropService.modifyFormProp(formProp);
			// 返回客户端消息
			writeSuccessResult(formProp);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除formProp数据 
	 * @param formProp 包含id的表单属性对象
	 * @throws Exception
	 */
	public void removeFormProp() {
		// 获取FormProp标识
		String formProp = getParam("formProp");
		try {
			// 执行删除操作,返回前台判断标识
			formPropService.delFormProp(formProp);
			// 返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 获取FormProp列表
	 */
	public void getFormProp(){
		try {
			//获取前台传入参数
			String formId = getParam("formId");
			//执行查询操作
			List<FormPropFormView> formPropList = formPropService.getFormPropList(formId);
			//返回客户端信息
			writeSuccessResult(formPropList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据Id获取formProp
	 * @param id id
	 * @throws Exception
	 */
	public void getFormPropById() {
		// 获取FormProp标识
		String id = getParam("id");
		try {
			// 执行查询操作
			FormProp formProp = formPropService.getFormProp(id);
			// 返回客户端消息
			writeSuccessResult(formProp);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 初始化基础数据页面，返回成功列表页面
	 */
	public String initBaseData(){
		//创建表单前台页面初始化类
		FormPropPage formProps = new FormPropPage();
		request.setAttribute("html", formProps.getFormBaseListPage());
		return SUCCESS;
	}
	
	/**
	 * 获取基础数据列表
	 */
	public void getBaseData(){
		try {
			//执行查询方法
			List<BaseData> baseDataList = baseDataService.getBaseData();
			writeSuccessResult(baseDataList);
		} catch (Exception e) {
			e.printStackTrace();
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回错误信息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取基础数据
	 */
	public void getBaseDataByKey(){
		try {
			String id = getParam("id");
			BaseData baseData = baseDataService.getBaseData(id);
			writeSuccessResult(baseData);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取前台传回来的基础数据
	 */
	public void getBaseDataList(){
		try{
			String formBd = getParam("formBd");
			System.out.println(formBd);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}
