package com.ht.persistence.model.background.dicdata.defectitem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
import com.ht.persistence.model.base.BaseModel;

/** 
* @ClassName: DefectDao 
* @Description: TODO(缺陷项目实体类) 
* @author penghao
* @date 2016年11月6日 下午2:38:15 
*  
*/
@Entity
@Table(name = "DEFECTIVE_ITEM")
public class DefectItem extends BaseModel{
	
	@Id
	@Column(name = "id", length = 64)
	private String id;               //id
	private BaseData charttype;
	private DefectType type;		//缺陷类别
	@Column(name = "item", length = 256)
	private String item;			 //缺陷项目
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
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	
}
