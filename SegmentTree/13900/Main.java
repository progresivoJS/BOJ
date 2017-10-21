import java.util.*;
import java.io.*;

/**
 * problem 13900
 * 순서쌍의 곱의 합
 * https://www.acmicpc.net/problem/13900
 * written by progresivoJS on 2017.10.21
 */
public class Main
{
    public static void solve(int[] A)
    {
        int n = A.length;
        
        long[] pSum = new long[n];
        pSum[0] = A[0];
        for (int i = 1; i < n; i++)
            pSum[i] = pSum[i-1] + A[i];
        
        long result = 0;
        for (int i = 0; i < n - 1; i++)
            result += pSum[i] * A[i + 1];
        System.out.println(result);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++)
            A[i] = In.nextInt();
        solve(A);
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