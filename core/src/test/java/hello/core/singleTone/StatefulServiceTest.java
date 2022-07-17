package hello.core.singleTone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService s1 = ac.getBean("statefulService",StatefulService.class);
        StatefulService s2 = ac.getBean("statefulService",StatefulService.class);

        //TreadA : A 사용자 10000원 주문
        s1.order("userA",10000);
        //ThreadB : B 사용자 20000원 주문
        s2.order("userB",20000);

        //ThreadA : 사용자 A 가 주문금액 조회
        //int price=s1.getPrice();
        //System.out.println("price = " + price);

        assertThat(s1).isSameAs(s2);

    }

    static  class  TestConfig{
        @Bean
        public  StatefulService statefulService(){
            return new StatefulService();
        }
    }

}