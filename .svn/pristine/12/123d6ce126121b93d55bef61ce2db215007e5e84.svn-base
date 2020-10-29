package com.ht.action.background.dicdata.defectitem;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.dicdata.defectitem.DefectItemPage;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.defectitem.DefectItemService;
import com.ht.service.inter.background.dicdata.defecttype.DefectTypeService;

/**
* @ClassName: DefectAction 
* @Description: TODO(缺陷项目) 
* @author penghao
* @date 2016年11月6日 下午2:32:05 
*
 */
@SuppressWarnings("serial")
public class DefectItemAction extends BaseAction{
	
	/**
	 * 注入缺陷项目处理serive
	 */
	@Resource
	private DefectItemService defectItemService;
	
	/**
	 * 注入基础数据处理service
	 */
	@Resource
	private BaseDataService baseDataService;
	
	@Resource
	private DefectTypeService defectTypeSerivce;
	/**
	 * 初始化缺陷主页面
	 * @param
	 * @return
	 */
	public String init(){
		try {
			//实例化页面对象
			DefectItemPage page = DefectItemPage.getInstance();
			//获取页面节点字符串
			String listPage = page.getListPage();
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", listPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
	public String initEdit(){
		String id = getParam("id");
		try {
			//实例化对象
			DefectItemPage page = DefectItemPage.getInstance();
			//初始化编辑页面
			String editPaeg = page.getEditPage(baseDataService, defectItemService,defectTypeSerivce,id);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPaeg);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return SUCCESS;
		
	}
	
	/**
	 * 获取缺陷项目列表
	 */
	public void getDefectItemList(){
		String item = getParam("di");
		try {
			// 获取缺陷项目列表
			List<DefectItem> list = defectItemService.getDefectItemList(item);
			// 返回客户端成功消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 打印日志 
			LogHelper.ERROR.log(e.getMessage());
			//返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 根据id获取项目
	 */
	public void getDefectItemById(){
		String id = getParam("id");
		try {
			// 获取缺陷项目
			DefectItem item = defectItemService.getDefectItemById(id);
			// 返回客户端成功消息
			writeSuccessResult(item);
		} catch (Exception e) {
			// 打印日志 
			LogHelper.ERROR.log(e.getMessage());
			//返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}
	
	public void getDefectItemListByDefectTypeId(){
		String defecttypeId = getParam("defecttypeId");
		try {
			// 获取缺陷项目列表
			List<DefectItem> list = defectItemService.getDefectItemListByDefectTypeId(defecttypeId);
			// 返回客户端成功消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			//返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}
	
	/**
	 * 保存缺陷
	 * @param
	 * @return
	 */
	public void add(){
		String params = getParam("item");
		try {
			defectItemService.add(params);
			// 成功返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			// 返回客户端失败消息
			writeFailResult(e.getMessage());
		}		
	}
	
	public void remove(){
		String ids = getParam("ids");
		try {
			// 根据id删除缺陷
			String msg = defectItemService.delete(ids);
			// 删除成功后返回客户端消息
			writeSuccessResult(msg);
		} catch (Exception e) {
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			//返回客户端失败消息
			writeFailResult(e.getMessage());
}
	}

}
