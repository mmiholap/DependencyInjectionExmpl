package com.miholap.dependencyinjection.injector.Factories;

import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.Definition;

import java.util.Map;

public class ApplicationObjectFactory extends ObjectFactory {

    public ApplicationObjectFactory(Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> beanDefinitionReaders) {
        super(beanDefinitionReaders);
    }

    @Override
    public Object getBean(String beanName) {
        Definition definition = getDefinitionByBeanName(beanName);

        return null;
    }



}
