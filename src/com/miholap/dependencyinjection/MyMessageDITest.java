package com.miholap.dependencyinjection;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.EmailServiceInjector;
import com.miholap.dependencyinjection.injector.MessageServiceInjector;
import com.miholap.dependencyinjection.injector.SMSServiceInjector;

public class MyMessageDITest {

	public static void main(String[] args) {
		String msg = "Hi All";
		String email = "user@di.com";
		String phone = "123456789";
		MessageServiceInjector injector = null;
		Consumer app = null;
		
		//Send email
		injector = new EmailServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, email);
		
		//Send SMS
		injector = new SMSServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, phone);
	}

}
