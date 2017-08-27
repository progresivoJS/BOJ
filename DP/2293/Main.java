import java.util.*;
import java.io.*;
public class Main
{
    private static int[] coins;
    private static int[][] cache;
    private static int n;
    public static int solve(int[] coins, int k)
    {
        Main.coins = coins;
        n = coins.length;
        cache = new int[n][k + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(cache[i], -1);
        
        return useCoin(n-1, k);
    }
    
    /**
     * 0 ~ index 번째 동전을 사용하여, value 값을 만들 때
     * 경우의 수.
     */
    private static int useCoin(int index, int value)
    {
        if (value == 0)
            return 1;
        else if (value < 0)
            return 0;
        else if (index < 0)
            return 0;
        
        if (cache[index][value] != -1)
            return cache[index][value];
        
        int result = 0;
        result += useCoin(index, value - coins[index]) + useCoin(index-1, value);
        return cache[index][value] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();
        int[] coins = new int[n];
        for (int i = 0; i < n; i++)
            coins[i] = In.nextInt();
        System.out.println(solve(coins, k));
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
 * 문제 정의를 잘못해서 매우 헤맴.
 * 사용한 경우, 사용하지 않은 경우 나눠서 하면 편해.
 * array 사용 범위를 줄여가면서, 각 index마다 사용한 case랑
 * 사용하지 않은 case 나눠서 부분문제 푼다.
 */