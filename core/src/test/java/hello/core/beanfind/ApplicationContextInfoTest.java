package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String [] beanDefinitionNames=ac.getBeanDefinitionNames();
        for(String beanDefinitionName: beanDefinitionNames){
            Object bean =ac.getBean(beanDefinitionName);
            System.out.println("name= " +beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findAApplicationBean(){
        String [] beanDefinitionNames=ac.getBeanDefinitionNames();
        for(String beanDefinitionName: beanDefinitionNames){
            BeanDefinition beandef =ac.getBeanDefinition(beanDefinitionName);

            //Role ROLE_APPLIVATION: 직접 등록한 애플리케이션 빈
            if(beandef.getRole() == beandef.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name= " +beanDefinitionName + " object = " + bean);}
        }
    }
}
