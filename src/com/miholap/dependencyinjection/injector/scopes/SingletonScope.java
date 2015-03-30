package com.miholap.dependencyinjection.injector.scopes;

import com.miholap.dependencyinjection.service.MessageService;

public class SingletonScope implements Scope {
    private MessageService messageService;

    @Override
    public MessageService get(Class<? extends MessageService> clazz) {
        if (messageService == null){
            try {
                messageService = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return messageService;
    }

    @Override
    public String getName() {
        return "singleton";
    }


}
