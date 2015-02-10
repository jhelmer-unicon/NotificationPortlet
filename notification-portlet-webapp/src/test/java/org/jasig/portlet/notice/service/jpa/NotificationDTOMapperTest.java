package org.jasig.portlet.notice.service.jpa;

import org.dozer.DozerBeanMapper;
import org.jasig.portlet.notice.rest.AttributeDTO;
import org.jasig.portlet.notice.rest.EntryDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public class NotificationDTOMapperTest {
    private DozerBeanMapper mapper;


    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(
                "mapping/jpa-mappings.xml"
        ));
    }


    @Test
    public void testMapJpaEntry() {
        AttributeDTO attr1 = new AttributeDTO();
        attr1.setName("name1");
        attr1.setValues(Arrays.asList("val1", "val2"));

        AttributeDTO attr2 = new AttributeDTO();
        attr2.setName("name2");
        attr2.setValues(Arrays.asList("val3", "val4", "val5"));

        Set<AttributeDTO> attrs = new HashSet<>();
        attrs.add(attr1);
        attrs.add(attr2);

        EntryDTO dto = new EntryDTO();
        dto.setId(1);
        dto.setTitle("title");
        dto.setImage("image");
        dto.setBody("body");
        dto.setAbs("abs");
        dto.setAttributes(attrs);

        JpaEntry jpa = mapper.map(dto, JpaEntry.class);

        assertThat(jpa.getId(), is(dto.getId()));
        assertThat(jpa.getTitle(), is(dto.getTitle()));
        assertThat(jpa.getImage(), is(dto.getImage()));
        assertThat(jpa.getAbs(), is(dto.getAbs()));
        assertThat(jpa.getAttributes().size(), is(2));

        Iterator<JpaAttribute> attrIt = jpa.getAttributes().iterator();
        JpaAttribute jpaAttr = attrIt.next();
        assertThat(jpaAttr.getName(), is("name1"));
        assertThat(jpaAttr.getValues().size(), is(2));
        assertThat(jpaAttr.getValues(), containsInAnyOrder("val1", "val2"));

        jpaAttr = attrIt.next();
        assertThat(jpaAttr.getName(), is("name2"));
        assertThat(jpaAttr.getValues().size(), is(3));
        assertThat(jpaAttr.getValues(), containsInAnyOrder("val3", "val4", "val5"));
    }
}
