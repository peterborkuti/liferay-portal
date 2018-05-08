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

package com.liferay.exportimport.web.internal.display.context;

import com.liferay.exportimport.kernel.configuration.ExportImportConfigurationConstants;
import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalServiceUtil;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownGroupItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPCreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPDropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portlet.layoutsadmin.display.context.GroupDisplayContextHelper;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Péter Alius
 */
public class ExportImportToolbarDisplayContext {

	public ExportImportToolbarDisplayContext(
		HttpServletRequest request, PageContext pageContext,
		LiferayPortletResponse portletResponse) {

		_request = request;

		_pageContext = pageContext;

		_portletResponse = portletResponse;

		Portlet portlet = portletResponse.getPortlet();

		_portletNamespace = PortalUtil.getPortletNamespace(
			portlet.getRootPortletId());
	}

	public JSPDropdownItemList getActionItems() {
		return new JSPDropdownItemList(_pageContext) {
			{
				add(
					deleteEntriesItem -> {
						deleteEntriesItem.setHref(
							"javascript:" + _portletNamespace + "deleteEntries();");
						deleteEntriesItem.setLabel(
							LanguageUtil.get(_request, "delete"));
					});
			}
		};
	}

	public JSPCreationMenu getCreationMenu() {
		JSPCreationMenu creationMenu = new JSPCreationMenu(_pageContext);

		GroupDisplayContextHelper groupDisplayContextHelper =
			new GroupDisplayContextHelper(_request);

		String mvcRenderCommandName = ParamUtil.getString(
			_request, "mvcRenderCommandName");
		String mvcPath;
		String cmd;
		String label;

		if (mvcRenderCommandName.equals("exportLayoutsView")) {
			mvcPath = "/export/new_export/export_layouts.jsp";
			cmd = Constants.EXPORT;
			label = "custom-export";
		}
		else {
			mvcPath = "/import/new_import/import_layouts.jsp";
			cmd = Constants.IMPORT;
			label = "import";
		}

		creationMenu.addPrimaryDropdownItem(
			mainAddButton -> {
				mainAddButton.setHref(
					getRenderURL(), "mvcPath", mvcPath, Constants.CMD, cmd,
					"groupId",
					String.valueOf(ParamUtil.getLong(_request, "groupId")),
					"liveGroupId",
					String.valueOf(groupDisplayContextHelper.getLiveGroupId()),
					"displayStyle",
					ParamUtil.getString(
						_request, "displayStyle", "descriptive"));

				mainAddButton.setLabel(LanguageUtil.get(_request, label));
			});

		if (Objects.equals(cmd, Constants.EXPORT)) {
			List<ExportImportConfiguration> exportImportConfigurations =
				ExportImportConfigurationLocalServiceUtil.getExportImportConfigurations(
					groupDisplayContextHelper.getLiveGroupId(),
					ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT);

			for (ExportImportConfiguration exportImportConfiguration :
					exportImportConfigurations) {

				Map<String, Serializable> settingsMap =
					exportImportConfiguration.getSettingsMap();

				creationMenu.addRestDropdownItem(
					dropdownItem -> {
						dropdownItem.setHref(
							getRenderURL(), "mvcPath",
							"/export/new_export/export_layouts.jsp",
							Constants.CMD, Constants.EXPORT,
							"exportImportConfigurationId",
							String.valueOf(exportImportConfiguration.getExportImportConfigurationId()),
							"groupId",
							String.valueOf(ParamUtil.getLong(_request, "groupId")),
							"liveGroupId",
							String.valueOf(groupDisplayContextHelper.getLiveGroupId()),
							"privateLayout",
							MapUtil.getString(settingsMap, "privateLayout"),
							"displayStyle",
							ParamUtil.getString(
								_request, "displayStyle", "descriptive"));

						dropdownItem.setLabel(
							exportImportConfiguration.getName());
					});
			}
		}

		return creationMenu;
	}

