import java.util.*;
import java.io.*;

/**
 * problem 10282
 * 해킹
 * https://www.acmicpc.net/problem/10282
 * written by progresivoJS on 2017.10.15
 */
public class Main
{
    private static int INF = 987654321;
    private static int[] dist;
    private static PriorityQueue<Node> pq;
    private static StringBuilder str;
    
    public static void solve(LinkedList<Edge>[] adj, int s)
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
        
        int count = 0;
        int max = 0;
        for (int i : dist)
            if (i != INF)
            {
                count++;
                max = Math.max(max, i);
            }

        str.append(count + " " + max).append('\n');
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
            int d = In.nextInt();
            int infected = In.nextInt() - 1;
            
            LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            
            while (d-- > 0)
            {
                int to = In.nextInt() - 1;
                int from = In.nextInt() - 1;
                adj[from].add(new Edge(from, to, In.nextInt()));
            }
            
            solve(adj, infected);
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
