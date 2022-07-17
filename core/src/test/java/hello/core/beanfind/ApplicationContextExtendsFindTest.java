package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    /*@Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면, 중복오류가 발생한다.")
    void findBeanByParentTypeDup(){
        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                () ->ac.getBean(DiscountPolicy.class));
    }*/
    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면, 이름을 지정하면 된다..")
    void findBeanByParentName(){
        DiscountPolicy bean = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertEquals(bean.getClass(),RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParent(){
        Map<String ,DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertEquals(beansOfType.size(),2);
        for(String key:beansOfType.keySet()){
            System.out.println(key);
        }

    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByParentObject(){ // 스프링 내부에서 쓰이는것도 모두 조회된다.
        Map<String ,Object> beansOfType = ac.getBeansOfType(Object.class);
        //Assertions.assertEquals(beansOfType.size(),2);
        for(String key:beansOfType.keySet()){
            System.out.println(key);
        }

    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertEquals(bean.getClass(),RateDiscountPolicy.class);
    }

    @Configuration
    static  class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }


    }
}
