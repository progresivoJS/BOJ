import java.util.*;
import java.io.*;

/**
 * problem 1654
 * 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 * written by progresivoJS on 2017.09.16
 */
public class Main
{
    public static void solve(long[] lines, int n, long max)
    {
        long lo = 0;
        long hi = max + 1;
        
        // 반복문 불변식
        // 1. lo로 자르면 항상 n개이상 만들 수 있음.
        // 2. hi로 자르면 n개 못 만듬.
        while (lo + 1 < hi)
        {
            long mid = (lo + hi) / 2;
            
            long count = 0;
            for (int i = 0; i < lines.length; i++)
                count += lines[i] / mid;
               
            if (count < n)
                hi = mid;
            else
                lo = mid;
        }
        System.out.println(lo);
    }
    public static void main(String[] args)
    {
        In.init();
        int k = In.nextInt();
        int n = In.nextInt();
        long[] lines = new long[k];
        long max = 0;
        for (int i = 0; i < k; i++)
        {
            lines[i] = In.nextLong();
            max = Math.max(max, lines[i]);
        }
        solve(lines, n, max);
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
        
        public static long nextLong()
        {
            return Long.parseLong(next());
        }
    }
}