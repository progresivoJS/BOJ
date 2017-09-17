import java.util.*;
import java.io.*;

/**
 * problem 1300
 * k 번째 수
 * https://www.acmicpc.net/problem/1300
 * written by progresivoJS on 2017.09.17
 */
public class Main
{
    public static void solve(long n, long k)
    {
        long lo = 1;
        long hi = n * n;
        
        while (lo < hi)
        {
            long mid = (lo + hi) / 2;
            if (ok(mid, n, k))
                hi = mid;
            else
                lo = mid + 1;
        }
        
        System.out.println(lo);
    }
    
    // mid >= k 번째 element 를 만족합니까?
    private static boolean ok(long mid, long n, long k)
    {
        long count = 0;
        for (long i = 1; i <= n; i++)
            count += Math.min((mid / i), n);
        if (count >= k)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        long n = In.nextLong();
        long k = In.nextLong();
        solve(n, k);
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
    
        public static long nextLong()
        {
            return Long.parseLong(next());
        }
    }
}