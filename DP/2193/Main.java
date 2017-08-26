import java.util.Scanner;
import java.util.Arrays;
public class Main
{
    private static long[] cache;
    public static long solve(int n)
    {
        cache = new long[n+1];
        Arrays.fill(cache, -1);
        return pinary(n);
    }
    
    private static long pinary(int n)
    {
        if (n <= 2)
            return 1;
        if (cache[n] != -1)
            return cache[n];
        return cache[n] = pinary(n-1) + pinary(n-2);
    }
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(solve(n));
    }
}

/**
 * 피보나치 일 때, N이 큰 경우, int로 해결못하는 경우 많으니 check!
 */
