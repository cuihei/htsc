package com.ht.service.impl.datum.datum;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.datum.bookinfo.ReturnBookDao;
import com.ht.persistence.dao.inter.datum.datum.BorrowingDao;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.model.datum.bookinfo.ReturnBook;
import com.ht.persistence.model.datum.datum.Borrowing;
import com.ht.persistence.model.datum.datum.BorrowingReturn;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.datum.VBorrowing;
import com.ht.persistence.model.datum.datum.VBorrowingReturn;
import com.ht.service.inter.datum.datum.BorrowingService;

/**
 * 借阅记录Service实现类
 * @author zyd
 *
 */
public class BorrowingServiceImpl implements BorrowingService {
	
	/**
	 * 注入借阅记录Dao
	 */
	@Resource (name = "BorrowingDao")
	private BorrowingDao BorrowingDao;

	/**
	 * 注入DatumFileDao
	 */
	@Resource (name = "DatumFileDao")
	private DatumFileDao datumFileDao;
	
	/**
	 * 注入借阅记录Dao
	 */
	@Resource (name = "returnBookDao")
	private ReturnBookDao returnBookDao;
	
	/**
	 * 添加借阅记录
	 * @param BorrowingParam 借阅记录数据
	 */
	@Override
	public void addBorrowing(String BorrowingParam,DatumFile datumFile,String returnNo) throws Exception {
		try {
			if(returnNo.equals("0")){
				// 将JSON转成对象
				Borrowing borrowing = (Borrowing) DataConverter.convertJson2Object(BorrowingParam, Borrowing.class);
				// 添加Borrowing
				borrowing.setId(GenerateSequenceUtil.generateSequenceNo());
				borrowing.setCreator("");
				borrowing.setCreationDate(new Date());
				borrowing.setModifier("");
				borrowing.setModifyDate(new Date());
				// 获取图书总数量
				String fileNum = datumFile.getEntityFileNum();
				// 将string类型转为int型
				int entityFileNum = Integer.parseInt(fileNum==null?"0":fileNum);
				// 获取图书借阅数量
				String borrowNo = borrowing.getBorrowNo();
				// 将string类型转为int型
				int bookBorrowNo = Integer.parseInt(borrowNo);
				// 计算剩余图书数量
				String remainingNum = String.valueOf(entityFileNum - bookBorrowNo);
				datumFile.setEntityFileNum(remainingNum);
				// 执行借阅记录的添加
				BorrowingDao.addBorrowing(borrowing);
				// 更新资料文件表
				datumFileDao.modifyDatumFile(datumFile);
			}else {
				// 将JSON转成对象
				Borrowing borrowing = (Borrowing) DataConverter.convertJson2Object(BorrowingParam, Borrowing.class);
				// 添加Borrowing
				borrowing.setCreator("");
				borrowing.setCreationDate(new Date());
				borrowing.setModifier("");
				borrowing.setModifyDate(new Date());
				
				// 获取图书总数量
				String fileNum = datumFile.getEntityFileNum();
				// 将string类型转为int型
				int entityFileNum = Integer.parseInt(fileNum);
				// 获取图书归还数量
				String borrowNo = returnNo;
				// 将string类型转为int型
				int bookReturnNo = Integer.parseInt(borrowNo);
				// 计算剩余图书数量
				String remainingNum = String.valueOf(entityFileNum + bookReturnNo);
				datumFile.setEntityFileNum(remainingNum);
				// 执行借阅记录的编辑
				BorrowingDao.modifyBorrowing(borrowing);
				// 更新资料文件表
				datumFileDao.modifyDatumFile(datumFile);
			}
			
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 更新借阅记录
	 * @param BorrowingParam 借阅记录数据
	 */
	@Override
	public void modifyBorrowing(String BorrowingParam) throws Exception {
		try {
			// 将JSON转成对象
			Borrowing borrowing = (Borrowing) DataConverter.convertJson2Object(BorrowingParam, Borrowing.class);
			// 更新借阅记录
			BorrowingDao.modifyBorrowing(borrowing);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除借阅记录数据
	 * @param id 借阅记录Id
	 */
	@Override
	public void deleteBorrowing(String id) throws Exception {
		try {
			Borrowing borrowing = new Borrowing();
			borrowing.setId(id);
			// 删除Borrowing
			BorrowingDao.deleteBorrowing(borrowing);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询所有借阅记录
	 */
	@Override
	public List<Borrowing> getBorrowing() throws Exception {
		try {
			// 获取所有Borrowing
			return BorrowingDao.getBorrowing();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询一条借阅记录
	 * @param id 借阅记录Id
	 */
	@Override
	public Borrowing getBorrowing(String id) throws Exception {
		try {
			Borrowing borrowing = new Borrowing();
			borrowing.setId(id);
			// 根据id获取Borrowing
			return BorrowingDao.getBorrowing(borrowing);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据图书编号获取一条记录
	 */
	@Override
	public Borrowing getBorrowingByFileId(String fileId) throws Exception {
		try {
			Borrowing borrowing = new Borrowing();
			borrowing.setBorrowCode(fileId);
			// 根据id获取Borrowing
			return BorrowingDao.getBorrowingByFileId(borrowing);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 获取全部图书借阅归还信息
	 * return BorrowingReturn列表
	 */
	@Override
	public List<BorrowingReturn> getBorrowingReturn() throws Exception {
		try {
			// 获取所有Borrowing
			List<BorrowingReturn> list =  BorrowingDao.getBorrowingReturn();
			return list;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 获取全部图书借阅归还信息
	 * return BorrowingReturn列表
	 */
	@Override
	public List<VBorrowingReturn> getBorrowingReturnListByPerson(String userName) throws Exception {
		try {
			// 获取所有Borrowing
			BorrowingReturn br = new BorrowingReturn();
			br.setBorrowPerson(userName);
			List<VBorrowingReturn> result = new ArrayList<VBorrowingReturn>(); 
			List<BorrowingReturn> list =  BorrowingDao.getBorrowingReturnListByPerson(br);
			List<String> ids = new ArrayList<String>();
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++){
					VBorrowingReturn v = new VBorrowingReturn();
					BorrowingReturn b = list.get(i);
					v.setId(b.getId());
					v.setBorrowBookName(b.getBorrowBookName());
					v.setBorrowCode(b.getBorrowCode());
					//汇交时间
					v.setConcurrentTime(b.getConcurrentTime());
					v.setBorrowId(b.getBorrowId());
					v.setJystatus(b.getJystatus());
					v.setBorrowPerson(b.getBorrowPerson());
					v.setBorrowDate(b.getBorrowDate());
					v.setBorrowNo(b.getBorrowNo());
					v.setType(b.getType());
					v.setReturnDate(b.getReturnDate());
					v.setReturnNo(b.getReturnNo());
					v.setReturnPerson(b.getReturnPerson());
					v.setGhstatus(b.getGhstatus());
					result.add(v);
					if(!ids.contains(list.get(i).getBorrowId())){
						ids.add(list.get(i).getBorrowId());
					}
				}
			}
			if(ids.size()>0){
				for (int i = 0; i < ids.size(); i++){
					String borrowId = ids.get(i);
					int count = 0;
					for (int j = 0; j < result.size(); j++){
						if(borrowId.equals(result.get(j).getBorrowId())){
							count = count + Integer.valueOf(result.get(j).getReturnNo()==null?"0":result.get(j).getReturnNo());
						}
					}
					for (int k = 0; k < result.size(); k++){
						if(borrowId.equals(result.get(k).getBorrowId())){
							result.get(k).setShouldReturn(String.valueOf(Integer.valueOf(result.get(k).getBorrowNo())-count));
						}
					}
				}
			}
			return result;
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	@Override
	public List<VBorrowing> getReturnList(String ids) throws Exception {
		List<VBorrowing> booklist = new ArrayList<VBorrowing>();
		try {
			if (StringUtils.isNotEmpty(ids)) {
				// 将用户String类型转成Plan对象
				String[] idsArray = ids.split(",");
				for (int i = 0; i < idsArray.length; i++) {
					if(!iscontain(idsArray[i],booklist)){
						ReturnBook rb = new ReturnBook();
						rb.setBorrowId(idsArray[i]);
						int count = returnBookDao.getReturnByBorrowId(rb);
						
						Borrowing borrowing = new Borrowing();
						borrowing.setId(idsArray[i]);
						borrowing = BorrowingDao.getBorrowing(borrowing);
						String shouldReturn = "0";
						int sum = 0;
						if(count>0){
							sum = Integer.valueOf(borrowing.getBorrowNo())-count;
							shouldReturn = String.valueOf(sum);
						}else{
							shouldReturn = borrowing.getBorrowNo();
						}
					    VBorrowing v = new VBorrowing();
					    v.setId(idsArray[i]);
					    v.setBorrowBookName(borrowing.getBorrowBookName());
					    v.setBorrowCode(borrowing.getBorrowCode());
					    v.setBorrowDate(borrowing.getBorrowDate());
					    v.setBorrowNo(borrowing.getBorrowNo());
					    v.setBorrowPerson(borrowing.getBorrowPerson());
					    v.setShouldReturn(shouldReturn);
						v.setType(borrowing.getType());
						booklist.add(v);
					}
				}
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
		return booklist;
	}
	
	public boolean iscontain(String id,List<VBorrowing> booklist) throws Exception {
		if(booklist.size()>0){
			for (int i = 0; i < booklist.size(); i++)
			{
				String vId = booklist.get(i).getId();
				if(vId.equals(id)){
					return true;
				}
			}
		}
		return false;
	}
	
}
