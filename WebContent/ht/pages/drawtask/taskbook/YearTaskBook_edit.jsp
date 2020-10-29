<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> 
<META HTTP-EQUIV="expires" CONTENT="0"> 
<%@ include file="../../TagLibs.jsp"%>
<jsp:include page="../../Main.jsp"></jsp:include>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/drawtask/taskbook/YearTaskBook_edit.js"></script>
<input type="hidden" id="processInstId" value="${processInstId}">
<input type="hidden" id="revision" value="">
<style>
	.page_footer{
		display:block;
		padding-top:5px;
	    text-align: right;
    	font-size: 15px;
	}
	#taskbookNo{
	float: right;}
	#Enclosure{
		font-size: 15px;
		color: white;
	}
	#Enclosure:hover{
		    background-color: #5cb85c;
 			border-color: #5cb85c;
	}
	#Enclosure:active {
		    background-color: #5cb85c;
 			border-color: #5cb85c;
	}
	#Enclosure:visited{
		    background-color: #5cb85c;
 			border-color: #5cb85c;
	}
	.modal-dialog {
   		margin: 5% auto;
	}
	textarea {
    	resize: none;
	}
	#myModal{
		 overflow-y : hidden;  
	 }
</style>
