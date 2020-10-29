package com.ht.persistence.dao.impl.catalog.detail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.util.CollectionUtils;

import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.impl.base.BaseDaoImpl;
import com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.drawtask.taskbook.book.StatisticsTask;
import com.ht.persistence.model.system.workflow.task.HiTask;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;

/**
 * @ClassName: CatalogDetailDaoImpl
 * @Description: 目录明细的数据操作类
 * @author penghao
 * @date 2016年10月14日 下午5:00:13
 * 
 */
@SuppressWarnings("unchecked")
public class CatalogDetailDaoImpl extends BaseDaoImpl implements CatalogDetailDao {

	/*
	 * (非 Javadoc) Title: getDetail Description:
	 * 
	 * @param id
	 * 
	 * @return 根据Id获取目录实体
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#getDetail(java.
	 * lang.String)
	 */
	@Override
	public CatalogDetail getDetail(CatalogDetail detail) throws Exception {
		List<CatalogDetail> result = null;
		try {
			// 获取目录
			result = this.findByNamedQueryAndNamedParam("findCatalogDetailById", "id", detail.getId());
			if (CollectionUtils.isEmpty(result)) {
				return null;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return result.get(0);
	}

	/*
	 * (非 Javadoc) Title: getDetailList Description: 获取目录明细列表
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#getDetailList()
	 */
	@Override
	public List<CatalogDetail> getDetailListByCatalogTypeId(CatalogDetail detail) throws Exception {
		String typeId = detail.getType().getId();
		Date star = null;
		Date end = null;
		if (detail != null && detail.getCreationDate() != null) {
			star = detail.getCreationDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(star);
			cal.add(Calendar.YEAR, 1);
			end = cal.getTime();

		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(" from CatalogDetail where type.id = ? and creationDate between ? and ?");
		if (detail != null && StringUtils.isNotBlank(detail.getStarLongitude())
				&& StringUtils.isNotBlank(detail.getEndLongitude()) && StringUtils.isNotBlank(detail.getStarLatitude())
				&& StringUtils.isNotBlank(detail.getEndLatitude())) {

			/*
			 * 经纬度转为数据比较 INSTR 字符在字符转的位置、SUBSTR 字符串截取、to_number 转为numerber 类型 、 trunc 保留小数五位
			 */

			// 第一种 数据库的起始经度小于或等于终止经度，起始纬度小于或等于终止纬度
			// 起始经度
			buffer.append(" and(( trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)>=trunc(?,5)");
			// 终止经度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)<=trunc(?,5)");
			// 起始纬度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)>=trunc(?,5)");

			// 终止纬度
			buffer.append(" and  trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)<=trunc(?,5) )");

			buffer.append(" and  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)< "
					+ " trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)  and "
					+ "trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)<"
					+ "trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)  )");

			// 第二种 数据库起始经度大于终止经度，起始纬度小于或等于终止纬度
			// 起始经度
			buffer.append("or(  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)<=trunc(?,5)");
			// 终止经度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)>=trunc(?,5)");
			// 起始纬度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)>=trunc(?,5) ");

			// 终止纬度
			buffer.append(" and  trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)<=trunc(?,5)  ");

			buffer.append(" and  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)> "
					+ " trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)  and "
					+ "trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)<"
					+ "trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)  )");

			// 第三种 数据库的起始经度小于或等于终止经度，起始纬度大于终止纬度

			// 起始经度
			buffer.append("or(  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)>=trunc(?,5)");
			// 终止经度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)<=trunc(?,5)");
			// 起始纬度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)<=trunc(?,5) ");

			// 终止纬度
			buffer.append(" and  trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)>=trunc(?,5)   ");

			buffer.append(" and  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)< "
					+ " trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)  and "
					+ "trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)>"
					+ "trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)  )");

			// 第四种 数据库的起始经度大于终止经度，起始纬度大于终止纬度
			// 起始经度
			buffer.append("or(  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)<=trunc(?,5)");
			// 终止经度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)>=trunc(?,5)");
			// 起始纬度
			buffer.append(" and trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)<=trunc(?,5) ");

			// 终止纬度
			buffer.append(" and  trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)>=trunc(?,5) ) ");

			buffer.append(" and  trunc( to_number( to_number(SUBSTR(starLongitude,1,INSTR(starLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLongitude,INSTR(starLongitude, '°')+1,INSTR(starLongitude, '′')-INSTR(starLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLongitude,INSTR(starLongitude, '′')+1,2))/60/60 ),5)> "
					+ " trunc(to_number( to_number(SUBSTR(endLongitude,1,INSTR(endLongitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLongitude,INSTR(endLongitude, '°')+1,INSTR(endLongitude, '′')-INSTR(endLongitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLongitude,INSTR(endLongitude, '′')+1,2))/60/60),5)  and "
					+ "trunc(to_number( to_number(SUBSTR(starLatitude,1,INSTR(starLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(starLatitude,INSTR(starLatitude, '°')+1,INSTR(starLatitude, '′')-INSTR(starLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(starLatitude,INSTR(starLatitude, '′')+1,2))/60/60 ),5)>"
					+ "trunc(to_number (to_number(SUBSTR(endLatitude,1,INSTR(endLatitude, '°')-1 ))+"
					+ "(to_number(SUBSTR(endLatitude,INSTR(endLatitude, '°')+1,INSTR(endLatitude, '′')-INSTR(endLatitude, '°')-1)))/60"
					+ "+to_number(SUBSTR(endLatitude,INSTR(endLatitude, '′')+1,2))/60/60) ,5)  )");

			// buffer.append(" and starLongitude<=endLongitude and
			// starLatitude<=endLatitude)");

			/*
			 * // 第一种 数据库的起始经度小于或等于终止经度，起始纬度小于或等于终止纬度
			 * buffer.append(" and (((starLongitude>=? and endLongitude<=?)");
			 * buffer.append(" or (starLongitude<=? and endLongitude>=?)");
			 * buffer.append(" or (starLongitude<=? and endLongitude>=?))");
			 * buffer.append(" and ((starLatitude>=? and endLatitude<=?)");
			 * buffer.append(" or (starLatitude<=? and endLatitude>=?)");
			 * buffer.append(" or (starLatitude<=? and endLatitude>=?))"); buffer.
			 * append(" and starLongitude<=endLongitude and starLatitude<=endLatitude)"); //
			 * 第二种 数据库的起始经度大于终止经度，起始纬度大于终止纬度
			 * buffer.append(" or (((endLongitude>=? and starLongitude<=?)");
			 * buffer.append(" or ( endLongitude<=? and starLongitude>=?)");
			 * buffer.append(" or (endLongitude<=? and starLongitude>=?))");
			 * buffer.append(" and ((endLatitude>=? and starLatitude<=?)");
			 * buffer.append(" or (endLatitude<=? and starLatitude>=?)");
			 * buffer.append(" or (endLatitude<=? and starLatitude>=?))");
			 * buffer.append(" and starLongitude>endLongitude and starLatitude>endLatitude)"
			 * ); // 第三种 数据库的起始经度小于或等于终止经度，起始纬度大于终止纬度
			 * buffer.append(" or (((starLongitude>=? and endLongitude<=?)");
			 * buffer.append(" or (starLongitude<=? and endLongitude>=?)");
			 * buffer.append(" or (starLongitude<=? and endLongitude>=?))");
			 * buffer.append(" and ((endLatitude>=? and starLatitude<=?)");
			 * buffer.append(" or (endLatitude<=? and starLatitude>=?)");
			 * buffer.append(" or (endLatitude<=? and starLatitude>=?))"); buffer.
			 * append(" and starLongitude<=endLongitude and starLatitude>endLatitude)"); //
			 * 第四种 数据库起始经度大于终止经度，起始纬度小于或等于终止纬度
			 * buffer.append(" or (((endLongitude>=? and starLongitude<=?)");
			 * buffer.append(" or ( endLongitude<=? and starLongitude>=?)");
			 * buffer.append(" or (endLongitude<=? and starLongitude>=?))");
			 * buffer.append(" and ((starLatitude>=? and endLatitude<=?)");
			 * buffer.append(" or (starLatitude<=? and endLatitude>=?)");
			 * buffer.append(" or (starLatitude<=? and endLatitude>=?))"); buffer.
			 * append(" and starLongitude>endLongitude and starLatitude<=endLatitude)");
			 */

		}
		if (detail != null && StringUtils.isNotBlank(detail.getStatus())) {
			if (detail.getStatus().equals("1")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_WAIT + "%'");
			} else if (detail.getStatus().equals("2")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_PASS + "%'");
			} else if (detail.getStatus().equals("3")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_BACK + "%'");
			} else {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_INIT + "%'");
			}
		}
		buffer.append(" order by creationDate desc");
		List<CatalogDetail> list = null;
		try {
			// 获取目录明细列表
			/* list = this.find(buffer.toString()); */
			Query query = this.getSession().createQuery(buffer.toString());
			query.setParameter(0, typeId);
			query.setParameter(1, star);
			query.setParameter(2, end);
			if (detail != null && StringUtils.isNotBlank(detail.getStarLongitude())
					&& StringUtils.isNotBlank(detail.getEndLongitude())
					&& StringUtils.isNotBlank(detail.getStarLatitude())
					&& StringUtils.isNotBlank(detail.getEndLatitude())) {

				/*
				 * // 起始经度 String starLongitude = detail.getStarLongitude(); // 终止经度 String
				 * endLongitude = detail.getEndLongitude(); // 起始纬度 String starLatitude =
				 * detail.getStarLatitude(); // 终止纬度 String endLatitude =
				 * detail.getEndLatitude();
				 */

				// 起始经度
				Double starLongitude = Double.parseDouble(detail.getStarLongitude());
				// 终止经度
				Double endLongitude = Double.parseDouble(detail.getEndLongitude());
				// 起始纬度
				Double starLatitude = Double.parseDouble(detail.getStarLatitude());
				// 终止纬度
				Double endLatitude = Double.parseDouble(detail.getEndLatitude());

				// 第一种
				query.setParameter(3, starLongitude);
				query.setParameter(4, endLongitude);
				query.setParameter(5, starLatitude);
				query.setParameter(6, endLatitude);
				// 第二种
				query.setParameter(7, starLongitude);
				query.setParameter(8, endLongitude);
				query.setParameter(9, starLatitude);
				query.setParameter(10, endLatitude);

				// 第三种
				query.setParameter(11, starLongitude);
				query.setParameter(12, endLongitude);
				query.setParameter(13, starLatitude);
				query.setParameter(14, endLatitude);

				// 第四种
				query.setParameter(15, starLongitude);
				query.setParameter(16, endLongitude);
				query.setParameter(17, starLatitude);
				query.setParameter(18, endLatitude);

				/*
				 * // 经度 query.setParameter(3, starLongitude); query.setParameter(4,
				 * endLongitude); query.setParameter(5, starLongitude); query.setParameter(6,
				 * starLongitude); query.setParameter(7, endLongitude); query.setParameter(8,
				 * endLongitude); // 纬度 query.setParameter(9, starLatitude);
				 * query.setParameter(10, endLatitude); query.setParameter(11, starLatitude);
				 * query.setParameter(12, starLatitude); query.setParameter(13, starLatitude);
				 * query.setParameter(14, endLongitude); // 经度 query.setParameter(15,
				 * starLongitude); query.setParameter(16, endLongitude); query.setParameter(17,
				 * starLongitude); query.setParameter(18, starLongitude); query.setParameter(19,
				 * endLongitude); query.setParameter(20, endLongitude); // 纬度
				 * query.setParameter(21, starLatitude); query.setParameter(22, endLatitude);
				 * query.setParameter(23, starLatitude); query.setParameter(24, starLatitude);
				 * query.setParameter(25, starLatitude); query.setParameter(26, endLongitude);
				 * // 经度 query.setParameter(27, starLongitude); query.setParameter(28,
				 * endLongitude); query.setParameter(29, starLongitude); query.setParameter(30,
				 * starLongitude); query.setParameter(31, endLongitude); query.setParameter(32,
				 * endLongitude); // 纬度 query.setParameter(33, starLatitude);
				 * query.setParameter(34, endLatitude); query.setParameter(35, starLatitude);
				 * query.setParameter(36, starLatitude); query.setParameter(37, starLatitude);
				 * query.setParameter(38, endLongitude); // 经度 query.setParameter(39,
				 * starLongitude); query.setParameter(40, endLongitude); query.setParameter(41,
				 * starLongitude); query.setParameter(42, starLongitude); query.setParameter(43,
				 * endLongitude); query.setParameter(44, endLongitude); // 纬度
				 * query.setParameter(45, starLatitude); query.setParameter(46, endLatitude);
				 * query.setParameter(47, starLatitude); query.setParameter(48, starLatitude);
				 * query.setParameter(49, starLatitude); query.setParameter(50, endLongitude);
				 */
			}
			list = query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return list;
	}

