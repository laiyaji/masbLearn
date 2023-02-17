package test;

public class OddThread extends Thread {
    // 奇数线程

    Object lock;
    private volatile int i;

    public OddThread(Object o, int n) {
        this.i = n;
        this.lock = o;
    }

    @Override
    public void run() {

        synchronized (lock) {
            while (i <= 10) {
                try {

                    if (i%2!=0)
                    System.out.println("奇数为：" + i);
                    i++;
                    lock.notify();
                    lock.wait();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            lock.notify();
        }

    }
}
