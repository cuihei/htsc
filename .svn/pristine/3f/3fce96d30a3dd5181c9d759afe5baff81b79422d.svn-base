<%@page import="java.util.ArrayList"%>
<%@page import="com.ht.common.util.TreeNode"%>
<%@page import="com.ht.common.util.Tree"%>
<%@page import="com.ht.common.util.LoginUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../TagLibs.jsp"%>
<jsp:include page="../_Include.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body style="overflow: hidden">
	<!-- Start: Header -->
	<div class="navbar" role="navigation">
		<div class="container-fluid container-nav">
			<!-- Navbar Action -->
			<div class="nav navbar-nav navbar-actions navbar-left"
				style="margin-top: 20px">
				<div class="visible-md visible-lg">
					<a id="main-menu-toggle" href="javascript:void(0)"><i class="fa fa-th-large"></i></a>
				</div>
			</div>
			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications hidden-sm hidden-xs">
					<li><a href="javascript:void(0)"
						class="dropdown-toggle notification-icon" id="workspace"
						data-toggle="dropdown" title="个人工作台"> <i class="fa fa-laptop"></i>
							<span class="badge"></span>
					</a></li>
				</ul>
				<!-- End Notifications -->
				<!-- Userbox -->
				<div class="userbox">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<div class="profile-info">
						<input id="password" value="${password}" type="hidden">
							<span class="name">${userName}</span> <span class="role">${roleName}</span>
						</div> <i class="fa custom-caret"></i>
					</a>
					<div class="dropdown-menu">
						<ul class="list-unstyled">
							<li class="dropdown-menu-header bk-bg-white bk-margin-top-15">
								<div class="progress progress-xs  progress-striped active">
									<div class="progress-bar progress-bar-primary"
										role="progressbar" aria-valuenow="60" aria-valuemin="0"
										aria-valuemax="100" style="width: 60%;">60%</div>
								</div>
							</li>
							<li><a id="updatepassword" href="javascript:void(0)" onclick="passwordEdit()"><i
									class="fa fa-user"></i>更改密码</a></li>
