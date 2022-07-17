package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @component 어노테이션이 붙으면 자동으로 빈에 등록.
        basePackages = "hello.core", // hello.core 부터 차례대로 빈에 등록.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class) // 등록하지 않을 필터설정
)        // @Configuration 어노테이션이 붙은것을 필터링.
public class AutoAppConfig {

    //Test

}
