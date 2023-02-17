package test;
// 奇数偶数交替打印线程类
public class EvenThread extends Thread {
    Object lock;
    private volatile int i;

    public EvenThread(Object o, int n) {
        this.i = n;
        this.lock = o;
    }

    @Override
    public void run() {

        synchronized (lock) {
            while (i <= 10) {

                try {
                    if (i%2==0)
                    System.out.println("偶数为：" + i);
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
