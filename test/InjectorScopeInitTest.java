import com.miholap.dependencyinjection.consumer.Consumer;
import com.miholap.dependencyinjection.injector.generalInjector.Injector;
import com.miholap.dependencyinjection.service.MessageService;
import org.junit.Assert;
import org.junit.Test;

public class InjectorScopeInitTest {
    private Injector injector = new Injector() {
        @Override
        public Consumer getConsumer(Class<? extends Consumer> csClass,
                                    Class<? extends MessageService> msClass,
                                    String scopeName) {
            return null;
        }
    };

    @Test
    public void testIfScopesInitialized(){
        Assert.assertTrue(injector.getScopes().size() > 0);
    }
}
