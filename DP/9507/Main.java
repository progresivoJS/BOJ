import java.util.*;
import java.io.*;

/**
 * problem 9507
 * Generations of Tribbles
 * https://www.acmicpc.net/problem/9507
 * written by progresivoJS
 */
public class Main
{
    private static long[] cache;
    public static void solve(int test)
    {
        cache = new long[67 + 1];
        Arrays.fill(cache, -1);
        
        for (int i = 0; i < test; i++)
            System.out.println(koong(In.nextInt()));
    }
    
    private static long koong(int n)
    {
        if (n < 2)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        
        if (cache[n] != -1)
            return cache[n];
            
        return cache[n] = koong(n-1) + koong(n-2) + koong(n-3) + koong(n-4);
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