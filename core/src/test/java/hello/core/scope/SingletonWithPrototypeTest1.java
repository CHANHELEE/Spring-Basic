package hello.core.scope;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    void singletonClientPrototype(){
        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(ClientBean.class,ProtoTypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 =clientBean1.logic();
        assertThat(count1).isSameAs(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 =clientBean2.logic();
        //assertThat(count2).isSameAs(2);
        assertThat(count2).isSameAs(1);


        ac.close();




    }


    @RequiredArgsConstructor
    static  class ClientBean {
        //private  final ProtoTypeBean protoTypeBean; //프로토 타입을 자동 주입해서 참조값을 만들어, 계속 같은 프로토타입 빈 사용.
        //@Autowired
        private final ObjectProvider<ProtoTypeBean> protoTypeBeanProvider;
        public int logic (){
            ProtoTypeBean protoTypeBean = protoTypeBeanProvider.getObject();
            protoTypeBean.addCount();
            int count = protoTypeBean.getCount();
            return count;
        }

        @PreDestroy
        public  void destroy(){
            System.out.println("Client destroy");
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
