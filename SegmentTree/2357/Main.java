import java.util.*;
import java.io.*;

/**
 * problem 2357
 * 최소값과 최대값
 * https://www.acmicpc.net/problem/2357
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
        {
            int value = In.nextInt();
            ST.minTree[ST.start + i] = value;
            ST.maxTree[ST.start + i] = value;
        }
        ST.construct();
        
        StringBuilder str = new StringBuilder();
        while (m-- > 0)
        {
            int a = In.nextInt() - 1;
            int b = In.nextInt() - 1;
            str.append(ST.minQuery(a, b)).append(' ').append(ST.maxQuery(a, b)).append('\n');
        }
        System.out.println(str);
    }
    
    public static class SegmentTree
    {
        int[] maxTree;
        int[] minTree;
        int start;
        
        public SegmentTree(int n)
        {
            int height = (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
            maxTree = new int[1 << height];
            minTree = new int[1 << height];
            
            start = 1;
            while (start < n) start *= 2;
        }
        
        public void construct()
        {
            for (int i = start - 1; i >= 1; i--)
            {
                maxTree[i] = Math.max(maxTree[2 * i], maxTree[2 * i + 1]);
                minTree[i] = Math.min(minTree[2 * i], minTree[2 * i + 1]);
            }
        }
        
        public int maxQuery(int a, int b)
        {
            return maxQuery(a, b, 1, 0, start - 1);
        }
        
        private int maxQuery(int a, int b, int v, int na, int nb)
        {
            if (nb < a || b < na) return Integer.MIN_VALUE;
            if (a <= na && nb <= b) return maxTree[v];
            
            int mid = (na + nb) / 2;
            return Math.max(maxQuery(a, b, 2 * v, na, mid), maxQuery(a, b, 2 * v + 1, mid + 1, nb));
        }
        
        public int minQuery(int a, int b)
        {
            return minQuery(a, b, 1, 0, start - 1);
        }
        
        private int minQuery(int a, int b, int v, int na, int nb)
        {
            if (b < na || nb < a) return Integer.MAX_VALUE;
            if (a <= na && b >= nb) return minTree[v];
            
            int mid = (na + nb) / 2;
            return Math.min(minQuery(a, b, 2 * v, na, mid), minQuery(a, b, 2 * v + 1, mid + 1, nb));
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