import java.util.*;
import java.io.*;

/**
 * problem 1562
 * 계단 수
 * https://www.acmicpc.net/problem/1562
 * written by progresivoJS on 2017.09.13
 * 
 * 생각 더 해서 풀자.
 */
public class Main
{
    private static int n;
    private static int[][] cache;
    
    private static int M = 1000 * 1000 * 1000;
    
    public static void solve(int n)
    {
        Main.n = n;
        int result = 0;
        for (int i = 1; i <= 9; i++)
            result = (result + stairNumber(n, i)) % M;
        System.out.println(result);
    }
    
    private static int stairNumber(int n, int first)
    {
        if (n == 1)
            return 1;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        solve(n);
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