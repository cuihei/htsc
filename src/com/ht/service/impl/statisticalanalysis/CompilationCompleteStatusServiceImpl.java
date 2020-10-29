package com.ht.service.impl.statisticalanalysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ht.common.util.ConvertUtil;
import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationCompleteStatusDao;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.service.inter.statisticalanalysis.CompilationCompleteStatusService;

/**
 * 港口航道图完成情况Service实现类
 * @author zyd
 *
 */
public class CompilationCompleteStatusServiceImpl implements CompilationCompleteStatusService {
	
	/**
	 * 注入港口航道图完成情况Dao
	 */
	@Resource(name="compilationCompleteStatusDao")
	CompilationCompleteStatusDao compilationCompleteStatusDao;

	
	/**
	 * 获取所有港口航道图完成情况
	 */
	@Override
	public List<CompilationCompleteStatusView> getCompilationCompleteStatus(String start,String end,String year) throws Exception {
		try {
			List<CompilationCompleteStatusView> list = new ArrayList<CompilationCompleteStatusView>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = null;
			Date date2 = null;
			if(start != null && end != null){
				date1 = sdf.parse(start);
			    date2 = sdf.parse(end);
			    Calendar c= Calendar.getInstance();  
			    c.setTime(date2);
			    c.add(Calendar.DAY_OF_MONTH, 1);
			    date2=c.getTime();
			    list = compilationCompleteStatusDao.getCompilationCompleteStatus(date1,date2);
			}else{
				CompilationCompleteStatusView c= new CompilationCompleteStatusView();
				c.setCreateYear(year);
				list = compilationCompleteStatusDao.getCompilationCompleteStatusByYear(c);
			}
			return list;
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}

	
	/**
	 * 根据Id获取一条数据
	 * @param id
	 * @return
	 */
	@Override
	public CompilationCompleteStatusView getCompilationCompleteStatusById(String id) {
		try {
			CompilationCompleteStatusView bookInfo = new CompilationCompleteStatusView();
			bookInfo.setId(id);
			// 根据id获取图书资料
			return compilationCompleteStatusDao.getCompilationCompleteStatusById(bookInfo);
		} catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	
	/**
	 * 导出
	 */
	@Override
	public void export(String completionStatus, HttpServletResponse respose) {
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH + "dzy\\export\\" + "dynamicTable";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath + "\\" + ("港口航道图——完成情况") +".xls";
		// 将Json转换为list
		List<CompilationCompleteStatusView> list = (List<CompilationCompleteStatusView>) DataConverter.convertJson2List(completionStatus,CompilationCompleteStatusView.class);
		// 数组的形式创建表格标题行
		String[] col = {"图号","图名","比例尺(1:)","编绘内容","完成日期（审定）","备注"};
		// 数组的形式创建表格值（对应实体类的字段）
		String[] zd = {"mapNo","mapName","scale","content","completeDate","remarks"};

		List<CompilationCompleteStatusView> ccList = new ArrayList<CompilationCompleteStatusView>();
		for (int i = 0; i < list.size(); i++) {
			CompilationCompleteStatusView dynamicTable = this.getCompilationCompleteStatusById(list.get(i).getId());
			ccList.add(dynamicTable);
		}
		if(ccList.size() > 0){
			ExportExcel ee = new ExportExcel();
			boolean exportResult = ee.exportExcel("完成情况", col, zd, ccList,path);
			if(exportResult){
				try {
					ExcelFileUtil.download(path, respose);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}

}
