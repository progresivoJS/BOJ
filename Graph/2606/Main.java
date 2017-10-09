import java.util.*;
import java.io.*;

/**
 * problem 2606
 * 바이러스
 * https://www.acmicpc.net/problem/2606
 * written by progresivoJS on 2017.10.09
 */
public class Main
{
    private static int count;
    private static boolean[] marked;
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        marked = new boolean[n];
        count = -1;
        dfs(adj, 0);
        System.out.println(count);
    }
    
    private static void dfs(LinkedList<Integer>[] adj, int v)
    {
        count++;
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs(adj, w);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
        
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            adj[from].add(to);
            adj[to].add(from);
        }
        
        solve(adj);
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