package com.miholap.dependencyinjection.injector.generalInjector;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.scopes.Scope;
import com.miholap.dependencyinjection.service.MessageService;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract  class Injector {
    private Map<String, Scope> scopes;
    public Injector(){
        Reflections reflections =
                new Reflections("com.miholap.dependencyinjection.injector.scopes");

        Set<Class<? extends Scope>> allScopes =
                reflections.getSubTypesOf(Scope.class);

        scopes = new HashMap<>();
        allScopes.forEach(clazz -> {
            try {
                Scope scope = clazz.newInstance();
                scopes.put(scope.getName(),scope);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Map<String, Scope> getScopes() {
        return scopes;
    }

    public abstract Consumer getConsumer(Class<? extends Consumer> csClass,Class<? extends MessageService> msClass, String scopeName);
}
