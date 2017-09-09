import java.util.*;
import java.io.*;

/**
 * problem 3943
 * 헤일스톤 수열
 * https://www.acmicpc.net/problem/3943
 * written by progresivoJS
 */
public class Main
{
    private static int[] cache;
    private static int max;
    
    public static void solve(int test) throws IOException
    {
        cache = new int[100001];
        Arrays.fill(cache, -1);
        
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            out.write(String.valueOf(hailstone(n)));
            out.write("\n");
        }
        
        out.close();
    }
    
    private static int hailstone(int n)
    {
        if (n == 1)
            return 1;
        
        if (n <= 100000 && cache[n] != -1)
            return cache[n];

        int result = 0;
        if (n % 2 == 0)
            result = Math.max(n, hailstone(n/2));
        else
            result = Math.max(n, hailstone(n * 3  + 1));
        
        if (n <= 100000)
            cache[n] = result;
            
        return result;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int test = In.nextInt();
        solve(test);
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