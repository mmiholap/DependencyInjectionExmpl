package com.miholap.dependencyinjection.injector.scopes;


import com.miholap.dependencyinjection.service.MessageService;

public  interface Scope {
    MessageService get(Class< ? extends MessageService> clazz);

    String getName();
}
