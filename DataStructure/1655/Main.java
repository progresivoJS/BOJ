import java.util.*;
import java.io.*;

/**
 * problem 1655
 * 가운데를 말해요
 * https://www.acmicpc.net/problem/1655
 * written by progresivoJS on 2017.10.17
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>();
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        
        StringBuilder str = new StringBuilder();
        
        for (int i = 0; i < n; i++)
        {
            int m = In.nextInt();
            
            if (i == 0)
                maxPQ.add(-m);
            else if (i == 1)
            {
                int left = -maxPQ.poll();
                if (left > m)
                {
                    minPQ.add(left);
                    maxPQ.add(-m);
                }
                else
                {
                    minPQ.add(m);
                    maxPQ.add(-left);
                }
            }
            else
            {
                int left = -maxPQ.peek();
                int right = minPQ.peek();
                
                if (minPQ.size() == maxPQ.size())
                {
                    if (m < right)
                        maxPQ.add(-m);
                    else
                    {
                        maxPQ.add(-minPQ.poll());
                        minPQ.add(m);
                    }
                }
                else
                {
                    if (m < left)
                    {
                        minPQ.add(-maxPQ.poll());
                        maxPQ.add(-m);
                    }
                    else
                        minPQ.add(m);
                }
            }
            
            str.append(-maxPQ.peek() + "\n");
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