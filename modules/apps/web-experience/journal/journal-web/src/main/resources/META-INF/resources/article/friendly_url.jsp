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
JournalArticle article = journalDisplayContext.getArticle();
%>

<div class="form-group">
	<label for="<portlet:namespace />friendlyURL"><liferay-ui:message key="friendly-url" /><liferay-ui:icon-help message='<%= LanguageUtil.format(request, "for-example-x", "<em>/news</em>", false) %>' /></label>

	<div class="input-group lfr-friendly-url-input-group">
		<span class="input-group-addon" id="<portlet:namespace />urlBase">
			<span class="input-group-constrain"><liferay-ui:message key="<%= StringUtil.shorten(themeDisplay.getPortalURL() + JournalArticleConstants.CANONICAL_URL_SEPARATOR, 40) %>" /></span>
		</span>

		<liferay-ui:input-localized defaultLanguageId="<%= LocaleUtil.toLanguageId(themeDisplay.getSiteDefaultLocale()) %>" name="friendlyURL" xml="<%= HttpUtil.decodeURL(article.getFriendlyURLsXML()) %>" />
	</div>
</div>