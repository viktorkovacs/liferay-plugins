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

package com.liferay.wsrp.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.InvokerPortlet;
import com.liferay.portlet.PortletInstanceFactoryUtil;
import com.liferay.wsrp.WSRPConsumerPortletHandleException;
import com.liferay.wsrp.WSRPConsumerPortletNameException;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.portlet.ConsumerFriendlyURLMapper;
import com.liferay.wsrp.portlet.ConsumerPortlet;
import com.liferay.wsrp.service.ClpSerializer;
import com.liferay.wsrp.service.base.WSRPConsumerPortletLocalServiceBaseImpl;
import com.liferay.wsrp.util.ExtensionUtil;
import com.liferay.wsrp.util.LocalizedStringUtil;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.types.MarkupType;
import oasis.names.tc.wsrp.v2.types.ParameterDescription;
import oasis.names.tc.wsrp.v2.types.PortletDescription;

import org.apache.axis.message.MessageElement;

/**
 * <a href="WSRPConsumerPortletLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerPortletLocalServiceImpl
	extends WSRPConsumerPortletLocalServiceBaseImpl {

	public WSRPConsumerPortlet addWSRPConsumerPortlet(
			long wsrpConsumerId, String name, String portletHandle,
			String userToken)
		throws PortalException, SystemException {

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerId);
		Date now = new Date();

		validate(name, portletHandle);

		long wsrpConsumerPortletId = CounterLocalServiceUtil.increment();

		WSRPConsumerPortlet wsrpConsumerPortlet =
			wsrpConsumerPortletPersistence.create(wsrpConsumerPortletId);

		wsrpConsumerPortlet.setCompanyId(wsrpConsumer.getCompanyId());
		wsrpConsumerPortlet.setCreateDate(now);
		wsrpConsumerPortlet.setModifiedDate(now);
		wsrpConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
		wsrpConsumerPortlet.setName(name);
		wsrpConsumerPortlet.setPortletHandle(portletHandle);

		wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, false);

		initWSRPConsumerPortlet(wsrpConsumerPortlet, userToken);

		return wsrpConsumerPortlet;
	}

	public void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws PortalException, SystemException {

		WSRPConsumerPortlet wsrpConsumerPortlet =
			wsrpConsumerPortletPersistence.findByPrimaryKey(
				wsrpConsumerPortletId);

		deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public void deleteWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		destroyWSRPConsumerPortlet(wsrpConsumerPortlet);

		wsrpConsumerPortletPersistence.remove(wsrpConsumerPortlet);
	}

	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws PortalException, SystemException {

		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			wsrpConsumerPortletPersistence.findByWsrpConsumerId(wsrpConsumerId);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
		}
	}

	public void destroyWSRPConsumerPortlets()
		throws PortalException, SystemException {

		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			wsrpConsumerPortletPersistence.findAll();

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			destroyWSRPConsumerPortlet(wsrpConsumerPortlet);
		}
	}

	public List<WSRPConsumerPortlet> getWSRPConsumerPortlets(
			long wsrpConsumerId, int start, int end)
		throws SystemException {

		return wsrpConsumerPortletPersistence.findByWsrpConsumerId(
			wsrpConsumerId, start, end);
	}

	public int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws SystemException {

		return wsrpConsumerPortletPersistence.countByWsrpConsumerId(
			wsrpConsumerId);
	}

	public void initWSRPConsumerPortlets()
		throws PortalException, SystemException {

		for (WSRPConsumerPortlet wsrpConsumerPortlet :
				wsrpConsumerPortletPersistence.findAll()) {

			initWSRPConsumerPortlet(wsrpConsumerPortlet, null);
		}
	}

	public WSRPConsumerPortlet updateWSRPConsumerPortlet(
			long wsrpConsumerPortletId, String name)
		throws PortalException, SystemException {

		validate(name);

		WSRPConsumerPortlet wsrpConsumerPortlet =
			wsrpConsumerPortletPersistence.findByPrimaryKey(
				wsrpConsumerPortletId);

		wsrpConsumerPortlet.setModifiedDate(new Date());
		wsrpConsumerPortlet.setName(name);

		wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, false);

		return wsrpConsumerPortlet;
	}

	protected void addPortletExtraInfo(
		Portlet portlet, PortletApp portletApp,
		PortletDescription portletDescription, String title) {

		MarkupType[] markupTypes = portletDescription.getMarkupTypes();

		for (MarkupType markupType : markupTypes) {
			Set<String> mimeTypePortletModes = new HashSet<String>();

			for (String portletMode : markupType.getModes()) {
				portletMode = portletMode.toLowerCase();

				if (portletMode.startsWith("wsrp:")) {
					portletMode = portletMode.substring(5);
				}

				mimeTypePortletModes.add(portletMode);
			}

			portlet.getPortletModes().put(
				markupType.getMimeType(), mimeTypePortletModes);

			Set<String> mimeTypeWindowStates = new HashSet<String>();

			for (String windowState : markupType.getWindowStates()) {
				windowState = windowState.toLowerCase();

				if (windowState.startsWith("wsrp:")) {
					windowState = windowState.substring(5);
				}

				mimeTypeWindowStates.add(windowState);
			}

			portlet.getWindowStates().put(
				markupType.getMimeType(), mimeTypeWindowStates);
		}

		String shortTitle = LocalizedStringUtil.getLocalizedStringValue(
			portletDescription.getShortTitle(), title);
		String keywords = StringUtil.merge(
			LocalizedStringUtil.getLocalizedStringValues(
				portletDescription.getKeywords()),
			StringPool.SPACE);
		String description = LocalizedStringUtil.getLocalizedStringValue(
			portletDescription.getShortTitle());

		PortletInfo portletInfo = new PortletInfo(
			title, shortTitle, keywords, description);

		portlet.setPortletInfo(portletInfo);

		portlet.setFriendlyURLMapperClass(
			ConsumerFriendlyURLMapper.class.getName());

		ParameterDescription[] parameterDescriptions =
			portletDescription.getNavigationalPublicValueDescriptions();

		if (parameterDescriptions != null) {
			for (ParameterDescription parameterDescription :
					parameterDescriptions) {

				QName[] qNames = parameterDescription.getNames();

				if ((qNames == null) || (qNames.length == 0)) {
					continue;
				}

				com.liferay.portal.kernel.xml.QName qName =	getQName(qNames[0]);

				String identifier = parameterDescription.getIdentifier();

				portletApp.addPublicRenderParameter(identifier, qName);

				portlet.addPublicRenderParameter(
					portletApp.getPublicRenderParameter(identifier));
			}
		}

		QName[] handledEvents = portletDescription.getHandledEvents();

		if (handledEvents != null) {
			for (QName handledEvent : handledEvents) {
				portlet.addProcessingEvent(getQName(handledEvent));
			}
		}

		QName[] publishedEvents = portletDescription.getPublishedEvents();

		if (publishedEvents != null) {
			for (QName publishedEvent : publishedEvents) {
				portlet.addPublishingEvent(getQName(publishedEvent));
			}
		}

		MessageElement[] messageElements = ExtensionUtil.getMessageElements(
			portletDescription.getExtensions());

		if (messageElements != null) {
			for (MessageElement messageElement : messageElements) {
				setExtension(portlet, messageElement);
			}
		}
	}

	protected void destroyWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		try {
			Portlet portlet = _portletsPool.get(
				wsrpConsumerPortlet.getWsrpConsumerPortletId());

			if (portlet == null) {
				return;
			}

			WSRPConsumer wsrpConsumer =
				wsrpConsumerPersistence.findByPrimaryKey(
					wsrpConsumerPortlet.getWsrpConsumerId());

			WSRPConsumerManagerFactory.destroyWSRPConsumerManager(
				wsrpConsumer.getUrl());

			PortletLocalServiceUtil.destroyRemotePortlet(portlet);

			PortletInstanceFactoryUtil.destroy(portlet);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected Portlet getPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet, String userToken)
		throws Exception {

		Portlet portlet = _portletsPool.get(
			wsrpConsumerPortlet.getWsrpConsumerPortletId());

		if (portlet != null) {
			return portlet;
		}

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(
				wsrpConsumer, userToken);

		PortletDescription portletDescription =
			wsrpConsumerManager.getPortletDescription(
				wsrpConsumerPortlet.getPortletHandle());

		StringBuilder sb = new StringBuilder();

		sb.append(ConsumerPortlet.PORTLET_NAME_PREFIX);
		sb.append(wsrpConsumerPortlet.getCompanyId());
		sb.append(StringPool.UNDERLINE);
		sb.append(wsrpConsumerPortlet.getWsrpConsumerPortletId());

		String portletId = PortalUtil.getJsSafePortletId(sb.toString());

		portlet = PortletLocalServiceUtil.newPortlet(
			wsrpConsumerPortlet.getCompanyId(), portletId);

		portlet.setTimestamp(System.currentTimeMillis());

		PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(
			ClpSerializer.SERVLET_CONTEXT_NAME);

		portlet.setPortletApp(portletApp);

		portlet.setPortletName(portletId);
		portlet.setDisplayName(portletId);
		portlet.setPortletClass(ConsumerPortlet.class.getName());

		Map<String, String> initParams = portlet.getInitParams();

		initParams.put(
			InvokerPortlet.INIT_INVOKER_PORTLET_NAME, _CONSUMER_PORTLET_NAME);

		if (portletDescription != null) {
			addPortletExtraInfo(
				portlet, portletApp, portletDescription,
				wsrpConsumerPortlet.getName());
		}

		_portletsPool.put(
			wsrpConsumerPortlet.getWsrpConsumerPortletId(), portlet);

		PortletBag portletBag = PortletBagPool.get(_CONSUMER_PORTLET_ID);

		portletBag = (PortletBag)portletBag.clone();

		portletBag.setPortletName(portletId);
		portletBag.setPortletInstance(new ConsumerPortlet());

		PortletBagPool.put(portletId, portletBag);

		return portlet;
	}

	protected String getProxyURL(String url) {
		return "/proxy?url=" + HttpUtil.encodeURL(url);
	}

	protected com.liferay.portal.kernel.xml.QName getQName(QName qName) {
		String localPart = qName.getLocalPart();
		String prefix = qName.getPrefix();
		String namespaceURI = qName.getNamespaceURI();

		Namespace namespace = SAXReaderUtil.createNamespace(
			prefix, namespaceURI);

		return SAXReaderUtil.createQName(localPart, namespace);
	}

	protected void initWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet, String userToken)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(wsrpConsumerPortlet, userToken);

			PortletLocalServiceUtil.deployRemotePortlet(
				portlet, _WSRP_CATEGORY);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected void setExtension(
		Portlet portlet, MessageElement messageElement) {

		String name = messageElement.getName();
		String value = messageElement.getValue();

		if (name.equals("css-class-wrapper")) {
			portlet.setCssClassWrapper(value);
		}
		else if (name.equals("footer-portal-css")) {
			portlet.getFooterPortalCss().add(getProxyURL(value));
		}
		else if (name.equals("footer-portal-javascript")) {
			portlet.getFooterPortalJavaScript().add(getProxyURL(value));
		}
		else if (name.equals("footer-portlet-css")) {
			portlet.getFooterPortletCss().add(getProxyURL(value));
		}
		else if (name.equals("footer-portlet-javascript")) {
			portlet.getFooterPortletJavaScript().add(getProxyURL(value));
		}
		else if (name.equals("header-portal-css")) {
			portlet.getHeaderPortalCss().add(getProxyURL(value));
		}
		else if (name.equals("header-portal-javascript")) {
			portlet.getHeaderPortalJavaScript().add(getProxyURL(value));
		}
		else if (name.equals("header-portlet-css")) {
			portlet.getHeaderPortletCss().add(getProxyURL(value));
		}
		else if (name.equals("header-portlet-javascript")) {
			portlet.getHeaderPortletJavaScript().add(getProxyURL(value));
		}
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new WSRPConsumerPortletNameException();
		}
	}

	protected void validate(String name, String portletHandle)
		throws PortalException {

		validate(name);

		if (Validator.isNull(portletHandle)) {
			throw new WSRPConsumerPortletHandleException();
		}
	}

	private static final String _CONSUMER_PORTLET_ID = "2_WAR_wsrpportlet";

	private static final String _CONSUMER_PORTLET_NAME = "2";

	private static final String _WSRP_CATEGORY = "category.wsrp";

	private static Map<Long, Portlet> _portletsPool =
		new ConcurrentHashMap<Long, Portlet>();

}