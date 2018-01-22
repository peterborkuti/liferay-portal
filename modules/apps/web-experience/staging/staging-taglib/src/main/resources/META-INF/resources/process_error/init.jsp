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

<%@ page import="com.liferay.exportimport.kernel.exception.RemoteExportException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchGroupException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchLayoutException" %><%@
page import="com.liferay.portal.kernel.exception.NoSuchRoleException" %><%@
page import="com.liferay.portal.kernel.exception.RemoteOptionsException" %><%@
page import="com.liferay.portal.kernel.security.auth.AuthException" %><%@
page import="com.liferay.portal.kernel.security.auth.RemoteAuthException" %>

<%@ include file="/init.jsp" %>

<%
boolean authException = ((Boolean)request.getAttribute("liferay-staging:process-error:authException")).booleanValue();
boolean illegalArgumentException = ((Boolean)request.getAttribute("liferay-staging:process-error:illegalArgumentException")).booleanValue();
boolean noSuchExceptions = ((Boolean)request.getAttribute("liferay-staging:process-error:noSuchExceptions")).booleanValue();
boolean remoteExportException = ((Boolean)request.getAttribute("liferay-staging:process-error:remoteExportException")).booleanValue();
boolean remoteOptionsException = ((Boolean)request.getAttribute("liferay-staging:process-error:remoteOptionsException")).booleanValue();
%>