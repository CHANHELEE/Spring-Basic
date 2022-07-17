package hello.core.singleTone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingleToneTest {

    @Test
    @DisplayName("스프링 없이 순수한 DI 컨테이너 ")
    public void pureContainer(){
        //요청을 할 때마다 계속 객체를 생성한다.
        AppConfig appConfig  = new AppConfig();
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println(memberService1);
        System.out.println(memberService2);

        //Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){ // 처음 만든 객체를 계속 참조함.
        SingleToneService singleToneService1 = SingleToneService.getInstance();
        SingleToneService singleToneService2 = SingleToneService.getInstance();


        System.out.println(singleToneService1);
        System.out.println(singleToneService2);

        assertThat(singleToneService1).isEqualTo(singleToneService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤") // 스프링 컨테이너는 알아서 싱글톤으로 관리해준다.
    void springContainer(){
        ApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = appConfig.getBean("memberService",MemberService.class);
        MemberService memberService2 = appConfig.getBean("memberService",MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }

}
