import java.util.*;
import java.io.*;

/**
 * problem 2814
 * BROJ 최소인수
 * https://www.acmicpc.net/problem/2814
 * written by progresivoJS
 * 
 * 시간초과 때문에 못품. 에라토스테네스의 체 쓰려니 메모리 부족 문제.. 나중에 다시 풀자.
 */
public class Main
{
    private static int[] minFactor;
    private static int M = 1000 * 1000 * 1000;
    public static int solve(int n, int p)
    {
        int count = 0;
        for (int i = 1; i <= M; i++)
            if (minFactor(i) == p)
            {
                count ++;
                if (count == n)
                    return i;
            }

        return 0;
    }

    private static int minFactor(int n)
    {
        if (n % 2 == 0)
            return 2;
        if (n % 3 == 0)
            return 3;
        if (n % 5 == 0)
            return 5;
        int sqrt = (int)Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2)
        {
            if (n % i == 0)
                return i;
        }

        return -1;
    }

    private static void eratosthenes()
    {
        minFactor = new int[M+1];
        for (int i = 0; i <= M; i++)
            minFactor[i] = i;
        minFactor[0] = -1;
        minFactor[1] = -1;

        int sqrt = (int) Math.sqrt(M);
        for (int i = 2; i <= sqrt; i++)
            if (minFactor[i] == i)
                for (int j = i * i; j <= M; j += i)
                    if (minFactor[j] == j)
                        minFactor[j] = i;
    }

    public static void main(String[] args)
    {
        In.init();

//        eratosthenes();

        int n = In.nextInt();
        int p = In.nextInt();

        System.out.println(solve(n, p));

//        for (int n = 1; n <= M; n++)
//            for (int p = 1; p <= M; p++)
//                System.out.println(solve(n, p));
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
