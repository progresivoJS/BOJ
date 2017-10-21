import java.util.*;
import java.io.*;

/**
 * problem 10868
 * 최소값
 * https://www.acmicpc.net/problem/10868
 * written by progresivoJS on 2017.10.21
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        SegmentTree ST = new SegmentTree(n);
        for (int i = 0; i < n; i++)
            ST.tree[ST.start + i] = In.nextInt();
        ST.construct();
        
        StringBuilder str = new StringBuilder();
        while (m-- > 0)
        {
            int a = In.nextInt() - 1;
            int b = In.nextInt() - 1;
            str.append(ST.query(a, b)).append('\n');
        }
        System.out.println(str);
    }
    
    private static class SegmentTree
    {
        private static int INF = Integer.MAX_VALUE;
        int[] tree;
        int start;
        
        public SegmentTree(int n)
        {
            int height = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
            tree = new int[1 << height];
            
            start = 1;
            while (start < n) start *= 2;
        }
        
        public void construct()
        {
            for (int i = start - 1; i >= 1; i--)
                tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
        }
        
        // find min in range [a, b].
        public int query(int a, int b)
        {
            return query(a, b, 1, 0, start - 1);
        }
        
        private int query(int a, int b, int v, int na, int nb)
        {
            if (nb < a || na > b) return INF;
            if (a <= na && nb <= b) return tree[v];
            
            int mid = (na + nb) / 2;
            return Math.min(query(a, b, 2 * v, na, mid), query(a, b, 2 * v + 1, mid + 1, nb));
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