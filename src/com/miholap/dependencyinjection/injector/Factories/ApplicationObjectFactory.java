package com.miholap.dependencyinjection.injector.Factories;

import com.miholap.dependencyinjection.Util;
import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.Definition;
import com.miholap.dependencyinjection.injector.Context.ValueKinds;

import java.lang.reflect.Method;
import java.util.Map;

public class ApplicationObjectFactory extends ObjectFactory {

    public ApplicationObjectFactory(Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> beanDefinitionReaders) {
        super(beanDefinitionReaders);
    }

    @Override
    public Object getBean(String beanName) {
        Definition definition = getDefinitionByBeanName(beanName);
        final Class objectClass = definition.getObjectClass();
        final Map<String,Definition.Property> properties = definition.getProperties();

        try {
            final Object initializedObject = objectClass.newInstance();
            if (properties!= null && properties.size() > 0 ) {
                properties.forEach( (fieldName,property) -> {

                    Method setter = Util.getSetter(fieldName, objectClass);

                    if (property.getKind() == ValueKinds.VALUE){
                        throw new RuntimeException("unimplemented VALUE field initialization");
                    }

                    Definition propDef = getDefinitionByBeanName(property.getValue());
                    if(propDef == null) {
                        try {
                            setter.invoke(initializedObject,null);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Class propClass = propDef.getObjectClass();
                    Object value = propDef.getScope().get(property.getValue(), ApplicationObjectFactory.this);
                    try {
                        setter.invoke(initializedObject,propClass.cast(value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });

                return initializedObject;

            } else{
                return initializedObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
