import java.util.*;
import java.io.*;

/**
 * problem 14721
 * 성적표
 * https://www.acmicpc.net/problem/14721
 * written by progresivoJS on 2017.11.01
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++)
        {
            x[i] = In.nextInt();
            y[i] = In.nextInt();
        }


        long min = Long.MAX_VALUE;
        int a = -1;
        int b = -1;
        for (int i = 1; i <= 100; i++)
            for (int j = 1; j <= 100; j++)
            {
                long value = rss(i, j, x, y);
                if (min > value)
                {
                    min = value;
                    a = i;
                    b = j;
                }
            }
        System.out.println(a + " " + b);
    }

    private static long rss(int a, int b, int[] x, int [] y)
    {
        int n = x.length;
        long sum = 0;
        for (int i = 0; i < n; i++)
        {
            long half = (y[i] - (a * x[i] + b));
            sum += half * half;
        }
        return sum;
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
