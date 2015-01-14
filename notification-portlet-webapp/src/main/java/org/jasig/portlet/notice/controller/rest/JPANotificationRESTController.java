package org.jasig.portlet.notice.controller.rest;

import org.codehaus.jackson.JsonNode;
import org.jasig.portlet.notice.rest.EntryDTO;
import org.jasig.portlet.notice.service.jpa.IJpaNotificationRESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
@Controller()
@RequestMapping("/v1/notifications")
public class JPANotificationRESTController {
    @Autowired
    private IJpaNotificationRESTService restService;

    // @PreAuthorize("hasAnyRole(" + Roles.REST_READ + ", " + Roles.REST_WRITE + ")")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<EntryDTO> getNotifications(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        return restService.getNotifications(page, pageSize);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EntryDTO createNotification(HttpServletResponse response, @RequestBody EntryDTO entry) {
        EntryDTO persisted = restService.createNotification(entry);

        String url = getSingleNotificationRESTUrl("" + persisted.getId());
        response.addHeader("Location", url);

        return persisted;
    }


    @RequestMapping(value = "/{notificationId}", method = RequestMethod.GET)
    @ResponseBody
    public EntryDTO getNotification(HttpServletResponse response, @PathVariable("notificationId") long id) {
        EntryDTO notification = restService.getNotification(id);
        if (notification == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }

        return notification;
    }


    @RequestMapping(value = "/{notificationId}/states", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public JsonNode createEvent(@PathVariable("notificationId") String notificationId, HttpServletResponse response) {
        String url = getSingleNotificationRESTUrl("newId") + "/state/newId";
        response.addHeader("Location", url);

        return null;
    }


    private String getSingleNotificationRESTUrl(String id) {
        // TODO:  fix this.
        return "http://localhost:8080/api/v1/notifications/" + id;
    }
}
