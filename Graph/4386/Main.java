import java.util.*;
import java.io.*;

/**
 * problem 4386
 * 별자리 만들기
 * https://www.acmicpc.net/problem/4386
 * written by progresivoJS on 2017.11.07
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
            points[i] = new Point(Double.parseDouble(In.next()), Double.parseDouble(In.next()));
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            {
                Edge e = new Edge(i, j, points[i].distTo(points[j]));
                pq.add(e);
            }
        
        UF uf = new UF(n);
        
        int count = 0;
        double weight = 0;
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
    
    public static class UF
    {
        int[] parent;
        int[] size;
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
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            else
            {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }
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
    
    public static class Point
    {
        double x, y;
        public Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
        
        public double distTo(Point other)
        {
            double dx = this.x - other.x;
            double dy = this.y - other.y;
            return Math.sqrt(dx * dx + dy * dy);
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
