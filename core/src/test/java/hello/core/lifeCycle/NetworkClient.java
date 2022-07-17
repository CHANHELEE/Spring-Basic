package hello.core.lifeCycle;

import lombok.Getter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Getter
public class NetworkClient
{ // 의존관계 주입이 끝난 후 관련 메소드 호출 & 해당 빈이 종료될 때 관련 메서드 호출

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    void connect(){
        System.out.println("Connetct: "+ url);
    }

    void call(String message){
        System.out.println("call: "+url+" message = "+ message);

    }
    void  disconnect(){
        System.out.println("close: "+ url);
    }

    @PostConstruct
    public void init()  {
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close()  {
        disconnect();
    }
}
