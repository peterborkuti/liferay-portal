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

package com.liferay.sharing.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.sharing.model.SharingEntry;
import com.liferay.sharing.model.SharingEntryModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the SharingEntry service. Represents a row in the &quot;SharingEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SharingEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SharingEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryImpl
 * @see SharingEntry
 * @see SharingEntryModel
 * @generated
 */
@ProviderType
public class SharingEntryModelImpl extends BaseModelImpl<SharingEntry>
	implements SharingEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sharing entry model instance should use the {@link SharingEntry} interface instead.
	 */
	public static final String TABLE_NAME = "SharingEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "sharingEntryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "fromUserId", Types.BIGINT },
			{ "toUserId", Types.BIGINT },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "actionIds", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sharingEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("fromUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("toUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("actionIds", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table SharingEntry (uuid_ VARCHAR(75) null,sharingEntryId LONG not null primary key,groupId LONG,companyId LONG,createDate DATE null,modifiedDate DATE null,fromUserId LONG,toUserId LONG,classNameId LONG,classPK LONG,actionIds LONG)";
	public static final String TABLE_SQL_DROP = "drop table SharingEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY sharingEntry.sharingEntryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SharingEntry.sharingEntryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.sharing.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.sharing.model.SharingEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.sharing.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.sharing.model.SharingEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.sharing.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.sharing.model.SharingEntry"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long CLASSPK_COLUMN_BITMASK = 2L;
	public static final long COMPANYID_COLUMN_BITMASK = 4L;
	public static final long FROMUSERID_COLUMN_BITMASK = 8L;
	public static final long GROUPID_COLUMN_BITMASK = 16L;
	public static final long TOUSERID_COLUMN_BITMASK = 32L;
	public static final long UUID_COLUMN_BITMASK = 64L;
	public static final long SHARINGENTRYID_COLUMN_BITMASK = 128L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.sharing.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.sharing.model.SharingEntry"));

	public SharingEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sharingEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSharingEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sharingEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return SharingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sharingEntryId", getSharingEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fromUserId", getFromUserId());
		attributes.put("toUserId", getToUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("actionIds", getActionIds());

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

		Long sharingEntryId = (Long)attributes.get("sharingEntryId");

		if (sharingEntryId != null) {
			setSharingEntryId(sharingEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long fromUserId = (Long)attributes.get("fromUserId");

		if (fromUserId != null) {
			setFromUserId(fromUserId);
		}

		Long toUserId = (Long)attributes.get("toUserId");

		if (toUserId != null) {
			setToUserId(toUserId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long actionIds = (Long)attributes.get("actionIds");

		if (actionIds != null) {
			setActionIds(actionIds);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
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

	@Override
	public long getSharingEntryId() {
		return _sharingEntryId;
	}

	@Override
	public void setSharingEntryId(long sharingEntryId) {
		_sharingEntryId = sharingEntryId;
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
	public long getFromUserId() {
		return _fromUserId;
	}

	@Override
	public void setFromUserId(long fromUserId) {
		_columnBitmask |= FROMUSERID_COLUMN_BITMASK;

		if (!_setOriginalFromUserId) {
			_setOriginalFromUserId = true;

			_originalFromUserId = _fromUserId;
		}

		_fromUserId = fromUserId;
	}

	@Override
	public String getFromUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getFromUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setFromUserUuid(String fromUserUuid) {
	}

	public long getOriginalFromUserId() {
		return _originalFromUserId;
	}

	@Override
	public long getToUserId() {
		return _toUserId;
	}

	@Override
	public void setToUserId(long toUserId) {
		_columnBitmask |= TOUSERID_COLUMN_BITMASK;

		if (!_setOriginalToUserId) {
			_setOriginalToUserId = true;

			_originalToUserId = _toUserId;
		}

		_toUserId = toUserId;
	}

	@Override
	public String getToUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getToUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setToUserUuid(String toUserUuid) {
	}

	public long getOriginalToUserId() {
		return _originalToUserId;
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
	public long getActionIds() {
		return _actionIds;
	}

	@Override
	public void setActionIds(long actionIds) {
		_actionIds = actionIds;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				SharingEntry.class.getName()), getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SharingEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SharingEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SharingEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SharingEntryImpl sharingEntryImpl = new SharingEntryImpl();

		sharingEntryImpl.setUuid(getUuid());
		sharingEntryImpl.setSharingEntryId(getSharingEntryId());
		sharingEntryImpl.setGroupId(getGroupId());
		sharingEntryImpl.setCompanyId(getCompanyId());
		sharingEntryImpl.setCreateDate(getCreateDate());
		sharingEntryImpl.setModifiedDate(getModifiedDate());
		sharingEntryImpl.setFromUserId(getFromUserId());
		sharingEntryImpl.setToUserId(getToUserId());
		sharingEntryImpl.setClassNameId(getClassNameId());
		sharingEntryImpl.setClassPK(getClassPK());
		sharingEntryImpl.setActionIds(getActionIds());

		sharingEntryImpl.resetOriginalValues();

		return sharingEntryImpl;
	}

	@Override
	public int compareTo(SharingEntry sharingEntry) {
		long primaryKey = sharingEntry.getPrimaryKey();

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

		if (!(obj instanceof SharingEntry)) {
			return false;
		}

		SharingEntry sharingEntry = (SharingEntry)obj;

		long primaryKey = sharingEntry.getPrimaryKey();

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
		SharingEntryModelImpl sharingEntryModelImpl = this;

		sharingEntryModelImpl._originalUuid = sharingEntryModelImpl._uuid;

		sharingEntryModelImpl._originalGroupId = sharingEntryModelImpl._groupId;

		sharingEntryModelImpl._setOriginalGroupId = false;

		sharingEntryModelImpl._originalCompanyId = sharingEntryModelImpl._companyId;

		sharingEntryModelImpl._setOriginalCompanyId = false;

		sharingEntryModelImpl._setModifiedDate = false;

		sharingEntryModelImpl._originalFromUserId = sharingEntryModelImpl._fromUserId;

		sharingEntryModelImpl._setOriginalFromUserId = false;

		sharingEntryModelImpl._originalToUserId = sharingEntryModelImpl._toUserId;

		sharingEntryModelImpl._setOriginalToUserId = false;

		sharingEntryModelImpl._originalClassNameId = sharingEntryModelImpl._classNameId;

		sharingEntryModelImpl._setOriginalClassNameId = false;

		sharingEntryModelImpl._originalClassPK = sharingEntryModelImpl._classPK;

		sharingEntryModelImpl._setOriginalClassPK = false;

		sharingEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SharingEntry> toCacheModel() {
		SharingEntryCacheModel sharingEntryCacheModel = new SharingEntryCacheModel();

		sharingEntryCacheModel.uuid = getUuid();

		String uuid = sharingEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			sharingEntryCacheModel.uuid = null;
		}

		sharingEntryCacheModel.sharingEntryId = getSharingEntryId();

		sharingEntryCacheModel.groupId = getGroupId();

		sharingEntryCacheModel.companyId = getCompanyId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			sharingEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			sharingEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			sharingEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			sharingEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		sharingEntryCacheModel.fromUserId = getFromUserId();

		sharingEntryCacheModel.toUserId = getToUserId();

		sharingEntryCacheModel.classNameId = getClassNameId();

		sharingEntryCacheModel.classPK = getClassPK();

		sharingEntryCacheModel.actionIds = getActionIds();

		return sharingEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", sharingEntryId=");
		sb.append(getSharingEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", fromUserId=");
		sb.append(getFromUserId());
		sb.append(", toUserId=");
		sb.append(getToUserId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", actionIds=");
		sb.append(getActionIds());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sharing.model.SharingEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sharingEntryId</column-name><column-value><![CDATA[");
		sb.append(getSharingEntryId());
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
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fromUserId</column-name><column-value><![CDATA[");
		sb.append(getFromUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>toUserId</column-name><column-value><![CDATA[");
		sb.append(getToUserId());
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
			"<column><column-name>actionIds</column-name><column-value><![CDATA[");
		sb.append(getActionIds());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SharingEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SharingEntry.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _sharingEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _fromUserId;
	private long _originalFromUserId;
	private boolean _setOriginalFromUserId;
	private long _toUserId;
	private long _originalToUserId;
	private boolean _setOriginalToUserId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _actionIds;
	private long _columnBitmask;
	private SharingEntry _escapedModel;
}