import java.util.*;
import java.io.*;
public class Main
{
    private static int M = 1000000000;
    private static int[][] cache;
    public static int solve(int n)
    {
        cache = new int[n+1][10];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = 0;
        for (int i = 1; i < 10; i++)
            result = (result + stairNumber(n, i)) % M;
        return result;
    }
    
    /**
     * n자리일 때, digit으로 시작하는 stairNumber의 수.
     */
    private static int stairNumber(int n, int digit)
    {
        if (n == 1)
            return 1;
        else if (n == 2)
        {
            if (digit == 9)
                return 1;
            else
                return 2;
        }
        if (cache[n][digit] != -1)
            return cache[n][digit];
        
        int result = 0;
        if (digit == 1)
        {
            result = stairNumber(n-2, 1) % M + stairNumber(n-1, 2) % M;
        }
        else if (digit == 9)
        {
            result = stairNumber(n-1, 8) % M;
        }
        else
        {
            result = stairNumber(n-1, digit - 1) % M + stairNumber(n-1, digit + 1) % M;
        }
        
        return cache[n][digit] = result % M;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        System.out.println(solve(n));
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        public static String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}

/**
 * Modular 한 것들의 합 또한 modular 연산 해줘야 한다.
 * 과하다 싶을정도로 modular 연산 해버려라.
 */