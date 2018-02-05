<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/process_summary/init.jsp" %>

<%
long backgroundTaskId = GetterUtil.getLong(request.getAttribute("backgroundTaskId"), ParamUtil.getLong(request, "backgroundTaskId"));

BackgroundTask backgroundTask = BackgroundTaskManagerUtil.fetchBackgroundTask(backgroundTaskId);

long exportImportConfigurationId = Long.parseLong(backgroundTask.getTaskContextMap().get("exportImportConfigurationId").toString());

ExportImportConfiguration exportImportConfiguration = ExportImportConfigurationLocalServiceUtil.getExportImportConfiguration(exportImportConfigurationId);

Map<String, Serializable> exportImportConfigurationSettingsMap = exportImportConfiguration.getSettingsMap();

Map<String, Serializable> parameterMap = (Map<String, Serializable>)exportImportConfigurationSettingsMap.get("parameterMap");
%>

<div class="container-fluid-1280">
	<%@ include file="/process_summary/process_summary_pages.jspf" %>

	<%@ include file="/process_summary/process_summary_dates.jspf" %>

	<%@ include file="/process_summary/process_summary_portlets.jspf" %>

	<%@ include file="/process_summary/process_summary_deletions_permissions.jspf" %>
</div>