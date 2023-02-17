package lambda;

import java.util.function.Supplier;

// 测试lambda表达式
public class Test {
    public static void main(String[] args) {
        IlambdaInterface il0 = (a, b) ->
                a + b;
        ;
        System.out.println(il0.sum(1, 2));
        // lambda表达式的前提是有函数式接口才能用
        new Thread(
                // 函数式接口，Runnebale,就是fun方法的的实现
                () -> {
                    String name = Thread.currentThread().getName();
                    System.out.println(name);
                }
        ).start();

        int[] array = {1, 2, 4, 9, 13, 99, 34, 70, 15};
        int max = getMax(() -> {
            int temp = 1;
            for (int i = 0; i < array.length; i++)
                if (array[i]>temp  ) {
                    temp = array[i];
                }
            return temp;
        });
        System.out.println("最大值为：" + max);
    }

    public static int getMax(Supplier<Integer> supplier) {

        return supplier.get();
    }
}
