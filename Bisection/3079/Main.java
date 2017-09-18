import java.util.*;
import java.io.*;

/**
 * problem 3079
 * 입국심사
 * https://www.acmicpc.net/problem/3079
 * written by progresivoJS on 2017.09.18
 */
public class Main
{
    public static void solve(long[] time, long m)
    {
        long lo = 0;
        long hi = (long)1e18;
        
        while (lo < hi)
        {
            long mid = (lo + hi) / 2;
            
            if (ok(mid, time, m))
                hi = mid;
            else
                lo = mid + 1;
        }
        
        System.out.println(lo);
    }
    
    // 각 심사대가 걸리는 시간이 time일 때, mid 시간동안 m명이 심사를 모두 받을 수 있는가?
    private static boolean ok(long mid, long[] time, long m)
    {
        long count = 0;
        for (int i = 0; i < time.length; i++)
        {
            count += mid / time[i];
            if (count >= m)
                return true;
        }
            
        if (count >= m)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        long n = In.nextLong();
        long m = In.nextLong();
        
        long[] time = new long[(int)n];
        for (int i = 0; i < n; i++)
            time[i] = In.nextLong();
        
        solve(time, m);
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