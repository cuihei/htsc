package com.ht.persistence.model.background.dicdata.defectform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * @ClassName: DefectForm
 * @Description: TODO(缺陷表单类别实体类)
 * @author penghao
 * @date 2016年11月6日 下午2:38:15
 */
@Entity
@Table(name = "FORM_DEFECTIVE")
public class DefectForm extends BaseModel
{

	@Id
	@Column(name = "id", length = 64)
	private String id; // id
	private String formId;// 图名
	private String splitId; // 任务拆分id
	private String taskInstId; // 流程任务实例id
	private String processInstId; // 流程实例id
	private String layer; // 图层
	private String charttype; // 海图类型
	private String defecttype; // 缺陷类别
	private String defectitem; // 缺陷项目
	private String discription; // 缺陷的描述
	private String opinion; // 缺陷意见
	private String deep; // 缺陷的级别
	private String number; // 缺陷个数
	private String score; // 扣分
	private String actual; // 实际扣分
	private String remarks; // 备注
	private String total; // 累计扣分
	private String coefficient; // 调整系数
	private String actualTotal; // 实际总扣分
	private String grading; // 质量评分
	private String layerName;// 图名
	private String defaultValue;//  默认值

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFormId()
	{
		return formId;
	}

	public void setFormId(String formId)
	{
		this.formId = formId;
	}

	public String getSplitId()
	{
		return splitId;
	}

	public void setSplitId(String splitId)
	{
		this.splitId = splitId;
	}

	public String getTaskInstId()
	{
		return taskInstId;
	}

	public void setTaskInstId(String taskInstId)
	{
		this.taskInstId = taskInstId;
	}

	public String getProcessInstId()
	{
		return processInstId;
	}

	public void setProcessInstId(String processInstId)
	{
		this.processInstId = processInstId;
	}

	public String getLayer()
	{
		return layer;
	}

	public void setLayer(String layer)
	{
		this.layer = layer;
	}

	public String getCharttype()
	{
		return charttype;
	}

	public void setCharttype(String charttype)
	{
		this.charttype = charttype;
	}

	public String getDefecttype()
	{
		return defecttype;
	}

	public void setDefecttype(String defecttype)
	{
		this.defecttype = defecttype;
	}

	public String getDefectitem()
	{
		return defectitem;
	}

	public void setDefectitem(String defectitem)
	{
		this.defectitem = defectitem;
	}

	public String getDiscription()
	{
		return discription;
	}

	public void setDiscription(String discription)
	{
		this.discription = discription;
	}

	public String getOpinion()
	{
		return opinion;
	}

	public void setOpinion(String opinion)
	{
		this.opinion = opinion;
	}

	public String getDeep()
	{
		return deep;
	}

	public void setDeep(String deep)
	{
		this.deep = deep;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getScore()
	{
		return score;
	}

	public void setScore(String score)
	{
		this.score = score;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getTotal()
	{
		return total;
	}

	public void setTotal(String total)
	{
		this.total = total;
	}

	public String getCoefficient()
	{
		return coefficient;
	}

	public void setCoefficient(String coefficient)
	{
		this.coefficient = coefficient;
	}

	public String getActual()
	{
		return actual;
	}

	public void setActual(String actual)
	{
		this.actual = actual;
	}

	public String getGrading()
	{
		return grading;
	}

	public void setGrading(String grading)
	{
		this.grading = grading;
	}

	public String getActualTotal()
	{
		return actualTotal;
	}

	public void setActualTotal(String actualTotal)
	{
		this.actualTotal = actualTotal;
	}

	public String getLayerName()
	{
		return layerName;
	}

	public void setLayerName(String layerName)
	{
		this.layerName = layerName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
