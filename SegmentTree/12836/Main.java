import java.util.*;
import java.io.*;

/**
 * problem 12836
 * 가계부 (Easy)
 * https://www.acmicpc.net/problem/12836
 * written by progresivoJS on 2017.10.28
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
            // update
            if (In.nextInt() == 1)
            {
                int index = In.nextInt() - 1;
                int value = In.nextInt();
                ST.update(index, value);
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
        long[] tree;
        int n;
        public SegmentTree(int n)
        {
            this.n = n;
            tree = new long[4 * n];
        }
        
        public long update(int index, int value)
        {
            return update(index, value, 1, 0, n - 1);
        }
        
        private long update(int index, int value, int v, int left, int right)
        {
            if (index < left || right < index)
                return tree[v];
            if (left == right)
                return tree[v] += value;
            int mid = (left + right) / 2;
            return tree[v] = update(index, value, 2 * v, left, mid) + update(index, value, 2 * v + 1, mid + 1, right);
        }
        
        public long query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
        }
        
        private long query(int a, int b, int v, int na, int nb)
        {
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