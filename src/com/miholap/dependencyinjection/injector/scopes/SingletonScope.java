package com.miholap.dependencyinjection.injector.scopes;

import com.miholap.dependencyinjection.injector.Factories.ObjectFactory;
import java.util.HashMap;
import java.util.Map;

public class SingletonScope implements Scope {
    private Map<String, Object> cache = new HashMap<>();

    @Override
    public String getName() {
        return "singleton";
    }

    @Override
    public Object get(String beanName, ObjectFactory objectFactory) {
        Object bean = cache.get(beanName);
        if(bean == null){
            bean = objectFactory.getBean(beanName);
            cache.put(beanName,bean);
        }
        return bean;
    }

}
