import java.util.*;
import java.io.*;

/**
 * problem 1238
 * 파티
 * https://www.acmicpc.net/problem/1238
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    private static int INF = 987654321;
    private static int[][] dist;
    private static PriorityQueue<Pair> pq;
    public static void solve(int x, LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        dist = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);

        for (int i = 0; i < n; i++)
        {
            pq = new PriorityQueue<>();
            int start = i;
            dist[start][start] = 0;

            pq.add(new Pair(start, dist[start][start]));

            while (!pq.isEmpty())
            {
                Pair top = pq.poll();
                int v = top.index;
                int cost = top.key;
                
                if (start != x && v == x) break;
                if (dist[start][v] < cost) continue;
                
                for (Edge e : adj[v])
                    relax(start, e);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, dist[i][x] + dist[x][i]);

        System.out.println(max);
    }

    private static void relax(int start, Edge e)
    {
        int from = e.from;
        int to = e.to;

        if (dist[start][to] > dist[start][from] + e.weight)
        {
            dist[start][to] = dist[start][from] + e.weight;
            pq.add(new Pair(to, dist[start][to]));
        }
    }

    public static void main(String[] args)
    {
        In.init();

        int n = In.nextInt();
        int m = In.nextInt();
        int x = In.nextInt() - 1;

        LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();

        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            int cost = In.nextInt();

            adj[from].add(new Edge(from, to, cost));
        }

        solve(x, adj);
    }

    public static class Edge
    {
        int from, to, weight;

        public Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static class Pair implements Comparable<Pair>
    {
        int index, key;

        public Pair(int index, int key)
        {
            this.index = index;
            this.key = key;
        }

        public int compareTo(Pair other)
        {
            return this.key - other.key;
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