	/*
	 * (非 Javadoc) Title: exists Description: 判断实体是否存在
	 * 
	 * @param mapNo
	 * 
	 * @return true or false
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#exists(java.lang
	 * .String)
	 */
	@Override
	public boolean exists(CatalogDetail detail) throws Exception {

		try {
			String[] names = { "mapNo", "id" };
			String categoryId = "";
			if (detail.getType() != null && StringUtils.isNotEmpty(detail.getType().getId())) {
				categoryId = detail.getType().getId();
			}
			String[] params = { detail.getMapNo(), categoryId };
			List<CatalogDetail> result = this.findByNamedQueryAndNamedParam("findCatalogDetailByMapNo", names, params);
			if (!CollectionUtils.isEmpty(result)) {
				return true;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return false;
	}

	/*
	 * (非 Javadoc) Title: exists Description: 判断实体是否存在
	 * 
	 * @param mapNo
	 * 
	 * @return true or false
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#exists(java.lang
	 * .String)
	 */
	@Override
	public List<CatalogDetail> getDetialListByMapNoAndCatalogId(CatalogDetail detail) throws Exception {
		List<CatalogDetail> list = null;
		try {
			String[] names = { "mapNo", "id" };
			String categoryId = "";
			if (detail.getType() != null && StringUtils.isNotEmpty(detail.getType().getId())) {
				categoryId = detail.getType().getId();
			}
			String[] params = { detail.getMapNo(), categoryId };
			list = this.findByNamedQueryAndNamedParam("findCatalogDetailByMapNo", names, params);
			if (!CollectionUtils.isEmpty(list)) {
				return list;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return null;
	}

	public List<CatalogDetail> getDetialListById(String id) {
		try {
			String hql = "select * from CATALOG_DETAIL WHERE id='" + id + "' order by id";
			SQLQuery query = getSession().createSQLQuery(hql);
			query.addEntity(CatalogDetail.class);
			List<CatalogDetail> ht = query.list();
			return ht;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public List<CatalogDetail> getDetialListByMapNo(CatalogDetail detail) throws Exception {
		List<CatalogDetail> list = null;
		try {
			String[] names = { "mapNo", "id" };
			String[] params = { detail.getMapNo(), detail.getType().getId() };
			list = this.findByNamedQueryAndNamedParam("findCatalogDetailByMapNo", names, params);
			if (!CollectionUtils.isEmpty(list)) {
				return list;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return null;
	}

	/*
	 * (非 Javadoc) Title: addDetail Description: 新增目录明细
	 * 
	 * @param detail 目录明细实体类
	 * 
	 * @throws Exception
	 * 
	 * @see com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#addDetail(
	 * com.ht.persistence.model.catalog.detail.CatalogDetail)
	 */
	@Override
	public void addDetail(CatalogDetail detail) throws Exception {
		this.save(detail);

	}

	/*
	 * (非 Javadoc) Title: modifyDetail Description: 更新一个实体
	 * 
	 * @param detail
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#modifyDetail
	 * (com.ht.persistence.model.catalog.detail.CatalogDetail)
	 */
	@Override
	public void modifyDetail(CatalogDetail detail) throws Exception {
		CatalogDetail detial2 = (CatalogDetail) this.merge(detail);
		this.update(detial2);
	}

	/*
	 * (非 Javadoc) Title: delDetail Description: 删除一个实体
	 * 
	 * @param detail
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao#delDetail(com.ht
	 * .persistence.model.catalog.detail.CatalogDetail)
	 */
	@Override
	public void delDetail(CatalogDetail detail) throws Exception {
		this.delete(detail);

	}

	/*
	 * (非 Javadoc) Title: getDetailById Description: 更具CATEGORY_ID,AREA_ID获取目录
	 * 
	 * @param id 目录ID
	 * 
	 * @return CatalogDetail 目录明细实体
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.ht.service.inter.catalog.detail.CatalogDetailService#getDetailById(java.
	 * lang.String)
	 */
	@Override
	public List<CatalogDetail> getDetailByCategoryId(CatalogDetail detail) throws Exception {
		List<CatalogDetail> list = null;
		try {
			String[] names = { "categoryId", "areaId" };
			String[] params = { detail.getType().getId(), detail.getArea().getId() };
			list = this.findByNamedQueryAndNamedParam("getDetailByCategoryId", names, params);
			if (!CollectionUtils.isEmpty(list)) {
				return list;
			}
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage());
		}
		return null;
	}

	@Override
	public List<BaseData> getBaseDataFromDetail() throws Exception {
		try {
			String sql = "select new BaseData(bd.id,bd.value) from BaseData bd "
					+ ",CatalogDetail cd where cd.type.id = bd.id " + "group by bd.value,bd.id";
			Query query = this.getSession().createQuery(sql);
			List<BaseData> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CatalogArea> getCatalogAreaListFromDetail(BaseData baseData) throws Exception {
		try {
			String sql = "select new CatalogArea(ca.areaName,ca.id) from CatalogArea ca,CatalogDetail cd"
					+ ",BaseData bd where bd.id = :baseDataId and cd.area.id = ca.id "
					+ "and bd.id = cd.type.id group by ca.areaName,ca.id";
			Query query = this.getSession().createQuery(sql).setParameter("baseDataId", baseData.getId());
			List<CatalogArea> list = query.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CatalogDetail> getOtherDetailListByCatalogTypeId(CatalogDetail detail) throws Exception {
		String typeId = detail.getType().getId();
		Date star = null;
		Date end = null;
		if (detail != null && detail.getCreationDate() != null) {
			star = detail.getCreationDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(star);
			cal.add(Calendar.YEAR, 1);
			end = cal.getTime();

		}
		System.out.println(end);
		StringBuffer buffer = new StringBuffer();
		buffer.append("select  from CatalogDetail where status = ? and type.id = ? and creationDate between ? and ?");
		if (detail != null && StringUtils.isNotBlank(detail.getStarLongitude())
				&& StringUtils.isNotBlank(detail.getEndLongitude()) && StringUtils.isNotBlank(detail.getStarLatitude())
				&& StringUtils.isNotBlank(detail.getEndLatitude())) {

			// 第一种 数据库的起始经度小于或等于终止经度，起始纬度小于或等于终止纬度
			buffer.append(" and (((starLongitude>=? and endLongitude<=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?))");
			buffer.append(" and ((starLatitude>=? and endLatitude<=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?))");
			buffer.append(" and starLongitude<=endLongitude and starLatitude<=endLatitude)");
			// 第二种 数据库的起始经度大于终止经度，起始纬度大于终止纬度
			buffer.append(" or (((endLongitude>=? and starLongitude<=?)");
			buffer.append(" or ( endLongitude<=? and starLongitude>=?)");
			buffer.append(" or (endLongitude<=? and starLongitude>=?))");
			buffer.append(" and ((endLatitude>=? and starLatitude<=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?))");
			buffer.append(" and starLongitude>endLongitude and starLatitude>endLatitude)");
			// 第三种 数据库的起始经度小于或等于终止经度，起始纬度大于终止纬度
			buffer.append(" or (((starLongitude>=? and endLongitude<=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?))");
			buffer.append(" and ((endLatitude>=? and starLatitude<=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?))");
			buffer.append(" and starLongitude<=endLongitude and starLatitude>endLatitude)");
			// 第四种 数据库起始经度大于终止经度，起始纬度小于或等于终止纬度
			buffer.append(" or (((endLongitude>=? and starLongitude<=?)");
			buffer.append(" or ( endLongitude<=? and starLongitude>=?)");
			buffer.append(" or (endLongitude<=? and starLongitude>=?))");
			buffer.append(" and ((starLatitude>=? and endLatitude<=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?))");
			buffer.append(" and starLongitude>endLongitude and starLatitude<=endLatitude)");

		}
		if (detail != null && StringUtils.isNotBlank(detail.getStatus())) {
			if (detail.getStatus().equals("1")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_WAIT + "%'");
			} else if (detail.getStatus().equals("2")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_PASS + "%'");
			} else if (detail.getStatus().equals("3")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_BACK + "%'");
			} else {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_INIT + "%'");
			}
		}
		buffer.append(" order by creationDate DESC ");
		List<CatalogDetail> list = null;
		try {
			// 获取目录明细列表
			/* list = this.find(buffer.toString()); */
			Query query = this.getSession().createQuery(buffer.toString());
			query.setParameter(0, "审核通过");
			query.setParameter(1, typeId);
			query.setParameter(2, star);
			query.setParameter(3, end);
			if (detail != null && StringUtils.isNotBlank(detail.getStarLongitude())
					&& StringUtils.isNotBlank(detail.getEndLongitude())
					&& StringUtils.isNotBlank(detail.getStarLatitude())
					&& StringUtils.isNotBlank(detail.getEndLatitude())) {
				// 起始经度
				String starLongitude = detail.getStarLongitude();
				// 终止经度
				String endLongitude = detail.getEndLongitude();
				// 起始纬度
				String starLatitude = detail.getStarLatitude();
				// 终止纬度
				String endLatitude = detail.getEndLatitude();

				// 经度
				query.setParameter(4, starLongitude);
				query.setParameter(5, endLongitude);
				query.setParameter(6, starLongitude);
				query.setParameter(7, starLongitude);
				query.setParameter(8, endLongitude);
				query.setParameter(9, endLongitude);
				// 纬度
				query.setParameter(10, starLatitude);
				query.setParameter(11, endLatitude);
				query.setParameter(12, starLatitude);
				query.setParameter(13, starLatitude);
				query.setParameter(14, starLatitude);
				query.setParameter(15, endLongitude);
				// 经度
				query.setParameter(16, starLongitude);
				query.setParameter(17, endLongitude);
				query.setParameter(18, starLongitude);
				query.setParameter(19, starLongitude);
				query.setParameter(20, endLongitude);
				query.setParameter(21, endLongitude);
				// 纬度
				query.setParameter(22, starLatitude);
				query.setParameter(23, endLatitude);
				query.setParameter(24, starLatitude);
				query.setParameter(25, starLatitude);
				query.setParameter(26, starLatitude);
				query.setParameter(27, endLongitude);
				// 经度
				query.setParameter(28, starLongitude);
				query.setParameter(29, endLongitude);
				query.setParameter(30, starLongitude);
				query.setParameter(31, starLongitude);
				query.setParameter(32, endLongitude);
				query.setParameter(33, endLongitude);
				// 纬度
				query.setParameter(34, starLatitude);
				query.setParameter(35, endLatitude);
				query.setParameter(36, starLatitude);
				query.setParameter(37, starLatitude);
				query.setParameter(38, starLatitude);
				query.setParameter(39, endLongitude);
				// 经度
				query.setParameter(40, starLongitude);
				query.setParameter(41, endLongitude);
				query.setParameter(42, starLongitude);
				query.setParameter(43, starLongitude);
				query.setParameter(44, endLongitude);
				query.setParameter(45, endLongitude);
				// 纬度
				query.setParameter(46, starLatitude);
				query.setParameter(47, endLatitude);
				query.setParameter(48, starLatitude);
				query.setParameter(49, starLatitude);
				query.setParameter(50, starLatitude);
				query.setParameter(51, endLongitude);
			}
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return list;
	}

	@Override
	public List<CatalogDetail> getOtherMeDetailListByCatalogTypeId(CatalogDetail detail, String userNo)
			throws Exception {
		String typeId = detail.getType().getId();
		Date star = null;
		Date end = null;
		if (detail != null && detail.getCreationDate() != null) {
			star = detail.getCreationDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(star);
			cal.add(Calendar.YEAR, 1);
			end = cal.getTime();

		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"from CatalogDetail where status != ? and creator = ? and type.id = ? and creationDate between ? and ?");
		if (detail != null && StringUtils.isNotBlank(detail.getStarLongitude())
				&& StringUtils.isNotBlank(detail.getEndLongitude()) && StringUtils.isNotBlank(detail.getStarLatitude())
				&& StringUtils.isNotBlank(detail.getEndLatitude())) {
			// 第一种 数据库的起始经度小于或等于终止经度，起始纬度小于或等于终止纬度
			buffer.append(" and (((starLongitude>=? and endLongitude<=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?))");
			buffer.append(" and ((starLatitude>=? and endLatitude<=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?))");
			buffer.append(" and starLongitude<=endLongitude and starLatitude<=endLatitude)");
			// 第二种 数据库的起始经度大于终止经度，起始纬度大于终止纬度
			buffer.append(" or (((endLongitude>=? and starLongitude<=?)");
			buffer.append(" or ( endLongitude<=? and starLongitude>=?)");
			buffer.append(" or (endLongitude<=? and starLongitude>=?))");
			buffer.append(" and ((endLatitude>=? and starLatitude<=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?))");
			buffer.append(" and starLongitude>endLongitude and starLatitude>endLatitude)");
			// 第三种 数据库的起始经度小于或等于终止经度，起始纬度大于终止纬度
			buffer.append(" or (((starLongitude>=? and endLongitude<=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?)");
			buffer.append(" or (starLongitude<=? and endLongitude>=?))");
			buffer.append(" and ((endLatitude>=? and starLatitude<=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?)");
			buffer.append(" or (endLatitude<=? and starLatitude>=?))");
			buffer.append(" and starLongitude<=endLongitude and starLatitude>endLatitude)");
			// 第四种 数据库起始经度大于终止经度，起始纬度小于或等于终止纬度
			buffer.append(" or (((endLongitude>=? and starLongitude<=?)");
			buffer.append(" or ( endLongitude<=? and starLongitude>=?)");
			buffer.append(" or (endLongitude<=? and starLongitude>=?))");
			buffer.append(" and ((starLatitude>=? and endLatitude<=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?)");
			buffer.append(" or (starLatitude<=? and endLatitude>=?))");
			buffer.append(" and starLongitude>endLongitude and starLatitude<=endLatitude)");
		}
		if (detail != null && StringUtils.isNotBlank(detail.getStatus())) {
			if (detail.getStatus().equals("1")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_WAIT + "%'");
			} else if (detail.getStatus().equals("2")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_PASS + "%'");
			} else if (detail.getStatus().equals("3")) {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_BACK + "%'");
			} else {
				buffer.append(" and status like '%" + BaseDataConstants.CATALOG_STATUS_INIT + "%'");
			}
		}
		List<CatalogDetail> list = null;
		try {
			// 获取目录明细列表
			/* list = this.find(buffer.toString()); */
			Query query = this.getSession().createQuery(buffer.toString());
			query.setParameter(0, "审核通过");
			query.setParameter(1, userNo);
			query.setParameter(2, typeId);
			query.setParameter(3, star);
			query.setParameter(4, end);
			if (detail != null && StringUtils.isNotBlank(detail.getStarLongitude())
					&& StringUtils.isNotBlank(detail.getEndLongitude())
					&& StringUtils.isNotBlank(detail.getStarLatitude())
					&& StringUtils.isNotBlank(detail.getEndLatitude())) {
				// 起始经度
				String starLongitude = detail.getStarLongitude();
				// 终止经度
				String endLongitude = detail.getEndLongitude();
				// 起始纬度
				String starLatitude = detail.getStarLatitude();
				// 终止纬度
				String endLatitude = detail.getEndLatitude();
				// 经度
				query.setParameter(5, starLongitude);
				query.setParameter(6, endLongitude);
				query.setParameter(7, starLongitude);
				query.setParameter(8, starLongitude);
				query.setParameter(9, endLongitude);
				query.setParameter(10, endLongitude);
				// 纬度
				query.setParameter(11, starLatitude);
				query.setParameter(12, endLatitude);
				query.setParameter(13, starLatitude);
				query.setParameter(14, starLatitude);
				query.setParameter(15, starLatitude);
				query.setParameter(16, endLongitude);
				// 经度
				query.setParameter(17, starLongitude);
				query.setParameter(18, endLongitude);
				query.setParameter(19, starLongitude);
				query.setParameter(20, starLongitude);
				query.setParameter(21, endLongitude);
				query.setParameter(22, endLongitude);
				// 纬度
				query.setParameter(23, starLatitude);
				query.setParameter(24, endLatitude);
				query.setParameter(25, starLatitude);
				query.setParameter(26, starLatitude);
				query.setParameter(27, starLatitude);
				query.setParameter(28, endLongitude);
				// 经度
				query.setParameter(29, starLongitude);
				query.setParameter(30, endLongitude);
				query.setParameter(31, starLongitude);
				query.setParameter(32, starLongitude);
				query.setParameter(33, endLongitude);
				query.setParameter(34, endLongitude);
				// 纬度
				query.setParameter(35, starLatitude);
				query.setParameter(36, endLatitude);
				query.setParameter(37, starLatitude);
				query.setParameter(38, starLatitude);
				query.setParameter(39, starLatitude);
				query.setParameter(40, endLongitude);
				// 经度
				query.setParameter(41, starLongitude);
				query.setParameter(42, endLongitude);
				query.setParameter(43, starLongitude);
				query.setParameter(44, starLongitude);
				query.setParameter(45, endLongitude);
				query.setParameter(46, endLongitude);
				// 纬度
				query.setParameter(47, starLatitude);
				query.setParameter(48, endLatitude);
				query.setParameter(49, starLatitude);
				query.setParameter(50, starLatitude);
				query.setParameter(51, starLatitude);
				query.setParameter(52, endLongitude);
			}
			list = query.list();
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
		}
		return list;
	}
}
