package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingleToneTest {
    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1=ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2=ac.getBean(SingletonBean.class);
        System.out.println("singleton1 =  "+ singletonBean1);
        System.out.println("singleton2 =  "+ singletonBean2);
        ac.close();
    }

    @Scope("singleton")
    static  class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
        }
    }
}
