import java.util.*;
import java.io.*;

/**
 * problem 11003
 * 최소값 찾기
 * https://www.acmicpc.net/problem/11003
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int M = In.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        
        SegmentTree ST = new SegmentTree(array);
        
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            if (i < M - 1)
                str.append(ST.query(0, i));
            else
                str.append(ST.query(i - M + 1, i));
            str.append(' ');
        }
        System.out.println(str);
    }
    
    private static class SegmentTree
    {
        int[] tree;
        int n;
        
        public SegmentTree(int[] array)
        {
            n = array.length;
            tree = new int[4 * n];
            
            init(array, 1, 0, n - 1);
        }
        
        private int init(int[] array, int v, int left, int right)
        {
            if (left == right)
                return tree[v] = array[left];
            int mid = (left + right) / 2;
            return tree[v] = Math.min(init(array, 2 * v, left, mid), init(array, 2 * v + 1, mid + 1, right));
        }
        
        public int query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
        }
        
        private int query(int a, int b, int v, int na, int nb)
        {
            if (b < na || nb < a)
                return Integer.MAX_VALUE;
            if (a <= na && nb <= b)
                return tree[v];
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