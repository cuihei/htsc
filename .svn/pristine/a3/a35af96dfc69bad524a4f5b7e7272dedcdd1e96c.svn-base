package com.ht.service.constant.experiencebook.electronicchartsmallcorrection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.constant.experiencebook.PersonAndDateUtil;
import com.ht.service.constant.experiencebook.constants.ElectronSmallFlowConstants;
import com.ht.service.constant.experiencebook.constants.ElectronSmallFlowConstants;
/**
 * 电子海图小改正 各节点信息
 * @author huodesheng
 *
 */
public class ElectronSmallPersonAndDate extends PersonAndDateUtil{
	//编绘记录，编绘员
    String compilationPerson=null;
	
    //编绘记录，编绘时间
    String compilationDate=null;
    
    //质量检查记录，质检员
    String qualityPerson=null;
    
    //质量检查记录，质检时间
    String qualityDate=null;
    
    //制图事业部结论，制图事业部主任
    String chiefPerson=null;
    //制图事业部结论，结论
    String chiefOpinion=null;
    //制图事业部结论，日期
    String chiefDate=null;
    
    //中心质量审定记录，审定员
    String validationPerson=null;
    
    //中心质量审定记录，审定日期
    String validationDate=null;
    
    //质量管理部门结论，质量管理部门科长
    String qualityChiefPerson=null;
    //质量管理部门结论，质量结论
    String qualityChiefchiefOpinion=null;
    
    //质量管理部门结论，日期
    String qualityChiefDate=null;

	//工序流程列表
	List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
    
    
	public ElectronSmallPersonAndDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ElectronSmallPersonAndDate(List<Flows> flowList) {
		
		//编绘记录， 编绘人，编绘时间
		Flows compilationFlow = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.COMPILATIONFLOW, flowList);
		if(null!=compilationFlow){
			String userSignature = getUserSignature(compilationFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.compilationPerson=userSignature;
			}else{
				this.compilationPerson=compilationFlow.getUserName();
			}
			this.compilationDate=formatDate(compilationFlow.getEndTime());
		}
		
