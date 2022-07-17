package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();주석2
        //MemberService memberService = appConfig.memberService();주석2
        //MemberService memberService = new MemberServiceImpl();  주석 1
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //스프링 컨테이너 생성
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class); // Appconfig 의 메서드 이름으로 빈에 저장됨

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " +findMember.getName() );
    }
}
