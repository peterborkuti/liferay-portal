package com.liferay.exportimport.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.portal.kernel.language.LanguageUtil;

import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class ExportTemplatesDisplayContext {
	static NavigationItem exportTemplatesNavigationItem;

	public static JSPNavigationItemList ExportImportJSPNavigationItemList(
		RenderResponse renderResponse, HttpServletRequest request, PageContext pageContext) {
		exportTemplatesNavigationItem = new NavigationItem();
		exportTemplatesNavigationItem.setActive(true);
		exportTemplatesNavigationItem.setHref(renderResponse.createRenderURL());
		exportTemplatesNavigationItem.setLabel(LanguageUtil.get(request, "export-templates"));

		return new JSPNavigationItemList(pageContext) {
			{
				add(exportTemplatesNavigationItem);
			}
		};
	}
}