		//质量检查记录	质检员、质检日期
		Flows qualityFlow = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.QUALITYFLOW, flowList);
		if(null!=qualityFlow){
			String userSignature = getUserSignature(qualityFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.qualityPerson=userSignature;
			}else{
				this.qualityPerson=qualityFlow.getUserName();
			}
			this.qualityDate=formatDate(qualityFlow.getEndTime());
		}
	    
		//制图事业部结论 制图事业部主任、日期
		Flows departmentFlow = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.DEPARTMENTFLOW, flowList);
		if(null!=departmentFlow){
			String userSignature = getUserSignature(departmentFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.chiefPerson=userSignature;
			}else{
				this.chiefPerson=departmentFlow.getUserName();
			}
			this.chiefOpinion=!departmentFlow.getTaskResult().equals("退回") ? "同意" : null;
			this.chiefDate=formatDate(departmentFlow.getEndTime());
		}
		//中心质量审定记录	审定员、审定日期
		Flows coreValidationFlow = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.COREVALIDATIONFLOW, flowList);
		if(null!=coreValidationFlow){
			String userSignature = getUserSignature(coreValidationFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.validationPerson=userSignature;
			}else{
				this.validationPerson=coreValidationFlow.getUserName();
			}
			this.validationDate=formatDate(coreValidationFlow.getEndTime());
		}
		
		//质量管理部门结论	质量管理部门科长、日期
		Flows departmentQualityFlow = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.DEPARTMENTQUALITYFLOW, flowList);
		if(null!=departmentQualityFlow){
			String userSignature = getUserSignature(departmentQualityFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.qualityChiefPerson=userSignature;
			}else{
				this.qualityChiefPerson=departmentQualityFlow.getUserName();
			}
			this.qualityChiefchiefOpinion=!departmentQualityFlow.getTaskResult().equals("退回") ? "同意" : null;
			this.qualityChiefDate=formatDate(departmentQualityFlow.getEndTime());
		}
		this.setListMap(flowList);
	}

	public String getCompilationPerson() {
		return compilationPerson;
	}

	public void setCompilationPerson(String compilationPerson) {
		this.compilationPerson = compilationPerson;
	}

	public String getCompilationDate() {
		return compilationDate;
	}

	public void setCompilationDate(String compilationDate) {
		this.compilationDate = compilationDate;
	}

	public String getQualityPerson() {
		return qualityPerson;
	}

	public void setQualityPerson(String qualityPerson) {
		this.qualityPerson = qualityPerson;
	}

	public String getQualityDate() {
		return qualityDate;
	}

	public void setQualityDate(String qualityDate) {
		this.qualityDate = qualityDate;
	}

	public String getChiefPerson() {
		return chiefPerson;
	}

	public void setChiefPerson(String chiefPerson) {
		this.chiefPerson = chiefPerson;
	}

	public String getChiefDate() {
		return chiefDate;
	}

	public void setChiefDate(String chiefDate) {
		this.chiefDate = chiefDate;
	}

	public String getValidationPerson() {
		return validationPerson;
	}

	public void setValidationPerson(String validationPerson) {
		this.validationPerson = validationPerson;
	}

	public String getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(String validationDate) {
		this.validationDate = validationDate;
	}

	public String getQualityChiefPerson() {
		return qualityChiefPerson;
	}

	public void setQualityChiefPerson(String qualityChiefPerson) {
		this.qualityChiefPerson = qualityChiefPerson;
	}

	public String getQualityChiefDate() {
		return qualityChiefDate;
	}

	public void setQualityChiefDate(String qualityChiefDate) {
		this.qualityChiefDate = qualityChiefDate;
	}

	public String getChiefOpinion() {
		return chiefOpinion;
	}

	public void setChiefOpinion(String chiefOpinion) {
		this.chiefOpinion = chiefOpinion;
	}

	public String getQualityChiefchiefOpinion() {
		return qualityChiefchiefOpinion;
	}

	public void setQualityChiefchiefOpinion(String qualityChiefchiefOpinion) {
		this.qualityChiefchiefOpinion = qualityChiefchiefOpinion;
	}

	public List<Map<String, Object>> getListMap() {
		return listMap;
	}

	public void setListMap(List<Flows> flowList) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		com.ht.service.constant.experiencebook.sourcedata.Flows seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
		//创建
		/*Flows createFlows = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.CREATE, flowList);
		if(null!=createFlows){
			seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
			seaMapFlow.setORDER("工程创建");
			seaMapFlow.setPERFOERMER(createFlows.getUserName());
			String dates = null;
			if (createFlows.getEndTime() != null)
			{
				dates = df.format(createFlows.getEndTime());
			}
			seaMapFlow.setDATES(dates);
			listMap.add(seaMapFlow.getFlowsMap());
		}*/
		//编   绘
		Flows compilationFlows = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.COMPILATIONFLOW, flowList);
		if(null!=compilationFlows){
			seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
			seaMapFlow.setORDER("编   绘");
			//设置人员
			seaMapFlow.setPERFOERMER(compilationFlows.getUserName());
			String dates = null;
			if (compilationFlows.getStartTime() != null)
			{
				dates = df.format(compilationFlows.getStartTime());
			}
			if (dates != null &&  compilationFlows.getEndTime() != null)
			{
				dates += "至" + df.format(compilationFlows.getEndTime());
			}
			//加入时间
			seaMapFlow.setDATES(dates);
			listMap.add(seaMapFlow.getFlowsMap());
		}
		//部门质检
		Flows departmenttesting1Flows = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.QUALITYFLOW, flowList);
		if(null!=departmenttesting1Flows){
			seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
			seaMapFlow.setORDER("部门质检");
			String dates = null;
			if (departmenttesting1Flows!=null&&departmenttesting1Flows.getEndTime() != null)
			{
				dates =  df.format(departmenttesting1Flows.getEndTime());
			}
			//设置人员
			seaMapFlow.setPERFOERMER(departmenttesting1Flows.getUserName());
			if (dates != null && departmenttesting1Flows.getEndTime() != null)
			{
				dates += "至"+ df.format(departmenttesting1Flows.getEndTime());
			}
			//加入时间
			seaMapFlow.setDATES(dates);
			listMap.add(seaMapFlow.getFlowsMap());
		}
		//质检后改图，质检改后确认
		//质检后改图
		/*List<Flows> testingChangeFlows = getFlowsListByTaskDefIds(ElectronSmallFlowConstants.TESTINGCHANGE, flowList);
		//质检改后确认
		List<Flows> testingConfirmFlows = getFlowsListByTaskDefIds(ElectronSmallFlowConstants.TESTINGCONFIRM, flowList);
		if(testingChangeFlows!=null&&testingChangeFlows.size()>0&&testingConfirmFlows!=null&&testingConfirmFlows.size()>0){
			for (Flows testingChange : testingChangeFlows) {
				for (Flows testingConfirm : testingConfirmFlows) {
					if(testingChange!=null&&testingConfirm!=null){
						String starttime = sdf.format(testingChange.getEndTime());
						String endtime = sdf.format(testingConfirm.getStartTime());
						if(starttime.equals(endtime)){
							//加入质检后改图
							seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
							seaMapFlow.setORDER("质检后改图");
							//设置人员
							seaMapFlow.setPERFOERMER(testingChange.getUserName());
							String dates = null;
							if (testingChange.getStartTime() != null)
							{
								dates = df.format(testingChange.getStartTime());
							}
							if (dates != null &&  testingChange.getEndTime() != null)
							{
								dates += "至" + df.format(testingChange.getEndTime());
							}
							//加入时间
							seaMapFlow.setDATES(dates);
							listMap.add(seaMapFlow.getFlowsMap());
							//加入质检改后确认
							seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
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
						}
					}
				}
			}
		}
		if(testingChangeFlows.size()>testingConfirmFlows.size()){
			seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
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
		}*/
		//中心审定
		Flows centralValidationFlows = getPersonAndDateByTaskDefIds(ElectronSmallFlowConstants.COREVALIDATIONFLOW, flowList);
		if(null!=centralValidationFlows){
			seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
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
		/*//审定后改图
		List<Flows> valaditondChangeFlows = getFlowsListByTaskDefIds(ElectronSmallFlowConstants.VALADITONDCHANGE, flowList);
		//审定改后确认
		List<Flows> valaditondConfirmFlows = getFlowsListByTaskDefIds(ElectronSmallFlowConstants.VALADITONDCONFIRM, flowList);
		//判空
		if(valaditondChangeFlows!=null&&valaditondChangeFlows.size()>0&&valaditondConfirmFlows!=null&&valaditondConfirmFlows.size()>0){
			//因为 审定改后确认的次数是小于等于审定后改图的次数的。所以循环的下标使用审定改后确认的次数
			for (int i = 0; i < valaditondConfirmFlows.size(); i++) {
				//当前审定后改图节点
				Flows valaditondChange = valaditondChangeFlows.get(i);
				//当前审定改后确认节点
				Flows valaditondConfirm = valaditondConfirmFlows.get(i);
				if(valaditondChange!=null&&valaditondConfirm!=null){
					//添加审定后改图工序流程
					seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
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
					seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
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
				}
			}
			//可能会出现，改图次数多余改后确认次数的情况。
			if(valaditondChangeFlows.size()>valaditondConfirmFlows.size()){
				seaMapFlow = new com.ht.service.constant.experiencebook.sourcedata.Flows();
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
		}*/
		
		this.listMap=listMap;
	}
	
    
}
