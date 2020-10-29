<%@ include file="../TagLibs.jsp"%>
<jsp:include page="../Main.jsp"></jsp:include>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/statisticalanalysis/chartCompletion2.js"></script>
<script type="text/javascript" src="${RESOURCE_PATH}/kendo/js/jszip.min.js"></script>
<input type="hidden" value="${isComplete}" id= "isComplete">
<input type="hidden" value="${type}" id= "type">


<style>
#sdup{display:none;};
.upbuttun{ width:700px; color: #8E8282};

</style>