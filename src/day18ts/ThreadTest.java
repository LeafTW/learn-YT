package day18ts;

public class ThreadTest extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                System.out.println(getName());
//                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}

class runts {
    public static void main(String[] args) {
        ThreadTest s1 = new ThreadTest();
        ThreadTest s2 = new ThreadTest();
        s1.setName("現成1");
        s2.setName("現成2");
        s1.start();
        s2.start();

    }
}
