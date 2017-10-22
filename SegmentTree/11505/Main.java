import java.util.*;
import java.io.*;

/**
 * problem 11505
 * 구간 곱 구하기
 * https://www.acmicpc.net/problem/11505
 * written by progresivoJS on 2017.10.22
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
        private static int M = 1000000007;
        long[] tree;
        int n;
        
        public SegmentTree(int[] array)
        {
            n = array.length;
            tree = new long[4 * n];
            
            init(array, 1, 0, n - 1);
        }
        
        private long init(int[] array, int v, int left, int right)
        {
            if (left == right)
                return tree[v] = array[left];
            int mid = (left + right) / 2;
            return tree[v] = (init(array, 2 * v, left, mid) * init(array, 2 * v + 1, mid + 1, right)) % M;
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
                return tree[v] = value;
            int mid = (left + right) / 2;
            return tree[v] = (update(index, value, 2 * v, left, mid) * update(index, value, 2 * v + 1, mid + 1, right)) % M;
        }
        
        public long query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
        }
        
        private long query(int a, int b, int v, int na, int nb)
        {
            if (b < na || nb < a)
                return 1;
            if (a <= na && nb <= b)
                return tree[v];
            int mid = (na + nb) / 2;
            return (query(a, b, 2 * v, na, mid) * query(a, b, 2 * v + 1, mid + 1, nb)) % M;
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