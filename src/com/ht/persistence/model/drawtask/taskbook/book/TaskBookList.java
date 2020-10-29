package com.ht.persistence.model.drawtask.taskbook.book;

import com.ht.persistence.model.base.BaseModel;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="COMPILATION_TASK_BOOK")
public class TaskBookList
  extends BaseModel
{
  private String id;
  private String taskbookName;
  private String taskbookNo;
  private String taskName;
  private String taskFrom;
  private String executeDeptId;
  private Date executeTime;
  private String technologyStandard;
  private String technologyDemand;
  private String otherDemand;
  private String revision;
  private String authenticity;
  private String enclosure;
  private String taskBookType;
  private String projectName;
  private String noticeNo;
  private String correctNo;
  private String correctNoticeNo;
  private String itemName;
  private String correctItemNo;
  private Date endTime;
  private Date issueTime;
  private String state;
  private String ehRevision;
  private Date ehCreationDate;
  private Date sjhjTime;
  private String planId;
  public String getEhRevision()
  {
    return this.ehRevision;
  }
  
  public void setEhRevision(String ehRevision)
  {
    this.ehRevision = ehRevision;
  }
  
  public Date getEhCreationDate()
  {
    return this.ehCreationDate;
  }
  
  public void setEhCreationDate(Date ehCreationDate)
  {
    this.ehCreationDate = ehCreationDate;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getTaskbookName()
  {
    return this.taskbookName;
  }
  
  public void setTaskbookName(String taskbookName)
  {
    this.taskbookName = taskbookName;
  }
  
  public String getTaskbookNo()
  {
    return this.taskbookNo;
  }
  
  public void setTaskbookNo(String taskbookNo)
  {
    this.taskbookNo = taskbookNo;
  }
  
  public String getTaskName()
  {
    return this.taskName;
  }
  
  public void setTaskName(String taskName)
  {
    this.taskName = taskName;
  }
  
  public String getTaskFrom()
  {
    return this.taskFrom;
  }
  
  public void setTaskFrom(String taskFrom)
  {
    this.taskFrom = taskFrom;
  }
  
  public String getExecuteDeptId()
  {
    return this.executeDeptId;
  }
  
  public void setExecuteDeptId(String executeDeptId)
  {
    this.executeDeptId = executeDeptId;
  }
  
  public Date getExecuteTime()
  {
    return this.executeTime;
  }
  
  public void setExecuteTime(Date executeTime)
  {
    this.executeTime = executeTime;
  }
  
  public String getTechnologyStandard()
  {
    return this.technologyStandard;
  }
  
  public void setTechnologyStandard(String technologyStandard)
  {
    this.technologyStandard = technologyStandard;
  }
  
  public String getTechnologyDemand()
  {
    return this.technologyDemand;
  }
  
  public void setTechnologyDemand(String technologyDemand)
  {
    this.technologyDemand = technologyDemand;
  }
  
  public String getOtherDemand()
  {
    return this.otherDemand;
  }
  
  public void setOtherDemand(String otherDemand)
  {
    this.otherDemand = otherDemand;
  }
  
  public String getRevision()
  {
    return this.revision;
  }
  
  public void setRevision(String revision)
  {
    this.revision = revision;
  }
  
  public String getAuthenticity()
  {
    return this.authenticity;
  }
  
  public void setAuthenticity(String authenticity)
  {
    this.authenticity = authenticity;
  }
  
  public String getEnclosure()
  {
    return this.enclosure;
  }
  
  public void setEnclosure(String enclosure)
  {
    this.enclosure = enclosure;
  }
  
  public String getTaskBookType()
  {
    return this.taskBookType;
  }
  
  public void setTaskBookType(String taskBookType)
  {
    this.taskBookType = taskBookType;
  }
  
  public String getProjectName()
  {
    return this.projectName;
  }
  
  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }
  
  public String getNoticeNo()
  {
    return this.noticeNo;
  }
  
  public void setNoticeNo(String noticeNo)
  {
    this.noticeNo = noticeNo;
  }
  
  public String getCorrectNo()
  {
    return this.correctNo;
  }
  
  public void setCorrectNo(String correctNo)
  {
    this.correctNo = correctNo;
  }
  
  public String getCorrectNoticeNo()
  {
    return this.correctNoticeNo;
  }
  
  public void setCorrectNoticeNo(String correctNoticeNo)
  {
    this.correctNoticeNo = correctNoticeNo;
  }
  
  public String getItemName()
  {
    return this.itemName;
  }
  
  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }
  
  public String getCorrectItemNo()
  {
    return this.correctItemNo;
  }
  
  public void setCorrectItemNo(String correctItemNo)
  {
    this.correctItemNo = correctItemNo;
  }
  
  public Date getEndTime()
  {
    return this.endTime;
  }
  
  public void setEndTime(Date endTime)
  {
    this.endTime = endTime;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public Date getIssueTime()
  {
    return this.issueTime;
  }
  
  public void setIssueTime(Date issueTime)
  {
    this.issueTime = issueTime;
  }

public Date getSjhjTime() {
	return sjhjTime;
}

public void setSjhjTime(Date sjhjTime) {
	this.sjhjTime = sjhjTime;
}

public String getPlanId() {
	return planId;
}

public void setPlanId(String planId) {
	this.planId = planId;
}
}
