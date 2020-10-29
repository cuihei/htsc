package com.ht.service.constant.experiencebook.correctionnoticeTemplete;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.constant.experiencebook.PersonAndDateUtil;
import com.ht.service.constant.experiencebook.constants.CorrectionNoticeTempleteFlowConstants;
import com.ht.service.constant.experiencebook.constants.CorrectionNoticeTempleteFlowConstants;
import com.ht.service.constant.experiencebook.constants.CorrectionNoticeTempleteFlowConstants;
import com.ht.service.constant.experiencebook.paper.PaperFlows;

/**
 * 改正通告模版编辑 各节点信息
 * @author huodesheng
 *
 */
public class CorrectionNoticeTempletePersonAndDate extends PersonAndDateUtil{
	
	//编辑情况记录表 编辑员
	String recordCompilationPerson=null;
	//编辑情况记录表 编辑时间	
	String recordCompilationDate=null;
	
	//部门质量检查记录表 编辑员
	String departmentQualityCompilationPerson=null;
	//部门质量检查记录表 编辑时间	
	String departmentQualityCompilationDate=null;
	//部门质量检查记录表 质检员
	String departmentQualityQualityPerson=null;
	//部门质量检查记录表 质检时间
	String departmentQualityQualityDate=null;
	//部门质量检查记录表 科长
	String departmentQualityChief=null;
	//部门质量检查记录表 操作时间
	String departmentQualityChiefDate=null;
		
	//中心质量检查记录表 质检员
	String centreQualityQualityPerson=null;
	//中心质量检查记录表 质检时间
	String centreQualityQualityDate=null;
	//中心质量检查记录表 审定员
	String centreQualityvaladitonPerson=null;
	//中心质量检查记录表 审定时间	
	String centreQualityvaladitonDate=null;
	//中心质量检查记录表 科长
	String centreQualityChief=null;
	//中心质量检查记录表 操作时间
	String centreQualityChiefDate=null;
	
	// 总工程师人
	String enginnerPerson = null;
	// 总工程师时间
	String enginnerDate = null;
	// 总工程师意见
	String enginnerAdvice = null;
	// 总工程师签章图片
	String enginnerSignature = null;
	//工序流程列表
	List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	
	public CorrectionNoticeTempletePersonAndDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CorrectionNoticeTempletePersonAndDate(List<Flows> flowList) {
		// 编辑情况记录表   编辑员、编辑时间
		Flows recordCompilationFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.RECORDCOMPILATIONFLOW, flowList);
		if(null!=recordCompilationFlow){
			String userSignature = getUserSignature(recordCompilationFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.recordCompilationPerson=userSignature;
			}else{
				this.recordCompilationPerson=recordCompilationFlow.getUserName();
			}
			this.recordCompilationDate=formatDate(recordCompilationFlow.getEndTime());
		}
		
