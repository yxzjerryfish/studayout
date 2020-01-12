package fish.study;

import fish.study.config.AppConfig;
import fish.study.config.ConfigB;
import fish.study.model.A;
import fish.study.model.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigB.class);
//        A a = applicationContext.getBean(A.class);
//        B b = applicationContext.getBean(B.class);
//
//        System.out.println(a.getA() + b.getB());
        LocalDateTime l = LocalDateTime.of(2019,4,16,0,0);
        LocalDateTime m = l.plusDays(168);
        System.out.println(m.toLocalDate());
    }
}
