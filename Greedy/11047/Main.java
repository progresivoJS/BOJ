import java.util.*;
import java.io.*;
public class Main
{
    /**
     * choice는 동전의 가치가 remain과 같거나 작은 동전 중에 가장 큰 것.
     */
    public static int solve(int[] coins, int k)
    {
        int n = coins.length;
        int remain = k;
        int count = 0;
        int index = n-1;
        while(index >= 0)
        {
            if (remain == 0)
                return count;
            if (remain >= coins[index])
            {
                remain -= coins[index];
                count++;
            }
            else
                index--;
        }
        
        return count;
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