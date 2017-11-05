import java.util.*;
import java.io.*;

/**
 * problem 11657
 * 타임머신
 * https://www.acmicpc.net/problem/11657
 * written by progresivoJS on 2017.11.05
 */
public class Main
{
    public static final int INF = 987654321;
    public static void solve(int n, Edge[] adj)
    {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;
        
        for (int i = 0; i < n; i++)
            for (Edge e : adj)
            {
                int from = e.from;
                int to = e.to;
                
                if (dist[from] != INF && dist[to] > dist[from] + e.weight)
                {
                    dist[to] = dist[from] + e.weight;
                    if (i == n - 1)
                    {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < n; i++)
        {
            if (dist[i] != INF)
                str.append(dist[i]).append('\n');
            else
                str.append(-1).append('\n');
        }
        System.out.println(str);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        // LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
        // for (int i = 0; i < n; i++)
        //     adj[i] = new LinkedList<>();
        Edge[] adj = new Edge[m];
        
        for (int i = 0; i < m; i++)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            int cost = In.nextInt();
            adj[i] = new Edge(from, to, cost);
            // adj[from].add(new Edge(from, to, cost));
        }
        
        solve(n, adj);
    }
    
    private static class Edge
    {
        int from , to, weight;
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