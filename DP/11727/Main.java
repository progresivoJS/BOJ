import java.util.*;
import java.io.*;
public class Main
{
    private static int[] cache;
    private static final int M = 10007;
    public static int solve(int n)
    {
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        return tiling(n);
    }

    private static int tiling(int n)
    {
        if (n == 1)
            return 1;
        if (n == 2)
            return 3;

        if (cache[n] != -1)
            return cache[n];

        int result = (tiling(n-1) + 2 * tiling(n-2) % M) % M;
        return cache[n] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        System.out.println(solve(n));
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
