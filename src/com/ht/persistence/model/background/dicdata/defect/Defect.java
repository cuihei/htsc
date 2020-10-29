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
public class Defect extends BaseModel{
	
	@Id
	@Column(name = "id", length = 64)
	private String id;               //id
	private BaseData charttype;
	private DefectType type;
	private DefectItem item;
	private String discription;		 //缺陷的描述
	private String deep;		 //缺陷的级别
	private String score;		 //扣分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BaseData getCharttype() {
		return charttype;
	}
	public void setCharttype(BaseData charttype) {
		this.charttype = charttype;
	}
	public DefectType getType() {
		return type;
	}
	public void setType(DefectType type) {
		this.type = type;
	}
	public DefectItem getItem() {
		return item;
	}
	public void setItem(DefectItem item) {
		this.item = item;
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
