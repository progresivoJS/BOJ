import java.util.*;
import java.io.*;

/**
 * problem 5676
 * 음주 코딩
 * https://www.acmicpc.net/problem/5676
 * written by progresivoJS on 2017.10.22
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        In.init();
        StringBuilder str = new StringBuilder();
        while (true)
        {
            String line = In.nextLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] array = new int[n];
            for (int i = 0; i < n; i++)
                array[i] = In.nextInt();
            
            SegmentTree ST = new SegmentTree(array);
            
            while (m-- > 0)
            {
                String s = In.next();
                // update
                if (s.charAt(0) == 'C')
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
                    int result = ST.query(a, b);
                    if (result > 0)
                        str.append('+');
                    else if (result < 0)
                        str.append('-');
                    else
                        str.append('0');
                }
            }
            str.append('\n');
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
            {
                if (array[left] > 0)
                    return tree[v] = 1;
                else if (array[left] < 0)
                    return tree[v] = -1;
                else
                    return tree[v] = 0;
            }
            
            int mid = (left + right) / 2;
            return tree[v] = init(array, 2 * v, left, mid) * init(array, 2 * v + 1, mid + 1, right);
        }
        
        public int update(int index, int value)
        {
            return update(index, value, 1, 0, n - 1);
        }
        
        private int update(int index, int value, int v, int left, int right)
        {
            if (index < left || right < index)
                return tree[v];
            if (left == right)
            {
                if (value > 0)
                    return tree[v] = 1;
                else if (value < 0)
                    return tree[v] = -1;
                else
                    return tree[v] = 0;
            }
            int mid = (left + right) / 2;
            return tree[v] = update(index, value, 2 * v, left, mid) * update(index, value, 2 * v + 1, mid + 1, right);
        }
        
        public int query(int a, int b)
        {
            return query(a, b, 1, 0, n - 1);
        }
        
        private int query(int a, int b, int v, int na, int nb)
        {
            if (b < na || nb < a)
                return 1;
            if (a <= na && nb <= b)
                return tree[v];
            int mid = (na + nb) / 2;
            return query(a, b, 2 * v, na, mid) * query(a, b, 2 * v + 1, mid + 1, nb);
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
        
        public static String nextLine() throws IOException
        {
            return br.readLine();
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