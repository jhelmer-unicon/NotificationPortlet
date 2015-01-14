package org.jasig.portlet.notice.service.jpa;

import org.dozer.DozerBeanMapper;
import org.jasig.portlet.notice.rest.EntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
@Service
public class JpaNotificationRESTService implements IJpaNotificationRESTService {
    @Autowired
    private INotificationDao notificationDao;


    @Override
    @Transactional
    public List<EntryDTO> getNotifications(Integer page, Integer pageSize) {
        List<JpaEntry> entries = notificationDao.list(page, pageSize);

        return toEntryList(entries);
    }


    @Override
    @Transactional
    public EntryDTO getNotification(long id) {
        JpaEntry entry = notificationDao.find(id);
        if (entry == null) {
            return null;
        }

        return toEntry(entry);
    }


    @Override
    @Transactional
    public EntryDTO createNotification(EntryDTO entry) {
        JpaEntry jpaEntry = toJpaEntry(entry);

        JpaEntry inserted = notificationDao.create(jpaEntry);

        return toEntry(inserted);
    }

    private List<EntryDTO> toEntryList(List<JpaEntry> entries) {
        List<EntryDTO> out = new ArrayList<>(entries.size());
        for (JpaEntry entry : entries) {
            EntryDTO dto = toEntry(entry);
            out.add(dto);
        }

        return out;
    }


    private JpaEntry toJpaEntry(EntryDTO entry) {
        JpaEntry jpa = new JpaEntry();

        jpa.setId(entry.getId());
        jpa.setTitle(entry.getTitle());
        jpa.setUrl(entry.getUrl());
        jpa.setLinkText(entry.getLinkText());
        jpa.setPriority(entry.getPriority());
        jpa.setDueDate(entry.getDueDate());
        jpa.setImage(entry.getImage());
        jpa.setAbs((entry.getAbs()));
        jpa.setBody(entry.getBody());

        return jpa;
    }


    private EntryDTO toEntry(JpaEntry entry) {
        EntryDTO dto = new EntryDTO();

        dto.setId(entry.getId());
        dto.setTitle(entry.getTitle());
        dto.setUrl(entry.getUrl());
        dto.setLinkText(entry.getLinkText());
        dto.setPriority(entry.getPriority());
        dto.setDueDate(entry.getDueDate());
        dto.setImage(entry.getImage());
        dto.setAbs((entry.getAbs()));
        dto.setBody(entry.getBody());

        return dto;
    }
}
