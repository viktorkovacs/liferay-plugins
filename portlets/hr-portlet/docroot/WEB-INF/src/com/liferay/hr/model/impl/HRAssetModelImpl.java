/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.model.impl;

import com.liferay.hr.model.HRAsset;
import com.liferay.hr.model.HRAssetModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the HRAsset service. Represents a row in the &quot;HRAsset&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.hr.model.HRAssetModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link HRAssetImpl}.
 * </p>
 *
 * @author Wesley Gong
 * @see HRAssetImpl
 * @see com.liferay.hr.model.HRAsset
 * @see com.liferay.hr.model.HRAssetModel
 * @generated
 */
public class HRAssetModelImpl extends BaseModelImpl<HRAsset>
	implements HRAssetModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a h r asset model instance should use the {@link com.liferay.hr.model.HRAsset} interface instead.
	 */
	public static final String TABLE_NAME = "HRAsset";
	public static final Object[][] TABLE_COLUMNS = {
			{ "hrAssetId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "hrAssetDefinitionId", Types.BIGINT },
			{ "hrAssetTypeId", Types.BIGINT },
			{ "serialNumber", Types.VARCHAR },
			{ "inactiveDate", Types.TIMESTAMP }
		};
	public static final String TABLE_SQL_CREATE = "create table HRAsset (hrAssetId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,hrAssetDefinitionId LONG,hrAssetTypeId LONG,serialNumber VARCHAR(75) null,inactiveDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table HRAsset";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.hr.model.HRAsset"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.hr.model.HRAsset"),
			true);

	public Class<?> getModelClass() {
		return HRAsset.class;
	}

	public String getModelClassName() {
		return HRAsset.class.getName();
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.hr.model.HRAsset"));

	public HRAssetModelImpl() {
	}

	public long getPrimaryKey() {
		return _hrAssetId;
	}

	public void setPrimaryKey(long primaryKey) {
		setHrAssetId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_hrAssetId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getHrAssetId() {
		return _hrAssetId;
	}

	public void setHrAssetId(long hrAssetId) {
		_hrAssetId = hrAssetId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getHrAssetDefinitionId() {
		return _hrAssetDefinitionId;
	}

	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAssetDefinitionId = hrAssetDefinitionId;
	}

	public long getHrAssetTypeId() {
		return _hrAssetTypeId;
	}

	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetTypeId = hrAssetTypeId;
	}

	public String getSerialNumber() {
		if (_serialNumber == null) {
			return StringPool.BLANK;
		}
		else {
			return _serialNumber;
		}
	}

	public void setSerialNumber(String serialNumber) {
		_serialNumber = serialNumber;
	}

	public Date getInactiveDate() {
		return _inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		_inactiveDate = inactiveDate;
	}

	@Override
	public HRAsset toEscapedModel() {
		if (isEscapedModel()) {
			return (HRAsset)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (HRAsset)Proxy.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					HRAsset.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		HRAssetImpl hrAssetImpl = new HRAssetImpl();

		hrAssetImpl.setHrAssetId(getHrAssetId());
		hrAssetImpl.setGroupId(getGroupId());
		hrAssetImpl.setCompanyId(getCompanyId());
		hrAssetImpl.setUserId(getUserId());
		hrAssetImpl.setUserName(getUserName());
		hrAssetImpl.setCreateDate(getCreateDate());
		hrAssetImpl.setModifiedDate(getModifiedDate());
		hrAssetImpl.setHrAssetDefinitionId(getHrAssetDefinitionId());
		hrAssetImpl.setHrAssetTypeId(getHrAssetTypeId());
		hrAssetImpl.setSerialNumber(getSerialNumber());
		hrAssetImpl.setInactiveDate(getInactiveDate());

		hrAssetImpl.resetOriginalValues();

		return hrAssetImpl;
	}

	public int compareTo(HRAsset hrAsset) {
		long primaryKey = hrAsset.getPrimaryKey();

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
		if (obj == null) {
			return false;
		}

		HRAsset hrAsset = null;

		try {
			hrAsset = (HRAsset)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = hrAsset.getPrimaryKey();

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
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<HRAsset> toCacheModel() {
		HRAssetCacheModel hrAssetCacheModel = new HRAssetCacheModel();

		hrAssetCacheModel.hrAssetId = getHrAssetId();

		hrAssetCacheModel.groupId = getGroupId();

		hrAssetCacheModel.companyId = getCompanyId();

		hrAssetCacheModel.userId = getUserId();

		hrAssetCacheModel.userName = getUserName();

		String userName = hrAssetCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			hrAssetCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			hrAssetCacheModel.createDate = createDate.getTime();
		}
		else {
			hrAssetCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			hrAssetCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			hrAssetCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		hrAssetCacheModel.hrAssetDefinitionId = getHrAssetDefinitionId();

		hrAssetCacheModel.hrAssetTypeId = getHrAssetTypeId();

		hrAssetCacheModel.serialNumber = getSerialNumber();

		String serialNumber = hrAssetCacheModel.serialNumber;

		if ((serialNumber != null) && (serialNumber.length() == 0)) {
			hrAssetCacheModel.serialNumber = null;
		}

		Date inactiveDate = getInactiveDate();

		if (inactiveDate != null) {
			hrAssetCacheModel.inactiveDate = inactiveDate.getTime();
		}
		else {
			hrAssetCacheModel.inactiveDate = Long.MIN_VALUE;
		}

		return hrAssetCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{hrAssetId=");
		sb.append(getHrAssetId());
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
		sb.append(", hrAssetDefinitionId=");
		sb.append(getHrAssetDefinitionId());
		sb.append(", hrAssetTypeId=");
		sb.append(getHrAssetTypeId());
		sb.append(", serialNumber=");
		sb.append(getSerialNumber());
		sb.append(", inactiveDate=");
		sb.append(getInactiveDate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hr.model.HRAsset");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>hrAssetId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetId());
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
			"<column><column-name>hrAssetDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hrAssetTypeId</column-name><column-value><![CDATA[");
		sb.append(getHrAssetTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serialNumber</column-name><column-value><![CDATA[");
		sb.append(getSerialNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inactiveDate</column-name><column-value><![CDATA[");
		sb.append(getInactiveDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = HRAsset.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			HRAsset.class
		};
	private long _hrAssetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrAssetDefinitionId;
	private long _hrAssetTypeId;
	private String _serialNumber;
	private Date _inactiveDate;
	private transient ExpandoBridge _expandoBridge;
	private HRAsset _escapedModelProxy;
}