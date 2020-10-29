package com.ht.persistence.model.paper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PANEL属性信息类
 * @author zqy
 * @date 2016/12/9
 */
@Entity
@Table(name = "PAPER_PANEL")
public class PaperPanel {
	@Id
	@Column(name = "panelvr_panelver_id")
	private String id="";
	@Column(name = "pannam")
	private String pannam="";
	@Column(name = "pannum")
	private String pannum="";
	@Column(name = "panpos")
	private String panpos="";
	@Column(name = "profile_id")
	private String profile_id="";
	@Column(name = "datum")
	private String datum="";
	@Column(name = "helmert")
	private String helmert="";
	@Column(name = "shiftxy")
	private String shiftxy="";
	@Column(name = "shiftlatlong")
	private String shiftlatlong="";
	@Column(name = "align_lat1")
	private String align_lat1="";
	@Column(name = "align_lon1")
	private String align_lon1="";
	@Column(name = "align_lat2")
	private String align_lat2="";
	@Column(name = "align_lon2")
	private String align_lon2="";
	@Column(name = "origin_x")
	private String origin_x="";
	@Column(name = "origin_y")
	private String origin_y="";
	@Column(name = "boundary")
	private String boundary="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPannam() {
		return pannam;
	}
	public void setPannam(String pannam) {
		if(pannam==null){
			pannam="";
		}
		this.pannam = pannam;
	}
	public String getPannum() {
		return pannum;
	}
	public void setPannum(String pannum) {
		if(pannum==null){
			pannum="";
		}
		this.pannum = pannum;
	}
	public String getPanpos() {
		return panpos;
	}
	public void setPanpos(String panpos) {
		if(panpos==null){
			panpos="";
		}
		this.panpos = panpos;
	}
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		if(profile_id==null){
			profile_id="";
		}
		this.profile_id = profile_id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		if(datum==null){
			datum="";
		}
		this.datum = datum;
	}
	public String getHelmert() {
		return helmert;
	}
	public void setHelmert(String helmert) {
		if(helmert==null){
			helmert="";
		}
		this.helmert = helmert;
	}
	public String getShiftxy() {
		return shiftxy;
	}
	public void setShiftxy(String shiftxy) {
		if(shiftxy==null){
			shiftxy="";
		}
		this.shiftxy = shiftxy;
	}
	public String getShiftlatlong() {
		return shiftlatlong;
	}
	public void setShiftlatlong(String shiftlatlong) {
		if(shiftlatlong==null){
			shiftlatlong="";
		}
		this.shiftlatlong = shiftlatlong;
	}
	public String getAlign_lat1() {
		return align_lat1;
	}
	public void setAlign_lat1(String align_lat1) {
		if(align_lat1==null){
			align_lat1="";
		}
		this.align_lat1 = align_lat1;
	}
	public String getAlign_lon1() {
		return align_lon1;
	}
	public void setAlign_lon1(String align_lon1) {
		if(align_lon1==null){
			align_lon1="";
		}
		this.align_lon1 = align_lon1;
	}
	public String getAlign_lat2() {
		return align_lat2;
	}
	public void setAlign_lat2(String align_lat2) {
		if(align_lat2==null){
			align_lat2="";
		}
		this.align_lat2 = align_lat2;
	}
	public String getAlign_lon2() {
		return align_lon2;
	}
	public void setAlign_lon2(String align_lon2) {
		if(align_lon2==null){
			align_lon2="";
		}
		this.align_lon2 = align_lon2;
	}
	public String getOrigin_x() {
		return origin_x;
	}
	public void setOrigin_x(String origin_x) {
		if(origin_x==null){
			origin_x="";
		}
		this.origin_x = origin_x;
	}
	public String getOrigin_y() {
		return origin_y;
	}
	public void setOrigin_y(String origin_y) {
		if(origin_y==null){
			origin_y="";
		}
		this.origin_y = origin_y;
	}
	public String getBoundary() {
		return boundary;
	}
	public void setBoundary(String boundary) {
		if(boundary==null){
			boundary="";
		}
		this.boundary = boundary;
	}
	@Override
	public String toString() {
		return "PANNAM Panel Name   " + pannam+ " \r\n GRTINT	Graticule Interval (minutes)   \r\n PANNUM     Panel Number"
				+ pannum + " \r\n PANPOS  X,Y Offset (mm)" + panpos
				+ " \r\n PSCALE	Scale    \r\n PRJCTN	 Projection     \r\n PANTYP	Type of Panel  \r\n PANTIT	Panel Title " +
				"\r\n PRONAM	Projection Name  " +
				"\r\n SHFTNS	Shift N/S " +shiftxy+
				"\r\n SHFTEW	Shift E/W " +shiftlatlong+
				"\r\n PARUSD	Parameters Used" +
				"\r\n SRCHDT	Source Horizontal Datum Name	" +
				"\r\n SRCHDP	Source Horizontal Datum Parameters " +
				"\r\n DEPUNT	Depth Units" +
				"\r\n DEPDAT	Depth Datum " +
				"\r\n HGHTUN	Height Units " +
				"\r\n HGHA		Height Datum " +
				"\r\n BDRTYP	Border Type " +
				"\r\n PUSAGE	Usage" +
				"\r\n SYMLIB	Symbol Library Used " +
				"\r\n sdgfc		Sounding Feature Code " +
				"\r\n sdgsz		Sounding Size in Millimetres " +
				"\r\n presid	Presentation Identifier " +
				"\r\n annoid	Annotation Idtifier" +
				"\r\n DATUM		Panel Datum Information " + datum +
				"\r\n PROFILE	Panel Catalog"
				/* + profile_id==null?"":profile_id
				", helmert=" + helmert + ", shiftxy="
				+ shiftxy + ", shiftlatlong=" + shiftlatlong + ", align_lat1="
				+ align_lat1 + ", align_lon1=" + align_lon1 + ", align_lat2="
				+ align_lat2 + ", align_lon2=" + align_lon2 + ", origin_x="
				+ origin_x + ", origin_y=" + origin_y + ", boundary="
				+ boundary*/;
	}
	
}
