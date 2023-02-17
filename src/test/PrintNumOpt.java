package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumOpt {

    int num = 1;

    int maxNum = 100;

    ReentrantLock lock = new ReentrantLock();

    /**
     * 偶数队列
     */
    Condition condition0 = lock.newCondition();

    /**
     * 奇数队列
     */
    Condition condition1 = lock.newCondition();

    // 打印偶数
    public void print0() {

        lock.lock();
        try {
            for (; num <= maxNum; num++) {
                if (num % 2 == 0) {
                    System.out.println("偶数：" + num);

                    // 唤醒奇数队列
                    condition1.signal();
                    // 偶数队列休眠
                    condition0.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 打印奇数
    public void print1() {

        lock.lock();
        try {
            for (; num < maxNum; num++) {
                if (num % 2 != 0) {
                    System.out.println("奇数：" + num);


                    condition0.signal();
                    condition1.await();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
