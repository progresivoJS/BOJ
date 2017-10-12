import java.util.*;
import java.io.*;

/**
 * problem 1753
 * 최단경로
 * https://www.acmicpc.net/problem/1753
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    private static int INF = 987654321;
    private static PriorityQueue<Pair> pq;
    private static int[] dist;

    public static void solve(int start, LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        pq = new PriorityQueue<>(n);
        dist = new int[n];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        pq.add(new Pair(start, dist[start]));

        while (!pq.isEmpty())
        {
            Pair top = pq.poll();
            int v = top.index;
            int cost = top.key;

            if (dist[v] < cost) continue;

            for (Edge e : adj[v])
                relax(e);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
            str.append(dist[i] == INF ? "INF" : dist[i]).append('\n');

        System.out.println(str.toString());
    }

    public static void relax(Edge e)
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

        int start = In.nextInt() - 1;

        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            int weight = In.nextInt();
            adj[from].add(new Edge(from, to, weight));
        }

        solve(start, adj);
    }

    private static class Pair implements Comparable<Pair>
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
