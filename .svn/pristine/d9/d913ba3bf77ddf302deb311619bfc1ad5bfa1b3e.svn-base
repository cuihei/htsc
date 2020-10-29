package com.ht.action.complication.form;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.complication.form.FormPage;
import com.ht.front.pages.complication.form.FormValuePage;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.complication.form.Form;
import com.ht.persistence.model.complication.form.FormBaseDataView;
import com.ht.persistence.model.complication.formprop.FormProp;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.complication.form.FormContentService;
import com.ht.service.inter.complication.form.FormService;
import com.ht.service.inter.complication.formprop.FormPropService;
import com.ht.service.inter.system.symbol.SymbolService;

/**
 * 表单类控制器
 * @author houchen
 */
@SuppressWarnings("serial")
public class FormAction extends BaseAction{
	
	/**
	 * 注入表单Service
	 */
	@Resource(name="formService")
	FormService formService;
	/**
	 * 源数据小改正 符号管理
	 */
	@Resource
	private SymbolService symbolService;
	/**
	 * 注入表单Service
	 */
	@Resource(name="formPropService")
	FormPropService formPropService;
	
	/**
	 * 注入表单Service
	 */
	@Resource(name="formContentService")
	FormContentService formContentService;
	
	/**
	 * 注入基础数据baseDataService
	 */
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	/**
	 * 初始化表单页面，返回成功列表页面
	 */
	public String init(){
		//创建表单前台页面初始化类
		FormPage form = new FormPage();
		request.setAttribute("html", form.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 初始化新增编辑表单页面，返回成功页面
	 * */
	public String editInit() {
		//查询基础数据list
		List<BaseData> baseDataList;
		try {
			baseDataList = baseDataService.getBaseDataByTypeId("10251116389610126");
			// 接受要修改的表单id
			String id = getParam("id");
			//创建表单前台页面初始化类
			FormPage form = new FormPage();
			request.setAttribute("html", form.getEditPage(formService,id,baseDataList));
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
		return SUCCESS;
	}
	
	/**
	 * 新增form数据
	 */
	public void addForm(){
		try {
			//获取前台传入参数
			String form = getParam("form");
			//执行保存操作
			formService.addForm(form);
			//返回客户端消息
			writeSuccessResult(form);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新Form数据
	 * @param form json类型的表单对象
	 * @throws Exception
	 */
	public void modifyForm() {
		// 获取FormProp修改数据
		String form = getParam("form");
		try {
			// 执行修改操作
			formService.modifyForm(form);
			// 返回客户端消息
			writeSuccessResult(form);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 返回客户端消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除form数据 
	 * @param form 包含id的表单对象
	 * @throws Exception
	 */
	public void removeForm() {
		// 获取Form标识
		String form = getParam("form");
		try {
			// 执行删除操作,返回前台判断标识
			formService.delForm(form);
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
	 * 获取Form列表
	 */
	public void getForm(){
		try {
			//执行查询操作
			List<FormBaseDataView> formList = formService.getFormList();
			//返回客户端信息
			writeSuccessResult(formList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			//返回客户端错误消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据Id获取form
	 * @param id id
	 * @throws Exception
	 */
	public void getFormById() {
		// 获取FormProp标识
		String id = getParam("id");
		try {
			// 执行查询操作
			Form form = formService.getForm(id);
			// 返回客户端消息
			writeSuccessResult(form);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据BaseDataId获取form
	 * @param id id
	 * @throws Exception
	 */
	public void getFormByBaseDataId() {
		// 获取FormProp标识
		String id = getParam("baseDataId");
		try {
			// 执行查询操作
			List<Form> list = formService.getFormByBaseDataId(id);
			// 返回客户端消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 一键生成form属性编辑页面
	 * @throws Exception 
	 */
	public void addAllForm() {
		try {
			List<Form> formList = formService.getForm();
			for (Form fp : formList) {
				String formId = fp.getId();
				if (formId != null) {
					// 创建应用资源界面初始化类ApplicationView
					FormValuePage formValuePage = new FormValuePage();
					// 获取基础类对象的List集合
					List<FormProp> formPropList = null;
					String forPage = null;

					formPropList = formPropService.getFormPropByFormId(formId);
					// 根据FormId获取Form
					Form form = formService.getForm(formId);
					String formName = form.getName();
					forPage = formValuePage.getInitForm(formPropList, formName,symbolService,baseDataService);

					// 将生成的编辑页面添加到form表单内容表中
					formContentService.addFormContent(forPage, formId);
				}
			}
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}

	}
}
