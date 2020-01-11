package fish.study.netty.service.impl;

import fish.study.netty.service.Hello;

public class HelloImpl implements Hello {
    @Override
    public void say() {
        System.out.println("hello world!");
    }
}
