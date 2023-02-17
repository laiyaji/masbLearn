package test;


public class OddAndEvenTest {
    // 奇数偶数交替打印测试类
    private static volatile boolean odd = true;
    private static int numr = 1;

    public static void main(String[] args) {


       /*Thread oddThread=new Thread(()->{
           while(numr<=10){
                while (odd){
                    System.out.println(numr);
                    numr++;
                    odd=false;
                }
           }
       });
        Thread evenThread=new Thread(()->{
            while(numr<=10){
                while (!odd){
                    System.out.println(numr);
                    numr++;
                    odd=true;
                }
            }
        });*/
        Object obj = new Object();
        Thread oddThread = new OddThread(obj, 1);
        Thread evenThread = new OddThread(obj, 1);
        oddThread.start();
        evenThread.start();

    }
}
