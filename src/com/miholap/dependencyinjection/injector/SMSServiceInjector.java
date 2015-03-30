package com.miholap.dependencyinjection.injector;

import com.miholap.dependencyinjection.consumer.MyDIApplication;
import com.miholap.dependencyinjection.service.SMSServiceImpl;
import com.miholap.dependencyinjection.consumer.Consumer;

public class SMSServiceInjector implements MessageServiceInjector {

	@Override
	public Consumer getConsumer() {
		return new MyDIApplication(new SMSServiceImpl());
	}

}
