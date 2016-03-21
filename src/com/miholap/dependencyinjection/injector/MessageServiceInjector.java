package com.miholap.dependencyinjection.injector;

import com.miholap.dependencyinjection.consumer.Consumer;

public interface MessageServiceInjector {

    public Consumer getConsumer();
}
