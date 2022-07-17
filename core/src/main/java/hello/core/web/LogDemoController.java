package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private  final  LogDemoService logDemoService;
    private  final MyLogger myLogger; // 프록시 설정하지 않으면 에러발생 ..  bc: 리퀘스트 스코프는 클라이언트가 리퀘스트를 한 후 부터 빈에 등록하여 관리 한다. 하지만 클라이언트는 요청을 한적이 없기 때문에 오류가 난다.
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL= request.getRequestURL().toString();

        //MyLogger myLogger = myLoggerProvider.getObject(); // 프록시 설정하지 않았을 때 사용
        System.out.println("mylogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "ok";
    }
}
