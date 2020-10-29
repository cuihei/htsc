package com.ht.action.datum.type;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.DataConverter;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.datum.type.DatumCategoryPage;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.datum.datum.DatumFileService;
import com.ht.service.inter.datum.type.DatumCategoryService;


/**
 * 资料类别action
 * @author zyd
 */
@SuppressWarnings("serial")
public class DatumCategoryAction extends BaseAction{
	
	/**
	 * 注入资料类别Service
	 */
	@Resource(name="datumcategoryService")
	DatumCategoryService datumcategoryService;
	
	/**
	 * 注入DatumFileService
	 */
	@Resource(name="datumFileService")
	DatumFileService datumFileService;
	
	
	/**
	 * 初始化资料类别页面，返回成功列表页面
	 * @return
	 */
	public String init(){
		// 创建资料类别页面初始化类
		DatumCategoryPage category = new DatumCategoryPage();
		// 将获取的节点字符串返回给前台页面
		request.setAttribute("html", category.getListPage());
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑资料类别数据页面，返回成功页面
	 * @throws Exception 
	 * */
	public String editInit() throws Exception {
		List<DatumCategory> datumcategoryList = datumcategoryService.getDatumCategory();
		// 接收前台传的Id
		String id = getParam("id");
		// 接收前台传的修改标识
		String flag = getParam("flag");
		DatumCategoryPage category = new DatumCategoryPage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", category.getEditNode(flag,datumcategoryService, id, datumcategoryList));
		return SUCCESS;
	}
	
	/**
	 * 新增资料类别
	 */
	public void addDatumCategory(){
		try {
			// 获取前台传入参数
			String datumCategory = getParam("dcategory");
			datumcategoryService.addDatumCategory(datumCategory);
			writeSuccessResult();
		} catch (Exception e) {
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 更新资料类别数据
	 */
	public void modifyDatumCategory(){
		try {
			// 获取前台传入参数
			String datumCategory = getParam("datumCategory");
			datumcategoryService.modifyDatumCategory(datumCategory);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 删除资料类别数据
	 */
	public void removeDatumCategory(){
		// 获取资料类别标识
		String datumCategoryId = getParam("datumCategoryId");
		try {
			datumcategoryService.delDatumCategory(datumCategoryId);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取资料类别列表
	 */
	public void getDatumCategory(){
		try {
			List<DatumCategory> datumcategoryList = datumcategoryService.getDatumCategory();
			respose.getWriter().write(DataConverter.convertObject2Json(datumcategoryList));
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取资料类别
	 */
	public void getDatumCategoryByKey(){
		try {
			// 获取资料类别标识
			String id = getParam("id");
			DatumCategory datumcategory = datumcategoryService.getDatumCategory(id);
			writeSuccessResult(datumcategory);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获所有子节点的集合，形树图
	 */
	public void getDatumCategoryTree(){
		// 获取页面返回的参数，资源ID
		try {
			String treeJson = datumcategoryService.getDatumCategoryTree();
			writeSuccessResult(treeJson);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 获取部分资料
	 */
	public void getFile() {
		// 接收前台传的参数
		String datumCategoryId = getParam("datumCategoryId");
		String datumCategoryParentId = getParam("datumCategoryParentId");
		try {
			
			if(!"201610301150".equals(datumCategoryId)){
				// 获取部分资料
				int result = datumFileService.checkIfExist(datumCategoryId,datumCategoryParentId);
				writeSuccessResult(result);
			}else{
				writeSuccessResult("root");
			}
		}catch(Exception e) {
			LogHelper.ERROR.log(e.getMessage(),e);
			// 写失败信息到客户端
			writeFailResult(e.getMessage());
		}
	}
}
