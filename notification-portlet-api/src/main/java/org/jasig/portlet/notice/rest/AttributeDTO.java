package org.jasig.portlet.notice.rest;

import java.io.Serializable;
import java.util.List;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public class AttributeDTO implements Serializable {
    private static final long serialVersionUid = 1l;
    private long id;
    private String name;
    private List<String> values;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<String> getValues() {
        return values;
    }


    public void setValues(List<String> values) {
        this.values = values;
    }
}
