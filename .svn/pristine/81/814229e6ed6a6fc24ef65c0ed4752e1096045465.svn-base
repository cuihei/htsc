package com.ht.action.catalog.type;


import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.catalog.type.CatalogTypePage;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.catalog.type.CatalogTypeService;


/**
 * 处理目录类型的Action类 
 * @author penghao
 */

@SuppressWarnings("serial")
public class CatalogTypeAction extends BaseAction{
	
	//注入基础数据baseDataService
	@Resource(name="baseDataService")
	BaseDataService baseDataService;
	
	@Resource
	CatalogTypeService catalogTypeService;
	
	//文件下载
	private InputStream downloadStream;
	//文件下载名称
	private String fileName;
	
	
	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	* @Title: index 
	* @Description: 初始化目录类型的页面
	* @param:
	* @return String    返回类型 
	* @throws
	 */
	public String index(){
		//实例化对象
		CatalogTypePage page = new CatalogTypePage();
		//获取页面节点字符串
		String listNode = page.getListNode();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", listNode);
		return SUCCESS;
	}
	
	/**
	 * 初始化编辑目录类型数据页面，返回成功页面
	 * @throws Exception 
	 * */
	public String initEdit() throws Exception {
		// 接受要修改的编号
		String id = getParam("id");
		CatalogTypePage catalogType = new CatalogTypePage();
		//将获取的节点字符串返回到前台页面
		request.setAttribute("html", catalogType.getEditNode(baseDataService,id));
		return SUCCESS;
	}

	/**
	* @Title: getCatalogTpyeList 
	* @Description: 从基础字典表中获取目录类型列表 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void getCatalogTypeList(){
		try {
			String typeId = BaseDataConstants.MULX_TYPE_ID;
			List<BaseData> detailList = baseDataService.getBaseDataByTypeId(typeId);
			writeSuccessResult(detailList);
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	* @Title: modifyCatalogType 
	* @Description: 修改目录类型 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public void modifyCatalogType(){
		try {
			//获取baseDataParam对象
			String baseDataParam = getParam("type");
			//将baseDataParam对象转换为json
			catalogTypeService.modifyData(baseDataParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	* @Title: delCatalogType 
	* @Description: 删除目录类型 
	* @param     设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void delCatalogType(){
		try {
			// 获取基础数据标识
			String typeParam = getParam("type");
			baseDataService.delBaseData(typeParam);
			writeSuccessResult();
		} catch (Exception e) {
			// 写入错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			writeFailResult(e.getMessage());
		}
	}

	/**
	 * 导出
	 * @throws Exception 
	 */
	public void export() throws Exception{
		catalogTypeService.export();
	}
}
