package com.ht.persistence.model.correctionnotice;

import java.io.Serializable;

import com.ht.persistence.model.base.BaseModel;
/**
 * 改正通告查询 年份数据
 * @author huodesheng	
 * @date 2017/3/1
 */
public class NoticeYear extends BaseModel  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ID;//id
	private String name;//年份
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "NoticeYear [ID=" + ID + ", name=" + name + "]";
	}
	public NoticeYear() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
