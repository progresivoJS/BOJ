import java.util.*;
import java.io.*;

/**
 * problem 2698
 * 인접한 비트의 개수
 * https://www.acmicpc.net/problem/2698
 * written by progresivoJS
 */
public class Main
{
    private static int[][] cache;
    private static void solve(int test)
    {
        cache = new int[101][101];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            int k = In.nextInt();
            
            System.out.println(bitCount(n, k));
        }
    }
    
    private static int bitCount(int n, int k)
    {
        if (n == 1 && k == 0)
            return 2;
        else if (n == 2 && k == 0)
            return 3;
        else if (n-2 == k)
            return 2;
        else if (n-1 == k)
            return 1;
            
        if (cache[n][k] != -1)
            return cache[n][k];
            
        int result = 0;
        result += bitCount(n-1, k);
        
        int i = k;
        int j = n-2;
        while (i >= 0)
        {
            result += bitCount(j, i);
            i--;
            j--;
        }
        return cache[n][k] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        solve(test);
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