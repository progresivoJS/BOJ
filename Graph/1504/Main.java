import java.util.*;
import java.io.*;

/**
 * problem 1504
 * 특정한 최단 경로
 * https://www.acmicpc.net/problem/1504
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    private static int INF = 987654321;
    private static long[] dist;
    private static PriorityQueue<Pair> pq;
    
    public static void solve(LinkedList<Edge>[] adj, int x, int y)
    {
        int n = adj.length;
        
        long[] distFromStart = dijkstra(adj, 0);
        long[] distFromX = dijkstra(adj, x);
        long[] distFromY = dijkstra(adj, y);
        
        long a = distFromStart[x] + distFromX[y] + distFromY[n - 1];
        long b = distFromStart[y] + distFromY[x] + distFromX[n - 1];
        
        long distance = Math.min(a, b);
        if (distance >= INF)
            System.out.println(-1);
        else
            System.out.println(distance);
    }
    
    private static long[] dijkstra(LinkedList<Edge>[] adj, int start)
    {
        int n = adj.length;
        
        dist = new long[n];
        Arrays.fill(dist, INF);
        pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Pair(start, dist[start]));
        
        while (!pq.isEmpty())
        {
            Pair top = pq.poll();
            int v = top.index;
            long cost = top.key;
            
            if (dist[v] < cost) continue;
            
            for (Edge e : adj[v])
                relax(e);
        }
        
        return dist;
    }
    
    private static void relax(Edge e)
    {
        int v = e.from;
        int w = e.to;
        if (dist[w] > dist[v] + e.weight)
        {
            dist[w] = dist[v] + e.weight;
            pq.add(new Pair(w, dist[w]));
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
        
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            int cost = In.nextInt();
            adj[from].add(new Edge(from, to, cost));
            adj[to].add(new Edge(to, from, cost));
        }
        
        int x = In.nextInt() - 1;
        int y = In.nextInt() - 1;
        
        solve(adj, x, y);
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
    
    private static class Pair implements Comparable<Pair>
    {
        int index;
        long key;
        public Pair(int index, long key)
        {
            this.index = index;
            this.key = key;
        }
        
        public int compareTo(Pair other)
        {
            if (this.key == other.key)
                return 0;
            else if (this.key < other.key)
                return -1;
            return 1;
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