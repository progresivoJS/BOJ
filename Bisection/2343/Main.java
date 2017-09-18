import java.util.*;
import java.io.*;

/**
 * problem 2343
 * 기타 레슨
 * https://www.acmicpc.net/problem/2343
 * written by progresivoJS on 2017.09.18
 */
public class Main
{
    private static long[] lesson;
    private static long m, max;
    // lesson들을 m개의 blue-lay에 넣는다.
    // 이 때, blue-ray의 최소 길이를 구하라.
    // 단 blue-ray들의 길이는 모두 같아야 하고, 강의들은 순차적으로 들어가야만 한다.
    public static void solve(long[] lesson, long m)
    {
        Main.lesson = lesson;
        Main.m = m;
        
        max = 0;
        for (int i = 0; i < lesson.length; i++)
            max = Math.max(max, lesson[i]);
            
        long lo = 0;
        long hi = (long)1e10;
        
        while (lo < hi)
        {
            long mid = lo + (hi - lo) / 2;
            
            if (ok(mid))
                hi = mid;
            else
                lo = mid + 1;
        }
        
        System.out.println(lo);
    }
    
    // blue-ray 각 길이가 mid일 때, m개의 blue-ray에 모든 lesson들이 들어갈 수 있는가?
    private static boolean ok(long mid)
    {
        if (mid < max)
            return false;
            
        long length = mid;
        long count = 1;
        for (int i = lesson.length - 1; i >= 0; i--)
        {
            if (length < lesson[i])
            {
                count ++;
                length = mid;
            }
            
            length -= lesson[i];
        }
        
        if (count <= m)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        long m = In.nextLong();
        long[] lesson = new long[n];
        for (int i = 0; i < n; i++)
            lesson[i] = In.nextInt();
        solve(lesson, m);
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