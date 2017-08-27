import java.util.*;
import java.io.*;
public class Main
{
    private static int[] coins;
    private static int n, k;
    private static int[] cache;
    private static int M = 987654321;
    
    public static void solve(int[] coins, int k)
    {
        Main.coins = coins;
        n = coins.length;
        Main.k = k;
        
        cache = new int[k+1];
        Arrays.fill(cache, -1);
        
        int result = M;
        result = Math.min(result, useCoin(k));
        if (result != M)
            System.out.println(result);
        else
            System.out.println(-1);
    }
    
    /**
     * 합 k을 만들고자 할 때, 사용하는 coin 갯수의 최솟값.
     */
    private static int useCoin(int k)
    {
        if (k == 0)
            return 0;
        if (k < 0)
            return M;
            
        if (cache[k] != -1)
            return cache[k];
            
        int result = M;
        for (int i = 0; i < n; i++)
            result = Math.min(result, 1 + useCoin(k - coins[i]));
        return cache[k] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        int n = In.nextInt();
        int k = In.nextInt();
        
        int[] coins = new int[n];
        for (int i = 0; i < n; i++)
            coins[i] = In.nextInt();
        solve(coins, k);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
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
 * cache dimension을 줄일 수 있으면 최대한 줄이는 것이,
 * 반복을 늘리고
 * 결국 속도를 더 빠르게 만드는 법.
 */