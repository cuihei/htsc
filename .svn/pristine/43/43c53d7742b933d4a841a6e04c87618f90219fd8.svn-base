package com.ht.service.constant.experiencebook.sourcedatacorrection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.constant.experiencebook.PersonAndDateUtil;
import com.ht.service.constant.experiencebook.constants.CorrectionNoticeFlowConstants;
import com.ht.service.constant.experiencebook.constants.SourceDataSamllFlowConstants;
import com.ht.service.constant.experiencebook.paper.PaperFlows;

/**
 * 源数据小改正 各节点信息
 * @author huodesheng
 *
 */
public class SourceDataSamllPersonAndDate extends PersonAndDateUtil{
	
	// 问题处理记录表 编绘人
	String problemCompilationPerson = null;
	// 问题处理记录表  编绘日期
	String problemCompilationDate = null;
	// 问题处理记录表  质检人
	String problemQualityPerson = null;
	// 问题处理记录表  质检日期
	String problemQualityDate = null;
	
	// 质检记录表 科长
	String testQualityApprovePerson = null;
	// 质检记录表  科长日期
	String testQualityApproveDate = null;
	// 质检记录表   质检人
	String testQualityPerson = null;
	// 质检记录表   质检日期
	String testQualityDate = null;
	
	// 审定记录表 审定人
	String valaditionPerson = null;
	// 审定记录表 审定日期
	String valaditionDate = null;
	// 审定记录表 科长
	String valaditonApprovePerson = null;
	// 审定记录表 科长审定时间
	String valaditonApproveDate = null;
	
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
	
	public SourceDataSamllPersonAndDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SourceDataSamllPersonAndDate(List<Flows> flowList) {
		// 问题处理记录表  编绘人，编绘时间
		Flows problemCompilationFlow = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.PROBLEMCOMPILATIONFLOW, flowList);
		if(null!=problemCompilationFlow){
			String userSignature = getUserSignature(problemCompilationFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.problemCompilationPerson=userSignature;
			}else{
				this.problemCompilationPerson=problemCompilationFlow.getUserName();
			}
			this.problemCompilationDate=formatDate(problemCompilationFlow.getEndTime());
		}
		// 问题处理记录表  质检人，质检时间
		Flows problemQualityFlow = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.PROBLEMQUALITYFLOW, flowList);
		if(null!=problemQualityFlow){
			String userSignature = getUserSignature(problemQualityFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.problemQualityPerson=userSignature;
			}else{
				this.problemQualityPerson=problemQualityFlow.getUserName();
			}
			this.problemQualityDate=formatDate(problemQualityFlow.getEndTime());
		}
		
		// 质检记录表 科长、操作时间
		Flows qualityChiefFLOW = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.QUALITYCHIEFFLOW, flowList);
		if(null!=qualityChiefFLOW){
			String userSignature = getUserSignature(qualityChiefFLOW.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.testQualityApprovePerson=userSignature;
			}else{
				this.testQualityApprovePerson=qualityChiefFLOW.getUserName();
			}
			this.testQualityApproveDate=formatDate(qualityChiefFLOW.getEndTime());
		}
		// 质检记录表  质检人，质检时间
		Flows testQualityFLOW = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.TESTQUALITYFLOW, flowList);
		if(null!=testQualityFLOW){
			String userSignature = getUserSignature(testQualityFLOW.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.testQualityPerson=userSignature;
			}else{
				this.testQualityPerson=testQualityFLOW.getUserName();
			}
			this.testQualityDate=formatDate(testQualityFLOW.getEndTime());
		}

		// 审定记录表  审定人，审定日期
		Flows valaditionFlow = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.VALADITIONFLOW, flowList);
		if(null!=valaditionFlow){
			String userSignature = getUserSignature(valaditionFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.valaditionPerson=userSignature;
			}else{
				this.valaditionPerson=valaditionFlow.getUserName();
			}
			this.valaditionDate=formatDate(valaditionFlow.getEndTime());
		}
		// 审定记录表 科长，科长审定时间
		Flows valaditonApproveFlow = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.VALADITONAPPROVEFLOW, flowList);
		if(null!=valaditonApproveFlow){
			String userSignature = getUserSignature(valaditonApproveFlow.getUserNo());
			if(userSignature!=null&&userSignature!=""){
				this.valaditonApprovePerson=userSignature;
			}else{
				this.valaditonApprovePerson=valaditonApproveFlow.getUserName();
			}
			this.valaditonApproveDate=formatDate(valaditonApproveFlow.getEndTime());
		}
		//总工程师
		Flows enginnerPersonFlow = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.ENGINNERPERSONFLOW, flowList);
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
	public String getProblemCompilationPerson() {
		return problemCompilationPerson;
	}
	public void setProblemCompilationPerson(String problemCompilationPerson) {
		this.problemCompilationPerson = problemCompilationPerson;
	}
	public String getProblemCompilationDate() {
		return problemCompilationDate;
	}
	public void setProblemCompilationDate(String problemCompilationDate) {
		this.problemCompilationDate = problemCompilationDate;
	}
	public String getProblemQualityPerson() {
		return problemQualityPerson;
	}
	public void setProblemQualityPerson(String problemQualityPerson) {
		this.problemQualityPerson = problemQualityPerson;
	}
	public String getProblemQualityDate() {
		return problemQualityDate;
	}
	public void setProblemQualityDate(String problemQualityDate) {
		this.problemQualityDate = problemQualityDate;
	}
	public String getTestQualityApprovePerson() {
		return testQualityApprovePerson;
	}
	public void setTestQualityApprovePerson(String testQualityApprovePerson) {
		this.testQualityApprovePerson = testQualityApprovePerson;
	}
	public String getTestQualityApproveDate() {
		return testQualityApproveDate;
	}
	public void setTestQualityApproveDate(String testQualityApproveDate) {
		this.testQualityApproveDate = testQualityApproveDate;
	}
	public String getTestQualityPerson() {
		return testQualityPerson;
	}
	public void setTestQualityPerson(String testQualityPerson) {
		this.testQualityPerson = testQualityPerson;
	}
	public String getTestQualityDate() {
		return testQualityDate;
	}
	public void setTestQualityDate(String testQualityDate) {
		this.testQualityDate = testQualityDate;
	}
	public String getValaditionPerson() {
		return valaditionPerson;
	}
	public void setValaditionPerson(String valaditionPerson) {
		this.valaditionPerson = valaditionPerson;
	}
	public String getValaditionDate() {
		return valaditionDate;
	}
	public void setValaditionDate(String valaditionDate) {
		this.valaditionDate = valaditionDate;
	}
	public String getValaditonApprovePerson() {
		return valaditonApprovePerson;
	}
	public void setValaditonApprovePerson(String valaditonApprovePerson) {
		this.valaditonApprovePerson = valaditonApprovePerson;
	}
	public String getValaditonApproveDate() {
		return valaditonApproveDate;
	}
	public void setValaditonApproveDate(String valaditonApproveDate) {
		this.valaditonApproveDate = valaditonApproveDate;
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
		Flows createFlows = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.CREATE, flowList);
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
		//源数据改正 开始
		Flows compilationBeginFlows = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.COMPILATIONBEGIN, flowList);
		if(null!=compilationBeginFlows){
			seaMapFlow = new PaperFlows();
			seaMapFlow.setORDER("源数据改正");
			//设置人员
			seaMapFlow.setPERFOERMER(compilationBeginFlows.getUserName());
			String dates = null;
			if (compilationBeginFlows.getStartTime() != null)
			{
				dates = df.format(compilationBeginFlows.getStartTime());
			}
			Flows compilationEndFlows = getPersonAndDateByTaskDefIds(SourceDataSamllFlowConstants.COMPILATIONEND, flowList);
			if (compilationEndFlows!=null&&dates != null &&  compilationEndFlows.getEndTime() != null)
			{
				dates += "至" + df.format(compilationEndFlows.getEndTime());
			}
			//加入时间
			seaMapFlow.setDATES(dates);
			listMap.add(seaMapFlow.getFlowsMap());
		}
		//部门质检
		List<Flows> departmenttestingFlowsList = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.DEPARTMENTTESTING, flowList);
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
		List<Flows> testingChangeFlows = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.TESTINGCHANGE, flowList);
		//质检改后确认
		List<Flows> testingConfirmFlows = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.TESTINGCONFIRM, flowList);
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
		List<Flows> centralValidationFlowsList = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.CENTRALVALIDATION, flowList);
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
		List<Flows> valaditondChangeFlows = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.VALADITONDCHANGE, flowList);
		//审定改后确认
		List<Flows> valaditondConfirmFlows = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.VALADITONDCONFIRM, flowList);
		//获取 排除审定后改图需要的list
		List<Flows> excludeValaditondConfirmFlows = getFlowsListByTaskDefIds(SourceDataSamllFlowConstants.EXCLUDEVALADITONDCONFIRM, flowList);
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
		
		this.listMap=listMap;
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

}
