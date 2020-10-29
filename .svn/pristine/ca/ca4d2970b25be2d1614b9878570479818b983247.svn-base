package com.ht.front.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ht.front.css.CssClass;
import com.ht.front.css.Prop;
import com.ht.front.model.Base;
import com.ht.front.model.Button;
import com.ht.front.model.ButtonGroup;
import com.ht.front.model.Div;
import com.ht.front.model.I;
import com.ht.front.model.InputGroup;
import com.ht.front.model.InputHidden;
import com.ht.front.model.Select;
import com.ht.front.model.Span;
import com.ht.front.model.TextBox;
import com.ht.persistence.model.base.BaseModel;

/**
 * 前端工具
 * @author 王有为
 * @date 2016/10/13
 */
public class FrontUtil {
	
	/**
	 * 前端工具实例
	 */
	private static FrontUtil util = null;
	
	/**
	 * 获取前端工具实例
	 * @return
	 */
	public static FrontUtil getInstance(){
		if (util == null) {
			util = new FrontUtil();
		}
		return util;
	}
	
	/**
	 * 创建一个页面容器
	 */
	public Base createRoot(){
		CssClass css = new CssClass("container-fluid");
		Div div = Div.getInstance(null,css,null);
		css = new CssClass("top35");
		Div top35 = Div.getInstance(null, css, null);
		div.addChildNode(top35);
		return div;
	}
	
	/**
	 * 创建头部描述
	 * @param headerWords 描述
	 * @return
	 */
	public Base createHeaderBar(String headerWords){
		CssClass css = new CssClass("page-header");
		Div header = Div.getInstance(null,css,null);
		css = new CssClass("pull-left");
		Div left = Div.getInstance(null,css,headerWords);
		header.addChildNode(left);
		return header;
	}
	
	/**
	 * 创建头部描述
	 * @param headerWords 描述
	 * @param parent 父容器
	 * @return
	 */
	public Base createHeaderBar(Base parent,String headerWords){
		CssClass css = new CssClass("page-header");
		Div header = Div.getInstance(null,css,null);
		css = new CssClass("pull-left");
		Div left = Div.getInstance(null,css,headerWords);
		header.addChildNode(left);
		parent.addChildNode(header);
		return header;
	}
	
	/**
	 * 创建行
	 */
	public Base createRow(){
		CssClass css = new CssClass("row row-top");
		Div div = Div.getInstance(null,css,null);
		return div;
	}
	
	/**
	 * 创建行
	 * @parent 父容器
	 */
	public Base createRow(Base parent){
		CssClass css = new CssClass("row");
		Div div = Div.getInstance(null,css,null);
		parent.addChildNode(div);
		return div;
	}
	
	/**
	 * 创建列
	 * @param colMdNum 正常屏幕的占用列
	 * @param colXsNum 大屏幕的占用列
	 * @return 列对象
	 */
	public Base createColumn(String colMdNum,String colXsNum){
		CssClass css = new CssClass("col-md-"+colMdNum+" col-xs-"+colXsNum);
		Div column = Div.getInstance(null,css,null);
		return column;
	}
	
	/**
	 * 在行内创建列
	 * @param row 行
	 * @param colMdNum 正常屏幕的占用列
	 * @param colXsNum 大屏幕的占用列
	 * @return 列对象
	 */
	public Base createColumn(Base row,String colMdNum,String colXsNum){
		CssClass css = new CssClass("col-md-"+colMdNum+" col-xs-"+colXsNum);
		Div column = Div.getInstance(null,css,null);
		row.addChildNode(column);
		return column;
	}
	
