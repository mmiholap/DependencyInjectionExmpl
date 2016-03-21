import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.miholap.dependencyinjection.consumer.MyDIApplication;
import com.miholap.dependencyinjection.injector.MessageServiceInjector;

public class MyDIApplicationJUnitTest {

    private MessageServiceInjector injector;

    @Before
    public void setUp() {
        //mock the injector with anonymous class
        injector = new MessageServiceInjector() {

            @Override
            public Consumer getConsumer() {
                //mock the message service
                return new MyDIApplication(new MessageService() {

                    @Override
                    public void sendMessage(String msg, String rec) {
                        System.out.println("Mock Message Service implementation");

                    }
                });
            }
        };
    }

    @Test
    public void test() {
        Consumer consumer = injector.getConsumer();
        consumer.processMessages("Hi All", "user@di.com");
    }

    @After
    public void tear() {
        injector = null;
    }

}
