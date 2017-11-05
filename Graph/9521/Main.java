import java.util.*;
import java.io.*;

/**
 * problem 9521
 * 색칠 공부
 * https://www.acmicpc.net/problem/9521
 * written by progresivoJS on 2017.11.04
 */
public class Main
{
    public static void solve(int[] fi, int k)
    {
        int n = fi.length;
        
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();
        int[] fi = new int[n];
        for (int i = 0; i < n; i++)
            fi[i] = In.nextInt() - 1;
        
        solve(fi, k);
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