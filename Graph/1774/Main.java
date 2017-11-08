import java.util.*;
import java.io.*;

/**
 * problem 1774
 * 우주신과의 교감
 * https://www.acmicpc.net/problem/1774
 * written by progresivoJS on 2017.11.08
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        UF uf = new UF(n);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
            points[i] = new Point(In.nextInt(), In.nextInt());
        
        for (int i = 0; i < m; i++)
            uf.union(In.nextInt() - 1, In.nextInt() - 1);
        
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
            {
                double distance = points[i].distanceTo(points[j]);
                pq.add(new Edge(i, j, distance));
            }
        
        double weight = 0;
        int count = m;
        while (!pq.isEmpty())
        {
            Edge e = pq.poll();
            if (!uf.connected(e.v, e.w))
            {
                uf.union(e.v, e.w);
                weight += e.weight;
                if (++count == n - 1) break;
            }
        }
        
        System.out.printf("%.2f", weight);
    }
    
    public static class Edge implements Comparable<Edge>
    {
        int v, w;
        double weight;
        
        public Edge(int v, int w, double weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        
        public int compareTo(Edge other)
        {
            double diff = this.weight - other.weight;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else
                return 0;
        }
    }
    
    private static class Point
    {
        int x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        
        public double distanceTo(Point other)
        {
            long dx = this.x - other.x;
            long dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
    }
    
    private static class UF
    {
        int[] size, parent;
        
        public UF(int n)
        {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++)
            {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public boolean connected(int p, int q)
        {
            return find(p) == find(q);
        }
        
        public int find(int p)
        {
            if (parent[p] == p)
                return p;
            return parent[p] = find(parent[p]);
        }
        
        public void union(int p, int q)
        {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            
            if (size[rootP] > size[rootQ])
            {
                size[rootP] += size[rootQ];
                parent[rootQ] = rootP;
            }
            else
            {
                size[rootQ] += size[rootP];
                parent[rootP] = rootQ;
            }
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