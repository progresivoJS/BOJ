import java.util.*;
import java.io.*;

/**
 * problem 1947
 * 선물 전달
 * 완전순열 참조..
 * https://www.acmicpc.net/problem/1947
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static long[] cache;
    private static long M = 1000000000;
    
    public static long solve(int n)
    {
        Main.n = n;
        cache = new long[n+1];
        Arrays.fill(cache, -1);
        
        return give(n);
    }
    
    private static long give(int n)
    {
        if (n == 1)
            return 0;
        else if (n == 2)
            return 1;
        else if (n == 3)
            return 2;
            
        if (cache[n] != -1)
            return cache[n];
        
        long result = 0;
        result = (result + ((n-1) * give(n-1)) % M) % M;
        result = (result + ((n-1) * give(n-2)) % M) % M;
        
        return cache[n] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        // int n = In.nextInt();
        // System.out.println(solve(n));
        
        for (int i = 1; i < 100; i++)
            System.out.println(solve(i));
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