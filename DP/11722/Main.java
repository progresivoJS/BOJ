import java.util.*;
import java.io.*;

/**
 * problem 11722
 * 11722 가장 긴 감소하는 부분수열
 * https://www.acmicpc.net/problem/11722
 * written by progresivoJS on 2017.09.13
 */
public class Main
{
    private static int n;
    private static int[] seq;
    private static int[] cache;
    
    private static void solve(int n, int[] seq)
    {
        Main.n = n;
        Main.seq = seq;
        cache = new int[n];
        Arrays.fill(cache, -1);
        
        int result = 0;
        for (int i = 0; i < n; i++)
            result = Math.max(result, descSeq(i));
        System.out.println(result);
    }
    
    /**
     * index부터 시작하는 감소하는 부분수열들의 길이의 최댓값.
     */
    private static int descSeq(int index)
    {
        if (cache[index] != -1)
            return cache[index];
            
        int result = 1;
        for (int next = index; next < n; next++)
            if (seq[next] < seq[index])
                result = Math.max(result, 1 + descSeq(next));
        
        return cache[index] = result;
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