<!-- 							<li><a href="#"><i class="fa fa-wrench"></i>设置</a></li> -->
							<li><a href="../index/login"><i class="fa fa-power-off"></i>注销</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- End Userbox -->
			</div>
			<!-- End Navbar Right -->
		</div>
	</div>
	<!-- End: Header -->

	<!-- Start: Content -->
	<!-- Sidebar -->
	<div class="sidebar">
		<div class="sidebar-collapse">
			<!-- Sidebar Header Logo-->
			<div class="sidebar-header">
				<img
					src="<%=request.getContextPath()%>/ht/resource/images/logo.png"
					class="img-responsive" alt="" />
			</div>
			<!-- Sidebar Menu-->
			<div class="sidebar-menu">
				<nav id="menu" class="nav-main" role="navigation">
					<ul class="nav nav-sidebar">
						<div class="panel-body text-center">
							<div class="bk-avatar">
								<img
									src="<%=request.getContextPath()%>/ht/resource/images/logo1.png"
									class="img-circle bk-img-60" alt="" />
							</div>
							<div class="bk-padding-top-10">
								<i class="fa fa-circle text-success"></i> <small id="userNo">${userName}</small>
							</div>
						</div>
						<%
							Tree tree = (Tree) request.getAttribute("navResult");
							if (tree != null) {
								for (TreeNode node : tree.getParentNodes()) {
									if (node != null) {
										// 父id为"10181145576270000",说明为一级菜单
										if (node.getParent().equals("10181145576270000")) {
						%>

						<li class="nav-parent"><a> <span><%=node.getName().substring(0,
						node.getName().lastIndexOf(","))%></span>
						</a>
							<ul class="nav nav-children">

								<%
									// 获取一级菜单下的孩子
													ArrayList<TreeNode> nodeList = tree
															.getNodesByParentId(node.getId());
													if (nodeList != null) {
														for (int j = 0; j < nodeList.size(); j++) {
															if (nodeList.get(j).getParent() != null
																	&& nodeList
																			.get(j)
																			.getName()
																			.substring(
																					nodeList.get(j)
																							.getName()
																							.lastIndexOf(
																									",") + 1)
																			.equals("")) {
								%>
								<li class="nav-parent"><a> <span><%=nodeList
									.get(j)
									.getName()
									.substring(
											0,
											nodeList.get(j)
													.getName()
													.lastIndexOf(
															","))%></span>
								</a>

									<ul class="nav nav-children new-nav">
										<%
											// 获取二级节点下的子节点	
																		ArrayList<TreeNode> nodeListLevel3 = tree
																				.getNodesByParentId(nodeList
																						.get(j).getId());
																		if (nodeListLevel3 != null) {
																			for (int k = 0; k < nodeListLevel3
																					.size(); k++)

																			{
										%>
										<li><a href="javascript:void(0)"
											_href="..<%=nodeListLevel3
											.get(k)
											.getName()
											.substring(
													nodeListLevel3
															.get(k)
															.getName()
															.lastIndexOf(
																	",") + 1)%>">
												<span class="text"><%=nodeListLevel3
											.get(k)
											.getName()
											.substring(
													0,
													nodeListLevel3
															.get(k)
															.getName()
															.lastIndexOf(
																	","))%></span>
										</a></li>
										<%
											}
																		}
										%>
									</ul></li>

								<%
									} else {// 获取二级节点没有子节点的菜单，生成链接
								%>
								<li><a href="javascript:void(0)"
									_href="..<%=nodeList
									.get(j)
									.getName()
									.substring(
											nodeList.get(j)
													.getName()
													.lastIndexOf(
															",") + 1)%>">
										<span class="text"><%=nodeList
									.get(j)
									.getName()
									.substring(
											0,
											nodeList.get(j)
													.getName()
													.lastIndexOf(
															","))%></span>
								</a></li>
								<%
									}
														}
													}
								%>
							</ul></li>
						<%
							}
									}
								}
							}
						%>

					</ul>
				</nav>
			</div>
			<!-- End Sidebar Menu-->
			<!-- End Sidebar -->
		</div>
	</div>
	
	<!-- Main Page -->
	<iframe id="page" class="main iframe" src="../workSpace/init">
	</iframe>
	<!-- End Main Page -->
	<!--/container-->
	
	<!-- Modal Dialog -->
		<div class="modal fade" id="myModal">
			<div class="modal-dialog" style="width:650px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title bk-fg-primary">修改密码</h4>
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-lg-3 col-md-1 col-sm-3 col-xs-3 control-label">登陆名：</label>
										<div class="row">
											<div class="col-lg-3 col-md-6 col-sm-3 col-xs-3">
												<input type="text" id="userName" name="userName"
													   class="form-control" value="${userName}" style="width:300px" readonly>
											</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 col-md-4 col-sm-3 col-xs-3 control-label">原密码：</label>
										<div class="row">
											<div class="col-lg-3 col-md-6 col-sm-3 col-xs-3">
												<input type="password" id="oldPassword" name="oldPassword"
													   class="form-control" placeholder="请输入旧密码" style="width:300px">
											</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 col-md-4 col-sm-3 col-xs-3 control-label">新密码：</label>
										<div class="row">
											<div class="col-lg-3 col-md-6 col-sm-3 col-xs-3">
												<input type="password" id="newPassword" name="newPassword"
													   class="form-control" placeholder="请输入新密码" style="width:300px">
											</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 col-md-4 col-sm-3 col-xs-3 control-label">确认密码：</label>
										<div class="row">
											<div class="col-lg-3 col-md-6 col-sm-3 col-xs-3">
												<input type="password" id="newPassword1" name="newPassword1"
													   class="form-control" placeholder="请确认密码" style="width:300px">
											</div>
									</div>
								</div>
								<div class="form-group" style="color:red;">
									<label class="col-lg-3 col-md-4 col-sm-3 col-xs-3 control-label">温馨提示：</label><span>密码的长度至少有 8 个字符，至少包含大写字母、小写字母、</br>数字、以及特殊字符等</span>
								</div>
								<div class="form-group">
									<label class="col-lg-3 col-md-4 col-sm-3 col-xs-3 control-label">
										<button type="button" class="btn btn-success" onclick="doSubmit()">确定</button>
									</label>
								</div>
							</form>
					</div>
				</div>
			</div>
		</div><!-- End Modal Dialog -->	
</body>
<script type="text/javascript"
	src="${SCRIPTS_PAGES_PATH}/index/index.js"></script>
</html>