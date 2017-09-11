import java.util.*;
import java.io.*;

/**
 * problem 11444
 * 피보나치 수 6
 * https://acmicpc.net/problem/11444
 * written by progresivoJS
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        long n = In.nextLong();
        System.out.println(fibo(n));
    }

    public static long fibo(long n)
    {
        long[][] W = new long[2][2];
        W[0][0] = 0;
        W[0][1] = W[1][0] = W[1][1] = 1;

        return Matrix.pow(W, n-1)[1][1];
    }

    public static class Matrix
    {
        private static int M = 1000000007;
        public static long[][] identity(int n)
        {
            long[][] a = new long[n][n];
            for (int i = 0; i < n; i++)
                a[i][i] = 1;
            return a;
        }

        public static long[][] mul(long[][] a, long[][] b)
        {
            int m1 = a.length;
            int n1 = a[0].length;
            int m2 = b.length;
            int n2 = b[0].length;
            if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions");

            long[][] c = new long[m1][n2];
            for (int i = 0; i < m1; i++)
                for (int j = 0; j < n2; j++)
                    for (int k = 0; k < n1; k++)
                        c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % M;
            return c;
        }

        public static long[][] pow(long[][] a, long m)
        {
            if (a.length != a[0].length)
                throw new RuntimeException("Illegal matrix dimensions");

            if (m == 0)
                return Matrix.identity(a.length);

            if (m % 2 == 1)
                return Matrix.mul(pow(a, m-1), a);

            long[][] half = pow(a, m/2);
            return Matrix.mul(half, half);
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

        public static long nextLong()
        {
            return Long.parseLong(next());
        }
    }
}
