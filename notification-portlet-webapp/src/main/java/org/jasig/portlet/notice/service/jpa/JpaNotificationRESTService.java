package org.jasig.portlet.notice.service.jpa;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.jasig.portlet.notice.rest.AttributeDTO;
import org.jasig.portlet.notice.rest.EntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
@Service
public class JpaNotificationRESTService implements IJpaNotificationRESTService {
    @Autowired
    private INotificationDao notificationDao;
    @Autowired
    private INotificationDTOMapper notificationMapper;


    @Override
    @Transactional
    public List<EntryDTO> getNotifications(Integer page, Integer pageSize) {
        List<JpaEntry> entries = notificationDao.list(page, pageSize);

//        return toEntryList(entries);
        return notificationMapper.toEntryList(entries);
    }


    @Override
    @Transactional
    public EntryDTO getNotification(long id) {
        JpaEntry entry = notificationDao.getEntry(id);
        if (entry == null) {
            return null;
        }

//        return toEntry(entry);
        return notificationMapper.toEntry(entry);
    }


    @Override
    @Transactional
    public EntryDTO createNotification(EntryDTO entry) {
//        JpaEntry jpaEntry = toJpaEntry(entry);
        JpaEntry jpaEntry = notificationMapper.toJpaEntry(entry);

        JpaEntry inserted = notificationDao.createOrUpdateEntry(jpaEntry);

//        return toEntry(inserted);
        return notificationMapper.toEntry(inserted);
    }


//    private List<EntryDTO> toEntryList(List<JpaEntry> entries) {
//        return Lists.transform(entries, new Function<JpaEntry, EntryDTO>() {
//            @Override
//            public EntryDTO apply(JpaEntry jpaEntry) {
//                return toEntry(jpaEntry);
//            }
//        });
//    }
//
//
//    private Set<JpaAttribute> toJpaAttributeSet(Set<AttributeDTO> attributes) {
//        if (attributes == null) {
//            return Collections.emptySet();
//        }
//
//        Set<JpaAttribute> attrs = new HashSet<JpaAttribute>();
//        for (AttributeDTO dto : attributes) {
//            JpaAttribute jpa = toJpaAttribute(dto);
//            attrs.add(jpa);
//        }
//
//        return attrs;
//    }
//
//
//    private Set<AttributeDTO> toAttributeSet(Set<JpaAttribute> attributes) {
//        if (attributes == null) {
//            return Collections.emptySet();
//        }
//
//        Set<AttributeDTO> attrs = new HashSet<>();
//        for (JpaAttribute jpa : attributes) {
//            AttributeDTO dto = toAttribute(jpa);
//            attrs.add(dto);
//        }
//
//        return attrs;
//    }
//
//
//    private JpaEntry toJpaEntry(EntryDTO entry) {
//        JpaEntry jpa = new JpaEntry();
//
//        jpa.setId(entry.getId());
//        jpa.setTitle(entry.getTitle());
//        jpa.setUrl(entry.getUrl());
//        jpa.setLinkText(entry.getLinkText());
//        jpa.setPriority(entry.getPriority());
//        jpa.setDueDate(entry.getDueDate());
//        jpa.setImage(entry.getImage());
//        jpa.setAbs((entry.getAbs()));
//        jpa.setBody(entry.getBody());
//
//        jpa.setAttributes(toJpaAttributeSet(entry.getAttributes()));
//
//        return jpa;
//    }
//
//
//    private EntryDTO toEntry(JpaEntry entry) {
//        EntryDTO dto = new EntryDTO();
//
//        dto.setId(entry.getId());
//        dto.setTitle(entry.getTitle());
//        dto.setUrl(entry.getUrl());
//        dto.setLinkText(entry.getLinkText());
//        dto.setPriority(entry.getPriority());
//        dto.setDueDate(entry.getDueDate());
//        dto.setImage(entry.getImage());
//        dto.setAbs((entry.getAbs()));
//        dto.setBody(entry.getBody());
//
//        dto.setAttributes(toAttributeSet(entry.getAttributes()));
//
//        return dto;
//    }
//
//
//    private JpaAttribute toJpaAttribute(AttributeDTO attr) {
//        JpaAttribute jpa = new JpaAttribute();
//        jpa.setId(attr.getId());
//        jpa.setName(attr.getName());
//
//        if (attr.getValues() != null) {
//            jpa.setValues(attr.getValues());
//        }
//
//        return jpa;
//    }
//
//
//    private AttributeDTO toAttribute(JpaAttribute attr) {
//        AttributeDTO dto = new AttributeDTO();
//
//        dto.setId(attr.getId());
//        dto.setName(attr.getName());
//        dto.setValues(attr.getValues());
//
//        return dto;
//    }
}
