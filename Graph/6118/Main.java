import java.util.*;
import java.io.*;

/**
 * problem 6118
 * 숨바꼭질
 * https://www.acmicpc.net/problem/6118
 * written by progresivoJS on 2017.10.12
 */
public class Main
{
    private static int INF = 987654321;
    private static int[] dist;
    private static PriorityQueue<Pair> pq;

    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;

        dist = new int[n];
        Arrays.fill(dist, INF);
        pq = new PriorityQueue<>(n);

        int start = 0;
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
        
        int index = -1;
        int max = 0;
        int count = 1;
        for (int i = 0; i < n; i++)
        {
            if (max < dist[i])
            {
                index = i;
                max = dist[i];
                count = 1;
            }
            else if (max == dist[i])
                count++;
        }
        
        System.out.println(index + 1 + " " + max + " " + count);
    }

    public static void relax(Edge e)
    {
        int v = e.from();
        int w = e.to();

        if (dist[w] > dist[v] + e.weight())
        {
            dist[w] = dist[v] + e.weight();
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
            adj[from].add(new Edge(from, to, 1));
            adj[to].add(new Edge(to, from, 1));
        }
        
        solve(adj);
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

        public int from()
        {
            return from;
        }

        public int to()
        {
            return to;
        }

        public int weight()
        {
            return weight;
        }
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
    
