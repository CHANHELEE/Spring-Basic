package hello.core.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProviderTest {

    @Test
    void providerTest(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(ClientBean.class, ProtoTypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 =clientBean1.logic();
        assertThat(count1).isSameAs(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 =clientBean2.logic();
        assertThat(count2).isSameAs(1);

        System.out.println(clientBean1.getCount()); // My code
        System.out.println(clientBean2.getCount()); // My code

        clientBean1.addCnt(); // My code
        System.out.println(clientBean1.getCount());  // My code
        System.out.println(clientBean2.getCount());  // My code

        ac.close();


    }


    static  class ClientBean {
        @Autowired
        private ApplicationContext ac;
        private ProtoTypeBean protoTypeBean ; // My code

        public int logic (){
            ProtoTypeBean protoTypeBean = ac.getBean(ProtoTypeBean.class);
            protoTypeBean.addCount();
            this.protoTypeBean=protoTypeBean; // my code
            int count = protoTypeBean.getCount();
            return count;
        }

        public int getCount(){ // my code
            int cnt=this.protoTypeBean.getCount();
            return cnt;
        }

        public  void addCnt(){  // My code
            this.protoTypeBean.addCount();
        }

        @PreDestroy
        public void destroy(){
            System.out.println("destroy");
            System.out.println("Client bean = "+ this);
        }

    }

    @Scope("prototype")
    @Getter
    static  class ProtoTypeBean{
        private  int count = 0;

        public  void addCount(){
            count++;
        }

        @PostConstruct
        public  void init(){
            System.out.println("PrototypeBean init "+this);

        }

        @PreDestroy
        public  void  destroy(){
            System.out.println("destroy");
        }

    }
}
