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
    public static void solve(int n, Edge[] adj)
    {
        boolean[] marked = new boolean[n];
        for (int s = 0; s < n; s++)
        {
            if (marked[s]) continue;
            int[] dist = new int[n];
            Arrays.fill(dist, INF);
            dist[s] = 0;
            marked[s] = true;
            
            for (int i = 0; i < n; i++)
                for (Edge e : adj)
                {
                    int from = e.from;
                    int to = e.to;
                    if (dist[from] != INF && dist[to] > dist[from] + e.weight)
                    {
                        marked[to] = true;
                        dist[to] = dist[from] + e.weight;
                        if (i == n - 1)
                        {
                            System.out.println("YES");
                            return;
                        }   
                    }
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
            
            Edge[] adj = new Edge[2 * m + w];
            
            // 도로 - 양방향
            for (int i = 0; i < 2 * m; i += 2)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                int weight = In.nextInt();
                adj[i] = new Edge(from, to, weight);
                adj[i + 1] = new Edge(to, from, weight);
            }
            // 웜홀 - 단방향
            for (int i = 2 * m; i < 2 * m + w; i++)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                int weight = -In.nextInt();
                adj[i] = new Edge(from, to, weight);
            }
            
            solve(n, adj);
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