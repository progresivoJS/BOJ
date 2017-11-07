import java.util.*;
import java.io.*;

/**
 * problem 1197
 * 최소 스패닝 트리
 * https://www.acmicpc.net/problem/1197
 * written by progresivoJS on 2017.11.07
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
        while (m-- > 0)
            pq.add(new Edge(In.nextInt() - 1, In.nextInt() - 1, In.nextInt()));

        int count = 0;
        int weight = 0;
        while (!pq.isEmpty())
        {
            Edge e = pq.poll();
            int v = e.from;
            int w = e.to;
            if (!uf.connected(v, w))
            {
                uf.union(v, w);
                weight += e.weight;
                if (++count == n - 1) break;
            }
        }

        System.out.println(weight);
    }
    
    private static class Edge implements Comparable<Edge>
    {
        int from, to;
        int weight;

        public Edge(int from, int to, int weight)
        {
            this.from = from;
            this.to = to;
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
