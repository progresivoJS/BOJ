import java.util.*;
import java.io.*;

/**
 * problem 11055
 * 가장 큰 증가 부분 수열
 * https://www.acmicpc.net/problem/11055
 * written by progresivoJS
 */
public class Main
{
    private static int n;
    private static int[] seq;
    private static int[] cache;
    public static int solve(int[] seq)
    {
        n = seq.length;
        Main.seq = seq;

        cache = new int[n+1];
        Arrays.fill(cache, -1);

        return lis(-1);
    }

    /**
     * index 이후 seq의 가장 큰 증가 부분 수열의 합
     */
    private static int lis(int index)
    {
        if (index == n)
            return 0;

        int cacheIndex = index + 1;
        if (cache[cacheIndex] != -1)
            return cache[cacheIndex];

        int result = 0;
        if (index != -1)
            result = seq[index];
        for (int i = index + 1; i < n; i++)
        {
            if (index == -1 || seq[index] < seq[i])
            {
                if (index != -1)
                    result = Math.max(result, seq[index] + lis(i));
                else
                    result = Math.max(result, lis(i));
            }
        }

        return cache[cacheIndex] = result;
    }

    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++)
            seq[i] = In.nextInt();
        System.out.println(solve(seq));
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
