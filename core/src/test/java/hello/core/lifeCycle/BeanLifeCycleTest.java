package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BeanLifeCycleTest {

    @Test
    public  void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient=ac.getBean("networkClient",NetworkClient.class);

        assertThat(networkClient.getUrl()).isEqualTo("http://chan");
        ac.close();
    }

    @Configuration
    static  class LifeCycleConfig{
        @Bean//(initMethod = "init",destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient =new NetworkClient();
            networkClient.setUrl("http://chan");
            return  networkClient;

        }

    }
}
