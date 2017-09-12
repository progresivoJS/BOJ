import java.util.*;
import java.io.*;

/**
 * problem 2225
 * 합분해
 * https://www.acmicpc.net/problem/2225
 * written by progresivoJS
 */
public class Main
{
    private static int n, k;
    private static int[][] cache;
    private static int M = 1000000000;
    public static int solve(int n, int k)
    {
        Main.n = n;
        Main.k = k;
        
        cache = new int[k + 1][n + 1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
                
        
        int result = 0;
        for (int i = 0; i <= n; i++)
            result = (result + decompose(1, i)) % M;
        return result;
    }
    
    private static int decompose(int count, int sum)
    {
        if (count == k && sum == n)
            return 1;
        if (count > k || sum > n)
            return 0;
        
        if (cache[count][sum] != -1)
            return cache[count][sum];
        
        int result = 0;
        for (int i = 0; i <= n; i++)
            result = (result + decompose(count + 1, sum + i)) % M;
        return cache[count][sum] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();
        
        System.out.println(solve(n, k));
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