import java.util.*;
import java.io.*;

/**
 * problem 9470
 * Strahler 순서
 * https://www.acmicpc.net/problem/9470
 * written by progresivoJS on 2017.09.29
 */
public class Main
{
    private static StringBuilder str;
    
    public static void solve(int test, LinkedList<Integer>[] adj, int[] ind)
    {
        int n = adj.length;
        Queue<Integer> q = new LinkedList<>();
        int[] order = new int[n];
        int[] maxOrder = new int[n];
        
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
            {
                order[i] = 1;
                q.add(i);
            }
        
        int result = 0;
        for (int i = 0; i < n; i++)
        {
            int v = q.poll();
            result = Math.max(result, order[v]);
            
            for (int w : adj[v])
            {
                if (maxOrder[w] < order[v])
                {
                    order[w] = order[v];
                    maxOrder[w] = order[v];
                }
                else if (maxOrder[w] == order[v])
                {
                    order[w] = order[v] + 1;
                }
                
                if (--ind[w] == 0)
                    q.add(w);
            }
        }
        
        str.append(test).append(' ').append(result).append('\n');
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int k = In.nextInt(); // case number
            int n = In.nextInt(); // node 수
            int m = In.nextInt(); // edge 수
            
            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            int[] ind = new int[n];
            
            for (int i = 0; i < m; i++)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                ind[to] ++;
                adj[from].add(to);
            }
            
            solve(k, adj, ind);
        }
        
        System.out.println(str.toString());
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