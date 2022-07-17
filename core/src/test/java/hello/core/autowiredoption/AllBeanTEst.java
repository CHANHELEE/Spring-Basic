package hello.core.autowiredoption;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AllBeanTEst {
    @Test
    void findAllBean(){
        // autoappconfig & Discountservice 를 컨테이너에 등록.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class ,DiscountService.class);
        DiscountService discountService=ac.getBean(DiscountService.class);
        Member member = new Member(1L,"userA", Grade.VIP);
        int discountPrice = discountService.discount(member,10000,"fixDiscountPolicy");

        assertThat(discountPrice).isEqualTo(1000);

    }

    static class DiscountService{
        //autowired 로 생성자에 자동 주입됨.
        private final Map<String, DiscountPolicy> policyMap;
        private  final List<DiscountPolicy> policyList;
        //private final Map<String, MemberService> memberMap;
        //private final Map<String, OrderService> orderMap;
        //@AutoWired 생략, 생성자가 1개이기 때문이다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;

            System.out.println("map = " + policyMap);
            System.out.println("list = " + policyList);
        }

        /*public DiscountService(Map<String, DiscountPolicy> policyMap, Map<String, MemberService> memberMap, Map<String, OrderService> orderMap ) {
            this.policyMap = policyMap;
            this.orderMap= orderMap;
            this.memberMap= memberMap;

            System.out.println("policymap = " + this.policyMap);
            System.out.println("membermap = " + this.memberMap);
            System.out.println("ordermap = " + this.orderMap);
        }*/



        public int discount(Member member,int price , String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
