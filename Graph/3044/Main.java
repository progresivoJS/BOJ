import java.util.*;
import java.io.*;

/**
 * problem 3044
 * 자전거 경주 준비하기
 * https://www.acmicpc.net/problem/3044
 * written by progresivoJS on 2017.10.01
 */
public class Main
{
    private static int pathCount;
    private static boolean[] marked;
    public static void solve(LinkedList<Integer>[] adj, int[] ind)
    {
        int n = adj.length;
        marked = new boolean[n];
        dfs_forComponent(adj, 0);
        
        int connectedCount = 0;
        for (int i = 0; i < marked.length; i++)
            if (marked[i])
                connectedCount++;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        
        for (int i = 0; i < connectedCount; i++)
        {
            if (q.isEmpty())
            {
                System.out.println("inf");
                return;
            }
            
            int v = q.poll();
            for (int w : adj[v])
                if (--ind[w] == 0)
                    q.add(w);
        }
        
        pathCount = 0;
        dfs_forCount(adj, 0);
        
        System.out.println(pathCount);
    }
    
    private static void dfs_forCount(LinkedList<Integer>[] adj, int v)
    {
        for (int w : adj[v])
        {
            if (w == 1)
            {
                pathCount++;
                pathCount %= 1000000000;
            }
            dfs_forCount(adj, w);
        }
    }
    
    private static void dfs_forComponent(LinkedList<Integer>[] adj, int v)
    {
        marked[v] = true;
        for (int w : adj[v])
            if (!marked[w])
                dfs_forComponent(adj, w);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
        int[] ind = new int[n];
        
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            adj[from].add(to);
            ind[to]++;
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