import java.util.*;
import java.io.*;

/**
 * problem 2211
 * 네트워크 복구
 * https://www.acmicpc.net/problem/2211
 * written by progresivoJS on 2017.10.16
 */
public class Main
{
    private static int INF = 987654321;
    private static PriorityQueue<Node> pq;
    private static int[] dist;
    private static Edge[] edgeTo;
    
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        pq = new PriorityQueue<>();
        edgeTo = new Edge[n];
        
        dist = new int[n];
        Arrays.fill(dist, INF);
        int s = 0;
        dist[s] = 0;
        
        pq.add(new Node(dist[s], s));
        
        while (!pq.isEmpty())
        {
            int v = pq.peek().index;
            int cost = pq.peek().key;
            pq.poll();
            
            if (dist[v] < cost) continue;
            
            for (Edge e : adj[v])
                relax(e);
        }
        
        for (int w = 1; w < n; w++)
            for (Edge e = edgeTo[w]; e != null; e = edgeTo[e.from])
                e.restored = true;
        
        StringBuilder str = new StringBuilder();
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            Edge e = edgeTo[i];
            if (e != null && e.restored)
            {
                count++;
                str.append((e.from + 1) + " " + (e.to + 1)).append('\n');
            }
        }
        
        System.out.println(count);
        System.out.println(str.toString());
    }
    
    private static void relax(Edge e)
    {
        int v = e.from;
        int w = e.to;
        
        if (dist[w] > dist[v] + e.weight)
        {
            dist[w] = dist[v] + e.weight;
            pq.add(new Node(dist[w], w));
            edgeTo[w] = e;
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
            int weight = In.nextInt();
            
            adj[from].add(new Edge(from, to, weight));
            adj[to].add(new Edge(to, from, weight));
        }
        
        solve(adj);
    }
    
    private static class Node implements Comparable<Node>
    {
        int key, index;
        public Node(int key, int index)
        {
            this.key = key;
            this.index = index;
        }
        
        public int compareTo(Node other)
        {
            return this.key - other.key;
        }
    }
    
    private static class Edge
    {
        int from, to, weight;
        boolean restored;
        public Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.restored = false;
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