import java.util.*;
import java.io.*;

/**
 * problem 2014
 * 소수의 곱
 * https://www.acmicpc.net/problem/2014
 * written by progresivoJS on 2017.10.18
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int k = In.nextInt();
        int n = In.nextInt();
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (k-- > 0)
            pq.add(In.nextInt());
        
        int rank = 1;
        int prevTop = -1;
        while (true)
        {
            int top = pq.poll();

            if (top == prevTop) continue;
            
            if (rank == n)
            {
                System.out.println(top);
                return;
            }
            
            int[] products = new int[pq.size()];
            int index = 0;
            for (int i : pq)
                products[index++] = i * top;
            for (int i : products)
            {
                if (i >= 0)
                    pq.add(i);
            }
            
            pq.add(top * top);
            
            prevTop = top;
            rank++;
        }
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