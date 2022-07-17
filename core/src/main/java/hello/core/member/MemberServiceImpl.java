package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    // DIP 위반 : MemberRepository(추상화된 클래스),MemoryMemberRepository(구현체)를 모두 의존하고 있음.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private  MemberRepository memberRepository;
    //더이상 DIP 위반이 아니다. 오로지 추상화된 클래스에만 의존하고 있다.
    @Autowired // 의존관계 자동 주입. , ac.getBean(MemberRepository.class) 와 같은 기능
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }// 어떠한 구현 객체를 주입할지 결정하는 생성자

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return  memberRepository;
    }
}
