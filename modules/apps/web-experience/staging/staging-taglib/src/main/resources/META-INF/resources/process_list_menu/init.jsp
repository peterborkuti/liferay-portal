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

<%@ page import="com.liferay.portal.kernel.backgroundtask.BackgroundTask" %>

<%@ include file="/init.jsp" %>

<%
BackgroundTask backgroundTask = (BackgroundTask)request.getAttribute("liferay-staging:process-list-menu:backgroundTask");
boolean localPublishing = ((Boolean)request.getAttribute("liferay-staging:process-list-menu:localPublishing")).booleanValue();
long backgroundTaskId = backgroundTask.getBackgroundTaskId();
long backgroundTaskGroupId = backgroundTask.getGroupId();
Date completionDate = backgroundTask.getCompletionDate();
String deleteLabel = LanguageUtil.get(request, ((completionDate != null) && completionDate.before(new Date())) ? "clear" : "cancel");
%>