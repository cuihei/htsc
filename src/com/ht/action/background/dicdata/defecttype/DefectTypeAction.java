package com.ht.action.background.dicdata.defecttype;

import java.util.List;

import javax.annotation.Resource;

import com.ht.action.base.BaseAction;
import com.ht.common.util.LogHelper;
import com.ht.front.pages.background.dicdata.defecttype.DefectTypePage;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.service.inter.background.dicdata.basedata.BaseDataService;
import com.ht.service.inter.background.dicdata.defecttype.DefectTypeService;

/**
* @ClassName: DefectAction 
* @Description: TODO(缺陷类别处理) 
* @author penghao
* @date 2016年11月6日 下午2:32:05 
*
 */
@SuppressWarnings("serial")
public class DefectTypeAction extends BaseAction{
	
	/**
	 * 注入缺陷处理类
	 */
	@Resource
	private DefectTypeService defectTypeService;
	
	/**
	 * 注入基础数据处理
	 */
	@Resource
	private BaseDataService baseDataService;
	
	/**
	 * 初始化类别主页面
	 * @param
	 * @return
	 */
	public String init(){
		try {
			//实例化页面对象
			DefectTypePage page = DefectTypePage.getInstance();
			//获取页面节点字符串
			String listPage = page.getListPage();
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", listPage);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化类别编辑页面
	 * @return
	 */
	public String initEdit(){
		String id = getParam("id");
		try {
			//实例化对象
			DefectTypePage page = DefectTypePage.getInstance();
			//初始化编辑页面
			String editPaeg = page.getEditPage(baseDataService, defectTypeService, id);
			//将获取的节点字符串返回到前台页面
			request.setAttribute("html", editPaeg);
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return SUCCESS;
		
	}
	
	/**
	 * 根据海图类型id获取类别列表的集合
	 * @param charttypeId 海图类型id
	 * @return list 类别集合
	 */
	public void getDefectTypeListByCharttypeId(){
		String charttypeId = getParam("charttypeId");
		try {
			// 获取缺陷列表
			List<DefectType> list = defectTypeService.getDefectTypeListByCharttypeId(charttypeId);
			// 返回客户端成功消息
			writeSuccessResult(list);
		} catch (Exception e) {
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			//返回客户端失败消息
			writeFailResult(e.getMessage());
		}
	}
	
	public void getDefectTypeList(){
		try {
			// 获取缺陷列表
			List<DefectType> list = defectTypeService.getDefectTypeList();
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
		String params = getParam("type");
		try {
			defectTypeService.add(params);
			// 成功返回客户端消息
			writeSuccessResult();
		} catch (Exception e) {
			// 打印日志
			LogHelper.ERROR.log(e.getMessage());
			//返回客户端失败消息
			writeFailResult(e.getMessage());
			
		}		
	}
	
	public void remove(){
		String ids = getParam("ids");
		try {
			// 根据id删除缺陷
			String msg = defectTypeService.delete(ids);
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
