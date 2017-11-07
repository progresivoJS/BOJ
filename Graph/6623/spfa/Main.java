import java.util.*;
import java.io.*;

/**
 * problem 6623
 * Arbitrage?
 * https://www.acmicpc.net/problem/6623
 * written by progresivoJS on 2017.11.06
 */
public class Main
{
    private static double INF = Double.MAX_VALUE;
    private static StringBuilder str;
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        boolean[] marked = new boolean[n];
        for (int s = 0; s < n; s++)
        {
            if (marked[s]) continue;
            double[] dist = new double[n];
            Arrays.fill(dist, INF);
            dist[s] = 0;
            marked[s] = true;
            
            Queue<Integer> q = new LinkedList<>();
            q.add(s);
            boolean[] inQ = new boolean[n];
            inQ[s] = true;
            
            int count = 0;
            boolean isArbitrage = false;
            while (!q.isEmpty())
            {
                if (count >= n)
                {
                    isArbitrage = true;
                    break;
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
            
            if (isArbitrage)
            {
                str.append("Arbitrage").append('\n');
                return;
            }
        }
        
        str.append("Ok").append('\n');
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        while (true)
        {
            int n = In.nextInt();
            if (n == 0) break;
            
            LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++)
                map.put(In.next(), i);
            
            int m = In.nextInt();
            for (int i = 0; i < m; i++)
            {
                int from = map.get(In.next());
                int to = map.get(In.next());
                String[] s = In.next().split(":");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                
                adj[from].add(new Edge(from, to, -Math.log((double)b/a)));
            }
            
            solve(adj);
        }
        System.out.println(str);
    }
    
    private static class Edge
    {
        int from, to;
        double weight;
        public Edge(int from, int to, double weight)
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