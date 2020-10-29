<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" />
<c:set var="RESOURCE_PATH" value="${CONTEXT_PATH}/ht/resource" />
<c:set var="SCRIPTS_PATH" value="${CONTEXT_PATH}/ht/js" />
<c:set var="SCRIPTS_PAGES_PATH" value="${CONTEXT_PATH}/ht/js/pages" />
<c:set var="STYLES_PATH" value="${CONTEXT_PATH}/ht/css" />
<c:set var="STYLES_PAGES_PATH" value="${CONTEXT_PATH}/ht/css/pages" />
<c:set var="WEBAPP_ROOT_URL" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>