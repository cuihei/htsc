package com.ht.service.impl.catalog.area;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

import com.ht.common.export.ExcelUtil;
import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.exception.DBException;
import com.ht.persistence.dao.inter.catalog.area.CatalogAreaDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.catalog.area.CatalogArea;
import com.ht.service.inter.catalog.area.CatalogAreaService;
import com.ht.service.inter.datum.datum.DatumFileService;

public class CatalogAreaServiceImpl implements CatalogAreaService {

	@Resource
	private CatalogAreaDao catalogAreaDao;

	@Resource
	private DatumFileService datumFileService;
	
	/**
	 * 查询
	 * 
	 * @return 区域管理list
	 * @throws Exception
	 */
	@Override
	public List<CatalogArea> getCatalogAreaList() throws Exception {
		try {
			// 获取所有CatalogArea
			return catalogAreaDao.getCatalogAreaList();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	

	/**
	 * 查询
	 * @return 通过id查询区域管理list
	 * @throws Exception
	 */
	@Override
	public CatalogArea getCatalogAreaById(String id) throws Exception {
		try {
			CatalogArea area = new CatalogArea();
			area.setId(id);
			// 获取所有CatalogArea
			return catalogAreaDao.getCatalogAreaById(area);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 删除所选
	 * 
	 * @param ids
	 *            多选id字符串
	 * @throws Exception
	 */
	@Override
	public void del(String catalogArea) throws Exception {
		try {
			@SuppressWarnings("unchecked")
			List<CatalogArea> list = (List<CatalogArea>) DataConverter.convertJson2List(catalogArea,CatalogArea.class);
			for (CatalogArea i:list) {
				// 删除
				catalogAreaDao.del(i);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 校验数据是否存在
	 * 
	 * @param catalogArea实体
	 * @return 存在（true）不存在（false）
	 * @throws Exception
	 */
	public Boolean isExist(CatalogArea catalogArea) throws Exception {
		boolean result;
		try {
			result = catalogAreaDao.isExist(catalogArea);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
		return result;
	}

	/**
	 * 更新所选
	 * 
	 * @param String
	 *            catalogAreaParam字符串
	 * @return 更新结果(成功/已存在)
	 * @throws Exception
	 */
	@Override
	public void modify(String catalogAreaParam,File myfiles,String myfilesFileName) throws Exception {
		try {
			CatalogArea catalogArea = (CatalogArea) DataConverter
					.convertJson2Object(catalogAreaParam, CatalogArea.class);
			//检验数据是否存在，存在返回true
			boolean ifExit = isExist(catalogArea);
			if (ifExit) {
				DBException e = new DBException("数据已经存在！");
				throw e;
			}
			if(!(null == myfiles)){
				  //存入图片格式
				int i = myfilesFileName.lastIndexOf(".");
				String imgType = myfilesFileName.substring(i+1, myfilesFileName.length());
				catalogArea.setImgType(imgType);
				//存入图片
				BASE64Encoder encoder = new BASE64Encoder();
				BufferedImage  bi = ImageIO.read(myfiles);      
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();      
		        ImageIO.write(bi,catalogArea.getImgType(), baos);      
		        byte[] bytes = baos.toByteArray();      
		        String img = encoder.encodeBuffer(bytes).trim();  
			    catalogArea.setAreaImg(img);
			}
			if("".equals(catalogArea.getId())){
				 //新增保存
				catalogArea.setId(GenerateSequenceUtil.generateSequenceNo());
				catalogAreaDao.add(catalogArea);
			}else if(!(null == myfiles) && !"".equals(catalogArea.getId())){
				//修改保存
				catalogAreaDao.modify(catalogArea);
			}else if(null == myfiles && !"".equals(catalogArea.getId())){
				//修改保存
				catalogAreaDao.modifyWithoutImg(catalogArea);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
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
	 */
	@Override
	public void export() throws Exception {
		List<CatalogArea> list = catalogAreaDao.getCatalogAreaList(); 
	    ExcelUtil<CatalogArea> util1 = new ExcelUtil<CatalogArea>(CatalogArea.class);  
	    util1.getListToExcel(list, "目录区域","目录区域导出");  
	   }
	
	/**
	 * 根据目录类型获取目录区域
	 * @param String categoryId 目录类型id
	 * @return 目录区域的列表
	 */
	@Override
	public List<CatalogArea> getCatalogAreaListByCategoryId(String categoryId) {
		BaseData data = new BaseData();
		data.setId(categoryId);
		CatalogArea area = new CatalogArea();
		area.setBaseData(data);
		return catalogAreaDao.getCatalogAreaListByCategoryId(area);
	}
	
	/**
	 * 上传文件
	 */
	@Override
	public String uploadFile(File upload, String uploadFileName) throws IOException {
		// 获取项目在服务器的路径
 		String serverPath = FileUtil.ROOT_PATH;
 		// 新建一个路径，在最后以当前年月日新建一个文件夹
 		String path = "\\upload\\areaImage\\"+ ConvertUtil.convertDateToString("yyyyMMdd",new Date());
 		// 创建文件夹
 		FileUtil.CreateFolder(serverPath+path);
        InputStream is = new FileInputStream(upload);
        OutputStream os = new FileOutputStream(new File(serverPath+path, uploadFileName));
        System.out.println("fileFileName: " + uploadFileName);

        // 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
        System.out.println("file: " + upload.getName());
        System.out.println("file: " + upload.getPath());
        
        byte[] buffer = new byte[500];
        int length = 0;
        
        while(-1 != (length = is.read(buffer, 0, buffer.length)))
        {
            os.write(buffer);
        }
        
        os.close();
        is.close();
        return "\\ht"+path+"\\"+uploadFileName;
	}
}
