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
boolean checked = GetterUtil.getBoolean(request.getAttribute("liferay-staging:checkbox:checked"));
String descriptionKey = GetterUtil.getString(request.getAttribute("liferay-staging:checkbox:description"));
boolean disabled = GetterUtil.getBoolean(request.getAttribute("liferay-staging:checkbox:disabled"));
String id = GetterUtil.getString(request.getAttribute("liferay-staging:checkbox:id"));
String labelKey = GetterUtil.getString(request.getAttribute("liferay-staging:checkbox:label"));
String name = GetterUtil.getString(request.getAttribute("liferay-staging:checkbox:name"));
String suggestionKey = GetterUtil.getString(request.getAttribute("liferay-staging:checkbox:suggestion"));
String warningKey = GetterUtil.getString(request.getAttribute("liferay-staging:checkbox:warning"));

if (Validator.isNull(id)) {
	id = name;
}

String checkedString = (checked) ? "checked" : "";
String description = LanguageUtil.get(request, descriptionKey);
String disabledString = (disabled) ? "disabled" : "";
String domId = liferayPortletResponse.getNamespace() + id;
String domName = liferayPortletResponse.getNamespace() + name;
String label = LanguageUtil.get(request, labelKey);
String suggestion = LanguageUtil.get(request, suggestionKey);
String warning = LanguageUtil.get(request, warningKey);
%>