import java.util.*;
import java.io.*;

/**
 * problem 11054
 * 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 * written by progresivoJS on 2017.09.13
 */
public class Main
{
    private static int n;
    private static int[] seq;
    
    // 열 부분은 0 일 때 false, 1 일 때 true.
    private static int[][] cache;
    
    public static void solve(int n, int[] seq)
    {
        Main.n = n;
        Main.seq = seq;
        
        cache = new int[n][2];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        
        int result = -1;
        for (int i = 0; i < n; i++)
        {
            result = Math.max(result, bitonic(i, true));
            result = Math.max(result, bitonic(i, false));
        }
        System.out.println(result);
    }
    
    /**
     * [index, last] 에서 increasing 혹은 decreasing 하고 있을 때, 가장 긴 바이토닉 부분 수열의 길이.
     */
    private static int bitonic(int index, boolean isIncreasing)
    {
        if (index == n - 1)
            return 1;
        int incIndex = isIncreasing ? 1 : 0;
        if (cache[index][incIndex] != -1)
            return cache[index][incIndex];
            
        int result = 1;
        for (int next = index; next < n; next++)
            if (isIncreasing)
            {
                if (seq[next] > seq[index])
                    result = Math.max(result, 1 + bitonic(next, true));
                else if (seq[next] < seq[index])
                    result = Math.max(result, 1 + bitonic(next, false));
            }
            else
            {
                if (seq[next] < seq[index])
                    result = Math.max(result, 1 + bitonic(next, false));
            }
            
        return cache[index][incIndex] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        solve(n, seq);
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