import java.util.*;
import java.io.*;

/**
 * problem 2887
 * 행성 터널
 * https://www.acmicpc.net/problem/2887
 * written by progresivoJS on 2017.11.08
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        UF uf = new UF(n);
        
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++)
            points[i] = new Point(i, In.nextInt(), In.nextInt(), In.nextInt());
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        Comparator<Point> sortByX = (o1, o2) -> o1.x - o2.x < 0 ? -1 : 1;
        Comparator<Point> sortByY = (o1, o2) -> o1.y - o2.y < 0 ? -1 : 1;
        Comparator<Point> sortByZ = (o1, o2) -> o1.z - o2.z < 0 ? -1 : 1;
        
        Arrays.sort(points, sortByX);
        for (int i = 0; i < n - 1; i++)
        {
            int weight = Math.abs(points[i].x - points[i + 1].x);
            Edge e = new Edge(points[i].index, points[i + 1].index, weight);
            pq.add(e);
        }
        
        Arrays.sort(points, sortByY);
        for (int i = 0; i < n - 1; i++)
        {
            int weight = Math.abs(points[i].y - points[i + 1].y);
            Edge e = new Edge(points[i].index, points[i + 1].index, weight);
            pq.add(e);
        }
        
        Arrays.sort(points, sortByZ);
        for (int i = 0; i < n - 1; i++)
        {
            int weight = Math.abs(points[i].z - points[i + 1].z);
            Edge e = new Edge(points[i].index, points[i + 1].index, weight);
            pq.add(e);
        }
        
        int weight = 0;
        int count = 0;
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
        
        System.out.println(weight);
    }
    
    public static class Edge implements Comparable<Edge>
    {
        int v, w, weight;
        public Edge(int v, int w, int weight)
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        
        public int compareTo(Edge other)
        {
            return this.weight - other.weight;
        }
    }
    
    public static class Point
    {
        int index, x, y, z;
        public Point(int index, int x, int y, int z)
        {
            this.index = index;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
    public static class UF
    {
        int[] parent, size;
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