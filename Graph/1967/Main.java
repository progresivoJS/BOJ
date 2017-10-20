import java.util.*;
import java.io.*;

/**
 * problem 1967
 * 트리의 지름
 * https://www.acmicpc.net/problem/1967
 * written by progresivoJS on 2017.10.20
 */
public class Main
{
    private static boolean[] marked;
    private static int far;
    private static int max;

    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        
        marked = new boolean[n];
        max = 0;
        far = 0;
        dfs2(adj, 0, 0);

        int diameter = 0;
        marked = new boolean[n];
        diameter = dfs(adj, far);
        System.out.println(diameter);
    }
    
    private static void dfs2(LinkedList<Edge>[] adj, int v, int length)
    {
        marked[v] = true;
        if (max < length)
        {
            max = length;
            far = v;
        }
        
        for (Edge e : adj[v])
        {
            int w = e.to;
            if (!marked[w])
                dfs2(adj, w, length + e.weight);
        }
    }

    private static int dfs(LinkedList<Edge>[] adj, int v)
    {
        marked[v] = true;
        int result = 0;
        for (Edge e : adj[v])
        {
            int w = e.to;
            int weight = e.weight;
            if (!marked[w])
                result = Math.max(result, weight + dfs(adj, w));
        }

        return result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        
        LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();

        while (n-- > 1)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            int weight = In.nextInt();

            adj[from].add(new Edge(from, to, weight));
            adj[to].add(new Edge(to, from, weight));
        }

        solve(adj);
    }

    private static class Edge
    {
        int from, to, weight;

        public Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
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