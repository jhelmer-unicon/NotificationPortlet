package org.jasig.portlet.notice.rest;

import java.io.Serializable;

/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public class AddresseeDTO implements Serializable {
    private static final long serialVersionUid = 1l;

    private long id;
    private long entryId;
    private String name;
    private RecipientType type;
    // private Set<JpaRecipient> recipients = new HashSet<JpaRecipient>();


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getEntryId() {
        return entryId;
    }


    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public RecipientType getType() {
        return type;
    }


    public void setType(RecipientType type) {
        this.type = type;
    }


//    public Set<JpaRecipient> getRecipients() {
//        return recipients;
//    }
//
//
//    public void setRecipients(Set<JpaRecipient> recipients) {
//        this.recipients = recipients;
//    }
}
