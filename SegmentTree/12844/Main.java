import java.util.*;
import java.io.*;

/**
 * problem 12844
 * XOR
 * https://www.acmicpc.net/problem/12844
 * written by progresivoJS on 2017.10.25
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        SegmentTree ST = new SegmentTree(array);
        
        StringBuilder str = new StringBuilder();
        int m = In.nextInt();
        while (m-- > 0)
        {
            // update
            if (In.nextInt() == 1)
            {
                int a = In.nextInt();
                int b = In.nextInt();
                int c = In.nextInt();
                
                ST.update(a, b, c);
            }
            // query
            else
            {
                int a = In.nextInt();
                int b = In.nextInt();
                str.append(ST.query(a, b)).append('\n');
            }
        }
        System.out.println(str);
    }
    
    private static class SegmentTree
    {
        int[] tree;
        int[] lazy;
        int n;
        
        public SegmentTree(int[] array)
        {
            n = array.length;
            tree = new int[4 * n];
            lazy = new int[4 * n];
            
            init(array, 1, 0, n - 1);
        }
        
        private int init(int[] array, int v, int left, int right)
        {
            if (left == right)
                return tree[v] = array[left];
            int mid = (left + right) / 2;
            return tree[v] = init(array, 2 * v, left, mid) ^ init(array, 2 * v + 1, mid + 1, right);
        }
        
        private void propagate(int v, int start, int end)
        {
            if (lazy[v] != 0)
            {
                if ((end - start + 1) % 2 == 1)
                    tree[v] ^= lazy[v];
                if (start != end)
                {
                    lazy[2 * v] ^= lazy[v];
                    lazy[2 * v + 1] ^= lazy[v];
                }
                
                lazy[v] = 0;
            }
        }
        
        public int update(int a, int b, int c)
        {
            int x = a < b ? a : b;
            int y = a < b ? b : a;
            return update(x, y, c, 1, 0, n - 1);
        }
        
        private int update(int a, int b, int c, int v, int na, int nb)
        {
            propagate(v, na, nb);
            
            if (b < na || nb < a)
                return tree[v];
            if (a <= na && nb <= b)
            {
                lazy[v] = c;
                propagate(v, na, nb);
                return tree[v];
            }
            
            int mid = (na + nb) / 2;
            return tree[v] = update(a, b, c, 2 * v, na, mid) ^ update(a, b, c, 2 * v + 1, mid + 1, nb);
        }
        
        public int query(int a, int b)
        {
            int x = a < b ? a : b;
            int y = a < b ? b : a;
            return query(x, y, 1, 0, n - 1);
        }
        
        private int query(int a, int b, int v, int na, int nb)
        {
            propagate(v, na, nb);
            if (b < na || nb < a)
                return 0; // identity element of xor.
            if (a <= na && nb <= b)
                return tree[v];
            int mid = (na + nb) / 2;
            return query(a, b, 2 * v, na, mid) ^ query(a, b, 2 * v + 1, mid + 1, nb);
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