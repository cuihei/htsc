package com.ht.persistence.model.datum.datum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 资料文件视图类
 * @author zyd
 *
 */
@Entity
@Table(name = "v_datum_file")
public class VDatumFile extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id; //标识
	
	@Column(name = "category_id", length = 64, nullable = false)
	private String categoryId; //类别Id

	@Column(name = "file_name", length = 100, nullable = false)
	private String fileName; //文件名

	@Column(name = "file_type", length = 254, nullable = false)
	private String fileType; //文件类型
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
	
}
