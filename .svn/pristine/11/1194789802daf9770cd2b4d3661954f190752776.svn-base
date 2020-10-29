<%@ include file="../../../TagLibs.jsp"%>
<jsp:include page="../../../Main.jsp"></jsp:include>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/background/authority/role/role_app_edit.js"></script>
<script id="tmplt_tree_checkbox" type="text/x-kendo-template">
			<input type='checkbox'  onclick='check_click(this)'>
			#: appCode #
</script>
<script id="write_select" type="text/x-kendo-template">
			<select id="write"> 
				<option value="1">Yes</option> 
				<option value="0">No</option>
			</select>
</script>
<script type="text/javascript">
	function check_click(obj){
		common.tree_check_children(obj);
		common.tree_check_parent(obj);
	}
</script>