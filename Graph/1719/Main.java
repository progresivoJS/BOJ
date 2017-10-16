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
    private static Edge[][] edgeTo;
    private static PriorityQueue<Node> pq;
    
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        pq = new PriorityQueue<>();
        
        int[][] distance = dijkstra(adj);
        
        int[][] result = new int[n][n];
        for (int s = 0; s < n; s++)
            for (int t = 0; t < n; t++)
            {
                Edge e = edgeTo[s][t];
                if (e != null)
                    result[t][s] = e.from + 1;
            }
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == j)
                {
                    str.append('-' + " ");
                    continue;
                }
                str.append(result[i][j] + " ");
            }
            str.append('\n');
        }
        System.out.println(str);
    }
    
    private static int[][] dijkstra(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        
        // edgeTo[s][v] = s에서의 최단경로에서, v 로 향하는 edge.
        edgeTo = new Edge[n][n];
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
                pq.poll();
                if (dist[s][v] < cost) continue;

                for (Edge e : adj[v])
                    relax(s, e);
            }
        }
        
        return dist;
    }

    private static void relax(int s, Edge e)
    {
        int v = e.from;
        int w = e.to;
        if (dist[s][w] > dist[s][v] + e.weight)
        {
            dist[s][w] = dist[s][v] + e.weight;
            pq.add(new Node(dist[s][w], w));
            edgeTo[s][w] = e;
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