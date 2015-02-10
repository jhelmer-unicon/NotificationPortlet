/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jasig.portlet.notice.service.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.Validate;
import org.jasig.portlet.notice.NotificationState;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This DAO class handles raw CRUD operations for the JPA-flavor notifications.
 *
 * @author drewwills
 */
@Repository
/* package-private */ class JpaNotificationDao implements INotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Search for a JpaEntry with the specified Id. If the entry exists in the
     * persistence context, it is returned;  otherwise, null is returned.
     */
    @Override
    public JpaEntry getEntry(long entryId) {
        Validate.isTrue(entryId > 0, "Invalid entryId:  " + entryId);

        JpaEntry rslt = entityManager.find(JpaEntry.class, entryId);
        return rslt;
    }

    @Override
    public List<JpaEntry> list(Integer page, Integer pageSize) {
        TypedQuery<JpaEntry> query = entityManager.createNamedQuery("JpaEntry.getAll", JpaEntry.class);

        if (page != null && pageSize != null) {
            query.setFirstResult(page * pageSize);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

    @Override
    @Transactional
    public JpaEntry createOrUpdateEntry(JpaEntry entry) {
        Validate.notNull(entry, "Argument 'entry' cannot be null");

        if (entry.getId() == 0) {
            entityManager.persist(entry);

            entityManager.flush();
            entityManager.refresh(entry);
        } else {
            entityManager.merge(entry);
        }

        return entry;
    }

    @Override
    @Transactional
    public void removeEntry(JpaEntry entry) {
        Validate.notNull(entry, "Argument 'entry' cannot be null");

        JpaEntry y = entityManager.merge(entry);  // Insures that cascades will be handled properly
        entityManager.remove(y);
    }

    @Override
    public Set<JpaEntry> getEntriesByRecipient(String username) {
        Validate.notEmpty(username, "Argument 'username' cannot be empty");

        final String jpql = "SELECT e FROM JpaEntry e WHERE e.addressees = ANY ("
                + "SELECT a FROM JpaAddressee a WHERE a.recipient = ANY ("
                + "SELECT r FROM JpaRecipent r WHERE r.username = :username))";
        TypedQuery<JpaEntry> query = entityManager.createQuery(jpql, JpaEntry.class);
        query.setParameter("username", username);
        List<JpaEntry> rslt = query.getResultList();
        return new HashSet<JpaEntry>(rslt);
    }

    @Override
    public Set<JpaEntry> getEntriesByRecipientByStatus(String username,
            Set<NotificationState> include, Set<NotificationState> exclude) {
        Validate.notEmpty(username, "Argument 'username' cannot be empty");

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
