package com.miholap.dependencyinjection.injector.generalInjector;

import com.miholap.dependencyinjection.injector.Context.ApplicationBeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.Definition;
import com.miholap.dependencyinjection.injector.Factories.ApplicationObjectFactory;
import com.miholap.dependencyinjection.injector.Factories.ObjectFactory;
import com.miholap.dependencyinjection.injector.scopes.Scope;
import java.util.Map;

public class ApplicationInjector extends Injector {

    private Map<String, Scope> scopes = super.getScopes();

    @Override
    public Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> initBeanDefinitionReaders() {
        return null;
    }

    @Override
    public Map<Class<? extends ObjectFactory>, ObjectFactory> initObjectFactories() {
        return null;
    }

    @Override
    public Map<String, Scope> initScopes() {
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        Map<String, Definition<?>> definitions =
                getBeanDefinitions().get(ApplicationBeanDefinitionReader.class).getDefinitions();
        ObjectFactory objectFactory = getFactories().get(ApplicationObjectFactory.class);

        Definition<?> definition = definitions.get(beanName);

        if(definition == null) return null;

        Scope scope = definition.getScope();

        if(scope == null) return  null;

        Object result = scope.get(beanName, objectFactory);
        return result;
    }
}
