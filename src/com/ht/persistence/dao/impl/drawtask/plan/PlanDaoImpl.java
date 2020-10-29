package com.ht.persistence.dao.impl.drawtask.plan;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.util.CollectionUtils;

import com.ht.common.exception.CommonException;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.drawtask.plan.PlanDao;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.drawtask.plan.PlanFile;
import com.ht.persistence.model.drawtask.plan.VPlan;


/** 
* @ClassName: PlanDaoImpl 
* @Description: 计划数据处理类
* @author penghao
* @date 2016年10月19日 下午5:35:36 
*  
*/
@SuppressWarnings("unchecked")
public class PlanDaoImpl extends BaseDaoImpl implements PlanDao {

	
	/**
	 * 获取计划
	 * @param id
	 * @return
	 */
	@Override
	public Plan getPlanById(Plan plan) throws Exception {
		List<Plan> result = null;
		try {
			//获取年计划
			result = this.findByNamedQueryAndNamedParam("findPlanById", "id", plan.getId());
			if(CollectionUtils.isEmpty(result)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return result.get(0);
	}

	/**
	 * 获取计划列表
	 * @param 
	 * @return
	 */
	@Override
	public List<VPlan> getPlanListByTyepId(Plan plan) throws Exception {	
		String categoryId = "";
		if (plan.getCategory() != null && StringUtils.isNotBlank(plan.getCategory().getId()))
		{
			categoryId = plan.getCategory().getId();
		}
		String year = "";
		if (plan != null && plan.getYear() != null)
		{
			year = plan.getYear();
		}
		String hql = "from VPlan where category.id =? and year =  ? order by creationDate desc";  //带年份 条件  重复图号不同年份 默认无法显示出来  2018.5.31
		//String hql = "from VPlan where category.id =? order by creationDate desc";
		List<VPlan> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, categoryId);
			query.setParameter(1, year);
			/*query.setParameter(2, end);*/
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 获取月计划列表
	 * @param 
	 * @return
	 */
	@Override
	public List<VPlan> getMonthPlanListByTyepId(Plan plan) throws Exception {	
		String categoryId = "";
		if (plan.getCategory() != null && StringUtils.isNotBlank(plan.getCategory().getId()))
		{
			categoryId = plan.getCategory().getId();
		}
		String year = "";
		if (plan.getYear() != null && StringUtils.isNotBlank(plan.getYear()))
		{
			year = plan.getYear();
		}
		String month = "";
		if (plan.getPlanMonth() != null && StringUtils.isNotBlank(plan.getPlanMonth()))
		{
			month = plan.getPlanMonth();
		}
		String hql = "from VPlan where category.id =? and year = ? and planMonth = ?"; //带年份 条件  重复图号不同年份 默认无法查出来  2018.5.31
		//String hql = "from VPlan where category.id =? ";
		List<VPlan> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, categoryId);
			query.setParameter(1, year);
			query.setParameter(2, month);
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
			throw new CommonException("数据库错误，请联系管理员！");
		}
		return list;
	}
	
	/**
	 * 获取年度布局计划列表
	 * @param 
	 * @return
	 */
	@Override
	public List<PlanFile> getPlanAttachmentList(PlanFile pf) throws Exception {	
		Date start = null;
		Date end = null;
		if (pf != null && pf.getPlanYear() != null)
		{
			start = pf.getPlanYear();
			Calendar cal = Calendar.getInstance();
			cal.setTime(start);
			cal.add(Calendar.YEAR, 1);
			end = cal.getTime();
	        
		}
		String hql = "from PlanFile where planYear between ? and ? order by creationDate desc";
		List<PlanFile> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, start);
			query.setParameter(1, end);
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 判断实体对象是否存在
	 * @param mapNo 图号
	 * @return

	@Override
	public boolean exists(Plan plan) throws Exception {
		List<Plan> list = null;
		
		try {
			String[] names = {"mapNo","categoryId", year};
			String categoryId = "";
			if(plan.getCategory() != null && StringUtils.isNotEmpty(plan.getCategory().getId())){
				categoryId = plan.getCategory().getId();
			}
			String[] params = {plan.getMapNo(),categoryId};
			//判断是否存在
			list = this.findByNamedQueryAndNamedParam("findPlanByMapNoAndCategoryId", names, params);
			//不存在
			if(CollectionUtils.isEmpty(list)){
				return false;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		//存在
		return true;
	}
	 */
	
	/**
	 * 判断实体对象是否存在
	 * @param mapNo 图号
	 * @param year 年份
	 * 2018.6.1  指令任务 判重依据 图号 图名 年份判断
	 * @return
	 */
		@Override
	public boolean exists(Plan plan) throws Exception {
		List<Plan> list = null;
	
		try {
			String categoryId = "";
			if(plan.getCategory() != null && StringUtils.isNotEmpty(plan.getCategory().getId())){
				categoryId = plan.getCategory().getId();
			}
			String year = "";
			if (plan.getYear() != null && StringUtils.isNotBlank(plan.getYear()))
			{
				year = plan.getYear();
			}
			String mapNo = "";
			if (plan.getMapNo() != null && StringUtils.isNotBlank(plan.getMapNo()))
			{
				mapNo = plan.getMapNo();
			}
			
			String hql = "from VPlan where category.id =? and year = ?  and mapNo = ?"; 
			Query query = this.getSession().createQuery(hql);
			query.setParameter(0, categoryId);
			query.setParameter(1, year);
			query.setParameter(2, mapNo);
			list = query.list();
			
			if(CollectionUtils.isEmpty(list))
			{
				return false;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		//存在
		return true;
	}
	
		
	
	/**
	 * 判断实体对象是否存在
	 * @param mapNo 图号
	 * @return
	 */
	@Override
	public List<Plan> getPlanListByMapNo(Plan plan) throws Exception {
		List<Plan> list = null;
		try {
			String[] names = {"mapNo","categoryId"};
			String categoryId = "";
			if(plan.getCategory() != null && StringUtils.isNotEmpty(plan.getCategory().getId())){
				categoryId = plan.getCategory().getId();
			}
			String[] params = {plan.getMapNo(),categoryId};
			//判断是否存在
			list = this.findByNamedQueryAndNamedParam("findPlanByMapNoAndCategoryId", names, params);
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}

	/**
	 * 添加计划
	 * @param plan
	 * @return
	 */
	@Override
	public void addPlan(Plan plan) throws Exception {
		this.save(plan);
	}

	/**
	 * 更新计划
	 * @param plan
	 * @return
	 */
	@Override
	public void modifyPlan(Plan plan) throws Exception {
		plan  = (Plan)this.merge(plan);
		this.update(plan);
	}

	/**
	 * 删除计划
	 * @param plan
	 * @return
	 */
	@Override
	public void delPlan(Plan plan) throws Exception {
		this.delete(plan);
	}

	/**
	 * 添加年度布局附件
	 */
	@Override
	public void addBookFile(PlanFile PlanFile) {
		this.save(PlanFile);
	}
	
	/**
	 * 跟id查找附件信息
	 * @throws Exception 
	 */
	@Override
	public PlanFile getPlanFileById(PlanFile pf) {
		List<PlanFile> list = null;
		try {
			list = this.findByNamedQueryAndNamedParam("getPlanFileById", "id", pf.getId());
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list.get(0);
	}
	
	/**
	 * 删除计划
	 * @param plan
	 * @return
	 */
	@Override
	public void delPlanFilesRemove(PlanFile planFile) throws Exception {
		this.delete(planFile);
	}
	
}
