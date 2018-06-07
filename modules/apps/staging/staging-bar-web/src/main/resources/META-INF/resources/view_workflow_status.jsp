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
LayoutRevision layoutRevision = (LayoutRevision)request.getAttribute(WebKeys.LAYOUT_REVISION);

if ((layoutRevision == null) && (layout != null)) {
	layoutRevision = LayoutStagingUtil.getLayoutRevision(layout);
}

LayoutSetBranch layoutSetBranch = (LayoutSetBranch)request.getAttribute(StagingProcessesWebKeys.LAYOUT_SET_BRANCH);

if (layoutSetBranch == null) {
	layoutSetBranch = LayoutSetBranchLocalServiceUtil.getLayoutSetBranch(layoutRevision.getLayoutSetBranchId());
}

boolean workflowEnabled = WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, LayoutRevision.class.getName());

boolean hasWorkflowTask = false;

if (workflowEnabled) {
	hasWorkflowTask = StagingUtil.hasWorkflowTask(user.getUserId(), layoutRevision);
}

String taglibHelpMessage = null;

String layoutSetBranchName = HtmlUtil.escape(layoutSetBranchDisplayContext.getLayoutSetBranchDisplayName(layoutSetBranch));

if (layoutRevision.isHead()) {
	taglibHelpMessage = LanguageUtil.format(request, "this-version-will-be-published-when-x-is-published-to-live", layoutSetBranchName, false);
}
else if (hasWorkflowTask) {
	taglibHelpMessage = "you-are-currently-reviewing-this-page.-you-can-make-changes-and-send-them-to-the-next-step-in-the-workflow-when-ready";
}
else {
	taglibHelpMessage = "a-new-version-is-created-automatically-if-this-page-is-modified";
}
%>

<span class="staging-bar-workflow-text text-center">
	<div class="alert alert-fluid alert-info custom-info-alert" role="alert">
		<div class="staging-alert-container">
			<span class="alert-indicator">
				<svg aria-hidden="true" class="lexicon-icon lexicon-icon-info-circle">
					<use xlink:href="<%= themeDisplay.getPathThemeImages() %>/lexicon/icons.svg#info-circle" />
				</svg>
			</span>

			<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= layoutRevision.getStatus() %>" statusMessage='<%= layoutRevision.isHead() ? "ready-for-publication" : null %>' />
		</div>
	</div>
</span>

<aui:script>
	AUI.$('.layout-revision-info .taglib-workflow-status').on(
		'mouseenter',
		function(event) {
			Liferay.Portal.ToolTip.show(event.currentTarget, '<liferay-ui:message key="<%= taglibHelpMessage %>" />');
		}
	);
</aui:script>