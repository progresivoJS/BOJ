import java.util.*;
import java.io.*;

/**
 * problem 1865
 * 웜홀
 * https://www.acmicpc.net/problem/1865
 * written by progresivoJS on 2017.11.05
 */
public class Main
{
    private static int INF = 987654321;
    public static void solve(ArrayList<Edge>[] adj)
    {
        int n = adj.length;
        boolean[] marked = new boolean[n];
        for (int s = 0; s < n; s++)
        {
            if (marked[s]) continue;

            int[] dist = new int[n];
            Arrays.fill(dist, INF);
            dist[s] = 0;
            marked[s] = true;

            Queue<Integer> q = new LinkedList<>();
            q.add(s);
            boolean[] inQ = new boolean[n];
            inQ[s] = true;

            int count = 0;
            while (!q.isEmpty())
            {
                if (count >= n)
                {
                    System.out.println("YES");
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
                            marked[to] = true;

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
        }
        
        System.out.println("NO");
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            int w = In.nextInt();
            
            ArrayList<Edge>[] adj = (ArrayList<Edge>[]) new ArrayList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new ArrayList<>();
            
            // 도로 - 양방향
            for (int i = 0; i < m; i++)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                int weight = In.nextInt();
                adj[from].add(new Edge(from, to, weight));
                adj[to].add(new Edge(to, from, weight));
            }
            // 웜홀 - 단방향
            for (int i = m; i < m + w; i++)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                int weight = -In.nextInt();
                adj[from].add(new Edge(from, to, weight));
            }
            
            solve(adj);
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
