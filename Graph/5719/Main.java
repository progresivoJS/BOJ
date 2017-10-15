import java.util.*;
import java.io.*;

/**
 * problem 5719
 * 거의 최단 경로
 * https://www.acmicpc.net/problem/5719
 * written by progresivoJS on 2017.10.15
 */
public class Main
{
    private static int INF = 987654321;
    private static int[] dist;
    private static PriorityQueue<Node> pq;
    
    private static StringBuilder str;
    
    private static void solve(LinkedList<Edge>[] adj, int s, int t)
    {
        int n = adj.length;
        int[] distFromS = dijkstra(adj, s);
        int[] distFromT = dijkstra(reverse(adj), t);
        
        int shortestPathLength = distFromS[t];
        for (int v = 0; v < n; v++)
            for (Edge e : adj[v])
            {
                int w = e.to;
                if (distFromS[v] + distFromT[w] + e.weight == shortestPathLength)
                    e.onOptimalPath = true;
            }
        
        int[] distAlmostOptimal = dijkstra(adj, s);
        str.append(distAlmostOptimal[t] == INF ? -1 : distAlmostOptimal[t]).append('\n');
    }
    
    private static LinkedList<Edge>[] reverse(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        LinkedList<Edge>[] reverse = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n ;i++)
            reverse[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (Edge e : adj[v])
            {
                int w = e.to;
                reverse[w].add(new Edge(w, v, e.weight));
            }
        return reverse;
    }
    
    private static int[] dijkstra(LinkedList<Edge>[] adj, int s)
    {
        int n = adj.length;
        pq = new PriorityQueue<>();
        
        dist = new int[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;
        
        pq.add(new Node(s, dist[s]));
        
        while (!pq.isEmpty())
        {
            int v = pq.peek().index;
            int cost = pq.peek().key;
            pq.poll();
            
            if (dist[v] < cost) continue;
            
            for (Edge e : adj[v])
                if (!e.onOptimalPath)
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
            pq.add(new Node(w, dist[w]));
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        
        str = new StringBuilder();
        
        while (true)
        {
            int n = In.nextInt();
            int edge = In.nextInt();
            
            if (n == 0 && edge == 0)
                break;
            
            int s = In.nextInt();
            int t = In.nextInt();
            
            LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            
            while (edge-- > 0)
            {
                int from = In.nextInt();
                int to = In.nextInt();
                int weight = In.nextInt();
                adj[from].add(new Edge(from, to, weight));
            }
            solve(adj, s, t);
        }
        
        System.out.println(str.toString());
    }
    
    private static class Node implements Comparable<Node>
    {
        int index, key;
        public Node(int index, int key)
        {
            this.index = index;
            this.key = key;
        }
        
        public int compareTo(Node other)
        {
            return this.key - other.key;
        }
    }
    
    private static class Edge
    {
        int from, to, weight;
        boolean onOptimalPath;
        
        public Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
            
            onOptimalPath = false;
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