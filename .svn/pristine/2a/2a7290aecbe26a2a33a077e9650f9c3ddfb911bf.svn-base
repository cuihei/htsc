package com.ht.persistence.model.paper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SHEET属性信息类
 * @author zqy
 * @date 2016/12/9
 */
@Entity
@Table(name = "PAPER_SHEET")
public class PaperSheet {
	@Id
	@Column(name = "sheetver_sheetver_id")
	private String id="";
	@Column(name = "cmmnts")
	private String cmmnts="";
	@Column(name = "layout")
	private String layout="";
	@Column(name = "margnb")
	private String margnb="";
	@Column(name = "margnl")
	private String margnl="";
	@Column(name = "margnr")
	private String margnr="";
	@Column(name = "margnt")
	private String margnt="";
	@Column(name = "pheigh")
	private String pheigh="";
	@Column(name = "ppsize")
	private String ppsize="";
	@Column(name = "pwidth")
	private String pwidth="";
	@Column(name = "sheet1")
	private String sheet1="";
	@Column(name = "sheet2")
	private String sheet2="";
	@Column(name = "tsheet")
	private String tsheet="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCmmnts() {
		return cmmnts;
	}
	public void setCmmnts(String cmmnts) {
		if(cmmnts==null){
			cmmnts="";
		}
		this.cmmnts = cmmnts;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		if(layout==null){
			layout="";
		}
		this.layout = layout;
	}
	public String getMargnb() {
		return margnb;
	}
	public void setMargnb(String margnb) {
		if(margnb==null){
			margnb="";
		}
		this.margnb = margnb;
	}
	public String getMargnl() {
		return margnl;
	}
	public void setMargnl(String margnl) {
		if(margnl==null){
			margnl="";
		}
		this.margnl = margnl;
	}
	public String getMargnr() {
		return margnr;
	}
	public void setMargnr(String margnr) {
		if(margnr==null){
			margnr="";
		}
		this.margnr = margnr;
	}
	public String getMargnt() {
		return margnt;
	}
	public void setMargnt(String margnt) {
		if(margnt==null){
			margnt="";
		}
		this.margnt = margnt;
	}
	public String getPheigh() {
		return pheigh;
	}
	public void setPheigh(String pheigh) {
		if(pheigh==null){
			pheigh="";
		}
		this.pheigh = pheigh;
	}
	public String getPpsize() {
		return ppsize;
	}
	public void setPpsize(String ppsize) {
		if(ppsize==null){
			ppsize="";
		}
		this.ppsize = ppsize;
	}
	public String getPwidth() {
		return pwidth;
	}
	public void setPwidth(String pwidth) {
		if(pwidth==null){
			pwidth="";
		}
		this.pwidth = pwidth;
	}
	public String getSheet1() {
		return sheet1;
	}
	public void setSheet1(String sheet1) {
		if(sheet1==null){
			sheet1="";
		}
		this.sheet1 = sheet1;
	}
	public String getSheet2() {
		return sheet2;
	}
	public void setSheet2(String sheet2) {
		if(sheet2==null){
			sheet2="";
		}
		this.sheet2 = sheet2;
	}
	public String getTsheet() {
		return tsheet;
	}
	public void setTsheet(String tsheet) {
		if(tsheet==null){
			tsheet="";
		}
		this.tsheet = tsheet;
	}
	@Override
	public String toString() {
		return "TSHEET	Type of Sheet	"+ tsheet+
				"\r\nPPSIZE	Paper Size   "+ ppsize+ 
				"\r\nPWIDTH	Paper Width	 " + pwidth+
				"\r\nPHEIGH	Paper Height	"+ pheigh+ 
				"\r\nMARGNL	Margin Left   " + margnl+ 
				"\r\nMARGNR	Margin Right" + margnr+
				"\r\nMARGNT	Margin Top" + margnt+ 
				"\r\nMARGN	Margi Bottom" + margnb+
				"\r\nLAYOUT	Orientation	Landscape"+ layout+ 
				"\r\nSHEET1	Sheet Title (Language 1)" + sheet1+ 
				"\r\nSHEET2	Sheet Title (Language 2)" + sheet2+
				"\r\nCMMNTS	Comments" + cmmnts +
//				", pheigh="+ pheigh +
				"\r\nTSHEET	Type of Sheet	"+ tsheet;
	}
	
}
