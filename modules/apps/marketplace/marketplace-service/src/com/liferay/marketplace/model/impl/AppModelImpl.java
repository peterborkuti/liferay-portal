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

package com.liferay.marketplace.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.marketplace.model.App;
import com.liferay.marketplace.model.AppModel;
import com.liferay.marketplace.model.AppSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the App service. Represents a row in the &quot;Marketplace_App&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AppModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AppImpl}.
 * </p>
 *
 * @author Ryan Park
 * @see AppImpl
 * @see App
 * @see AppModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class AppModelImpl extends BaseModelImpl<App> implements AppModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a app model instance should use the {@link App} interface instead.
	 */
	public static final String TABLE_NAME = "Marketplace_App";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "appId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "remoteAppId", Types.BIGINT },
			{ "title", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "category", Types.VARCHAR },
			{ "iconURL", Types.VARCHAR },
			{ "version", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("appId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("remoteAppId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("category", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("iconURL", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("version", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table Marketplace_App (uuid_ VARCHAR(75) null,appId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,remoteAppId LONG,title VARCHAR(75) null,description STRING null,category VARCHAR(75) null,iconURL STRING null,version VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Marketplace_App";
	public static final String ORDER_BY_JPQL = " ORDER BY app.appId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Marketplace_App.appId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.marketplace.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.marketplace.model.App"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.marketplace.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.marketplace.model.App"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.marketplace.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.marketplace.model.App"),
			true);
	public static final long CATEGORY_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long REMOTEAPPID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long APPID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static App toModel(AppSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		App model = new AppImpl();

		model.setUuid(soapModel.getUuid());
		model.setAppId(soapModel.getAppId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setRemoteAppId(soapModel.getRemoteAppId());
		model.setTitle(soapModel.getTitle());
		model.setDescription(soapModel.getDescription());
		model.setCategory(soapModel.getCategory());
		model.setIconURL(soapModel.getIconURL());
		model.setVersion(soapModel.getVersion());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<App> toModels(AppSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<App> models = new ArrayList<App>(soapModels.length);

		for (AppSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.marketplace.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.marketplace.model.App"));

	public AppModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _appId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAppId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _appId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return App.class;
	}

	@Override
	public String getModelClassName() {
		return App.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("appId", getAppId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("remoteAppId", getRemoteAppId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("category", getCategory());
		attributes.put("iconURL", getIconURL());
		attributes.put("version", getVersion());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long appId = (Long)attributes.get("appId");

		if (appId != null) {
			setAppId(appId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long remoteAppId = (Long)attributes.get("remoteAppId");

		if (remoteAppId != null) {
			setRemoteAppId(remoteAppId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		String iconURL = (String)attributes.get("iconURL");

		if (iconURL != null) {
			setIconURL(iconURL);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getAppId() {
		return _appId;
	}

	@Override
	public void setAppId(long appId) {
		_appId = appId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getRemoteAppId() {
		return _remoteAppId;
	}

	@Override
	public void setRemoteAppId(long remoteAppId) {
		_columnBitmask |= REMOTEAPPID_COLUMN_BITMASK;

		if (!_setOriginalRemoteAppId) {
			_setOriginalRemoteAppId = true;

			_originalRemoteAppId = _remoteAppId;
		}

		_remoteAppId = remoteAppId;
	}

	public long getOriginalRemoteAppId() {
		return _originalRemoteAppId;
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return StringPool.BLANK;
		}
		else {
			return _title;
		}
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return StringPool.BLANK;
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getCategory() {
		if (_category == null) {
			return StringPool.BLANK;
		}
		else {
			return _category;
		}
	}

	@Override
	public void setCategory(String category) {
		_columnBitmask |= CATEGORY_COLUMN_BITMASK;

		if (_originalCategory == null) {
			_originalCategory = _category;
		}

		_category = category;
	}

	public String getOriginalCategory() {
		return GetterUtil.getString(_originalCategory);
	}

	@JSON
	@Override
	public String getIconURL() {
		if (_iconURL == null) {
			return StringPool.BLANK;
		}
		else {
			return _iconURL;
		}
	}

	@Override
	public void setIconURL(String iconURL) {
		_iconURL = iconURL;
	}

	@JSON
	@Override
	public String getVersion() {
		if (_version == null) {
			return StringPool.BLANK;
		}
		else {
			return _version;
		}
	}

	@Override
	public void setVersion(String version) {
		_version = version;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			App.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public App toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (App)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AppImpl appImpl = new AppImpl();

		appImpl.setUuid(getUuid());
		appImpl.setAppId(getAppId());
		appImpl.setCompanyId(getCompanyId());
		appImpl.setUserId(getUserId());
		appImpl.setUserName(getUserName());
		appImpl.setCreateDate(getCreateDate());
		appImpl.setModifiedDate(getModifiedDate());
		appImpl.setRemoteAppId(getRemoteAppId());
		appImpl.setTitle(getTitle());
		appImpl.setDescription(getDescription());
		appImpl.setCategory(getCategory());
		appImpl.setIconURL(getIconURL());
		appImpl.setVersion(getVersion());

		appImpl.resetOriginalValues();

		return appImpl;
	}

	@Override
	public int compareTo(App app) {
		long primaryKey = app.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof App)) {
			return false;
		}

		App app = (App)obj;

		long primaryKey = app.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		AppModelImpl appModelImpl = this;

		appModelImpl._originalUuid = appModelImpl._uuid;

		appModelImpl._originalCompanyId = appModelImpl._companyId;

		appModelImpl._setOriginalCompanyId = false;

		appModelImpl._setModifiedDate = false;

		appModelImpl._originalRemoteAppId = appModelImpl._remoteAppId;

		appModelImpl._setOriginalRemoteAppId = false;

		appModelImpl._originalCategory = appModelImpl._category;

		appModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<App> toCacheModel() {
		AppCacheModel appCacheModel = new AppCacheModel();

		appCacheModel.uuid = getUuid();

		String uuid = appCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			appCacheModel.uuid = null;
		}

		appCacheModel.appId = getAppId();

		appCacheModel.companyId = getCompanyId();

		appCacheModel.userId = getUserId();

		appCacheModel.userName = getUserName();

		String userName = appCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			appCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			appCacheModel.createDate = createDate.getTime();
		}
		else {
			appCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			appCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			appCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		appCacheModel.remoteAppId = getRemoteAppId();

		appCacheModel.title = getTitle();

		String title = appCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			appCacheModel.title = null;
		}

		appCacheModel.description = getDescription();

		String description = appCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			appCacheModel.description = null;
		}

		appCacheModel.category = getCategory();

		String category = appCacheModel.category;

		if ((category != null) && (category.length() == 0)) {
			appCacheModel.category = null;
		}

		appCacheModel.iconURL = getIconURL();

		String iconURL = appCacheModel.iconURL;

		if ((iconURL != null) && (iconURL.length() == 0)) {
			appCacheModel.iconURL = null;
		}

		appCacheModel.version = getVersion();

		String version = appCacheModel.version;

		if ((version != null) && (version.length() == 0)) {
			appCacheModel.version = null;
		}

		return appCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", appId=");
		sb.append(getAppId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", remoteAppId=");
		sb.append(getRemoteAppId());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", category=");
		sb.append(getCategory());
		sb.append(", iconURL=");
		sb.append(getIconURL());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.marketplace.model.App");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>appId</column-name><column-value><![CDATA[");
		sb.append(getAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>remoteAppId</column-name><column-value><![CDATA[");
		sb.append(getRemoteAppId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>category</column-name><column-value><![CDATA[");
		sb.append(getCategory());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>iconURL</column-name><column-value><![CDATA[");
		sb.append(getIconURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = App.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			App.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _appId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _remoteAppId;
	private long _originalRemoteAppId;
	private boolean _setOriginalRemoteAppId;
	private String _title;
	private String _description;
	private String _category;
	private String _originalCategory;
	private String _iconURL;
	private String _version;
	private long _columnBitmask;
	private App _escapedModel;
}