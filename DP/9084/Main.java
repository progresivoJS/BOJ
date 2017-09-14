import java.util.*;
import java.io.*;

/**
 * problem 9084
 * 동전
 * https://www.acmicpc.net/problem/9084
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    private static int[] coins;
    private static int m, n;
    
    private static int[][] cache;
    
    public static int solve(int[] coins, int m)
    {
        Main.coins = coins;
        Main.m = m;
        Main.n = coins.length;
        
        cache = new int[coins.length][m + 1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = 0;
        
        for (int i = 0; i < n; i++)
            result += sumCoins(i, coins[i]);
        
        return result;
    }
    
    // 현재까지의 동전의 가치 합이 sum이고 
    // [index, last]까지의 동전을 써서,
    // m 원을 만들 수 있는 경우의 수.
    private static int sumCoins(int index, int sum)
    {
        if (sum == m)
            return 1;
        if (sum > m)
            return 0;
        
        if (cache[index][sum] != -1)
            return cache[index][sum];
            
        int result = 0;
        for (int next = index; next < n; next++)
            result += sumCoins(next, sum + coins[next]);
        return cache[index][sum] = result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            int[] coins = new int[n];
            for (int j = 0; j < n; j++)
                coins[j] = In.nextInt();
            int m = In.nextInt();
            
            out.write(String.valueOf(solve(coins, m)));
            out.write("\n");
        }
        out.close();
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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