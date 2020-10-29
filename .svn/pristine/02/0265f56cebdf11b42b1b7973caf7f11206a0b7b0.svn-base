package com.ht.persistence.dao.impl.statisticalanalysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationCompleteStatusDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationMonthPlanDao;
import com.ht.persistence.dao.inter.statisticalanalysis.DynamicTableDao;
import com.ht.persistence.model.statisticalanalysis.CompilationMonthPlan;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationMonthPlanView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;

/**
 * BookInfo数据映射操作类
 * @author zyd
 *
 */
public class CompilationMonthPlanDaoImpl extends BaseDaoImpl implements CompilationMonthPlanDao {
	
	/**
	 * 获取所有港口航道图完成情况
	 */
	@Override
	public List<CompilationMonthPlanView> getCompilationMonthPlan(Date date1,Date date2) {
		if(date1 != null && date2 != null){
			List<CompilationMonthPlanView> list = null;
			try {
				String hql = "from CompilationMonthPlanView where planExchangeTime>= :beginDate  and planExchangeTime<= :endDate";
				Query query = this.getSession().createQuery(hql);
				query.setDate("beginDate",date1);     
				query.setDate("endDate",date2);  
				list = query.list();
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage());
			}
			return list;
		}else{
			List<CompilationMonthPlanView> list = null;
			try {
				String hql = "from CompilationMonthPlanView ";
				Query query = this.getSession().createQuery(hql);
				list = query.list();
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage());
			}
			return list;
		}
	}
	
	/**
	 * 获取一条港口航道图月度编绘计划
	 */
	@Override
	public CompilationMonthPlanView getCompilationMonthPlanById(CompilationMonthPlanView cmp) {
		try {
			// 执行查询
//			String hql = "from CompilationMonthPlanView where id = :id";
//			Query query = this.getSession().createQuery(hql);
//			query.setParameter("id", cmp.getId());
//			List<CompilationMonthPlanView> result = query.list();
			Session session = this.getSessionFactory().openSession();
			Query setParameter =session.createSQLQuery("select * from V_COMPILATION_MONTH_PLAN where id = :id").addEntity(CompilationMonthPlanView.class).setParameter("id", cmp.getId());
			List<CompilationMonthPlanView>  result=setParameter.list();
			session.flush();
			session.close();
			if(result.size() > 0){
				return result.get(0);
			}
			
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 新增一条月度编绘计划
	 * @param c 月度编绘计划
	 * @throws Exception
	 */
	@Override
	public void addCompilationMonthPlan(CompilationMonthPlan c) {
		this.save(c);
	}

	@Override
	public List<CompilationMonthPlanView> getCompilationMonthPlanByYear(CompilationMonthPlanView cmp) {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<CompilationMonthPlanView> result = this.findByNamedQueryAndNamedParam("getCompilationMonthPlanByYear", "year", cmp.getYear());
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
}
