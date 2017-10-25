import java.util.*;
import java.io.*;

/**
 * problem 10999
 * 구간 합 구하기 2
 * https://www.acmicpc.net/problem/10999
 * written by progresivoJS on 2017.10.25
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int k = In.nextInt();
        
        long[] array = new long[n];
        for (int i = 0; i < n; i++)
            array[i] = Long.parseLong(In.next());
            
        SegmentTree ST = new SegmentTree(array);
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < m + k; i++)
        {
            if (In.nextInt() == 1)
            {
                int a = In.nextInt() - 1;
                int b = In.nextInt() - 1;
                int diff = In.nextInt();
                ST.update(a, b, diff);
            }
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
        long[] tree;
        long[] lazy;
        int n;
        
        public SegmentTree(long[] array)
        {
            n = array.length;
            
            tree = new long[n * 4];
            lazy = new long[n * 4];
            
            init(array, 1, 0, n - 1);
        }
        
        private long init(long[] array, int v, int left, int right)
        {
            if (left == right)
                return tree[v] = array[left];
            int mid = (left + right) / 2;
            return tree[v] = init(array, 2 * v, left, mid) + init(array, 2 * v + 1, mid + 1, right);
        }
        
        private void propagate(int v, int left, int right)
        {
            if (lazy[v] != 0)
            {
                tree[v] += (right - left + 1) * lazy[v];
                
                // if it is not leaf.
                if (left != right)
                {
                    lazy[v * 2] += lazy[v];
                    lazy[v * 2 + 1] += lazy[v];
                }
                lazy[v] = 0;
            }
        }
        
        // [a, b]에 diff를 더하라.
        public void update(int a, int b, int diff)
        {
            update(a, b, diff, 1, 0, n - 1);
        }
        
        private void update(int a, int b, int diff, int v, int na, int nb)
        {
            propagate(v, na, nb);
            
            if (b < na || nb < a)
                return;
            if (a <= na && nb <= b)
            {
                lazy[v] += diff;
                propagate(v, na, nb);
                return;
            }
            
            int mid = (na + nb) / 2;
            update(a, b, diff, 2 * v, na, mid);
            update(a, b, diff, 2 * v + 1, mid + 1, nb);
            tree[v] = tree[2 * v] + tree[2 * v + 1];
        }
        
        public long query(int left, int right)
        {
            return query(left, right, 1, 0, n - 1);
        }
        
        private long query(int a, int b, int v, int na, int nb)
        {
            propagate(v, na, nb);
            
            if (b < na || nb < a)
                return 0;
            if (a <= na && nb <= b)
                return tree[v];
            int mid = (na + nb) >> 1;
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