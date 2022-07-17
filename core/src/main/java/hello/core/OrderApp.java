package hello.core;

import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig(); //주입자 인스턴스 생성, 주석2
        //MemberService memberService = appConfig.memberService(); // 주입자를 통한 구현 객체 주입,주석2
        //OrderService orderService = appConfig.orderService();// 주입자를 통한 구현 객체 주입 ,주석2

        //MemberService memberService = new MemberServiceImpl(); 주석1
        //OrderService orderService = new OrderServiceImpl(); 주석1

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService=applicationContext.getBean("memberServiceImpl",MemberServiceImpl.class);
        OrderService orderService=applicationContext.getBean("orderServiceImpl",OrderServiceImpl.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA",10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
