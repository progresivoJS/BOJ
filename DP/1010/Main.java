import java.util.*;
import java.io.*;
public class Main
{
    private static int[][] cache;
    public static int solve(int n, int m)
    {
        cache = new int[n+1][m+1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        return bridge(n, m);
    }
    
    /**
     * 왼쪽에 n, 오른쪽에 m 일때 다리를 놓을 수 있는 경우의 수.
     */
    private static int bridge(int n, int m)
    {
        if (n > m)
            return 0;
        if (n == 1)
            return m;
        
        if (cache[n][m] != -1)
            return cache[n][m];
        
        int result = 0;
        for (int i = 1; i < m; i++)
            result += bridge(n-1, m-i);
        
        return cache[n][m] = result;
    }
    
    public static void main(String[] args)
    {
        In.init();
        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            int n = In.nextInt();
            int m = In.nextInt();
            System.out.println(solve(n, m));
        }
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