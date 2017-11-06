import java.util.*;
import java.io.*;

/**
 * problem 11657
 * 타임머신
 * https://www.acmicpc.net/problem/11657
 * written by progresivoJS on 2017.11.05
 */
public class Main
{
    public static final int INF = 987654321;
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        boolean[] inQ = new boolean[n];
        inQ[0] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        int count = 0;
        while (!q.isEmpty())
        {
            if (count >= n)
            {
                System.out.println(-1);
                return;
            }

            int size = q.size();
            while (size-- > 0)
            {
                int v = q.poll();
                inQ[v] = false;
                for (Edge e : adj[v])
                {
                    int from = e.from;
                    int to = e.to;
                    if (dist[to] > dist[from] + e.weight)
                    {
                        dist[to] = dist[from] + e.weight;
                        if (!inQ[to])
                        {
                            q.add(to);
                            inQ[to] = true;
                        }
                    }
                }
            }
            count++;
        }
        
        StringBuilder str = new StringBuilder();
        for (int i = 1; i < n; i++)
        {
            if (dist[i] != INF)
                str.append(dist[i]).append('\n');
            else
                str.append(-1).append('\n');
        }
        System.out.println(str);
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
            int cost = In.nextInt();
            adj[from].add(new Edge(from, to, cost));
        }
        
        solve(adj);
    }
    
    private static class Edge
    {
        int from , to, weight;
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
