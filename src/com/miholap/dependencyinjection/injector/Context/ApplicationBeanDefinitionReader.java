package com.miholap.dependencyinjection.injector.Context;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.consumer.MyDIApplication;
import com.miholap.dependencyinjection.service.EmailServiceImpl;
import com.miholap.dependencyinjection.service.MessageService;
import com.miholap.dependencyinjection.service.SMSServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ApplicationBeanDefinitionReader extends BeanDefinitionReader {
    @Override
    public Map<String, Definition<?>> getDefinitionsFromSource() {
        Map<String, Definition<?>> definitions = new HashMap<>();

        Definition<Consumer> consumerDefinition = new Definition<>();
        consumerDefinition.setObjectClass(MyDIApplication.class);
        Map<String,Definition<Consumer>.Property> consumerProperties = new HashMap<>();
        consumerProperties.put("service",
                consumerDefinition.new Property("smsService",ValueKinds.REFERENCE));
        consumerDefinition.setProperties(consumerProperties);
        definitions.put("consumer",consumerDefinition);

        Definition<MessageService> emailServiceDefinition = new Definition<>();
        emailServiceDefinition.setObjectClass(EmailServiceImpl.class);
        definitions.put("emailService",emailServiceDefinition);


        Definition<MessageService> smsServiceDefinition = new Definition<>();
        smsServiceDefinition.setObjectClass(SMSServiceImpl.class);
        definitions.put("smsService",smsServiceDefinition);

        return definitions;
    }
}
