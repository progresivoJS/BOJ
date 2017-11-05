import java.util.*;
import java.io.*;

/**
 * problem 2839
 * 설탕 배달
 * https://www.acmicpc.net/problem/2839
 * written by progresivoJS on 2017.11.05
 */
public class Main
{
    private static int[] cache;
    private static int INF = 987654321;
    public static void solve(int n)
    {
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        
        int result = deliver(n);
        if (result >= INF)
            System.out.println(-1);
        else
            System.out.println(result);
    }
    
    private static int deliver(int n)
    {
        if (n == 0)
            return 0;
        if (n < 0)
            return INF;
        
        if (cache[n] != -1)
            return cache[n];
        
        int result = Math.min(1 + deliver(n - 3), 1 + deliver(n - 5));
        return cache[n] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        solve(n);
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