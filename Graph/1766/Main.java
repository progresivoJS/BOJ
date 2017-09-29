import java.util.*;
import java.io.*;

/**
 * problem 1766
 * 문제집
 * https://www.acmicpc.net/problem/1766
 * written by progresivoJS on 2017.09.28
 */
public class Main
{
    public static void solve(LinkedList<Integer>[] adj, int[] ind)
    {
        int n = adj.length;
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
                minPQ.add(i);
        
        for (int i = 0; i < n; i++)
        {
            int v = minPQ.poll();
            result[i] = v;
            
            for (int w : adj[v])
                if (--ind[w] == 0)
                    minPQ.add(w);
        }
        
        StringBuilder str = new StringBuilder();
        for (int i : result)
            str.append(i + 1).append(' ');
        System.out.println(str.toString());
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();
        
        int[] ind = new int[n];
        
        for (int i = 0; i < m; i++)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            ind[to] ++;
            adj[from].add(to);
        }
        
        solve(adj, ind);
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