import java.util.*;
import java.io.*;

/**
 * problem 1644
 * Sum of Consecutive Prime Numbers 소수의 연속합
 * https://www.acmicpc.net/problem/1644
 * written by progresivoJS
 */
public class Main
{
    private static ArrayList<Integer> primeList;
    private static boolean[] isPrime;
    private static int M = 4 * 1000 * 1000;
    public static int solve(int n)
    {
        int count = 0;
        for (int i = 0; i < primeList.size() && primeList.get(i) <= n; i++)
        {
            int j = i;
            int result = 0;
            while (result <= n)
                if (result < n)
                {
                    result += primeList.get(j);
                    j++;
                    if (j >= primeList.size())
                        break;
                }
                else if (result == n)
                {
                    count++;
                    break;
                }
        }

        return count;
    }

    public static void eratosthenes()
    {
        primeList = new ArrayList<>();

        isPrime = new boolean[M + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        int sqrt = (int)Math.sqrt(M);
        for (int i = 2; i <= sqrt; i++)
            if (isPrime[i])
                for (int j = i * i; j <= M; j += i)
                    isPrime[j] = false;

        for (int i = 0; i <= M; i++)
            if (isPrime[i])
                primeList.add(i);
    }

    public static void main(String[] args)
    {
        In.init();
        eratosthenes();

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
