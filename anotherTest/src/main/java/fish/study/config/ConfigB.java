package fish.study.config;

import fish.study.model.A;
import fish.study.model.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ConfigA.class)
public class ConfigB {
    @Autowired
    A a;

    @Bean
    public B b(){
        System.out.println(a.getA());
        return new B();
    }
}
