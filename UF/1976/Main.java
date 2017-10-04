import java.util.*;
import java.io.*;

/**
 * problem 1976
 * 여행가자
 * https://www.acmicpc.net/problem/1976
 * written by progresivoJS on 2017.10.04
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        UF uf = new UF(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (In.nextInt() == 1)
                    uf.union(i, j);
        
        int[] goal = new int[m];
        for (int i = 0; i < m; i++)
            goal[i] = In.nextInt() - 1;
        
        int parent = uf.find(goal[0]);
        for (int i = 0; i < m; i++)
            if (parent != uf.find(goal[i]))
            {
                System.out.println("NO");
                return;
            }
        System.out.println("YES");
    }
    
    private static class UF
    {
        private static int[] size;
        private static int[] parent;
        
        public UF(int n)
        {
            size = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++)
            {
                size[i] = 1;
                parent[i] = i;
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