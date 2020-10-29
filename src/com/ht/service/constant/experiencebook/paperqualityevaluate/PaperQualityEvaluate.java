package com.ht.service.constant.experiencebook.paperqualityevaluate;
import java.util.HashMap;
import java.util.Map;

/**
 * 纸海图质量评定表
 * @author huodesheng
 *
 */
public class PaperQualityEvaluate {
	Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 书签
	 */
	public final String MARKER = "table_evaluate";
	
	//图号
	public final String MAP_NAME="text_evaluate_map_name";
	//期号
	public final String NO_NAME="text_evaluate_no_name";
	//日期
	public final String DATE="text_evaluate_date";
	//序号
	public final String NO="text_evaluate_no";
	//内容
	public final String CONTENT="text_evaluate_content";
	//质量扣分
	public final String ZLKF="text_evaluate_zlkf";
	//处理结果
	public final String CLJG="text_evaluate_cljg";
	//累计扣分
	public final String LJKF="text_evaluate_ljkf";
	//缺陷类别
	public final String QXLB="text_evaluate_deep";
	//缺陷个数
	public final String QXGS="text_evaluate_num";
	//最终得分
	public final String ZZDF="text_evaluate_zzdf";
	
	//作业部门质检员
	public final String ZYZJ="mtext_evaluate_zyzj";
	
	//难度系数
	public final String NDXS="text_evaluate_ndxs";
	
	//质量等级
	public final String ZLDJ="text_evaluate_zldj";
	
	//质量部门质检员
	public final String ZLZJ="mtext_evaluate_zlzj";
	//编绘
	public final String ZLBH="mtext_evaluate_zlbh";
	//科长
	public final String ZLKZ="mtext_evaluate_zlkz";
	//类型
	public final String TYPE="text_evaluate_type";
	
	public void setMAP_NAME(String map_name){
		this.map.put(this.MAP_NAME, map_name);
	}
	public void setTYPE(String type){
		this.map.put(this.TYPE, type);
	}
	public void setDATE(String date){
		this.map.put(this.DATE, date);
	}
	public void setNO(String no){
		this.map.put(this.NO, no);
	}
	public void setCONTENT(String content){
		this.map.put(this.CONTENT, content);
	}
	public void setZLKF(String zlkf){
		this.map.put(this.ZLKF, zlkf);
	}
	public void setCLJG(String cljg){
		this.map.put(this.CLJG, cljg);
	}
	public void setLJKF(String ljkf){
		this.map.put(this.LJKF, ljkf);
	}
	public void setZZDF(String zzdf){
		this.map.put(this.ZZDF, zzdf);
	}
	public void setZYZJ(String zyzj){
		this.map.put(this.ZYZJ, zyzj);
	}
	public void setNDXS(String ndxs){
		this.map.put(this.NDXS, ndxs);
	}
	public void setZLDJ(String zldj){
		this.map.put(this.ZLDJ,zldj);
	}
	public void setQXLB(String qxlb){
		this.map.put(this.QXLB,qxlb);
	}
	public void setQXGS(String qxgs){
		this.map.put(this.QXGS,qxgs);
	}
	public void setZLZJ(String zlzj){
		this.map.put(this.ZLZJ,zlzj);
	}
	public void setZLBH(String zlbh){
		this.map.put(this.ZLBH,zlbh);
	}
	public void setZLKZ(String zlkz){
		this.map.put(this.ZLKZ,zlkz);
	}
	public void setNO_NAME(String no_name){
		this.map.put(this.NO_NAME,no_name);
	}

	public Map<String, Object> getMap(){
		return map;
	}
}
