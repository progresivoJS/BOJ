import java.util.*;
import java.io.*;

/**
 * problem 6588
 * Goldbach's Conjecture
 * https://www.acmicpc.net/problem/6588
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    private static int M = 1000 * 1000;

    private static BufferedWriter out;

    public static void solve(int n) throws IOException
    {
        int i;
        for (i = 3; i <= n/2; i += 2)
            if (isPrime[i] && isPrime[n-i])
            {
                StringBuilder str = new StringBuilder();
                str.append(n);
                str.append(" = ");
                str.append(i);
                str.append(" + ");
                str.append(n-i);
                out.write(str.toString());
                out.write("\n");
                break;
            }
        if (i > n/2)
            out.write("Goldbach's conjecture is wrong.");
    }

    private static void eratosthenes()
    {
        isPrime = new boolean[M + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        isPrime[2] = true;

        int sqrt = (int) Math.sqrt(M);
        for (int i = 3; i <= sqrt; i += 2)
            if (isPrime[i])
                for (int j = i * i; j <= M; j += i)
                    isPrime[j] = false;
    }

    public static void main(String[] args) throws IOException
    {
        In.init();
        int n;
        out = new BufferedWriter(new OutputStreamWriter(System.out));

        eratosthenes();
        while ((n = In.nextInt()) != 0)
            solve(n);

        out.close();
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
