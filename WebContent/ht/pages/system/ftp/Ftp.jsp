<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="../../TagLibs.jsp"%>
<jsp:include page="../../_Include.jsp"></jsp:include>
<body>
	<div class='page-header'>
		<div class='pull-left'>上传</div>
	</div>
	<div class='container-fluid'>
	<form method="post" enctype="multipart/form-data" action="../file/uploadftp" class="form form-horizontal" id="importForm">
		<div class='top35'></div>
		<input type="file" name="files" id="fjs" />
		<div class='row row-top'>
			<div class=' col-sm-2 col-md-1 col-xs-1'>
				<button id='submit' class='btn btn-success'>确定</button>
			</div>
		</div>
		</form>
	</div>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/system/ftp/ftp.js"></script>
</body>
</html>