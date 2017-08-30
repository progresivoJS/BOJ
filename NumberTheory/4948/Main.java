import java.util.*;
import java.io.*;

/**
 * problem 4948
 * 베르트랑 공준
 * https://www.acmicpc.net/problem/4948
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    private static int M = 123456 * 2;

    public static int solve(int n)
    {
        int count = 0;
        for (int i = n + 1; i <= 2 * n; i++)
        {
            if (isPrime[i])
                count++;
        }

        return count;
    }

    private static void eratosthenes()
    {
        isPrime = new boolean[M + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        int sqrt = (int) Math.sqrt(M);
        for (int i = 2; i <= sqrt; i++)
            if (isPrime[i])
                for (int j = i * i; j <= M; j += i)
                    isPrime[j] = false;
    }

    public static void main(String[] args)
    {
        In.init();

        eratosthenes();

        int n;
        while ((n = In.nextInt()) > 0)
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
