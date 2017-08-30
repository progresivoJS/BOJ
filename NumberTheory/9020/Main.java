import java.util.*;
import java.io.*;

/**
 * problem 9020
 * Goldbach's Conjecture
 * https://www.acmicpc.net/problem/9020
 * written by progresivoJS
 */
public class Main
{
    private static boolean[] isPrime;
    private static int M = 10000;

    private static BufferedWriter out;

    public static void solve(int n) throws IOException
    {
        if (n == 4)
        {
            StringBuilder str = new StringBuilder();
            str.append(2);
            str.append(" ");
            str.append(2);
            out.write(str.toString());
            out.write("\n");
            return;
        }

        int i = n/2 - 1;
        if (i % 2 == 0)
            i += 1;
        for (; i >= 3; i -= 2)
            if (isPrime[i] && isPrime[n-i])
            {
                StringBuilder str = new StringBuilder();
                str.append(i);
                str.append(" ");
                str.append(n-i);
                out.write(str.toString());
                out.write("\n");
                break;
            }
    }

    private static void eratosthenes()
    {
        isPrime = new boolean[M + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        isPrime[2] = true;

        int sqrt = (int) Math.sqrt(M);
        for (int i = 2; i <= sqrt; i++)
            if (isPrime[i])
                for (int j = i * i; j <= M; j += i)
                    isPrime[j] = false;
    }

    public static void main(String[] args) throws IOException
    {
        In.init();
        out = new BufferedWriter(new OutputStreamWriter(System.out));

        eratosthenes();

        int test = In.nextInt();
        for (int i = 0; i < test; i++)
            solve(In.nextInt());

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
