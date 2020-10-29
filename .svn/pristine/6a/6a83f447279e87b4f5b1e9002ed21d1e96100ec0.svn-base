package com.ht.persistence.model.background.dicdata.defect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.persistence.model.base.BaseModel;

/** 
* @ClassName: DefectDao 
* @Description: TODO(缺陷类别实体类) 
* @author penghao
* @date 2016年11月6日 下午2:38:15 
*  
*/
@Entity
@Table(name = "DEFECTIVE")
public class ViewDefect extends BaseModel{
	
	@Id
	@Column(name = "id", length = 64)
	private String id;               //id
	private String chartName;        //海图类型
	private String typeName;		 //缺陷类别名称
	private String itemName;		 //缺陷项目名称
	private String discription;		 //缺陷的描述
	private String deep;		 //缺陷的级别
	private String score;		 //扣分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChartName() {
		return chartName;
	}
	public String getTypeName() {
		return typeName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getDeep() {
		return deep;
	}
	public void setDeep(String deep) {
		this.deep = deep;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
