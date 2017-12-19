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

package com.liferay.staging.taglib.servlet.taglib;

import aQute.bnd.annotation.ProviderType;

import com.liferay.staging.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Peter Borkuti
 */
@ProviderType
public class ProcessListMenuTag extends IncludeTag {

	public void setBackgroundTaskGroupId(long backgroundTaskGroupId) {
		_backgroundTaskGroupId = backgroundTaskGroupId;
	}

	public void setBackgroundTaskId(long backgroundTaskId) {
		_backgroundTaskId = backgroundTaskId;
	}

	public void setDeleteLabel(String deleteLabel) {
		_deleteLabel = deleteLabel;
	}

	public void setLocalPublishing(boolean localPublishing) {
		_localPublishing = localPublishing;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		_backgroundTaskId = 0;
		_backgroundTaskGroupId = 0;
		_deleteLabel = null;
		_localPublishing = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-staging:process-list-menu:backgroundTaskGroupId",
			_backgroundTaskGroupId);
		request.setAttribute(
			"liferay-staging:process-list-menu:backgroundTaskId",
			_backgroundTaskId);
		request.setAttribute(
			"liferay-staging:process-list-menu:deleteLabel", _deleteLabel);
		request.setAttribute(
			"liferay-staging:process-list-menu:localPublishing",
			_localPublishing);
	}

	private static final String _PAGE = "/process_list_menu/page.jsp";

	private long _backgroundTaskGroupId;
	private long _backgroundTaskId;
	private String _deleteLabel;
	private boolean _localPublishing;

}