import java.util.*;
import java.io.*;

/**
 * problem 1717
 * 집합의 표현
 * https://www.acmicpc.net/problem/1717
 * written by progresivoJS on 2017.10.04
 */
public class Main
{
    private static int[] parent;
    private static int[] size;
    public static void main(String[] args)
    {
        In.init();
        StringBuilder str = new StringBuilder();
        int n = In.nextInt();
        int m = In.nextInt();
        
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
        {
            parent[i] = i;
            size[i] = 1;
        }
        
        while (m-- > 0)
        {
            boolean isUnion = In.nextInt() == 0;
            int p = In.nextInt();
            int q = In.nextInt();
            // union operation
            if (isUnion)
                union(p, q);
            // connected operation
            else
                str.append(connected(p, q) ? "YES\n" : "NO\n");
        }
        
        System.out.println(str.toString());
    }
    
    public static boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }
    
    private static int find(int p)
    {
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root)
        {
            int newp = parent[p];
            parent[p] = root;
            p = newp;
        }
        return root;
    }
    
    public static void union(int p, int q)
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