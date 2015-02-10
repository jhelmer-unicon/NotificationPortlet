package org.jasig.portlet.notice.rest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public class EntryDTO implements Serializable {
    private static final long serialVersionUid = 1l;

    private long id;
    private String title;
    private String url;
    private String linkText;
    private int priority;
    private Timestamp dueDate;
    private String image;
    private String abs;
    private String body;
    private Set<AttributeDTO> attributes = Collections.emptySet();
//    private Set<JpaAction> actions = Collections.emptySet();
//    private Set<JpaAction> events = Collections.emptySet();
//    private Set<JpaAction> addressees = Collections.emptySet();


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getLinkText() {
        return linkText;
    }


    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }


    public int getPriority() {
        return priority;
    }


    public void setPriority(int priority) {
        this.priority = priority;
    }


    public Timestamp getDueDate() {
        return dueDate;
    }


    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getAbs() {
        return abs;
    }


    public void setAbs(String abs) {
        this.abs = abs;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public Set<AttributeDTO> getAttributes() {
        return attributes;
    }


    public void setAttributes(Set<AttributeDTO> attributes) {
        this.attributes = attributes;
    }
}
