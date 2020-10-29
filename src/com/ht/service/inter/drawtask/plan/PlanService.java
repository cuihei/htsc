package com.ht.service.inter.drawtask.plan;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.plan.PlanFile;
import com.ht.persistence.model.drawtask.plan.VPlan;


/** 
* @ClassName: PlanService 
* @Description: 计划接口
* @author penghao
* @date 2016年10月12日 上午11:19:48 
*  
*/
public interface PlanService{
	
	/**
	 * 根据ID获取计划
	 * @param id ID
	 * @return Plan实体对象
	 */
	Plan getPlanById(String id) throws Exception;
	
	/**
	 * 获取计划列表
	 * @param year 
	 * @param
	 * @return 实体对象集合
	 */
	List<VPlan> getPlanList(String categroryId, String year) throws Exception;
	
	/**
	 * 编辑计划
	 * @param String param json字符串
	 * @return 
	 */
	void addPlan(String param,String categoryId,String type) throws Exception;

	/**
	 * 删除计划
	 * @param ids 多个id
	 * @return
	 */
	void delPlan(String ids) throws Exception;
	
	/**
	 * 导入计划
	 * @param upload
	 * @param planType
	 */
	String addPlanByExcel(File upload, String type,String categoryId) throws Exception;
	
	/**
	 * 下载模板
	 * @param
	 * @return
	 */
	InputStream getTemplate(String type,String sheetName) throws Exception;
	
	/**
	 * 下载计划
	 * @param
	 * @return
	 */
	InputStream export(String year,String type,String categoryId,String sheetName) throws Exception;

	/**
	 * 获取月计划计划列表
	 * 
	 * @param
	 * @return
	 */
	List<VPlan> getMonthPlanList(String categoryId, String dateStr)throws Exception;

	/**
	 * 获取年度布局计划列表
	 * 
	 * @param
	 * @return
	 */
	List<PlanFile> getPlanAttachmentList(String year) throws Exception;

	/**
	 * 附件上传
	 * @throws Exception 
	 */
	void uploadFile(String booksId, File upload, String uploadFileName)throws Exception;

	/**
	 * 文件下载
	 * @throws Exception 
	 */
	void downloadFile(String id) throws Exception;

	/**
	 * 根据id查附件
	 */
	String getUrlById(String id);

	/**
	 * 删除年计划部局
	 * 
	 * @param String
	 *            ids
	 * @return
	 */
	void delPlanFilesRemove(String ids) throws Exception;

	PlanFile getPlanFileById(PlanFile pf);
}