		// 部门质量检查记录表   编辑员、编辑时间
		Flows departmentQualityCompilationFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.DEPARTMENTQUALITYCOMPILATIONFLOW, flowList);
		if(null!=departmentQualityCompilationFlow){
			String userSignature = getUserSignature(departmentQualityCompilationFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.departmentQualityCompilationPerson=userSignature;
			}else{
				this.departmentQualityCompilationPerson=departmentQualityCompilationFlow.getUserName();
			}
			this.departmentQualityCompilationDate=formatDate(departmentQualityCompilationFlow.getEndTime());
		}
		//部门质量检查记录表  质检员、质检时间	
		Flows departmentQualityQualityFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.DEPARTMENTQUALITYQUALITYFLOW, flowList);
		if(null!=departmentQualityQualityFlow){
			String userSignature = getUserSignature(departmentQualityQualityFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.departmentQualityQualityPerson=userSignature;
			}else{
				this.departmentQualityQualityPerson=departmentQualityQualityFlow.getUserName();
			}
			this.departmentQualityQualityDate=formatDate(departmentQualityQualityFlow.getEndTime());
		}
		//部门质量检查记录表   质检员、质检时间	
		Flows departmentQualityChiefFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.DEPARTMENTQUALITYCHIEFFLOW, flowList);
		if(null!=departmentQualityChiefFlow){
			String userSignature = getUserSignature(departmentQualityChiefFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.departmentQualityChief=userSignature;
			}else{
				this.departmentQualityChief=departmentQualityChiefFlow.getUserName();
			}
			this.departmentQualityChiefDate=formatDate(departmentQualityChiefFlow.getEndTime());
		}
		
		//中心质量检查记录表	质检员、质检时间
		Flows centreQualityQualityFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.CENTREQUALITYQUALITYFLOW, flowList);
		if(null!=centreQualityQualityFlow){
			String userSignature = getUserSignature(centreQualityQualityFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.centreQualityQualityPerson=userSignature;
			}else{
				this.centreQualityQualityPerson=centreQualityQualityFlow.getUserName();
			}
			this.centreQualityQualityDate=formatDate(centreQualityQualityFlow.getEndTime());
		}
		//中心质量检查记录表 	审定员、审定时间
		Flows centreQualityvaladitonFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.CENTREQUALITYVALADITONFLOW, flowList);
		if(null!=centreQualityvaladitonFlow){
			String userSignature = getUserSignature(centreQualityvaladitonFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.centreQualityvaladitonPerson=userSignature;
			}else{
				this.centreQualityvaladitonPerson=centreQualityvaladitonFlow.getUserName();
			}
			this.centreQualityvaladitonDate=formatDate(centreQualityvaladitonFlow.getEndTime());
		}
		//中心质量检查记录表   科长、操作时间	
		Flows centreQualityChiefFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.CENTREQUALITYCHIEFFLOW, flowList);
		if(null!=centreQualityChiefFlow){
			String userSignature = getUserSignature(centreQualityChiefFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.centreQualityChief=userSignature;
			}else{
				this.centreQualityChief=centreQualityChiefFlow.getUserName();
			}
			this.centreQualityChiefDate=formatDate(centreQualityChiefFlow.getEndTime());
		}
		//总工程师
		Flows enginnerPersonFlow = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.ENGINNERPERSONFLOW, flowList);
		if(null!=enginnerPersonFlow){
			String userSignature = getUserSignature(enginnerPersonFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.enginnerPerson=userSignature;
			}else{
				this.enginnerPerson=enginnerPersonFlow.getUserName();
			}
			this.enginnerDate=formatDate(enginnerPersonFlow.getEndTime());
			this.enginnerAdvice = !enginnerPersonFlow.getTaskResult().equals("退回") ? "同意" : null;
			this.enginnerSignature=getUserSignature(enginnerPersonFlow.getUserNo());
		}
		this.setListMap(flowList); 
		
		
	}
	public String getRecordCompilationPerson() {
		return recordCompilationPerson;
	}
	public void setRecordCompilationPerson(String recordCompilationPerson) {
		this.recordCompilationPerson = recordCompilationPerson;
	}
	public String getRecordCompilationDate() {
		return recordCompilationDate;
	}
	public void setRecordCompilationDate(String recordCompilationDate) {
		this.recordCompilationDate = recordCompilationDate;
	}
	public String getDepartmentQualityCompilationPerson() {
		return departmentQualityCompilationPerson;
	}
	public void setDepartmentQualityCompilationPerson(
			String departmentQualityCompilationPerson) {
		this.departmentQualityCompilationPerson = departmentQualityCompilationPerson;
	}
	public String getDepartmentQualityCompilationDate() {
		return departmentQualityCompilationDate;
	}
	public void setDepartmentQualityCompilationDate(
			String departmentQualityCompilationDate) {
		this.departmentQualityCompilationDate = departmentQualityCompilationDate;
	}
	public String getDepartmentQualityQualityPerson() {
		return departmentQualityQualityPerson;
	}
	public void setDepartmentQualityQualityPerson(
			String departmentQualityQualityPerson) {
		this.departmentQualityQualityPerson = departmentQualityQualityPerson;
	}
	public String getDepartmentQualityQualityDate() {
		return departmentQualityQualityDate;
	}
	public void setDepartmentQualityQualityDate(String departmentQualityQualityDate) {
		this.departmentQualityQualityDate = departmentQualityQualityDate;
	}
	public String getDepartmentQualityChief() {
		return departmentQualityChief;
	}
	public void setDepartmentQualityChief(String departmentQualityChief) {
		this.departmentQualityChief = departmentQualityChief;
	}
	public String getDepartmentQualityChiefDate() {
		return departmentQualityChiefDate;
	}
	public void setDepartmentQualityChiefDate(String departmentQualityChiefDate) {
		this.departmentQualityChiefDate = departmentQualityChiefDate;
	}
	
	public String getCentreQualityQualityPerson() {
		return centreQualityQualityPerson;
	}
	public void setCentreQualityQualityPerson(String centreQualityQualityPerson) {
		this.centreQualityQualityPerson = centreQualityQualityPerson;
	}
	public String getCentreQualityQualityDate() {
		return centreQualityQualityDate;
	}
	public void setCentreQualityQualityDate(String centreQualityQualityDate) {
		this.centreQualityQualityDate = centreQualityQualityDate;
	}
	public String getCentreQualityvaladitonPerson() {
		return centreQualityvaladitonPerson;
	}
	public void setCentreQualityvaladitonPerson(String centreQualityvaladitonPerson) {
		this.centreQualityvaladitonPerson = centreQualityvaladitonPerson;
	}
	public String getCentreQualityvaladitonDate() {
		return centreQualityvaladitonDate;
	}
	public void setCentreQualityvaladitonDate(String centreQualityvaladitonDate) {
		this.centreQualityvaladitonDate = centreQualityvaladitonDate;
	}
	public String getCentreQualityChief() {
		return centreQualityChief;
	}
	public void setCentreQualityChief(String centreQualityChief) {
		this.centreQualityChief = centreQualityChief;
	}
	public String getCentreQualityChiefDate() {
		return centreQualityChiefDate;
	}
	public void setCentreQualityChiefDate(String centreQualityChiefDate) {
		this.centreQualityChiefDate = centreQualityChiefDate;
	}
	public String getEnginnerPerson() {
		return enginnerPerson;
	}
	public void setEnginnerPerson(String enginnerPerson) {
		this.enginnerPerson = enginnerPerson;
	}
	public String getEnginnerDate() {
		return enginnerDate;
	}
	public void setEnginnerDate(String enginnerDate) {
		this.enginnerDate = enginnerDate;
	}
	public String getEnginnerAdvice() {
		return enginnerAdvice;
	}
	public void setEnginnerAdvice(String enginnerAdvice) {
		this.enginnerAdvice = enginnerAdvice;
	}
	public String getEnginnerSignature() {
		return enginnerSignature;
	}
	public void setEnginnerSignature(String enginnerSignature) {
		this.enginnerSignature = enginnerSignature;
	}
	public List<Map<String, Object>> getListMap() {
		return this.listMap;
	}

	public void setListMap(List<Flows> flowList) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		PaperFlows seaMapFlow = new PaperFlows();
		//创建
		Flows createFlows = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.CREATE, flowList);
		if(null!=createFlows){
			seaMapFlow = new PaperFlows();
			seaMapFlow.setORDER("工程创建");
			seaMapFlow.setPERFOERMER(createFlows.getUserName());
			String dates = null;
			if (createFlows.getEndTime() != null)
			{
				dates = df.format(createFlows.getEndTime());
			}
			seaMapFlow.setDATES(dates);
			listMap.add(seaMapFlow.getFlowsMap());
		}
		//编辑 开始
		Flows compilationBeginFlows = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.COMPILATIONBEGIN, flowList);
		if(null!=compilationBeginFlows){
			seaMapFlow = new PaperFlows();
			seaMapFlow.setORDER("编辑");
			//设置人员
			seaMapFlow.setPERFOERMER(compilationBeginFlows.getUserName());
			String dates = null;
			if (compilationBeginFlows.getStartTime() != null)
			{
				dates = df.format(compilationBeginFlows.getStartTime());
			}
			Flows compilationEndFlows = getPersonAndDateByTaskDefIds(CorrectionNoticeTempleteFlowConstants.COMPILATIONEND, flowList);
			if(compilationEndFlows!=null){
				if (dates != null &&  compilationEndFlows.getEndTime() != null)
				{
					dates += "至" + df.format(compilationEndFlows.getEndTime());
				}
				//加入时间
				seaMapFlow.setDATES(dates);
				listMap.add(seaMapFlow.getFlowsMap());
			}
		
		}
		//部门质检
		List<Flows> departmenttestingFlowsList = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.DEPARTMENTTESTING, flowList);
		if(null!=departmenttestingFlowsList&&departmenttestingFlowsList.size()>0){
			Flows	departmenttestingFlows=departmenttestingFlowsList.get(0);
			if(departmenttestingFlows!=null){
				seaMapFlow = new PaperFlows();
				seaMapFlow.setORDER("部门质检");
				String dates = null;
				//设置人员
				seaMapFlow.setPERFOERMER(departmenttestingFlows.getUserName());
				if (departmenttestingFlows!=null&&departmenttestingFlows.getStartTime() != null)
				{
					dates =  df.format(departmenttestingFlows.getStartTime());
				}
				if (dates != null && departmenttestingFlows.getEndTime() != null)
				{
					dates += "至"+ df.format(departmenttestingFlows.getEndTime());
				}
				//加入时间
				seaMapFlow.setDATES(dates);
				listMap.add(seaMapFlow.getFlowsMap());
			}
			
		}
		//质检后改图，质检改后确认
		//质检后改图
		List<Flows> testingChangeFlows = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.TESTINGCHANGE, flowList);
		//质检改后确认
		List<Flows> testingConfirmFlows = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.TESTINGCONFIRM, flowList);
		if(testingChangeFlows!=null&&testingChangeFlows.size()>0&&testingConfirmFlows!=null&&testingConfirmFlows.size()>0){
			//因为 质检改后确认的次数是小于等于质检后改图的次数的。所以循环的下标使用质检改后确认的次数
			int j=0;
			for (int i = 0; i < testingConfirmFlows.size(); i++) {
				if(testingChangeFlows.size()>j){
					boolean flag=true;
					//当前质检后改图节点
					Flows testingChange = testingChangeFlows.get(j);
					//当前审定改后确认节点
					Flows testingConfirm = testingConfirmFlows.get(i);
					if(testingChange!=null&&testingConfirm!=null){
						//排除多余的质检改后确认节点
						while (testingChange!=null&&testingConfirm!=null&&testingConfirm.getStartTime().before(testingChange.getStartTime())){
							i=i+1;
							if(testingConfirmFlows.size()>i){
								testingConfirm = testingConfirmFlows.get(i);
							}else{
								flag=false;
								break;
							}
						}
						if(testingChange!=null&&testingConfirm!=null){
							if(flag){
								//添加质检后改图工序流程
								seaMapFlow = new PaperFlows();
								seaMapFlow.setORDER("质检后改图");
								//设置人员
								seaMapFlow.setPERFOERMER(testingChange.getUserName());
								String dates = null;
								if (testingChange.getStartTime() != null)
								{
									dates =  df.format(testingChange.getStartTime());
								}
								if(testingConfirm!=null){
									if (dates != null && testingConfirm.getStartTime() != null)
									{
										dates += "至"+ df.format(testingConfirm.getStartTime());
									}
								}
								//加入时间
								seaMapFlow.setDATES(dates);
								listMap.add(seaMapFlow.getFlowsMap());
								//添加审定改后确认工序流程
								seaMapFlow = new PaperFlows();
								seaMapFlow.setORDER("质检改后确认");
								//设置人员
								seaMapFlow.setPERFOERMER(testingConfirm.getUserName());
								dates = null;
								if (testingConfirm.getStartTime() != null)
								{
									dates = df.format(testingConfirm.getStartTime());
								}
								if (dates != null &&  testingConfirm.getEndTime() != null)
								{
									dates += "至" + df.format(testingConfirm.getEndTime());
								}
								//加入时间
								seaMapFlow.setDATES(dates);
								listMap.add(seaMapFlow.getFlowsMap());
							}else{
								if(!(testingChangeFlows.size()>testingConfirmFlows.size())){
									//添加质检后改图工序流程
									seaMapFlow = new PaperFlows();
									seaMapFlow.setORDER("质检后改图");
									//设置人员
									seaMapFlow.setPERFOERMER(testingChange.getUserName());
									String dates = null;
									if (testingChange.getStartTime() != null)
									{
										dates =  df.format(testingChange.getStartTime());
									}
									//加入时间
									seaMapFlow.setDATES(dates);
									listMap.add(seaMapFlow.getFlowsMap());
								}
							}
						}
					}
					j++;
				}
			}
		}
		if(testingChangeFlows.size()>testingConfirmFlows.size()){
			seaMapFlow = new PaperFlows();
			seaMapFlow.setORDER("质检后改图");
			Flows testingChange = testingChangeFlows.get(testingChangeFlows.size()-1);
			if(testingChange!=null){
				//设置人员
				seaMapFlow.setPERFOERMER(testingChange.getUserName());
				String dates = null;
				if (testingChange.getStartTime() != null)
				{
					dates = df.format(testingChange.getStartTime())+"至";
				}
				//加入时间
				seaMapFlow.setDATES(dates);
				listMap.add(seaMapFlow.getFlowsMap());
			}
		}
		//中心审定
		List<Flows> centralValidationFlowsList = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.CENTRALVALIDATION, flowList);
		if(null!=centralValidationFlowsList&&centralValidationFlowsList.size()>0){
			Flows centralValidationFlows = centralValidationFlowsList.get(0);
			if(centralValidationFlows!=null){
				seaMapFlow = new PaperFlows();
				seaMapFlow.setORDER("中心审定");
				//设置人员
				seaMapFlow.setPERFOERMER(centralValidationFlows.getUserName());
				String dates = null;
				if (centralValidationFlows.getStartTime() != null)
				{
					dates = df.format(centralValidationFlows.getStartTime());
				}
				if (dates != null &&  centralValidationFlows.getEndTime() != null)
				{
					dates += "至" + df.format(centralValidationFlows.getEndTime());
				}
				//加入时间
				seaMapFlow.setDATES(dates);
				listMap.add(seaMapFlow.getFlowsMap());	
			}
			
		}
		//审定后改图
		List<Flows> valaditondChangeFlows = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.VALADITONDCHANGE, flowList);
		//审定改后确认
		List<Flows> valaditondConfirmFlows = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.VALADITONDCONFIRM, flowList);
		//获取 排除审定后改图需要的list
		List<Flows> excludeValaditondConfirmFlows = getFlowsListByTaskDefIds(CorrectionNoticeTempleteFlowConstants.EXCLUDEVALADITONDCONFIRM, flowList);
		//排除多余的审定后改图
		valaditondChangeFlows=excludeFlows(valaditondChangeFlows, excludeValaditondConfirmFlows);
		//判空
		if(valaditondChangeFlows!=null&&valaditondChangeFlows.size()>0&&valaditondConfirmFlows!=null&&valaditondConfirmFlows.size()>0){
			//因为 审定改后确认的次数是小于等于审定后改图的次数的。所以循环的下标使用审定改后确认的次数
			int j=0;
			for (int i = 0; i < valaditondConfirmFlows.size(); i++) {
				if(valaditondChangeFlows.size()>j){
				boolean flag=true;
				//当前审定后改图节点
				Flows valaditondChange = valaditondChangeFlows.get(j);
				//当前审定改后确认节点
				Flows valaditondConfirm = valaditondConfirmFlows.get(i);
				if(valaditondChange!=null&&valaditondConfirm!=null){
					//排除多余的质检改后确认节点
					while (valaditondChange!=null&&valaditondConfirm!=null&&valaditondConfirm.getStartTime().before(valaditondChange.getStartTime())){
						i=i+1;
						if(valaditondConfirmFlows.size()>i){
							valaditondConfirm = valaditondConfirmFlows.get(i);
						}else{
							flag=false;
							break;
						}
					}
					if(valaditondChange!=null&&valaditondConfirm!=null){
						if(flag){
							//添加审定后改图工序流程
							seaMapFlow = new PaperFlows();
							seaMapFlow.setORDER("审定后改图");
							//设置人员
							seaMapFlow.setPERFOERMER(valaditondChange.getUserName());
							String dates = null;
							if (valaditondChange.getEndTime() != null)
							{
								dates =  df.format(valaditondChange.getEndTime());
							}
							if(valaditondConfirm!=null){
								if (dates != null && valaditondConfirm.getStartTime() != null)
								{
									dates += "至"+ df.format(valaditondConfirm.getStartTime());
								}
							}
							//加入时间
							seaMapFlow.setDATES(dates);
							listMap.add(seaMapFlow.getFlowsMap());
							//添加审定改后确认工序流程
							seaMapFlow = new PaperFlows();
							seaMapFlow.setORDER("审定改后确认");
							//设置人员
							seaMapFlow.setPERFOERMER(valaditondConfirm.getUserName());
							dates = null;
							if (valaditondConfirm.getStartTime() != null)
							{
								dates = df.format(valaditondConfirm.getStartTime());
							}
							if (dates != null &&  valaditondConfirm.getEndTime() != null)
							{
								dates += "至" + df.format(valaditondConfirm.getEndTime());
							}
							//加入时间
							seaMapFlow.setDATES(dates);
							listMap.add(seaMapFlow.getFlowsMap());
						}else{
							if(!(valaditondChangeFlows.size()>valaditondConfirmFlows.size())){
								seaMapFlow = new PaperFlows();
								seaMapFlow.setORDER("审定后改图");
								//设置人员
								seaMapFlow.setPERFOERMER(valaditondChange.getUserName());
								String dates = null;
								if (valaditondChange.getStartTime() != null)
								{
									dates = df.format(valaditondChange.getStartTime())+"至";
								}
								//加入时间
								seaMapFlow.setDATES(dates);
								listMap.add(seaMapFlow.getFlowsMap());
							}
						}
					}
					}
				
				j++;
				}
			}
			//可能会出现，改图次数多余改后确认次数的情况。
			if(valaditondChangeFlows.size()>valaditondConfirmFlows.size()){
				seaMapFlow = new PaperFlows();
				seaMapFlow.setORDER("审定后改图");
				Flows valaditondChange = valaditondChangeFlows.get(valaditondChangeFlows.size()-1);
				if(valaditondChange!=null){
					//设置人员
					seaMapFlow.setPERFOERMER(valaditondChange.getUserName());
					String dates = null;
					if (valaditondChange.getStartTime() != null)
					{
						dates = df.format(valaditondChange.getStartTime())+"至";
					}
					//加入时间
					seaMapFlow.setDATES(dates);
					listMap.add(seaMapFlow.getFlowsMap());
				}
			}
		}
		
		this.listMap=listMap;
	}


}
