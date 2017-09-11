import java.util.*;
import java.io.*;

/**
 * problem 1629
 * 곱셈
 * https://acmicpc.net/problem/1629
 * written by progresivoJS
 */
public class Main
{
    private static int M;
    public static void main(String[] args)
    {
        In.init();
        int a = In.nextInt();
        int b = In.nextInt();
        M = In.nextInt();

        System.out.println(mul(a, b));
    }

    public static long mul(int a, int b)
    {
        long[][] W = new long[2][2];
        W[0][0] = a;
        W[0][1] = W[1][0] = W[1][1] = 0;

        return Matrix.pow(W, b)[0][0];
    }

    public static class Matrix
    {
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
        
        public static long[][] pow(long[][] a, int m)
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

        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}
