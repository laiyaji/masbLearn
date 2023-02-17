package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 生产后消费，消费后生产产品类
public class Product {
    private String brand;
    private String name;
    private boolean flag;// 默认是false，没有商品
    Lock lock = new ReentrantLock();
    Condition proCondition = lock.newCondition();
    Condition conCondition = lock.newCondition();
    private int i;// 默认为0

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 生产商品
    public synchronized void setProduct(String brand, String name) {
        if (flag == true) {// 有商品，不需要生产，等待消费完
            try {
                //wait（）：作用是使当前线程从调用处中断并且释放锁转入等待队列，
                // 直到收到notify或者notifyAll的通知才能从等待队列转入锁池队列，没有收到停止会一直死等
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 上面消费完了，就该生产了
        this.setBrand(brand);
        this.setName(name);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产者生产了" + this.getBrand() + "--" + this.getName());
        flag = true;
        //notify（）：随机从等待队列中通知一个持有相同锁的一个线程，如果没有持有相同锁的wait线程那么指令忽略无效。
        // 注意是持有相同锁，并且是随机没有固定的，顺序这一点在生产者消费者模型中很重要，会造成假死的状态
        notify();

    }

    public void proProduct(String brand, String name) {
        lock.lock();
        try {
            if (flag == true) {// 有商品，不需要生产，等待消费完
                this.proCondition.await();
            }
            // 上面消费完了，就该生产了
            this.setBrand(brand);
            this.setName(name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("生产者生产了" + this.getBrand() + "--" + this.getName());
            flag = true;
            // 上面生产完了，该唤醒消费队消费了
            this.conCondition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }


    }

    // 消费商品
    public void getProduct() {
        if (!flag) {// 没有商品，则需要等待生产
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 有商品则直接消费
        System.out.println("消费者消费了" + this.getBrand() + "--" + this.getName());
        flag = false;
        notify();
    }

    // 消费商品
    public void ConProduct() {
        lock.lock();
        try {
            if (!flag) {// 没有商品，则需要等待消费
                this.conCondition.await();
            }
            // 有商品则直接消费
            System.out.println("消费者消费了" + this.getBrand() + "--" + this.getName());
            flag = false;
            // 上面消费完了，该唤醒生产队生产了
            this.proCondition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    // 产生奇数
    public synchronized void setOdd() {

            if (i % 2 != 0) { // 奇数
                System.out.println("奇数为：" + i);
                i++;

                try {
                    //随机从等待队列中通知一个持有相同锁的一个线程，如果没有持有相同锁的wait线程那么指令忽略无效
                    notify();
                    //当前线程从调用处中断并且释放锁转入等待队列
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 上面消费完了，就该生产了


        //notify();

    }

    // 产生偶数
    public synchronized void setEven() {

        if (i % 2 == 0) { // 奇数
            System.out.println("偶数为：" + i);
            i++;

            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


       // notify();

    }
}
