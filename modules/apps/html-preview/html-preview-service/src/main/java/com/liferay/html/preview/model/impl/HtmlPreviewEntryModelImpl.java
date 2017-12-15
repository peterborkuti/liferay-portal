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

package com.liferay.html.preview.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.html.preview.model.HtmlPreviewEntry;
import com.liferay.html.preview.model.HtmlPreviewEntryModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the HtmlPreviewEntry service. Represents a row in the &quot;HtmlPreviewEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link HtmlPreviewEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HtmlPreviewEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HtmlPreviewEntryImpl
 * @see HtmlPreviewEntry
 * @see HtmlPreviewEntryModel
 * @generated
 */
@ProviderType
public class HtmlPreviewEntryModelImpl extends BaseModelImpl<HtmlPreviewEntry>
	implements HtmlPreviewEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a html preview entry model instance should use the {@link HtmlPreviewEntry} interface instead.
	 */
	public static final String TABLE_NAME = "HtmlPreviewEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "htmlPreviewEntryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "fileEntryId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("htmlPreviewEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table HtmlPreviewEntry (htmlPreviewEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,fileEntryId LONG)";
	public static final String TABLE_SQL_DROP = "drop table HtmlPreviewEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY htmlPreviewEntry.htmlPreviewEntryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY HtmlPreviewEntry.htmlPreviewEntryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.html.preview.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.html.preview.model.HtmlPreviewEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.html.preview.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.html.preview.model.HtmlPreviewEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.html.preview.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.html.preview.model.HtmlPreviewEntry"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long CLASSPK_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long HTMLPREVIEWENTRYID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.html.preview.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.html.preview.model.HtmlPreviewEntry"));

	public HtmlPreviewEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _htmlPreviewEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setHtmlPreviewEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _htmlPreviewEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return HtmlPreviewEntry.class;
	}

	@Override
	public String getModelClassName() {
		return HtmlPreviewEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("htmlPreviewEntryId", getHtmlPreviewEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("fileEntryId", getFileEntryId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long htmlPreviewEntryId = (Long)attributes.get("htmlPreviewEntryId");

		if (htmlPreviewEntryId != null) {
			setHtmlPreviewEntryId(htmlPreviewEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}
	}

	@Override
	public long getHtmlPreviewEntryId() {
		return _htmlPreviewEntryId;
	}

	@Override
	public void setHtmlPreviewEntryId(long htmlPreviewEntryId) {
		_htmlPreviewEntryId = htmlPreviewEntryId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

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
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			HtmlPreviewEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public HtmlPreviewEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (HtmlPreviewEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		HtmlPreviewEntryImpl htmlPreviewEntryImpl = new HtmlPreviewEntryImpl();

		htmlPreviewEntryImpl.setHtmlPreviewEntryId(getHtmlPreviewEntryId());
		htmlPreviewEntryImpl.setGroupId(getGroupId());
		htmlPreviewEntryImpl.setCompanyId(getCompanyId());
		htmlPreviewEntryImpl.setUserId(getUserId());
		htmlPreviewEntryImpl.setUserName(getUserName());
		htmlPreviewEntryImpl.setCreateDate(getCreateDate());
		htmlPreviewEntryImpl.setModifiedDate(getModifiedDate());
		htmlPreviewEntryImpl.setClassNameId(getClassNameId());
		htmlPreviewEntryImpl.setClassPK(getClassPK());
		htmlPreviewEntryImpl.setFileEntryId(getFileEntryId());

		htmlPreviewEntryImpl.resetOriginalValues();

		return htmlPreviewEntryImpl;
	}

	@Override
	public int compareTo(HtmlPreviewEntry htmlPreviewEntry) {
		long primaryKey = htmlPreviewEntry.getPrimaryKey();

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

		if (!(obj instanceof HtmlPreviewEntry)) {
			return false;
		}

		HtmlPreviewEntry htmlPreviewEntry = (HtmlPreviewEntry)obj;

		long primaryKey = htmlPreviewEntry.getPrimaryKey();

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
		HtmlPreviewEntryModelImpl htmlPreviewEntryModelImpl = this;

		htmlPreviewEntryModelImpl._originalGroupId = htmlPreviewEntryModelImpl._groupId;

		htmlPreviewEntryModelImpl._setOriginalGroupId = false;

		htmlPreviewEntryModelImpl._setModifiedDate = false;

		htmlPreviewEntryModelImpl._originalClassNameId = htmlPreviewEntryModelImpl._classNameId;

		htmlPreviewEntryModelImpl._setOriginalClassNameId = false;

		htmlPreviewEntryModelImpl._originalClassPK = htmlPreviewEntryModelImpl._classPK;

		htmlPreviewEntryModelImpl._setOriginalClassPK = false;

		htmlPreviewEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<HtmlPreviewEntry> toCacheModel() {
		HtmlPreviewEntryCacheModel htmlPreviewEntryCacheModel = new HtmlPreviewEntryCacheModel();

		htmlPreviewEntryCacheModel.htmlPreviewEntryId = getHtmlPreviewEntryId();

		htmlPreviewEntryCacheModel.groupId = getGroupId();

		htmlPreviewEntryCacheModel.companyId = getCompanyId();

		htmlPreviewEntryCacheModel.userId = getUserId();

		htmlPreviewEntryCacheModel.userName = getUserName();

		String userName = htmlPreviewEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			htmlPreviewEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			htmlPreviewEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			htmlPreviewEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			htmlPreviewEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			htmlPreviewEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		htmlPreviewEntryCacheModel.classNameId = getClassNameId();

		htmlPreviewEntryCacheModel.classPK = getClassPK();

		htmlPreviewEntryCacheModel.fileEntryId = getFileEntryId();

		return htmlPreviewEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{htmlPreviewEntryId=");
		sb.append(getHtmlPreviewEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
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
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.html.preview.model.HtmlPreviewEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>htmlPreviewEntryId</column-name><column-value><![CDATA[");
		sb.append(getHtmlPreviewEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = HtmlPreviewEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			HtmlPreviewEntry.class
		};
	private long _htmlPreviewEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _fileEntryId;
	private long _columnBitmask;
	private HtmlPreviewEntry _escapedModel;
}