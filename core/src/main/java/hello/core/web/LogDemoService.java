package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private  final MyLogger myLogger; // 프록시 설정하지 않으면 에러발생! 설명은 컨트롤러 참고
    //private final ObjectProvider<MyLogger> myLoggerProvider;


    public void logic(String id) {
        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id =" + id );
    }
}
