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
    public static void main(String[] args) throws IOException
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        
        SegmentTree ST = new SegmentTree(array);
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        while (m-- > 0)
        {
            int a = In.nextInt() - 1;
            int b = In.nextInt() - 1;
            out.write(String.valueOf(ST.query(a, b)));
            out.newLine();
        }
        out.close();
    }
    
    private static class SegmentTree
    {
        private static int INF = Integer.MAX_VALUE;
        int[] tree;
        int n;
        
        public SegmentTree(int[] array)
        {
            n = array.length;
            tree = new int[4 * n];
            init(array, 1, 0, n - 1);
        }
        
        public int init(int[] array, int v, int left, int right)
        {
            if (left == right)
                return tree[v] = array[left];
            int mid = (left + right) / 2;
            return tree[v] = Math.min(init(array, 2 * v, left, mid),init(array, 2 * v + 1, mid + 1, right));
        }
        
        // find min in range [a, b].
        public int query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
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