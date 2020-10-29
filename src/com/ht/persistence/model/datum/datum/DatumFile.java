package com.ht.persistence.model.datum.datum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 资料文件类
 * @author zyd
 *
 */
@Entity
@Table(name = "DATUM_FILE")
public class DatumFile extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id; //标识
	
	@Column(name = "category_id", length = 64, nullable = false)
	private String categoryId; //类别Id

	@Column(name = "file_name", length = 100, nullable = false)
	private String fileName; //文件名

	@Column(name = "suffix_name", length = 64, nullable = false)
	private String suffixName; //后缀名

	@Column(name = "space_size", length = 100, nullable = false)
	private String spaceSize; //空间大小

	@Column(name = "file_path", length = 254, nullable = false)
	private String filePath; //路径

	@Column(name = "remarks", length = 254, nullable = false)
	private String remarks; //备注
	
	@Column(name = "entity_file_name", length = 254, nullable = false)
	private String entityFileName; //实体文件名称

	@Column(name = "entity_file_num", length = 254, nullable = false)
	private String entityFileNum; //实体文件数量

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

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(String spaceSize) {
		this.spaceSize = spaceSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEntityFileName() {
		return entityFileName;
	}

	public void setEntityFileName(String entityFileName) {
		this.entityFileName = entityFileName;
	}

	public String getEntityFileNum() {
		return entityFileNum;
	}

	public void setEntityFileNum(String entityFileNum) {
		this.entityFileNum = entityFileNum;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
	
}