	/**
	 * 创建列
	 * @param colSmNum 小屏幕的占用列
	 * @param colMdNum 正常屏幕的占用列
	 * @param colXsNum 大屏幕的占用列
	 * @param offsetNum
	 * @return 列对象
	 */
	public Base createColumn(String colSmNum,String colMdNum,String colXsNum,String offsetNum){
		String cssStr = "";
		if (StringUtils.isNotEmpty(colSmNum)) {
			cssStr += " col-sm-"+colSmNum;
		}
		if (StringUtils.isNotEmpty(colMdNum)) {
			cssStr += " col-md-"+colMdNum;
		}
		if (StringUtils.isNotEmpty(colXsNum)) {
			cssStr += " col-xs-"+colXsNum;
		}
		if (StringUtils.isNotEmpty(offsetNum)) {
			cssStr += " col-xs-offset-"+offsetNum;
		}
		CssClass css = new CssClass(cssStr);
		Div column = Div.getInstance(null,css,null);
		return column;
	}
	
	/**
	 * 在行内创建列
	 * @param row 行
	 * @param colSmNum 小屏幕的占用列
	 * @param colMdNum 正常屏幕的占用列
	 * @param colXsNum 大屏幕的占用列
	 * @param offsetNum
	 * @return 列对象
	 */
	public Base createColumn(Base row,String colSmNum,String colMdNum,String colXsNum,String offsetNum){
		String cssStr = "";
		if (StringUtils.isNotEmpty(colSmNum)) {
			cssStr += " col-sm-"+colSmNum;
		}
		if (StringUtils.isNotEmpty(colMdNum)) {
			cssStr += " col-md-"+colMdNum;
		}
		if (StringUtils.isNotEmpty(colXsNum)) {
			cssStr += " col-xs-"+colXsNum;
		}
		if (StringUtils.isNotEmpty(offsetNum)) {
			cssStr += " col-xs-offset-"+offsetNum;
		}
		CssClass css = new CssClass(cssStr);
		Div column = Div.getInstance(null,css,null);
		row.addChildNode(column);
		return column;
	}
	
	/**
	 * 创建一个Grid的Div
	 * @param gridId Grid的Id
	 * @return Grid
	 */
	public Base createGrid(String gridId){
		CssClass css = new CssClass("bg-grid-container");
		Div grid = Div.getInstance(gridId, css, null);
		return grid;
	}
	
	/**
	 * 创建一个Grid的Div
	 * @param parent 父容器
	 * @param gridId Grid的Id
	 * @return Grid
	 */
	public Base createGrid(Base parent,String gridId){
		CssClass css = new CssClass("bg-grid-container");
		Div grid = Div.getInstance(gridId, css, null);
		parent.addChildNode(grid);
		return grid;
	}
	
	/**
	 * 创建一个行间距
	 * @param 父容器
	 */
	public Base createRowSpace(){
		CssClass css = new CssClass("top");
		Div div = Div.getInstance(null,css,null);
		return div;
	}
	
	/**
	 * 创建一个行间距
	 * @param 父容器
	 */
	public Base createRowSpace(Base parent){
		CssClass css = new CssClass("top");
		Div div = Div.getInstance(null,css,null);
		parent.addChildNode(div);
		return div;
	}
	
	/**
	 * 创建一个确定按钮
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public Base createSubmitButton(){
		CssClass css = new CssClass("btn btn-success");
		Button btn = Button.getInstance("submit", css, "确定");
		return btn;
	}
	
	/**
	 * 创建一个确定按钮
	 * @param parent 父容器
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public Base createSubmitButton(Base parent){
		CssClass css = new CssClass("btn btn-success");
		Button btn = Button.getInstance("submit", css, "确定");
		parent.addChildNode(btn);
		return btn;
	}
	
	/**
	 * 创建一个返回按钮
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public Base createCancelButton(){
		CssClass css = new CssClass("btn btn-default");
		Button btn = Button.getInstance("back", css, "返回");
		return btn;
	}
	
	/**
	 * 创建一个返回按钮
	 * @param parent 父容器
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public Base createCancelButton(Base parent){
		CssClass css = new CssClass("btn btn-default");
		Button btn = Button.getInstance("back", css, "返回");
		parent.addChildNode(btn);
		return btn;
	}
	
	/**
	 * 创建一个返回按钮
	 * @param parent 父容器
	 * @param value 按钮显示值
	 * @return 按钮实例
	 */
	public Base createCancelButtonBk5(Base parent){
		CssClass css = new CssClass("btn btn-default bk-margin-5");
		Button btn = Button.getInstance("back", css, "返回");
		parent.addChildNode(btn);
		return btn;
	}
	
