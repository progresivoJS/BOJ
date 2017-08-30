import java.util.*;
import java.io.*;

/**
 * problem 3896
 * 소수 사이 수열
 * https://www.acmicpc.net/problem/3896
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    private static int M = 1299709;
    public static int solve(int n)
    {
        if (isPrime[n])
            return 0;

        int begin = 0, end = 0;
        for (int i = n - 1; i >= 1; i--)
            if (isPrime[i])
            {
                begin = i;
                break;
            }
        for (int i = n + 1; i <= M; i++)
            if (isPrime[i])
            {
                end = i;
                break;
            }

        return end - begin;
    }

    public static void eratosthenes()
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

        int test = In.nextInt();
        for (int i = 0; i < test; i++)
        {
            System.out.println(solve(In.nextInt()));
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
