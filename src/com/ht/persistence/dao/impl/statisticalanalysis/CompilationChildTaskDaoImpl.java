package com.ht.persistence.dao.impl.statisticalanalysis;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationChildTaskDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.statisticalanalysis.view.CompilationChildTaskView;

/**
 * CompilationChildTaskDaoImpl数据映射操作类
 * @author liukai
 *
 */
public class CompilationChildTaskDaoImpl extends BaseDaoImpl implements CompilationChildTaskDao {
	
	/**
	 * 获取所有港口航道图完成情况
	 */
	@Override
	public List<CompilationChildTaskView> getCompilationChildTask(CompilationChildTaskView c,String userId) {
		List<CompilationChildTaskView> list = null;
		String hql = null;
		try {
			
			String sqluser=null;
		   ///如果是王娟 则不需要添加审定完成  节点到王娟那儿的时候 名称也是审定完成，
			//会造成王娟看不到任务
			if(userId.contains("11031828385090008")||userId.contains("11031827241200002")) {
			sqluser=" and 1=1  ";
			}else {
			sqluser=" and status not like '%审定完成%'  ";	
			}
			
			if(!c.getMapNo().equals("null")){
				hql = "from CompilationChildTaskView where mapNo = :mapNo and taskbookNo like :taskbookNo and taskName = :taskName and childTaskType like :childTaskType "+sqluser+" order by creationDate desc";
				Query query = this.getSession().createQuery(hql);
				query.setString("mapNo",c.getMapNo()); 
				query.setString("taskbookNo","%"+c.getTaskbookNo()+"%");
				query.setString("taskName",c.getTaskName()); 
				query.setString("childTaskType","%"+c.getChildTaskType()+"%"); 
				list = query.list();
			}else{
				hql = "from CompilationChildTaskView where mapNo is null and taskbookNo like :taskbookNo and taskName = :taskName and childTaskType like :childTaskType   "+sqluser+"  order by creationDate desc";
				Query query1 = this.getSession().createQuery(hql);
				query1.setString("taskName",c.getTaskName());
				query1.setString("taskbookNo","%"+c.getTaskbookNo()+"%");
				query1.setString("childTaskType","%"+c.getChildTaskType()+"%"); 
				list = query1.list();
			}
			
			
			
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
	
	
	
	/**
	 * 获取所有港口航道图完成情况 先前的查询待办  上面是2019.5.22 修改过
	 */
	@Override
	public List<CompilationChildTaskView> getCompilationChildTask_old(CompilationChildTaskView c) {
		List<CompilationChildTaskView> list = null;
		String hql = null;
		try {

			if(!c.getMapNo().equals("null")){
				hql = "from CompilationChildTaskView where mapNo = :mapNo and taskbookNo like :taskbookNo and taskName = :taskName and childTaskType like :childTaskType  order by creationDate desc";
				Query query = this.getSession().createQuery(hql);
				query.setString("mapNo",c.getMapNo()); 
				query.setString("taskbookNo","%"+c.getTaskbookNo()+"%");
				query.setString("taskName",c.getTaskName()); 
				query.setString("childTaskType","%"+c.getChildTaskType()+"%"); 
				list = query.list();
			}else{
				hql = "from CompilationChildTaskView where mapNo is null and taskbookNo like :taskbookNo and taskName = :taskName and childTaskType like :childTaskType   order by creationDate desc";
				Query query1 = this.getSession().createQuery(hql);
				query1.setString("taskName",c.getTaskName());
				query1.setString("taskbookNo","%"+c.getTaskbookNo()+"%");
				query1.setString("childTaskType","%"+c.getChildTaskType()+"%"); 
				list = query1.list();
			}
			
			
			
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 工作台获取任务
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public CompilationChildTaskView getVTaskById(String   id) {
		List<CompilationChildTaskView> result = null;
		String hql = null;
		try {
				hql = "from CompilationChildTaskView where id = :id ";
				Query query = this.getSession().createQuery(hql);
				query.setString("id",id); 
				result = query.list();
				if (result != null)
				{
					if (result.size()>0)
					{
						return result.get(0);
					}
				}
			
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return null;
	}
	
	
	
	
	/**
	 * 获取所有年份完成情况
	 */
/*	@Override
	public List<CompilationChildTaskView> getCompilationChildTaskAllYear(CompilationChildTaskView c) {
		List<CompilationChildTaskView> list = null;
		String hql = null;
		try {
				hql = "from CompilationChildTaskView where mapNo = :mapNo and taskbookNo like :taskbookNo and taskName = :taskName and childTaskType like :childTaskType order by creationDate desc";
				Query query = this.getSession().createQuery(hql);
				query.setString("mapNo",c.getMapNo()); 
				query.setString("taskbookNo","%"+c.getTaskbookNo()+"%");
				query.setString("taskName",c.getTaskName()); 
				query.setString("childTaskType","%"+c.getChildTaskType()+"%");
				list = query.list();
	
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}*/
	
	
	/**
	 * 获取所有港口航道图完成情况
	 */
	@Override
	public List<String> getYearList() {
		List<String> list = null;
		try {
			SQLQuery query = this.getSession().createSQLQuery("select DISTINCT TO_CHAR(CREATION_DATE,'yyyy') from V_COMPILATION_CHILD_TASK ");
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return list;
	}
}
