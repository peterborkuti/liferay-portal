/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;

import java.io.InputStream;

import java.util.List;

/**
 * <a href="WorkflowDefinitionManagerProxyBean.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionManagerProxyBean
	extends BaseProxyBean implements WorkflowDefinitionManager {

	public WorkflowDefinition deployWorkflowDefinition(
		long companyId, long userId, String name, InputStream inputStream) {

		throw new UnsupportedOperationException();
	}

	public WorkflowDefinition getWorkflowDefinition(
		long companyId, String name, int version) {

		throw new UnsupportedOperationException();
	}

	public int getWorkflowDefinitionCount(long companyId) {
		throw new UnsupportedOperationException();
	}

	public int getWorkflowDefinitionCount(long companyId, String name) {
		throw new UnsupportedOperationException();
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
		long companyId, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public List<WorkflowDefinition> getWorkflowDefinitions(
		long companyId, String name, int start, int end,
		OrderByComparator orderByComparator) {

		throw new UnsupportedOperationException();
	}

	public void undeployWorkflowDefinition(
		long companyId, long userId, String name, int version) {

		throw new UnsupportedOperationException();
	}

}