<%@page import="com.ht.common.util.LoginUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../TagLibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../_Include.jsp"></jsp:include>
<title>首页</title>
</head>
<body  style="overflow-y:hidden">
	<!-- Start: Header -->
	<div class="navbar" role="navigation">
		<div class="container-fluid container-nav">
			<!-- Navbar Action -->
			<div class="nav navbar-nav navbar-actions navbar-left" style="margin-top: 20px">
				<div class="visible-md visible-lg"><a id="main-menu-toggle"><i class="fa fa-th-large"></i></a></div>
			</div>
			<!-- Navbar Right -->
			<div class="navbar-right">
				<!-- Notifications -->
				<ul class="notifications hidden-sm hidden-xs">
					<li>
						<a href="javascript:void(0)" class="dropdown-toggle notification-icon" id="workspace" data-toggle="dropdown" title="个人工作台"> 
							<i class="fa fa-laptop"></i> <span class="badge"></span>
						</a>
					</li>
				</ul>
				<!-- End Notifications -->
				<!-- Userbox -->
				<div class="userbox">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<div class="profile-info">
							<span class="name">${userName}</span> <span class="role">管理员</span>
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
							<li>
								<a href="#"><i class="fa fa-user"></i>更改密码</a>
							</li>
							<li>
								<a href="#"><i class="fa fa-wrench"></i>设置</a>
							</li>
							<li>
								<a href="../index/login"><i class="fa fa-power-off"></i>注销</a>
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
	<div class="container-fluid content">
		<div class="row">
			<!-- Sidebar -->
			<div class="sidebar">
				<div class="sidebar-collapse">
					<!-- Sidebar Header Logo-->
					<div class="sidebar-header">
						<img src="<%=request.getContextPath()%>/ht/resource/images/logo.png"
							class="img-responsive" alt="" />
					</div>
					<!-- Sidebar Menu-->
					<div class="sidebar-menu">
						<nav id="menu" class="nav-main" role="navigation">
						<ul class="nav nav-sidebar">
							<div class="panel-body text-center">
								<div class="bk-avatar">
									<img src="<%=request.getContextPath()%>/ht/resource/images/logo1.png"
										class="img-circle bk-img-60" alt="" />
								</div>
								<div class="bk-padding-top-10">
									<i class="fa fa-circle text-success"></i> <small id="userNo">${userNo}</small>
								</div>
							</div>
							<li class="nav-parent">
								<a> <i class="fa fa-bars" aria-hidden="true"></i><span>目录管理</span></a>
								<ul class="nav nav-children">
									<li>
										<a href="javascript:void(0)" _href="../book/index"><span class="text">图书</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../cta/index"><span class="text">类别管理</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../catalog/area/index"><span class="text">区域管理</span></a>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>明细管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../detail/index?type=1&categoryId=10291633305840008"><span class="text">规划目录图</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../detail/index?type=2&categoryId=10301134291590000"><span class="text">海军目录图</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../detail/index?type=3&categoryId=10301548043560000"><span class="text">港口航道图</span></a>
											</li>
									    </ul>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>历史目录管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../hcd/planIndex?type=1"><span class="text">规划目录图明细</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../hcd/navyIndex?type=2"><span class="text">海军目录图明细 </span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../hcd/portChannelIndex?type=3"><span class="text">港口航道目录图明细</span></a>
											</li>
										</ul>
									</li>
								</ul>
							</li>
							
							<li class="nav-parent">
								<a> <i class="fa fa-copy" aria-hidden="true"></i><span>编绘任务管理</span></a>
								<ul class="nav nav-children">
									<li>
										<a href="javascript:void(0)" _href="../plan/index?type=1&categoryId=10250957411500057"><span class="text">指令性任务</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../plan/index?type=2&categoryId=10250957569190070"><span class="text">月度任务</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../plan/index?type=3&categoryId=10250958275470083"><span class="text">临时性任务</span></a>
									</li>
									<li  class="nav-parent">
										<a href="javascript:void(0)"><span >编绘任务书</span></a>
										<ul class="nav nav-children">
											<li>
												<a href="javascript:void(0)" _href="../taskbook/index?taskBookType=seamap"><span class="text">海图编绘任务书</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../taskbook/index?taskBookType=smallcor"><span class="text">海图小改正任务书</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../taskbook/index?taskBookType=cornotice"><span class="text">改正通告任务书</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../taskbook/index?taskBookType=project"><span class="text">工程&专题图任务书</span></a>
											</li>
										</ul>
									</li>
									<li class="nav-parent"><a><i class="fa fa-copy"  aria-hidden="true"></i><span>编绘任务清单</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../taskbill/init?flag=seamap"><span class="text">海图编绘清单</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../taskbill/init?flag=smallcor"><span class="text">海图小改正清单</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../taskbill/init?flag=cornotice"><span class="text">改正通告清单</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../taskbill/init?flag=project"><span class="text">工程&专题图清单</span></a>
											</li>
										</ul>
									</li>
									<li class="nav-parent"><a><i class="fa fa-copy"  aria-hidden="true"></i><span>编绘任务拆分</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../tasksplit/init?flag=seamap"><span class="text">海图编绘任务拆分</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../tasksplit/init?flag=smallcor"><span class="text">海图小改正任务拆分</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../tasksplit/init?flag=cornotice"><span class="text">改正通告任务拆分</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../tasksplit/init?flag=project"><span class="text">工程&专题图任务拆分</span></a>
											</li>
										</ul>
									</li>
								</ul>
							</li>
							
							<li class="nav-parent">
								<a> <i class="fa fa-book" aria-hidden="true"></i><span>编绘管理</span></a>
								<ul class="nav nav-children">
									<li class="nav-parent">
										<a> <i class="fa fa-book" aria-hidden="true"></i><span>海图编绘</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../source/index"><span class="text">源数据编绘</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../paper/index"><span class="text">纸海图编绘</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../electron/index"><span class="text">电子海图编绘</span></a>
											</li>
										</ul>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-book" aria-hidden="true"></i><span>改正通告编辑</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../soursmallcor/index"><span class="text">源数据小改正编绘</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../correctionnotice/index"><span class="text">改正通告编辑</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../templete/index"><span class="text">改正通告模板编辑</span></a>
											</li>
										</ul>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-book" aria-hidden="true"></i><span>小改正编绘</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../papersmallcor/index"><span class="text">纸海图小改正编绘</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../electronsmallcor/index"><span class="text">电子海图小改正编绘</span></a>
											</li>
										</ul>
									</li><li class="nav-parent">
										<a> <i class="fa fa-book" aria-hidden="true"></i><span>工程&专题图</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../paper/index"><span class="text">工程&专题图纸海图编绘</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../electron/index"><span class="text">工程&专题图电子海图编绘</span></a>
											</li>
										</ul>
									</li>
								</ul>
							</li>


							<li class="nav-parent">
								<a> <i class="fa fa-folder-open-o" aria-hidden="true"></i><span>资料管理</span></a>
								<ul class="nav nav-children">
									<li>
										<a href="javascript:void(0)" _href="../datumCategory/index"><span class="text">资料分类</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../datumFile/index"><span class="text">资料维护</span></a>
									</li>
								</ul>
							</li>
							<li class="nav-parent">
								<a> <i class="fa fa-search" aria-hidden="true"></i><span>改正通告查询</span></a>
								<ul class="nav nav-children">
									<li>
										<a href="javascript:void(0)" _href="../cns/notice"><span class="text">改正通告查询</span></a>
									</li>
								</ul>
							</li>
							<li class="nav-parent">
								<a> <i class="fa fa-bookmark" aria-hidden="true"></i><span>统计分析</span></a>
								<ul class="nav nav-children">
									<li>
										<a href="javascript:void(0)" _href="../statistic/yearPlan"><span class="text">编绘计划（生产管理）</span></a>
									</li>
									  
									<li>
										<a href="javascript:void(0)" _href="../statistic/completionStatus"><span class="text">港口航道图完成清况</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../statistic/dynamicTable"><span class="text">港口航道图月度在编动态</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../statistic/submissionSummary"><span class="text">港口航道图月度汇交</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../statistic/monthlyPlan"><span class="text">港口航道图月度编绘计划</span></a>
									</li>
									
									<!-- 新增表456 -->

                                


									
								</ul>
							</li>
							<li class="nav-parent">
								<a> <i class="fa fa-cog" aria-hidden="true"></i><span>系统管理</span></a>
								<ul class="nav nav-children">
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>组织人员管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../user/index"><span class="text">用户管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../org/index"><span class="text">组织机构管理</span></a>
											</li>
									    </ul>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>系统权限管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../role/index"><span class="text">角色管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../app/index"><span class="text">应用资源管理</span></a>
											</li>
									    </ul>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>系统日志管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../syslog/index"><span class="text">系统访问日志管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../so/index"><span class="text">系统操作日志管理</span></a>
											</li>
									    </ul>
									</li>
									<!-- <li>
										<a href="javascript:void(0)" _href="../forms/FlowChart.jsp"><span class="text">流程管理</span></a>
									</li> -->
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>文档模板管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../mte/index"><span class="text">文档模版类型管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../doctemplete/TempleteList.jsp"><span class="text">文档模版管理</span></a>
											</li>
									    </ul>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../notice/index"><span class="text">通知管理</span></a>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>字典管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../dictionaries/index"><span class="text">字典管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../dictionariesType/index"><span class="text">字典类型管理</span></a>
											</li>
									    </ul>
									</li>
									
									
								</ul>
							</li>
							<li class="nav-parent">
								<a> <i class="fa fa-refresh" aria-hidden="true"></i><span>流程管理</span></a>
								<ul class="nav nav-children">
									<li>
										<a href="javascript:void(0)" _href="../workflow/init"><span class="text">工作流管理</span></a>
									</li>
									<li>
										<a href="javascript:void(0)" _href="../auditrole/index"><span class="text">审批角色管理</span></a>
									</li>
									<li class="nav-parent">
										<a> <i class="fa fa-bars" aria-hidden="true"></i><span>表单管理</span></a>
										<ul class="nav nav-children new-nav">
											<li>
												<a href="javascript:void(0)" _href="../form/index"><span class="text">表单管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../formProp/index"><span class="text">表单属性管理</span></a>
											</li>
											<li>
												<a href="javascript:void(0)" _href="../formValue/index"><span class="text">表单编辑</span></a>
											</li>
									    </ul>
									</li>
								</ul>
							</li>
						</ul>
						</nav>
					</div>
					<!-- End Sidebar Menu-->
				</div>
				<!-- Sidebar Footer-->
				<div class="sidebar-footer"></div>
				<!-- End Sidebar Footer-->
			</div>
			<!-- End Sidebar -->

			<!-- Main Page -->
			<div class="main sidebar-minified">
				<div class="row" id="iframe">
					<div class="col-lg-12 iframe">
						<iframe id="page" class="iframe" src="../workSpace/init"></iframe>
					</div>
				</div>
			</div>
		<!-- End Main Page -->
	</div>
</div>
<!--/container-->
</body>
<script type="text/javascript" src="${SCRIPTS_PAGES_PATH}/index/index.js"></script>
</html>