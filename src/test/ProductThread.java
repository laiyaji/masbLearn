package test;

public class ProductThread extends Thread {

    private Product product;

    public ProductThread(Product p) {
        this.product = p;
        this.product.setI(1);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            this.product.setOdd();
            /*if (i % 2 == 0) {

                //this.product.setProduct("德芙","巧克力");
                this.product.proProduct("德芙", "巧克力");


            } else {
                //this.product.setProduct("大乌苏","啤酒");
                this.product.proProduct("大乌苏", "啤酒");

            }*/

        }

    }
}
