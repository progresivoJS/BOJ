import java.util.*;
import java.io.*;

/**
 * problem 1395
 * 스위치
 * https://www.acmicpc.net/problem/1395
 * written by progresivoJS on 2017.10.25
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        SegmentTree ST = new SegmentTree(n);
        
        StringBuilder str = new StringBuilder();
        while (m-- > 0)
        {
            // update_range
            if (In.nextInt() == 0)
            {
                int a = In.nextInt() - 1;
                int b = In.nextInt() - 1;
                ST.update(a, b);
            }
            // query
            else
            {
                int a = In.nextInt() - 1;
                int b = In.nextInt() - 1;
                str.append(ST.query(a, b)).append('\n');
            }
        }
        
        System.out.println(str);
    }
    
    private static class SegmentTree
    {
        int[] lazy;
        int[] tree;
        int n;
        
        public SegmentTree(int n)
        {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }
        
        private void propagate(int v, int start, int end)
        {
            if (lazy[v] != 0)
            {
                if (start != end)
                {
                    lazy[2 * v] ^= 1;
                    lazy[2 * v + 1] ^= 1;
                }
                tree[v] = (end - start + 1) - tree[v];
                lazy[v] = 0;
            }
        }
        
        // flip [a, b]
        public void update(int a, int b)
        {
            update(a, b, 1, 0, n - 1);
        }
        
        private void update(int a, int b, int v, int na, int nb)
        {
            propagate(v, na, nb);
            
            if (b < na || nb < a)
                return;
            if (a <= na && nb <= b)
            {
                lazy[v] ^= 1;
                propagate(v, na, nb);
                return;
            }
            
            int mid = (na + nb) / 2;
            update(a, b, 2 * v, na, mid);
            update(a, b, 2 * v + 1, mid + 1, nb);
            
            tree[v] = tree[2 * v] + tree[2 * v + 1];
        }
        
        public int query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
        }
        
        private int query(int a, int b, int v, int na, int nb)
        {
            propagate(v, na, nb);
            if (b < na || nb < a)
                return 0;
            if (a <= na && nb <= b)
                return tree[v];
            int mid = (na + nb) / 2;
            return query(a, b, 2 * v, na, mid) + query(a, b, 2 * v + 1, mid + 1, nb);
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