import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.consumer.MyDIApplication;
import com.miholap.dependencyinjection.injector.generalInjector.GenericMessageServiceInjector;
import com.miholap.dependencyinjection.injector.generalInjector.Injector;
import com.miholap.dependencyinjection.service.EmailServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sentinel on 31 Mar.
 */
public class InjectorTest {

    @Test
    public void injectorTest(){
        Injector injector = new GenericMessageServiceInjector();
        Consumer consumer = injector.getConsumer(MyDIApplication.class,
                EmailServiceImpl.class, "singleton");
        Assert.assertTrue(consumer != null);
        consumer.processMessages("hello","max@max.max");
    }
}
