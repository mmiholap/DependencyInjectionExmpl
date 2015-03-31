package com.miholap.dependencyinjection.injector.scopes;


import com.miholap.dependencyinjection.injector.Factories.ObjectFactory;
import com.miholap.dependencyinjection.service.MessageService;

public  interface Scope {
    String getName();

    Object get(String beanName, ObjectFactory objectFactory);
}
