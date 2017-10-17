import java.util.*;
import java.io.*;

/**
 * problem 11279
 * 최대 힙
 * https://www.acmicpc.net/problem/11279
 * written by progresivoJS on 2017.10.17
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        StringBuilder str = new StringBuilder();

        int n = In.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (n-- > 0)
        {
            int m = In.nextInt();
            if (m == 0)
            {
                if (pq.isEmpty())
                    str.append(0).append('\n');
                else
                    str.append(-pq.poll()).append('\n');
            }
            else
                pq.add(-m);
        }

        System.out.println(str);
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
