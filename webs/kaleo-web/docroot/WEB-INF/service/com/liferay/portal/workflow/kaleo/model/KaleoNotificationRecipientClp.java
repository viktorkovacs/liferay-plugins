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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="KaleoNotificationRecipientClp.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoNotificationRecipientClp extends BaseModelImpl<KaleoNotificationRecipient>
	implements KaleoNotificationRecipient {
	public KaleoNotificationRecipientClp() {
	}

	public long getPrimaryKey() {
		return _kaleoNotificationRecipientId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoNotificationRecipientId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoNotificationRecipientId);
	}

	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipientId;
	}

	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_kaleoNotificationRecipientId = kaleoNotificationRecipientId;
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
		return _userName;
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

	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationId = kaleoNotificationId;
	}

	public String getRecipientClassName() {
		return _recipientClassName;
	}

	public void setRecipientClassName(String recipientClassName) {
		_recipientClassName = recipientClassName;
	}

	public long getRecipientClassPK() {
		return _recipientClassPK;
	}

	public void setRecipientClassPK(long recipientClassPK) {
		_recipientClassPK = recipientClassPK;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public KaleoNotificationRecipient toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (KaleoNotificationRecipient)Proxy.newProxyInstance(KaleoNotificationRecipient.class.getClassLoader(),
				new Class[] { KaleoNotificationRecipient.class },
				new AutoEscapeBeanHandler(this));
		}
	}

	public Object clone() {
		KaleoNotificationRecipientClp clone = new KaleoNotificationRecipientClp();

		clone.setKaleoNotificationRecipientId(getKaleoNotificationRecipientId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoNotificationId(getKaleoNotificationId());
		clone.setRecipientClassName(getRecipientClassName());
		clone.setRecipientClassPK(getRecipientClassPK());
		clone.setAddress(getAddress());

		return clone;
	}

	public int compareTo(KaleoNotificationRecipient kaleoNotificationRecipient) {
		int value = 0;

		if (getKaleoNotificationRecipientId() < kaleoNotificationRecipient.getKaleoNotificationRecipientId()) {
			value = -1;
		}
		else if (getKaleoNotificationRecipientId() > kaleoNotificationRecipient.getKaleoNotificationRecipientId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KaleoNotificationRecipientClp kaleoNotificationRecipient = null;

		try {
			kaleoNotificationRecipient = (KaleoNotificationRecipientClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = kaleoNotificationRecipient.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{kaleoNotificationRecipientId=");
		sb.append(getKaleoNotificationRecipientId());
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
		sb.append(", kaleoNotificationId=");
		sb.append(getKaleoNotificationId());
		sb.append(", recipientClassName=");
		sb.append(getRecipientClassName());
		sb.append(", recipientClassPK=");
		sb.append(getRecipientClassPK());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoNotificationRecipientId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationRecipientId());
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
			"<column><column-name>kaleoNotificationId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNotificationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassName</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recipientClassPK</column-name><column-value><![CDATA[");
		sb.append(getRecipientClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoNotificationRecipientId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoNotificationId;
	private String _recipientClassName;
	private long _recipientClassPK;
	private String _address;
}