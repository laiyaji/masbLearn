package test;

public class Test {
    public static void main(String[] args) {
        /*Product product = new Product();
        ProductThread productThread = new ProductThread(product);
        ConsumThread consumThread = new ConsumThread(product);

        productThread.start();
        consumThread.start();
        */
        // 测试位运算，左右移动xx位后复制
        int i=8;  // 二进制数据 00000000 00000000 00000000 00001000
        i<<=3;    //左移后赋值 左移三位 在赋值  00000000 00000000 00000000 01000000
        System.out.println(i);

        // lambda 表达式
        System.out.println("位运算符很好用，效率高");


    }
}
