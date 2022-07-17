package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    //할인 금액을 return
    int discount(Member member,int price);


}
