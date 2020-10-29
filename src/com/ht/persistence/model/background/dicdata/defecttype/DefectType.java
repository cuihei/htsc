package com.ht.persistence.model.background.dicdata.defecttype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.base.BaseModel;

/** 
* @ClassName: DefectDao 
* @Description: TODO(缺陷类别实体类) 
* @author penghao
* @date 2016年11月6日 下午2:38:15 
*  
*/
@Entity
@Table(name = "DEFECTIVE_TYPE")
public class DefectType extends BaseModel{
	
	@Id
	@Column(name = "id", length = 64)
	private String id;               //id
	private BaseData charttype;      //海图类型
	@Column(name = "type_name", length = 256)
	private String typeName;			 //类别内容
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
