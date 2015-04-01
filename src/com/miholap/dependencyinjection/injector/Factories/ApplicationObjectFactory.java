package com.miholap.dependencyinjection.injector.Factories;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.Definition;
import com.miholap.dependencyinjection.service.MessageService;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static org.reflections.ReflectionUtils.*;

public class ApplicationObjectFactory extends ObjectFactory {

    public ApplicationObjectFactory(Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> beanDefinitionReaders) {
        super(beanDefinitionReaders);
    }

    @Override
    public Object getBean(String beanName) {
        Definition definition = getDefinitionByBeanName(beanName);
        final Class objectClass = definition.getObjectClass();
        final Map<String,Object> properties = definition.getProperties();
        Object initializedObject = null;
        try {
            initializedObject = objectClass.newInstance();
            if (properties.size() > 0 ) {
                properties.forEach( (fieldName,value) -> {
/*                    try {
                        objectClass.getDeclaredField(fieldName).getS;
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }*/
                });

                Set<Method> objectSetters = getAllMethods(objectClass,
                        withPrefix("set"),
                        withParametersCount(1));
                if (objectSetters.size() > 0) {

                } else return null;

            } else{
                return initializedObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
