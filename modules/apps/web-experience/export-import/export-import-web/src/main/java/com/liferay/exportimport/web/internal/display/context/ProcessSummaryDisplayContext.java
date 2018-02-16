package com.liferay.exportimport.web.internal.display.context;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;

public class ProcessSummaryDisplayContext {
	public ArrayList getPageNames(JSONArray layoutsArray) {
		ArrayList pageNames = new ArrayList();

		for (int i = 0; i < layoutsArray.length(); ++i) {
			JSONObject layoutArrayElement = layoutsArray.getJSONObject(i);

			String pageName = layoutArrayElement.getString("name");

			pageNames.add(pageName);

			if (layoutArrayElement.getBoolean("hasChildren")) {
				ArrayList childPageNames = getChildPageNames(pageName, layoutArrayElement.getJSONObject("children"));
				pageNames.addAll(childPageNames);
			}
		}

		return pageNames;
	}

	private ArrayList getChildPageNames(String basePageName, JSONObject layoutArrayChildElement) {
		ArrayList pageNames = new ArrayList();

		JSONArray childrenLayouts = layoutArrayChildElement.getJSONArray("layouts");

		for (int i = 0; i < childrenLayouts.length(); ++i) {
			JSONObject childLayout = childrenLayouts.getJSONObject(i);

			String childPageName = basePageName + StringPool.FORWARD_SLASH + childLayout.getString("name");

			pageNames.add(childPageName);

			if (childLayout.getBoolean("hasChildren")) {
				ArrayList childPageNames = getChildPageNames(childPageName, childLayout.getJSONObject("children"));
				pageNames.addAll(childPageNames);
			}
		}

		return pageNames;
	}
}
