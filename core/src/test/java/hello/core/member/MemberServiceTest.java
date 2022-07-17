package hello.core.member;

import hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository()); 구현클래스 생성자 주입전

    MemberService memberService; // 구현클래스 생생자 주입 후
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test // 테스트 코트는 실무에서 중요하다.
    void join(){
        //given
        Member member =new Member(1L,"memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertEquals(findMember,member);
    }
}
