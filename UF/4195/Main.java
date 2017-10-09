import java.util.*;
import java.io.*;

/**
 * problem 4195
 * 친구 네트워크
 * https://www.acmicpc.net/problem/4195
 * written by progresivoJS on 2017.10.09
 */
public class Main
{
    private static StringBuilder str;
    private static void solve(String[] from, String[] to)
    {
        int n = from.length;
        HashMap<String, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            if (!map.containsKey(from[i]))
                map.put(from[i], count++);
            if (!map.containsKey(to[i]))
                map.put(to[i], count++);
        }
        
        UF uf = new UF(map.size());
        for (int i = 0; i < n; i++)
        {
            int a = map.get(from[i]);
            int b = map.get(to[i]);
            str.append(uf.union(a, b)).append('\n');
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        int test = In.nextInt();
        while (test-- > 0)
        {
            int relationship = In.nextInt();
            String[] from = new String[relationship];
            String[] to = new String[relationship];
            for (int i = 0; i < relationship; i++)
            {
                from[i] = In.next();
                to[i] = In.next();
            }
            
            solve(from, to);
        }
        
        System.out.println(str.toString());
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
        
        public int union(int p, int q)
        {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return size[rootP];
            
            if (size[rootP] > size[rootQ])
            {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
                return size[rootP];
            }
            else
            {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
                return size[rootQ];
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