package org.jasig.portlet.notice.service.jpa;

import org.codehaus.jackson.JsonNode;
import org.jasig.portlet.notice.rest.EntryDTO;

import java.util.List;

/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public interface IJpaNotificationRESTService {
    List<EntryDTO> getNotifications(Integer page, Integer pageSize);
    EntryDTO getNotification(long id);

    EntryDTO createNotification(EntryDTO notification);
}
