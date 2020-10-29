package com.ht.persistence.model.datum.datum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 资料文件类型实体类
 * @author zyd
 *
 */
@Entity
public class DatumFileType extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id; //标识
	
	@Column(name = "file_Type", length = 64, nullable = false)
	private String fileType; //文件类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}



	
}
