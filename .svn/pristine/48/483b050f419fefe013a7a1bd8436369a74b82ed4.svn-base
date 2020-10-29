package com.ht.workflow.layout;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.ht.common.constant.EnvironmentConstants;
import com.ht.common.util.ConfigLookupHelper;
import com.ht.common.util.FileUtil;
import com.ht.common.util.LogHelper;
import com.mxgraph.io.mxCodec;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;

public class ProcessImageLayoutUtil {

	public static String saveModelLayoutXml(String processId,BpmnModel model) throws IOException{
		BpmnAutoLayout layout = new BpmnAutoLayout(model);
		layout.execute();
		mxCodec codec = new mxCodec();
		String xml = new mxUtils().getPrettyXml(codec.encode(layout.getGraph().getModel()));
		String root = FileUtil.ROOT_PATH;
		String path = root+"/ht/workflow/xml/"+processId+".xml";
		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(xml);
		try {
			FileUtil.DeletFile(path);
			helper.save(path, EnvironmentConstants.DEFAULT_CHAR_CODEC);
			return "/ht/workflow/xml/"+processId+".xml";
		} catch (Exception e) {
			LogHelper.ERROR.log(e.getMessage(), e);
			return null;
		}
	}
	
	public static void setModelLayout(String processId,BpmnModel model,String xml){
		String root = FileUtil.ROOT_PATH;
		String path = root+"\\ht\\workflow\\xml\\"+processId+".xml";
//		ConfigLookupHelper helper = ConfigLookupHelper.getInstance(xml);
//		try {
//			FileUtil.DeletFile(path);
//			helper.save(path, EnvironmentConstants.DEFAULT_CHAR_CODEC);
//		} catch (Exception e) {
//			LogHelper.ERROR.log(e.getMessage(), e);
//		}
		mxGraph graph = new mxGraph();
		Document doc = null;
		try {
			File file = new File(path);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			doc = docBuilder.parse(file);
		} catch (Exception e) {
			LogHelper.SYSTEM.log(e.getMessage(),e);
		}
		
        // Document doc = parse(helper.getDocument());
		mxCodec coder = new mxCodec(doc);
		coder.decode(doc,graph);
		BpmnAutoLayout layout = new BpmnAutoLayout(model);
		layout.setGraph(graph);
		layout.execute();
	}
	
	/**
	  * 实现dom4j向org.w3c.dom.Document的转换
	  * @param doc
	  * @return
	  * @throws Exception
	  */
	 public static org.w3c.dom.Document parse(org.dom4j.Document doc) throws Exception {
	  if (doc == null) {
	   return (null);
	  }
	  java.io.StringReader reader = new java.io.StringReader(doc.toString());
	  org.xml.sax.InputSource source = new org.xml.sax.InputSource(reader);
	  javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory = javax.xml.parsers.DocumentBuilderFactory
	    .newInstance();
	  javax.xml.parsers.DocumentBuilder documentBuilder = documentBuilderFactory
	    .newDocumentBuilder();
	  return (documentBuilder.parse(source));
	 }
}
