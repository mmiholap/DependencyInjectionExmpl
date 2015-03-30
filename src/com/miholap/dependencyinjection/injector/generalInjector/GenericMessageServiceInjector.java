package com.miholap.dependencyinjection.injector.generalInjector;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.MessageServiceInjector;
import com.miholap.dependencyinjection.injector.scopes.NoSuchScopeException;
import com.miholap.dependencyinjection.injector.scopes.Scope;
import com.miholap.dependencyinjection.service.MessageService;
import static org.reflections.ReflectionUtils.*;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class GenericMessageServiceInjector extends Injector {

    private Map<String, Scope> scopes = super.getScopes();

    @Override
    public Consumer getConsumer(Class<? extends Consumer> csClass,
                                Class<? extends MessageService> msClass,
                                String scopeName) {
        if( !scopes.containsKey(scopeName)){
            throw new NoSuchScopeException();
        }
        Scope scope = scopes.get(scopeName);
        MessageService messageService = scope.get(msClass);
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
}
