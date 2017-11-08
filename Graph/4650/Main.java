import java.util.*;
import java.io.*;

/**
 * problem 4650
 * Jungle Roads
 * https://www.acmicpc.net/problem/4650
 * written by progresivoJS on 2017.11.08
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        StringBuilder str = new StringBuilder();
        while (true)
        {
            int n = In.nextInt();
            if (n == 0) break;
            UF uf = new UF(n);
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int i = 0; i < n - 1; i++)
            {
                In.next();
                int k = In.nextInt();
                while (k-- > 0)
                {
                    int from = i;
                    int to = In.next().charAt(0) - 'A';
                    int weight = In.nextInt();
                    pq.add(new Edge(from, to, weight));
                }
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
            
            str.append(weight).append('\n');
        }
        
        System.out.println(str);
    }
    
    private static class Edge implements Comparable<Edge>
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
    
    private static class UF
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