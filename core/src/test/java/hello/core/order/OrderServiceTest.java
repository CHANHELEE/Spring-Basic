package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
    //OrderService orderService = new OrderServiceImpl();
    AppConfig appConfig = new AppConfig();
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        this.memberService = appConfig.memberService();
        this.orderService = appConfig.orderService();
    }

    @Test
    //given
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "itemA", Grade.VIP);
        memberService.join(member);


        //when
        Order order = orderService.createOrder(memberId,"itemA",10000);
        //then
        Assertions.assertEquals(order.getDiscountPrice(),1000);
        System.out.println(order.getDiscountPrice());
    }
}