	/**
	 * 增加默认按钮组
	 * @param businessName 业务名称
	 * @return 按钮组对象
	 */
	public Base createDefaultButtonGroup(String businessName){
		ButtonGroup bg = ButtonGroup.getDefaultButtonGroup(businessName);
		return bg;
	}
	
	/**
	 * 增加默认按钮组
	 * @param parent 父容器
	 * @param businessName 业务名称
	 * @return 按钮组对象
	 */
	public Base createDefaultButtonGroup(Base parent,String businessName){
		ButtonGroup bg = ButtonGroup.getDefaultButtonGroup(businessName);
		parent.addChildNode(bg);
		return bg;
	}
	
	/**
	 * 增加提交按钮组
	 * @param parent 父容器
	 * @param businessName 业务名称
	 * @return 按钮组对象
	 */
	public Base createSubmitButtonGroup(Base parent,String businessName){
		ButtonGroup bg = ButtonGroup.getSubmitButtonGroup(businessName);
		parent.addChildNode(bg);
		return bg;
	}
	
	/**
	 * 创建一个默认的选择框组件
	 * @param description 选择框组件的描述文本
	 * @param selectId 选择框的ID
	 * @param listModel 实体对象的集合
	 * @param optionValueKey 选择框项绑定的value的域（实体的字段）
	 * @param optionDisplayKey 选择框项绑定的显示值的域（实体的字段）
	 * @param isBlank 初始化是否有值
	 * @return 选择框组件
	 */
	public Base creatDefaultSelectGroup(String description,String selectId,List<? extends BaseModel> listModel,String optionValueKey,String optionDisplayKey,Boolean isBlank){
		InputGroup selectGroup = InputGroup.getSelectGroup(description, selectId, listModel, optionValueKey, optionDisplayKey,isBlank);
		return selectGroup;
	}
	
	/**
	 * 创建一个默认的选择框组件
	 * @param parent 父容器
	 * @param description 选择框组件的描述文本
	 * @param selectId 选择框的ID
	 * @param listModel 实体对象的集合
	 * @param optionValueKey 选择框项绑定的value的域（实体的字段）
	 * @param optionDisplayKey 选择框项绑定的显示值的域（实体的字段）
	 * @param isBlank 初始化是否有值
	 * @param selectedValue 初始值
	 * @return 选择框组件
	 */
	public Base creatDefaultSelectGroup(String description,String selectId,List<? extends BaseModel> listModel,String optionValueKey,String optionDisplayKey,String selectedValue){
		Base select = InputGroup.getSelectGroup(description, selectId, listModel, optionValueKey, optionDisplayKey,selectedValue);
		return select;
	}
	
	/**
	 * 创建一个默认的选择框组件
	 * @param description 选择框组件的描述文本
	 * @param selectId 选择框的ID
	 * @param listModel 实体对象的集合
	 * @param optionValueKey 选择框项绑定的value的域（实体的字段）
	 * @param optionDisplayKey 选择框项绑定的显示值的域（实体的字段）
	 * @param isBlank 初始化是否有值
	 * @return 选择框组件
	 */
	public Base creatMapSelectGroup(String description,String selectId,List<Map<String,String>> listModel,String optionValueKey,String optionDisplayKey,Boolean isBlank){
		InputGroup selectGroup = InputGroup.getSelectGroupWithDefaultOption(description, selectId, listModel, optionValueKey, optionDisplayKey,isBlank);
		return selectGroup;
	}
	
