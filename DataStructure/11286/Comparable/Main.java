import java.util.*;
import java.io.*;

/**
 * problem 11286
 * 절대값 힙
 * https://www.acmicpc.net/problem/11286
 * written by progresivoJS on 2017.10.17
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        StringBuilder str = new StringBuilder();

        int n = In.nextInt();
        PriorityQueue<AbsoluteValue> pq = new PriorityQueue<>();
        while (n-- > 0)
        {
            int m = In.nextInt();
            if (m == 0)
            {
                if (pq.isEmpty())
                    str.append(0).append('\n');
                else
                    str.append(pq.poll().n).append('\n');
            }
            else
                pq.add(new AbsoluteValue(m));
        }

        System.out.println(str);
    }

    private static class AbsoluteValue implements Comparable<AbsoluteValue>
    {
        int n;
        public AbsoluteValue(int n)
        {
            this.n = n;
        }

        public int compareTo(AbsoluteValue other)
        {
            int abs = Math.abs(this.n) - Math.abs(other.n);
            if (abs == 0)
                return this.n - other.n;
            else
                return abs;
        }
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
