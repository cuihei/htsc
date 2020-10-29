package com.ht.service.constant.experiencebook.project;

import java.util.List;

import com.ht.persistence.model.system.workflow.task.Flows;
import com.ht.service.constant.experiencebook.PersonAndDateUtil;
import com.ht.service.constant.experiencebook.constants.ProjectFlowConstants;
/**
 * 工程&专题（纸/电子）海图 各节点信息
 * @author huodesheng
 *
 */
public class ProjectPersonAndDate extends PersonAndDateUtil{
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
    
    //质量管理部门结论，日期
    String qualityChiefDate=null;
  //质量管理部门结论，质量结论
    String qualityChiefchiefOpinion=null;
    
    
    
    
	public ProjectPersonAndDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProjectPersonAndDate(List<Flows> flowList) {
		
		//编绘记录， 编绘人，编绘时间
		Flows compilationFlow = getPersonAndDateByTaskDefIds(ProjectFlowConstants.COMPILATIONFLOW, flowList);
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
		Flows qualityFlow = getPersonAndDateByTaskDefIds(ProjectFlowConstants.QUALITYFLOW, flowList);
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
		Flows departmentFlow = getPersonAndDateByTaskDefIds(ProjectFlowConstants.DEPARTMENTFLOW, flowList);
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
		Flows coreValidationFlow = getPersonAndDateByTaskDefIds(ProjectFlowConstants.COREVALIDATIONFLOW, flowList);
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
		Flows departmentQualityFlow = getPersonAndDateByTaskDefIds(ProjectFlowConstants.DEPARTMENTQUALITYFLOW, flowList);
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
	
    
}
