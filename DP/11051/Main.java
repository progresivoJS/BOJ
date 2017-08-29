import java.util.*;
import java.io.*;
public class Main
{
    private static int[][] cache;
    private static final int M = 10007;
    public static int solve(int n, int k)
    {
        cache = new int[n+1][n+1];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);
        return combination(n, k);
    }

    private static int combination(int n, int k)
    {
        if (n == k)
            return 1;
        if (k == 0)
            return 1;

        if (cache[n][k] != -1)
            return cache[n][k];

        int result = (combination(n-1, k-1) % M + combination(n-1, k) % M) % M;
        return cache[n][k] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int k = In.nextInt();

        System.out.println(solve(n, k));
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
