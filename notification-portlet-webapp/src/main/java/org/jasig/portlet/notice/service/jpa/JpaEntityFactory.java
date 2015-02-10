package org.jasig.portlet.notice.service.jpa;

import org.dozer.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;


/**
 * @author Josh Helmer, jhelmer.unicon.net
 */
public class JpaEntityFactory implements BeanFactory {
    private Logger log = LoggerFactory.getLogger(JpaEntityFactory.class);


    @Override
    public Object createBean(Object sourceObject, Class<?> sourceClass, String beanId) {
        Object instance = null;
        try {
            Class cls = Thread.currentThread().getContextClassLoader().loadClass(beanId);
            Constructor constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return instance;
    }
}
