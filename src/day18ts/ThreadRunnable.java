package day18ts;

import java.util.concurrent.locks.ReentrantLock;

class Runnablets implements Runnable {
    private int s=100;
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
//              wait():令当前线程挂起并放弃CPU、同步资源并等待，使别的线程可访问并修改共享资源，而当 前线程排队等候其他线程调用notify()或notifyAll()方法唤醒，
//                  唤醒后等待重新获得对监视器的所有 权后才能继续执行。
//              notify():唤醒正在排队等待同步资源的线程中优先级最高者结束等待
//              notifyAll ():唤醒正在排队等待资源的所有线程结束等待.
                notify();
                if (s !=0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + " " + s);
                    s--;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else break;
            }
        }
    }
}

public class ThreadRunnable extends Runnablets {
    public static void main(String[] args) {
        Runnablets s1 = new Runnablets();
        Thread t1 = new Thread(s1);
        Thread t2 = new Thread(s1);
        t1.setName("現成1");
        t2.setName("現成2");
        t1.start();
        t2.start();
    }
}
