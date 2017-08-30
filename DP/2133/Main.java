import java.util.*;
import java.io.*;

/**
 * 2133
 * Tri Tiling
 * https://www.acmicpc.net/problem/2133
 * by progresivoJS
 */
public class Main
{
    private static int[] cache;
    public static int solve(int n)
    {
        cache = new int[n+1];
        Arrays.fill(cache, -1);

        if (n == 0)
            return 0;
        if (n % 2 == 1)
            return 0;

        return tiling(n);
    }

    private static int tiling(int n)
    {
        if (n == 2)
            return 3;

        if (cache[n] != -1)
            return cache[n];

        int result = 2;
        result += 3 * tiling(n-2);
        for (int i = n-4; i >= 2; i -= 2)
            result += 2 * tiling(i);

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
