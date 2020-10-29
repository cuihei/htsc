package com.ht.persistence.dao.impl.statisticalanalysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.defectform.DefectFormDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationWorkDaysDao;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationYearPlanDao;
import com.ht.persistence.model.background.application.Application;
import com.ht.persistence.model.background.dicdata.defectform.DefectForm;
import com.ht.persistence.model.statisticalanalysis.CompilationRealWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationWorkDays;
import com.ht.persistence.model.statisticalanalysis.CompilationYearPlan;
import com.ht.persistence.model.statisticalanalysis.view.CompilationYearPlanView;
import com.ht.persistence.model.statisticalanalysis.view.YearPlanView;
import com.ht.service.constant.experiencebook.ChartStatusConstants;

/**
 * BookInfo数据映射操作类
 * @author zyd
 *
 */
public class CompilationYearPlanDaoImpl extends BaseDaoImpl implements CompilationYearPlanDao {
	
	 /**
     * 注入roleDao
     */
	@Resource(name = "defectFormDao") 
	private DefectFormDao defectFormDao;
	
	/**
	 * 注入工天管理Dao
	 * */
	@Resource
	private CompilationWorkDaysDao workDaysDao;
	
	/**
	 * 获取所有编绘计划
	 */
	@Override
	public List<YearPlanView> getCompilationYearPlan() {
		try {
			@SuppressWarnings("unchecked")
			List<YearPlanView> outList =  new ArrayList<YearPlanView>();
			// 执行查询
			SQLQuery query = this.getSession().createSQLQuery("select * from V_COMPILATION_YEAR_PLAN ");
			query.addEntity(CompilationYearPlanView.class);
			
			@SuppressWarnings("unchecked")
			List<CompilationYearPlanView> result =  query.list();
			Map<String, String> ids  = new HashMap<String, String>();
			if(result.size() > 0){
				for (int i = 0; i < result.size(); i++) {
					if(!ids.containsKey(result.get(i).getId())){
						ids.put(result.get(i).getId(), result.get(i).getCompilationContent());
					}
				}
				List<CompilationYearPlanView> getSdEnd = new ArrayList<CompilationYearPlanView>();
				Iterator<Entry<String, String>> entries = ids.entrySet().iterator();
				while (entries.hasNext()) {
					YearPlanView y = new YearPlanView();
				    Entry<String, String> entry = entries.next();  
				    if(entry.getValue().equals("源数据编绘")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_distribution_worker"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_work"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_distribution_zhijian"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime()==null?getZjEnd.get(0).getStartTime():getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_distribution_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_shending_sdjielunb"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    	
				    }else if(entry.getValue().equals("纸海图编绘")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_distribution_worker"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_wtcljlb"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_distribution_zhijian"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_zhijian_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime()==null?getZjEnd.get(0).getStartTime():getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_distribution_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_shending_sdjielunb"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }else if(entry.getValue().equals("电子海图编绘")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_distribution_worker"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_work"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_distribution_zhijian"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_distribution_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_shending_sdjielunb"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }else if(entry.getValue().equals("源数据小改正编绘")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_fenpei_bjy"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_wtcljlb"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_wtcljlb"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getEndTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_fenpei_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_zjy_queren"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }else if(entry.getValue().equals("改正通告编辑")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_bjqkjlb"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_bjqkjlb"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_bjqkjlb"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_fenpei_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_zj_queren"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }else if(entry.getValue().equals("改正通告模板编辑")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_fenpei_bjy"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_qkjlb"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_qkjlb"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_fenpei_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_zjy_queren"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }else if(entry.getValue().equals("纸海图小改正")||entry.getValue().equals("电子海图小改正")||entry.getValue().equals("工程&专题纸海图")||entry.getValue().equals("工程&专题电子海图")){
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_distribution_worker"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_bhjlb"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_distribution_zhijian"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_distribution_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_zjy_queren"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }else{
				    	//编绘作业数据
				    	String[] paramNames={"id","taskDefId"};
				    	String[] values={entry.getKey(),"u_task_distribution_worker"};
				    	List<CompilationYearPlanView> getBhStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values);
				    	Date compilationStartTime = null;
				    	if(getBhStart.size()>0){
				    		String compilationClerk = getBhStart.get(0).getAssigneeName();
				    		y.setCompilationClerk(compilationClerk);
				    		compilationStartTime = getBhStart.get(0).getStartTime();
				    		y.setCompilationStartTime(compilationStartTime);
				    	}
				    	this.clear();
				    	Date compilationEndTime = null;
				    	String[] values1={entry.getKey(),"u_task_bh"};
				    	List<CompilationYearPlanView> getBhEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values1);
				    	if(getBhEnd.size()>0){
				    		compilationEndTime = getBhEnd.get(0).getEndTime();
				    		y.setCompilationEndTime(compilationEndTime);
				    	}
				    	
				    	//编绘质检作业数据
				    	this.clear();
				    	String[] values2={entry.getKey(),"u_task_distribution_zhijian"};
				    	List<CompilationYearPlanView> getZjStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values2);
				    	Date qualityStartTime = null;
				    	if(getZjStart.size()>0){
				    		String inspector = getZjStart.get(0).getAssigneeName();
				    		y.setInspector(inspector);
				    		qualityStartTime = getZjStart.get(0).getStartTime();
				    		y.setQualityStartTime(qualityStartTime);
				    	}
				    	Date qualityEndTime = null;
				    	this.clear();
				    	String[] values3={entry.getKey(),"u_task_ztkz_shenhe"};
				    	List<CompilationYearPlanView> getZjEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values3);
				    	if(getZjEnd.size()>0){
				    		qualityEndTime = getZjEnd.get(0).getEndTime();
				    		y.setQualityEndTime(qualityEndTime);
				    	}
				    	
				    	//审定作业数据
				    	this.clear();
				    	String[] values4={entry.getKey(),"u_task_distribution_shending"};
				    	List<CompilationYearPlanView> getSdStart = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values4);
				    	Date authorizedStartTime = null;
				    	if(getSdStart.size()>0){
				    		String authorizedOfficer = getSdStart.get(0).getAssigneeName();
				    		y.setAuthorizedOfficer(authorizedOfficer);
				    		authorizedStartTime = getSdStart.get(0).getStartTime();
				    		y.setAuthorizedStartTime(authorizedStartTime);
				    	}
				    	Date authorizedEndTime = null;
				    	this.clear();
				    	String[] values5={entry.getKey(),"u_task_zjy_queren"};
				    	getSdEnd = this.findByNamedQueryAndNamedParam("getCompilationYearPlanByIdAndDefId",paramNames,values5);
				    	if(getSdEnd.size()>0){
				    		authorizedEndTime = getSdEnd.get(0).getEndTime();
				    		y.setAuthorizedEndTime(authorizedEndTime);
				    	}
				    }
				    String processInstId = null;
				    String processDefKey = null;
				    String mapNo = null;
				    if(getSdEnd.size()>0){
				    	y.setId(getSdEnd.get(0).getId());
				    	y.setMapNo(getSdEnd.get(0).getMapNo());
				    	y.setMapName(getSdEnd.get(0).getMapName());
				    	y.setScale(getSdEnd.get(0).getScale());
				    	y.setFrederickCycle(getSdEnd.get(0).getFrederickCycle());
				    	y.setLastTimeProperty(getSdEnd.get(0).getLastTimeProperty());
				    	y.setLastTimeDate(getSdEnd.get(0).getLastTimeDate());
				    	y.setPlanType(getSdEnd.get(0).getPlanType());
				    	y.setPlanExchangeTime(getSdEnd.get(0).getPlanExchangeTime());
				    	y.setActualExchangeTime(getSdEnd.get(0).getActualExchangeTime());
				    	y.setTaskReleaseTime(getSdEnd.get(0).getTaskReleaseTime());
				    	y.setPlanCompleteTime(getSdEnd.get(0).getPlanCompleteTime());
				    	y.setCompilationProperty(getSdEnd.get(0).getCompilationProperty());
				    	y.setCompilationContent(getSdEnd.get(0).getCompilationContent());
				    	y.setAdjustmentCoefficient(getSdEnd.get(0).getAdjustmentCoefficient());
				    	y.setType(getSdEnd.get(0).getType());
				    	processInstId = getSdEnd.get(0).getProcessInstId();
				    	processDefKey = getSdEnd.get(0).getProcessDefKey();
				    	mapNo = getSdEnd.get(0).getMapNo();
				    }else{
				    	CompilationYearPlanView cyp = new CompilationYearPlanView();
				    	cyp.setId(entry.getKey());
				    	cyp = getCompilationYearPlanById(cyp);
				    	y.setId(cyp.getId());
				    	y.setMapNo(cyp.getMapNo());
				    	y.setMapName(cyp.getMapName());
				    	y.setScale(cyp.getScale());
				    	y.setFrederickCycle(cyp.getFrederickCycle());
				    	y.setLastTimeProperty(cyp.getLastTimeProperty());
				    	y.setLastTimeDate(cyp.getLastTimeDate());
				    	y.setPlanType(cyp.getPlanType());
				    	y.setPlanExchangeTime(cyp.getPlanExchangeTime());
				    	y.setActualExchangeTime(cyp.getActualExchangeTime());
				    	y.setTaskReleaseTime(cyp.getTaskReleaseTime());
				    	y.setPlanCompleteTime(cyp.getPlanCompleteTime());
				    	y.setCompilationProperty(cyp.getCompilationProperty());
				    	y.setCompilationContent(cyp.getCompilationContent());
				    	y.setAdjustmentCoefficient(cyp.getAdjustmentCoefficient());
				    	y.setType(cyp.getType());
				    	processInstId = cyp.getProcessInstId();
				    	processDefKey = cyp.getProcessDefKey();
				    	mapNo = cyp.getMapNo();
				    }
				    //根据图号和编绘内容获取工天
				    String content = null;
				    if(processDefKey != null){
				    	if(processDefKey.contains("SEA_MAP_COMPILATION_SOURCE")){
							content = "源数据";
						}else if(processDefKey.contains("SEA_MAP_COMPILATION_PAPER")){
							content = "纸海图";
						}else if(processDefKey.contains("SEA_MAP_COMPILATION_ELECTRONIC")){
							content = "电子海图";
						}
				    }else{
				    	content = null;
				    }
				    CompilationWorkDays c = new CompilationWorkDays();
				    if (mapNo != null){
						c.setMapNo(mapNo);
						c.setContent(content);
						c = workDaysDao.getWorkDaysListByMapNo(c);
					}
					if(c != null){
						y.setCompilationWorkDays(c.getCompilationWorkDays());
						y.setQualityWorkDays(c.getCheckWorkDays());
						y.setAuthorizedWorkDays(c.getExamineWorkDays());
					}
				    // 设置质检得分
				    String qualityScore = getQualityAchievementScore(entry.getValue(), processInstId);
				    y.setQualityScore(qualityScore);
				    
					String qualityAchievement = getQualityScore(entry.getValue(), processInstId);
					y.setQualityAchievement(qualityAchievement);
			    	
			    	outList.add(y);
				  
				}  
				return outList;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取质检得分
	 * */
	public String getQualityAchievementScore(String compilationContent,String processInstId){
		List<DefectForm> defectFormList = new ArrayList<DefectForm>();
		if(compilationContent.equals("源数据小改正编绘")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zjjilub");
	    }else if(compilationContent.equals("改正通告编辑")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "bmzljcjl");
	    }else if(compilationContent.equals("改正通告模板编辑")||compilationContent.equals("纸海图小改正")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zljcjlb");
	    }else{
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, ChartStatusConstants.QUALITY_LIKE);
	    }
	    if(defectFormList != null){
	    	if(defectFormList.size()>0){
	    		return defectFormList.get(0).getGrading();
	    	}
	    }
		return null;
	}
	
	/**
	 * 获取审定质量评分
	 * */
	public String getQualityScore(String compilationContent,String processInstId){
		List<DefectForm> defectFormList = new ArrayList<DefectForm>();
		if(compilationContent.equals("源数据小改正编绘")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "sdjilub");
	    }else if(compilationContent.equals("改正通告编辑")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zxzljcjl");
	    }else if(compilationContent.equals("改正通告模板编辑")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "zxzljcjlb");
	    }else if(compilationContent.equals("纸海图小改正")){
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, "sdjlb");
	    }else{
	    	defectFormList = defectFormDao.getDefectFormListByProcessInstId(processInstId, ChartStatusConstants.APPROVE_LIKE);
	    }
	    if(defectFormList != null){
	    	if(defectFormList.size()>0){
	    		return defectFormList.get(0).getGrading();
	    	}
	    }
		return null;
		
	}
	
	/**
	 * 获取一条编绘计划
	 */
	@Override
	public CompilationYearPlanView getCompilationYearPlanById(CompilationYearPlanView cyp) {
		try {
			@SuppressWarnings("unchecked")
			// 执行查询
			List<CompilationYearPlanView> result = this.findByNamedQueryAndNamedParam("getCompilationYearPlanById", "id", cyp.getId());
			if(result.size() > 0){
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * 保存
	 */
	@Override
	public void save(CompilationYearPlan cyp) {
		this.save(cyp);
	}
}
