package com.ht.persistence.dao.impl.datum.datum;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.dao.DataAccessException;

import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.model.datum.bookinfo.BookInfo;
import com.ht.persistence.model.datum.books.Books;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;


/**
 * DatumFile数据映射操作类
 * @author zyd
 *
 */
public class DatumFileDaoImpl extends BaseDaoImpl implements DatumFileDao {
	
	/**
	 * 增加资料文件
	 * @param datumFile datumFile实体
	 */
	@Override
	public void addDatumFile(DatumFile datumFile) {
		try {
			this.save(datumFile);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新资料文件
	 * @param datumFile datumFile实体
	 */
	@Override
	public void modifyDatumFile(DatumFile datumFile) {
		this.update(datumFile);
	}
	
	/**
	 * 删除资料文件
	 * @param id DatumFile主键
	 */
	@Override
	public void deleteDatumFile(DatumFile datumFile) {
		this.delete(datumFile);
	}
	
	/**
	 * 获取全部资料文件
	 * return DatumFile列表
	 */
	@Override
	public List<DatumFile> getDatumFile() {
		try{
			@SuppressWarnings("unchecked")
			List<DatumFile> result = this.findByNamedQuery("getDatumFile");
			return result;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取一条资料文件
	 * @param id DatumFile主键
	 * return DatumFile实体
	 */
	@Override
	public DatumFile getDatumFile(DatumFile datumFile) {
		@SuppressWarnings("unchecked")
		List<DatumFile> result = this.findByNamedQueryAndNamedParam("getDatumFileById", "id", datumFile.getId());
		
		if(result.size() > 0){
			return result.get(0);
		}
		return null;
	}
	
	/**
	 * 获取部分资料
	 */
	@Override
	public List<DatumFile> getIsFile(String datumCategoryId) {
		try{
			@SuppressWarnings("unchecked")
			List<DatumFile> result = this.findByNamedQueryAndNamedParam("getIsFile","datumCategoryId",datumCategoryId);
			return result;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * 获取部分资料
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int checkIfExist(DatumCategory datumCategory) {
		if(!"201610301150".equals(datumCategory.getParentId())){
			//如果删除的是二级子类
			String [] values={"oneSubClass","twoSubClass"};
			String [] params={datumCategory.getParentId(),datumCategory.getId()};
			List<Books> booksResult = this.findByNamedQueryAndNamedParam("checkIfExistBooksByDatumCategoryId",values,params);
			List<BookInfo> bookInfoResult = this.findByNamedQueryAndNamedParam("checkIfExistBookInfoByDatumCategoryId",values,params);
			return booksResult.size()+bookInfoResult.size();
		}else{
			//如果删除的是一级子类
			List<DatumCategory> result = this.findByNamedQueryAndNamedParam("getDatumCategoryByParentId", "parentId", datumCategory.getId());
			return result.size();
		}
	}
	
	/**
	 * 获取资料维护树的数据
	 * @return List<DatumFile>资料维护列表
	 */
	@Override
	public List<DatumFile> getDatumFileTree() {
		SQLQuery query = this.getSession().createSQLQuery("");
		query.addEntity(DatumFile.class);
		@SuppressWarnings("unchecked")
		List<DatumFile> datumFileList =  query.list();
		return datumFileList;
	}
}
