import java.util.*;
import java.math.BigInteger;

/**
 * problem 13277
 * 큰 수 곱셈
 * https://www.acmicpc.net/problem/13277
 * written by 13277
 */
public class Main
{ 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String n1 = sc.next();
        String n2 = sc.next();
        BigInteger a = new BigInteger(n1);
        BigInteger b = new BigInteger(n2);
        
        System.out.println(a.multiply(b));
    }
}