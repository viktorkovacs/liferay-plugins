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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.wsrp.NoSuchProducerException;
import com.liferay.wsrp.model.WSRPProducer;
import com.liferay.wsrp.model.impl.WSRPProducerImpl;
import com.liferay.wsrp.model.impl.WSRPProducerModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="WSRPProducerPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPProducerPersistence
 * @see       WSRPProducerUtil
 * @generated
 */
public class WSRPProducerPersistenceImpl extends BasePersistenceImpl<WSRPProducer>
	implements WSRPProducerPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = WSRPProducerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(WSRPProducer wsrpProducer) {
		EntityCacheUtil.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);
	}

	public void cacheResult(List<WSRPProducer> wsrpProducers) {
		for (WSRPProducer wsrpProducer : wsrpProducers) {
			if (EntityCacheUtil.getResult(
						WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
						WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(),
						this) == null) {
				cacheResult(wsrpProducer);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(WSRPProducerImpl.class.getName());
		EntityCacheUtil.clearCache(WSRPProducerImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public WSRPProducer create(long wsrpProducerId) {
		WSRPProducer wsrpProducer = new WSRPProducerImpl();

		wsrpProducer.setNew(true);
		wsrpProducer.setPrimaryKey(wsrpProducerId);

		return wsrpProducer;
	}

	public WSRPProducer remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	public WSRPProducer remove(long wsrpProducerId)
		throws NoSuchProducerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WSRPProducer wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
					new Long(wsrpProducerId));

			if (wsrpProducer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						wsrpProducerId);
				}

				throw new NoSuchProducerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					wsrpProducerId);
			}

			return remove(wsrpProducer);
		}
		catch (NoSuchProducerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public WSRPProducer remove(WSRPProducer wsrpProducer)
		throws SystemException {
		for (ModelListener<WSRPProducer> listener : listeners) {
			listener.onBeforeRemove(wsrpProducer);
		}

		wsrpProducer = removeImpl(wsrpProducer);

		for (ModelListener<WSRPProducer> listener : listeners) {
			listener.onAfterRemove(wsrpProducer);
		}

		return wsrpProducer;
	}

	protected WSRPProducer removeImpl(WSRPProducer wsrpProducer)
		throws SystemException {
		wsrpProducer = toUnwrappedModel(wsrpProducer);

		Session session = null;

		try {
			session = openSession();

			if (wsrpProducer.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(WSRPProducerImpl.class,
						wsrpProducer.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(wsrpProducer);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey());

		return wsrpProducer;
	}

	public WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws SystemException {
		wsrpProducer = toUnwrappedModel(wsrpProducer);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, wsrpProducer, merge);

			wsrpProducer.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
			WSRPProducerImpl.class, wsrpProducer.getPrimaryKey(), wsrpProducer);

		return wsrpProducer;
	}

	protected WSRPProducer toUnwrappedModel(WSRPProducer wsrpProducer) {
		if (wsrpProducer instanceof WSRPProducerImpl) {
			return wsrpProducer;
		}

		WSRPProducerImpl wsrpProducerImpl = new WSRPProducerImpl();

		wsrpProducerImpl.setNew(wsrpProducer.isNew());
		wsrpProducerImpl.setPrimaryKey(wsrpProducer.getPrimaryKey());

		wsrpProducerImpl.setWsrpProducerId(wsrpProducer.getWsrpProducerId());
		wsrpProducerImpl.setCompanyId(wsrpProducer.getCompanyId());
		wsrpProducerImpl.setCreateDate(wsrpProducer.getCreateDate());
		wsrpProducerImpl.setModifiedDate(wsrpProducer.getModifiedDate());
		wsrpProducerImpl.setName(wsrpProducer.getName());
		wsrpProducerImpl.setPortletIds(wsrpProducer.getPortletIds());

		return wsrpProducerImpl;
	}

	public WSRPProducer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPProducer findByPrimaryKey(long wsrpProducerId)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = fetchByPrimaryKey(wsrpProducerId);

		if (wsrpProducer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + wsrpProducerId);
			}

			throw new NoSuchProducerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				wsrpProducerId);
		}

		return wsrpProducer;
	}

	public WSRPProducer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	public WSRPProducer fetchByPrimaryKey(long wsrpProducerId)
		throws SystemException {
		WSRPProducer wsrpProducer = (WSRPProducer)EntityCacheUtil.getResult(WSRPProducerModelImpl.ENTITY_CACHE_ENABLED,
				WSRPProducerImpl.class, wsrpProducerId, this);

		if (wsrpProducer == null) {
			Session session = null;

			try {
				session = openSession();

				wsrpProducer = (WSRPProducer)session.get(WSRPProducerImpl.class,
						new Long(wsrpProducerId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (wsrpProducer != null) {
					cacheResult(wsrpProducer);
				}

				closeSession(session);
			}
		}

		return wsrpProducer;
	}

	public List<WSRPProducer> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(3);

				query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<WSRPProducer> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<WSRPProducer> findByCompanyId(long companyId, int start,
		int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;

				if (obc != null) {
					query = new StringBundler(3 +
							(obc.getOrderByFields().length * 3));
				}
				else {
					query = new StringBundler(3);
				}

				query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				if (obc != null) {
					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
				}

				else {
					query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
				}

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public WSRPProducer findByCompanyId_First(long companyId,
		OrderByComparator obc) throws NoSuchProducerException, SystemException {
		List<WSRPProducer> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPProducer findByCompanyId_Last(long companyId,
		OrderByComparator obc) throws NoSuchProducerException, SystemException {
		int count = countByCompanyId(companyId);

		List<WSRPProducer> list = findByCompanyId(companyId, count - 1, count,
				obc);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchProducerException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public WSRPProducer[] findByCompanyId_PrevAndNext(long wsrpProducerId,
		long companyId, OrderByComparator obc)
		throws NoSuchProducerException, SystemException {
		WSRPProducer wsrpProducer = findByPrimaryKey(wsrpProducerId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBundler query = null;

			if (obc != null) {
				query = new StringBundler(3 +
						(obc.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WSRPPRODUCER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (obc != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);
			}

			else {
				query.append(WSRPProducerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					wsrpProducer);

			WSRPProducer[] array = new WSRPProducerImpl[3];

			array[0] = (WSRPProducer)objArray[0];
			array[1] = (WSRPProducer)objArray[1];
			array[2] = (WSRPProducer)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<WSRPProducer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<WSRPProducer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<WSRPProducer> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<WSRPProducer> list = (List<WSRPProducer>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = null;
				String sql = null;

				if (obc != null) {
					query = new StringBundler(2 +
							(obc.getOrderByFields().length * 3));

					query.append(_SQL_SELECT_WSRPPRODUCER);

					appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, obc);

					sql = query.toString();
				}

				else {
					sql = _SQL_SELECT_WSRPPRODUCER.concat(WSRPProducerModelImpl.ORDER_BY_JPQL);
				}

				Query q = session.createQuery(sql);

				if (obc == null) {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WSRPProducer>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<WSRPProducer>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (WSRPProducer wsrpProducer : findByCompanyId(companyId)) {
			remove(wsrpProducer);
		}
	}

	public void removeAll() throws SystemException {
		for (WSRPProducer wsrpProducer : findAll()) {
			remove(wsrpProducer);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBundler query = new StringBundler(2);

				query.append(_SQL_COUNT_WSRPPRODUCER_WHERE);

				query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

				String sql = query.toString();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WSRPPRODUCER);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.wsrp.model.WSRPProducer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WSRPProducer>> listenersList = new ArrayList<ModelListener<WSRPProducer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<WSRPProducer>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPProducerPersistence")
	protected com.liferay.wsrp.service.persistence.WSRPProducerPersistence wsrpProducerPersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.ResourcePersistence")
	protected com.liferay.portal.service.persistence.ResourcePersistence resourcePersistence;
	@BeanReference(name = "com.liferay.portal.service.persistence.UserPersistence")
	protected com.liferay.portal.service.persistence.UserPersistence userPersistence;
	private static final String _SQL_SELECT_WSRPPRODUCER = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer";
	private static final String _SQL_SELECT_WSRPPRODUCER_WHERE = "SELECT wsrpProducer FROM WSRPProducer wsrpProducer WHERE ";
	private static final String _SQL_COUNT_WSRPPRODUCER = "SELECT COUNT(wsrpProducer) FROM WSRPProducer wsrpProducer";
	private static final String _SQL_COUNT_WSRPPRODUCER_WHERE = "SELECT COUNT(wsrpProducer) FROM WSRPProducer wsrpProducer WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "wsrpProducer.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "wsrpProducer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WSRPProducer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WSRPProducer exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(WSRPProducerPersistenceImpl.class);
}