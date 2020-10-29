package com.ht.persistence.dao.impl.drawtask.taskbook.book;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.drawtask.taskbook.book.TaskBookDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBook;
import com.ht.persistence.model.drawtask.taskbook.book.TaskBookList;
import com.ht.persistence.model.drawtask.taskbook.book.VTaskBook;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;

/**
 * 编绘任务书
 * @author huodesheng
 *
 */
public class TaskBookDaoImpl extends BaseDaoImpl implements TaskBookDao {

	@SuppressWarnings("unchecked")
	@Override
	public TaskBook findById(String id) throws Exception {
		List<TaskBook> result = null;
		try {
			Query setParameter = this.getSession().createQuery(" from TaskBook where id = :id").setParameter("id", id);
//			result = this.findByNamedParam("findTaskBookById","id",id);
			result = setParameter.list();
			if(CollectionUtils.isEmpty(result)){
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return result.get(0);
	}
	@Override
	public TaskBookList findBookListByid(String id) throws Exception {
		List<TaskBookList> result = null;
		try {
			SQLQuery query = this.getSession().createSQLQuery("select t.*,b.value from COMPILATION_TASK_BOOK t , BASE_DATA b where  b.type_id=? and t.TASKBOOK_TYPE=b.code and t.id=?");
			query.setString(0,BaseDataConstants.TASK_BOOK_TYPE);
			query.setString(1, id);
			query.addEntity(TaskBookList.class);
			result=query.list();
			if(CollectionUtils.isEmpty(result)){
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return result.get(0);
	}

	@Override
	public List<TaskBook> findList() throws Exception {
		List<TaskBook> list = null;
		try {
			
			list = this.findByNamedQuery("findTaskBookList");
			if(CollectionUtils.isEmpty(list)){
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TaskBookList> findListIncludeTypeName(String year) throws Exception {
		List<TaskBookList> appList = null;
		try
		{
			/*SQLQuery query = this.getSession().createSQLQuery("select t.* from COMPILATION_TASK_BOOK t where to_char(t.creation_date,'yyyy')=? and t.state ='已下发' order by t.creation_date desc");*/
			
			//2018.6.6   按要求  以任务书编号年份归档 
			SQLQuery query = this.getSession().createSQLQuery("select t.* from COMPILATION_TASK_BOOK t where substr(TASKBOOK_NO,1,4)=? and t.state ='已下发' order by t.creation_date desc");
			query.setString(0, year);
			query.addEntity(TaskBookList.class);
			appList=  query.list();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return appList;
	}
	
	@Override
	public long count() throws Exception {
		return 0;
	}

	@Override
	public boolean exists(String taskBookNo) throws Exception {
		return false;
	}

	@Override
	public void addTaskBook(TaskBook taskBook) throws Exception {
		this.save(taskBook);
	}

	@Override
	public void modifyTaskBook(TaskBook taskBook) throws Exception {
		this.saveOrUpdate(taskBook);
	}

	@Override
	public void delTaskBook(String id) throws Exception {
		this.delete(id);
	}

	@Override
	public void delTaskBook(TaskBook taskBook) throws Exception {
		this.delete(taskBook);
	}

	@Override
	public List<TaskBook> countByNo(String noName) throws Exception {
		try {
			
			String hql="from TaskBook taskBook where taskBook.taskbookNo like :param";
			return this.getSession().createQuery(hql).setString("param", "%"+noName+"%").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getTechnologyDemand() throws Exception {
		String hql="from BaseData baskData where baskData.typeName = :param order by baskData.code";
		return this.getSession().createQuery(hql).setString("param","技术要求").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBook> findByType(String taskBookType) throws Exception {
		return this.findByNamedQueryAndNamedParam("findTaskBookByType", "taskBookType", taskBookType);
	}

	@Override
	public List<TaskBook> findFiveList() {
		@SuppressWarnings("unchecked")
		List<TaskBook> list = this.findByNamedQuery("findTaskBookList");
		if (list.size()>=5) {
			list = list.subList(0, 5);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getOtherDemand() throws Exception {
		String hql="from BaseData baskData where baskData.typeName = :param order by baskData.code";
		return this.getSession().createQuery(hql).setString("param","其他要求").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getTechnologyStandard() throws Exception {
		String hql="from BaseData baskData where baskData.typeName = :param order by baskData.code";
		return this.getSession().createQuery(hql).setString("param","技术标准").list();
	}

	/**
	 * 获取版次
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getVersion() throws Exception {
		String hql="from BaseData baskData where baskData.typeName = :param order by baskData.code";
		return this.getSession().createQuery(hql).setString("param","版次").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBookList> findListCorrectionNotice() throws Exception
	{
		List<TaskBookList> appList = null;
		try
		{
			SQLQuery query = this.getSession().createSQLQuery("select t.*,b.value from COMPILATION_TASK_BOOK t , BASE_DATA b where  b.type_id=? and t.TASKBOOK_TYPE=b.code and TASKBOOK_TYPE = 'TASK_BOOK_CORRECTION_NOTICE' order by NOTICE_NO desc");
			query.setString(0,BaseDataConstants.TASK_BOOK_TYPE);
			query.addEntity(TaskBookList.class);
			appList=  query.list();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return appList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TaskBook> findListCorrectionNoticeByYear(String year) throws Exception
	{
		List<TaskBook> appList = null;
		try
		{
			SQLQuery query = this.getSession().createSQLQuery("select t.* from COMPILATION_TASK_BOOK t , BASE_DATA b where  b.type_id=? and t.TASKBOOK_TYPE=b.code and TASKBOOK_TYPE = 'TASK_BOOK_CORRECTION_NOTICE' and substr(t.TASKBOOK_NO,1,4)=? order by NOTICE_NO desc");
			query.setString(0,BaseDataConstants.TASK_BOOK_TYPE);
			query.setString(1,year);
			query.addEntity(TaskBook.class);
			appList=  query.list();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return appList;
	}
	@Override
	public List<TaskBookList> findListByYearAndState(String year, String state) throws Exception
	{
		List<TaskBookList> appList = null;
		try
		{
			SQLQuery query = this.getSession().createSQLQuery("select t.* from COMPILATION_TASK_BOOK t where substr(t.TASKBOOK_NO,1,4)=?  and t.state=? order by t.creation_date desc");
			query.setString(0, year);
			query.setString(1, state);
			query.addEntity(TaskBookList.class);
			appList=  query.list();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return appList;
	}
	@Override
	public List<VTaskBook> findCreateTaskListByYearAndState(String year, String state) throws Exception
	{
		List<VTaskBook> appList = null;
		try
		{
			SQLQuery query = this.getSession().createSQLQuery("select t.* from V_COMPILATION_TASK_BOOK_NUMBER t where   substr(t.TASKBOOK_NO,1,4)=?  and t.state=? order by t.creation_date desc");
			query.setString(0, year);
			query.setString(1, state);
			query.addEntity(VTaskBook.class);
			appList=  query.list();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return appList;
	}

	/**
	 * 下发
	 */
	@Override
	public void issue(TaskBook tb) throws Exception
	{
		this.update(tb);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TaskBookList> findList(String year,boolean jurisdiction,String booktype) throws Exception {
 		List<TaskBookList> appList = null;
		try
		{
			String Booktype=" ";
			if(booktype.equals("1")) {Booktype="   and t.TASKBOOK_NO like '%绘%'   ";}
			if(booktype.equals("2")) {Booktype="   and t.TASKBOOK_NO like '%临%'   ";}
			
			String sql="select t.* from COMPILATION_TASK_BOOK t where t.state='已下发' and  substr(t.TASKBOOK_NO,1,4)=? "+Booktype+" order by t.creation_date desc";
			if(jurisdiction){
			/*	sql="select t.* from COMPILATION_TASK_BOOK t where to_char(t.creation_date,'yyyy')=? order by t.creation_date desc";*/
			sql="select *  from COMPILATION_TASK_BOOK where  substr(TASKBOOK_NO,1,4)=? "+Booktype+" order by creation_date desc"; //2018.6.6   按要求  以任务书编号年份归档 
		// 取修订次数最大的值，关联任务书，重做了任务书的字段，取消这种做法
			//select A.*,b.revision as erevision,to_char(b.CREATION_DATE,'yyyy-mm-dd') as creationtime from COMPILATION_TASK_BOOK a  left join ( SELECT * FROM COMPILATION_TASK_BOOK_EIDT_HIS W1 WHERE NOT EXISTS( SELECT 1 FROM COMPILATION_TASK_BOOK_EIDT_HIS W2 WHERE W1.TASKBOOK_ID=W1.TASKBOOK_ID AND W1.REVISION < W2.REVISION) ) b on a.ID=b.TASKBOOK_ID  where   substr(a.TASKBOOK_NO,1,4)=?  order by a.creation_date desc";
			}
		
						
			SQLQuery query = this.getSession().createSQLQuery(sql);
			query.setString(0, year);
			query.addEntity(TaskBookList.class);
			appList=  query.list();
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return appList;
	}
	@Override
	public String findTaskselect(String taskselect) {

		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		
 		String selectNum=null;
 		String type=null;
		try
		{
			if(taskselect.equals("year")) {
				type="印绘";
			}else {
				type="印临";
			}
			//// 取编号 在转数字 在取最大值
		 String sql="select max(CAST(SUBSTR(taskbook_no, 8, 3) as int)) from compilation_task_book where taskbook_no like '%"+year+"%' and taskbook_no like '%"+type+"%'";
		  SQLQuery query = this.getSession().createSQLQuery(sql.toString());
		  selectNum = query.uniqueResult().toString();
		  return selectNum;
		}
		
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return selectNum;
		
		
	}
}
