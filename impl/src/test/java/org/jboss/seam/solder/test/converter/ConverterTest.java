package org.jboss.seam.solder.test.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.solder.bean.Beans;
import org.jboss.seam.solder.bean.defaultbean.DefaultBeanExtension;
import org.jboss.seam.solder.bean.generic.GenericBeanExtension;
import org.jboss.seam.solder.beanManager.BeanManagerAware;
import org.jboss.seam.solder.converter.Converter;
import org.jboss.seam.solder.core.Client;
import org.jboss.seam.solder.core.CoreExtension;
import org.jboss.seam.solder.el.Resolver;
import org.jboss.seam.solder.literal.DefaultLiteral;
import org.jboss.seam.solder.logging.Category;
import org.jboss.seam.solder.logging.TypedMessageLoggerExtension;
import org.jboss.seam.solder.logging.internal.Logger;
import org.jboss.seam.solder.messages.Messages;
import org.jboss.seam.solder.reflection.Synthetic;
import org.jboss.seam.solder.resourceLoader.ResourceLoader;
import org.jboss.seam.solder.serviceHandler.ServiceHandlerExtension;
import org.jboss.seam.solder.support.SolderMessages;
import org.jboss.seam.solder.test.properties.ClassToIntrospect;
import org.jboss.seam.solder.unwraps.UnwrapsExtension;
import org.jboss.seam.solder.util.Sortable;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ConverterTest {

    @Inject
    private Converter<String, Integer> converter;

    @Test
    public void testConverterInjection() throws Exception {
        assertNotNull(converter);
    }

    @Test
    public void testConversion() throws Exception {
        Integer result = converter.convert("123");
        assertNotNull(result);
        assertEquals(123, result.intValue());
    }

    /**
     * Seam Solder TODO: there must be a better way to get Solder jar
     */
    @Deployment
    public static JavaArchive createSeamSolder() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "solder.jar");

        jar.addPackages(true, Beans.class.getPackage()); // .bean
        jar.addPackages(true, BeanManagerAware.class.getPackage()); // .beanManager
        jar.addPackages(true, Converter.class.getPackage()); // .converter
        jar.addPackages(true, Client.class.getPackage()); // .core
        jar.addPackages(true, Resolver.class.getPackage()); // .el
        jar.addPackages(true, DefaultLiteral.class.getPackage()); // .literal
        jar.addPackages(true, Category.class.getPackage()); // .log
        jar.addPackages(true, Messages.class.getPackage()); // .logging
        jar.addPackages(true, ClassToIntrospect.class.getPackage()); // .properties
        jar.addPackages(true, SolderMessages.class.getPackage()); // .messages
        jar.addPackages(true, Synthetic.class.getPackage()); // .reflection
        jar.addPackages(true, ResourceLoader.class.getPackage()); // .resourceLoader
        jar.addPackages(true, ServiceHandlerExtension.class.getPackage()); // .serviceHandler
        jar.addPackages(true, UnwrapsExtension.class.getPackage()); // .unwraps
        jar.addPackages(true, Sortable.class.getPackage()); // .util
        jar.addPackages(false, Logger.class.getPackage()); // org.jboss.seam.solder.logging.internal

        jar.addAsServiceProvider(Extension.class, GenericBeanExtension.class, DefaultBeanExtension.class, CoreExtension.class,
                UnwrapsExtension.class, TypedMessageLoggerExtension.class, ServiceHandlerExtension.class);
        jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        return jar;
    }

}
