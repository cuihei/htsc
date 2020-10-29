package com.ht.persistence.model.drawtask.taskbook.book;

import com.ht.persistence.model.base.BaseModel;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="COMPILATION_TASK_UPTIME")
public class TaskBookUpTime
  extends BaseModel
{
  private String id;
  private String state;
  private String upDate;
  private String upPerson;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getUpDate() {
	return upDate;
}
public void setUpDate(String upDate) {
	this.upDate = upDate;
}
public String getUpPerson() {
	return upPerson;
}
public void setUpPerson(String upPerson) {
	this.upPerson = upPerson;
}
  
  
  
}
