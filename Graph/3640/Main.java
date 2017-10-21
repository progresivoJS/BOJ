import java.util.*;
import java.io.*;

/**
 * problem 3640
 * 제독
 * https://www.acmicpc.net/problem/3640
 * written by progresivoJS on 2017.10.21
 */
public class Main
{
    private static StringBuilder str;
    private static int INF = 987654321;
    
    private static int[] distTo;
    private static PriorityQueue<Node> pq;
    
    private static boolean[] onOptimalPath;
    
    public static void solve(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        int result = 0;
        onOptimalPath = new boolean[n];
        
        int[] distFromS = dijkstra(adj, 0);
        int[] distFromT = dijkstra(reverse(adj), n - 1);
        
        int shortestPathLength = distFromS[n - 1];
        // for (int i : distFromS)
        // {
        //     System.out.println(i);
        // }
        for (int i : distFromT)
        {
            System.out.println(i);
        }
        
        for (int v = 0; v < n; v++)
            for (Edge e : adj[v])
            {
                int w = e.to;
                if (distFromS[v] + distFromT[w] + e.weight == shortestPathLength || distFromS[w] + distFromT[v] + e.weight == shortestPathLength)
                {
                    onOptimalPath[v] = true;
                    onOptimalPath[w] = true;
                }
            }
        onOptimalPath[0] = onOptimalPath[n - 1] = false;
        // for (int i = 0; i < n; i++)
        // {
        //     if (onOptimalPath[i])
        //         System.out.println(i);
        // }
        
        int[] secondBest = dijkstra(adj, 0);
        
        result = distFromS[n - 1] + secondBest[n - 1];
        // System.out.println(distFromS[n - 1]);
        // System.out.println(secondBest[n - 1]);
        System.out.println(result);
    }
    
    private static LinkedList<Edge>[] reverse(LinkedList<Edge>[] adj)
    {
        int n = adj.length;
        LinkedList<Edge>[] reverse = (LinkedList<Edge>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            reverse[i] = new LinkedList<>();
        
        for (int v = 0; v < n; v++)
            for (Edge e : adj[v])
            {
                int w = e.to;
                int weight = e.weight;
                reverse[w].add(new Edge(w, v, weight));
            }
            
        return reverse;
    }
    
    private static int[] dijkstra(LinkedList<Edge>[] adj, int s)
    {
        int n = adj.length;
        distTo = new int[n];
        Arrays.fill(distTo, INF);
        distTo[s] = 0;
        
        pq = new PriorityQueue<>();
        pq.add(new Node(distTo[s], s));
        
        while (!pq.isEmpty())
        {
            int v = pq.peek().index;
            int cost = pq.peek().key;
            pq.poll();
            
            if (distTo[v] < cost) continue;
            
            for (Edge e : adj[v])
                if (!onOptimalPath[e.to])
                    relax(e);
        }
        
        return distTo;
    }
    
    private static void relax(Edge e)
    {
        int v = e.from;
        int w = e.to;
        if (distTo[w] > distTo[v] + e.weight)
        {
            distTo[w] = distTo[v] + e.weight;
            pq.add(new Node(distTo[w], w));
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        while (true)
        {
            String line = In.nextLine();
            if (line == null) break;
            
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            LinkedList<Edge>[] adj = (LinkedList<Edge>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            
            while (m-- > 0)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                int weight = In.nextInt();
                
                adj[from].add(new Edge(from, to, weight));
            }
            
            solve(adj);
        }
        
        System.out.println(str);
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
        boolean isOptimal;
        public Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.isOptimal = false;
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
        
        public static String nextLine()
        {
            try
            {
                return br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            return null;
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