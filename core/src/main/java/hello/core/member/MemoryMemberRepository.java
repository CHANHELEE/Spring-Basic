package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>(); // memory 선언  , 실무에서는 concurrrent hashmap 사용!

    @Override
    public void  save(Member member){
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId){
        return  store.get(memberId);
    }


}
