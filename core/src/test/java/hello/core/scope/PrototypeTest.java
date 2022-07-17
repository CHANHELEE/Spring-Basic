package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1=ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2=ac.getBean(PrototypeBean.class);
        System.out.println("singleton1 =  "+ prototypeBean1);
        System.out.println("singleton2 =  "+ prototypeBean2);
        ac.close();

    }

    @Scope("prototype") // 생성 -> 의존관계 주입후 더 이상빈에서 관리 하지 않는다.
    static  class PrototypeBean{
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

