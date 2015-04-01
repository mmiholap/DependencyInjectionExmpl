package com.miholap.dependencyinjection;

import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.generalInjector.ApplicationInjector;
import com.miholap.dependencyinjection.injector.generalInjector.Injector;

public class MyMessageDITest {

	public static void main(String[] args) {
		String msg = "Hi All";
		String email = "user@di.com";
		String phone = "123456789";

		Injector injector = new ApplicationInjector();

		Consumer app = (Consumer)injector.getBean("consumer");
		app.processMessages(msg, email);
	}

}