	public Base createRangeControlGroup(String spanPreText,String controlPreId,String controlPreHorder,String controlNextId,String controlNextHorder){
		spanPreText = spanPreText + "：";
		Span spanPre = Span.getDefault(spanPreText);
		TextBox textPre = TextBox.getDefault(controlPreId, null, controlPreHorder);
		Span spanNext = Span.getDefault("-");
		TextBox textNext = TextBox.getDefault(controlNextId, null, controlNextHorder);
		return InputGroup.getInstance(spanPre,textPre,spanNext,textNext);
	}
	
	public Base createSeachControlGroup(String spanText,String controlId,String controlHorder,String btnId,CssClass btnCss){
		spanText = spanText + "：";
		Span span = Span.getDefault(spanText);
		TextBox textPre = TextBox.getDefault(controlId, null, controlHorder);
		I i = I.getInstance(btnCss);
		Span spans = Span.getSpanWithI(null,i,btnId);
		Prop prop  = new Prop();
		prop.setPropKey("style");
		prop.setPropValue("cursor: pointer;");
		spans.addProp(prop);
		return InputGroup.getInstance(span,textPre,spans,null);
	}
	
	public Base createRangeDatePickerGroup(String spanPreText,String datePickerPreId,String datePickerPreHorder,String datePickerNextId,String datePickerNextHorder){
		spanPreText = spanPreText + "：";
		Span spanPre = Span.getDefault(spanPreText);
		InputGroup igPre =  InputGroup.getDatePicker(datePickerPreId,datePickerPreHorder);
		Span spanNext = Span.getDefault("—");
		InputGroup igNext =  InputGroup.getDatePicker(datePickerNextId,datePickerNextHorder);
		return InputGroup.getInstance(spanPre,igPre,spanNext,igNext);
	}
	public Base createYearMonthDatePickerGroup(String spanPreText,String datePickerPreId,String datePickerPreHorder,String datePickerNextId,String datePickerNextHorder){
		spanPreText = spanPreText + "：";
		Span spanPre = Span.getDefault(spanPreText);
		InputGroup igPre =  InputGroup.getDatePicker(datePickerPreId,datePickerPreHorder,"yyyy-mm");
		Span spanNext = Span.getDefault("—");
		InputGroup igNext =  InputGroup.getDatePicker(datePickerNextId,datePickerNextHorder,"yyyy-mm");
		return InputGroup.getInstance(spanPre,igPre,spanNext,igNext);
	}
	public Base createYearMonthDatePickerGroup(String spanPreText,String datePickerPreId,String textPreValue,String datePickerPreHorder,String datePickerNextId,String textNextValue,String datePickerNextHorder){
		spanPreText = spanPreText + "：";
		Span spanPre = Span.getDefault(spanPreText);
		InputGroup igPre =  InputGroup.getFormatDatePicker(datePickerPreId,textPreValue,datePickerPreHorder,"yyyy-mm-dd");
		Span spanNext = Span.getDefault("—");
		InputGroup igNext =  InputGroup.getFormatDatePicker(datePickerNextId,textNextValue,datePickerNextHorder,"yyyy-mm-dd");
		return InputGroup.getInstance(spanPre,igPre,spanNext,igNext);
	}
	
	/**
	 * 创建一个隐藏域
	 * @param id 隐藏域ID
	 * @param value 隐藏域值
	 * @return 隐藏域
	 */
	public Base createHidden(String id,String value){
		InputHidden hidden = InputHidden.getInstance(id, value);
		return hidden;
	}
	
	/**
	 * 创建一个隐藏域
	 * @param parent 父容器
	 * @param id 隐藏域ID
	 * @param value 隐藏域值
	 * @return 隐藏域
	 */
	public Base createHidden(Base parent,String id,String value){
		InputHidden hidden = InputHidden.getInstance(id, value);
		parent.addChildNode(hidden);
		return hidden;
	}
}
