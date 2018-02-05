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

<liferay-portlet:renderURL portletName="<%= ExportImportPortletKeys.EXPORT_IMPORT %>" var="processSummaryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="showSummary" />
	<portlet:param name="backgroundTaskId" value="<%= Long.toString(backgroundTask.getBackgroundTaskId()) %>" />
</liferay-portlet:renderURL>

<%
BackgroundTaskDisplay backgroundTaskDisplay = BackgroundTaskDisplayFactoryUtil.getBackgroundTaskDisplay(backgroundTask);

String processSummaryClickFnName = liferayPortletResponse.getNamespace() + "showProcessSummary(" + String.valueOf(backgroundTask.getBackgroundTaskId()) + ", '" + HtmlUtil.escape(backgroundTaskDisplay.getDisplayName(request)) + "', '" + processSummaryURL + "');";
%>

<liferay-ui:icon
	message="summary"
	onClick="<%= processSummaryClickFnName %>"
	url="javascript:;"
/>

<script>
	function <portlet:namespace />showProcessSummary(backgroundTaskId, backgroundTaskName, processSummaryURL) {
		Liferay.Util.openWindow(
			{
				dialog: {
					destroyOnHide: true
				},
				id: '<portlet:namespace />showSummary_' + backgroundTaskId,
				title: backgroundTaskName,
				uri: processSummaryURL + '&<portlet:namespace />backgroundTaskId=' + backgroundTaskId
			}
		);
	}
</script>