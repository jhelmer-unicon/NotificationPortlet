package org.jasig.portlet.notice.service.jpa;

import org.jasig.portlet.notice.rest.EntryDTO;

import java.util.List;

/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public interface INotificationDTOMapper {
    List<EntryDTO> toEntryList(List<JpaEntry> entries);
    List<JpaEntry> toJpaEntryList(List<EntryDTO> entries);

    EntryDTO toEntry(JpaEntry entry);
    JpaEntry toJpaEntry(EntryDTO entry);
}
