package test;

// 奇数偶数交替打印线程类
public class ConsumThread extends Thread {
    private Product product;

    public ConsumThread(Product p) {
        this.product = p;
        this.product.setI(1);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            //this.product.getProduct();
            //this.product.ConProduct();
            this.product.setEven();
        }


    }
}
