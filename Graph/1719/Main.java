import java.util.*;
import java.io.*;

/**
 * problem 1719
 * 택배
 * https://www.acmicpc.net/problem/1719
 * written by progresivoJS on 2017.10.16
 */
public class Main
{
    private static int INF = 987654321;
    private static int[][] dist;
    private static PriorityQueue<Node> pq;
    
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        pq = new PriorityQueue<>();

        // dist[i][j] = i에서 j까지 가는 최단거리.
        dist = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);

        for (int s = 0; s < n; s++)
        {
            dist[s][s] = 0;
            pq = new PriorityQueue<>();
            pq.add(new Node(dist[s][s], s));

            while (!pq.isEmpty())
            {
                int v = pq.peek().index;
                int cost = pq.peek().key;
                if (dist[s][v] < cost) continue;

                for (Edge e : adj[v])
                    relax(e);
            }
        }
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
                br = new BufferedReader(new FileReader("/Users/rokaf/Desktop/data.txt"));
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