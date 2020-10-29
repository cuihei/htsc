<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT"> 
<META HTTP-EQUIV="expires" CONTENT="0"> 
<%@ include file="../../TagLibs.jsp"%>
<jsp:include page="../../Main.jsp"></jsp:include>
<title>文件下载</title>
</head>
<body>
	<script type="text/javascript">
	layer.msg("下载成功");
	setTimeout(function() {
		 window.close();
		}, 1000); // 1000毫秒后开始跳转。
	</script>
</body>
</html>