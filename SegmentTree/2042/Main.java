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
        
        SegmentTree ST = new SegmentTree(n);
        for (int i = 0; i < n; i++)
            ST.tree[ST.start + i] = Long.parseLong(In.next());
        ST.construct();
        
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
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                str.append(ST.sum(from, to) + "\n");
            }
        }
        
        System.out.println(str);
    }
    
    public static class SegmentTree
    {
        private long[] tree;
        private int start;
        
        public SegmentTree(int n)
        {
            int height = (int)Math.ceil(Math.log(n)/Math.log(2)) + 1;
            tree = new long[1 << height];
            
            start = 1;
            while (start < n)
                start *= 2;
        }
        
        public void construct()
        {
            for (int i = start - 1; i >= 1; i--)
                tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
        
        public void update(int index, long value)
        {
            index += start;
            tree[index] = value;
            while (index > 1)
            {
                index /= 2;
                tree[index] = tree[index * 2] + tree[index * 2 + 1];
            }
        }
        
        public long sum(int left, int right)
        {
            return sum(left, right, 1, 0, start - 1);
        }
        
        private long sum(int a, int b, int v, int na, int nb)
        {
            if (b < na || a > nb) return 0;
            if (a <= na && b >= nb) return tree[v];
            
            int mid = (na + nb) / 2;
            return sum(a, b, 2 * v, na, mid) + sum(a, b, 2 * v + 1, mid + 1, nb);
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