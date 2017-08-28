import java.util.*;
import java.io.*;
public class Main
{
    private static int[] cache;

    public static int solve(int n)
    {
        cache = new int[n+1];
        Arrays.fill(cache, -1);
        return square(n);
    }

    /**
     * n을 제곱수의 합으로 표현할 때 항의 갯수의 최솟값.
     */
    private static int square(int n)
    {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;

        if (cache[n] != -1)
            return cache[n];

        int result = 987654321;

        // 큰 수를 뺀다고 해서, 그 수의 square(n) 값이 작다는 보장은 없다.
        for (int i = 1; i*i <= n; i++)
            result = Math.min(result, 1 + square(n - i*i));

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

/**
 * N이 너무 커서, recursive 방법으로 하기에는 depth가 너무 깊다.
 * 따라서 iterative DP로 풀어야 하는 문제이다.
 */
