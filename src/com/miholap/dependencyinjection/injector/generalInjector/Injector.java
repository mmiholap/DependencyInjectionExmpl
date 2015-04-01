package com.miholap.dependencyinjection.injector.generalInjector;

import com.google.common.base.Optional;
import com.miholap.dependencyinjection.injector.Context.ApplicationBeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Factories.ApplicationObjectFactory;
import com.miholap.dependencyinjection.injector.Factories.ObjectFactory;
import com.miholap.dependencyinjection.injector.scopes.Scope;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Injector {
    private Map<String, Scope> scopes = new HashMap<>();
    private Map<Class<? extends BeanDefinitionReader>,BeanDefinitionReader> beanDefinitions = new HashMap<>();
    private Map<Class<? extends ObjectFactory>,ObjectFactory> factories= new HashMap<>();

    public Injector() {
        defaultInitialization();

        scopes.putAll(Optional
                .fromNullable(initScopes())
                .or(new HashMap<>())
        );

        beanDefinitions.putAll(Optional
                .fromNullable(initBeanDefinitionReaders())
                .or(new HashMap<>())
        );

        factories.putAll(Optional
                        .fromNullable(initObjectFactories())
                        .or(new HashMap<>())
        );
    }

    private void defaultInitialization(){
        initDefaultBeanDefinitions();
        initDefaultFactories();
        initDefaultScopes();
    }

    private void initDefaultBeanDefinitions(){
        beanDefinitions.put(ApplicationBeanDefinitionReader.class, new ApplicationBeanDefinitionReader());
    }

    private void initDefaultFactories(){
        factories.put(ApplicationObjectFactory.class,new ApplicationObjectFactory(beanDefinitions));
    }

    private void initDefaultScopes() {
        Reflections reflections =
                new Reflections("com.miholap.dependencyinjection.injector.scopes");

        Set<Class<? extends Scope>> allScopes =
                reflections.getSubTypesOf(Scope.class);

        allScopes.forEach(clazz -> {
            try {
                Scope scope = clazz.newInstance();
                scopes.put(scope.getName(), scope);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Map<String, Scope> getScopes() {
        return scopes;
    }

    public Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> getBeanDefinitions() {
        return beanDefinitions;
    }

    public Map<Class<? extends ObjectFactory>, ObjectFactory> getFactories() {
        return factories;
    }

    public abstract Map<Class<? extends BeanDefinitionReader>,BeanDefinitionReader> initBeanDefinitionReaders();

    public abstract Map<Class<? extends ObjectFactory>,ObjectFactory> initObjectFactories();

    public abstract Map<String, Scope> initScopes();


    public abstract Object getBean(String beanName);
}
