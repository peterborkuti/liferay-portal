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

<%@ include file="/deletions/init.jsp" %>

<%
String deleteApplicationDataBeforeImportingLabelTitle = LanguageUtil.get(request, "delete-application-data-before-importing");
String deleteApplicationDataBeforeImportingLabelWarning = LanguageUtil.get(request, "delete-content-before-importing-warning");
String deleteApplicationDataBeforeImportingLabelSuggestion = LanguageUtil.get(request, "delete-content-before-importing-suggestion");

String individualDeletionsTitle = StringPool.BLANK;
String individualDeletionsDescription = StringPool.BLANK;

if (cmd.equals(Constants.EXPORT)) {
	individualDeletionsTitle = LanguageUtil.get(request, "export-individual-deletions");
	individualDeletionsDescription = LanguageUtil.get(request, "deletions-help-export");
}
else {
	individualDeletionsTitle = LanguageUtil.get(request, "replicate-individual-deletions");
	individualDeletionsDescription = LanguageUtil.get(request, "deletions-help");
}

String deleteApplicationDataBeforeImportingLabel =
	deleteApplicationDataBeforeImportingLabelTitle + ": <span style='font-weight: normal;'>" +
		deleteApplicationDataBeforeImportingLabelWarning + " " + deleteApplicationDataBeforeImportingLabelSuggestion + "</span> ";
String individualDeletionsLabel = individualDeletionsTitle + ": <span style='font-weight: normal;'>" + individualDeletionsDescription + "</span> ";
%>

<c:if test="<%= cmd.equals(Constants.EXPORT) || cmd.equals(Constants.IMPORT) || cmd.equals(Constants.PUBLISH) %>">
	<aui:fieldset cssClass="options-group" markupView="lexicon">
		<div class="sheet-section">
			<h3 class="sheet-subtitle"><liferay-ui:message key="deletions" /></h3>
			<c:if test="<%= !cmd.equals(Constants.EXPORT) %>">
				<clay:checkbox checked="<%= MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.DELETE_PORTLET_DATA, false) %>" disabled="<%= disableInputs %>" id="<%= liferayPortletResponse.getNamespace() + PortletDataHandlerKeys.DELETE_PORTLET_DATA %>" label="<%= deleteApplicationDataBeforeImportingLabel %>" name="<%= liferayPortletResponse.getNamespace() + PortletDataHandlerKeys.DELETE_PORTLET_DATA %>" />
			</c:if>

			<clay:checkbox checked="<%= MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.DELETIONS, false) %>" disabled="<%= disableInputs %>" id="<%= liferayPortletResponse.getNamespace() + PortletDataHandlerKeys.DELETIONS %>" label="<%= individualDeletionsLabel %>" name="<%= liferayPortletResponse.getNamespace() + PortletDataHandlerKeys.DELETIONS %>" />
		</div>
	</aui:fieldset>
</c:if>