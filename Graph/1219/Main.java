import java.util.*;
import java.io.*;

/**
 * problem 1219
 * 오민식의 고민
 * https://www.acmicpc.net/problem/1219
 * written by progresivoJS on 2017.11.05
 */
public class Main
{
    private static long INF = Long.MAX_VALUE;
    public static void solve(int n, int s, int e, Edge[] edges, boolean[][] connected, int[] money)
    {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[s] = -money[s];
        
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    connected[i][j] = connected[i][k] && connected[k][j];
        
        boolean hasCycle = false;
        for (int i = 0; i < n; i++)
        {
            for (Edge edge : edges)
            {
                int from = edge.from;
                int to = edge.to;
                if (dist[from] != INF && dist[to] > dist[from] + edge.weight - money[to])
                {
                    dist[to] = dist[from] + edge.weight - money[to];
                    if (i == n - 1 && connected[to][e])
                        hasCycle = true;
                }
            }
        }
        
        if (hasCycle)
            System.out.println("Gee");
        else if (dist[e] == INF)
            System.out.println("gg");
        else
            System.out.println(-dist[e]);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int s = In.nextInt();
        int e = In.nextInt();
        int m = In.nextInt();
        
        Edge[] edges = new Edge[m];
        boolean[][] connected = new boolean[n][n];
        int[] money = new int[n];
        
        for (int i = 0; i < m; i++)
        {
            int from = In.nextInt();
            int to = In.nextInt();
            int weight = In.nextInt();
            edges[i] = new Edge(from, to, weight);
            connected[from][to] = true;
        }
        
        for (int i = 0; i < n; i++)
            connected[i][i] = true;
        
        for (int i = 0; i < n; i++)
            money[i] = In.nextInt();
        
        solve(n, s, e, edges, connected, money);
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