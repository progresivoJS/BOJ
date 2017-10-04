import java.util.*;
import java.io.*;

/**
 * problem 10775
 * 공항
 * https://www.acmicpc.net/problem/10775
 * written by progresivoJS on 2017.10.04
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int g = In.nextInt(); // gate 수
        int p = In.nextInt(); // plane 수
        
        UF uf = new UF(g);
        
        // dock[i] = i번째 비행기가 1번부터 dock[i]까지 게이트 중에 하나에 도킹하려고 한다.
        int[] dock = new int[p];
        for (int i = 0; i < p; i++)
            dock[i] = In.nextInt() - 1;
        
        boolean[] marked = new boolean[g];
        int count = 0;
        for (int i = 0; i < p; i++)
        {
            if (!marked[dock[i]])
            {
                marked[dock[i]] = true;
                count ++;
            }
            else
            {
                while (-- dock[i] >= 0)
                    if (!marked[dock[i]])
                    {
                        marked[dock[i]] = true;
                        count++;
                        break;
                    }
            }
        }
        
        System.out.println(count);
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