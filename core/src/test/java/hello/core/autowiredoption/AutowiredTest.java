package hello.core.autowiredoption;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutoWiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }


    static class TestBean{

        @Autowired(required = false) // false: 주입할 대상이 없기 때문에 호출 자체를 안함.
        public void setNoBean1(Member member){ // Member는 스프링 컨테이너의 관리대상이 아니다.
            System.out.println("noBean1 = " +  member);
        }

        @Autowired // @Nullable: 호출은 되지만 주입 대상이 없기 때문에 null 이 됨.
        public void setNoBean2(@Nullable  Member member){ // Member는 스프링 컨테이너의 관리대상이 아니다.
            System.out.println("noBean2 = " +  member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member){ // Member는 스프링 컨테이너의 관리대상이 아니다.
            System.out.println("noBean3 = " +  member);
        }

    }
}
