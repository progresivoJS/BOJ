import java.util.*;
import java.io.*;

/**
 * problem 10530
 * Flowery Trails
 * https://www.acmicpc.net/problem/10530
 * written by progresivoJS on 2017.10.15
 */
public class Main
{
    private static int INF = 987654321;
    private static int[] dist;
    private static PriorityQueue<IndexPoint> pq;
    
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        int[] distFromS = dijkstra(adj, 0);
        int[] distFromT = dijkstra(adj, n - 1);
        
        int result = 0;
        int shortestPathLength = distFromS[n-1];
        for (int v = 0; v < n; v++)
            for (Edge e : adj[v])
            {
                int w = e.to;
                if (distFromS[v] + distFromT[w] + e.weight == shortestPathLength)
                    result += e.weight;
            }
            
        System.out.println(result * 2);
    }
    
    private static int[] dijkstra(LinkedList<Edge>[] adj, int s)
    {
        int n = adj.length;
        
        pq = new PriorityQueue<>();
        dist = new int[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        pq.add(new IndexPoint(s, dist[s]));
        
        while (!pq.isEmpty())
        {
            int v = pq.peek().index;
            int cost = pq.peek().key;
            pq.poll();
            
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
            pq.add(new IndexPoint(w, dist[w]));
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int edge = In.nextInt();
        
        LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
        
        while (edge-- > 0)
        {
            int from = In.nextInt();
            int to = In.nextInt();
            int cost = In.nextInt();
            adj[from].add(new Edge(from, to, cost));
            adj[to].add(new Edge(to, from, cost));
        }
        
        solve(adj);
    }
    
    private static class IndexPoint implements Comparable<IndexPoint>
    {
        int index, key;
        
        public IndexPoint(int index, int key)
        {
            this.index = index;
            this.key = key;
        }
        
        public int compareTo(IndexPoint other)
        {
            return this.key - other.key;
        }
    }
    
    private static class Edge
    {
        int from, to;
        int weight;
        
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