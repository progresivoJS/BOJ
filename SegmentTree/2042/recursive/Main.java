import java.util.*;
import java.io.*;

/**
 * problem 2042
 * 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 * written by progresivoJS on 2017.10.21
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int k = In.nextInt();
        
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        SegmentTree ST = new SegmentTree(array);
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < m + k; i++)
        {
            int operation = In.nextInt();
            
            // update
            if (operation == 1)
            {
                int index = In.nextInt() - 1;
                long value = Long.parseLong(In.next());
                ST.update(index, value);
            }
            // range sum query
            else
            {
                int a = In.nextInt() - 1;
                int b = In.nextInt() - 1;
                str.append(ST.query(a, b) + "\n");
            }
        }
        
        System.out.println(str);
    }
    
    public static class SegmentTree
    {
        private long[] tree;
        private int n;
        
        public SegmentTree(int[] array)
        {
            n = array.length;
            tree = new long[4 * n];
            init(array, 0, n-1, 1);
        }
        
        public long init(int[] array, int left, int right, int v)
        {
            if (left == right)
                return tree[v] = array[left];
            int mid = (left + right) / 2;
            return tree[v] = init(array, left, mid, v * 2) + init(array, mid + 1, right, v * 2 + 1);
        }
        
        public long query(int a, int b)
        {
            return query(a, b, 1, 0, n-1);
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
        
        public long update(int index, long value)
        {
            return update(index, value, 1, 0, n-1);
        }
        
        private long update(int index, long value, int v, int na, int nb)
        {
            if (index < na || nb < index)
                return tree[v];
            if (na == nb)
                return tree[v] = value;
            int mid = (na + nb) / 2;
            return tree[v] = (update(index, value, 2 * v, na, mid) + update(index, value, 2 * v + 1, mid + 1, nb));
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