package com.ht.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ht.persistence.dao.impl.background.dicdata.basedata.BaseDataDaoImpl;
import com.ht.persistence.dao.inter.background.dicdata.basedata.BaseDataDao;
import com.ht.persistence.model.background.dicdata.basedata.BaseData;
import com.ht.persistence.model.background.dicdata.defect.Defect;
import com.ht.persistence.model.background.dicdata.defectitem.DefectItem;
import com.ht.persistence.model.background.dicdata.defecttype.DefectType;
 
/**
 * @Description: 读取Excel工具类
 * @Title:ReadExcelUtil
 * @author houchen
 * @date 2017年8月4日 上午11:01:58
 */
public class ReadExcelUtil {

	
//	public static void main(String[] args) throws IOException {
//		ReadExcelUtil util = new ReadExcelUtil();
//		DefectType bean = null;
//        List<DefectType> list = util.readXls();
//         
//        for (int i = 0; i < list.size(); i++) {
//            bean = (DefectType) list.get(i);
//            System.out.println(bean.getCharttype().getValue() + "    " + bean.getTypeName() + "    "
//                    + bean.getId());
//        }
// 
//    }
 
	/*@Override
	public void readXls() throws Exception {
		InputStream is = new FileInputStream("d:\\01_缺陷类别.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        DefectType bean = null;
       // List<DefectType> list = new ArrayList<DefectType>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                bean = new DefectType();
                // 循环列Cell
                // 0海图类型 1缺陷类别
                HSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }
                String name = getValue(xh);
                BaseData b = new BaseData();
                b.setTypeId("11061934251720000");
                b.setValue(name);
                b = baseDataDao.getDataByValue(b);
                bean.setCharttype(b);
                HSSFCell xm = hssfRow.getCell(1);
                if (xm == null) {
                	bean.setTypeName("");
                }else{
                	bean.setTypeName(getValue(xm));
                }
                bean.setId(GenerateSequenceUtil.generateSequenceNo());
                bean.setCreator("admin");
                bean.setCreationDate(new Date());
                bean.setModifier("admin");
                bean.setModifyDate(new Date());
                defectTypeDao.add(bean);
                //list.add(bean);
            }
        }
	}*/
	
	/*@Override
	public void readXls() throws Exception {
		InputStream is = new FileInputStream("d:\\02_缺陷项目.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        DefectItem bean = null;
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                bean = new DefectItem();
                // 循环列Cell
                // 0海图类型 1缺陷类别
                HSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }
                String name = getValue(xh);
                BaseData b = new BaseData();
                b.setTypeId("11061934251720000");
                b.setValue(name);
                b = baseDataDao.getDataByValue(b);
                bean.setCharttype(b);
                
                HSSFCell xm = hssfRow.getCell(1);
                if (xm == null) {
                	bean.setType(null);
                }else{
                	String type = getValue(xm);
                	DefectType d = new DefectType();
                	d.setTypeName(type);
                	d.setCharttype(b);
                	List<DefectType> exsit = defectTypeDao.isExsit(d);
                	DefectType defectType = exsit.get(0);
                	bean.setType(defectType);
                }
                
                HSSFCell item = hssfRow.getCell(2);
                if (item == null) {
                	bean.setItem("");
                }else{
                	bean.setItem(getValue(item));
                }
                bean.setId(GenerateSequenceUtil.generateSequenceNo());
                bean.setCreator("admin");
                bean.setCreationDate(new Date());
                bean.setModifier("admin");
                bean.setModifyDate(new Date());
                defectItemDao.add(bean);
            }
        }
	}*/
	
	/*public void readXls() throws Exception {
		InputStream is = new FileInputStream("d:\\03_缺陷描述.xls");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Defect bean = null;
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }
                bean = new Defect();
                // 循环列Cell
                // 0海图类型 1缺陷类别
                HSSFCell xh = hssfRow.getCell(0);
                if (xh == null) {
                    continue;
                }
                String name = getValue(xh);
                BaseData b = new BaseData();
                b.setTypeId("11061934251720000");
                b.setValue(name);
                b = baseDataDao.getDataByValue(b);
                bean.setCharttype(b);
                
                HSSFCell xm = hssfRow.getCell(1);
                if (xm == null) {
                	bean.setType(null);
                }else{
                	String type = getValue(xm);
                	DefectType d = new DefectType();
                	d.setTypeName(type);
                	d.setCharttype(b);
                	List<DefectType> exsit = defectTypeDao.isExsit(d);
                	DefectType defectType = exsit.get(0);
                	bean.setType(defectType);
                }
                
                HSSFCell it = hssfRow.getCell(2);
                if (it == null) {
                	bean.setItem(null);
                }else{
                	String item = getValue(it);
                	DefectItem defectItem = new DefectItem();
                	defectItem.setCharttype(b);
                	defectItem.setType(bean.getType());
                	defectItem.setItem(item);
                	List<DefectItem> list = defectItemService.isExsit(defectItem);
                	DefectItem dt = list.get(0);
                	bean.setItem(dt);
                }
                
                HSSFCell des = hssfRow.getCell(3);
                if (des == null) {
                	bean.setDiscription("");
                }else{
                	bean.setDiscription(getValue(des));
                }
                
                HSSFCell deep = hssfRow.getCell(4);
                if (deep == null) {
                	bean.setDeep("");
                }else{
                	bean.setDeep(getValue(deep));
                }
                
                HSSFCell score = hssfRow.getCell(5);
                if (des == null) {
                	bean.setScore("");
                }else{
                	bean.setScore(getValue(score));
                }
                bean.setId(GenerateSequenceUtil.generateSequenceNo());
                bean.setCreator("admin");
                bean.setCreationDate(new Date());
                bean.setModifier("admin");
                bean.setModifyDate(new Date());
                defectDao.add(bean);
            }
        }
	}*/
 
    /**
     * 得到Excel表中的值
     * 
     * @param hssfCell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    public String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
