package com.ht.persistence.model.paper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CHART属性信息类
 * @author zqy
 * @date 2016/12/9
 */
@Entity
@Table(name = "PAPER_CHART")
public class PaperChart {
	@Id
	@Column(name = "chartver_chartver_id")
	private String id="";
	@Column(name = "chtcom")
	private String chtcom="";
	@Column(name = "chtnum")
	private String chtnum="";
	@Column(name = "chtype")
	private String chtype="";
	@Column(name = "ctdate")
	private String ctdate="";
	@Column(name = "ctitl1")
	private String ctitl1="";
	@Column(name = "ctitl2")
	private String ctitl2="";
	@Column(name = "eddate")
	private String eddate="";
	@Column(name = "ednumb")
	private String ednumb="";
	@Column(name = "intnum")
	private String intnum="";
	@Column(name = "ltstnm")
	private String ltstnm="";
	@Column(name = "nregn1")
	private String nregn1="";
	@Column(name = "nregn2")
	private String nregn2="";
	@Column(name = "parcon")
	private String parcon="";
	@Column(name = "pubcon")
	private String pubcon="";
	@Column(name = "pubdat")
	private String pubdat="";
	@Column(name = "sarea1")
	private String sarea1="";
	@Column(name = "sarea2")
	private String sarea2="";
	@Column(name = "seanum")
	private String seanum="";
	@Column(name = "usrnam")
	private String usrnam="";
	@Column(name = "retired")
	private String retired="";
	@Column(name = "rejected")
	private String rejected="";
	@Column(name = "product_status")
	private String product_status="";
	@Column(name = "processing_status")
	private String processing_status="";
	@Column(name = "majorver")
	private String majorver="";
	@Column(name = "minorver")
	private String minorver="";
	@Column(name = "version")
	private String version="";
	@Column(name = "CHTLNK")
	private String CHTLNK="";
	@Column(name = "productgroup")
	private String productgroup="";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChtcom() {
		return chtcom;
	}
	public void setChtcom(String chtcom) {
		if(chtcom==null){
			chtcom="";
		}
		this.chtcom = chtcom;
	}
	public String getChtnum() {
		return chtnum;
	}
	public void setChtnum(String chtnum) {
		if(chtnum==null){
			chtnum="";
		}
		this.chtnum = chtnum;
	}
	public String getChtype() {
		return chtype;
	}
	public void setChtype(String chtype) {
		if(chtype==null){
			chtype="";
		}
		this.chtype = chtype;
	}
	public String getCtdate() {
		return ctdate;
	}
	public void setCtdate(String ctdate) {
		if(ctdate==null){
			ctdate="";
		}
		this.ctdate = ctdate;
	}
	public String getCtitl1() {
		return ctitl1;
	}
	public void setCtitl1(String ctitl1) {
		if(ctitl1==null){
			ctitl1="";
		}
		this.ctitl1 = ctitl1;
	}
	public String getCtitl2() {
		return ctitl2;
	}
	public void setCtitl2(String ctitl2) {
		if(ctitl2==null){
			ctitl2="";
		}
		this.ctitl2 = ctitl2;
	}
	public String getEddate() {
		return eddate;
	}
	public void setEddate(String eddate) {
		if(eddate==null){
			eddate="";
		}
		this.eddate = eddate;
	}
	public String getEdnumb() {
		return ednumb;
	}
	public void setEdnumb(String ednumb) {
		if(ednumb==null){
			ednumb="";
		}
		this.ednumb = ednumb;
	}
	public String getIntnum() {
		return intnum;
	}
	public void setIntnum(String intnum) {
		if(intnum==null){
			intnum="";
		}
		this.intnum = intnum;
	}
	public String getLtstnm() {
		return ltstnm;
	}
	public void setLtstnm(String ltstnm) {
		if(ltstnm==null){
			ltstnm="";
		}
		this.ltstnm = ltstnm;
	}
	public String getNregn1() {
		return nregn1;
	}
	public void setNregn1(String nregn1) {
		if(nregn1==null){
			nregn1="";
		}
		this.nregn1 = nregn1;
	}
	public String getNregn2() {
		return nregn2;
	}
	public void setNregn2(String nregn2) {
		if(nregn2==null){
			nregn2="";
		}
		this.nregn2 = nregn2;
	}
	public String getParcon() {
		return parcon;
	}
	public void setParcon(String parcon) {
		if(parcon==null){
			parcon="";
		}
		this.parcon = parcon;
	}
	public String getPubcon() {
		return pubcon;
	}
	public void setPubcon(String pubcon) {
		if(pubcon==null){
			pubcon="";
		}
		this.pubcon = pubcon;
	}
	public String getPubdat() {
		return pubdat;
	}
	public void setPubdat(String pubdat) {
		if(pubdat==null){
			pubdat="";
		}
		this.pubdat = pubdat;
	}
	public String getSarea1() {
		return sarea1;
	}
	public void setSarea1(String sarea1) {
		if(sarea1==null){
			sarea1="";
		}
		this.sarea1 = sarea1;
	}
	public String getSarea2() {
		return sarea2;
	}
	public void setSarea2(String sarea2) {
		if(sarea2==null){
			sarea2="";
		}
		this.sarea2 = sarea2;
	}
	public String getSeanum() {
		return seanum;
	}
	public void setSeanum(String seanum) {
		if(seanum==null){
			seanum="";
		}
		this.seanum = seanum;
	}
	public String getUsrnam() {
		return usrnam;
	}
	public void setUsrnam(String usrnam) {
		if(usrnam==null){
			usrnam="";
		}
		this.usrnam = usrnam;
	}
	public String getRetired() {
		return retired;
	}
	public void setRetired(String retired) {
		if(retired==null){
			retired="";
		}
		this.retired = retired;
	}
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		if(rejected==null){
			rejected="";
		}
		this.rejected = rejected;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		if(product_status==null){
			product_status="";
		}
		this.product_status = product_status;
	}
	public String getProcessing_status() {
		return processing_status;
	}
	public void setProcessing_status(String processing_status) {
		if(processing_status==null){
			processing_status="";
		}
		this.processing_status = processing_status;
	}
	public String getMajorver() {
		return majorver;
	}
	public void setMajorver(String majorver) {
		if(majorver==null){
			majorver="";
		}
		this.majorver = majorver;
	}
	public String getMinorver() {
		return minorver;
	}
	public void setMinorver(String minorver) {
		if(minorver==null){
			minorver="";
		}
		this.minorver = minorver;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		if(version==null){
			version="";
		}
		this.version = version;
	}
	public String getCHTLNK() {
		return CHTLNK;
	}
	public void setCHTLNK(String cHTLNK) {
		if(cHTLNK==null){
			cHTLNK="";
		}
		CHTLNK = cHTLNK;
	}
	public String getProductgroup() {
		return productgroup;
	}
	public void setProductgroup(String productgroup) {
		if(productgroup==null){
			productgroup="";
		}
		this.productgroup = productgroup;
	}
	@Override
	public String toString() {
		return 	"CHTCOM Comments" + chtcom
				+ "\r\n CHTNUM Chart Number"+ chtnum
				+ "\r\n CHTYPE Chart Type  " + chtype
				+ "\r\n CTDATE	Date" + ctdate
				+ "\r\n CTITL1	Chart Title (Language 1) " + ctitl1
				+ "\r\n CTITL2	Chart Title (Language 2)  " + ctitl2
				+ "\r\n EDDATE	Edition Date  "+ eddate 
				+ "\r\n EDNUMB	Edition Number " + ednumb 
				+ "\r\n INTNUM	International Chart Number" + intnum
				+ "\r\n LTSTNM	Latest Notice to Mariners " + ltstnm 
				+ "\r\n NREGN1	Name of Region (Language 1)" + nregn1 
				+ "\r\n  NREGN2	Name of Region (Language 2)"+ nregn2
				+ "\r\n PARCON	Partner Country " + parcon
				+ "\r\n PUBCON	Publication Country	" + pubcon
				+ "\r\n  PUBDAT	Publication Date " + pubdat 
				+ "\r\n SAREA1	Name of Sea Area (Language 1) " + sarea1
				+ "\r\n SAREA2	Name of Sea Area (Language 2)"+ sarea2
				+ "\r\n SEANUM	Sea Area Number	" + seanum
				+ "\r\n USRNAM  User Name " + usrnam
				/*+ "\r\n retired=" + retired + ", rejected=" + rejected
				+ ", product_status=" + product_status + ", processing_status="
				+ processing_status + ", majorver=" + majorver + ", minorver="
				+ minorver + ", version=" + version + ", CHTLNK=" + CHTLNK
				+ ", productgroup=" + productgroup*/;
	}

}
