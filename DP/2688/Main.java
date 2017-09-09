import java.util.*;
import java.io.*;

/**
 * problem 2688
 * 줄어들지 않아
 * https://www.acmicpc.net/problem/2688
 * written by progresivoJS
 */
public class Main
{
    private static long[][] cache;
    
    private static long solve(int n)
    {
        cache = new long[n + 1][10];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        long result = 0;
        for (int i = 0; i < 10; i++)
            result += notDecreasing(n, i);
        
        return result;
    }
    
    private static long notDecreasing(int n, int index)
    {
        if (n == 1)
            return 1;
            
        if (cache[n][index] != -1)
            return cache[n][index];
            
        long result = 0;
        for (int i = index; i < 10; i++)
            result += notDecreasing(n-1, i);
        
        return cache[n][index] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            System.out.println(solve(n));
        }
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