package com.miholap.dependencyinjection.injector.Context;

import com.miholap.dependencyinjection.injector.scopes.Scope;
import com.miholap.dependencyinjection.injector.scopes.SingletonScope;

import java.util.Map;

public class Definition<T> {
    private Class<? extends T> objectClass;
    private Map<String,Object> properties;
    private Scope scope = new SingletonScope();

    public Class<? extends T> getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(Class<? extends T> objectClass) {
        this.objectClass = objectClass;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
