import java.util.*;
import java.io.*;

/**
 * problem 11004
 * K번째 수
 * https://www.acmicpc.net/problem/11004
 * written by progresivoJS on 2017.10.28
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt() - 1;
        
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = In.nextInt();
        
        System.out.println(select(array, k));
    }
    
    public static int select(int[] a, int k)
    {
        shuffle(a);
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi)
        {
            int j = partition(a, lo, hi);
            if (j < k)
                lo = j + 1;
            else if (j > k)
                hi = j - 1;
            else
                return a[k];
        }
        return a[k];
    }
    
    private static int partition(int[] a, int lo, int hi)
    {
        int i = lo, j = hi + 1;
        while (true)
        {
            while (a[++i] < a[lo])
                if (i == hi) break;
            while (a[lo] < a[--j])
                if (j == lo) break;
                
            if (i >= j) break;
            exch(a, i, j);
        }
        
        exch(a, lo, j);
        return j;
    }
    
    private static void shuffle(int[] a)
    {
        int n = a.length;
        for (int i = 0; i < n; i++)
        {
            int r = (int)(Math.random() * (i + 1));
            int temp = a[r];
            a[r] = a[i];
            a[i] = temp;
        }
    }
    
    private static void exch(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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