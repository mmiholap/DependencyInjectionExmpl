package com.miholap.dependencyinjection.injector.Context;

import java.util.Map;

public abstract class BeanDefinitionReader {
    private Map<String,Definition<?>> definitions;

    public abstract Map<String,Definition<?>> getDefinitionsFromSource();

    public final void initialize(){
        definitions = getDefinitionsFromSource();
    }

    public BeanDefinitionReader() {
        initialize();
    }

    public Map<String, Definition<?>> getDefinitions() {
        return definitions;
    }
}
