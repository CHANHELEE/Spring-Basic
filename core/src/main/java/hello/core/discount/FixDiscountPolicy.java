package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{//고정적으로 1000원을 할인해주는 클래스

    private  int discountFixAmount =1000; // 할인가격

    @Override
    public int discount(Member member, int price){
        if (member.getGrade()== Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }

    }

}