	public DropdownGroupItem getFilterByList() {
		DropdownGroupItem filterByGroup = new DropdownGroupItem();

		filterByGroup.setLabel(LanguageUtil.get(_request, "filter"));

		DropdownItemList filterByList = new DropdownItemList();

		DropdownItem allItems = new DropdownItem();

		allItems.setHref(
			getRenderURL(), "groupId",
			String.valueOf(ParamUtil.getLong(_request, "groupId")),
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")),
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"),
			"orderByCol", ParamUtil.getString(_request, "orderByCol"),
			"orderByType", ParamUtil.getString(_request, "orderByType"),
			"navigation", "all", "searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		allItems.setLabel(LanguageUtil.get(_request, "all"));

		DropdownItem completedItems = new DropdownItem();

		completedItems.setHref(
			getRenderURL(), "groupId",
			String.valueOf(ParamUtil.getLong(_request, "groupId")),
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")),
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"),
			"orderByCol", ParamUtil.getString(_request, "orderByCol"),
			"orderByType", ParamUtil.getString(_request, "orderByType"),
			"navigation", "completed", "searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		completedItems.setLabel(LanguageUtil.get(_request, "completed"));

		DropdownItem inProgressItems = new DropdownItem();

		inProgressItems.setHref(
			getRenderURL(), "groupId",
			String.valueOf(ParamUtil.getLong(_request, "groupId")),
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")),
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"),
			"orderByCol", ParamUtil.getString(_request, "orderByCol"),
			"orderByType", ParamUtil.getString(_request, "orderByType"),
			"navigation", "in-progress", "searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		inProgressItems.setLabel(LanguageUtil.get(_request, "in-progress"));

		filterByList.add(allItems);
		filterByList.add(completedItems);
		filterByList.add(inProgressItems);

		filterByGroup.setSeparator(true);
		filterByGroup.setDropdownItems(filterByList);

		return filterByGroup;
	}

	public DropdownItemList getFilterItems() {
		return new DropdownItemList() {
			{
				add(getFilterByList());
				add(getOrderByList());
			}
		};
	}

	public DropdownGroupItem getOrderByList() {
		DropdownGroupItem orderByGroup = new DropdownGroupItem();

		orderByGroup.setLabel(LanguageUtil.get(_request, "order-by"));

		DropdownItemList orderByList = new DropdownItemList();

		DropdownItem orderByName = new DropdownItem();

		orderByName.setHref(
			getRenderURL(), "groupId",
			String.valueOf(ParamUtil.getLong(_request, "groupId")),
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")),
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"),
			"orderByCol", "name", "orderByType",
			ParamUtil.getString(_request, "orderByType"), "navigation",
			ParamUtil.getString(_request, "navigation", "all"),
			"searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		orderByName.setLabel(LanguageUtil.get(_request, "name"));

		DropdownItem orderByCreateDate = new DropdownItem();

		orderByCreateDate.setHref(
			getRenderURL(), "groupId",
			String.valueOf(ParamUtil.getLong(_request, "groupId")),
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")),
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"),
			"orderByCol", "create-date", "orderByType",
			ParamUtil.getString(_request, "orderByType"), "navigation",
			ParamUtil.getString(_request, "navigation", "all"),
			"searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		orderByCreateDate.setLabel(LanguageUtil.get(_request, "create-date"));

		DropdownItem orderByCompletionDate = new DropdownItem();

		orderByCreateDate.setHref(
			getRenderURL(), "groupId",
			String.valueOf(ParamUtil.getLong(_request, "groupId")),
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")),
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"),
			"orderByCol", "completion-date", "orderByType",
			ParamUtil.getString(_request, "orderByType"), "navigation",
			ParamUtil.getString(_request, "navigation", "all"),
			"searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		orderByCompletionDate.setLabel(
			LanguageUtil.get(_request, "completion-date"));

		orderByList.add(orderByName);
		orderByList.add(orderByCreateDate);
		orderByList.add(orderByCompletionDate);

		orderByGroup.setDropdownItems(orderByList);

		return orderByGroup;
	}

	public String getSearchContainerId() {
		return ParamUtil.getString(_request, "searchContainerId");
	}

	public String getSortingOrder() {
		return ParamUtil.getString(_request, "orderByType", "asc");
	}

	public String getSortingURL() {
		PortletURL sortingURL = getRenderURL();

		sortingURL.setParameter(
			"groupId", String.valueOf(ParamUtil.getLong(_request, "groupId")));
		sortingURL.setParameter(
			"privateLayout",
			String.valueOf(ParamUtil.getBoolean(_request, "privateLayout")));
		sortingURL.setParameter(
			"displayStyle",
			ParamUtil.getString(_request, "displayStyle", "descriptive"));
		sortingURL.setParameter(
			"orderByCol", ParamUtil.getString(_request, "orderByCol"));

		String orderByType = ParamUtil.getString(_request, "orderByType");

		if (orderByType.equals("asc")) {
			sortingURL.setParameter("orderByType", "desc");
		}
		else {
			sortingURL.setParameter("orderByType", "asc");
		}

		sortingURL.setParameter(
			"navigation", ParamUtil.getString(_request, "navigation", "all"));
		sortingURL.setParameter(
			"searchContainerId",
			ParamUtil.getString(_request, "searchContainerId"));

		return sortingURL.toString();
	}

	public List<ViewTypeItem> getViewTypes() {
		return new ViewTypeItemList(getRenderURL(), getDisplayStyle()) {
			{
				addListViewTypeItem();
				addTableViewTypeItem();
			}
		};
	}

	protected String getDisplayStyle() {
		return ParamUtil.getString(_request, "displayStyle", "list");
	}

	protected DropdownItem getDropdownItem(String label, String href) {
		return _getDropdownItem(label, href, false, false, StringPool.BLANK);
	}

	protected PortletURL getRenderURL() {
		return _portletResponse.createRenderURL();
	}

	private DropdownItem _getDropdownItem(
		String label, String href, boolean separator, boolean quickAction,
		String icon) {

		DropdownItem item = new DropdownItem();

		item.setLabel(label);
		item.setHref(href);

		item.setSeparator(separator);

		item.setQuickAction(quickAction);
		item.setIcon(icon);

		return item;
	}

	private final PageContext _pageContext;
	private final String _portletNamespace;
	private final LiferayPortletResponse _portletResponse;
	private final HttpServletRequest _request;

}