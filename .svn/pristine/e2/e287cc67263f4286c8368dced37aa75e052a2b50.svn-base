package com.ht.service.impl.statisticalanalysis;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ht.common.util.DataConverter;
import com.ht.common.util.ExcelFileUtil;
import com.ht.common.util.ExportExcel;
import com.ht.common.util.FileUtil;
import com.ht.common.util.GenerateSequenceUtil;
import com.ht.common.util.LogHelper;
import com.ht.persistence.dao.inter.statisticalanalysis.CompilationMonthPlanDao;
import com.ht.persistence.dao.inter.statisticalanalysis.DynamicTableDao;
import com.ht.persistence.model.background.monitor.accesslog.Syslog;
import com.ht.persistence.model.statisticalanalysis.CompilationMonthPlan;
import com.ht.persistence.model.statisticalanalysis.view.CompilationCompleteStatusView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationMonthPlanView;
import com.ht.persistence.model.statisticalanalysis.view.CompilationMonthPlanView;
import com.ht.service.inter.statisticalanalysis.CompilationCompleteStatusService;
import com.ht.service.inter.statisticalanalysis.CompilationMonthPlanService;
import com.ht.service.inter.statisticalanalysis.DynamicTableService;

/**
 * 港口航道图月度计划Service实现类
 * @author zyd
 *
 */
public class CompilationMonthPlanServiceImpl implements CompilationMonthPlanService {
	
	/**
	 * 注入港口航道图月度在编动态Dao
	 */
	@Resource(name="compilationMonthPlanDao")
	CompilationMonthPlanDao compilationMonthPlanDao;

	
	/**
	 * 获取所有港口航道图月度计划
	 */
	@Override
	public List<CompilationMonthPlanView> getCompilationMonthPlan(String start,String end,String year) throws Exception {
		try {
			List<CompilationMonthPlanView> list = new ArrayList<CompilationMonthPlanView>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = null;
			Date date2 = null;
			if(start != null && end != null){
				date1 = sdf.parse(start);
			    date2 = sdf.parse(end);
			    list = compilationMonthPlanDao.getCompilationMonthPlan(date1,date2);
			}else{
				CompilationMonthPlanView c= new CompilationMonthPlanView();
				c.setYear(year);
				list = compilationMonthPlanDao.getCompilationMonthPlanByYear(c);
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
	 * 获取一条月度编绘计划
	 */
	@Override
	public CompilationMonthPlanView getCompilationMonthPlanById(String id) {
		try {
			CompilationMonthPlanView cmp = new CompilationMonthPlanView();
			cmp.setId(id);
			return compilationMonthPlanDao.getCompilationMonthPlanById(cmp);
		}catch (Exception e) {
			// 写错误日志
			LogHelper.ERROR.log(e.getMessage(),e);
			// 抛出异常
			throw e;
		}
	}
	
	/**
	 * 新增一条月度编绘计划
	 * @param c 月度编绘计划
	 * @throws Exception
	 */
	@Override
	public void addCompilationMonthPlan(CompilationMonthPlan c) throws Exception {
		try {
			//执行保存操作
			compilationMonthPlanDao.addCompilationMonthPlan(c);
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
	public void export(List<CompilationMonthPlanView> dynamicTableList,String monthPlans, HttpServletResponse respose) {
		// 获取路径
		String folderPath = FileUtil.ROOT_PATH + "dzy\\export\\" + "dynamicTable";
		// 判断文件夹是否存在，不存在则创建
		if(!FileUtil.exists(folderPath)){
			FileUtil.CreateFolder(folderPath);
		}
		String path = folderPath + "\\" + ("港口航道图——月度编绘计划")+ ".xls";
		// 将Json转换为list
		List<CompilationMonthPlanView> list = (List<CompilationMonthPlanView>) DataConverter.convertJson2List(monthPlans,CompilationMonthPlanView.class);
		// 数组的形式创建表格标题行
		String[] col = {"海区","图号","图名","比例尺(1:)","性质","汇交时间","备注"};
		// 数组的形式创建表格值（对应实体类的字段）
		String[] zd = {"seaArea","mapNo","mapName","scale","property","planExchangeTime","remarks"};

		List<CompilationMonthPlanView> planlist =new ArrayList<CompilationMonthPlanView>();
		for (int i = 0; i < list.size(); i++) {
			for (CompilationMonthPlanView compilationMonthPlanView : dynamicTableList) {
				if(compilationMonthPlanView.getId().equals(list.get(i).getId())){
					planlist.add(compilationMonthPlanView);
				}
			}
		}
		
			ExportExcel<CompilationMonthPlanView> ee = new ExportExcel<CompilationMonthPlanView>();
			boolean exportResult = ee.exportExcel("月度编绘计划", col, zd, planlist,path);
			if(exportResult){
				try {
					ExcelFileUtil.download(path, respose);
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
	}
	

	
	
}
