package employees;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }

//    @Bean
//    public ModelMapper modelMapper() {return new ModelMapper();}
}
