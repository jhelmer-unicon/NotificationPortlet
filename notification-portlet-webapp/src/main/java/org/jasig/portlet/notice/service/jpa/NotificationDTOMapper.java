package org.jasig.portlet.notice.service.jpa;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.dozer.Mapper;
import org.jasig.portlet.notice.rest.EntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
@Service
public class NotificationDTOMapper implements INotificationDTOMapper {
    @Autowired
    private Mapper dozerBeanMapper;


    @Override
    public List<EntryDTO> toEntryList(List<JpaEntry> entries) {
        return Lists.transform(entries, new Function<JpaEntry, EntryDTO>() {
            @Override
            public EntryDTO apply(JpaEntry jpaEntry) {
                return toEntry(jpaEntry);
            }
        });
    }


    @Override
    public List<JpaEntry> toJpaEntryList(List<EntryDTO> entries) {
        return Lists.transform(entries, new Function<EntryDTO, JpaEntry>() {
            @Override
            public JpaEntry apply(EntryDTO entry) {
                return toJpaEntry(entry);
            }
        });
    }


    @Override
    public EntryDTO toEntry(JpaEntry entry) {
        return dozerBeanMapper.map(entry, EntryDTO.class);
    }


    @Override
    public JpaEntry toJpaEntry(EntryDTO entry) {
        return dozerBeanMapper.map(entry, JpaEntry.class);
    }
}
