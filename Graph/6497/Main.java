import java.util.*;
import java.io.*;

/**
 * problem 6497
 * 전력난
 * https://www.acmicpc.net/problem/6497
 * written by progresivoJS on 2017.11.07
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
            int m = In.nextInt();
            if (n == 0 && m == 0) break;

            UF uf = new UF(n);
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            int totalWeight = 0;
            while (m-- > 0)
            {
                int v = In.nextInt();
                int w = In.nextInt();
                int weight = In.nextInt();
                totalWeight += weight;
                pq.add(new Edge(v, w, weight));
            }

            int count = 0;
            int mstWeight = 0;
            while (!pq.isEmpty())
            {
                Edge e = pq.poll();
                int v = e.v;
                int w = e.w;
                if (!uf.connected(v, w))
                {
                    uf.union(v, w);
                    mstWeight += e.weight;
                    if (++count == n - 1) break;
                }
            }

            str.append(totalWeight - mstWeight).append('\n');
        }

        System.out.println(str);
    }
    
    private static class Edge implements Comparable<Edge>
    {
        int v, w;
        int weight;

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
        private int[] parent;
        private int[] size;

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
