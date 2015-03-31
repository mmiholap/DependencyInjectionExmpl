package com.miholap.dependencyinjection.injector.Factories;


import com.miholap.dependencyinjection.injector.Context.ApplicationBeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.Definition;

import java.util.Map;

public abstract class ObjectFactory {
    private Map<Class<? extends BeanDefinitionReader>,BeanDefinitionReader> beanDefinitionReaders;
    public abstract Object getBean(String beanName);

    public ObjectFactory(Map<Class<? extends BeanDefinitionReader>,BeanDefinitionReader> beanDefinitionReaders){
        this.beanDefinitionReaders = beanDefinitionReaders;
    }

    protected Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> getBeanDefinitionReaders() {
        return beanDefinitionReaders;
    }

    protected Definition getDefinitionByBeanName(String BeanName){
        Map<String, Definition<?>> definitions =
                getBeanDefinitionReaders().get(ApplicationBeanDefinitionReader.class).getDefinitions();
        return definitions.get(BeanName);
    }
}
