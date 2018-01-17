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

<%@ include file="/init.jsp" %>

<%
boolean localPublishing = true;

if (liveGroup.isStaged() && liveGroup.isStagedRemotely()) {
	localPublishing = false;
}

String displayStyle = ParamUtil.getString(request, "displayStyle", "descriptive");
String navigation = ParamUtil.getString(request, "navigation", "all");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
String searchContainerId = ParamUtil.getString(request, "searchContainerId");

PortletURL renderURL = liferayPortletResponse.createRenderURL();

renderURL.setParameter("mvcRenderCommandName", "publishLayoutsView");
renderURL.setParameter("tabs1", "processes");
renderURL.setParameter("localPublishing", String.valueOf(localPublishing));
renderURL.setParameter("displayStyle", displayStyle);
renderURL.setParameter("navigation", navigation);
renderURL.setParameter("orderByCol", orderByCol);
renderURL.setParameter("orderByType", orderByType);
renderURL.setParameter("searchContainerId", searchContainerId);

String taskExecutorClassName = localPublishing ? BackgroundTaskExecutorNames.LAYOUT_STAGING_BACKGROUND_TASK_EXECUTOR : BackgroundTaskExecutorNames.LAYOUT_REMOTE_STAGING_BACKGROUND_TASK_EXECUTOR;

OrderByComparator<BackgroundTask> orderByComparator = BackgroundTaskComparatorFactoryUtil.getBackgroundTaskOrderByComparator(orderByCol, orderByType);
%>

<portlet:actionURL name="deleteBackgroundTasks" var="deleteBackgroundTasksURL">
	<portlet:param name="redirect" value="<%= currentURL.toString() %>" />
</portlet:actionURL>

<aui:form action="<%= deleteBackgroundTasksURL %>" method="get" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL.toString() %>" />
	<aui:input name="deleteBackgroundTaskIds" type="hidden" />

	<%@ include file="/error/error_auth_exception.jspf" %>

	<%@ include file="/error/error_remote_export_exception.jspf" %>

	<%@ include file="/error/error_remote_options_exception.jspf" %>

	<liferay-ui:search-container
		emptyResultsMessage="no-publication-processes-were-found"
		id="<%= searchContainerId %>"
		iteratorURL="<%= renderURL %>"
		orderByCol="<%= orderByCol %>"
		orderByComparator="<%= orderByComparator %>"
		orderByType="<%= orderByType %>"
		rowChecker="<%= new EmptyOnClickRowChecker(liferayPortletResponse) %>"
	>
		<liferay-ui:search-container-results>

			<%
			int importProcessesCount = 0;
			List<BackgroundTask> importProcesses = null;

			if (navigation.equals("all")) {
				importProcessesCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(new long[] {groupId, liveGroupId}, taskExecutorClassName);
				importProcesses = BackgroundTaskManagerUtil.getBackgroundTasks(new long[] {groupId, liveGroupId}, taskExecutorClassName, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
			}
			else {
				boolean completed = false;

				if (navigation.equals("completed")) {
					completed = true;
				}

				importProcessesCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(new long[] {groupId, liveGroupId}, taskExecutorClassName, completed);
				importProcesses = BackgroundTaskManagerUtil.getBackgroundTasks(new long[] {groupId, liveGroupId}, taskExecutorClassName, completed, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
			}

			searchContainer.setResults(importProcesses);
			searchContainer.setTotal(importProcessesCount);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.backgroundtask.BackgroundTask"
			keyProperty="backgroundTaskId"
			modelVar="backgroundTask"
		>
			<c:choose>
				<c:when test='<%= displayStyle.equals("descriptive") %>'>
					<liferay-ui:search-container-column-text>
						<liferay-ui:user-portrait
							cssClass="user-icon-lg"
							userId="<%= backgroundTask.getUserId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>
						<liferay-staging:process-title
							backgroundTask="<%= backgroundTask %>"
							listView="<%= false %>"
						/>

						<liferay-staging:process-in-progress
							backgroundTask="<%= backgroundTask %>"
						/>

						<liferay-staging:process-status
							backgroundTaskStatus="<%= backgroundTask.getStatus() %>"
							backgroundTaskStatusLabel="<%= backgroundTask.getStatusLabel() %>"
						/>

						<liferay-staging:process-message-task-details
							backgroundTaskId="<%= backgroundTask.getBackgroundTaskId() %>"
							backgroundTaskStatusMessage="<%= backgroundTask.getStatusMessage() %>"
							linkClass="background-task-status-row"
						/>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= displayStyle.equals("list") %>'>
					<liferay-ui:search-container-column-text
						cssClass="background-task-user-column"
						name="user"
					>
						<liferay-ui:user-display
							displayStyle="3"
							showUserDetails="<%= false %>"
							showUserName="<%= false %>"
							userId="<%= backgroundTask.getUserId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="title"
					>
						<liferay-staging:process-title
							backgroundTask="<%= backgroundTask %>"
							listView="<%= true %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="background-task-status-column"
						name="status"
					>
						<liferay-staging:process-in-progress
							backgroundTask="<%= backgroundTask %>"
						/>

						<liferay-staging:process-status
							backgroundTaskStatus="<%= backgroundTask.getStatus() %>"
							backgroundTaskStatusLabel="<%= backgroundTask.getStatusLabel() %>"
						/>

						<liferay-staging:process-message-task-details
							backgroundTaskId="<%= backgroundTask.getBackgroundTaskId() %>"
							backgroundTaskStatusMessage="<%= backgroundTask.getStatusMessage() %>"
							linkClass="background-task-status-row"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-date
						name="create-date"
						orderable="<%= true %>"
						value="<%= backgroundTask.getCreateDate() %>"
					/>

					<liferay-ui:search-container-column-date
						name="completion-date"
						orderable="<%= true %>"
						value="<%= backgroundTask.getCompletionDate() %>"
					/>
				</c:when>
			</c:choose>

			<liferay-ui:search-container-column-text
				align="right"
			>
				<liferay-staging:process-list-menu
					backgroundTask="<%= backgroundTask %>"
					localPublishing="<%= localPublishing %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" resultRowSplitter="<%= new PublishResultRowSplitter() %>" />
	</liferay-ui:search-container>
</aui:form>

<liferay-staging:incomplete-process-message
	localPublishing="<%= localPublishing %>"
	taskExecutorClassName="<%= taskExecutorClassName %>"
/>

<aui:script>
	function <portlet:namespace />deleteEntries() {
		if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-entries") %>')) {
			var form = AUI.$(document.<portlet:namespace />fm);

			form.attr('method', 'post');
			form.fm('<%= Constants.CMD %>').val('<%= Constants.DELETE %>');
			form.fm('deleteBackgroundTaskIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));

			submitForm(form);
		}
	}
</aui:script>