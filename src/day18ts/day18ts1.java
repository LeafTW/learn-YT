package day18ts;

import org.junit.jupiter.api.Test;

public class day18ts1 extends Thread {
    public static void or(){
        System.out.println("run");
    }
    @Override
    public void run() {
        for (int i =0 ;i<=50; i++){
            System.out.println(i);
            System.out.println(this.getName());
        }
    }

    @Test
    public void test(){
        day18ts1 s1=new day18ts1();
        s1.setName("線程1");
        day18ts1 s2=new day18ts1();
        s2.setName("線程2");
        s1.start();
        s2.start();
   }



}
