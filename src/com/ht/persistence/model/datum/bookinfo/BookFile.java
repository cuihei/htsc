package com.ht.persistence.model.datum.bookinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ht.persistence.model.base.BaseModel;

/**
 * 图书文件类
 * @author 刘凯
 */
@Entity
@Table(name = "BOOK_FILE")
public class BookFile extends BaseModel {
	
	@Id
	@Column(name = "id", length = 64)
	private String id; //标识
	
	@Column(name = "book_id", length = 64, nullable = false)
	private String bookId; //图书Id
	
	@Column(name = "book_name", length = 64, nullable = false)
	private String bookName; //图书名称

	@Column(name = "file_name", length = 100, nullable = false)
	private String fileName; //文件名

	@Column(name = "suffix_name", length = 64, nullable = false)
	private String suffixName; //后缀名

	@Column(name = "space_size", length = 100, nullable = false)
	private String spaceSize; //空间大小

	@Column(name = "file_path", length = 254, nullable = false)
	private String filePath; //路径

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
}
