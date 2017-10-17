import java.util.*;
import java.io.*;

/**
 * problem 2075
 * N번째 큰 수
 * https://www.acmicpc.net/problem/2075
 * written by progresivoJS on 2017.10.17
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int n = In.nextInt();
        for (int i = 0; i < n * n; i++)
        {
            int m = In.nextInt();
            if (pq.size() < n)
                pq.add(m);
            else
            {
                int min = pq.poll();
                if (min < m)
                    pq.add(m);
                else
                    pq.add(min);
            }
        }
        
        System.out.println(pq.poll());
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