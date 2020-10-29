package com.ht.service.impl.catalog.detail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import com.ht.common.exception.CommonException;
import com.ht.common.util.BuildExcelTemplate;
import com.ht.common.util.CellValueUtil;
import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.dao.inter.catalog.area.CatalogAreaDao;
import com.ht.persistence.dao.inter.catalog.detail.CatalogDetailDao;
import com.ht.persistence.dao.inter.catalog.history.HistoryCatalogDetailDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.persistence.model.catalog.detail.CatalogDetail;
import com.ht.persistence.model.catalog.history.HistoryCatalogDetail;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.catalog.detail.CatalogDetailService;

/**
 * @ClassName: CatalogDetailServiceImpl
 * @Description: 目录业务处理
 * @author penghao
 * @date 2016年10月15日 下午5:47:43
 */
@SuppressWarnings("unchecked")
public class CatalogDetailServiceImpl implements CatalogDetailService
{

	/**
	 * 注入目录Dao
	 */
	@Resource(name = "catalogDetailDao")
	private CatalogDetailDao catalogDetailDao;

	/**
	 * 注入基础数据Dao
	 */
	@Resource(name = "baseDataDao")
	private BaseDataDao baseDataDao;

	/**
	 * 注入目录区域数据Dao
	 */
	@Resource(name = "catalogAreaDao")
	private CatalogAreaDao catalogAreaDao;

	/**
	 * 注入历史目录数据Dao
	 */
	@Resource(name = "historyCatalogDetailDao")
	private HistoryCatalogDetailDao historyDao;
	/* (非 Javadoc) Title: getDetailById Description: 更具Id获取目录
	 * @param id 目录ID
	 * @return CatalogDetail 目录明细实体
	 * @throws Exception
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#getDetailById(java.lang.String) */
	@Override
	public CatalogDetail getDetailById(String id) throws Exception
	{
		CatalogDetail detail = new CatalogDetail();
		detail.setId(id);
		return catalogDetailDao.getDetail(detail);
	}

	/* (非 Javadoc) Title: getDetailById Description: 更具CATEGORY_ID,AREA_ID获取目录
	 * @param id 目录ID
	 * @return CatalogDetail 目录明细实体
	 * @throws Exception
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#getDetailById(java.lang.String) */
	@Override
	public List<CatalogDetail> getDetailByCategoryId(String categoryId,String areaId) throws Exception
	{
		CatalogDetail detail = new CatalogDetail();
		CatalogArea area = new CatalogArea();
		area.setId(areaId);
		detail.setArea(area);
		BaseData bd = new BaseData();
		bd.setId(categoryId);
		detail.setType(bd);
		return catalogDetailDao.getDetailByCategoryId(detail);
	}
	
