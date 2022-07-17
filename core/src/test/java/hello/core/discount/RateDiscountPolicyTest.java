package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip 는 10 % 할인이 적용되어야 한다.")

    void vip_o(){
        //given
        Member member = new Member(1L,"VIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        Assertions.assertEquals(discount,1000);
    }

}