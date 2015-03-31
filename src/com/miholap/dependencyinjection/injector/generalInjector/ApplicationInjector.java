package com.miholap.dependencyinjection.injector.generalInjector;

import com.miholap.dependencyinjection.injector.Context.ApplicationBeanDefinitionReader;
import com.miholap.dependencyinjection.injector.Context.BeanDefinitionReader;
import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.Context.Definition;
import com.miholap.dependencyinjection.injector.Factories.ApplicationObjectFactory;
import com.miholap.dependencyinjection.injector.Factories.ObjectFactory;
import com.miholap.dependencyinjection.injector.scopes.NoSuchScopeException;
import com.miholap.dependencyinjection.injector.scopes.Scope;
import com.miholap.dependencyinjection.service.MessageService;
import static org.reflections.ReflectionUtils.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class ApplicationInjector extends Injector {

    private Map<String, Scope> scopes = super.getScopes();


    public Consumer getConsumer(Class<? extends Consumer> csClass,
                                Class<? extends MessageService> msClass,
                                String scopeName) {
        if( !scopes.containsKey(scopeName)){
            throw new NoSuchScopeException();
        }
        Scope scope = scopes.get(scopeName);
        MessageService messageService = null;//scope.get(msClass);
        Consumer consumer = null;
        try {
            consumer = csClass.newInstance();
            Set<Method> messageServiceSetters = getAllMethods(csClass,
                    withPrefix("set"),
                    withParametersCount(1),
                    withParametersAssignableTo(MessageService.class));
            if(messageServiceSetters.size() > 0){
                Method msSetter = messageServiceSetters.iterator().next();
                msSetter.invoke(consumer,messageService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumer;
    }


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
