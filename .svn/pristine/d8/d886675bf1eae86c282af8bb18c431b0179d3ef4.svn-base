package com.ht.service.impl.datum.type;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ht.common.util.DataConverter;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.common.util.Tree;
import com.ht.common.util.TreeNode;
import com.ht.persistence.dao.inter.datum.datum.DatumFileDao;
import com.ht.persistence.dao.inter.datum.type.DatumCategoryDao;
import com.ht.persistence.model.datum.datum.DatumFile;
import com.ht.persistence.model.datum.type.DatumCategory;
import com.ht.service.inter.datum.type.DatumCategoryService;

/**
 * 资料类别Service实现类
 * @author zyd
 *
 */
public class DatumCategoryServiceImpl implements DatumCategoryService {
    
	/**
	 * 注入资料类别Dao
	 */
	@Resource(name = "datumcategoryDao") 
	private DatumCategoryDao datumcategoryDao;
	
	/**
	 * 注入DatumFileDao
	 */
	@Resource (name = "DatumFileDao")
	private DatumFileDao datumFileDao;
	
	/**
	 * 添加资料类别
	 * @param datumCategoryParam 资料类别数据
	 */
	@Override
	public void addDatumCategory(String datumCategoryParam) throws Exception {
		try {
			// 将JSON转成对象
			DatumCategory datumcategory = (DatumCategory) DataConverter.convertJson2Object(datumCategoryParam, DatumCategory.class);
			// 如果Id不为空进行修改操作
			if(datumcategory.getId() != null) {
				datumcategory.setCreator("");
				datumcategory.setCreationDate(new Date());
				datumcategory.setModifier("");
				datumcategory.setModifyDate(new Date());
				datumcategoryDao.modifyDatumCategory(datumcategory);
			}else { // 如果Id为空，进行增加操作
				String id = GenerateSequenceUtil.generateSequenceNo();
				datumcategory.setId(id);
				if(datumcategory.getParentId().equals("--请选择--")){
					datumcategory.setParentId("201610301150");
				}
				datumcategory.setCreator("");
				datumcategory.setCreationDate(new Date());
				datumcategory.setModifier("");
				datumcategory.setModifyDate(new Date());
				
				DatumFile df = new DatumFile();
				df.setId(id);
				df.setCategoryId("10271749038240008");
				df.setFileType("目录");
				df.setFileName(datumcategory.getCategoryName());
				df.setCreator("");
				df.setCreationDate(new Date());
				df.setModifier("");
				df.setModifyDate(new Date());
				
				datumcategoryDao.addDatumCategory(datumcategory);
				datumFileDao.addDatumFile(df);
			}
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	/**
	 * 更新资料类别
	 * @param datumCategoryParam 资料类别数据
	 */
	@Override
	public void modifyDatumCategory(String datumCategoryParam) throws Exception {
		try {
			// 将JSON转成对象
			DatumCategory datumcategory = (DatumCategory) DataConverter.convertJson2Object(datumCategoryParam, DatumCategory.class);
			datumcategoryDao.modifyDatumCategory(datumcategory);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 删除资料类别
	 * @param id 资料类别Id
	 */
	@Override
	public void delDatumCategory(String datumCategoryId) throws Exception {
		try {
			DatumCategory datumcategory = new DatumCategory();
			datumcategory.setId(datumCategoryId);
			datumcategoryDao.delDatumCategory(datumcategory);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询所有资料类别
	 */
	@Override
	public List<DatumCategory> getDatumCategory() throws Exception {
		try {
			// 获取所有资料类别
			return datumcategoryDao.getDatumCategory();
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 查询一条资料类别
	 * @param id 资料类别Id
	 */
	@Override
	public DatumCategory getDatumCategory(String id) throws Exception {
		try {
			DatumCategory datumcategory = new DatumCategory();
			datumcategory.setId(id);
			// 根据Id获取资料类别
			return datumcategoryDao.getDatumCategory(datumcategory);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 导出下载
	 * @param String sheetName sheet名称
	 * @param String[] title 第一行标题
	 * @param String url 下载后存放的位置
	 * @param String name excel的名称
	 * @return
	 */
	@Override
	public void exportExcel(String sheetName, String[] title, String url,
			String name, List<DatumCategory> datumCategoryList) {
		// 创建excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个工作表
		XSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置单元格宽度
		sheet.setColumnWidth((short) 0, (short) 9000);
		sheet.setColumnWidth((short) 1, (short) 9000);
		sheet.setColumnWidth((short) 2, (short) 9000);
		// 给一个工作表名称一个长度
		sheet.setDefaultColumnWidth(15);
		// 生成一个样式
		XSSFCellStyle style = workbook.createCellStyle();
		// 设置单元格边框样式
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
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
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		//创建记录
		int size = datumCategoryList.size();
		if (size > 0) {
			for (int i = 1; i <= size; i++) {
				XSSFRow xssfRow = sheet.createRow(i);
				XSSFCell xssfCell = null;
				DatumCategory s = datumCategoryList.get(i-1);
				DatumCategory datumcategory = new DatumCategory();
				for (int j = 0; j < title.length; j++) {
					if (j == 0) {
						xssfCell = xssfRow.createCell(j);
						xssfCell.setCellValue(s.getId());
					}
					if (j == 1) {
						xssfCell = xssfRow.createCell(j);
						if(s.getCategoryName() != null) {
							xssfCell.setCellValue(s.getCategoryName());
						}
					}
					if (j == 2) {
						xssfCell = xssfRow.createCell(j);
						// 获取上级资料类别Id
						String parentId = s.getParentId();
						if(parentId != null){
							datumcategory.setId(parentId);
							// 根据Id获取资料类别
							DatumCategory parentName = datumcategoryDao.getDatumCategory(datumcategory);
							xssfCell.setCellValue(parentName.getCategoryName());
						}else {
							xssfCell.setCellValue("");
						}
					}
					xssfCell.setCellStyle(style);
				}
			}
		}
		// 创建一个文件
		File file = new File(url + name+".xls");
		try {
			file.createNewFile();
			FileOutputStream stream = new FileOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取DatumCategory对象父节点下的所有子节点，形成树结构
	 * @param DatumCategory DatumCategory对象
	 * @return List<DatumCategory> DatumCategory对象的集合
	 */
	@Override
	public String getDatumCategoryTree()
			throws Exception {
		try {
			List<DatumCategory> result = datumcategoryDao.getDatumCategoryTree();
			Tree tree = new Tree();
			TreeNode root = null;
			for (int i = 0; i < result.size(); i++) {
				TreeNode node = new TreeNode(result.get(i).getId(), result.get(i).getCategoryName(), result.get(i).getParentId());
				if (result.get(i).getParentId() == null) {
					root = node;
				}
				else{
					tree.addNode(node);
				}
			}
			return tree.getTreeJson(tree, root);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(), e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 根据父Id获取部分资料类别
	 */
	@Override
	public List<DatumCategory> getDatumCategoryByParentId(String parentId) {
		try {
			DatumCategory dc = new DatumCategory();
			dc.setParentId(parentId);
			return datumcategoryDao.getDatumCategoryByParentId(dc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
