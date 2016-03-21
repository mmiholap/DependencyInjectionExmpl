package com.miholap.dependencyinjection.injector;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.consumer.MyDIApplication;
import com.miholap.dependencyinjection.service.EmailServiceImpl;

public class EmailServiceInjector implements MessageServiceInjector {

    @Override
    public Consumer getConsumer() {
        MyDIApplication app = new MyDIApplication();
        app.setService(new EmailServiceImpl());
        return app;
    }

}
