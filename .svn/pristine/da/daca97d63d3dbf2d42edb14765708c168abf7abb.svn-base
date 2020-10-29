package com.ht.service.impl.catalog.type;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.service.impl.background.dicdata.constants.BaseDataConstants;
import com.ht.service.inter.catalog.type.CatalogTypeService;

public class CatalogTypeServiceImpl implements CatalogTypeService {

	@Resource
	private BaseDataDao baseDataDao;

	/**
	 * 修改基础数据
	 * @param baseDataParam json对象
	 * @throws Exception
	 */
	@Override
	public void modifyData(String baseDataParam) throws Exception {
		try {
			//将json转换为baseData对象
			BaseData baseData = (BaseData) DataConverter.convertJson2Object(baseDataParam, BaseData.class);
			// 更新baseData
			baseData.setTypeName("目录");
			baseData.setTypeId("10201803306430000");
			if("".equals(baseData.getId())){
				baseData.setId(GenerateSequenceUtil.generateSequenceNo());
				baseDataDao.addBaseData(baseData);
			}else{
				baseDataDao.modifyBaseData(baseData);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 导出目录信息模板
	 * 
	 * @param String sheetName sheet名称
	 * @param String[] title 第一行标题
	 * @param String url 下载后存放的位置
	 * @param String excelName excel的名称
	 * @return
	 * @throws IOException 
	 */
	@Override
	public void export() throws Exception {
		BaseData db = new BaseData();
		db.setTypeId(BaseDataConstants.MULX_TYPE_ID);
		List<BaseData> list = baseDataDao.getBaseDataByTypeId(db); 
	    ExcelUtil<BaseData> util1 = new ExcelUtil<BaseData>(BaseData.class);  
	    util1.getListToExcel(list, "类别管理","类别管理导出");  
	   }
}
