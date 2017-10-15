import java.util.*;
import java.io.*;

/**
 * problem 9370
 * 미확인 도착지
 * https://www.acmicpc.net/problem/9370
 * written by progresivoJS on 2017.10.15
 */
public class Main
{
    private static int INF = 987654321;
    private static int[] dist;
    private static PriorityQueue<Node> pq;
    private static StringBuilder str;
    
    public static void solve(LinkedList<Edge>[] adj, int s, int g, int h, int[] candidate)
    {
        int n = adj.length;
        
        int criticalEdgeWeight = 0;
        for (Edge e : adj[g])
            if (e.to == h) criticalEdgeWeight = e.weight;
        
        int[] distFromS = dijkstra(adj, s);
        LinkedList<Integer> result = new LinkedList<>();
        for (int c : candidate)
        {
            int[] distFromC = dijkstra2(adj, c, g, h);
            int shortestPathLength = distFromS[c];
            
            if (distFromS[g] + distFromC[h] + criticalEdgeWeight == shortestPathLength || distFromC[g] + distFromS[h] + criticalEdgeWeight == shortestPathLength)
                result.add(c + 1);
        }
        
        Collections.sort(result);
        for (int r : result)
            str.append(r + " ");
        str.append('\n');
    }
    
    // distance from s to target.
    private static int[] dijkstra(LinkedList<Edge>[] adj, int s)
    {
        int n = adj.length;
        
        pq = new PriorityQueue<>();
        dist = new int[n];
        Arrays.fill(dist, INF);
        
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
        
        return dist;
    }
    
    // distance from s to target.
    private static int[] dijkstra2(LinkedList<Edge>[] adj, int s, int g, int h)
    {
        int n = adj.length;
        
        pq = new PriorityQueue<>();
        dist = new int[n];
        Arrays.fill(dist, INF);
        
        dist[s] = 0;
        pq.add(new Node(dist[s], s));
        
        boolean[] marked = new boolean[2];
        
        while (!pq.isEmpty())
        {
            int v = pq.peek().index;
            int cost = pq.peek().key;
            pq.poll();
            if (dist[v] < cost) continue;
            
            if (v == g) marked[0] = true;
            if (v == h) marked[1] = true;
            if (marked[0] && marked[1]) break;
            
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
            pq.add(new Node(dist[w], w));
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            int t = In.nextInt();
            
            int s = In.nextInt() - 1;
            int g = In.nextInt() - 1;
            int h = In.nextInt() - 1;
            
            LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            int[] candidate = new int[t];
            
            while (m-- > 0)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                int weight = In.nextInt();
                adj[from].add(new Edge(from, to, weight));
                adj[to].add(new Edge(to, from, weight));
            }
            
            for (int i = 0; i < t; i++)
                candidate[i] = In.nextInt() - 1;
                
            solve(adj, s, g, h, candidate);
        }
        
        System.out.println(str.toString());
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