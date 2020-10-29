package com.ht.persistence.dao.inter.drawtask.plan;

import java.util.List;

import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.plan.PlanFile;
import com.ht.persistence.model.drawtask.plan.VPlan;



/** 
* @ClassName: PlanDao 
* @Description: 计划数据接口
* @author penghao
* @date 2016年10月19日 下午5:28:38 
*  
*/
public interface PlanDao {
	
	/**
	 * 获取计划
	 * @param id
	 * @return
	 */
	Plan getPlanById(Plan plan) throws Exception;
	
	/**
	 * 获取年度计划列表
	 * @param year 
	 * @param 
	 * @return
	 */
	List<VPlan> getPlanListByTyepId(Plan plan) throws Exception;

	/**
	 * 判断实体对象是否存在
	 * @param mapNo 图号
	 * @return
	 */
	boolean exists(Plan plan) throws Exception;
	
	/**
	 * 判断实体对象是否存在
	 * @param mapNo 图号
	 * @return
	 */
	List<Plan> getPlanListByMapNo(Plan plan) throws Exception;
	
	/**
	 * 添加实体对象
	 * @param plan
	 * @return
	 */
	void addPlan(Plan plan) throws Exception;

	/**
	 * 更新实体对象
	 * @param plan
	 * @return 
	 */
	void modifyPlan(Plan plan) throws Exception;

	/**
	 * 删除实体对象
	 * @param entity
	 * @return
	 */
	void delPlan(Plan plan) throws Exception;

	/**
	 * 获取月计划列表
	 * @param 
	 * @return
	 */
	List<VPlan> getMonthPlanListByTyepId(Plan plan) throws Exception;

	/**
	 * 获取年度布局计划列表
	 * @param 
	 * @return
	 */
	List<PlanFile> getPlanAttachmentList(PlanFile pf) throws Exception;

	/**
	 * 添加年度布局附件
	 */
	void addBookFile(PlanFile PlanFile);

	/**
	 * 跟id查找附件信息
	 * @throws Exception 
	 */
	PlanFile getPlanFileById(PlanFile pf);

	/**
	 * 删除计划
	 * @param plan
	 * @return
	 */
	void delPlanFilesRemove(PlanFile planFile) throws Exception;

}
