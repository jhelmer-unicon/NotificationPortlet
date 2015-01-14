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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/* package-private */ class JpaNotificationDao implements INotificationDao {

    @PersistenceContext
    private EntityManager entityManager;


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
    public JpaEntry find(long id) {
        JpaEntry entry = entityManager.find(JpaEntry.class, id);
        return entry;
    }


    @Override
    public JpaEntry create(JpaEntry entry) {
        entityManager.persist(entry);

        // grrr.  populate the auto-generated id...
        entityManager.flush();
        entityManager.refresh(entry);

        return entry;
    }
}
