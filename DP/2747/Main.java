import java.util.*;
import java.io.*;

/**
 * problem 2747
 * 피보나치 수
 * https://www.acmicpc.net/problem/2747
 * written by progresivoJS
 */
public class Main
{
    private static int[] cache;
    public static int solve(int n)
    {
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        return fibo(n);
    }
    
    private static int fibo(int n)
    {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        
        if (cache[n] != -1)
            return cache[n];
        
        return cache[n] = fibo(n-1) + fibo(n-2);
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