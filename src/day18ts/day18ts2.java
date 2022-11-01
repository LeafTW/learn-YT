package day18ts;

public class day18ts2 implements Runnable {
    private int tick = 100;

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                notify();
                if(tick >=0){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(tick);
                tick--;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else break;
            }

        }
    }
}



