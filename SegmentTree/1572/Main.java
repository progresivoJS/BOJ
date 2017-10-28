import java.util.*;
import java.io.*;

/**
 * problem 1572
 * 중앙값
 * https://www.acmicpc.net/problem/1572
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        
        SegmentTree ST = new SegmentTree(array);
        
        long result = 0;
        for (int i = 0; i < n - m; i++)
            result += ST.query(i, i + m);
        System.out.println(result);
    }
    
    private static class SegmentTree
    {
        long[] tree;
        int n;
        public SegmentTree(int[] array)
        {
            n = array.length;
            tree = new long[4 * n];
            
            init(array, 1, 0, n - 1);
        }
        
        private void init(int[] array, int v, int left, int right)
        {
            if (left == right)
            {
                tree[v] = array[left];
                return;
            }
            
            int mid = (left + right) / 2;
            init(array, 2 * v, left, mid);
            init(array, 2 * v + 1, mid + 1, right);
            tree[v] = median(left, right);
        }
        
        public long query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
        }
        
        private long query(int a, int b, int v, int na, int nb)
        {
            
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