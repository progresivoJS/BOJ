import java.util.*;
import java.io.*;

/**
 * 11057
 * 오르막수
 * https://www.acmicpc.net/problem/11057
 * by progresivoJS
 */
public class Main
{
    private static int[][] cache;
    private static int M = 10007;
    public static int solve(int n)
    {
        cache = new int[n+1][10];
        for (int i = 0; i < cache.length; i++)
            Arrays.fill(cache[i], -1);

        int result = 0;
        for (int i = 0; i <= 9; i++)
            result = (result + ascending(n, i)) % M;
        return result;
    }

    private static int ascending(int n, int index)
    {
        if (n == 1)
            return 1;

        if (cache[n][index] != -1)
            return cache[n][index];

        int result = 0;
        for (int i = index; i <= 9; i++)
            result = (result + ascending(n-1, i)) % M;
        return cache[n][index] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n= In.nextInt();
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
