<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ include file="../TagLibs.jsp"%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>海图编绘生产管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
	<link href="${STYLES_PATH}/new_file.css" rel="stylesheet"></link>
</head>
<body onload="refresh();">
	<script>
		function refresh() {
			if(window.parent.length == "1"){
				window.parent.location.href="/ht/index/login";
			}
		};
	</script>
		<div class="home_background">
			<div class="content">
				<div class="content_box">
					<div class="content_right">
						<div class="content_font">
							<img src="${RESOURCE_PATH}/img/logo.png"/>
						</div>
						<div class="content_button">
							综合生产管理系统
						</div>
					</div>
					<div class="content_input_box">
						<div class="logo_box">
							<!--<div class="logo_text">用户登录</div>-->
							<!--<img src="img/line.png"/>-->
							<div class="foot_new"><img src="${RESOURCE_PATH}/img/line.png"/></div>
						</div>
						<div class="content_input_list">
							<div class="content_input_img"><img src="${RESOURCE_PATH}/img/admin.png"/></div>
							<input type="text" id="userNo" name="userNo" placeholder="用户名" class="content_input"/>
						</div>
						<div class="content_input_list">
							<div class="content_input_img"><img src="${RESOURCE_PATH}/img/password.png"/></div>
							<input type="password" id="pwd"  name ="pwd" placeholder="密码" class="content_input" />
						</div>
						<a><div id="login" class="content_input_button">登&nbsp;&nbsp;&nbsp;&nbsp;录</div></a>
						
							<!-- div style="margin: 50px 0 0 0; color:#666; width: 100%; text-align: center; font-size: 12px;">最后更新时间：2018-08-30</div -->

					</div>
	
				</div>
			</div>
		</div>
	</body>
<script src="${RESOURCE_PATH}/jquery/jquery-2.1.4.min.js"></script>
<script src="${SCRIPTS_PATH}/message/message.js"></script>
<script src="${RESOURCE_PATH}/layer/1.9.3/layer.js"></script>
<script src="${SCRIPTS_PATH}/common/common.js"></script>
<script src="${SCRIPTS_PATH}/common/layer_custom.js"></script>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/index/login.js"></script>
</html>