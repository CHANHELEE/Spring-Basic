package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring 사용 , 해당 어노테이션을 제거하면  단순 자바로 돌아감. , 구현된 클래스를 자동으로 주입해주는 spring 기능
public class AppConfig { // 생성자 주입을 위한 클래스 , DIP 위반을 방지한다.
    @Bean // bean 에 넣고 스프링이 직접 관리
    public MemberService memberService(){
        //return new MemberServiceImpl(new MemoryMemberRepository());
        return new MemberServiceImpl(memberRepository());
        //구체화된 객체를 생성자를 통해 주입한다.
        //return  null;
    }
    @Bean
    public OrderService orderService(){
        //return new OrderServiceImpl(new FixDiscountPolish(),memberRepository());
        return new OrderServiceImpl(discountPolicy(),memberRepository());
        //구체화된 객체를 생성자를 통해 주입한다.
        //return  null;
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository(); // 어떤 레포를 사용할지 결정하는 메소드
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();// 어떤 할인 정책을 사용할지 결정하는 메소드
        return  new RateDiscountPolicy();
    }
}
