package day29ts.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class optionalTS {
    //optional 等同於容器得一思
    @Test
    public void tes1() {
        Boy boy = new Boy();
//      boy=null;
//      Optional.of(T t) :创建一个Optiona实例,t必须非空;
        Optional<Boy> oil = Optional.of(boy);
        System.out.println(oil);
//      Optional.empty() 创建一个空的Optiona实例
        Optional<Object> oil1 = Optional.empty();
//      Optional.ofNullable(T t): t可以為null
        Optional<Boy> otl2 = Optional.ofNullable(boy);
        System.out.println(otl2);


    }

    @Test
    public void test2(){
        Boy boy = new Boy();
        boy=null;
        Optional<Boy> otl1 = Optional.ofNullable(boy);
        System.out.println(otl1);
        //orelse(Tt1):如果单前的optional内部封装的是非空的,则返回内部的t。
        //如果内部的是空的,则返回orelse()方法中的参数t1
        Boy boyel = otl1.orElse(new Boy("boy"));
        System.out.println(boyel.getName());

    }
}
