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

package com.liferay.knowledgebase.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="ArticleServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link ArticleService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ArticleService
 * @generated
 */
public class ArticleServiceUtil {
	public static com.liferay.knowledgebase.model.Article addArticle(
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description, int priority,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addArticle(parentResourcePrimKey, title, content,
			description, priority, serviceContext);
	}

	public static void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteArticle(resourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.Article getArticle(
		long resourcePrimKey, double version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getArticle(resourcePrimKey, version);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getArticles(resourcePrimKey, start, end, orderByComparator);
	}

	public static int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getArticlesCount(resourcePrimKey);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getCompanyArticles(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCompanyArticles(companyId, start, end, orderByComparator);
	}

	public static int getCompanyArticlesCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyArticlesCount(companyId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupArticles(groupId, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, long parentResourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupArticles(groupId, parentResourcePrimKey, start,
			end, orderByComparator);
	}

	public static int getGroupArticlesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupArticlesCount(groupId);
	}

	public static int getGroupArticlesCount(long groupId,
		long parentResourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupArticlesCount(groupId, parentResourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestArticle(resourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.Article updateArticle(
		long resourcePrimKey, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, int priority,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateArticle(resourcePrimKey, parentResourcePrimKey,
			title, content, description, priority, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ArticleService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					ArticleService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					ArticleService.class.getName(), portletClassLoader);

			_service = new ArticleServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(ArticleService service) {
		_service = service;
	}

	private static ArticleService _service;
}