	/* (非 Javadoc) Title: getDetailList Description: 获取目录明细列表主页面显示用
	 * @return 目录明细列表
	 * @throws Exception
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#getDetailList() */
	@Override
	public List<CatalogDetail> getDetailList(String year,String jsonParam) throws Exception
	{
		try
		{
			// 将目录明细String类型转成CatalogDetail对象
			CatalogDetail detail = (CatalogDetail) DataConverter.convertJson2Object(jsonParam, CatalogDetail.class);
			// 指定一个日期  
			if(StringUtils.isBlank(year)){
				Calendar cal = Calendar.getInstance();
				int yearint = cal.get(Calendar.YEAR);
				year  = String.valueOf(yearint);
			}
			String dateStr = year+"-01-01";
		    Date date = DateUtils.parseDate(dateStr, "yyyy-MM-dd");
			if (detail == null)
			{
				detail = new CatalogDetail();
			}
			detail.setCreationDate(date);
			if (detail != null && StringUtils.isNotBlank(detail.getMapNo()))
			{
				// 根据mapNo获取经纬度
				List<CatalogDetail> list1 = catalogDetailDao.getDetialListByMapNo(detail);
				if (!CollectionUtils.isEmpty(list1))
				{
					String starLongitude = list1.get(0).getStarLongitude();
					String endLongitude = list1.get(0).getEndLongitude();
					String starLatitude = list1.get(0).getStarLatitude();
					String endLatitude = list1.get(0).getEndLatitude();
					if (StringUtils.isBlank(starLongitude) || StringUtils.isBlank(endLongitude) || StringUtils.isBlank(starLatitude)
							|| StringUtils.isBlank(endLatitude)) { 
						throw new Exception("该图号的经纬度有错误或不完整，请改正！"); 
					}
					detail.setStarLongitude(starLongitude);
					detail.setEndLongitude(endLongitude);
					detail.setStarLatitude(starLatitude);
					detail.setEndLatitude(endLatitude);
				}
			}
			// 获取相应的目录明细列表
			List<CatalogDetail> list = catalogDetailDao.getDetailListByCatalogTypeId(detail);
			
			return list;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/* (非 Javadoc) Title: addDetail Description: 保存目录明细数据
	 * @param detailParam
	 * @throws Exception
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#addDetail(java.lang.String) */
	@Override
	public void addDetail(String detailParam, String categoryId) throws Exception
	{
		try
		
		{
			
			// 将目录明细String类型转成CatalogDetail对象
			CatalogDetail detail = (CatalogDetail) DataConverter.convertJson2Object(detailParam, CatalogDetail.class);
		
			if(!detail.getId().equals("")) {
				List<CatalogDetail> list = catalogDetailDao.getDetialListById(detail.getId());
				detail.setCreationDate(list.get(0).getCreationDate());
				detail.setCreator(list.get(0).getCreator());
			}
				
			// 设置目录类型id
			BaseData data = new BaseData();
			data.setId(categoryId);
			detail.setType(data);
			if (StringUtils.isBlank(detail.getId()))
			{
				// 添加之前判重
				if (catalogDetailDao.exists(detail))
				{
					throw new CommonException("该图号已经存在！");
				}
				else
				{
					detail.setId(GenerateSequenceUtil.generateSequenceNo());
					detail.setStatus(BaseDataConstants.CATALOG_STATUS_INIT);
					// 执行添加操作
					catalogDetailDao.addDetail(detail);
				}
			}
			else
			{
				// 更改之前去重(排除自己)
				List<CatalogDetail> list = catalogDetailDao.getDetialListByMapNoAndCatalogId(detail);
				if(list!=null&&list.size()>0){
					for (int i = 0; i < list.size(); i++)
					{
						if (list.get(i).getId().equals(detail.getId()))
						{
							list.remove(i);
						}
					}
				}
				if (list!=null&&list.size() > 0)
				{
					throw new CommonException("该图号已经存在！");
				}
				else
				{
					// 获取操作之前的数据
					CatalogDetail beforeDetail = catalogDetailDao.getDetail(detail);
					if (beforeDetail != null) {
						String status = beforeDetail.getStatus();
						if ("审核通过".equals(status)) {
							// 更改之前保存目录到历史表
							// 组织数据
							HistoryCatalogDetail history = new HistoryCatalogDetail();
							history.setMapNo(beforeDetail.getMapNo() == null ? null : beforeDetail.getMapNo());
							String catagoryId = null;
							if (beforeDetail.getType() != null && StringUtils.isNotEmpty(beforeDetail.getType().getId()))
							{
								catagoryId = beforeDetail.getType().getId();
							}
							String areaId = null;
							if (beforeDetail.getArea() != null && StringUtils.isNotEmpty(beforeDetail.getArea().getId()))
							{
								areaId = beforeDetail.getArea().getId();
							}
							history.setCategoryId(catagoryId);
							history.setAreaId(areaId);
							history.setMapName(beforeDetail.getMapName() == null ? null : beforeDetail.getMapName());
							history.setScale(beforeDetail.getScale() == null ? null : beforeDetail.getScale());
							history.setStarLongitude(beforeDetail.getStarLongitude() == null ? null : beforeDetail.getStarLongitude());
							history.setEndLongitude(beforeDetail.getEndLongitude() == null ? null : beforeDetail.getEndLongitude());
							history.setStarLatitude(beforeDetail.getStarLatitude() == null ? null : beforeDetail.getStarLatitude());
							history.setEndLatitude(beforeDetail.getEndLatitude() == null ? null : beforeDetail.getEndLatitude());
							history.setNature(beforeDetail.getNature() == null ? null : beforeDetail.getNature());
							history.setMeasurementPeriod(beforeDetail.getMeasurementPeriod() == null ? null : beforeDetail.getMeasurementPeriod());
							history.setTestIng(beforeDetail.getTestIng() == null ? null : beforeDetail.getTestIng());
							history.setPublicationYear(beforeDetail.getPublicationYear() == null ? null : beforeDetail.getPublicationYear());
							history.setDatumLatitude(beforeDetail.getDatumLatitude() == null ? null : beforeDetail.getDatumLatitude());
							history.setMapProportion(beforeDetail.getMapProportion() == null ? null : beforeDetail.getMapProportion());
							history.setAdjustmentProperty(beforeDetail.getAdjustmentProperty() == null ? null : beforeDetail.getAdjustmentProperty());
							history.setPublicationDate(beforeDetail.getPublicationDate() == null ? null : beforeDetail.getPublicationDate());
							history.setPrintNum(beforeDetail.getPrintNum() == null ? null : beforeDetail.getPrintNum());
							history.setRemarks(beforeDetail.getRemarks() == null ? null : beforeDetail.getRemarks());
							history.setId(GenerateSequenceUtil.generateSequenceNo());
							history.setDetailId(detail.getId());
							// 调用历史目录的保存接口保存为历史数据
							historyDao.addHistoryCatalogDetail(history);
						}
					}
				}
				if ("待审核".equals(detail.getStatus())) {
					throw new CommonException("该目录正在审核中，不能编辑！");
				}
				detail.setStatus("创建");
				// 执行保存操作
				catalogDetailDao.modifyDetail(detail);
			}
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
			throw e;
		}
	}

	/* (非 Javadoc) Title: delDetail Description: 删除目录明细
	 * @param id
	 * @throws Exception
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#delDetail(java.lang.String) */
	@Override
	public void delDetail(String ids) throws Exception
	{
		// 将用户String类型转成Detail对象
		List<CatalogDetail> list = (List<CatalogDetail>) DataConverter.convertJson2List(ids, CatalogDetail.class);
		/*for (int i = 0; i < list.size(); i++)
		{
			CatalogDetail detail = catalogDetailDao.getDetail(list.get(i));
			if ("待审核".equals(detail.getStatus())) {
				throw new CommonException("所选目录正在审核中，不能删除！");
			}
		}*/
		for (int i = 0; i < list.size(); i++)
		{
			CatalogDetail beforeDetail = catalogDetailDao.getDetail(list.get(i));
			if (beforeDetail != null) {
				String status = beforeDetail.getStatus();
				if ("审核通过".equals(status)) {
					// 更改之前保存目录到历史表
					// 组织数据
					HistoryCatalogDetail history = new HistoryCatalogDetail();
					history.setMapNo(beforeDetail.getMapNo() == null ? null : beforeDetail.getMapNo());
					String catagoryId = null;
					if (beforeDetail.getType() != null && StringUtils.isNotEmpty(beforeDetail.getType().getId()))
					{
						catagoryId = beforeDetail.getType().getId();
					}
					String areaId = null;
					if (beforeDetail.getArea() != null && StringUtils.isNotEmpty(beforeDetail.getArea().getId()))
					{
						areaId = beforeDetail.getArea().getId();
					}
					history.setCategoryId(catagoryId);
					history.setAreaId(areaId);
					history.setMapName(beforeDetail.getMapName() == null ? null : beforeDetail.getMapName());
					history.setScale(beforeDetail.getScale() == null ? null : beforeDetail.getScale());
					history.setStarLongitude(beforeDetail.getStarLongitude() == null ? null : beforeDetail.getStarLongitude());
					history.setEndLongitude(beforeDetail.getEndLongitude() == null ? null : beforeDetail.getEndLongitude());
					history.setStarLatitude(beforeDetail.getStarLatitude() == null ? null : beforeDetail.getStarLatitude());
					history.setEndLatitude(beforeDetail.getEndLatitude() == null ? null : beforeDetail.getEndLatitude());
					history.setNature(beforeDetail.getNature() == null ? null : beforeDetail.getNature());
					history.setMeasurementPeriod(beforeDetail.getMeasurementPeriod() == null ? null : beforeDetail.getMeasurementPeriod());
					history.setTestIng(beforeDetail.getTestIng() == null ? null : beforeDetail.getTestIng());
					history.setPublicationYear(beforeDetail.getPublicationYear() == null ? null : beforeDetail.getPublicationYear());
					history.setDatumLatitude(beforeDetail.getDatumLatitude() == null ? null : beforeDetail.getDatumLatitude());
					history.setMapProportion(beforeDetail.getMapProportion() == null ? null : beforeDetail.getMapProportion());
					history.setAdjustmentProperty(beforeDetail.getAdjustmentProperty() == null ? null : beforeDetail.getAdjustmentProperty());
					history.setPublicationDate(beforeDetail.getPublicationDate() == null ? null : beforeDetail.getPublicationDate());
					history.setPrintNum(beforeDetail.getPrintNum() == null ? null : beforeDetail.getPrintNum());
					history.setRemarks(beforeDetail.getRemarks() == null ? null : beforeDetail.getRemarks());
					history.setId(GenerateSequenceUtil.generateSequenceNo());
					history.setDetailId(list.get(i).getId());
					// 调用历史目录的保存接口保存为历史数据
					historyDao.addHistoryCatalogDetail(history);
				}
			}
			// 删除对象
			catalogDetailDao.delDetail(beforeDetail);
		}
	}

	/* (非 Javadoc) Title: addDetailByExcel Description: 导入目录数据
	 * @param upload
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#addDetailByExcel(java.io.File) */
	@Override
	public String addDetailByExcel(File file, String categoryId, String type) throws Exception
	{
		StringBuffer failMsg = new StringBuffer();
		try
		{
			// 创建文件流
			FileInputStream is = new FileInputStream(file);
			// 加载文件流
			XSSFWorkbook wbs = new XSSFWorkbook(is);
			// 读取第一个Sheet
			XSSFSheet firstSheet = wbs.getSheetAt(0);
			// 遍历行
			Map<String, CatalogDetail> map = new HashMap<String, CatalogDetail>();
			Map<String, CatalogArea> areaMap = new HashMap<String, CatalogArea>();
			int failNum = 0;
			int count = 0;
			for (int i = 1; i < firstSheet.getLastRowNum() + 1; i++)
			{
				CatalogDetail detail = new CatalogDetail();
				// 获得行对象
				XSSFRow row = firstSheet.getRow(i);
				if (null != row)
				{
					// 设置目录类型id
					BaseData data = new BaseData();
					data.setId(categoryId);
					detail.setType(data);
					// 获取目录区域名称
					if (row.getCell(2) == null)
					{
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：区域名称不能为空！</p>");
						failNum = failNum + 1;
						continue;
					}
					if (row.getCell(2).getCellType() == XSSFCell.CELL_TYPE_BLANK)
					{
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：区域名称不能为空！</p>");
						failNum = failNum + 1;
						continue;
					}
					// 目录区域名称
					String areaName = CellValueUtil.removePoint(row.getCell(2));
					CatalogArea catalogArea = new CatalogArea();
					catalogArea.setAreaName(areaName);
					catalogArea.setBaseData(data);
					// 获取目录区域的ID
					CatalogArea area = catalogAreaDao.getIdByNameAndCategoryId(catalogArea);
					if (area != null)
					{
						detail.setArea(area);
					}
					else
					{
						// 区域不存在保存区域
						catalogArea.setId(GenerateSequenceUtil.generateSequenceNo());
						String key = areaName + categoryId;
						if (!areaMap.containsKey(key))
						{
							areaMap.put(key, catalogArea);
						}
						// 设置区域id
						detail.setArea(areaMap.get(key));
					}
					if (row.getCell(0) == null)
					{
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：图号不能为空！</p>");
						failNum = failNum + 1;
						continue;
					}
					if (row.getCell(0).getCellType() == XSSFCell.CELL_TYPE_BLANK)
					{
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：图号不能为空！</p>");
						failNum = failNum + 1;
						continue;
					}
					String mapNo = CellValueUtil.removePoint(row.getCell(0));
					detail.setMapNo(mapNo);
					// 与数据库对比去重
					if (catalogDetailDao.exists(detail))
					{
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：该目录下图号" + mapNo + "已经存在！</p>");
						failNum = failNum + 1;
						continue;
					}
					detail.setMapName(CellValueUtil.removePoint(row.getCell(1)));
					detail.setScale(CellValueUtil.removePoint(row.getCell(3)));
					String longitude = CellValueUtil.getCellValue(row.getCell(5));
					String starLong = null;
					String endLong = null;
					if (StringUtils.isNotEmpty(longitude))
					{
						if (longitude.contains("-"))
						{
							String[] longitudes = longitude.split("-");
							starLong = longitudes[0];
							starLong = ConvertUtil.convertLongitudeToPlusMinus(starLong);
							endLong = longitudes[1];
							endLong = ConvertUtil.convertLongitudeToPlusMinus(endLong);
						}else if(longitude.contains("—")){
							String[] longitudes = longitude.split("—");
							starLong = longitudes[0];
							starLong = ConvertUtil.convertLongitudeToPlusMinus(starLong);
							endLong = longitudes[1];
							endLong = ConvertUtil.convertLongitudeToPlusMinus(endLong);
						}else
						{
							starLong = longitude;
							starLong = ConvertUtil.convertLongitudeToPlusMinus(starLong);
						}
					}
					String latitude = CellValueUtil.getCellValue(row.getCell(4));
					String starLati = null;
					String endLati = null;
					if (StringUtils.isNotEmpty(latitude))
					{
						if (latitude.contains("-"))
						{
							String[] latitudes = latitude.split("-");
							starLati = latitudes[0];
							starLati = ConvertUtil.convertLatitudeToPlusMinus(starLati);
							endLati = latitudes[1];
							endLati = ConvertUtil.convertLatitudeToPlusMinus(endLati);
						}else if (latitude.contains("—"))
						{
							String[] latitudes = latitude.split("—");
							starLati = latitudes[0];
							starLati = ConvertUtil.convertLatitudeToPlusMinus(starLati);
							endLati = latitudes[1];
							endLati = ConvertUtil.convertLatitudeToPlusMinus(endLati);
						}
						else
						{
							starLati = latitude;
							starLati = ConvertUtil.convertLatitudeToPlusMinus(starLati);
						}
					}
					if(starLong.equals("error")||endLong.equals("error")){
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：该目录下经度范围解析失败,建议格式：105°00′00″—133°00′00″或105°00′00″E—133°00′00″E</p>");
						failNum = failNum + 1;
						continue;
					}
					if(starLati.equals("error")||endLati.equals("error")){
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：该目录下纬度范围解析失败,建议格式：3°00′00″—41°20′00″或3°00′00″N—41°20′00″N</p>");
						failNum = failNum + 1;
						continue;
						
					}
					detail.setStarLongitude(starLong);
					detail.setEndLongitude(endLong);
					detail.setStarLatitude(starLati);
					detail.setEndLatitude(endLati);
					// 规划目录图多字段
					if (type.contains(BaseDataConstants.MLGL_TYPE_GHMLT))
					{
						detail.setNature(CellValueUtil.removePoint(row.getCell(6)));
						detail.setMeasurementPeriod(CellValueUtil.removePoint(row.getCell(7)));
						detail.setTestIng(CellValueUtil.removePoint(row.getCell(8)));
						detail.setPublicationYear(CellValueUtil.removePoint(row.getCell(9)));
						detail.setDatumLatitude(CellValueUtil.removePoint(row.getCell(10)));
						detail.setMapProportion(CellValueUtil.removePoint(row.getCell(11)));
						detail.setAdjustmentProperty(CellValueUtil.removePoint(row.getCell(12)));
						Date publicationDate = null;
						if (row.getCell(13) != null)
						{
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
							String publication = CellValueUtil.getdate(row.getCell(13),"yyyy-MM");
							if(StringUtils.isNotEmpty(publication)){
								publicationDate = sdf.parse(publication);
							}
						}
						detail.setPublicationDate(publicationDate);
						//detail.setPrintNum(CellValueUtil.removePoint(row.getCell(13)));
						detail.setRemarks(CellValueUtil.removePoint(row.getCell(14)));
					}
					else
					{
						Date publicationDate = null;
						if (row.getCell(6) != null)
						{
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
							String publication = CellValueUtil.getdate(row.getCell(6),"yyyy-MM");
							if(StringUtils.isNotEmpty(publication)){
								publicationDate = sdf.parse(publication);
							}
						}
						detail.setPublicationDate(publicationDate);
						detail.setRemarks(CellValueUtil.removePoint(row.getCell(7)));
					}
					// 与表格数据去重
					String detailKey = mapNo + categoryId;
					if (map.containsKey(detailKey))
					{
						failMsg.append("<p>第" + (i + 1) + "行导入失败，失败原因为：该目录下图号" + mapNo + "已经存在！</p>");
						failNum = failNum + 1;
						continue;
					}
					else
					{
						map.put(detailKey, detail);
					}
					detail.setId(GenerateSequenceUtil.generateSequenceNo());
					detail.setStatus(BaseDataConstants.CATALOG_STATUS_PASS);
//					detail.setStatus(BaseDataConstants.CATALOG_STATUS_INIT);
					catalogDetailDao.addDetail(detail);
				}
				count++;
			}
			for (Entry<String, CatalogArea> area : areaMap.entrySet())
			{
				// 保存区域
				catalogAreaDao.add(area.getValue());
			}
			failMsg.append("<p>共成功导入" + count + "条数据，失败" + failNum + "条！</p>");
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			throw e;
		}
		return failMsg.toString();
	}

	/* (非 Javadoc) Title: export Description: 导出目录明细
	 * @see com.ht.service.inter.catalog.detail.CatalogDetailService#export() */
	@Override
	public InputStream export(String year,String type, String categoryId, String sheetName, String roleId, String userNo) throws Exception
	{
		InputStream stream = null;
		CatalogDetail detail = new CatalogDetail();
		// 第一行标题
		String[] title;
		if (type.contains(BaseDataConstants.MLGL_TYPE_GHMLT))
		{
			title = new String[]
			{ "图号", "图名","海区", "比例尺(1:)","图幅范围（纬度）", "图幅范围（经度）" , "性质", "测量周期", "出版周期", "基准纬度", "图积", "调整性质", "出版日期", "备注" };
		}
		else
		{
			title = new String[]
			{  "图号", "图名","海区", "比例尺(1:)",  "图幅范围（纬度）","图幅范围（经度）", "出版日期", "印次", "备注" };
		}
		//String key = null;
		try
		{
			// 获取出的目录类型id
			BaseData data = new BaseData();
			data.setId(categoryId);
			detail.setType(data);
			// 指定一个日期  
			if(StringUtils.isBlank(year)){
				Calendar cal = Calendar.getInstance();
				int yearint = cal.get(Calendar.YEAR);
				year  = String.valueOf(yearint);
			}
			String dateStr = year+"-01-01";
		    Date date = DateUtils.parseDate(dateStr, "yyyy-MM-dd");
		    detail.setCreationDate(date);
		    List<CatalogDetail> detailList = null;
		    String detailParam = DataConverter.convertObject2Json(detail);
			// 获取数据
		    if ("11031903128270101".equals(roleId)) {
				//获取目录列表
				detailList = getDetailList(year,detailParam);
			}else{
				detailList = getOtherDetailList(year,detailParam,userNo);
			}
			// 创建excel工作簿
			XSSFWorkbook workbook = new XSSFWorkbook();
			// 创建一个工作表
			XSSFSheet sheet = workbook.createSheet(sheetName);
			// 给一个工作表名称一个长度
			sheet.setDefaultColumnWidth(15);
			// 生成一个样式
			XSSFCellStyle style = workbook.createCellStyle();
			// 创建第一行
			XSSFRow row = sheet.createRow(0);
			// 设置样式居中
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			// 创建字体对象
			Font font = workbook.createFont();
			// 字体加粗
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// 添加字体样式
			style.setFont(font);
			// 设置行高
			row.setHeightInPoints(20);
			// 设置第一行标题
			XSSFCell cell = null;
			for (int i = 0; i < title.length; i++)
			{
				cell = row.createCell(i);
				cell.setCellValue(title[i]);
				sheet.setColumnWidth(i, title[i].getBytes().length * 2 * 256);
				cell.setCellStyle(style);
			}
			
			for (int i = 1; i < detailList.size() + 1; i++)
			{
				//key = String.valueOf(i);
				CatalogDetail catalogDetail = detailList.get(i - 1);
				XSSFRow otherrow = sheet.createRow(i);
				// 图号
				XSSFCell cell1 = otherrow.createCell(0);
				cell1.setCellValue(catalogDetail.getMapNo() == null ? "" : catalogDetail.getMapNo());
				// 图名
				cell1 = otherrow.createCell(1);
				cell1.setCellValue(catalogDetail.getMapName() == null ? "" : catalogDetail.getMapName());
				// 目录区域
				cell1 = otherrow.createCell(2);
				if(catalogDetail.getArea()!=null){
					cell1.setCellValue(catalogDetail.getArea().getAreaName() == null ? "" : catalogDetail.getArea().getAreaName());
				}else{
					cell1.setCellValue("");
				}
				// 比例尺
				cell1 = otherrow.createCell(3);
				cell1.setCellValue(catalogDetail.getScale() == null ? "" : catalogDetail.getScale());
				// 纬度
				String latitude = "";
				if (StringUtils.isNotEmpty(catalogDetail.getStarLatitude()))
				{
					latitude = catalogDetail.getStarLatitude();
					latitude = ConvertUtil.convertPlusMinusToLatitude(latitude);
				}
				if (StringUtils.isNotEmpty(catalogDetail.getEndLatitude()))
				{
					latitude += "-" + ConvertUtil.convertPlusMinusToLatitude(catalogDetail.getEndLatitude());
				}
				cell1 = otherrow.createCell(4);
				cell1.setCellValue(latitude);
				
				// 经度
				String longitude = "";
				if (StringUtils.isNotEmpty(catalogDetail.getStarLongitude()))
				{
					longitude = catalogDetail.getStarLongitude();
					longitude = ConvertUtil.convertPlusMinusToLongitude(longitude);
				}
				if (StringUtils.isNotEmpty(catalogDetail.getEndLongitude()))
				{
					longitude += "-" + ConvertUtil.convertPlusMinusToLongitude(catalogDetail.getEndLongitude());
				}
				cell1 = otherrow.createCell(5);
				cell1.setCellValue(longitude);
				// 规划目录图多字段
				if (type.contains(BaseDataConstants.MLGL_TYPE_GHMLT))
				{
					// 性质
					cell1 = otherrow.createCell(6);
					cell1.setCellValue(catalogDetail.getNature() == null ? "" : catalogDetail.getNature());
					// 测量周期
					cell1 = otherrow.createCell(7);
					cell1.setCellValue(catalogDetail.getMeasurementPeriod() == null ? "" : catalogDetail.getMeasurementPeriod());
					cell1.setCellValue(catalogDetail.getTestIng() == null ? "" : catalogDetail.getTestIng());
					// 出版年份
					cell1 = otherrow.createCell(8);
					cell1.setCellValue(catalogDetail.getPublicationYear() == null ? "" : catalogDetail.getPublicationYear());
					// 基准纬度
					cell1 = otherrow.createCell(9);
					cell1.setCellValue(catalogDetail.getDatumLatitude() == null ? "" : catalogDetail.getDatumLatitude());
					// 图积
					cell1 = otherrow.createCell(10);
					cell1.setCellValue(catalogDetail.getMapProportion() == null ? "" : catalogDetail.getMapProportion());
					// 调整性质
					cell1 = otherrow.createCell(11);
					cell1.setCellValue(catalogDetail.getAdjustmentProperty() == null ? "" : catalogDetail.getAdjustmentProperty());
					// 出版日期
					cell1 = otherrow.createCell(12);
					if (catalogDetail.getPublicationDate() == null)
					{
						cell1.setCellValue("");
					}
					else
					{
						cell1.setCellValue(catalogDetail.getPublicationDate());
					}
					XSSFCellStyle cellStyle = workbook.createCellStyle();
					XSSFDataFormat format = workbook.createDataFormat();
					cellStyle.setDataFormat(format.getFormat("yyyy-mm"));
					cell1.setCellStyle(cellStyle);

					// 版次
					/*cell1 = otherrow.createCell(13);
					cell1.setCellValue(catalogDetail.getPrintNum() == null ? "" : catalogDetail.getPrintNum());*/
					// 备注
					cell1 = otherrow.createCell(13);
					cell1.setCellValue(catalogDetail.getRemarks() == null ? "" : catalogDetail.getRemarks());
				}
				else
				{
					// 出版日期
					cell1 = otherrow.createCell(6);
					if (catalogDetail.getPublicationDate() == null)
					{
						cell1.setCellValue("");
					}
					else
					{
						cell1.setCellValue(catalogDetail.getPublicationDate());
					}
					XSSFCellStyle cellStyle = workbook.createCellStyle();
					XSSFDataFormat format = workbook.createDataFormat();
					cellStyle.setDataFormat(format.getFormat("yyyy-mm"));
					cell1.setCellStyle(cellStyle);
					// 印次
					cell1 = otherrow.createCell(7);
					cell1.setCellValue(catalogDetail.getPrintNum() == null ? "" : catalogDetail.getPrintNum());
					// 备注
					cell1 = otherrow.createCell(8);
					cell1.setCellValue(catalogDetail.getRemarks() == null ? "" : catalogDetail.getRemarks());
				}
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			byte[] byteArray = outputStream.toByteArray();
			stream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
			outputStream.close();
		}
		catch (Exception e)
		{
			//System.out.println(key);
			LogHelper.ERROR.log(e.getMessage(), e);
			throw new CommonException("导出目录异常！");
		}
		return stream;

	}

	/**
	 * 下载模板
	 * @throws IOException
	 */
	@Override
	public InputStream getTemplate(String type, String sheetName) throws IOException
	{
		// 第一行标题
		String[] title;
		if (type.contains(BaseDataConstants.MLGL_TYPE_GHMLT))
		{
			title = new String[]
			{ "图号", "图名","海区", "比例尺(1:)","图幅范围（纬度）", "图幅范围（经度）",  "性质", "测量周期", "出版周期", "基准纬度", "图积", "调整性质", "出版日期",  "备注" };
		}
		else
		{
			title = new String[]
			{ "图号", "图名","海区", "比例尺(1:)", "图幅范围（纬度）", "图幅范围（经度）", "出版日期", "备注" };
		}
		InputStream stream = null;
		try
		{
			stream = BuildExcelTemplate.getTemplate(sheetName, title);
		}
		catch (Exception e)
		{
			LogHelper.ERROR.log(e.getMessage());
		}
		return stream;

	}
	
	
	/**
	 *  获取存在图号的目录类型
	 * @throws Exception 
	 */
	@Override
	public List<BaseData> getBaseDataFromDetail() throws Exception
	{
		List<BaseData> list = catalogDetailDao.getBaseDataFromDetail();
		return list;
	}
	
	/**
	 *  根据目录类型获取存在图号的目录区域
	 * @throws Exception 
	 */
	@Override
	public List<CatalogArea> getCatalogAreaListFromDetail(String baseDataId) throws Exception
	{
		BaseData baseData = new BaseData();
		baseData.setId(baseDataId);
		List<CatalogArea> list = catalogDetailDao.getCatalogAreaListFromDetail(baseData);
		return list;
	}
	
	/**
	 *  根据图号获取list
	 * @throws Exception 
	 */
	@Override
	public List<CatalogDetail> getDetialListByMapNo(String mapNo,String baseDataId) throws Exception
	{
		CatalogDetail cd = new CatalogDetail();
		cd.setMapNo(mapNo);
		BaseData bd = new BaseData();
		bd.setId(baseDataId);
		cd.setType(bd);
		List<CatalogDetail> list = catalogDetailDao.getDetialListByMapNo(cd);
		return list;
	}

	@Override
	public List<CatalogDetail> getOtherDetailList(String year, String jsonParam, String userNo) throws Exception {
		try
		{
			List<CatalogDetail> list = new ArrayList<CatalogDetail>();
			// 将目录明细String类型转成CatalogDetail对象
			CatalogDetail detail = (CatalogDetail) DataConverter.convertJson2Object(jsonParam, CatalogDetail.class);
			// 指定一个日期  
			if(StringUtils.isBlank(year)){
				Calendar cal = Calendar.getInstance();
				int yearint = cal.get(Calendar.YEAR);
				year  = String.valueOf(yearint);
			}
			String dateStr = year+"-01-01";
			System.out.println(dateStr);
		    Date date = DateUtils.parseDate(dateStr, "yyyy-MM-dd");
			if (detail == null)
			{
				detail = new CatalogDetail();
			}
			detail.setCreationDate(date);
			if (detail != null && StringUtils.isNotBlank(detail.getMapNo()))
			{
				// 根据mapNo获取经纬度
				List<CatalogDetail> list1 = catalogDetailDao.getDetialListByMapNo(detail);
				if (!CollectionUtils.isEmpty(list1))
				{
					String starLongitude = list1.get(0).getStarLongitude();
					String endLongitude = list1.get(0).getEndLongitude();
					String starLatitude = list1.get(0).getStarLatitude();
					String endLatitude = list1.get(0).getEndLatitude();
					if (StringUtils.isBlank(starLongitude) || StringUtils.isBlank(endLongitude) || StringUtils.isBlank(starLatitude)
							|| StringUtils.isBlank(endLatitude)) { 
						throw new Exception("该图号的经纬度有错误或不完整，请改正！"); 
					}
					detail.setStarLongitude(starLongitude);
					detail.setEndLongitude(endLongitude);
					detail.setStarLatitude(starLatitude);
					detail.setEndLatitude(endLatitude);
					list = catalogDetailDao.getOtherDetailListByCatalogTypeId(detail);
					if(list != null){
						if(list.size()>0){
							Iterator<CatalogDetail> it = list.iterator();
							 while(it.hasNext()){  
								 CatalogDetail tempobj = (CatalogDetail)it.next();  
						            if(!tempobj.getStatus().equals("审核通过")){  
						                //移除当前的对象  
						                it.remove();  
						            }  
						        }  
						}
					}
				}
			}else{
				// 获取相应的目录明细列表
				list = catalogDetailDao.getOtherDetailListByCatalogTypeId(detail);
			}
			
			/*List<CatalogDetail> list2 = catalogDetailDao.getOtherMeDetailListByCatalogTypeId(detail,userNo);
			for(CatalogDetail c : list2){
				list.add(c);
			}*/
			return list;
		}
		catch (Exception e)
		{
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
}
