package foroffer.foroffer.time01.top40;

/**
 * Created by liyazhou on 2017/5/30.
 * 面试题34：丑数
 *
 * 题目：
 *      我们把只包含因子 2、3 和 5 的数称作为丑数（Ugly number）。求从小到大的顺序的第 1500 个丑数。
 *      例如 6、8 都是丑数，但 14 不是，因为它包含了因子 7。习惯上，我们把 1 当做第一个丑数。
 *
 * 考查点：
 *      1. 根据定义找规律（没找到 =_=）
 *
 * 思路：
 *      1. 本办法，依次判断每一个自然数是否是丑数，当 k =1500 时明显感觉算时间较长
 *
 */
public class Test34 {

    public static int getKthUglyNumber(int k){
         if (k < 1) return -1;
         for (int number = 1; ; number++){
             if (isUgly(number)) k--;
             if (k == 0) return number;
        }
    }

    private static boolean isUgly(int number){
        while (number % 2 == 0) number /= 2;
        while (number % 3 == 0) number /= 3;
        while (number % 5 == 0) number /= 5;
        return number == 1;
    }

    public static void main(String[] args){
        System.out.println(getKthUglyNumber(8));  // 9
        System.out.println(getKthUglyNumber(1500));  // 859963392
    }
}
