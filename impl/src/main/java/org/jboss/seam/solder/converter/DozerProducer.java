package org.jboss.seam.solder.converter;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class DozerProducer {

    @Produces
    @Singleton
    public Mapper createDozerMapper() {
        return new DozerBeanMapper();
    }
}
