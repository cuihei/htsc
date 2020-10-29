package com.ht.persistence.dao.impl.statisticalanalysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.ht.common.exception.CommonException;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationCompleteStatusDao;
import com.ht.persistence.model.drawtask.plan.Plan;
import com.ht.persistence.model.statisticalanalysis.CompilationCompleteStatus;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.DynamicTableView;

/**
 * BookInfo数据映射操作类
 * @author zyd
 *
 */
public class CompilationCompleteStatusDaoImpl extends BaseDaoImpl implements CompilationCompleteStatusDao {
	
	/**
	 * 获取所有港口航道图完成情况
	 */
	@Override
	public List<CompilationCompleteStatusView> getCompilationCompleteStatus(Date date1,Date date2) {
		if(date1 != null && date2 != null){
			List<CompilationCompleteStatusView> list = null;
			try {
				String hql = "from CompilationCompleteStatusView where completeDate>= :beginDate  and completeDate<= :endDate";
				Query query = this.getSession().createQuery(hql);
				query.setDate("beginDate",date1);     
				query.setDate("endDate",date2);  
				list = query.list();
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage());
			}
			return list;
		}else{
			List<CompilationCompleteStatusView> list = null;
			try {
				String hql = "from CompilationCompleteStatusView ";
				Query query = this.getSession().createQuery(hql);
				list = query.list();
			} catch (Exception e) {
				LogHelper.ERROR.log(e.getMessage());
			}
			return list;
		}
	}
	
	
	
	/**
	 * 根据Id获取一条数据
	 */
	@Override
	public CompilationCompleteStatusView getCompilationCompleteStatusById(CompilationCompleteStatusView ccs) {
		@SuppressWarnings("unchecked")
		List<CompilationCompleteStatusView> result = this.findByNamedQueryAndNamedParam("getCompilationCompleteStatusById", "id", ccs.getId());
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	
	}
	
	/**
	 * 根据Id获取一条数据
	 */
	@Override
	public List<CompilationCompleteStatusView> getCompilationCompleteStatusByYear(CompilationCompleteStatusView ccs) {
		@SuppressWarnings("unchecked")
		List<CompilationCompleteStatusView> result = this.findByNamedQueryAndNamedParam("getCompilationCompleteStatusByYear", "createYear", ccs.getCreateYear());
		return result;
	
	}
	
	/**
	 * 保存一条数据
	 */
	@Override
	public void add(CompilationCompleteStatus ccs) {
		this.save(ccs);
	}
}
