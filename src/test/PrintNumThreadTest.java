package test;

public class PrintNumThreadTest {
    public static void main(String[] args) {
        PrintNumOpt printNumOpt = new PrintNumOpt();
        new Thread(() -> printNumOpt.print1()).start();
        new Thread(() -> printNumOpt.print0()).start();

    }
}
